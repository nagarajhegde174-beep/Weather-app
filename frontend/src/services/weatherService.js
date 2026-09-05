import axios from 'axios';

const apiClient = axios.create({
  baseURL: '/api',
  timeout: 15000,
  headers: {
    'Content-Type': 'application/json',
  },
});

apiClient.interceptors.request.use(
  (config) => config,
  (error) => Promise.reject(error)
);

apiClient.interceptors.response.use(
  (response) => response.data,
  (error) => {
    if (error.code === 'ECONNABORTED') {
      return Promise.reject({
        message: 'Request timed out. Please try again.',
        status: 408,
      });
    }
    if (!error.response) {
      return Promise.reject({
        message: 'Unable to connect to the server. Please check your internet connection.',
        status: 503,
      });
    }
    const { status, data } = error.response;
    return Promise.reject({
      message: data?.message || 'An error occurred. Please try again.',
      status,
      error: data?.error,
    });
  }
);

const weatherService = {
  getWeatherSummary: (city) => {
    return apiClient.get('/weather', { params: { city } });
  },

  getCurrentWeather: (city) => {
    return apiClient.get('/weather/current', { params: { city } });
  },

  getForecast: (city) => {
    return apiClient.get('/weather/forecast', { params: { city } });
  },

  getSearchHistory: () => {
    return apiClient.get('/weather/history');
  },

  clearSearchHistory: () => {
    return apiClient.delete('/weather/history');
  },

  removeSearchHistoryCity: (city) => {
    return apiClient.delete(`/weather/history/${encodeURIComponent(city)}`);
  },

  getFavorites: () => {
    return apiClient.get('/favorites');
  },

  addFavorite: (city) => {
    return apiClient.post('/favorites', { city });
  },

  removeFavorite: (city) => {
    return apiClient.delete(`/favorites/${encodeURIComponent(city)}`);
  },

  getStatistics: () => {
    return apiClient.get('/weather/statistics');
  },

  healthCheck: () => {
    return apiClient.get('/health');
  },
};

export default weatherService;
