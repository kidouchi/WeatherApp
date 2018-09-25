package com.weather.kidouchi.weatherapp.services.apiresponses;

import com.google.gson.annotations.SerializedName;

public class WeatherApiReponse {

    @SerializedName("weather")
    Weather weather;

    public Weather getWeather() {
        return weather;
    }
}
