package com.uw.alice.common.network.okhttp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.Headers;

public class OkHttpAssist {

    //打印请求头
    public static void PrintRequestHeader(Headers responseHeaders){
        for (int i = 0, size = responseHeaders.size(); i < size; i++) {
            System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
        }
    }

    public static Gson createGson() {
        //通过构造函数来获取
        Gson gson1 = new Gson();
        //通过 GsonBuilder 来获取，可以进行多项特殊配置
        Gson gson2 = new GsonBuilder().create();
        return gson1;
    }

}
