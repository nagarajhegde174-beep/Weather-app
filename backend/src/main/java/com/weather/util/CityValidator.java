package com.weather.util;

import com.weather.exception.InvalidCityException;
import org.springframework.stereotype.Component;

@Component
public class CityValidator {

    private static final int MAX_CITY_LENGTH = 100;
    private static final String VALID_CITY_REGEX = "^[a-zA-Z\\s\\-\\.',]+$";

    public String validateAndNormalize(String city) {
        if (city == null || city.isBlank()) {
            throw new InvalidCityException("City name cannot be empty.");
        }
        
        String trimmed = city.trim();
        
        if (trimmed.length() > MAX_CITY_LENGTH) {
            throw new InvalidCityException("City name is too long. Maximum length allowed is " + MAX_CITY_LENGTH + " characters.");
        }
        
        if (trimmed.matches("^\\d+$")) {
            throw new InvalidCityException("City name cannot consist purely of numeric numbers.");
        }

        if (!trimmed.matches(VALID_CITY_REGEX)) {
            throw new InvalidCityException("City name contains invalid characters. Only letters, spaces, hyphens, and apostrophes are allowed.");
        }

        return trimmed;
    }
}
