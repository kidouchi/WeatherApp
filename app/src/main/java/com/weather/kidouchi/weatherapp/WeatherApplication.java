package com.weather.kidouchi.weatherapp;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.weather.kidouchi.weatherapp.services.ApiRequestInterceptor;
import com.weather.kidouchi.weatherapp.services.ApiService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherApplication extends Application {
    private Retrofit mRetrofit;
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/";
    public static final String API_KEY = "9f34ca091f6096ff17bb825b21eb2791";

    @Override
    public void onCreate() {
        super.onCreate();

        Stetho.initializeWithDefaults(this);

        mRetrofit = new Retrofit.Builder()
                .client(createOkHttpClient())
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public ApiService getApiService() {
        return mRetrofit.create(ApiService.class);
    }

    private OkHttpClient createOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new ApiRequestInterceptor())
                .build();
    }
}
