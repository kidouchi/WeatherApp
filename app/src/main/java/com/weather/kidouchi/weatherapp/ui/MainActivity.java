package com.weather.kidouchi.weatherapp.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkRequest;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.weather.kidouchi.weatherapp.R;
import com.weather.kidouchi.weatherapp.WeatherApplication;
import com.weather.kidouchi.weatherapp.db.WeatherRepository;
import com.weather.kidouchi.weatherapp.db.models.Weather;
import com.weather.kidouchi.weatherapp.utils.NetworkUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.weather_button)
    Button mWeatherButton;

    @BindView(R.id.city_name_edit_text)
    EditText mCityNameEditText;

    @BindView(R.id.temperature_text_view)
    TextView mTemperatureTextView;

    @BindView(R.id.location_name_text_view)
    TextView mLocationNameTextView;

    @BindView(R.id.weather_description_text_view)
    TextView mDescriptionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
	    LayoutInflater.from(this).in
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        final BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

            }
        };

        this.registerReceiver(receiver,);
    }

    @OnClick(R.id.weather_button)
    public void getWeather() {
        final WeatherApplication application = (WeatherApplication) getApplicationContext();

        final WeatherRepository weatherRepository = new WeatherRepository(application, application.getApiService());

        weatherRepository.getWeather(mCityNameEditText.getText().toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(weather -> setupWeatherView(weather),
                        throwable -> {
                	        if (!NetworkUtils.hasInternetConnenction(this)) {
		                        Snackbar.make(this.view, "No Network Connnection", Snackbar.LENGTH_LONG)
		                            .setAction("Retry", v -> getWeather())
		                            .show();
	                        } else {

	                        }
                        });
    }

    private void setupWeatherView(Weather weather) {
        mTemperatureTextView.setText(Double.toString(weather.getTemperature()));
        mLocationNameTextView.setText(weather.getLocationName());
        mDescriptionTextView.setText(weather.getDescription());
    }
}
