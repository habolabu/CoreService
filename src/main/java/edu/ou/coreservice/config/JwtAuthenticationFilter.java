package edu.ou.coreservice.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ou.coreservice.data.pojo.request.impl.token.RefreshTokenRequest;
import edu.ou.coreservice.data.pojo.request.impl.token.TokenRequest;
import edu.ou.coreservice.data.pojo.response.impl.RefreshTokenResponse;
import edu.ou.coreservice.queue.auth.external.token.TokenCheckValidQueueE;
import edu.ou.coreservice.queue.auth.external.token.TokenRefreshQueueE;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    JwtTokenProvider tokenProvider;
    @Autowired
    UserDetailsService accountDetailsService;
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    ObjectMapper objectMapper;

    /**
     * filter request before call api
     *
     * @param request     request from client
     * @param response    response from server
     * @param filterChain filter request
     * @throws ServletException exception from server
     * @throws IOException      exception
     * @author Nguyen Trung Kien - OU
     */

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        filter(
                request,
                response
        );
        filterChain.doFilter(
                request,
                response
        );
    }

    /**
     * filter request from client
     *
     * @param request  request from client
     * @param response response from server
     * @author Nguyen Trung Kien - OU
     */
    private void filter(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        TokenRequest tokenRequest = getJwtFromRequest(request);

        if (Objects.isNull(tokenRequest)
                || !StringUtils.hasText(tokenRequest.getToken())
                || !StringUtils.hasText(tokenRequest.getRefreshToken())) {
            return;
        }

        final String username = tokenProvider.getClaimValueFromJWT(
                tokenRequest.getToken(),
                "preferred_username");
        final UserDetails userDetails = accountDetailsService.loadUserByUsername(username);

        if (Objects.isNull(userDetails)) {
            return;
        }

        final Object refreshTokenResponse = rabbitTemplate.convertSendAndReceive(
                TokenRefreshQueueE.EXCHANGE,
                TokenRefreshQueueE.ROUTING_KEY,
                new RefreshTokenRequest()
                        .setToken(tokenRequest.getToken())
                        .setRefreshToken(tokenRequest.getRefreshToken())
        );

        if (Objects.isNull(refreshTokenResponse)) {
            return;
        }

        final RefreshTokenResponse refreshToken = objectMapper.convertValue(
                refreshTokenResponse,
                new TypeReference<>() {
                });

        if (!tokenRequest.getToken().equals(refreshToken.getNewToken())) {
            tokenRequest.setToken(refreshToken.getNewToken());
            tokenRequest.setRefreshToken(refreshToken.getNewToken());
            response.addHeader("access_token", tokenRequest.getToken());
            response.addHeader("refresh_token", tokenRequest.getRefreshToken());
            response.addHeader("Access-Control-Expose-Headers",  "access_token, refresh_token");
        }

        final Boolean validToken = (Boolean) rabbitTemplate.convertSendAndReceive(
                TokenCheckValidQueueE.EXCHANGE,
                TokenCheckValidQueueE.ROUTING_KEY,
                tokenRequest.getToken()
        );

        if (Objects.isNull(validToken)
                || Boolean.FALSE.equals(validToken)) {
            return;
        }

        final UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);

    }

    /**
     * Get jwt token from request
     *
     * @param request request from client
     * @return jwt token
     * @author Nguyen Trung Kien - OU
     */
    private TokenRequest getJwtFromRequest(HttpServletRequest request) {
        final String bearerToken = request.getHeader("Authorization");
        final String refreshToken = request.getHeader("Refresh-Token");

        if (StringUtils.hasText(bearerToken)
                && bearerToken.startsWith("Bearer ")
                && StringUtils.hasText(refreshToken)) {
            return new TokenRequest()
                    .setToken(bearerToken.substring(7))
                    .setRefreshToken(refreshToken);
        }

        return null;
    }
}
