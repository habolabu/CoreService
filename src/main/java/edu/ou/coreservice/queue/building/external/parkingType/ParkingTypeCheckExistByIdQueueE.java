package edu.ou.coreservice.queue.building.external.parkingType;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ParkingTypeCheckExistByIdQueueE {
    public static final String EXCHANGE = "exc.parking_type";
    public static final String QUEUE = "q.e.parking_type.check_exist_by_id";
    public static final String ROUTING_KEY = "rk.e.parking_type.check_exist_by_id";

    @Bean
    public Declarables parkingTypeCheckExistByIdDeclarableE() {
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
