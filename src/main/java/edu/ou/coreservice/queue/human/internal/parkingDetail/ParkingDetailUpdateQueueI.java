package edu.ou.coreservice.queue.human.internal.parkingDetail;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ParkingDetailUpdateQueueI {
    public static final String EXCHANGE = "exc.parking_detail";
    public static final String QUEUE = "q.i.parking_detail.update";
    public static final String ROUTING_KEY = "rk.i.parking_detail.update";

    @Bean
    public Declarables parkingDetailUpdateDeclarableI() {
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
