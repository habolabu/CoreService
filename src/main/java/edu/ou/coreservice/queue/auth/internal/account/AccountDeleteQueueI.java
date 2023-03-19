package edu.ou.coreservice.queue.auth.internal.account;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AccountDeleteQueueI {
    public static final String EXCHANGE = "exc.account";
    public static final String QUEUE = "q.i.account.delete";
    public static final String ROUTING_KEY = "rk.i.account.delete";

    @Bean
    public Declarables accountDeleteDeclarableI() {
        return new Declarables(
                new DirectExchange(
                       EXCHANGE,
                        false,
                        true,
                        null
                ),
                new Queue(
                        QUEUE,
                        false,
                        false,
                        true,
                        null
                ),
                new Binding(
                        QUEUE,
                        Binding.DestinationType.QUEUE,
                        EXCHANGE,
                        ROUTING_KEY,
                        null
                )
        );
    }

}
