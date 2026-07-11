const ErrorAlert = ({ error, onDismiss }) => {
  if (!error) return null;

  const getErrorIcon = (status) => {
    if (!status) return 'bi-wifi-off';
    if (status === 404) return 'bi-geo-alt-fill';
    if (status === 503) return 'bi-plug-fill';
    if (status === 408) return 'bi-clock-fill';
    return 'bi-exclamation-triangle-fill';
  };

  const getErrorTitle = (status) => {
    if (!status) return 'Connection Error';
    if (status === 404) return 'City Not Found';
    if (status === 503) return 'Service Unavailable';
    if (status === 408) return 'Request Timeout';
    if (status === 500) return 'Server Error';
    return 'Something Went Wrong';
  };

  const errorIcon = getErrorIcon(error.status);
  const errorTitle = getErrorTitle(error.status);

  return (
    <div className="error-container fade-in" role="alert">
      <i
        className={`bi ${errorIcon}`}
        style={{ fontSize: '2.5rem', color: 'var(--danger-color)', marginBottom: '12px', display: 'block' }}
        aria-hidden="true"
      ></i>
      <h5 style={{ color: 'var(--danger-color)', marginBottom: '8px' }}>{errorTitle}</h5>
      <p style={{ color: 'var(--text-secondary)', marginBottom: '16px' }}>
        {error.message || 'An unexpected error occurred. Please try again.'}
      </p>
      {onDismiss && (
        <button
          className="btn btn-sm"
          style={{
            background: 'rgba(255, 107, 107, 0.2)',
            border: '1px solid rgba(255, 107, 107, 0.3)',
            color: 'var(--danger-color)',
            borderRadius: '50px',
            padding: '6px 18px',
          }}
          onClick={onDismiss}
          aria-label="Dismiss error"
        >
          <i className="bi bi-x-circle me-1"></i>
          Dismiss
        </button>
      )}
    </div>
  );
};

export default ErrorAlert;
