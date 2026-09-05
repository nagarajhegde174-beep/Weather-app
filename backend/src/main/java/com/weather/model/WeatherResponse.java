package com.weather.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clean domain model representing a structured weather response returned by the backend API.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WeatherResponse implements WeatherApiResponse {

    private String city;
    private String country;
    private Double temperature;
    private Double feelsLike;
    private Double tempMin;
    private Double tempMax;
    private Integer humidity;
    private Double windSpeed;
    private String description;
    private String icon;
}
