package edu.ou.coreservice.common.exception;


import edu.ou.coreservice.common.constant.Message;
import edu.ou.coreservice.data.pojo.response.impl.ErrorPojo;
import edu.ou.coreservice.data.pojo.response.impl.ErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final Environment environment;

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse<ErrorPojo<?>>> handleUnwantedException(BusinessException e) {

        //TODO: will remove in future
        e.printStackTrace();
        //

        final ErrorPojo<?> errorPojo = new ErrorPojo<>(
                e.getCode(),
                e.getMessage()
        );

        return ResponseEntity
                .status(Integer.parseInt((String) errorPojo.getBody()))
                .body(
                        new ErrorResponse<>(
                                errorPojo,
                                environment.getProperty(Message.Error.TITLE)
                        )
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse<ErrorPojo<?>>> handleUnwantedException(Exception e) {

        //TODO: will remove in future
        e.printStackTrace();
        //

        final ErrorPojo<?> errorPojo = new ErrorPojo<>(
                500,
                e.getMessage()
        );

        return ResponseEntity
                .status(Integer.parseInt((String) errorPojo.getBody()))
                .body(
                        new ErrorResponse<>(
                                errorPojo,
                                environment.getProperty(Message.Error.TITLE)
                        )
                );
    }
}
