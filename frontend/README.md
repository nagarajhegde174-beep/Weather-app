# Weather Frontend

React + Vite frontend for the Weather Application.

## Tech Stack
- React 18
- Vite 5
- Bootstrap 5 + Bootstrap Icons
- Axios
- React Router DOM

## Getting Started

```bash
cd weather-frontend
npm install
npm run dev
```

App runs at: http://localhost:5173

## Folder Structure

```
src/
├── components/     # Reusable UI components
├── pages/          # Route-level page components
├── services/       # Axios API client
├── hooks/          # Custom React hooks
├── utils/          # Pure utility functions
├── css/            # Global styles
├── App.jsx
├── main.jsx
└── routes.jsx
```

## Available Scripts

| Command         | Description               |
|----------------|---------------------------|
| `npm run dev`   | Start development server  |
| `npm run build` | Build for production      |
| `npm run preview` | Preview production build |

## API Configuration

The Vite dev server proxies all `/api` requests to `http://localhost:8080`.
Backend must be running before searching for weather.
