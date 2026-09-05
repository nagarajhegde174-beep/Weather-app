package com.weather.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WeatherStatistics {
    @Builder.Default
    private AtomicLong totalSearches = new AtomicLong(0);

    @Builder.Default
    private AtomicLong cacheHits = new AtomicLong(0);

    @Builder.Default
    private AtomicLong apiCalls = new AtomicLong(0);

    @Builder.Default
    private Map<String, AtomicLong> citySearchCounts = new ConcurrentHashMap<>();
}
