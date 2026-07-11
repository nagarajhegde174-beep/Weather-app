import axios from 'axios';


const apiClient = axios.create({
  baseURL: '/api',
  timeout: 15000,
  headers: {
    'Content-Type': 'application/json',
  },
});


apiClient.interceptors.request.use(
  (config) => {
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
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
  
  getCurrentWeather: (city) => {
    return apiClient.get('/weather/current', { params: { city } });
  },

  
  getForecast: (city) => {
    return apiClient.get('/weather/forecast', { params: { city } });
  },

  
  healthCheck: () => {
    return apiClient.get('/weather/health');
  },
};

export default weatherService;
