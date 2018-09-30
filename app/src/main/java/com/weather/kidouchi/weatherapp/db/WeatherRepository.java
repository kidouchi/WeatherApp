package com.weather.kidouchi.weatherapp.db;

import android.app.Application;

import com.weather.kidouchi.weatherapp.db.models.Weather;
import com.weather.kidouchi.weatherapp.services.ApiService;
import com.weather.kidouchi.weatherapp.services.apiresponses.Temperature;
import com.weather.kidouchi.weatherapp.services.apiresponses.WeatherCondition;
import com.weather.kidouchi.weatherapp.services.apiresponses.WeatherResponse;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class WeatherRepository {

    private final ApiService mApiService;
    private final AppDatabase mAppDatabase;

    public WeatherRepository(final Application application, final ApiService apiService) {
        mApiService = apiService;
        mAppDatabase = AppDatabase.getInstance(application);
    }

    public Observable<Weather> getWeather(final String cityName) {
        return Observable.concat(getWeatherFromDb(cityName), getWeatherFromApi(cityName));
    }

    private Observable<Weather> getWeatherFromApi(final String cityName) {
        return mApiService.weather(cityName)
                .subscribeOn(Schedulers.io())
                .map(weatherResponseResponse -> {
                    final WeatherResponse weatherResponse = weatherResponseResponse.body();
                    final Temperature temperature = weatherResponse.getTemperature();
                    final WeatherCondition weatherCondition = weatherResponse.getWeatherConditions().get(0);
                    return new Weather(weatherResponse.getName(),
                            weatherCondition.getWeatherCondition(),
                            weatherCondition.getDescription(),
                            temperature.getTemperature(),
                            temperature.getTemperatureMin(),
                            temperature.getTemperatureMax());
                })
                .doOnNext(weather -> updateDb(weather));
    }


     private Observable<Weather> getWeatherFromDb(final String cityName) {
        return mAppDatabase.weatherDao().findByName(cityName)
                .toObservable();
     }

    private void updateDb(final Weather weather) {
        Completable.fromAction(() -> mAppDatabase.weatherDao().insert(weather))
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe();
    }

}
