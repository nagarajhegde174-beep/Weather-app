package com.example.weather_app.model;

import com.sun.tools.javac.Main;

import java.util.List;

public class WeatherResponse {
    private String name;

    private Sys sys;

    private List<Weather> weather;

    private Main main;

    private Wind wind;

    public static class Sys{

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        private String country;
    }

    public static class Weather{

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        private int id;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        private String description;



    }




}
