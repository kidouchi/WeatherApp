package com.weather.kidouchi.weatherapp.services.apiresponses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherResponse {

    @SerializedName("weather")
    List<WeatherCondition> weatherConditions;

    @SerializedName("name")
    String name;

    @SerializedName("main")
    Temperature temperature;

    public List<WeatherCondition> getWeatherConditions() {
        return weatherConditions;
    }

    public String getName() {
        return name;
    }

    public Temperature getTemperature() {
        return temperature;
    }
}
