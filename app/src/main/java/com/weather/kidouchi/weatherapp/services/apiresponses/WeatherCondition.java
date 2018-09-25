package com.weather.kidouchi.weatherapp.services.apiresponses;

import com.google.gson.annotations.SerializedName;

public class WeatherCondition {

    @SerializedName("id")
    int id;

    @SerializedName("main")
    String main;

    @SerializedName("description")
    String description;

    @SerializedName("icon")
    String icon;

    public int getId() {
        return id;
    }

    public String getMain() {
        return main;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }
}
