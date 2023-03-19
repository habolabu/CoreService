package edu.ou.coreservice.queue.human.internal.emergency;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class EmergencyDeleteAllByUserIdQueueI {
    public static final String EXCHANGE = "exc.emergency";
    public static final String QUEUE = "q.i.emergency.delete_all_by_user_id";
    public static final String ROUTING_KEY = "rk.i.emergency.delete_all_by_user_id";

    @Bean
    public Declarables emergencyDeleteAllByUserIdDeclarableI() {
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
