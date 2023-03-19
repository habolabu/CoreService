package edu.ou.coreservice.data.pojo.response.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import edu.ou.coreservice.data.pojo.response.base.IBaseResponse;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse<T> implements IBaseResponse {
    private ErrorPojo<T> error;

    public ErrorResponse(
            T object,
            String message
    ) {
        error = new ErrorPojo<>(
                object,
                message
        );
    }
}
