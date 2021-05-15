package com.uw.alice.common.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


import com.uw.alice.common.db.entity.City;

import java.util.List;

import io.reactivex.Completable;

@Dao
public interface CityDao {
    @Query("SELECT * FROM City")
    List<City> getAll();

    @Query("SELECT * FROM City WHERE city_name LIKE :cityName")
    City query(String cityName);

    @Insert
    void insert(City... city);

    @Delete
    void delete(City city); //目前不起作用

    @Query("DELETE FROM City WHERE city_name LIKE :cityName")
    void delete(String cityName);  //绕过去，相当于查询删除

    @Update
    void update(City city); //目前不起作用

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertUsers(List<City> cityList);
}
