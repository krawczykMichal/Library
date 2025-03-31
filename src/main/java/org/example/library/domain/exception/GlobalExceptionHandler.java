package org.example.library.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Controller
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNameAlreadyTakenException.class)
    public ResponseEntity<String> handleUsernameAlreadyTaken(UserNameAlreadyTakenException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
}
