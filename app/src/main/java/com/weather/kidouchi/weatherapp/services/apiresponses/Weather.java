package com.weather.kidouchi.weatherapp.services.apiresponses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Weather {

    @SerializedName("weather")
    List<WeatherCondition> weatherConditions;

    public List<WeatherCondition> getWeatherConditions() {
        return weatherConditions;
    }
}
