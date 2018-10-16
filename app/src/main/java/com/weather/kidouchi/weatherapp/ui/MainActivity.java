package com.weather.kidouchi.weatherapp.ui;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.weather.kidouchi.weatherapp.R;
import com.weather.kidouchi.weatherapp.WeatherApplication;
import com.weather.kidouchi.weatherapp.db.models.Weather;
import com.weather.kidouchi.weatherapp.utils.AirplaneModeBroadcastReceiver;
import com.weather.kidouchi.weatherapp.utils.NetworkUtils;
import com.weather.kidouchi.weatherapp.viewmodel.WeatherViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
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

    private CompositeDisposable compositeDisposable;
    private BroadcastReceiver airplaneBroadcastReceiver;
    private WeatherViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        compositeDisposable = new CompositeDisposable();

        NetworkUtils.checkNetworkRequest(this,
                () -> Toast.makeText(this, "On Available", Toast.LENGTH_LONG).show(),
                () -> Toast.makeText(this, "On network lost", Toast.LENGTH_LONG).show());

        final IntentFilter intentFilter = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        airplaneBroadcastReceiver = new AirplaneModeBroadcastReceiver();
        LocalBroadcastManager.getInstance(this).registerReceiver(airplaneBroadcastReceiver, intentFilter);

        final WeatherApplication application = (WeatherApplication) getApplicationContext();
        viewModel = new WeatherViewModel(application.getWeatherRepository());
    }

    @Override
    protected void onResume() {
        super.onResume();
        final IntentFilter intentFilter = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        LocalBroadcastManager.getInstance(this).registerReceiver(airplaneBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }

        LocalBroadcastManager.getInstance(this).unregisterReceiver(airplaneBroadcastReceiver);
    }

    @OnClick(R.id.weather_button)
    public void getWeather(final View view) {
        final String query = mCityNameEditText != null ? mCityNameEditText.getText().toString() : "";
        compositeDisposable.add(viewModel.getWeather(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(weather -> setupWeather(weather),
                        throwable -> {
                            if (!NetworkUtils.hasInternetConnenction(this)) {
                                showSnackbarOnLostConnect(view);
                            } else {
                                mLocationNameTextView.setText(R.string.not_found);
                            }
                        })
        );
    }

    private void setupWeather(final Weather weather) {
        mLocationNameTextView.setText(weather.getLocationName());
        mDescriptionTextView.setText(weather.getDescription());
        mTemperatureTextView.setText(Double.toString(weather.getTemperature()));
    }

    private void showSnackbarOnLostConnect(final View view) {
        Snackbar.make(view, "No Network Connnection", Snackbar.LENGTH_LONG)
                .setAction("Retry", v -> getWeather(view))
                .show();
    }
}
