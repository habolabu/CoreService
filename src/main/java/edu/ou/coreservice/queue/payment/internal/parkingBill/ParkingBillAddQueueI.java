package edu.ou.coreservice.queue.payment.internal.parkingBill;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ParkingBillAddQueueI {
    public static final String EXCHANGE = "exc.parking_bill";
    public static final String QUEUE = "q.i.parking_bill.add";
    public static final String ROUTING_KEY = "rk.i.parking_bill.add";

    @Bean
    public Declarables parkingBillAddDeclarableI() {
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
