package com.weather.util;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


public final class WeatherUtil {

    private WeatherUtil() {  }

    
    public static String validateCity(String city) {
        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException("City name cannot be empty.");
        }
        String trimmed = city.trim();
        if (trimmed.matches("\\d+")) {
            throw new IllegalArgumentException("City name cannot be a number.");
        }
        if (trimmed.length() > 100) {
            throw new IllegalArgumentException("City name is too long.");
        }
        return trimmed;
    }

    
    public static double celsiusToFahrenheit(double celsius) {
        return Math.round(((celsius * 9.0 / 5.0) + 32) * 10.0) / 10.0;
    }

    
    public static String formatUnixTimestamp(long unixSeconds) {
        ZonedDateTime zdt = Instant.ofEpochSecond(unixSeconds).atZone(ZoneOffset.UTC);
        return zdt.format(DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm 'UTC'"));
    }

    
    public static String degreesToCompass(double degrees) {
        String[] labels = {
            "N", "NNE", "NE", "ENE", "E", "ESE", "SE", "SSE",
            "S", "SSW", "SW", "WSW", "W", "WNW", "NW", "NNW"
        };
        return labels[(int) Math.round(degrees / 22.5) % 16];
    }

    
    public static String formatVisibility(int metres) {
        if (metres >= 1000) {
            return (Math.round(metres / 100.0) / 10.0) + " km";
        }
        return metres + " m";
    }
}
