package edu.ou.coreservice.queue.payment.external.payment;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ParkingPaymentFindAllQueueE {
    public static final String EXCHANGE = "exc.parking_payment";
    public static final String QUEUE = "q.e.parking_payment.find_all";
    public static final String ROUTING_KEY = "rk.e.parking_payment.find_all";

    @Bean
    public Declarables parkingPaymentGetAllDetailDeclarableE() {
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
