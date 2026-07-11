import useLocalStorage from '../hooks/useLocalStorage';

export const useSearchHistory = () => {
  const [history, setHistory, clearHistory] = useLocalStorage('weather-search-history', []);

  const addToHistory = (city) => {
    setHistory((prev) => {
      const filtered = prev.filter((c) => c.city.toLowerCase() !== city.toLowerCase());
      return [{ city, timestamp: Date.now() }, ...filtered].slice(0, 10);
    });
  };

  const removeFromHistory = (city) => {
    setHistory((prev) => prev.filter((c) => c.city !== city));
  };

  return { history, addToHistory, removeFromHistory, clearHistory };
};

const SearchHistory = ({ history, onSearch, onRemove, onClear }) => {
  if (!history?.length) return null;

  const formatTime = (ts) => {
    const diff = Math.floor((Date.now() - ts) / 60000);
    if (diff < 1) return 'just now';
    if (diff < 60) return `${diff}m ago`;
    const h = Math.floor(diff / 60);
    if (h < 24) return `${h}h ago`;
    return `${Math.floor(h / 24)}d ago`;
  };

  return (
    <div className="fade-in mb-4">
      <div className="d-flex justify-content-between align-items-center mb-2">
        <h6 className="fw-bold mb-0" style={{ color: 'var(--text-secondary)' }}>
          <i className="bi bi-clock-history me-2" style={{ color: 'var(--accent-color)' }}></i>
          Recent Searches
        </h6>
        <button
          className="btn btn-sm"
          style={{
            background: 'transparent',
            border: 'none',
            color: 'var(--danger-color)',
            fontSize: '0.8rem',
            padding: '2px 8px',
          }}
          onClick={onClear}
          aria-label="Clear search history"
        >
          <i className="bi bi-trash me-1"></i>Clear all
        </button>
      </div>

      <div>
        {history.map((item) => (
          <div
            key={item.city}
            className="history-item"
            onClick={() => onSearch(item.city)}
            role="button"
            tabIndex={0}
            onKeyDown={(e) => e.key === 'Enter' && onSearch(item.city)}
            aria-label={`Search ${item.city} again`}
          >
            <div className="d-flex align-items-center gap-2">
              <i className="bi bi-geo-alt" style={{ color: 'var(--accent-color)' }}></i>
              <span className="history-city">{item.city}</span>
            </div>
            <div className="d-flex align-items-center gap-2">
              <span className="history-time">{formatTime(item.timestamp)}</span>
              <button
                className="btn btn-sm p-0"
                style={{ background: 'transparent', border: 'none', color: 'var(--text-muted)', lineHeight: 1 }}
                onClick={(e) => { e.stopPropagation(); onRemove(item.city); }}
                aria-label={`Remove ${item.city} from history`}
              >
                <i className="bi bi-x-lg" style={{ fontSize: '0.75rem' }}></i>
              </button>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default SearchHistory;
