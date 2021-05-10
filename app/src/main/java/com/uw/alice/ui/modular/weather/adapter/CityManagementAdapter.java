package com.uw.alice.ui.modular.weather.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.uw.alice.R;
import com.uw.alice.common.db.entity.City;
import com.uw.alice.common.network.okhttp.OkHttpUtils;
import com.uw.alice.data.model.CityWeather;
import com.uw.alice.databinding.ItemCityWeatherListBinding;
import com.uw.alice.interfaces.OnItemClickListener;
import com.uw.alice.interfaces.OnItemLongClickListener;
import com.uw.alice.ui.adapter.NewsListAdapter;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CityManagementAdapter extends RecyclerView.Adapter<CityManagementAdapter.ViewHolder> {

    private static final String TAG = "CityManagementAdapter";
    private final List<City> localDataList;
    private Context context;
    private final Activity mActivity;
    private CityWeather data;
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemCityWeatherListBinding binding;

        ViewHolder(View view) {
            super(view);
            binding = ItemCityWeatherListBinding.bind(view);
        }
    }

    public CityManagementAdapter(List<City> dataList, Activity activity) {
        localDataList = dataList;
        mActivity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city_weather_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        if (position == 0){
            holder.binding.ivLocation.setVisibility(View.VISIBLE);
        }
        City city = localDataList.get(position);

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
                data = new Gson().fromJson(Objects.requireNonNull(response.body()).string(), CityWeather.class);
                CityWeather.ResultBeanX.ResultBean resultBean = data.getResult().getResult();
                mActivity.runOnUiThread(() -> {
                    if (data.getResult().getStatus() == 0) {
                        String weather = resultBean.getWeather();
                        if (weather.contains("晴")) {
                            holder.binding.rootCard.setCardBackgroundColor(context.getColor(R.color.weather_qing));
                        } else if (weather.contains("多云")) {
                            holder.binding.rootCard.setCardBackgroundColor(context.getColor(R.color.weather_duoyun));
                        } else if (weather.contains("阴")) {
                            holder.binding.rootCard.setCardBackgroundColor(context.getColor(R.color.weather_yin));
                        } else if (weather.contains("雨")) {
                            holder.binding.rootCard.setCardBackgroundColor(context.getColor(R.color.weather_rain));
                        } else if (weather.contains("浮尘")) {
                            holder.binding.rootCard.setCardBackgroundColor(context.getColor(R.color.weather_fuchen));
                        } else {
                            holder.binding.rootCard.setCardBackgroundColor(context.getColor(R.color.weather_yin));
                        }
                        holder.binding.tvCity.setText(resultBean.getCity());
                        if (resultBean.getAqi().getQuality().contains("污染")) {
                            holder.binding.tvAir.setText(resultBean.getAqi().getQuality());
                        } else {
                            holder.binding.tvAir.setText(String.format("空气%s", resultBean.getAqi().getQuality()));
                        }
                        holder.binding.tvNowTemperature.setText(String.format("%s°", resultBean.getTemp()));
                        holder.binding.tvTemperature.setText(String.format("%s° / %s°", resultBean.getTemphigh(), resultBean.getTemplow()));
                    } else {
                        Toast.makeText(context, "msg: " + data.getResult().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        OkHttpUtils.getInstance().requestCityWeather(city.cityName, callback);

        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null){
                onItemClickListener.onItemClick(holder.itemView, holder.getLayoutPosition());
            }
        });

        holder.itemView.setOnLongClickListener(v -> {
            if (onItemLongClickListener != null){
                onItemLongClickListener.onItemLongClick(holder.itemView, holder.getLayoutPosition());
                return true;
            }
            return false;
        });
    }

    @Override
    public int getItemCount() {
        return localDataList.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

}
