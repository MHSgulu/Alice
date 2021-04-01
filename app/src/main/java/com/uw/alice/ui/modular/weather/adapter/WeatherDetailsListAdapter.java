package com.uw.alice.ui.modular.weather.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uw.alice.R;
import com.uw.alice.databinding.ItemWeatherDetailsBinding;

import java.util.List;

public class WeatherDetailsListAdapter extends RecyclerView.Adapter<WeatherDetailsListAdapter.ViewHolder> {

    private static final String TAG = "WeatherDetailsListAdapt";
    private final List<String> localDataList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemWeatherDetailsBinding binding;

        ViewHolder(View view) {
            super(view);
            binding = ItemWeatherDetailsBinding.bind(view);
        }
    }

    public WeatherDetailsListAdapter(List<String> dataList) {
        localDataList = dataList;
    }

    @NonNull
    @Override
    public WeatherDetailsListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather_details, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final WeatherDetailsListAdapter.ViewHolder holder, int position) {
        String aaa = localDataList.get(position);
        holder.binding.tvWeatherContent.setText(aaa);
    }

    @Override
    public int getItemCount() {
        return localDataList.size();
    }


}
