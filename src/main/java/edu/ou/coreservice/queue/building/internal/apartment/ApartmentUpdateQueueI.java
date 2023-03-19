package edu.ou.coreservice.queue.building.internal.apartment;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ApartmentUpdateQueueI {
    public static final String EXCHANGE = "exc.apartment";
    public static final String QUEUE = "q.i.apartment.update";
    public static final String ROUTING_KEY = "rk.i.apartment.update";

    @Bean
    public Declarables apartmentUpdateDeclarableI() {
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
