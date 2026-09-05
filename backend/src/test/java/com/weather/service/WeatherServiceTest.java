package com.weather.service;

import com.weather.dto.CurrentWeatherDTO;
import com.weather.mapper.WeatherMapper;
import com.weather.model.WeatherResponse;
import com.weather.util.CityValidator;
import com.weather.util.WeatherUnitConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class WeatherServiceTest {

    private WeatherService weatherService;
    private WeatherApiService weatherApiService;
    private WeatherCacheService weatherCacheService;

    @BeforeEach
    void setUp() {
        CityValidator cityValidator = new CityValidator();
        weatherApiService = mock(WeatherApiService.class);
        weatherCacheService = mock(WeatherCacheService.class);
        SearchHistoryService searchHistoryService = mock(SearchHistoryService.class);
        StatisticsService statisticsService = mock(StatisticsService.class);
        WeatherSummaryService weatherSummaryService = new WeatherSummaryService();
        WeatherUnitConverter unitConverter = new WeatherUnitConverter();
        WeatherMapper weatherMapper = new WeatherMapper(unitConverter);

        weatherService = new WeatherService(
                cityValidator,
                weatherApiService,
                weatherCacheService,
                searchHistoryService,
                statisticsService,
                weatherSummaryService,
                weatherMapper
        );
    }

    @Test
    void getWeatherSummary_CacheMiss_FetchesFromApi() {
        when(weatherCacheService.get("Bangalore")).thenReturn(Optional.empty());

        CurrentWeatherDTO dto = new CurrentWeatherDTO();
        dto.setName("Bangalore");
        CurrentWeatherDTO.Main main = new CurrentWeatherDTO.Main();
        main.setTemp(28.0);
        main.setHumidity(65);
        dto.setMain(main);

        CurrentWeatherDTO.Weather weather = new CurrentWeatherDTO.Weather();
        weather.setMain("Clouds");
        weather.setDescription("scattered clouds");
        dto.setWeather(List.of(weather));

        when(weatherApiService.fetchCurrentWeather("Bangalore")).thenReturn(dto);

        WeatherResponse response = weatherService.getWeatherSummary("Bangalore");

        assertNotNull(response);
        assertEquals("Bangalore", response.getCity());
        assertEquals(28.0, response.getTemperature());
        assertFalse(response.getCached());
        verify(weatherApiService, times(1)).fetchCurrentWeather("Bangalore");
        verify(weatherCacheService, times(1)).put(eq("Bangalore"), any());
    }

    @Test
    void getWeatherSummary_CacheHit_ReturnsCachedResponse() {
        WeatherResponse cachedResponse = WeatherResponse.builder()
                .city("Bangalore")
                .temperature(28.0)
                .cached(true)
                .build();

        when(weatherCacheService.get("Bangalore")).thenReturn(Optional.of(cachedResponse));

        WeatherResponse response = weatherService.getWeatherSummary("Bangalore");

        assertNotNull(response);
        assertEquals("Bangalore", response.getCity());
        assertTrue(response.getCached());
        verify(weatherApiService, never()).fetchCurrentWeather(anyString());
    }
}
