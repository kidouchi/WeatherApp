package com.weather.kidouchi.weatherapp.db.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class WeatherLocation {

    @PrimaryKey
    private int id;

    @ColumnInfo(name = "location_name")
    private String locationName;
}
