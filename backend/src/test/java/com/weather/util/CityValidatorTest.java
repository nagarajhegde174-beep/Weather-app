package com.weather.util;

import com.weather.exception.InvalidCityException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CityValidatorTest {

    private CityValidator cityValidator;

    @BeforeEach
    void setUp() {
        cityValidator = new CityValidator();
    }

    @Test
    void validateAndNormalize_ValidCity_ReturnsTrimmedCity() {
        String input = "  Bangalore  ";
        String result = cityValidator.validateAndNormalize(input);
        assertEquals("Bangalore", result);
    }

    @Test
    void validateAndNormalize_NullOrEmpty_ThrowsInvalidCityException() {
        assertThrows(InvalidCityException.class, () -> cityValidator.validateAndNormalize(null));
        assertThrows(InvalidCityException.class, () -> cityValidator.validateAndNormalize("   "));
    }

    @Test
    void validateAndNormalize_NumericOnly_ThrowsInvalidCityException() {
        assertThrows(InvalidCityException.class, () -> cityValidator.validateAndNormalize("12345"));
    }

    @Test
    void validateAndNormalize_InvalidCharacters_ThrowsInvalidCityException() {
        assertThrows(InvalidCityException.class, () -> cityValidator.validateAndNormalize("London<script>"));
    }

    @Test
    void validateAndNormalize_TooLong_ThrowsInvalidCityException() {
        String longName = "A".repeat(101);
        assertThrows(InvalidCityException.class, () -> cityValidator.validateAndNormalize(longName));
    }
}
