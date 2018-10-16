package com.weather.kidouchi.weatherapp.viewmodel;

import com.weather.kidouchi.weatherapp.db.WeatherRepository;
import com.weather.kidouchi.weatherapp.db.models.Weather;

import io.reactivex.Observable;

public class WeatherViewModel {

    final WeatherRepository weatherRepository;

    public WeatherViewModel(final WeatherRepository repository) {
        weatherRepository = repository;
    }


    public Observable<Weather> getWeather(final String query) {
        return weatherRepository.getWeather(query);
    }
}
