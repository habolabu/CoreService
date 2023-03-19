package edu.ou.coreservice.data.pojo.response.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import edu.ou.coreservice.data.pojo.response.base.IBaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SuccessResponse<T> implements IBaseResponse {
    private SuccessPojo<T> response;

    public void setResponse(SuccessPojo<T> response) {
        this.response = response;
    }

    public SuccessResponse(T object) {
        this.response = new SuccessPojo<>(object);
    }

    public SuccessResponse(
            T object,
            String code,
            String messageCode
    ) {
        this.response = new SuccessPojo<>(
                object,
                code,
                messageCode
        );
    }

    public SuccessResponse(
            T object,
            Integer length,
            String code,
            String messageCode
    ) {
        this.response = new SuccessPojo<>(
                object,
                length,
                code,
                messageCode
        );
    }

    public SuccessResponse(
            T object,
            Integer length
    ) {
        this.response = new SuccessPojo<>(
                object,
                length
        );
    }
}
