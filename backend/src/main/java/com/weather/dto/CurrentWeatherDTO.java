package com.weather.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrentWeatherDTO {

    private String name;

    @JsonProperty("coord")
    private Coord coordinates;

    private List<Weather> weather;
    private Main main;
    private Wind wind;
    private Clouds clouds;
    private Sys sys;
    private Integer visibility;
    private Long dt;
    private Integer timezone;

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class Coord {
        private Double lon;
        private Double lat;
    }

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class Weather {
        private Integer id;
        private String main;
        private String description;
        private String icon;
    }

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class Main {
        private Double temp;
        @JsonProperty("feels_like") private Double feelsLike;
        @JsonProperty("temp_min")   private Double tempMin;
        @JsonProperty("temp_max")   private Double tempMax;
        private Integer pressure;
        private Integer humidity;
        @JsonProperty("sea_level")  private Integer seaLevel;
        @JsonProperty("grnd_level") private Integer grndLevel;
    }

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class Wind {
        private Double speed;
        private Integer deg;
        private Double gust;
    }

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class Clouds {
        private Integer all;
    }

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class Sys {
        private Integer type;
        private Integer id;
        private String country;
        private Long sunrise;
        private Long sunset;
    }
}
