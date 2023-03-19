package edu.ou.coreservice.queue.activity.internal.commentEmotion;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CommentEmotionDeleteQueueI {
    public static final String EXCHANGE = "exc.comment-emotion";
    public static final String QUEUE = "q.i.comment-emotion.delete";
    public static final String ROUTING_KEY = "rk.i.comment-emotion.delete";

    @Bean
    public Declarables commentEmotionDeleteDeclarableI() {
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
