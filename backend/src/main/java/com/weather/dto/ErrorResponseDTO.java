package com.weather.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseDTO {

    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

    
    public ErrorResponseDTO(Integer status, String error, String message, String path) {
        this.timestamp = LocalDateTime.now();
        this.status    = status;
        this.error     = error;
        this.message   = message;
        this.path      = path;
    }
}
