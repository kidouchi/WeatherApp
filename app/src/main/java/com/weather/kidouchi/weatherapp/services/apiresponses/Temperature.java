package com.weather.kidouchi.weatherapp.services.apiresponses;

import com.google.gson.annotations.SerializedName;

public class Temperature {

	@SerializedName("temp")
	double temperature;

	@SerializedName("temp_min")
	double temperatureMin;

	@SerializedName("temp_max")
	double temperatureMax;

	public double getTemperature() {
		return temperature;
	}

	public double getTemperatureMin() {
		return temperatureMin;
	}

	public double getTemperatureMax() {
		return temperatureMax;
	}
}
