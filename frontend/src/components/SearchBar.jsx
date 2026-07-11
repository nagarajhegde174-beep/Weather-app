import { useState } from 'react';

const SearchBar = ({ onSearch, loading }) => {
  const [city, setCity] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    const trimmed = city.trim();
    if (!trimmed) return;
    onSearch(trimmed);
  };

  return (
    <form onSubmit={handleSubmit} className="search-container w-100">
      <div style={{ position: 'relative' }}>
        <i
          className="bi bi-search"
          style={{
            position: 'absolute',
            left: '20px',
            top: '50%',
            transform: 'translateY(-50%)',
            color: 'var(--text-muted)',
            fontSize: '1rem',
            pointerEvents: 'none',
          }}
        ></i>
        <input
          type="text"
          className="search-input"
          placeholder="Search city... e.g. London, Tokyo, New York"
          value={city}
          onChange={(e) => setCity(e.target.value)}
          style={{ paddingLeft: '48px' }}
          disabled={loading}
          autoComplete="off"
          aria-label="City search"
        />
        <button
          type="submit"
          className="search-btn"
          disabled={loading || !city.trim()}
          aria-label="Search weather"
        >
          {loading ? (
            <span
              className="spinner-border spinner-border-sm me-1"
              role="status"
              aria-hidden="true"
            ></span>
          ) : (
            <i className="bi bi-search me-1"></i>
          )}
          {loading ? 'Searching...' : 'Search'}
        </button>
      </div>
    </form>
  );
};

export default SearchBar;
