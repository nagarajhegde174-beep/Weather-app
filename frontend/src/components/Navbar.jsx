import { Link } from 'react-router-dom';
import ThemeToggle from './ThemeToggle';

const Navbar = () => {
  return (
    <nav className="navbar-custom">
      <div className="container">
        <div className="d-flex align-items-center justify-content-between">
          
          <Link to="/" className="navbar-brand-custom">
            <i className="bi bi-cloud-sun-fill"></i>
            WeatherApp
          </Link>

          
          <div className="d-flex align-items-center gap-3">
            <ThemeToggle />
          </div>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
