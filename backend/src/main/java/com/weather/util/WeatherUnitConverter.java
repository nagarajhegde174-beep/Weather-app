package com.weather.util;

import org.springframework.stereotype.Component;

@Component
public class WeatherUnitConverter {

    public double celsiusToFahrenheit(double celsius) {
        return Math.round(((celsius * 9.0 / 5.0) + 32.0) * 10.0) / 10.0;
    }

    public double fahrenheitToCelsius(double fahrenheit) {
        return Math.round(((fahrenheit - 32.0) * 5.0 / 9.0) * 10.0) / 10.0;
    }

    public double kilometersToMiles(double kilometers) {
        return Math.round((kilometers * 0.621371) * 10.0) / 10.0;
    }

    public double milesToKilometers(double miles) {
        return Math.round((miles / 0.621371) * 10.0) / 10.0;
    }

    public double metersPerSecondToKmPerHour(double ms) {
        return Math.round((ms * 3.6) * 10.0) / 10.0;
    }

    public double kmPerHourToMetersPerSecond(double kmh) {
        return Math.round((kmh / 3.6) * 10.0) / 10.0;
    }
}
