package com.weather.service;

import com.weather.model.CachedWeather;
import com.weather.model.WeatherResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class WeatherCacheService {

    private static final long DEFAULT_TTL_MINUTES = 10;
    private final Map<String, CachedWeather> cache = new ConcurrentHashMap<>();

    public Optional<WeatherResponse> get(String city) {
        String key = normalizeKey(city);
        CachedWeather cached = cache.get(key);

        if (cached == null) {
            log.debug("Cache miss for city: {}", city);
            return Optional.empty();
        }

        if (cached.isExpired()) {
            log.info("Cache expired for city: {}", city);
            cache.remove(key);
            return Optional.empty();
        }

        log.info("Cache hit for city: {}", city);
        WeatherResponse response = cached.getWeatherResponse();
        response.setCached(true);
        return Optional.of(response);
    }

    public void put(String city, WeatherResponse response) {
        String key = normalizeKey(city);
        LocalDateTime now = LocalDateTime.now();
        CachedWeather cached = CachedWeather.builder()
                .weatherResponse(response)
                .cachedAt(now)
                .expiresAt(now.plusMinutes(DEFAULT_TTL_MINUTES))
                .build();
        cache.put(key, cached);
        log.info("Cached weather data for city: {} (TTL: {} minutes)", city, DEFAULT_TTL_MINUTES);
    }

    public void clear() {
        cache.clear();
        log.info("Weather cache cleared.");
    }

    public int getCacheSize() {
        return cache.size();
    }

    private String normalizeKey(String city) {
        return city != null ? city.trim().toLowerCase() : "";
    }
}
