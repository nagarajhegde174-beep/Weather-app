package com.weather.controller;

import com.weather.dto.CurrentWeatherDTO;
import com.weather.dto.ForecastDTO;
import com.weather.service.WeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/api/weather")
@RequiredArgsConstructor
@Slf4j
public class WeatherController {

    private final WeatherService weatherService;

    
    @GetMapping("/current")
    public ResponseEntity<CurrentWeatherDTO> getCurrentWeather(@RequestParam String city) {
        log.info("GET /api/weather/current?city={}", city);
        return ResponseEntity.ok(weatherService.getCurrentWeather(city));
    }

    
    @GetMapping("/forecast")
    public ResponseEntity<ForecastDTO> getForecast(@RequestParam String city) {
        log.info("GET /api/weather/forecast?city={}", city);
        return ResponseEntity.ok(weatherService.getForecast(city));
    }

    
    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        return ResponseEntity.ok(Map.of(
                "status",  "UP",
                "service", "Weather API",
                "message", "Spring Boot weather-backend is running"
        ));
    }
}
