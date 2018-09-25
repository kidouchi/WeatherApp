package com.weather.kidouchi.weatherapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.weather.kidouchi.weatherapp.R;
import com.weather.kidouchi.weatherapp.WeatherApplication;
import com.weather.kidouchi.weatherapp.services.apiresponses.Weather;
import com.weather.kidouchi.weatherapp.services.apiresponses.WeatherApiReponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.weather_button)
    Button mWeatherButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.weather_button)
    public void getWeather() {
        WeatherApplication application = (WeatherApplication) getApplicationContext();

        Observable<Response<Weather>> weatherResponse =
                application.getApiService().weather("New York");

        weatherResponse
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Response<Weather>>() {
                               @Override
                               public void accept(Response<Weather> weatherApiReponseResponse) throws Exception {
                                   Toast.makeText(MainActivity.this,
                                           weatherApiReponseResponse.body().getWeatherConditions().get(0).getMain(),
                                           Toast.LENGTH_LONG).show();
                               }
                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Log.d("TEST", throwable + "");

                                Toast.makeText(MainActivity.this, "something borked", Toast.LENGTH_LONG).show();
                            }
                        });
    }
}
