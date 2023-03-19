package edu.ou.coreservice.queue.auth.internal.password;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class PasswordDeleteByAccountIdQueueI {
    public static final String EXCHANGE = "exc.password";
    public static final String QUEUE = "q.i.password.delete_by_account_id";
    public static final String ROUTING_KEY = "rk.i.password.delete_by_account_id";

    @Bean
    public Declarables passwordDeleteByAccountIdDeclarableI() {
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
