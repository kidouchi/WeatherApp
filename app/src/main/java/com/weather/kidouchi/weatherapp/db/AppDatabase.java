package com.weather.kidouchi.weatherapp.db;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.weather.kidouchi.weatherapp.db.dao.UserDao;
import com.weather.kidouchi.weatherapp.db.dao.WeatherDao;

public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract WeatherDao weatherDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "app_database")
                            .build();
                }
            }
        }

        return INSTANCE;
    }
}
