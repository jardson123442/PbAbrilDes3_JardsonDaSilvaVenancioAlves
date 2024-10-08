package br.com.jardson.mspayment.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
        super("Data not found");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
