package edu.ou.coreservice.queue.human.external.user;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class UserFindDetailByIdQueueE {
    public static final String EXCHANGE = "exc.user";
    public static final String QUEUE = "q.e.user.find_detail";
    public static final String ROUTING_KEY = "rk.e.user.find_detail";

    @Bean
    public Declarables userFindDetailDeclarableE() {
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
