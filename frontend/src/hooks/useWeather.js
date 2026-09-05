import { useState, useCallback } from 'react';
import weatherService from '../services/weatherService';

const useWeather = () => {
  const [currentWeather, setCurrentWeather] = useState(null);
  const [forecast, setForecast] = useState(null);
  const [weatherSummary, setWeatherSummary] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const fetchWeather = useCallback(async (city) => {
    if (!city || !city.trim()) {
      setError({ message: 'Please enter a city name.' });
      return;
    }

    setLoading(true);
    setError(null);

    try {
      const [weatherData, forecastData, summaryData] = await Promise.all([
        weatherService.getCurrentWeather(city.trim()),
        weatherService.getForecast(city.trim()),
        weatherService.getWeatherSummary(city.trim()).catch(() => null),
      ]);

      setCurrentWeather(weatherData);
      setForecast(forecastData);
      setWeatherSummary(summaryData?.summary || null);
    } catch (err) {
      setError(err);
      setCurrentWeather(null);
      setForecast(null);
      setWeatherSummary(null);
    } finally {
      setLoading(false);
    }
  }, []);

  const clearError = useCallback(() => setError(null), []);
  const clearWeather = useCallback(() => {
    setCurrentWeather(null);
    setForecast(null);
    setWeatherSummary(null);
    setError(null);
  }, []);

  return {
    currentWeather,
    forecast,
    weatherSummary,
    loading,
    error,
    fetchWeather,
    clearError,
    clearWeather,
  };
};

export default useWeather;
