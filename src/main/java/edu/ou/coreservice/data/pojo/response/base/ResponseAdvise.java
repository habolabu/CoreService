package edu.ou.coreservice.data.pojo.response.base;

import edu.ou.coreservice.data.pojo.response.impl.ErrorResponse;
import edu.ou.coreservice.data.pojo.response.impl.SuccessResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Objects;

@ControllerAdvice
public class ResponseAdvise implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(
            MethodParameter returnType,
            Class<? extends HttpMessageConverter<?>> converterType
    ) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(
            Object body,
            MethodParameter returnType,
            MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType,
            ServerHttpRequest request,
            ServerHttpResponse response
    ) {
        if (
                returnType.getContainingClass()
                        .isAnnotationPresent(RestController.class)
                        && !Objects.requireNonNull(returnType.getMethod())
                        .isAnnotationPresent(IgnoreResponseBinding.class)
                        && !(body instanceof ErrorResponse)
                        && !(body instanceof SuccessResponse)
        ) {
            return new SuccessResponse<>(body);
        }
        return body;
    }
}
