const Footer = () => {
  const year = new Date().getFullYear();

  return (
    <footer className="footer-custom mt-auto">
      <div className="container">
        <div className="row align-items-center">
          <div className="col-md-6 text-center text-md-start mb-2 mb-md-0">
            <span>
              <i className="bi bi-cloud-sun-fill me-2" style={{ color: 'var(--accent-color)' }}></i>
              <strong style={{ color: 'var(--text-secondary)' }}>WeatherApp</strong>
            </span>
            <span className="ms-2">© {year} — Powered by OpenWeatherMap</span>
          </div>
          <div className="col-md-6 text-center text-md-end">
            <span className="me-3">
              <i className="bi bi-code-slash me-1"></i>
              Spring Boot + React
            </span>
            <span>
              <i className="bi bi-geo-alt-fill me-1"></i>
              Real-time Weather
            </span>
          </div>
        </div>
      </div>
    </footer>
  );
};

export default Footer;
