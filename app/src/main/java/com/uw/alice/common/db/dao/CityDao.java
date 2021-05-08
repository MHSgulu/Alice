package com.uw.alice.common.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import com.uw.alice.common.db.entity.City;

import java.util.List;

import io.reactivex.Completable;

@Dao
public interface CityDao {
    @Query("SELECT * FROM City")
    List<City> getAll();

    @Query("SELECT * FROM City WHERE city_name LIKE :cityName")
    City queryCity(String cityName);

    @Insert
    void insert(City... city);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertUsers(List<City> cityList);

    @Delete
    void delete(City city);
}
