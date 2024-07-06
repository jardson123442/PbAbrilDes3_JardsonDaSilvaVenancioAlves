package br.com.jardson.mspayment.exceptions.handler;


import br.com.jardson.mspayment.exceptions.ExceptionResponse;
import br.com.jardson.mspayment.exceptions.ResourceIntegrationNotFound;
import br.com.jardson.mspayment.exceptions.ResourceNotFoundException;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class PaymentHandlerExceptions extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage());

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(value = ResourceNotFoundException.class)
    protected ResponseEntity<Object> handlerResourceNotFoundException(ResourceNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse("Customer not found"));
    }

    @ExceptionHandler(value = ResourceIntegrationNotFound.class)
    protected ResponseEntity<Object> handlerResourceIntegrationNotFound(ResourceIntegrationNotFound exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ExceptionResponse("Data not found"));
    }
}
