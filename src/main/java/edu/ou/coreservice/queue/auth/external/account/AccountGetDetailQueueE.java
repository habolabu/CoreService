package edu.ou.coreservice.queue.auth.external.account;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AccountGetDetailQueueE {
    public static final String EXCHANGE = "exc.account";
    public static final String QUEUE = "q.e.account.get_detail";
    public static final String ROUTING_KEY = "rk.e.account.get_detail";

    @Bean
    public Declarables accountGetDetailDeclarableE() {
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
