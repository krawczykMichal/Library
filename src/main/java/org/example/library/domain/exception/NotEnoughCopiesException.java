package org.example.library.domain.exception;

public class NotEnoughCopiesException extends RuntimeException {

    public NotEnoughCopiesException(String message) {
        super(message);
    }
}
