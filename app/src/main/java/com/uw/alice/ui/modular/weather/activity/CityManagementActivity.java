package com.uw.alice.ui.modular.weather.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.uw.alice.common.Constant;
import com.uw.alice.common.db.AppDatabase;
import com.uw.alice.common.db.SingletonRoomDatabase;
import com.uw.alice.common.db.entity.City;
import com.uw.alice.databinding.ActivityCityManagementBinding;
import com.uw.alice.interfaces.OnItemClickListener;
import com.uw.alice.interfaces.OnItemLongClickListener;
import com.uw.alice.ui.modular.weather.adapter.CityManagementAdapter;

import java.util.ArrayList;
import java.util.List;

public class CityManagementActivity extends AppCompatActivity {

    private static final String TAG = "CityManagementActivity";
    private ActivityCityManagementBinding viewBinding;
    private Context context;

    private CityManagementAdapter adapter;
    private List<City> dataList = new ArrayList<>();

    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_city_management);
        viewBinding = ActivityCityManagementBinding.inflate(getLayoutInflater());
        setContentView(viewBinding.getRoot());

        context = CityManagementActivity.this;

        viewBinding.llBack.setOnClickListener(v -> finish());
        viewBinding.llSearchBox.setOnClickListener(v -> {
            Intent intent = new Intent(context, SearchCityActivity.class);
            startActivityForResult(intent, 520);
        });

        db = SingletonRoomDatabase.getInstance(getApplicationContext()).getDb();
        initData();
    }

    private void initData() {
        dataList = db.cityDao().getAll();
        Log.d(TAG, "点位： ————查看刚从数据库取出的的dataList————");
        for (City city: dataList){
            Log.d(TAG, "点位1：city：" + city.cityName);
        }
        if (dataList.size() > 0) {
            //Log.d(TAG, "点位： dataList: " + dataList.toString());
            adapter = new CityManagementAdapter(dataList, CityManagementActivity.this);
            viewBinding.recycleCityList.setAdapter(adapter);

            adapter.setOnItemClickListener((view, position) -> {
                //Toast.makeText(context, "短按事件", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, CityWeatherDetailsActivity.class);
                intent.putExtra(Constant.ARG_CityName, dataList.get(position).cityName);
                startActivity(intent);
            });

            adapter.setOnItemLongClickListener((view, position) -> {
                Log.d(TAG, "点位： position: " + position);
                if (position == 0) {
                    Toast.makeText(context, "该城市为定位城市,无法删除", Toast.LENGTH_SHORT).show();
                    Snackbar.make(viewBinding.getRoot(), "当前为默认城市", Snackbar.LENGTH_LONG).show();
                } else {
                    MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context);
                    builder.setTitle("提示");
                    builder.setMessage("从城市管理中删除该城市？");
                    builder.setPositiveButton("确定", (dialog, which) -> {
                        builder.create().dismiss();
                        City cityData = new City(dataList.get(position).cityName);
                        Log.d(TAG, "点位： 要删除的城市： " + cityData.cityName);
                        //从数据库中删除
                        db.cityDao().deleteQuery(cityData.cityName);
                        //再从数据列表中删除，UI可见
                        dataList.remove(position);
                        adapter.notifyDataSetChanged();
                        Log.d(TAG, "点位： ————查看删除后的本地数据 dataList————");
                        for (City city: dataList){
                            Log.d(TAG, "点位2：city：" + city.cityName);
                        }
                        dataList = db.cityDao().getAll();
                        Log.d(TAG, "点位： ————再次从数据库取出数据：dataList————");
                        for (City city: dataList){
                            Log.d(TAG, "点位3：city：" + city.cityName);
                        }
                    });
                    builder.setNegativeButton("取消", (dialog, which) -> builder.create().dismiss());
                    builder.create().show();
                }
            });

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 520/* && resultCode == RESULT_OK*/) {
            //Log.d(TAG, "点位： 从搜索页面返回默认刷新，偷懒了");
            initData();
        }
    }


    /*@Override
    public void finish() {
        super.finish();
        if (db != null){
            db.close();
        }
    }*/


}