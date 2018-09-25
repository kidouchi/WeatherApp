package com.weather.kidouchi.weatherapp.services;

import com.weather.kidouchi.weatherapp.services.apiresponses.Weather;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("weather")
    Observable<Response<Weather>> weather(@Query("q") final String cityName);
}
