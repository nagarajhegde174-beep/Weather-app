package com.weather.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO representing a request to fetch weather data for a specific city.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherRequest {

    @NotBlank(message = "City name must not be blank")
    private String city;
}
