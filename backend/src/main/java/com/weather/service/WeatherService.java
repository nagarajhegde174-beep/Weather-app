package com.weather.service;

import com.weather.config.WeatherApiConfig;
import com.weather.dto.CurrentWeatherDTO;
import com.weather.dto.ForecastDTO;
import com.weather.model.WeatherResponse;
import com.weather.exception.CityNotFoundException;
import com.weather.exception.WeatherApiException;
import com.weather.util.WeatherUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


@Service
@RequiredArgsConstructor
@Slf4j
public class WeatherService {

    private final RestTemplate restTemplate;
    private final WeatherApiConfig weatherApiConfig;

    
    public CurrentWeatherDTO getCurrentWeather(String city) {
        String validCity = WeatherUtil.validateCity(city);
        log.info("Fetching current weather for city: {}", validCity);

        try {
            CurrentWeatherDTO response = restTemplate.getForObject(
                    buildUrl("/weather", validCity), CurrentWeatherDTO.class);
            log.info("Successfully fetched current weather for: {}", validCity);
            return response;
        } catch (HttpClientErrorException.NotFound e) {
            throw new CityNotFoundException(
                    "City '" + validCity + "' not found. Please check the spelling and try again.");
        } catch (HttpClientErrorException e) {
            throw new WeatherApiException("Failed to fetch weather data: " + e.getMessage());
        } catch (Exception e) {
            throw new WeatherApiException("An unexpected error occurred while fetching weather data.", e);
        }
    }

    
    public ForecastDTO getForecast(String city) {
        String validCity = WeatherUtil.validateCity(city);
        log.info("Fetching forecast for city: {}", validCity);

        try {
            ForecastDTO response = restTemplate.getForObject(
                    buildUrl("/forecast", validCity), ForecastDTO.class);
            log.info("Successfully fetched forecast for: {}", validCity);
            return response;
        } catch (HttpClientErrorException.NotFound e) {
            throw new CityNotFoundException(
                    "City '" + validCity + "' not found. Please check the spelling and try again.");
        } catch (HttpClientErrorException e) {
            throw new WeatherApiException("Failed to fetch forecast data: " + e.getMessage());
        } catch (Exception e) {
            throw new WeatherApiException("An unexpected error occurred while fetching forecast data.", e);
        }
    }

    

    public WeatherResponse getWeatherSummary(String city) {
        CurrentWeatherDTO dto = getCurrentWeather(city);
        return WeatherResponse.builder()
                .city(dto.getName())
                .country(dto.getSys() != null ? dto.getSys().getCountry() : null)
                .temperature(dto.getMain() != null ? dto.getMain().getTemp() : null)
                .feelsLike(dto.getMain() != null ? dto.getMain().getFeelsLike() : null)
                .tempMin(dto.getMain() != null ? dto.getMain().getTempMin() : null)
                .tempMax(dto.getMain() != null ? dto.getMain().getTempMax() : null)
                .humidity(dto.getMain() != null ? dto.getMain().getHumidity() : null)
                .windSpeed(dto.getWind() != null ? dto.getWind().getSpeed() : null)
                .description((dto.getWeather() != null && !dto.getWeather().isEmpty()) ? dto.getWeather().get(0).getDescription() : null)
                .icon((dto.getWeather() != null && !dto.getWeather().isEmpty()) ? dto.getWeather().get(0).getIcon() : null)
                .build();
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
