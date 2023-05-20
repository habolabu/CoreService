package edu.ou.coreservice.common.util;

import edu.ou.coreservice.data.pojo.response.impl.CurrentAccountInfoResponse;
import edu.ou.coreservice.queue.auth.external.account.AccountGetDetailQueueE;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SecurityUtils {

    /**
     * Get information of current account
     *
     * @param rabbitTemplate rabbit template
     * @return a CurrentAccountInfoResponse represents for current account information
     * @author Nguyen Trung Kien - OU
     */
    public static CurrentAccountInfoResponse getCurrentAccount(RabbitTemplate rabbitTemplate) {
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

        return new CurrentAccountInfoResponse()
                .setUserId(Integer.parseInt((String) accountDetail.get("userId")))
                .setAccountId(Integer.parseInt((String) accountDetail.get("accountId")))
                .setUsername((String) accountDetail.get("username"))
                .setPermissions((List<String>) accountDetail.get("permissions"))
                .setPassword((String) accountDetail.get("password"));
    }
}
