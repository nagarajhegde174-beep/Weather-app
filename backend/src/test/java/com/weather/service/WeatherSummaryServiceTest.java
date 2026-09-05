package com.weather.service;

import com.weather.dto.CurrentWeatherDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WeatherSummaryServiceTest {

    private WeatherSummaryService summaryService;

    @BeforeEach
    void setUp() {
        summaryService = new WeatherSummaryService();
    }

    @Test
    void generateSummary_WarmAndClear() {
        CurrentWeatherDTO dto = new CurrentWeatherDTO();
        dto.setName("Bangalore");

        CurrentWeatherDTO.Main main = new CurrentWeatherDTO.Main();
        main.setTemp(32.0);
        main.setHumidity(60);
        dto.setMain(main);

        CurrentWeatherDTO.Weather weather = new CurrentWeatherDTO.Weather();
        weather.setMain("Clear");
        dto.setWeather(List.of(weather));

        String summary = summaryService.generateSummary(dto);
        assertNotNull(summary);
        assertTrue(summary.contains("Warm"));
        assertTrue(summary.contains("clear"));
    }
}
