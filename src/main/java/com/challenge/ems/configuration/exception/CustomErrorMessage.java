package com.challenge.ems.configuration.exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CustomErrorMessage {
    private String path;
    private String status;
    private LocalDateTime timestamp;
    private String trace;


}