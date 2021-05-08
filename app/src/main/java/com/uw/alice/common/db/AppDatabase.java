package com.uw.alice.common.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.uw.alice.common.db.dao.CityDao;
import com.uw.alice.common.db.dao.UserDao;
import com.uw.alice.common.db.entity.City;
import com.uw.alice.common.db.entity.User;

@Database(entities = {User.class, City.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();

    public abstract CityDao cityDao();

}

