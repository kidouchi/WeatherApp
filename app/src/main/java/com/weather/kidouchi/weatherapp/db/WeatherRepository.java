package com.weather.kidouchi.weatherapp.db;

import android.app.Application;

import com.weather.kidouchi.weatherapp.services.ApiService;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class WeatherRepository {

    private final ApiService mApiService;
    private final AppDatabase mAppDatabase;

    public WeatherRepository(final Application application, final ApiService apiService) {
        mApiService = apiService;
        mAppDatabase = AppDatabase.getInstance(application);
    }

    public void getWeather(final String cityName) {
        if (mAppDatabase.weatherDao().findByName(cityName)) {

        }
    }

     public Observable updateDb(final String cityName) {
        return Observable.fromCallable(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    return null;
                }
            }).subscribeOn(Schedulers.io()).observeOn(Schedulers.io());
     }

}
