import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import ThemeToggle from './ThemeToggle';
import weatherService from '../services/weatherService';

const Navbar = () => {
  const [backendStatus, setBackendStatus] = useState('CHECKING');

  useEffect(() => {
    weatherService.healthCheck()
      .then((res) => {
        if (res && res.status === 'UP') {
          setBackendStatus('UP');
        } else {
          setBackendStatus('OFFLINE');
        }
      })
      .catch(() => setBackendStatus('OFFLINE'));
  }, []);

  return (
    <nav className="navbar-custom">
      <div className="container">
        <div className="d-flex align-items-center justify-content-between">
          <Link to="/" className="navbar-brand-custom">
            <i className="bi bi-cloud-sun-fill"></i>
            WeatherApp
          </Link>

          <div className="d-flex align-items-center gap-3">
            <span
              className={`badge bg-${backendStatus === 'UP' ? 'success' : backendStatus === 'CHECKING' ? 'warning' : 'danger'}-subtle text-${backendStatus === 'UP' ? 'success' : backendStatus === 'CHECKING' ? 'warning' : 'danger'} border`}
              style={{ fontSize: '0.75rem', padding: '4px 8px', borderRadius: '12px' }}
              title={`Spring Boot Backend Status: ${backendStatus}`}
            >
              <i className={`bi bi-${backendStatus === 'UP' ? 'check-circle-fill' : 'exclamation-circle-fill'} me-1`}></i>
              Backend: {backendStatus}
            </span>
            <ThemeToggle />
          </div>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
