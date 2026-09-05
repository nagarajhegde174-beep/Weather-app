package com.weather.service;

import com.weather.dto.CurrentWeatherDTO;
import com.weather.dto.WeatherSummaryResponse;
import org.springframework.stereotype.Service;

@Service
public class WeatherSummaryService {

    public String generateSummary(CurrentWeatherDTO dto) {
        if (dto == null || dto.getMain() == null) {
            return "Weather information currently unavailable.";
        }

        double temp = dto.getMain().getTemp();
        int humidity = dto.getMain().getHumidity();
        double wind = (dto.getWind() != null) ? dto.getWind().getSpeed() : 0;
        String condition = (dto.getWeather() != null && !dto.getWeather().isEmpty())
                ? dto.getWeather().get(0).getMain() : "Clear";

        String tempDesc;
        if (temp >= 35) tempDesc = "Scorching hot";
        else if (temp >= 30) tempDesc = "Warm";
        else if (temp >= 20) tempDesc = "Pleasant";
        else if (temp >= 10) tempDesc = "Cool";
        else if (temp >= 0) tempDesc = "Cold";
        else tempDesc = "Freezing cold";

        String condDesc;
        switch (condition.toLowerCase()) {
            case "rain":
            case "drizzle":
                condDesc = "rainy";
                break;
            case "clouds":
                condDesc = "cloudy";
                break;
            case "clear":
                condDesc = "clear";
                break;
            case "snow":
                condDesc = "snowy";
                break;
            case "thunderstorm":
                condDesc = "stormy";
                break;
            case "mist":
            case "fog":
            case "haze":
                condDesc = "hazy";
                break;
            default:
                condDesc = condition.toLowerCase();
                break;
        }

        String humidityDesc;
        if (humidity >= 80) humidityDesc = "high humidity";
        else if (humidity <= 35) humidityDesc = "dry air";
        else humidityDesc = "moderate humidity";

        String windDesc = "";
        if (wind >= 30) windDesc = " and strong winds";
        else if (wind >= 15) windDesc = " and gentle breezes";

        return String.format("%s and %s weather with %s%s.", tempDesc, condDesc, humidityDesc, windDesc);
    }

    public WeatherSummaryResponse getSummaryResponse(CurrentWeatherDTO dto) {
        String summary = generateSummary(dto);
        String condition = (dto != null && dto.getWeather() != null && !dto.getWeather().isEmpty())
                ? dto.getWeather().get(0).getDescription() : "N/A";
        Double temp = (dto != null && dto.getMain() != null) ? dto.getMain().getTemp() : null;
        String city = (dto != null) ? dto.getName() : "Unknown";

        return WeatherSummaryResponse.builder()
                .city(city)
                .summary(summary)
                .temperature(temp)
                .condition(condition)
                .build();
    }
}
