package com.weather.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WeatherUnitConverterTest {

    private WeatherUnitConverter unitConverter;

    @BeforeEach
    void setUp() {
        unitConverter = new WeatherUnitConverter();
    }

    @Test
    void celsiusToFahrenheit_CorrectConversion() {
        assertEquals(32.0, unitConverter.celsiusToFahrenheit(0.0));
        assertEquals(212.0, unitConverter.celsiusToFahrenheit(100.0));
        assertEquals(77.0, unitConverter.celsiusToFahrenheit(25.0));
    }

    @Test
    void fahrenheitToCelsius_CorrectConversion() {
        assertEquals(0.0, unitConverter.fahrenheitToCelsius(32.0));
        assertEquals(100.0, unitConverter.fahrenheitToCelsius(212.0));
    }

    @Test
    void kilometersToMiles_CorrectConversion() {
        assertEquals(6.2, unitConverter.kilometersToMiles(10.0));
    }

    @Test
    void metersPerSecondToKmPerHour_CorrectConversion() {
        assertEquals(36.0, unitConverter.metersPerSecondToKmPerHour(10.0));
    }
}
