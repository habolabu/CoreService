package edu.ou.coreservice.common.util;

import edu.ou.coreservice.queue.auth.external.account.AccountGetDetailQueueE;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SecurityUtils {

    /**
     * Get information of current account
     *
     * @param rabbitTemplate rabbit template
     * @return a map represents for current account information
     * @author Nguyen Trung Kien - OU
     */
    public static Map<String, String> getCurrentAccount(RabbitTemplate rabbitTemplate) {
        final UserDetails principal = (UserDetails)
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal();

        final Object response = rabbitTemplate.convertSendAndReceive(
                AccountGetDetailQueueE.EXCHANGE,
                AccountGetDetailQueueE.ROUTING_KEY,
                principal.getUsername()
        );
        final Map<String, Object> accountDetail = (HashMap<String, Object>) response;
        if(Objects.isNull(accountDetail)){
            return null;
        }
        final Map<String, String> currentAccount= new HashMap<>();
        accountDetail.forEach((key, value) -> currentAccount.put(key, (String) value));

        return currentAccount;
    }
}
