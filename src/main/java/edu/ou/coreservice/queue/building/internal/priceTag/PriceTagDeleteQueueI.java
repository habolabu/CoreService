package edu.ou.coreservice.queue.building.internal.priceTag;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class PriceTagDeleteQueueI {
    public static final String EXCHANGE = "exc.price_tag";
    public static final String QUEUE = "q.i.price_tag.delete";
    public static final String ROUTING_KEY = "rk.i.price_tag.delete";

    @Bean
    public Declarables priceTagDeleteDeclarableI() {
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
