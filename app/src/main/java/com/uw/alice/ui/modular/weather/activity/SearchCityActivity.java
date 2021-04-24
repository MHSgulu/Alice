package com.uw.alice.ui.modular.weather.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.uw.alice.R;
import com.uw.alice.databinding.ActivitySearchCityBinding;

import java.util.ArrayList;
import java.util.List;


public class SearchCityActivity extends AppCompatActivity {

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
        viewBinding.llCancel.setOnClickListener(v -> finish());

        initCityList();

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
        viewBinding.chipGroup.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup group, int checkedId) {
                if (checkedId != -1){
                    Toast.makeText(context, "查看" + cityNameList.get(checkedId) + "天气", Toast.LENGTH_SHORT).show();
                }/*else{
                    Toast.makeText(context, "取消了当前选择", Toast.LENGTH_SHORT).show();
                }*/

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

        return chip;
    }


}