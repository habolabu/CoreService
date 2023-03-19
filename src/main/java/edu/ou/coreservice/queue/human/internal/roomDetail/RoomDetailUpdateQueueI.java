package edu.ou.coreservice.queue.human.internal.roomDetail;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RoomDetailUpdateQueueI {
    public static final String EXCHANGE = "exc.room_detail";
    public static final String QUEUE = "q.i.room_detail.update";
    public static final String ROUTING_KEY = "rk.i.room_detail.update";

    @Bean
    public Declarables roomDetailUpdateDeclarableI() {
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
