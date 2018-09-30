package com.weather.kidouchi.weatherapp.db.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Weather {

	public void setId(int id) {
		this.id = id;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public void setWeatherCondition(String weatherCondition) {
		this.weatherCondition = weatherCondition;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public void setTemperatureMin(double temperatureMin) {
		this.temperatureMin = temperatureMin;
	}

	public void setTemperatureMax(double temperatureMax) {
		this.temperatureMax = temperatureMax;
	}

	@PrimaryKey(autoGenerate = true)
    private int id;

	@ColumnInfo(name = "location_name")
    private String locationName;

    @ColumnInfo(name = "weather_condition")
    private String weatherCondition;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "temperature")
    private double temperature;

    @ColumnInfo(name = "temperature_min")
    private double temperatureMin;

    @ColumnInfo(name = "temperature_max")
    private double temperatureMax;

    public Weather(final String locationName,
                   final String weatherCondition,
                   final String description,
                   final double temperature,
                   final double temperatureMin,
                   final double temperatureMax) {
        this.locationName = locationName;
        this.weatherCondition = weatherCondition;
        this.description = description;
        this.temperature = temperature;
        this.temperatureMin = temperatureMin;
        this.temperatureMax = temperatureMax;
    }

	public String getLocationName() {
		return locationName;
	}

	public String getWeatherCondition() {
		return weatherCondition;
	}

	public String getDescription() {
		return description;
	}

	public double getTemperature() {
		return temperature;
	}

	public double getTemperatureMin() {
		return temperatureMin;
	}

	public double getTemperatureMax() {
		return temperatureMax;
	}

	public int getId() {
		return id;
	}
}
