package edu.ou.coreservice.queue.auth.external.role;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RoleFindByUserIdQueueE {
    public static final String EXCHANGE = "exc.role";
    public static final String QUEUE = "q.e.role.find_by_user_id";
    public static final String ROUTING_KEY = "rk.e.role.find_by_user_id";

    @Bean
    public Declarables roleFindByUserIdDeclarableE() {
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
