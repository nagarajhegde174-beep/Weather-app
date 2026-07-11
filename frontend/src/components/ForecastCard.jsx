import { formatTemp, formatShortDate, getWeatherIconUrl, capitalize, groupForecastByDay } from '../utils/weatherUtils';

const ForecastCard = ({ data, unit }) => {
  if (!data?.list) return null;

  const days = groupForecastByDay(data.list);

  return (
    <div className="fade-in">
      <h5 className="fw-bold mb-3" style={{ color: 'var(--text-primary)' }}>
        <i className="bi bi-calendar-week-fill me-2" style={{ color: 'var(--accent-color)' }}></i>
        5-Day Forecast
      </h5>

      <div className="row g-3">
        {days.map((item, index) => {
          const w = item.weather?.[0];
          return (
            <div key={index} className="col-6 col-md-4 col-lg">
              <div className="forecast-card">
                <p className="fw-semibold mb-2" style={{ color: 'var(--text-secondary)', fontSize: '0.85rem' }}>
                  {formatShortDate(item.dt_txt)}
                </p>

                <img
                  src={getWeatherIconUrl(w?.icon)}
                  alt={w?.description}
                  className="forecast-icon"
                />

                <p className="fw-bold mb-1" style={{ color: 'var(--text-primary)', fontSize: '1.2rem' }}>
                  {formatTemp(item.main?.temp, unit)}
                </p>

                <p className="mb-2" style={{ color: 'var(--text-muted)', fontSize: '0.78rem' }}>
                  {capitalize(w?.description)}
                </p>

                <div className="d-flex justify-content-center gap-2 flex-wrap">
                  <span className="weather-badge">
                    <i className="bi bi-droplet-fill" style={{ color: '#4fc3f7' }}></i>
                    {item.main?.humidity}%
                  </span>
                  <span className="weather-badge">
                    <i className="bi bi-wind" style={{ color: '#81c784' }}></i>
                    {item.wind?.speed}m/s
                  </span>
                </div>
              </div>
            </div>
          );
        })}
      </div>
    </div>
  );
};

export default ForecastCard;
