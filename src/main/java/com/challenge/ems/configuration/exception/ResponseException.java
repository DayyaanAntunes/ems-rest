package com.challenge.ems.configuration.exception;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

@Getter
@Setter
@Data
public class ResponseException extends RuntimeException {
    private final String message;
    private final Integer statusCode;
    private final LocalDateTime timestamp;

    public ResponseException(String message, HttpStatus statusCode) {
        this.message = message;
        this.statusCode = statusCode.value();
        this.timestamp = LocalDateTime.now();
    }

}
