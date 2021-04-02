package com.uw.alice.common.network.okhttp;


import com.uw.alice.common.Constant;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class OkHttpUtils {
    private final OkHttpClient client;
    private Request request;

    private OkHttpUtils() {
        client = new OkHttpClient();
    }

    private static class OkHttpUtilsInner {
        public static OkHttpUtils instance = new OkHttpUtils();
    }

    public static OkHttpUtils getInstance() {
        return OkHttpUtilsInner.instance;
    }


    public void requestCityWeather(String cityName, Callback callback) {
        request = new Request.Builder()
                .url(Constant.JDAPI_URL + "jisuapi/weather?city=" + cityName +  "&appkey=" + Constant.JDAPI_KEY)
                .build();

        client.newCall(request).enqueue(callback);
    }

}
