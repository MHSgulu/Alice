package com.uw.alice.ui.modular.weather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.uw.alice.common.network.okhttp.OkHttpUtils;
import com.uw.alice.data.model.CityWeather;
import com.uw.alice.databinding.ActivityCityWeatherDetailsBinding;
import com.uw.alice.ui.modular.weather.adapter.WeatherDetailsAdapter;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CityWeatherDetailsActivity extends AppCompatActivity {

    private static final String TAG = "CityWeatherDetailsActiv";
    private ActivityCityWeatherDetailsBinding viewBinding;
    private Context context;
    private WeatherDetailsAdapter adapter;
    private List<CityWeather.ResultBeanX.ResultBean.DailyBean> dataList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_city_weather_details);
        viewBinding = ActivityCityWeatherDetailsBinding.inflate(getLayoutInflater());
        View view = viewBinding.getRoot();
        setContentView(view);

        context = CityWeatherDetailsActivity.this;

        requestWeatherData();
    }

    private void requestWeatherData() {
        Callback callback = new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
                Log.e(TAG, "onFailure: "+ e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (!response.isSuccessful()) {
                    Toast.makeText(context, "响应错误: " + response, Toast.LENGTH_SHORT).show();
                    throw new IOException("异常: " + response);
                }
                //OkHttpAssist.PrintRequestHeader(response.headers());
                //System.out.println(response.body().string()); //response.body().string() 只能调用一次
                CityWeather data = new Gson().fromJson(response.body().string(), CityWeather.class);
                //System.out.println("111: " + data.getResult().getResult().getCity());
                //当前回调已不在UI线程也就是Android主线程，需要手动切换
                runOnUiThread(data);
            }
        };
        OkHttpUtils.getInstance().requestCityWeather("临沂", callback);
    }

    private void runOnUiThread(CityWeather data) {

        /*
         * 在UI线程上运行指定的操作。 如果当前线程是UI线程，则立即执行该操作。
         * 如果当前线程不是UI线程，则将操作发布到UI线程的事件队列。
         *
         * @param action 在UI线程上运行的操作
         */
        CityWeatherDetailsActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (data.getResult().getStatus()==0){
                    viewBinding.tvAddress.setText(data.getResult().getResult().getCity());
                    viewBinding.tvWeather.setText(data.getResult().getResult().getWeather());
                    viewBinding.tvTemperature.setText(data.getResult().getResult().getTemp());
                    viewBinding.tvAirQuality.setText(data.getResult().getResult().getAqi().getQuality());

                    viewBinding.recycleWeather.setLayoutManager(new LinearLayoutManager(context));
                    dataList = data.getResult().getResult().getDaily();
                    adapter = new WeatherDetailsAdapter(dataList);
                    viewBinding.recycleWeather.setAdapter(adapter);

                }else{
                    Toast.makeText(context, "msg: " + data.getResult().getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}