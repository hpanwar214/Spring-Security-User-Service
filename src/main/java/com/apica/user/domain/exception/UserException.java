

package com.apica.user.domain.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class UserException extends RuntimeException{
    private String message;
    private HttpStatus status;
    public UserException(String message, HttpStatus status) {
        super(message);
        this.message = message;
        this.status =status;
    }
}
