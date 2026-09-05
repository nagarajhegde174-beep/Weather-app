package com.weather.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatisticsResponse {
    private long totalSearches;
    private String mostSearchedCity;
    private long cacheHits;
    private long apiCalls;
    private int favoriteCities;
}
