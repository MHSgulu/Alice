package com.uw.alice.ui.modular.weather.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.room.util.StringUtil;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.hardware.input.InputManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ResultReceiver;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
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
    private AssetManager assetManager;
    private InputMethodManager inputMethodManager;

    private final List<String> cityNameList = new ArrayList<>();
    private String cityData;
    private CityEntity cityEntity;
    private List<String> cityNameDataList = new ArrayList<>();

    private List<String> cityNameDataStringList = new ArrayList<>();
    private String cityStringData;


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
        viewBinding.llCancel.setOnClickListener(v -> {
            finish();
            /*
              强制完成您先前从 {@link #startActivityForResult} 开始的另一项活动.
              @param requestCode 您已赋予startActivityForResult（）的活动的请求代码。 如果有多个活动以该请求代码开始，则所有活动都将完成.
             */
            //finishActivity(510);
        });
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
        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

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
                    Log.d(TAG, "点位：s.toString()：" + s.toString());
                    //输入之后，将得到的城市名与本地数据匹配
                    if (cityStringData.contains(s.toString())) {
                        /*
                          没有结果的 {@link #hideSoftInputFromWindow(IBinder, int, ResultReceiver)} 的同义词：
                          请求从当前正在接受输入的窗口的上下文中隐藏软输入窗口
                          @param windowToken 发出请求的窗口的令牌,由 {@link View#getWindowToken() View.getWindowToken()} 返回.
                          @param flags 提供其他操作标志. 当前可能为0或已设置该位{@link #HIDE_IMPLICIT_ONLY}.
                         */
                        inputMethodManager.hideSoftInputFromWindow(viewBinding.getRoot().getWindowToken(), 0);
                        /*
                          制作小吃店以显示消息
                          <p>Snackbar会尝试通过提供给{@code view}的值来找到一个父视图来保存Snackbar的视图。
                          Snackbar将沿着视图树走，试图找到合适的父级，将其定义为{@link CoordinatorLayout}或窗口装饰的内容视图（以先到者为准）.
                          <p>在视图层次结构中使用{@link CoordinatorLayout}可以使Snackbar启用某些功能，例如滑动到关闭和自动移动小部件.
                          @param view从中查找父级的视图。 调用{@link Snackbar＃setAnchorView（int）}时，此视图还用于查找锚视图.
                          @param 文本要显示的文本。 可以格式化文本.
                          @param 持续时间显示消息的时间。 可以是{@link #LENGTH_SHORT}，{@ link #LENGTH_LONG}，{@ link #LENGTH_INDEFINITE}或自定义的持续时间（以毫秒为单位）.
                         */
                        Snackbar.make(viewBinding.getRoot(), "已匹配到" + s.toString(), Snackbar.LENGTH_LONG)
                                .setAction("查看天气", v -> {
                                    Intent intent = new Intent(context, SevenDayWeatherActivity.class);
                                    intent.putExtra(Constant.ARG_CityName, s.toString());
                                    startActivity(intent);
                                }).show();

                    } else {
                        Toast.makeText(context, "未查到此区域", Toast.LENGTH_SHORT).show();
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
            cityEntity = new Gson().fromJson(cityData, CityEntity.class);
            //Log.d(TAG, "点位: 测试cityEntity：  " + cityEntity.getResult().get(0).getCity());
            List<CityEntity.ResultBean> dataList = cityEntity.getResult();
            int i = 0;
            for (CityEntity.ResultBean result : dataList) {
                cityNameDataList.add(dataList.get(i).getCity());
                i++;
            }
            //Log.d(TAG, "点位: cityNameDataList：  " + cityNameDataList);


            //将城市名列表转化为String
            StringBuilder sb = new StringBuilder();
            for (String s : cityNameDataList) {
                sb.append(s);
            }
            cityStringData = sb.toString();
            //Log.d(TAG, "点位: cityStringData：  " + cityStringData);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}