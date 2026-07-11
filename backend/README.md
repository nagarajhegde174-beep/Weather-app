# Weather Backend

Spring Boot REST API backend for the Weather Application.

## Tech Stack
- Java 21
- Spring Boot 3.3.1
- Spring Web
- Lombok
- Bean Validation
- Maven

## Getting Started

```bash
cd weather-backend
./mvnw spring-boot:run
```

Backend runs at: http://localhost:8080

## REST API Endpoints

| Method | Endpoint                           | Description              |
|--------|------------------------------------|--------------------------|
| GET    | `/api/weather/current?city=London` | Current weather for city |
| GET    | `/api/weather/forecast?city=London`| 5-day forecast for city  |
| GET    | `/api/weather/health`              | Health check             |

## Configuration

Edit `src/main/resources/application.properties`:

```properties
api.key=your_openweathermap_api_key
api.base-url=https://api.openweathermap.org/data/2.5
server.port=8080
```

## Package Structure

```
com.weather/
├── controller/    WeatherController.java
├── service/       WeatherService.java
├── dto/           CurrentWeatherDTO, ForecastDTO, ErrorResponseDTO
├── config/        WeatherApiConfig, RestTemplateConfig, CorsConfig
├── exception/     CityNotFoundException, WeatherApiException, GlobalExceptionHandler
├── model/         WeatherApiResponse
├── util/          WeatherUtil
└──                WeatherApplication.java
```

## Build

```bash
./mvnw clean package
java -jar target/weather-backend-1.0.0.jar
```
