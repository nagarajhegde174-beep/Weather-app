import { useEffect } from 'react';
import useLocalStorage from '../hooks/useLocalStorage';
import weatherService from '../services/weatherService';

export const useFavorites = () => {
  const [favorites, setFavorites] = useLocalStorage('weather-favorites', []);

  const fetchFavorites = async () => {
    try {
      const res = await weatherService.getFavorites();
      if (res && res.favorites) {
        setFavorites(res.favorites.map((f) => f.city));
      }
    } catch {
      // Fallback to local storage if backend unavailable
    }
  };

  useEffect(() => {
    fetchFavorites();
  }, []);

  const addFavorite = async (city) => {
    if (!city) return;
    setFavorites((prev) => {
      if (prev.some((c) => c.toLowerCase() === city.toLowerCase())) return prev;
      return [...prev, city];
    });
    try {
      await weatherService.addFavorite(city);
    } catch {
      // Ignore backend sync failure
    }
  };

  const removeFavorite = async (city) => {
    setFavorites((prev) => prev.filter((c) => c.toLowerCase() !== city.toLowerCase()));
    try {
      await weatherService.removeFavorite(city);
    } catch {
      // Ignore backend sync failure
    }
  };

  const isFavorite = (city) =>
    favorites.some((c) => c.toLowerCase() === city?.toLowerCase());

  return { favorites, addFavorite, removeFavorite, isFavorite, fetchFavorites };
};

const FavoriteCities = ({ favorites, currentCity, onSearch, onAdd, onRemove, isFavorite }) => {
  return (
    <div className="fade-in mb-4">
      <div className="d-flex justify-content-between align-items-center mb-2">
        <h6 className="fw-bold mb-0" style={{ color: 'var(--text-secondary)' }}>
          <i className="bi bi-star-fill me-2" style={{ color: '#ffd43b' }}></i>
          Favorite Cities
        </h6>

        {currentCity && !isFavorite(currentCity) && (
          <button
            className="btn btn-sm"
            style={{
              background: 'rgba(255, 212, 59, 0.1)',
              border: '1px solid rgba(255, 212, 59, 0.3)',
              color: '#ffd43b',
              fontSize: '0.8rem',
              borderRadius: '50px',
              padding: '3px 10px',
            }}
            onClick={() => onAdd(currentCity)}
            aria-label={`Add ${currentCity} to favorites`}
          >
            <i className="bi bi-star me-1"></i>Add "{currentCity}"
          </button>
        )}
      </div>

      {favorites.length === 0 ? (
        <p style={{ color: 'var(--text-muted)', fontSize: '0.85rem', fontStyle: 'italic' }}>
          No favorites yet. Search a city and add it here.
        </p>
      ) : (
        <div>
          {favorites.map((city) => (
            <div
              key={city}
              className="favorite-item"
              onClick={() => onSearch(city)}
              role="button"
              tabIndex={0}
              onKeyDown={(e) => e.key === 'Enter' && onSearch(city)}
              aria-label={`Search ${city}`}
            >
              <div className="d-flex align-items-center gap-2">
                <i className="bi bi-star-fill" style={{ color: '#ffd43b', fontSize: '0.85rem' }}></i>
                <span className="favorite-city">{city}</span>
              </div>
              <button
                className="btn btn-sm p-0"
                style={{ background: 'transparent', border: 'none', color: 'var(--danger-color)', lineHeight: 1 }}
                onClick={(e) => { e.stopPropagation(); onRemove(city); }}
                aria-label={`Remove ${city} from favorites`}
              >
                <i className="bi bi-trash" style={{ fontSize: '0.8rem' }}></i>
              </button>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default FavoriteCities;
