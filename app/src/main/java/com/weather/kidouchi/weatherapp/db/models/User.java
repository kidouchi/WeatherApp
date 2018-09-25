package com.weather.kidouchi.weatherapp.db.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class User {

    @PrimaryKey
    private int uid;

    @ColumnInfo(name = "name")
    String name;

}
