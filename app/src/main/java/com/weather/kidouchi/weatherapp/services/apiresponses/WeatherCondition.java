package com.weather.kidouchi.weatherapp.services.apiresponses;

import com.google.gson.annotations.SerializedName;

public class WeatherCondition {

    @SerializedName("id")
    int id;

    @SerializedName("main")
    String weatherCondition;

    @SerializedName("description")
    String description;

    @SerializedName("icon")
    String icon;

    public int getId() {
        return id;
    }

    public String getWeatherCondition() {
        return weatherCondition;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }
}
