package com.uw.alice.ui.modular.weather.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.uw.alice.R;
import com.uw.alice.common.Constant;
import com.uw.alice.common.db.AppDatabase;
import com.uw.alice.common.db.SingletonRoomDatabase;
import com.uw.alice.common.db.entity.City;
import com.uw.alice.common.network.okhttp.OkHttpUtils;
import com.uw.alice.data.model.CityWeather;
import com.uw.alice.databinding.ActivityCityWeatherDetailsBinding;
import com.uw.alice.entity.IPLocationEntity;
import com.uw.alice.ui.modular.weather.adapter.RealTimeWeatherAdapter;
import com.uw.alice.ui.modular.weather.adapter.WeatherDetailsAdapter;
import com.uw.alice.ui.modular.weather.adapter.WeatherTipsAdapter;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CityWeatherDetailsActivity extends AppCompatActivity {

    private static final String TAG = "CityWeatherDetailsActiv";
    private ActivityCityWeatherDetailsBinding viewBinding;
    private Context context;

    private CityWeather data;
    private IPLocationEntity ipLocationEntity;

    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_city_weather_details);
        viewBinding = ActivityCityWeatherDetailsBinding.inflate(getLayoutInflater());
        View view = viewBinding.getRoot();
        setContentView(view);

        context = viewBinding.getRoot().getContext();
        db = SingletonRoomDatabase.getInstance(getApplicationContext()).getDb();
        initUIView();
        initOnClickListener();

        String cityName = getIntent().getStringExtra(Constant.ARG_CityName);
        //Log.d(TAG, "点位：cityName: " + cityName);
        if (cityName == null){
            getLocation();
        }else{
            requestWeatherData(getIntent().getStringExtra(Constant.ARG_CityName));
        }
    }

    private void getLocation() {
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
                ipLocationEntity = new Gson().fromJson(response.body().string(),IPLocationEntity.class);
                Log.d(TAG, "点位： 当前IP地址所在城市: " + ipLocationEntity.getLocation().getCity());
                requestWeatherData(ipLocationEntity.getLocation().getCity());
                //如果数据库没有数据，添加当前地址进去，不可删除
                if (db.cityDao().getAll().size() > 0){
                    Log.d(TAG, "点位： 当前数据库中存在城市数据");
                }else{
                    Log.d(TAG, "点位： 当前数据库中不存在城市数据，添加一条所在IP定位城市数据");
                    db.cityDao().insert(new City(ipLocationEntity.getLocation().getCity()));
                }
            }
        };
        OkHttpUtils.getInstance().requestIpLocation(callback);

    }

    private void initUIView() {
        //viewBinding.rootView.setBackgroundColor(getColor(R.color.weather_qing));
        /*
         * 将状态栏的颜色设置为{@code color}.
         *
         * 为了使此效果生效，该窗口必须使用{@link android.view.WindowManager.LayoutParams#FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS}
         * 而且不得设置 {@link android.view.WindowManager.LayoutParams#FLAG_TRANSLUCENT_STATUS}
         *
         * 如果颜色是透明的，考虑设置{@link android.view.View#SYSTEM_UI_FLAG_LAYOUT_STABLE}和{@link android.view.View#SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN}.
         * <p>
         * The transitionName for the view background will be "android:status:background".
         * 视图背景的transitionName将为“ android：status：background”。
         * </p>
         */
        getWindow().setStatusBarColor(getColor(R.color.white)); //设置系统状态栏背景颜色

        /*
         * 将导航栏的颜色设置为{@param color}.
         *
         * 为了使此效果生效，该窗口必须使用以下命令绘制系统栏背景{@link android.view.WindowManager.LayoutParams#FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS}
         * 而且不得设置 {@link android.view.WindowManager.LayoutParams#FLAG_TRANSLUCENT_NAVIGATION}.
         *
         * 如果颜色是透明，请考虑设置{@link android.view.View#SYSTEM_UI_FLAG_LAYOUT_STABLE} 和 {@link android.view.View#SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION}.
         * <p>
         * The transitionName for the view background will be "android:navigation:background".
         * 视图背景的transitionName将为“ android：navigation：background”。
         * </p>
         * @attr ref android.R.styleable#Window_navigationBarColor
         */
        //getWindow().setNavigationBarColor(getColor(R.color.weather_qing)); //设置底部系统导航栏状态栏背景颜色
    }

    private void initOnClickListener() {
        viewBinding.ivAdd.setOnClickListener(v -> {
            Intent intent = new Intent(context, CityManagementActivity.class);
            startActivity(intent);
        });
    }

    private void requestWeatherData(String cityName) {
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
                //OkHttpAssist.PrintRequestHeader(response.headers());
                //System.out.println(response.body().string()); //response.body().string() 只能调用一次
                data = new Gson().fromJson(Objects.requireNonNull(response.body()).string(), CityWeather.class);
                //System.out.println("111: " + data.getResult().getResult().getCity());
                //当前回调已不在UI线程也就是Android主线程，需要手动切换
                runOnUiThread();
            }
        };
        OkHttpUtils.getInstance().requestCityWeather(cityName, callback);
    }

    private void runOnUiThread() {

        /*
         * 在UI线程上运行指定的操作。 如果当前线程是UI线程，则立即执行该操作。
         * 如果当前线程不是UI线程，则将操作发布到UI线程的事件队列。
         *
         * @param action 在UI线程上运行的操作
         */
        CityWeatherDetailsActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (data.getResult().getStatus() == 0) {
                    String weather = data.getResult().getResult().getWeather();
                    if (weather.contains("晴")) {
                        viewBinding.rootView.setBackgroundColor(getColor(R.color.weather_qing));
                        getWindow().setStatusBarColor(getColor(R.color.weather_qing));
                        getWindow().setNavigationBarColor(getColor(R.color.weather_qing));
                    } else if (weather.contains("多云")) {
                        viewBinding.rootView.setBackgroundColor(getColor(R.color.weather_duoyun));
                        getWindow().setStatusBarColor(getColor(R.color.weather_duoyun));
                        getWindow().setNavigationBarColor(getColor(R.color.weather_duoyun));
                    } else if (weather.contains("阴")) {
                        viewBinding.rootView.setBackgroundColor(getColor(R.color.weather_yin));
                        getWindow().setStatusBarColor(getColor(R.color.weather_yin));
                        getWindow().setNavigationBarColor(getColor(R.color.weather_yin));
                    } else if (weather.contains("雨")) {
                        viewBinding.rootView.setBackgroundColor(getColor(R.color.weather_rain));
                        getWindow().setStatusBarColor(getColor(R.color.weather_rain));
                        getWindow().setNavigationBarColor(getColor(R.color.weather_rain));
                    } else if (weather.contains("浮尘")) {
                        viewBinding.rootView.setBackgroundColor(getColor(R.color.weather_fuchen));
                        getWindow().setStatusBarColor(getColor(R.color.weather_fuchen));
                        getWindow().setNavigationBarColor(getColor(R.color.weather_fuchen));
                    } else {
                        viewBinding.rootView.setBackgroundColor(getColor(R.color.weather_yin));
                        getWindow().setStatusBarColor(getColor(R.color.weather_yin));
                        getWindow().setNavigationBarColor(getColor(R.color.weather_yin));
                    }

                    viewBinding.tvAddress.setText(data.getResult().getResult().getCity());
                    viewBinding.tvWeather.setText(data.getResult().getResult().getWeather());
                    viewBinding.tvTemperature.setText(data.getResult().getResult().getTemp());
                    viewBinding.tvAirQuality.setText(data.getResult().getResult().getAqi().getQuality());

                    viewBinding.tvTimeSunrise.setText(String.format("日出 %s", data.getResult().getResult().getDaily().get(0).getSunrise()));
                    viewBinding.tvTimeSunset.setText(String.format("日落 %s", data.getResult().getResult().getDaily().get(0).getSunset()));

                    viewBinding.tvWindForce.setText(data.getResult().getResult().getWindpower());
                    viewBinding.tvWindDirection.setText(data.getResult().getResult().getWinddirect());
                    viewBinding.tvHumidity.setText(String.format("%s%%", data.getResult().getResult().getHumidity()));
                    viewBinding.tvSomatosensory.setText(String.format("%s °", data.getResult().getResult().getTemp()));
                    viewBinding.tvAirPressure.setText(String.format("%s", data.getResult().getResult().getPressure()));

                    //固定长度为7的一周天气列表
                    //viewBinding.recycleWeather.setLayoutManager(new LinearLayoutManager(context));
                    viewBinding.recycleWeather.setAdapter(new WeatherDetailsAdapter(data.getResult().getResult().getDaily()));

                    //固定长度为24的实时天气列表
                    //viewBinding.recycleTimeWeather.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
                    viewBinding.recycleTimeWeather.setAdapter(new RealTimeWeatherAdapter(data.getResult().getResult().getHourly()));

                    //固定长度为6的天气指数列表
                    //viewBinding.recycleTips.setLayoutManager(new GridLayoutManager(context, 3));
                    //adapter3 = new WeatherTipsAdapter(data.getResult().getResult().getIndex());
                    viewBinding.recycleTips.setAdapter(new WeatherTipsAdapter(data.getResult().getResult().getIndex()));


                } else {
                    Toast.makeText(context, "msg: " + data.getResult().getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    /*@Override
    public void finish() {
        super.finish();
        if (db != null){
            db.close();
        }
    }*/
}