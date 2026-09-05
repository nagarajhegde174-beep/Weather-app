package com.weather.service;

import com.weather.dto.StatisticsResponse;
import com.weather.util.CityValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StatisticsServiceTest {

    private StatisticsService statisticsService;

    @BeforeEach
    void setUp() {
        FavoriteService favoriteService = new FavoriteService(new CityValidator());
        statisticsService = new StatisticsService(favoriteService);
    }

    @Test
    void recordSearch_UpdatesMetricsCorrectly() {
        statisticsService.recordSearch("Sydney", false);
        statisticsService.recordSearch("Sydney", true);
        statisticsService.recordSearch("Melbourne", false);

        StatisticsResponse stats = statisticsService.getStatistics();
        assertEquals(3, stats.getTotalSearches());
        assertEquals(1, stats.getCacheHits());
        assertEquals(2, stats.getApiCalls());
        assertEquals("Sydney", stats.getMostSearchedCity());
    }
}
