package edu.ou.coreservice.data.pojo.response.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import edu.ou.coreservice.common.util.MessageHelper;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SuccessPojo<T> implements Serializable {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T body;
    private int length = 1;
    private String code;
    private String message = null;

    public SuccessPojo(
            T body,
            int length,
            String code,
            String messageCode
    ) {
        this.body = body;
        this.length = length;
        this.code = code;
        this.message = MessageHelper.getMessageStatic(messageCode);

        if (length == 0) {
            if (this.body instanceof List) {
                this.length = ((List<?>) this.body).size();
            }

            if (this.body instanceof Map) {
                this.length = ((Map<?, ?>) this.body).size();
            }
        }

    }

    public SuccessPojo(
            T body,
            String code,
            String messageCode
    ) {
        this.body = body;
        this.code = code;
        this.message = MessageHelper.getMessageStatic(messageCode);

        if (this.body instanceof List) {
            this.length = ((List<?>) this.body).size();
        }

        if (this.body instanceof Map) {
            this.length = ((Map<?, ?>) this.body).size();
        }
    }

    public SuccessPojo(
            T body,
            Integer length
    ) {
        this.body = body;
        this.length = length;
    }

    public SuccessPojo(T body) {
        this.body = body;
        if (this.body instanceof List) {
            this.length = ((List<?>) this.body).size();
        }

        if (this.body instanceof Map) {
            this.length = ((Map<?, ?>) this.body).size();
        }
    }

}
