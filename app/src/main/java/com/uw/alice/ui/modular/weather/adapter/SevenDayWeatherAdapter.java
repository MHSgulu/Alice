package com.uw.alice.ui.modular.weather.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uw.alice.R;
import com.uw.alice.data.model.CityWeather;
import com.uw.alice.databinding.ItemSevenDayWeatherListBinding;

import java.util.List;

public class SevenDayWeatherAdapter extends RecyclerView.Adapter<SevenDayWeatherAdapter.ViewHolder> {

    private final List<CityWeather.ResultBeanX.ResultBean.DailyBean> localDataList;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemSevenDayWeatherListBinding binding;

        ViewHolder(View view) {
            super(view);
            binding = ItemSevenDayWeatherListBinding.bind(view);
        }
    }

    public SevenDayWeatherAdapter(List<CityWeather.ResultBeanX.ResultBean.DailyBean> dataList) {
        localDataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_seven_day_weather_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        CityWeather.ResultBeanX.ResultBean.DailyBean data = localDataList.get(position);
        holder.binding.tvWeek.setText(data.getWeek());
        holder.binding.tvDate.setText(data.getDate().substring(5));
        holder.binding.tvWeather1.setText(data.getDay().getWeather());
        holder.binding.tvWeather2.setText(data.getNight().getWeather());
        holder.binding.tvHighTemperature.setText(String.format("%s°", data.getDay().getTemphigh()));
        holder.binding.tvLowTemperature.setText(String.format("%s°", data.getNight().getTemplow()));
        holder.binding.tvWindDirection.setText(data.getNight().getWinddirect());
        holder.binding.tvWindPower.setText(data.getNight().getWindpower());

        String dayWeather = data.getDay().getWeather();
        String nightWeather = data.getNight().getWeather();
        //昼天气
        if (dayWeather.contains("晴")){
            holder.binding.ivIconWeather1.setBackgroundResource(R.drawable.icon_weather_sunny);
        }else if (dayWeather.contains("多云")) {
            holder.binding.ivIconWeather1.setBackgroundResource(R.drawable.icon_weather_cloudy);
        }else if (dayWeather.contains("阴")) {
            holder.binding.ivIconWeather1.setBackgroundResource(R.drawable.icon_weather_overcast);
        }else if (dayWeather.contains("雨")) {
            holder.binding.ivIconWeather1.setBackgroundResource(R.drawable.icon_weather_light_rain);
        }else if(dayWeather.contains("阵雨")){
            holder.binding.ivIconWeather1.setBackgroundResource(R.drawable.icon_weather_shower);
        }else if(dayWeather.contains("雷阵雨")){
            holder.binding.ivIconWeather1.setBackgroundResource(R.drawable.icon_weather_thunder_shower);
        }else if(dayWeather.contains("小雨")){
            holder.binding.ivIconWeather1.setBackgroundResource(R.drawable.icon_weather_light_rain);
        }else if(dayWeather.contains("中雨")){
            holder.binding.ivIconWeather1.setBackgroundResource(R.drawable.icon_weather_moderate_rain);
        }else if(dayWeather.contains("大雨")){
            holder.binding.ivIconWeather1.setBackgroundResource(R.drawable.icon_weather_heavy_rain);
        }else if(dayWeather.contains("暴雨")){
            holder.binding.ivIconWeather1.setBackgroundResource(R.drawable.icon_weather_rainstorm);
        }else if(dayWeather.contains("特大暴雨")){
            holder.binding.ivIconWeather1.setBackgroundResource(R.drawable.icon_weather_rainstorm_2);
        }else {
            holder.binding.ivIconWeather1.setBackgroundResource(R.drawable.icon_weather_overcast);
        }
        //夜天气
        if (nightWeather.contains("晴")){
            holder.binding.ivIconWeather2.setBackgroundResource(R.drawable.icon_weather_sunny);
        }else if (nightWeather.contains("多云")) {
            holder.binding.ivIconWeather2.setBackgroundResource(R.drawable.icon_weather_cloudy);
        }else if (nightWeather.contains("阴")) {
            holder.binding.ivIconWeather2.setBackgroundResource(R.drawable.icon_weather_overcast);
        }else if (nightWeather.contains("雨")) {
            holder.binding.ivIconWeather2.setBackgroundResource(R.drawable.icon_weather_light_rain);
        }else if(nightWeather.contains("阵雨")){
            holder.binding.ivIconWeather2.setBackgroundResource(R.drawable.icon_weather_shower);
        }else if(nightWeather.contains("雷阵雨")){
            holder.binding.ivIconWeather2.setBackgroundResource(R.drawable.icon_weather_thunder_shower);
        }else if(nightWeather.contains("小雨")){
            holder.binding.ivIconWeather2.setBackgroundResource(R.drawable.icon_weather_light_rain);
        }else if(nightWeather.contains("中雨")){
            holder.binding.ivIconWeather2.setBackgroundResource(R.drawable.icon_weather_moderate_rain);
        }else if(nightWeather.contains("大雨")){
            holder.binding.ivIconWeather2.setBackgroundResource(R.drawable.icon_weather_heavy_rain);
        }else if(nightWeather.contains("暴雨")){
            holder.binding.ivIconWeather2.setBackgroundResource(R.drawable.icon_weather_rainstorm);
        }else if(nightWeather.contains("特大暴雨")){
            holder.binding.ivIconWeather2.setBackgroundResource(R.drawable.icon_weather_rainstorm_2);
        }else {
            holder.binding.ivIconWeather2.setBackgroundResource(R.drawable.icon_weather_overcast);
        }


    }

    @Override
    public int getItemCount() {
        return localDataList.size();
    }


}
