const SkeletonBox = ({ width = '100%', height = '20px', borderRadius = '8px', style = {} }) => (
  <div
    className="skeleton"
    style={{ width, height, borderRadius, ...style }}
  ></div>
);

export const SkeletonLoader = () => (
  <div className="fade-in">
    
    <div className="weather-main-card mb-4">
      <div className="row align-items-center">
        <div className="col-md-8">
          <SkeletonBox width="60%" height="28px" style={{ marginBottom: '12px' }} />
          <SkeletonBox width="40%" height="72px" style={{ marginBottom: '12px', borderRadius: '12px' }} />
          <SkeletonBox width="50%" height="22px" />
        </div>
        <div className="col-md-4 text-center">
          <SkeletonBox width="120px" height="120px" borderRadius="50%" style={{ margin: '0 auto' }} />
        </div>
      </div>
      <hr style={{ borderColor: 'var(--border-color)' }} />
      <div className="row g-3 mt-1">
        {[...Array(6)].map((_, i) => (
          <div key={i} className="col-6 col-md-4 col-lg-2">
            <SkeletonBox height="90px" borderRadius="16px" />
          </div>
        ))}
      </div>
    </div>

    
    <SkeletonBox width="200px" height="28px" style={{ marginBottom: '16px' }} />
    <div className="row g-3">
      {[...Array(5)].map((_, i) => (
        <div key={i} className="col-6 col-md-4 col-lg">
          <SkeletonBox height="180px" borderRadius="16px" />
        </div>
      ))}
    </div>
  </div>
);

const LoadingSpinner = ({ message = 'Fetching weather data...' }) => (
  <div className="spinner-container">
    <div className="spinner-ring" role="status" aria-label="Loading"></div>
    <p style={{ color: 'var(--text-muted)', fontSize: '0.95rem' }}>{message}</p>
  </div>
);

export default LoadingSpinner;
