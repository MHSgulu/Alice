package com.uw.alice.ui.modular.weather.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uw.alice.R;
import com.uw.alice.data.model.CityWeather;
import com.uw.alice.databinding.ItemWeatherDetailsBinding;

import java.util.List;

public class WeatherDetailsAdapter extends RecyclerView.Adapter<WeatherDetailsAdapter.ViewHolder> {

    private final List<CityWeather.ResultBeanX.ResultBean.DailyBean> localDataList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemWeatherDetailsBinding binding;

        ViewHolder(View view) {
            super(view);
            binding = ItemWeatherDetailsBinding.bind(view);
        }
    }

    public WeatherDetailsAdapter(List<CityWeather.ResultBeanX.ResultBean.DailyBean> dataList) {
        localDataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather_details, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        CityWeather.ResultBeanX.ResultBean.DailyBean data = localDataList.get(position);

        if (data.getDay().getWeather().contains("晴")){
            holder.binding.ivIconWeather.setBackgroundResource(R.drawable.icon_weather_sunny);
        }else if(data.getDay().getWeather().contains("多云")){
            holder.binding.ivIconWeather.setBackgroundResource(R.drawable.icon_weather_cloudy);
        }else if(data.getDay().getWeather().contains("阴")){
            holder.binding.ivIconWeather.setBackgroundResource(R.drawable.icon_weather_overcast);
        }else if(data.getDay().getWeather().contains("阵雨")){
            holder.binding.ivIconWeather.setBackgroundResource(R.drawable.icon_weather_shower);
        }else if(data.getDay().getWeather().contains("雷阵雨")){
            holder.binding.ivIconWeather.setBackgroundResource(R.drawable.icon_weather_thunder_shower);
        }else if(data.getDay().getWeather().contains("小雨")){
            holder.binding.ivIconWeather.setBackgroundResource(R.drawable.icon_weather_light_rain);
        }else if(data.getDay().getWeather().contains("中雨")){
            holder.binding.ivIconWeather.setBackgroundResource(R.drawable.icon_weather_moderate_rain);
        }else if(data.getDay().getWeather().contains("大雨")){
            holder.binding.ivIconWeather.setBackgroundResource(R.drawable.icon_weather_heavy_rain);
        }else if(data.getDay().getWeather().contains("暴雨")){
            holder.binding.ivIconWeather.setBackgroundResource(R.drawable.icon_weather_rainstorm);
        }else if(data.getDay().getWeather().contains("特大暴雨")){
            holder.binding.ivIconWeather.setBackgroundResource(R.drawable.icon_weather_rainstorm_2);
        }else{
            //默认天气阴吧
            holder.binding.ivIconWeather.setBackgroundResource(R.drawable.icon_weather_overcast);
        }


        if (TextUtils.equals(data.getDay().getWeather(),data.getNight().getWeather())){
            holder.binding.tvWeatherContent.setText(String.format("%s %s", data.getWeek(), data.getDay().getWeather()));
        }else{
            holder.binding.tvWeatherContent.setText(String.format("%s %s转%s", data.getWeek(), data.getDay().getWeather(), data.getNight().getWeather()));
        }
        holder.binding.tvTemperature.setText(String.format("%s° / %s°", data.getDay().getTemphigh(), data.getNight().getTemplow()));

    }

    @Override
    public int getItemCount() {
        return localDataList.size();
    }


}
