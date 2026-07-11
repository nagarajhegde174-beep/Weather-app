package com.weather.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix = "api")
@Data
public class WeatherApiConfig {
    private String key;
    private String baseUrl;
}
