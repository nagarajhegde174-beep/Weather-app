package com.weather.dto;

import com.weather.model.SearchHistory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchHistoryResponse {
    private List<SearchHistory> history;
    private int count;
    private String message;
}
