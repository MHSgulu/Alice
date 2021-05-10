package com.uw.alice.common.db;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.room.Room;

public class SingletonRoomDatabase {
    @SuppressLint("StaticFieldLeak")
    private static Context mContext;
    private final AppDatabase db;

    private SingletonRoomDatabase(Context context){
        //注意：如果您的应用在单个进程中运行，在实例化 AppDatabase 对象时应遵循单例设计模式。
        //每个 RoomDatabase 实例的成本相当高，而您几乎不需要在单个进程中访问多个实例。
        //如果您的应用在多个进程中运行，请在数据库构建器调用中包含 enableMultiInstanceInvalidation()。
        //这样，如果您在每个进程中都有一个 AppDatabase 实例，可以在一个进程中使共享数据库文件失效，并且这种失效会自动传播到其他进程中 AppDatabase 的实例。
        db = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "MyDatabase").allowMainThreadQueries().build();
    }

    private static class SingletonRoomDatabaseInner {
        @SuppressLint("StaticFieldLeak")
        public static SingletonRoomDatabase instance = new SingletonRoomDatabase(mContext);
    }

    public static SingletonRoomDatabase getInstance(Context context){
        mContext = context;
        return SingletonRoomDatabaseInner.instance;
    }


    public AppDatabase getDb(){
        return db;
    }

    public void close(){
        //如果数据库已经打开，则关闭它
        db.close();
    }


}
