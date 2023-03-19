package edu.ou.coreservice.service.impl;

import edu.ou.coreservice.queue.auth.external.account.AccountGetDetailQueueE;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountDetailsService implements UserDetailsService {
    private final RabbitTemplate rabbitTemplate;

    /**
     * Load user details by username
     *
     * @param username the username identifying the user whose data is required.
     * @return user details
     * @throws UsernameNotFoundException exception
     * @author Nguyen Trung Kien - OU
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Object account = rabbitTemplate.convertSendAndReceive(
                AccountGetDetailQueueE.EXCHANGE,
                AccountGetDetailQueueE.ROUTING_KEY,
                username
        );

        if (Objects.isNull(account)) {
            throw new UsernameNotFoundException("Account Not Found");
        }

        final Map<String, Object> dataMap = (HashMap<String, Object>) account;
        final List<String> permissions = (List<String>) dataMap.get("permissions");
        final Set<GrantedAuthority> authorities = permissions
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(
                (String) dataMap.get("username"),
                (String) dataMap.get("password"),
                true,
                true,
                true,
                true,
                authorities
        );
    }
}
