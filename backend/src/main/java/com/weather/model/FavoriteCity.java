package com.weather.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FavoriteCity {
    private String city;
    private String country;
    private LocalDateTime addedAt;
    private Double lastTemperature;
    private String lastCondition;
}
