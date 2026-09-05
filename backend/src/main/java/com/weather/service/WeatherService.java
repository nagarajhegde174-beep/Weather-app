package com.weather.service;

import com.weather.dto.CurrentWeatherDTO;
import com.weather.dto.ForecastDTO;
import com.weather.mapper.WeatherMapper;
import com.weather.model.WeatherResponse;
import com.weather.util.CityValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class WeatherService {

    private final CityValidator cityValidator;
    private final WeatherApiService weatherApiService;
    private final WeatherCacheService weatherCacheService;
    private final SearchHistoryService searchHistoryService;
    private final StatisticsService statisticsService;
    private final WeatherSummaryService weatherSummaryService;
    private final WeatherMapper weatherMapper;

    public WeatherResponse getWeatherSummary(String city) {
        String validCity = cityValidator.validateAndNormalize(city);
        log.info("Processing weather summary request for city: {}", validCity);

        
        Optional<WeatherResponse> cached = weatherCacheService.get(validCity);
        if (cached.isPresent()) {
            searchHistoryService.addSearch(validCity);
            statisticsService.recordSearch(validCity, true);
            return cached.get();
        }

        
        CurrentWeatherDTO currentWeatherDTO = weatherApiService.fetchCurrentWeather(validCity);
        String summary = weatherSummaryService.generateSummary(currentWeatherDTO);
        WeatherResponse response = weatherMapper.mapToWeatherResponse(currentWeatherDTO, summary, false);

        
        weatherCacheService.put(validCity, response);
        searchHistoryService.addSearch(validCity);
        statisticsService.recordSearch(validCity, false);

        return response;
    }

    public CurrentWeatherDTO getCurrentWeather(String city) {
        String validCity = cityValidator.validateAndNormalize(city);
        log.info("Fetching current weather DTO for city: {}", validCity);
        return weatherApiService.fetchCurrentWeather(validCity);
    }

    public ForecastDTO getForecast(String city) {
        String validCity = cityValidator.validateAndNormalize(city);
        log.info("Fetching forecast DTO for city: {}", validCity);
        return weatherApiService.fetchForecast(validCity);
    }

    public void clearCache() {
        weatherCacheService.clear();
    }
}
