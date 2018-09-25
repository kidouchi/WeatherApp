package com.weather.kidouchi.weatherapp.db;

import android.app.Application;
import android.os.Parcel;

import com.weather.kidouchi.weatherapp.db.models.WeatherLocation;
import com.weather.kidouchi.weatherapp.services.ApiService;
import com.weather.kidouchi.weatherapp.services.apiresponses.Weather;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class WeatherRepository {

    private final ApiService mApiService;
    private final AppDatabase mAppDatabase;

    public WeatherRepository(final Application application, final ApiService apiService) {
        mApiService = apiService;
        mAppDatabase = AppDatabase.getInstance(application);
    }

    public void getWeather(final String cityName) {
        mAppDatabase.weatherDao().findByName(cityName)
                .isEmpty()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(final Boolean isEmpty) throws Exception {
                        // If empty then cache in DB
                        if (isEmpty) {
                            mApiService.weather(cityName)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(Schedulers.io())
                                    .subscribe(new Consumer<Response<Weather>>() {
                                        @Override
                                        public void accept(Response<Weather> weatherResponse) throws Exception {
                                            final WeatherLocation weatherLocation = new WeatherLocation()
                                            updateDb(weatherResponse.body());
                                        }
                                    });
                        }
                    }
                });
    }

     public void updateDb(final WeatherLocation weatherLocation) {
        Observable.fromCallable(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    mAppDatabase.weatherDao().insert(weatherLocation);
                }
            }).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe();
     }

}
