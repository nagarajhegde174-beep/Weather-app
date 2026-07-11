
export const toFahrenheit = (celsius) => {
  return Math.round((celsius * 9) / 5 + 32);
};


export const formatTemp = (celsius, unit = 'C') => {
  if (celsius === null || celsius === undefined) return 'N/A';
  const value = unit === 'F' ? toFahrenheit(celsius) : Math.round(celsius);
  return `${value}°${unit}`;
};


export const formatTimestamp = (unix, timezone = 0) => {
  const date = new Date((unix + timezone) * 1000);
  return date.toUTCString().replace(' GMT', '');
};


export const formatTime = (unix, timezone = 0) => {
  const date = new Date((unix + timezone) * 1000);
  return date.toLocaleTimeString('en-US', {
    hour: '2-digit',
    minute: '2-digit',
    hour12: true,
    timeZone: 'UTC',
  });
};


export const formatDate = (unix, timezone = 0) => {
  const date = new Date((unix + timezone) * 1000);
  return date.toLocaleDateString('en-US', {
    weekday: 'long',
    year: 'numeric',
    month: 'long',
    day: 'numeric',
    timeZone: 'UTC',
  });
};


export const formatShortDate = (dtTxt) => {
  const date = new Date(dtTxt);
  return date.toLocaleDateString('en-US', {
    weekday: 'short',
    month: 'short',
    day: 'numeric',
  });
};


export const getWeatherIconUrl = (icon) => {
  return `https://openweathermap.org/img/wn/${icon}@2x.png`;
};


export const getWindDirection = (degrees) => {
  const directions = ['N', 'NNE', 'NE', 'ENE', 'E', 'ESE', 'SE', 'SSE',
    'S', 'SSW', 'SW', 'WSW', 'W', 'WNW', 'NW', 'NNW'];
  const index = Math.round(degrees / 22.5) % 16;
  return directions[index];
};


export const formatVisibility = (meters) => {
  if (!meters) return 'N/A';
  return meters >= 1000 ? `${(meters / 1000).toFixed(1)} km` : `${meters} m`;
};


export const getWeatherBootstrapIcon = (iconCode) => {
  const iconMap = {
    '01d': 'bi-sun-fill',
    '01n': 'bi-moon-stars-fill',
    '02d': 'bi-cloud-sun-fill',
    '02n': 'bi-cloud-moon-fill',
    '03d': 'bi-cloud-fill',
    '03n': 'bi-cloud-fill',
    '04d': 'bi-clouds-fill',
    '04n': 'bi-clouds-fill',
    '09d': 'bi-cloud-drizzle-fill',
    '09n': 'bi-cloud-drizzle-fill',
    '10d': 'bi-cloud-rain-fill',
    '10n': 'bi-cloud-rain-fill',
    '11d': 'bi-cloud-lightning-rain-fill',
    '11n': 'bi-cloud-lightning-rain-fill',
    '13d': 'bi-snow',
    '13n': 'bi-snow',
    '50d': 'bi-cloud-fog2-fill',
    '50n': 'bi-cloud-fog2-fill',
  };
  return iconMap[iconCode] || 'bi-cloud-fill';
};


export const capitalize = (str) => {
  if (!str) return '';
  return str
    .split(' ')
    .map((w) => w.charAt(0).toUpperCase() + w.slice(1))
    .join(' ');
};


export const formatCoords = (lat, lon) => {
  if (!lat || !lon) return 'N/A';
  const latDir = lat >= 0 ? 'N' : 'S';
  const lonDir = lon >= 0 ? 'E' : 'W';
  return `${Math.abs(lat).toFixed(2)}°${latDir}, ${Math.abs(lon).toFixed(2)}°${lonDir}`;
};


export const groupForecastByDay = (forecastList) => {
  if (!forecastList) return [];
  const seen = new Set();
  return forecastList.filter((item) => {
    const date = item.dt_txt.split(' ')[0];
    if (!seen.has(date)) {
      seen.add(date);
      return true;
    }
    return false;
  }).slice(0, 5);
};


export const copyToClipboard = async (text) => {
  try {
    await navigator.clipboard.writeText(text);
    return true;
  } catch {
    return false;
  }
};


export const getCurrentDateTime = () => {
  const now = new Date();
  return {
    date: now.toLocaleDateString('en-US', {
      weekday: 'long',
      year: 'numeric',
      month: 'long',
      day: 'numeric',
    }),
    time: now.toLocaleTimeString('en-US', {
      hour: '2-digit',
      minute: '2-digit',
      second: '2-digit',
    }),
  };
};
