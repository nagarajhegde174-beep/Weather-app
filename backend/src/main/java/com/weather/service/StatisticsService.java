package com.weather.service;

import com.weather.dto.StatisticsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatisticsService {

    private final FavoriteService favoriteService;

    private final AtomicLong totalSearches = new AtomicLong(0);
    private final AtomicLong cacheHits = new AtomicLong(0);
    private final AtomicLong apiCalls = new AtomicLong(0);
    private final Map<String, AtomicLong> citySearchCounts = new ConcurrentHashMap<>();

    public void recordSearch(String city, boolean isCacheHit) {
        if (city == null || city.isBlank()) return;
        String normalizedCity = city.trim();

        totalSearches.incrementAndGet();
        if (isCacheHit) {
            cacheHits.incrementAndGet();
        } else {
            apiCalls.incrementAndGet();
        }

        citySearchCounts.computeIfAbsent(normalizedCity, k -> new AtomicLong(0)).incrementAndGet();
        log.info("Updated statistics for search of '{}' (CacheHit={})", normalizedCity, isCacheHit);
    }

    public StatisticsResponse getStatistics() {
        String mostSearched = citySearchCounts.entrySet().stream()
                .max(Comparator.comparingLong(e -> e.getValue().get()))
                .map(Map.Entry::getKey)
                .orElse("None");

        return StatisticsResponse.builder()
                .totalSearches(totalSearches.get())
                .mostSearchedCity(mostSearched)
                .cacheHits(cacheHits.get())
                .apiCalls(apiCalls.get())
                .favoriteCities(favoriteService.getFavoritesCount())
                .build();
    }
}
