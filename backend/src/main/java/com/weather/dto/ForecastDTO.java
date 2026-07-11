package com.weather.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForecastDTO {

    private String cod;
    private Integer message;
    private Integer cnt;
    private List<ForecastItem> list;
    private City city;

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class ForecastItem {

        private Long dt;
        private Main main;
        private List<Weather> weather;
        private Clouds clouds;
        private Wind wind;
        private Integer visibility;
        private Double pop;
        private Sys sys;
        @JsonProperty("dt_txt") private String dtTxt;

        @Data @NoArgsConstructor @AllArgsConstructor
        public static class Main {
            private Double temp;
            @JsonProperty("feels_like") private Double feelsLike;
            @JsonProperty("temp_min")   private Double tempMin;
            @JsonProperty("temp_max")   private Double tempMax;
            private Integer pressure;
            @JsonProperty("sea_level")  private Integer seaLevel;
            @JsonProperty("grnd_level") private Integer grndLevel;
            private Integer humidity;
            @JsonProperty("temp_kf")    private Double tempKf;
        }

        @Data @NoArgsConstructor @AllArgsConstructor
        public static class Weather {
            private Integer id;
            private String main;
            private String description;
            private String icon;
        }

        @Data @NoArgsConstructor @AllArgsConstructor
        public static class Clouds { private Integer all; }

        @Data @NoArgsConstructor @AllArgsConstructor
        public static class Wind {
            private Double speed;
            private Integer deg;
            private Double gust;
        }

        @Data @NoArgsConstructor @AllArgsConstructor
        public static class Sys { private String pod; }
    }

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class City {
        private Integer id;
        private String name;
        private Coord coord;
        private String country;
        private Integer population;
        private Integer timezone;
        private Long sunrise;
        private Long sunset;

        @Data @NoArgsConstructor @AllArgsConstructor
        public static class Coord {
            private Double lat;
            private Double lon;
        }
    }
}
