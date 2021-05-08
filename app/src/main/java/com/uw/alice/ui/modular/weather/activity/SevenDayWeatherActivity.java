package com.uw.alice.ui.modular.weather.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.uw.alice.R;
import com.uw.alice.common.Constant;
import com.uw.alice.common.db.AppDatabase;
import com.uw.alice.common.db.entity.City;
import com.uw.alice.common.network.okhttp.OkHttpUtils;
import com.uw.alice.data.model.CityWeather;
import com.uw.alice.databinding.ActivitySevenDayWeatherBinding;
import com.uw.alice.ui.modular.weather.adapter.SevenDayWeatherAdapter;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SevenDayWeatherActivity extends AppCompatActivity {

    private static final String TAG = "SevenDayWeatherActivity";
    private ActivitySevenDayWeatherBinding viewBinding;
    private Context context;

    private CityWeather data;
    private String cityName;
    private AppDatabase db;
    private List<City> dataList = new ArrayList<>();

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
            cityName = getIntent().getStringExtra(Constant.ARG_CityName);
            viewBinding.tvCityName.setText(cityName);
            requestData(cityName);

            //注意：如果您的应用在单个进程中运行，在实例化 AppDatabase 对象时应遵循单例设计模式。
            //每个 RoomDatabase 实例的成本相当高，而您几乎不需要在单个进程中访问多个实例。
            //如果您的应用在多个进程中运行，请在数据库构建器调用中包含 enableMultiInstanceInvalidation()。
            //这样，如果您在每个进程中都有一个 AppDatabase 实例，可以在一个进程中使共享数据库文件失效，并且这种失效会自动传播到其他进程中 AppDatabase 的实例。
            db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "MyDatabase").allowMainThreadQueries().build();
        }

        viewBinding.llBack.setOnClickListener(v -> finish());
        viewBinding.ivAddCity.setOnClickListener(v -> {
            //将当前城市添加到本地数据库中
            db.cityDao().insert(new City(cityName));
            Toast.makeText(context, "添加成功", Toast.LENGTH_SHORT).show();
            checkIsInDB();

            dataList = db.cityDao().getAll();
            Log.d(TAG, "点位————数据库中的城市列表—————");
            int i = 0;
            for (City city: dataList){
                i++;
                Log.d(TAG, "点位：" + i + "、" + city.cityName);
            }
        });
        viewBinding.ivGoHome.setOnClickListener(v -> {
            Toast.makeText(context, "前往主页", Toast.LENGTH_SHORT).show();
            //Log.d(TAG, "点位——数据库中的城市列表：" + dataList);
        });

    }

    private void checkIsInDB() {
        City city = db.cityDao().queryCity(cityName);
        if (city != null){
            Log.d(TAG, "点位：数据库中已存在该城市：" + city.cityName);
            viewBinding.ivAddCity.setVisibility(View.GONE);
            viewBinding.ivGoHome.setVisibility(View.VISIBLE);
            viewBinding.tvTips.setText(R.string.go_hone);
            viewBinding.tvTips.setVisibility(View.VISIBLE);
        }else{
            viewBinding.ivAddCity.setVisibility(View.VISIBLE);
            viewBinding.ivGoHome.setVisibility(View.GONE);
            viewBinding.tvTips.setText(R.string.add_city);
            viewBinding.tvTips.setVisibility(View.VISIBLE);
        }
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

                checkIsInDB();
            } else {
                Toast.makeText(context, "msg: " + data.getResult().getMsg(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        db.close();
    }
}