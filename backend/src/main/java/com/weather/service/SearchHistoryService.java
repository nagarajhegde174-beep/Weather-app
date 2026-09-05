package com.weather.service;

import com.weather.dto.SearchHistoryResponse;
import com.weather.model.SearchHistory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class SearchHistoryService {

    private static final int MAX_HISTORY_SIZE = 10;
    private final List<SearchHistory> history = Collections.synchronizedList(new ArrayList<>());

    public void addSearch(String city) {
        if (city == null || city.isBlank()) {
            return;
        }
        String normalizedCity = city.trim();

        synchronized (history) {
            
            if (!history.isEmpty() && history.get(0).getCity().equalsIgnoreCase(normalizedCity)) {
                return;
            }

            
            history.removeIf(item -> item.getCity().equalsIgnoreCase(normalizedCity));

            SearchHistory entry = SearchHistory.builder()
                    .city(normalizedCity)
                    .searchedAt(LocalDateTime.now())
                    .timestamp(System.currentTimeMillis())
                    .build();

            history.add(0, entry);

            if (history.size() > MAX_HISTORY_SIZE) {
                history.remove(history.size() - 1);
            }
        }
        log.info("Recorded search history for city: {}", normalizedCity);
    }

    public SearchHistoryResponse getHistory() {
        synchronized (history) {
            List<SearchHistory> copy = new ArrayList<>(history);
            return SearchHistoryResponse.builder()
                    .history(copy)
                    .count(copy.size())
                    .message("Search history retrieved successfully")
                    .build();
        }
    }

    public void removeSearch(String city) {
        if (city == null) return;
        synchronized (history) {
            history.removeIf(item -> item.getCity().equalsIgnoreCase(city.trim()));
        }
        log.info("Removed city '{}' from search history", city);
    }

    public void clearHistory() {
        history.clear();
        log.info("Cleared search history.");
    }
}
