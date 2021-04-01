package com.uw.alice.ui.modular.weather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.uw.alice.R;
import com.uw.alice.common.network.retrofit.SingletonRetrofit;
import com.uw.alice.data.model.Weather;
import com.uw.alice.databinding.ActivityCityWeatherDetailsBinding;
import com.uw.alice.ui.modular.weather.adapter.WeatherDetailsListAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class CityWeatherDetailsActivity extends AppCompatActivity {

    private static final String TAG = "CityWeatherDetailsActiv";
    private ActivityCityWeatherDetailsBinding viewBinding;
    private Context context;
    private WeatherDetailsListAdapter adapter;
    private List<String> dataList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_city_weather_details);
        viewBinding = ActivityCityWeatherDetailsBinding.inflate(getLayoutInflater());
        View view = viewBinding.getRoot();
        setContentView(view);

        context = CityWeatherDetailsActivity.this;

        viewBinding.recycleWeather.setLayoutManager(new LinearLayoutManager(context));

        dataList.add("今天 阴转小雨");
        dataList.add("今天 阴转小雨");
        dataList.add("今天 阴转小雨");

        adapter = new WeatherDetailsListAdapter(dataList);
        viewBinding.recycleWeather.setAdapter(adapter);

        requestData();

    }

    private void requestData() {

        Observer<Weather> observer = new Observer<Weather>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
            }

            @Override
            public void onNext(@NonNull Weather t) {

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e(TAG, "onError: " + e);
                Toast.makeText(context, "捕获异常: " + e, Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onComplete() {
                Log.d(TAG, "observer onComplete: 执行了");
            }
        };
        SingletonRetrofit.getInstance().requestWeatherForecast(observer,"临沂");

    }




}