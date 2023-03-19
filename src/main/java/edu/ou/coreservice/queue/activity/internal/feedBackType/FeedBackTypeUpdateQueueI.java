package edu.ou.coreservice.queue.activity.internal.feedBackType;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class FeedBackTypeUpdateQueueI {
    public static final String EXCHANGE = "exc.feedback-type";
    public static final String QUEUE = "q.i.feedback-type.update";
    public static final String ROUTING_KEY = "rk.i.feedback-type.update";

    @Bean
    public Declarables feedBackTypeUpdateDeclarableI() {
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
