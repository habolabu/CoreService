package edu.ou.coreservice.queue.building.internal.ownerHistory;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class OwnerHistoryDeleteQueueI {
    public static final String EXCHANGE = "exc.owner_history";
    public static final String QUEUE = "q.i.owner_history.delete";
    public static final String ROUTING_KEY = "rk.i.owner_history.delete";

    @Bean
    public Declarables ownerHistoryDeleteDeclarableI() {
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
