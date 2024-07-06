package br.com.jardson.mspayment.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ResourceIntegrationNotFound extends RuntimeException {

    public ResourceIntegrationNotFound() {
        super("Data not found");
    }

    public ResourceIntegrationNotFound(String message) {
        super(message);
    }
}
