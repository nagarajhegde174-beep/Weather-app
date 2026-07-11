import { useState } from 'react';
import {
  formatTemp,
  formatDate,
  formatTime,
  formatVisibility,
  formatCoords,
  getWeatherIconUrl,
  capitalize,
  copyToClipboard,
} from '../utils/weatherUtils';

const StatBox = ({ icon, label, value }) => (
  <div className="stat-card">
    <div className="stat-icon">
      <i className={`bi ${icon}`}></i>
    </div>
    <div className="stat-value">{value}</div>
    <div className="stat-label">{label}</div>
  </div>
);

const WeatherCard = ({ data, unit, onRefresh, loading }) => {
  const [copied, setCopied] = useState(false);

  if (!data) return null;

  const { name, coordinates, weather, main, wind, clouds, sys, visibility, dt, timezone } = data;
  const w = weather?.[0];

  const handleCopy = async () => {
    const text = `Weather in ${name}, ${sys?.country}
Temperature: ${formatTemp(main?.temp, unit)}
Feels Like: ${formatTemp(main?.feelsLike, unit)}
Humidity: ${main?.humidity}%
Wind: ${wind?.speed} m/s
Description: ${capitalize(w?.description)}`;
    const ok = await copyToClipboard(text);
    if (ok) {
      setCopied(true);
      setTimeout(() => setCopied(false), 2000);
    }
  };

  return (
    <div className="weather-main-card fade-in mb-4">

      
      <div className="d-flex justify-content-between align-items-start flex-wrap gap-2 mb-3">
        <div>
          <h2 className="fw-bold mb-1" style={{ color: 'var(--text-primary)', fontSize: '1.8rem' }}>
            <i className="bi bi-geo-alt-fill me-2" style={{ color: 'var(--accent-color)' }}></i>
            {name}
            <span className="ms-2" style={{ fontSize: '1rem', color: 'var(--text-muted)', fontWeight: 400 }}>
              {sys?.country}
            </span>
          </h2>
          <p style={{ color: 'var(--text-muted)', fontSize: '0.9rem', margin: 0 }}>
            <i className="bi bi-calendar3 me-1"></i>
            {formatDate(dt, timezone)} &nbsp;|&nbsp;
            <i className="bi bi-clock me-1"></i>
            {formatTime(dt, timezone)}
          </p>
        </div>

        
        <div className="d-flex gap-2">
          <button
            className="theme-toggle-btn"
            onClick={handleCopy}
            title="Copy weather details"
            aria-label="Copy weather details"
          >
            <i className={`bi ${copied ? 'bi-check2' : 'bi-clipboard'}`}></i>
            <span className="d-none d-sm-inline">{copied ? 'Copied!' : 'Copy'}</span>
          </button>
          <button
            className="theme-toggle-btn"
            onClick={onRefresh}
            disabled={loading}
            title="Refresh weather"
            aria-label="Refresh weather"
          >
            <i className={`bi bi-arrow-clockwise ${loading ? 'spin' : ''}`}></i>
            <span className="d-none d-sm-inline">Refresh</span>
          </button>
        </div>
      </div>

      
      <div className="row align-items-center mb-4">
        <div className="col-8">
          <div className="temperature-display">{formatTemp(main?.temp, unit)}</div>
          <p className="mt-2 mb-1" style={{ color: 'var(--text-secondary)', fontSize: '1.1rem' }}>
            {capitalize(w?.description)}
          </p>
          <div className="d-flex gap-3 flex-wrap" style={{ color: 'var(--text-muted)', fontSize: '0.9rem' }}>
            <span><i className="bi bi-thermometer-low me-1"></i>Min {formatTemp(main?.tempMin, unit)}</span>
            <span><i className="bi bi-thermometer-high me-1"></i>Max {formatTemp(main?.tempMax, unit)}</span>
            <span><i className="bi bi-thermometer-half me-1"></i>Feels {formatTemp(main?.feelsLike, unit)}</span>
          </div>
        </div>
        <div className="col-4 text-end">
          <img
            src={getWeatherIconUrl(w?.icon)}
            alt={w?.description}
            className="weather-icon-large"
          />
        </div>
      </div>

      <hr style={{ borderColor: 'var(--border-color)', margin: '0 0 20px' }} />

      
      <div className="row g-3">
        <div className="col-6 col-md-4 col-lg-2">
          <StatBox icon="bi-droplet-fill"    label="Humidity"    value={`${main?.humidity}%`} />
        </div>
        <div className="col-6 col-md-4 col-lg-2">
          <StatBox icon="bi-wind"            label="Wind Speed"  value={`${wind?.speed} m/s`} />
        </div>
        <div className="col-6 col-md-4 col-lg-2">
          <StatBox icon="bi-speedometer2"    label="Pressure"    value={`${main?.pressure} hPa`} />
        </div>
        <div className="col-6 col-md-4 col-lg-2">
          <StatBox icon="bi-eye-fill"        label="Visibility"  value={formatVisibility(visibility)} />
        </div>
        <div className="col-6 col-md-4 col-lg-2">
          <StatBox icon="bi-cloud-fill"      label="Cloud Cover" value={`${clouds?.all}%`} />
        </div>
        <div className="col-6 col-md-4 col-lg-2">
          <StatBox icon="bi-geo"             label="Coordinates" value={formatCoords(coordinates?.lat, coordinates?.lon)} />
        </div>
        <div className="col-6 col-md-4 col-lg-2">
          <StatBox icon="bi-sunrise-fill"    label="Sunrise"     value={formatTime(sys?.sunrise, timezone)} />
        </div>
        <div className="col-6 col-md-4 col-lg-2">
          <StatBox icon="bi-sunset-fill"     label="Sunset"      value={formatTime(sys?.sunset, timezone)} />
        </div>
      </div>
    </div>
  );
};

export default WeatherCard;
