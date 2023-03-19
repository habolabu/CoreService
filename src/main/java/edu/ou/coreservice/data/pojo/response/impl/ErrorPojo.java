package edu.ou.coreservice.data.pojo.response.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorPojo<T> implements Serializable {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T body;
    private String message;

    public ErrorPojo(
            T body,
            String message
    ) {
        this.body = body;
        this.message = message;
    }

}
