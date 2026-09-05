package com.weather.controller;

import com.weather.dto.*;
import com.weather.model.WeatherResponse;
import com.weather.service.SearchHistoryService;
import com.weather.service.UnitConversionService;
import com.weather.service.WeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/weather")
@RequiredArgsConstructor
@Slf4j
public class WeatherController {

    private final WeatherService weatherService;
    private final SearchHistoryService searchHistoryService;
    private final UnitConversionService unitConversionService;

    @GetMapping
    public ResponseEntity<WeatherResponse> getWeather(@RequestParam String city) {
        log.info("GET /api/weather?city={}", city);
        return ResponseEntity.ok(weatherService.getWeatherSummary(city));
    }

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

    @GetMapping("/history")
    public ResponseEntity<SearchHistoryResponse> getSearchHistory() {
        log.info("GET /api/weather/history");
        return ResponseEntity.ok(searchHistoryService.getHistory());
    }

    @DeleteMapping("/history")
    public ResponseEntity<Map<String, String>> clearSearchHistory() {
        log.info("DELETE /api/weather/history");
        searchHistoryService.clearHistory();
        return ResponseEntity.ok(Map.of("message", "Search history cleared successfully"));
    }

    @DeleteMapping("/history/{city}")
    public ResponseEntity<Map<String, String>> removeHistoryCity(@PathVariable String city) {
        log.info("DELETE /api/weather/history/{}", city);
        searchHistoryService.removeSearch(city);
        return ResponseEntity.ok(Map.of("message", "City '" + city + "' removed from search history"));
    }

    @DeleteMapping("/cache")
    public ResponseEntity<Map<String, String>> clearCache() {
        log.info("DELETE /api/weather/cache");
        weatherService.clearCache();
        return ResponseEntity.ok(Map.of("message", "Weather cache cleared successfully"));
    }

    @PostMapping("/convert")
    public ResponseEntity<UnitConversionResponse> convertUnit(@RequestBody UnitConversionRequest request) {
        log.info("POST /api/weather/convert");
        return ResponseEntity.ok(unitConversionService.convert(request));
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        return ResponseEntity.ok(Map.of(
                "status", "UP",
                "service", "Weather Backend API",
                "message", "Spring Boot weather-backend is running successfully"
        ));
    }
}
