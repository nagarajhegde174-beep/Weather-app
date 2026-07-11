import { useState, useCallback } from 'react';
import weatherService from '../services/weatherService';

const useWeather = () => {
  const [currentWeather, setCurrentWeather] = useState(null);
  const [forecast, setForecast] = useState(null);
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
      const [weatherData, forecastData] = await Promise.all([
        weatherService.getCurrentWeather(city.trim()),
        weatherService.getForecast(city.trim()),
      ]);

      setCurrentWeather(weatherData);
      setForecast(forecastData);
    } catch (err) {
      setError(err);
      setCurrentWeather(null);
      setForecast(null);
    } finally {
      setLoading(false);
    }
  }, []);

  const clearError = useCallback(() => setError(null), []);
  const clearWeather = useCallback(() => {
    setCurrentWeather(null);
    setForecast(null);
    setError(null);
  }, []);

  return {
    currentWeather,
    forecast,
    loading,
    error,
    fetchWeather,
    clearError,
    clearWeather,
  };
};

export default useWeather;
