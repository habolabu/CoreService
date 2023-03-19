package edu.ou.coreservice.config;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Component
public class JwtTokenProvider {

    /**
     * get claims from jwt token
     *
     * @param token     jwt token
     * @param claimType type of claim
     * @return claim
     * @author Nguyen Trung Kien - OU
     */
    public String getClaimValueFromJWT(
            String token,
            String claimType
    ) {
        final String payload = this.getPayload(token);
        if (!StringUtils.hasText(payload)) {
            return null;
        }

        final List<String> claims = Arrays.stream(
                        payload.replaceAll(
                                        "[{}\"]",
                                        ""
                                )
                                .split(",")
                )
                .filter(claim -> claim.contains(claimType))
                .map(claim -> claim.substring(claimType.length() + 1))
                .toList();

        if (claims.size() > 0) {
            return claims.get(0);
        }

        return null;
    }

    /**
     * get payload from jwt token
     *
     * @param token jwt token
     * @return payload
     * @author Nguyen Trung Kien - OU
     */
    private String getPayload(String token) {
        if (!StringUtils.hasText(token)) {
            return "";
        }
        final String[] chunks = token.split("\\.");

        return new String(Base64.getUrlDecoder().decode(chunks[1]));
    }
}
