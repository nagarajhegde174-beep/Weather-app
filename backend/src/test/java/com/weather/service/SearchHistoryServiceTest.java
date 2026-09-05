package com.weather.service;

import com.weather.dto.SearchHistoryResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SearchHistoryServiceTest {

    private SearchHistoryService searchHistoryService;

    @BeforeEach
    void setUp() {
        searchHistoryService = new SearchHistoryService();
    }

    @Test
    void addSearch_RecordsCity() {
        searchHistoryService.addSearch("Bangalore");
        SearchHistoryResponse response = searchHistoryService.getHistory();
        assertEquals(1, response.getCount());
        assertEquals("Bangalore", response.getHistory().get(0).getCity());
    }

    @Test
    void addSearch_AvoidsConsecutiveDuplicates() {
        searchHistoryService.addSearch("Bangalore");
        searchHistoryService.addSearch("Bangalore");
        SearchHistoryResponse response = searchHistoryService.getHistory();
        assertEquals(1, response.getCount());
    }

    @Test
    void addSearch_LimitsToTenCities() {
        for (int i = 1; i <= 15; i++) {
            searchHistoryService.addSearch("City" + i);
        }
        SearchHistoryResponse response = searchHistoryService.getHistory();
        assertEquals(10, response.getCount());
        assertEquals("City15", response.getHistory().get(0).getCity());
    }

    @Test
    void clearHistory_ClearsAllEntries() {
        searchHistoryService.addSearch("Delhi");
        searchHistoryService.clearHistory();
        SearchHistoryResponse response = searchHistoryService.getHistory();
        assertEquals(0, response.getCount());
    }
}
