package com.weather.mapper;

import com.weather.dto.CurrentWeatherDTO;
import com.weather.model.WeatherResponse;
import com.weather.util.WeatherUnitConverter;
import com.weather.util.WeatherUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WeatherMapper {

    private final WeatherUnitConverter unitConverter;

    public WeatherResponse mapToWeatherResponse(CurrentWeatherDTO dto, String summary, boolean cached) {
        if (dto == null) {
            return null;
        }

        Double tempC = dto.getMain() != null ? dto.getMain().getTemp() : null;
        Double tempF = tempC != null ? unitConverter.celsiusToFahrenheit(tempC) : null;
        Double feelsLikeC = dto.getMain() != null ? dto.getMain().getFeelsLike() : null;
        Double tempMinC = dto.getMain() != null ? dto.getMain().getTempMin() : null;
        Double tempMaxC = dto.getMain() != null ? dto.getMain().getTempMax() : null;
        Double windKmh = dto.getWind() != null ? dto.getWind().getSpeed() : null;
        Double windMph = windKmh != null ? unitConverter.kilometersToMiles(windKmh) : null;

        String description = (dto.getWeather() != null && !dto.getWeather().isEmpty()) 
                ? dto.getWeather().get(0).getDescription() : null;
        String icon = (dto.getWeather() != null && !dto.getWeather().isEmpty()) 
                ? dto.getWeather().get(0).getIcon() : null;

        return WeatherResponse.builder()
                .city(dto.getName())
                .country(dto.getSys() != null ? dto.getSys().getCountry() : null)
                .temperature(tempC)
                .fahrenheitTemp(tempF)
                .feelsLike(feelsLikeC)
                .tempMin(tempMinC)
                .tempMax(tempMaxC)
                .humidity(dto.getMain() != null ? dto.getMain().getHumidity() : null)
                .windSpeed(windKmh)
                .windSpeedMph(windMph)
                .description(description)
                .icon(icon)
                .summary(summary)
                .cached(cached)
                .timestamp(WeatherUtil.formatUnixTimestamp((dto.getDt() != null && dto.getDt() != 0) ? dto.getDt() : System.currentTimeMillis() / 1000))
                .build();
    }
}
