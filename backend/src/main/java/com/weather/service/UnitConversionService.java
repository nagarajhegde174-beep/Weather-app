package com.weather.service;

import com.weather.dto.UnitConversionRequest;
import com.weather.dto.UnitConversionResponse;
import com.weather.util.WeatherUnitConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UnitConversionService {

    private final WeatherUnitConverter unitConverter;

    public UnitConversionResponse convert(UnitConversionRequest request) {
        if (request == null || request.getValue() == null) {
            throw new IllegalArgumentException("Conversion request and value cannot be null.");
        }

        double val = request.getValue();
        String from = request.getFromUnit() != null ? request.getFromUnit().trim().toUpperCase() : "C";
        String to = request.getToUnit() != null ? request.getToUnit().trim().toUpperCase() : "F";
        double result;

        if (from.equals("C") && to.equals("F")) {
            result = unitConverter.celsiusToFahrenheit(val);
        } else if (from.equals("F") && to.equals("C")) {
            result = unitConverter.fahrenheitToCelsius(val);
        } else if (from.equals("KM") && to.equals("MILES")) {
            result = unitConverter.kilometersToMiles(val);
        } else if (from.equals("MILES") && to.equals("KM")) {
            result = unitConverter.milesToKilometers(val);
        } else if (from.equals("MS") && to.equals("KMH")) {
            result = unitConverter.metersPerSecondToKmPerHour(val);
        } else if (from.equals("KMH") && to.equals("MS")) {
            result = unitConverter.kmPerHourToMetersPerSecond(val);
        } else {
            result = val;
        }

        String formatted = String.format("%.1f %s = %.1f %s", val, from, result, to);

        return UnitConversionResponse.builder()
                .originalValue(val)
                .fromUnit(from)
                .convertedValue(result)
                .toUnit(to)
                .formattedResult(formatted)
                .build();
    }
}
