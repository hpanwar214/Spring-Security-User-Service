

package com.apica.user.domain.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Component
@ControllerAdvice
public class UserExceptionHandler{
    @ExceptionHandler(UserException.class)
    public ResponseEntity<String> handleOtherException(UserException e) {
        return ResponseEntity.status(e.getStatus())
                .body(e.getMessage());
    }
}
