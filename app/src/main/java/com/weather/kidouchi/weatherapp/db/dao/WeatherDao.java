package com.weather.kidouchi.weatherapp.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.weather.kidouchi.weatherapp.db.models.WeatherLocation;

import java.util.List;

import io.reactivex.Maybe;

@Dao
public interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(final WeatherLocation weatherLocation);

    @Delete
    void delete(final WeatherLocation weatherLocation);

    @Delete
    void clear(final WeatherLocation ...weatherLocations);

    @Query("SELECT * from weatherlocation")
    List<WeatherLocation> getAllLocations();

    @Query("SELECT * FROM weatherlocation WHERE location_name LIKE :name LIMIT 1")
    Maybe<WeatherLocation> findByName(final String name);
}
