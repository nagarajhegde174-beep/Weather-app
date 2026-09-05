import { useEffect, useState } from 'react';
import weatherService from '../services/weatherService';

const WeatherStatsCard = ({ refreshTrigger }) => {
  const [stats, setStats] = useState(null);
  const [loading, setLoading] = useState(false);

  const fetchStats = async () => {
    setLoading(true);
    try {
      const data = await weatherService.getStatistics();
      setStats(data);
    } catch {
      // Backend stats error handling fallback
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchStats();
  }, [refreshTrigger]);

  if (!stats) return null;

  return (
    <div className="fade-in mt-4">
      <div className="d-flex justify-content-between align-items-center mb-2">
        <h6 className="fw-bold mb-0" style={{ color: 'var(--text-secondary)' }}>
          <i className="bi bi-bar-chart-line-fill me-2" style={{ color: 'var(--accent-color)' }}></i>
          Weather Dashboard Statistics
        </h6>
        <button
          className="btn btn-sm"
          style={{
            background: 'transparent',
            border: 'none',
            color: 'var(--text-muted)',
            fontSize: '0.8rem',
            padding: '2px 8px',
          }}
          onClick={fetchStats}
          disabled={loading}
          aria-label="Refresh Statistics"
        >
          <i className={`bi bi-arrow-clockwise me-1 ${loading ? 'spin' : ''}`}></i>Refresh
        </button>
      </div>

      <div className="row g-2">
        <div className="col-6">
          <div className="p-2 rounded bg-body-tertiary text-center border">
            <small className="text-muted d-block" style={{ fontSize: '0.75rem' }}>Total Searches</small>
            <span className="fw-bold text-primary">{stats.totalSearches}</span>
          </div>
        </div>
        <div className="col-6">
          <div className="p-2 rounded bg-body-tertiary text-center border">
            <small className="text-muted d-block" style={{ fontSize: '0.75rem' }}>Most Searched</small>
            <span className="fw-bold text-success text-truncate d-block" style={{ fontSize: '0.85rem' }}>
              {stats.mostSearchedCity || 'N/A'}
            </span>
          </div>
        </div>
        <div className="col-4">
          <div className="p-2 rounded bg-body-tertiary text-center border">
            <small className="text-muted d-block" style={{ fontSize: '0.75rem' }}>Cache Hits</small>
            <span className="fw-bold text-info">{stats.cacheHits}</span>
          </div>
        </div>
        <div className="col-4">
          <div className="p-2 rounded bg-body-tertiary text-center border">
            <small className="text-muted d-block" style={{ fontSize: '0.75rem' }}>API Calls</small>
            <span className="fw-bold text-warning">{stats.apiCalls}</span>
          </div>
        </div>
        <div className="col-4">
          <div className="p-2 rounded bg-body-tertiary text-center border">
            <small className="text-muted d-block" style={{ fontSize: '0.75rem' }}>Favorites</small>
            <span className="fw-bold text-danger">{stats.favoriteCities}</span>
          </div>
        </div>
      </div>
    </div>
  );
};

export default WeatherStatsCard;
