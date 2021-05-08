package com.uw.alice.ui.modular.weather.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.uw.alice.R;
import com.uw.alice.common.Constant;
import com.uw.alice.databinding.ActivitySearchCityBinding;

import java.util.ArrayList;
import java.util.List;

public class SearchCityActivity extends AppCompatActivity {

    private static final String TAG = "SearchCityActivity";
    private ActivitySearchCityBinding viewBinding;
    private Context context;

    private final List<String> cityNameList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_search_city);
        viewBinding = ActivitySearchCityBinding.inflate(getLayoutInflater());
        setContentView(viewBinding.getRoot());
        context = SearchCityActivity.this;

        initOnClickListener();
        initCityList();
        initEditTextChangedListener();
    }

    private void initOnClickListener() {
        viewBinding.llCancel.setOnClickListener(v -> finish());
        viewBinding.llClearInputBox.setOnClickListener(v -> {
            viewBinding.cityInputBox.setText("");
            //
        });
    }

    private void initCityList() {
        cityNameList.add("北京市");
        cityNameList.add("上海市");
        cityNameList.add("广州市");
        cityNameList.add("深圳市");
        cityNameList.add("珠海市");
        cityNameList.add("南京市");
        cityNameList.add("苏州市");
        cityNameList.add("厦门市");
        cityNameList.add("南宁市");
        cityNameList.add("成都市");
        cityNameList.add("长沙市");
        cityNameList.add("福州市");
        cityNameList.add("杭州市");
        cityNameList.add("武汉市");
        cityNameList.add("青岛市");
        cityNameList.add("西安市");
        cityNameList.add("太原市");
        cityNameList.add("沈阳市");
        cityNameList.add("重庆市");
        cityNameList.add("天津市");

        int i = 0;
        for (String cityName: cityNameList) {
            viewBinding.chipGroup.addView(createChip(cityName,i));
            i++;
        }
        viewBinding.chipGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId != -1){
                //Toast.makeText(context, "查看" + cityNameList.get(checkedId) + "天气", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context,SevenDayWeatherActivity.class);
                intent.putExtra(Constant.ARG_CityName,cityNameList.get(checkedId));
                startActivity(intent);
            }
        });


    }

    private void initEditTextChangedListener() {
        viewBinding.cityInputBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (s.toString().isEmpty()){
                    viewBinding.ivClear.setVisibility(View.GONE);
                }else{
                    viewBinding.ivClear.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty()){
                    viewBinding.ivClear.setVisibility(View.GONE);
                }else{
                    viewBinding.ivClear.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 1){
                    Toast.makeText(context, "正在匹配城市，请稍候", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "点位：已输入的文本：" + s.toString());
                    findCitiesFromLocalData(s.toString());
                }
                if (s.toString().isEmpty()){
                    viewBinding.ivClear.setVisibility(View.GONE);
                }else{
                    viewBinding.ivClear.setVisibility(View.VISIBLE);
                }
            }


        });
    }

    @SuppressLint("ResourceType")
    private Chip createChip(String label, int i) {
        Chip chip= new Chip(context);
        chip.setId(i);  //设置此视图的标识符。 标识符在此视图的层次结构中不必唯一。 标识符应为正数。 ChipGroup默认的Id找不到规律性 所以这里设置区分
        chip.setText(label);
        chip.setCheckable(true); //设置此芯片是否可被选择。如果为true，则可以切换芯片进行单选
        chip.setTextSize(14);
        chip.setChipBackgroundColorResource(R.drawable.my_choice_chip_background_color);
        chip.setChipCornerRadius(12); //设置此芯片圆角。
        chip.setChipStartPadding(30); //设置此芯片的开始填充。 4dp   文本到内边的距离
        chip.setChipEndPadding(30); //设置该芯片的尾部填充。
        chip.setChipMinHeight(70);  //设置此芯片的最小高度。 32dp

        //chip.setTextColor(/*R.drawable.my_choice_chip_text_color*/R.color.comingSoonDateColor);   //这玩意有问题

        //chip.setCheckedIconVisible(false); //设置此芯片的选中图标是否可见。
        //chip.setCloseIconVisible(false); //设置此芯片关闭图标是否可见。
        //chip.setTextStartPadding(30); //设置此芯片文本的开始填充。
        //chip.setTextEndPadding(30); //设置此芯片文本的结尾填充。

        chip.setCheckedIconResource(R.drawable.icon_arrow_right_black); //手动设置 没有默认的圆圈阴影效果

        return chip;
    }


    private void findCitiesFromLocalData(String cityName) {

    }


}