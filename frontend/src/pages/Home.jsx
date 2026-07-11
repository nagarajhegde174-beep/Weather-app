import { useState, useEffect } from 'react';
import Navbar from '../components/Navbar';
import Footer from '../components/Footer';
import SearchBar from '../components/SearchBar';
import WeatherCard from '../components/WeatherCard';
import ForecastCard from '../components/ForecastCard';
import ErrorAlert from '../components/ErrorAlert';
import { SkeletonLoader } from '../components/LoadingSpinner';
import SearchHistory, { useSearchHistory } from '../components/SearchHistory';
import FavoriteCities, { useFavorites } from '../components/FavoriteCities';
import useWeather from '../hooks/useWeather';

const Home = () => {
  const { currentWeather, forecast, loading, error, fetchWeather, clearError } = useWeather();
  const { history, addToHistory, removeFromHistory, clearHistory } = useSearchHistory();
  const { favorites, addFavorite, removeFavorite, isFavorite } = useFavorites();
  const [unit, setUnit] = useState(() => localStorage.getItem('weather-unit') || 'C');
  const [lastCity, setLastCity] = useState('');

  
  useEffect(() => {
    localStorage.setItem('weather-unit', unit);
  }, [unit]);

  const handleSearch = (city) => {
    setLastCity(city);
    addToHistory(city);
    fetchWeather(city);
  };

  const handleRefresh = () => {
    if (lastCity) fetchWeather(lastCity);
  };

  const hasData = currentWeather && forecast;

  return (
    <div style={{ minHeight: '100vh', display: 'flex', flexDirection: 'column' }}>
      <Navbar />

      <main className="flex-grow-1 py-4">
        <div className="container">

          
          <div className="text-center mb-5">
            <h1 className="fw-bold mb-2" style={{
              fontSize: 'clamp(1.8rem, 4vw, 2.8rem)',
              color: 'var(--text-primary)',
            }}>
              <i className="bi bi-cloud-sun-fill me-3" style={{ color: 'var(--accent-color)' }}></i>
              Weather Dashboard
            </h1>
            <p style={{ color: 'var(--text-muted)', marginBottom: '28px' }}>
              Real-time weather and 5-day forecast for any city
            </p>

            <SearchBar onSearch={handleSearch} loading={loading} />

            
            <div className="d-flex justify-content-center mt-3">
              <div className="unit-toggle">
                <button
                  className={`unit-btn ${unit === 'C' ? 'active' : ''}`}
                  onClick={() => setUnit('C')}
                  aria-pressed={unit === 'C'}
                >
                  °C
                </button>
                <button
                  className={`unit-btn ${unit === 'F' ? 'active' : ''}`}
                  onClick={() => setUnit('F')}
                  aria-pressed={unit === 'F'}
                >
                  °F
                </button>
              </div>
            </div>
          </div>

          
          <div className="row g-4">

            
            <div className="col-lg-3 order-lg-1 order-2">
              <div className="glass-card p-3">
                <FavoriteCities
                  favorites={favorites}
                  currentCity={currentWeather?.name}
                  onSearch={handleSearch}
                  onAdd={addFavorite}
                  onRemove={removeFavorite}
                  isFavorite={isFavorite}
                />
                <hr style={{ borderColor: 'var(--border-color)' }} />
                <SearchHistory
                  history={history}
                  onSearch={handleSearch}
                  onRemove={removeFromHistory}
                  onClear={clearHistory}
                />
              </div>
            </div>

            
            <div className="col-lg-9 order-lg-2 order-1">

              
              {error && !loading && (
                <ErrorAlert error={error} onDismiss={clearError} />
              )}

              
              {loading && <SkeletonLoader />}

              
              {!loading && !error && hasData && (
                <>
                  <WeatherCard
                    data={currentWeather}
                    unit={unit}
                    onRefresh={handleRefresh}
                    loading={loading}
                  />
                  <ForecastCard data={forecast} unit={unit} />
                </>
              )}

              
              {!loading && !error && !hasData && (
                <div className="glass-card p-5 text-center fade-in">
                  <i className="bi bi-cloud-sun"
                    style={{ fontSize: '5rem', color: 'var(--accent-color)', opacity: 0.6 }}
                  ></i>
                  <h4 className="mt-3 mb-2" style={{ color: 'var(--text-secondary)' }}>
                    Search for a city to get started
                  </h4>
                  <p style={{ color: 'var(--text-muted)' }}>
                    Enter any city name above to see current weather and a 5-day forecast.
                  </p>
                </div>
              )}

            </div>
          </div>

        </div>
      </main>

      <Footer />
    </div>
  );
};

export default Home;
