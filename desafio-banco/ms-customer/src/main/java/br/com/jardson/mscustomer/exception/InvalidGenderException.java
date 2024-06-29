package br.com.jardson.mscustomer.exception;

public class InvalidGenderException extends RuntimeException {

    public InvalidGenderException() {
        super("Invalid gender");
    }
}
