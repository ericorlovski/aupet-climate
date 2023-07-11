package aupet.microclimate.controller.advice;

import aupet.microclimate.exception.ErrorException;
import aupet.microclimate.web.GenericResponse;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CommonExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({Exception.class, RuntimeException.class})
    public final ResponseEntity<GenericResponse<Void>> handleException(RuntimeException ex) {

        val response = new GenericResponse<Void>(
                500,
                ex.getMessage(),
                null
        );

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ErrorException.class})
    public final ResponseEntity<GenericResponse<Void>> handleException(ErrorException ex) {

        val response = new GenericResponse<Void>(
                404,
                ex.getMessage(),
                null
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
