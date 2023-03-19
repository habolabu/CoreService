package edu.ou.coreservice.queue.human.external.user;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class UserCheckExistQueueE {
    public static final String EXCHANGE = "exc.user";
    public static final String QUEUE = "q.e.user.check_exist";
    public static final String ROUTING_KEY = "rk.e.user.check_exist";

    @Bean
    public Declarables userCheckExistDeclarableE() {
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
