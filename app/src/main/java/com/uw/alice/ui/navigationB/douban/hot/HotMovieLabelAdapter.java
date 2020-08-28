package com.uw.alice.ui.navigationB.douban.hot;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uw.alice.R;

import java.util.List;

public class HotMovieLabelAdapter extends RecyclerView.Adapter <HotMovieLabelAdapter.ViewHolder> {

    private List<String> mValues;

    @NonNull
    @Override
    public HotMovieLabelAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hot_movie_label_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final HotMovieLabelAdapter.ViewHolder holder, int position) {
        String tag = mValues.get(position);
        holder.tvMovieLabel.setText(tag);
    }

    @Override
    public int getItemCount()
    {
        return mValues.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView tvMovieLabel;

        ViewHolder(View view) {
            super(view);
            tvMovieLabel = view.findViewById(R.id.tv_movie_label);

        }
    }

    HotMovieLabelAdapter(List<String> listBeans){
        mValues = listBeans;
    }


}
