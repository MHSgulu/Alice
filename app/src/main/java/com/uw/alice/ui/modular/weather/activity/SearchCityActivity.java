package com.uw.alice.ui.modular.weather.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.google.gson.Gson;
import com.uw.alice.R;
import com.uw.alice.common.Constant;
import com.uw.alice.databinding.ActivitySearchCityBinding;
import com.uw.alice.entity.CityEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SearchCityActivity extends AppCompatActivity {

    private static final String TAG = "SearchCityActivity";
    private ActivitySearchCityBinding viewBinding;
    private Context context;

    private final List<String> cityNameList = new ArrayList<>();
    private AssetManager assetManager;
    private String cityData;
    private CityEntity cityEntity;
    private List<String> cityNameDataList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_search_city);
        viewBinding = ActivitySearchCityBinding.inflate(getLayoutInflater());
        setContentView(viewBinding.getRoot());
        context = SearchCityActivity.this;

        getAssetData();
        initOnClickListener();
        initCityList();
        initEditTextChangedListener();
    }


    private void initOnClickListener() {
        viewBinding.llCancel.setOnClickListener(v -> finish());
        viewBinding.llClearInputBox.setOnClickListener(v -> {
            //清空输入框
            viewBinding.cityInputBox.setText("");
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
        for (String cityName : cityNameList) {
            viewBinding.chipGroup.addView(createChip(cityName, i));
            i++;
        }
        viewBinding.chipGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId != -1) {
                //Toast.makeText(context, "查看" + cityNameList.get(checkedId) + "天气", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, SevenDayWeatherActivity.class);
                intent.putExtra(Constant.ARG_CityName, cityNameList.get(checkedId));
                startActivity(intent);
            }
        });


    }


    private void initEditTextChangedListener() {
        viewBinding.cityInputBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (s.toString().isEmpty()) {
                    viewBinding.ivClear.setVisibility(View.GONE);
                } else {
                    viewBinding.ivClear.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty()) {
                    viewBinding.ivClear.setVisibility(View.GONE);
                } else {
                    viewBinding.ivClear.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 1) {
                    Log.d(TAG, "点位：已输入的文本：" + s.toString());
                    //输入之后，将得到的城市名与本地数据匹配
                    for (String s1:cityNameDataList){
                        if (TextUtils.equals(s.toString(),s1)){
                            Log.d(TAG, "点位：已找到匹配的城市：");
                            Log.d(TAG, "点位：s.toString()：" + s.toString());
                            Log.d(TAG, "点位：s1：" + s1);
                        }else{
                            Log.d(TAG, "点位：没找到该城市");
                        }
                    }
                }
                if (s.toString().isEmpty()) {
                    viewBinding.ivClear.setVisibility(View.GONE);
                } else {
                    viewBinding.ivClear.setVisibility(View.VISIBLE);
                }
            }


        });
    }


    @SuppressLint("ResourceType")
    private Chip createChip(String label, int i) {
        Chip chip = new Chip(context);
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


    /*
    * 将本地assets目录下的城市json文件数据转化String
    */
    private void getAssetData() {
        StringBuilder stringBuilder = new StringBuilder();
        assetManager = getAssets();
        try {
            /*
              这个抽象类是表示字节输入流的所有类的超类。
              需要定义InputStream的子类的应用程序必须始终提供一种返回下一个输入字节的方法。
             */
            InputStream inputStream = assetManager.open("city_example_3.json");
            /*
              InputStreamReader是从字节流到字符流的桥梁：它读取字节并使用指定的字节将其解码为字符.
              它使用的字符集可以按名称指定，也可以明确指定，也可以接受平台的默认字符集。
              <p> InputStreamReader的read（）方法之一的每次调用都可能导致从基础字节输入流中读取一个或多个字节.
              为了实现字节到字符的有效转换，与满足当前读取操作所需的字节数相比，可以从基础流中提前读取更多字节
              <p> 为了获得最高的效率，请考虑将InputStreamReader包装在BufferedReader中。
             */
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            /*
              从字符输入流中读取文本，缓冲字符，以便有效读取字符，数组和行.
              <p> 可以指定缓冲区大小，也可以使用默认大小。 默认值对于大多数用途来说足够大.
              <p> 通常，由读取器发出的每个读取请求都会导致对基础字符或字节流进行相应的读取请求。
              因此，建议将BufferedReader包裹在其read（）操作可能会很昂贵的任何Reader上，例如FileReaders和InputStreamReaders.
              将缓冲来自指定文件的输入。
              没有缓冲，每个调用read（）或readLine（）可能导致从文件中读取字节，将其转换为字符，然后返回，这可能会非常低效.
              <p> 可以通过使用适当的BufferedReader替换每个DataInputStream来本地化使用DataInputStreams进行文本输入的程序.
             */
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String data;
            /*
              读取一行文字.  一行被认为由换行符（'\ n'），回车符（'\ r'）或回车符后紧跟换行符之一终止.

              @return  一个字符串，其中包含行的内容，不包含任何行终止符；如果已到达流的末尾，则为null
             */
            while ((data = bufferedReader.readLine()) != null) {
                stringBuilder.append(data);
            }

            cityData = stringBuilder.toString();
            //Log.d(TAG, "点位: 已转化的城市数据：  " + cityData);
            cityEntity = new Gson().fromJson(cityData,CityEntity.class);
            //Log.d(TAG, "点位: 测试cityEntity：  " + cityEntity.getResult().get(0).getCity());
            List<CityEntity.ResultBean> dataList = cityEntity.getResult();
            int i = 0;
            for (CityEntity.ResultBean result:dataList){
                cityNameDataList.add(dataList.get(i).getCity());
                i++;
            }
            //Log.d(TAG, "点位: cityNameDataList：  " + cityNameDataList);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void finish() {
        super.finish();
        if (assetManager != null) {
            assetManager.close();
        }
    }
}