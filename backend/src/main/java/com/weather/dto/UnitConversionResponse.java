package com.weather.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UnitConversionResponse {
    private Double originalValue;
    private String fromUnit;
    private Double convertedValue;
    private String toUnit;
    private String formattedResult;
}
