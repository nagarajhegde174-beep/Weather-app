package com.weather.service;

import com.weather.config.WeatherApiConfig;
import com.weather.dto.CurrentWeatherDTO;
import com.weather.dto.ForecastDTO;
import com.weather.exception.CityNotFoundException;
import com.weather.exception.WeatherApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
@Slf4j
public class WeatherApiService {

    private final RestTemplate restTemplate;
    private final WeatherApiConfig weatherApiConfig;

    public CurrentWeatherDTO fetchCurrentWeather(String city) {
        log.info("Calling external Weather API for city: {}", city);
        try {
            CurrentWeatherDTO response = restTemplate.getForObject(
                    buildUrl("/weather", city), CurrentWeatherDTO.class);
            if (response == null) {
                throw new WeatherApiException("Received empty response from Weather API.");
            }
            return response;
        } catch (HttpClientErrorException.NotFound e) {
            throw new CityNotFoundException("City '" + city + "' not found. Please check spelling.");
        } catch (HttpClientErrorException e) {
            throw new WeatherApiException("External API error: " + e.getMessage());
        } catch (Exception e) {
            throw new WeatherApiException("Unable to retrieve weather information.", e);
        }
    }

    public ForecastDTO fetchForecast(String city) {
        log.info("Calling external Forecast API for city: {}", city);
        try {
            ForecastDTO response = restTemplate.getForObject(
                    buildUrl("/forecast", city), ForecastDTO.class);
            if (response == null) {
                throw new WeatherApiException("Received empty response from Forecast API.");
            }
            return response;
        } catch (HttpClientErrorException.NotFound e) {
            throw new CityNotFoundException("City '" + city + "' not found. Please check spelling.");
        } catch (HttpClientErrorException e) {
            throw new WeatherApiException("External API error: " + e.getMessage());
        } catch (Exception e) {
            throw new WeatherApiException("Unable to retrieve forecast information.", e);
        }
    }

    private String buildUrl(String endpoint, String city) {
        return UriComponentsBuilder
                .fromHttpUrl(weatherApiConfig.getBaseUrl() + endpoint)
                .queryParam("q", city)
                .queryParam("appid", weatherApiConfig.getKey())
                .queryParam("units", "metric")
                .toUriString();
    }
}
