package com.uw.alice.ui.modular.weather.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.uw.alice.R;
import com.uw.alice.databinding.ActivitySearchCityBinding;

public class SearchCityActivity extends AppCompatActivity {

    private ActivitySearchCityBinding viewBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_search_city);
        viewBinding = ActivitySearchCityBinding.inflate(getLayoutInflater());
        setContentView(viewBinding.getRoot());

        viewBinding.llCancel.setOnClickListener(v -> finish());

    }


}