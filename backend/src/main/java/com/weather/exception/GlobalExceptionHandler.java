package com.weather.exception;

import com.weather.dto.ErrorResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(CityNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleCityNotFound(
            CityNotFoundException ex, HttpServletRequest request) {
        log.error("City not found: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ErrorResponseDTO(HttpStatus.NOT_FOUND.value(),
                        "City Not Found", ex.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(WeatherApiException.class)
    public ResponseEntity<ErrorResponseDTO> handleWeatherApi(
            WeatherApiException ex, HttpServletRequest request) {
        log.error("Weather API error: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(
                new ErrorResponseDTO(HttpStatus.SERVICE_UNAVAILABLE.value(),
                        "Weather API Error", ex.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ErrorResponseDTO> handleHttpClientError(
            HttpClientErrorException ex, HttpServletRequest request) {
        log.error("HTTP client error: {}", ex.getMessage());
        String message = ex.getStatusCode().value() == 404
                ? "City not found. Please check the city name and try again."
                : "Unable to fetch weather data.";
        return ResponseEntity.status(ex.getStatusCode()).body(
                new ErrorResponseDTO(ex.getStatusCode().value(),
                        "HTTP Client Error", message, request.getRequestURI()));
    }

    @ExceptionHandler(ResourceAccessException.class)
    public ResponseEntity<ErrorResponseDTO> handleResourceAccess(
            ResourceAccessException ex, HttpServletRequest request) {
        log.error("Network error: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(
                new ErrorResponseDTO(HttpStatus.SERVICE_UNAVAILABLE.value(),
                        "Network Error",
                        "Unable to connect to weather service. Please check your internet connection.",
                        request.getRequestURI()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDTO> handleIllegalArgument(
            IllegalArgumentException ex, HttpServletRequest request) {
        log.error("Invalid argument: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(),
                        "Invalid Request", ex.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGeneric(
            Exception ex, HttpServletRequest request) {
        log.error("Unexpected error: ", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ErrorResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "Internal Server Error",
                        "An unexpected error occurred. Please try again later.",
                        request.getRequestURI()));
    }
}
