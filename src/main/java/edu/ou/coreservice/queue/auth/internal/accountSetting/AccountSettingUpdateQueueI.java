package edu.ou.coreservice.queue.auth.internal.accountSetting;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AccountSettingUpdateQueueI {
    public static final String EXCHANGE = "exc.account_setting";
    public static final String QUEUE = "q.i.account_setting.update";
    public static final String ROUTING_KEY = "rk.i.account_setting.update";

    @Bean
    public Declarables accountSettingUpdateUpdateDeclarableI() {
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
