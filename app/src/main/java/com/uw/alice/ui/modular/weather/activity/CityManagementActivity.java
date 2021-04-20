package com.uw.alice.ui.modular.weather.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.uw.alice.R;
import com.uw.alice.data.model.CityM;
import com.uw.alice.databinding.ActivityCityManagementBinding;
import com.uw.alice.ui.modular.weather.adapter.CityManagementAdapter;

import java.util.ArrayList;
import java.util.List;

public class CityManagementActivity extends AppCompatActivity {

    private ActivityCityManagementBinding viewBinding;

    private CityManagementAdapter adapter;
    private List<CityM> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_city_management);
        viewBinding = ActivityCityManagementBinding.inflate(getLayoutInflater());
        setContentView(viewBinding.getRoot());

        viewBinding.llBack.setOnClickListener(v -> finish());

        initData();
        adapter = new CityManagementAdapter(dataList);
        viewBinding.recycleCityList.setAdapter(adapter);

    }

    private void initData() {
        CityM data1 = new CityM("临沂","良","16","23","11","阴");
        CityM data2 = new CityM("上海","优","16","23","11","晴");
        CityM data3 = new CityM("北京","严重污染","16","23","11","浮尘");
        CityM data4 = new CityM("广州","中度污染","16","23","11","多云");
        CityM data5 = new CityM("深圳","轻度污染","16","23","11","雨");
        dataList.add(data1);
        dataList.add(data2);
        dataList.add(data3);
        dataList.add(data4);
        dataList.add(data5);
    }


}