package com.uw.alice.ui.modular.weather.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.uw.alice.common.Constant;
import com.uw.alice.common.db.SingletonRoomDatabase;
import com.uw.alice.common.db.entity.City;
import com.uw.alice.databinding.ActivityCityManagementBinding;
import com.uw.alice.ui.modular.weather.adapter.CityManagementAdapter;

import java.util.ArrayList;
import java.util.List;

public class CityManagementActivity extends AppCompatActivity {

    private static final String TAG = "CityManagementActivity";
    private ActivityCityManagementBinding viewBinding;
    private Context context;

    private CityManagementAdapter adapter;
    private List<City> dataList = new ArrayList<>();

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

        initData();
    }

    private void initData() {
        dataList = SingletonRoomDatabase.getInstance(getApplicationContext()).getAllCity();
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
                        SingletonRoomDatabase.getInstance(getApplicationContext()).deleteCity(cityData.cityName);
                        //再从数据列表中删除，UI可见
                        dataList.remove(position);
                        /**
                         * 通知任何已注册的观察者数据集已更改。
                         *
                         * <p>有两种不同类别的数据更改事件，项目更改和结构更改。
                         * 项目更改是指单个项目的数据已更新但未发生位置更改。
                         * 结构变化是指在数据集中插入、删除或移动项目。</p>
                         *
                         * <p>这个事件没有指定数据集发生了什么变化，强制任何观察者假设所有现有项目和结构可能不再有效。
                         * LayoutManagers 将被迫完全重新绑定和重新布局所有可见视图。</p>
                         *
                         * 当使用此方法时，<p><code>RecyclerView</code> 将尝试为报告它们具有 {@link #hasStableIds() stable IDs} 的适配器合成可见的结构更改事件。
                         * 这有助于实现动画和视觉对象持久性，但仍需要重新分配和重新布局单个项目视图。</p>
                         *
                         * <p>如果你正在编写一个适配器，如果可以的话，使用更具体的更改事件总是会更有效率。
                         * 依靠 <code>notifyDataSetChanged()</code>作为最后的手段。</p>
                         *
                         * @see #notifyItemChanged(int)
                         * @see #notifyItemInserted(int)
                         * @see #notifyItemRemoved(int)
                         * @see #notifyItemRangeChanged(int, int)
                         * @see #notifyItemRangeInserted(int, int)
                         * @see #notifyItemRangeRemoved(int, int)
                         */
                        //adapter.notifyDataSetChanged();

                        /**
                         * 通知任何注册的观察者之前位于 <code>position</code> 的项目已从数据集中删除。
                         * 以前位于 <code>position</code> 和之后的项目现在可以在 <code>oldPosition - 1</code> 找到。
                         *
                         * <p>这是一个结构性变化事件。 数据集中其他现有项目的表示仍然被认为是最新的，不会反弹，尽管它们的位置可能会改变。</p>
                         *
                         * @param position 现在已经被移除的项目的位置
                         */
                        adapter.notifyItemRemoved(position);
                        Log.d(TAG, "点位： ————查看删除后的本地数据 dataList————");
                        for (City city: dataList){
                            Log.d(TAG, "点位2：city：" + city.cityName);
                        }
                        List<City> dataListEnd = SingletonRoomDatabase.getInstance(getApplicationContext()).getAllCity();
                        Log.d(TAG, "点位： ————再次从数据库取出数据：dataListEnd————");
                        for (City city: dataListEnd){
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

}