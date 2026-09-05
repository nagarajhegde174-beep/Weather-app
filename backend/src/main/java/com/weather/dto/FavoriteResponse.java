package com.weather.dto;

import com.weather.model.FavoriteCity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FavoriteResponse {
    private List<FavoriteCity> favorites;
    private int totalFavorites;
    private String message;
}
