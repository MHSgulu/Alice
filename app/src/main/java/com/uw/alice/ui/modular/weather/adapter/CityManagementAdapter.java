package com.uw.alice.ui.modular.weather.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uw.alice.R;
import com.uw.alice.data.model.CityM;
import com.uw.alice.databinding.ItemCityWeatherListBinding;

import java.util.List;

public class CityManagementAdapter extends RecyclerView.Adapter<CityManagementAdapter.ViewHolder> {

    private final List<CityM> localDataList;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemCityWeatherListBinding binding;

        ViewHolder(View view) {
            super(view);
            binding = ItemCityWeatherListBinding.bind(view);
        }
    }

    public CityManagementAdapter(List<CityM> dataList) {
        localDataList = dataList;
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
        CityM data = localDataList.get(position);

        if (data.getWeather().contains("晴")) {
            holder.binding.rootCard.setCardBackgroundColor(context.getColor(R.color.weather_qing));
        } else if (data.getWeather().contains("多云")) {
            holder.binding.rootCard.setCardBackgroundColor(context.getColor(R.color.weather_duoyun));
        } else if (data.getWeather().contains("阴")) {
            holder.binding.rootCard.setCardBackgroundColor(context.getColor(R.color.weather_yin));
        } else if (data.getWeather().contains("雨")) {
            holder.binding.rootCard.setCardBackgroundColor(context.getColor(R.color.weather_rain));
        } else if (data.getWeather().contains("浮尘")) {
            holder.binding.rootCard.setCardBackgroundColor(context.getColor(R.color.weather_fuchen));
        } else {
            holder.binding.rootCard.setCardBackgroundColor(context.getColor(R.color.weather_yin));
        }

        holder.binding.tvCity.setText(data.getCityName());
        if (data.getAirQuality().contains("污染")){
            holder.binding.tvAir.setText(data.getAirQuality());
        }else{
            holder.binding.tvAir.setText(String.format("空气%s", data.getAirQuality()));
        }
        holder.binding.tvNowTemperature.setText(String.format("%s°", data.getNowTemp()));
        holder.binding.tvTemperature.setText(String.format("%s° / %s°", data.getHighTemp(), data.getLowTemp()));
    }

    @Override
    public int getItemCount() {
        return localDataList.size();
    }


}
