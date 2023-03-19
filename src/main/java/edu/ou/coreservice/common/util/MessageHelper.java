package edu.ou.coreservice.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Locale;

@Component
public class MessageHelper {
    private static MessageSource messageSourceStatic;
    @Autowired
    private MessageSource messageSource;

    @PostConstruct
    public void init() {
        messageSourceStatic = messageSource;
    }

    public static String getMessageStatic(
            String code,
            Object... param
    ) {
        return messageSourceStatic.getMessage(
                code,
                param,
                Locale.getDefault()
        );
    }
}

