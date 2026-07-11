<div align="center">

# 🌤️ Weather App

### Full Stack Weather Application — Spring Boot + React

[![Java](https://img.shields.io/badge/Java-21-orange?style=flat-square&logo=openjdk&logoColor=white)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.1-6DB33F?style=flat-square&logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![React](https://img.shields.io/badge/React-18-61DAFB?style=flat-square&logo=react&logoColor=black)](https://reactjs.org/)
[![Vite](https://img.shields.io/badge/Vite-5-646CFF?style=flat-square&logo=vite&logoColor=white)](https://vitejs.dev/)
[![Bootstrap](https://img.shields.io/badge/Bootstrap-5-7952B3?style=flat-square&logo=bootstrap&logoColor=white)](https://getbootstrap.com/)
[![Axios](https://img.shields.io/badge/Axios-HTTP%20Client-5A29E4?style=flat-square&logo=axios&logoColor=white)](https://axios-http.com/)
[![Maven](https://img.shields.io/badge/Maven-Build-C71A36?style=flat-square&logo=apachemaven&logoColor=white)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/License-MIT-yellow?style=flat-square)](LICENSE)

**Real-time weather forecasts powered by OpenWeatherMap API**

</div>

---

## 🖼️ UI Preview

<div align="center">

### 🌙 Dark Mode
![Dark Mode UI](https://raw.githubusercontent.com/github/explore/main/topics/react/react.png)

> Dashboard with glassmorphism cards, animated weather icons, gradient background

</div>

---

## 🌐 Powered By

<div align="center">

| | | | | |
|:---:|:---:|:---:|:---:|:---:|
| <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" width="55" height="55" alt="Java"/> | <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original.svg" width="55" height="55" alt="Spring Boot"/> | <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/react/react-original.svg" width="55" height="55" alt="React"/> | <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/vitejs/vitejs-original.svg" width="55" height="55" alt="Vite"/> | <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/bootstrap/bootstrap-original.svg" width="55" height="55" alt="Bootstrap"/> |
| **Java 21** | **Spring Boot** | **React 18** | **Vite 5** | **Bootstrap 5** |

</div>

---

## ✨ Features

| | Feature | Description |
|---|---|---|
| 🌡️ | **Current Weather** | Temperature, Feels Like, Min/Max, Humidity, Pressure, Wind Speed |
| 📅 | **5-Day Forecast** | Daily forecast cards with icon, temp, humidity, wind |
| 🌅 | **Sun Info** | Sunrise, Sunset, Visibility, Cloud Coverage, Coordinates |
| ⭐ | **Favorites** | Save and quick-search favorite cities — stored in LocalStorage |
| 🕐 | **Search History** | Last 10 searches with timestamps, delete or clear all |
| 🌗 | **Theme Toggle** | Dark / Light mode persisted in LocalStorage |
| 🌡️ | **Unit Toggle** | Switch between Celsius and Fahrenheit |
| 🔄 | **Refresh** | Re-fetch latest data without re-typing the city |
| 📋 | **Copy Details** | One-click copy of full weather summary to clipboard |
| 📱 | **Responsive** | Mobile, tablet and desktop ready |

---

## 🛠️ Tech Stack

### Backend
| Technology | Version | Purpose |
|---|---|---|
| <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" width="20"/> Java | 21 | Core language |
| <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original.svg" width="20"/> Spring Boot | 3.3.1 | Application framework |
| Spring Web + RestTemplate | — | REST API & external HTTP calls |
| Lombok | — | Boilerplate reduction |
| Bean Validation | — | Input validation |
| <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/maven/maven-original.svg" width="20"/> Maven | — | Build tool |

### Frontend
| Technology | Version | Purpose |
|---|---|---|
| <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/react/react-original.svg" width="20"/> React | 18 | UI library |
| <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/vitejs/vitejs-original.svg" width="20"/> Vite | 5 | Build tool & dev server |
| <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/bootstrap/bootstrap-original.svg" width="20"/> Bootstrap | 5 | Responsive UI & icons |
| Axios | — | HTTP client |
| React Router DOM | 6 | Client-side routing |

---

## 🏗️ Project Structure

```
Weather-App/
├── weather-backend/
│   └── src/main/java/com/weather/
│       ├── controller/        WeatherController.java
│       ├── service/           WeatherService.java
│       ├── dto/               CurrentWeatherDTO, ForecastDTO, ErrorResponseDTO
│       ├── config/            WeatherApiConfig, CorsConfig, RestTemplateConfig
│       ├── exception/         GlobalExceptionHandler, Custom Exceptions
│       ├── model/             WeatherApiResponse
│       ├── util/              WeatherUtil
│       └── WeatherApplication.java
│
├── weather-frontend/
│   └── src/
│       ├── components/        Navbar, Footer, SearchBar, WeatherCard, ForecastCard,
│       │                      SearchHistory, FavoriteCities, LoadingSpinner, ErrorAlert
│       ├── pages/             Home.jsx
│       ├── services/          weatherService.js
│       ├── hooks/             useWeather, useTheme, useLocalStorage
│       ├── utils/             weatherUtils.js
│       └── css/               global.css
│
├── README.md
└── .gitignore
```

### Request Flow

```
React (5173) → Axios → Spring Boot (8080) → OpenWeatherMap API → JSON → React UI
```

---

## 🚀 Getting Started

**Prerequisites:** Java 21+, Node.js 18+

### Backend
```bash
cd weather-backend
./mvnw spring-boot:run
```
> Runs at `http://localhost:8080`

### Frontend
```bash
cd weather-frontend
npm install
npm run dev
```
> Runs at `http://localhost:5173`

---

## 🔌 API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/api/weather/current?city=London` | Current weather |
| `GET` | `/api/weather/forecast?city=London` | 5-day forecast |
| `GET` | `/api/weather/health` | Health check |

**Error Response Format**
```json
{
  "timestamp": "2025-07-11T10:30:00",
  "status": 404,
  "error": "City Not Found",
  "message": "City 'Xyz' not found. Please check the spelling and try again.",
  "path": "/api/weather/current"
}
```

---

