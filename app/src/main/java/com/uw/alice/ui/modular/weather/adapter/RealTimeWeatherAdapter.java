package com.uw.alice.ui.modular.weather.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uw.alice.R;
import com.uw.alice.data.model.CityWeather;
import com.uw.alice.databinding.ItemRealTimeWeatherBinding;

import java.util.List;

public class RealTimeWeatherAdapter extends RecyclerView.Adapter<RealTimeWeatherAdapter.ViewHolder> {

    private final List<CityWeather.ResultBeanX.ResultBean.HourlyBean> localDataList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemRealTimeWeatherBinding binding;

        ViewHolder(View view) {
            super(view);
            binding = ItemRealTimeWeatherBinding.bind(view);
        }
    }

    public RealTimeWeatherAdapter(List<CityWeather.ResultBeanX.ResultBean.HourlyBean> dataList) {
        localDataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_real_time_weather, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        CityWeather.ResultBeanX.ResultBean.HourlyBean data = localDataList.get(position);

        holder.binding.tvTime.setText(String.format("%s", data.getTime()));
        holder.binding.tvTemperature.setText(String.format("%s°", data.getTemp()));

        if (data.getWeather().contains("晴")){
            holder.binding.ivIconWeather.setBackgroundResource(R.drawable.icon_weather_sunny);
        }else if(data.getWeather().contains("多云")){
            holder.binding.ivIconWeather.setBackgroundResource(R.drawable.icon_weather_cloudy);
        }else if(data.getWeather().contains("阴")){
            holder.binding.ivIconWeather.setBackgroundResource(R.drawable.icon_weather_overcast);
        }else if(data.getWeather().contains("阵雨")){
            holder.binding.ivIconWeather.setBackgroundResource(R.drawable.icon_weather_shower);
        }else if(data.getWeather().contains("雷阵雨")){
            holder.binding.ivIconWeather.setBackgroundResource(R.drawable.icon_weather_thunder_shower);
        }else if(data.getWeather().contains("小雨")){
            holder.binding.ivIconWeather.setBackgroundResource(R.drawable.icon_weather_light_rain);
        }else if(data.getWeather().contains("中雨")){
            holder.binding.ivIconWeather.setBackgroundResource(R.drawable.icon_weather_moderate_rain);
        }else if(data.getWeather().contains("大雨")){
            holder.binding.ivIconWeather.setBackgroundResource(R.drawable.icon_weather_heavy_rain);
        }else if(data.getWeather().contains("暴雨")){
            holder.binding.ivIconWeather.setBackgroundResource(R.drawable.icon_weather_rainstorm);
        }else if(data.getWeather().contains("特大暴雨")){
            holder.binding.ivIconWeather.setBackgroundResource(R.drawable.icon_weather_rainstorm_2);
        }else{
            //默认天气阴吧
            holder.binding.ivIconWeather.setBackgroundResource(R.drawable.icon_weather_overcast);
        }


    }

    @Override
    public int getItemCount() {
        return localDataList.size();
    }


}
