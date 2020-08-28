package com.uw.alice;

import android.app.Application;

/**
 * 用于维护全局应用程序状态的基类。
 */
public class MyApplication extends Application {

    //在此处以下三种上下文是一致的内容   com.uw.alice.MainActivity@12aefd4
//Log.d(TAG, "Context1:"+mContext+"Context2:"+getActivity()+"Context3:"+getContext());



    @Override
    public void onCreate() {
        super.onCreate();
    }




}
