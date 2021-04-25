package com.uw.alice.ui.modular.weather.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.uw.alice.common.Constant;
import com.uw.alice.common.network.okhttp.OkHttpUtils;
import com.uw.alice.data.model.CityWeather;
import com.uw.alice.databinding.ActivitySevenDayWeatherBinding;
import com.uw.alice.ui.modular.weather.adapter.SevenDayWeatherAdapter;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SevenDayWeatherActivity extends AppCompatActivity {

    private static final String TAG = "SevenDayWeatherActivity";
    private ActivitySevenDayWeatherBinding viewBinding;
    private Context context;

    private CityWeather data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_seven_day_weather);
        viewBinding = ActivitySevenDayWeatherBinding.inflate(getLayoutInflater());
        View view = viewBinding.getRoot();
        setContentView(view);
        context = view.getContext();
        //Log.d(TAG, "数据点位：context实例1: " + context);
        //Log.d(TAG, "数据点位：context实例2: " + SevenDayWeatherActivity.this);

        if (getIntent() != null){
            String cityName = getIntent().getStringExtra(Constant.ARG_CityName);
            viewBinding.tvCityName.setText(cityName);
            requestData(cityName);
        }

        viewBinding.llBack.setOnClickListener(v -> finish());

    }

    private void requestData(String cityName) {
        Callback callback = new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
                Log.e(TAG, "onFailure: " + e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (!response.isSuccessful()) {
                    Toast.makeText(context, "响应错误: " + response, Toast.LENGTH_SHORT).show();
                    throw new IOException("异常: " + response);
                }
                data = new Gson().fromJson(Objects.requireNonNull(response.body()).string(), CityWeather.class);
                runOnUiThread();
            }
        };
        OkHttpUtils.getInstance().requestCityWeather(cityName, callback);
    }


    private void runOnUiThread() {
        runOnUiThread(() -> {
            if (data.getResult().getStatus() == 0) {
                viewBinding.recycleWeatherList.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
                viewBinding.recycleWeatherList.setAdapter(new SevenDayWeatherAdapter(data.getResult().getResult().getDaily()));
            } else {
                Toast.makeText(context, "msg: " + data.getResult().getMsg(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}