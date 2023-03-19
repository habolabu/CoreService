package edu.ou.coreservice.common.exception;

import edu.ou.coreservice.common.util.MessageHelper;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BusinessException extends RuntimeException {
    private String code;
    private String message;

    public BusinessException(
            String code,
            String messageCode,
            Object... params
    ) {
        this.message = MessageHelper.getMessageStatic(messageCode, params);
        this.code = code;
    }

}
