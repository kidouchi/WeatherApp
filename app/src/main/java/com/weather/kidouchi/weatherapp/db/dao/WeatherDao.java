package com.weather.kidouchi.weatherapp.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.weather.kidouchi.weatherapp.db.models.Weather;

import java.util.List;

import io.reactivex.Maybe;

@Dao
public interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(final Weather weather);

    @Delete
    void delete(final Weather weather);

    @Delete
    void clear(final Weather... weathers);

    @Query("SELECT * from Weather")
    List<Weather> getAllLocations();

    @Query("SELECT * FROM Weather WHERE LOWER(location_name) LIKE LOWER(:name) LIMIT 1")
    Maybe<Weather> findByName(final String name);
}
