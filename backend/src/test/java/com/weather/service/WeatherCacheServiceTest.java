package com.weather.service;

import com.weather.model.WeatherResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class WeatherCacheServiceTest {

    private WeatherCacheService cacheService;

    @BeforeEach
    void setUp() {
        cacheService = new WeatherCacheService();
    }

    @Test
    void putAndGet_CacheHit() {
        WeatherResponse response = WeatherResponse.builder().city("Mumbai").temperature(30.0).build();
        cacheService.put("Mumbai", response);

        Optional<WeatherResponse> cached = cacheService.get("Mumbai");
        assertTrue(cached.isPresent());
        assertEquals("Mumbai", cached.get().getCity());
        assertTrue(cached.get().getCached());
    }

    @Test
    void get_CacheMiss() {
        Optional<WeatherResponse> cached = cacheService.get("NonExistent");
        assertFalse(cached.isPresent());
    }

    @Test
    void clear_ClearsCache() {
        cacheService.put("Berlin", WeatherResponse.builder().city("Berlin").build());
        cacheService.clear();
        assertEquals(0, cacheService.getCacheSize());
    }
}
