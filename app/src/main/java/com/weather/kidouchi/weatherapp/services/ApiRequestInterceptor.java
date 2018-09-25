package com.weather.kidouchi.weatherapp.services;

import com.weather.kidouchi.weatherapp.WeatherApplication;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ApiRequestInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        HttpUrl url = original.url().newBuilder()
                .addQueryParameter("APPID", WeatherApplication.API_KEY)
                .build();

        Request.Builder requestBuilder = original.newBuilder()
                .url(url);

        return chain.proceed(requestBuilder.build());
    }
}
