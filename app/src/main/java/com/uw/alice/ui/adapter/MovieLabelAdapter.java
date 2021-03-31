package com.uw.alice.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uw.alice.R;
import com.uw.alice.interfaces.OnItemClickListener;

import java.util.List;

public class MovieLabelAdapter extends RecyclerView.Adapter<MovieLabelAdapter.ViewHolder> {

    private Context mContext;
    private List<String> mList;
    private OnItemClickListener onItemClickListener;

    MovieLabelAdapter(List<String> listBeans) {
        mList = listBeans;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public MovieLabelAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_label_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieLabelAdapter.ViewHolder holder, int position) {
        String tag = mList.get(position);
        holder.tvMovieLabel.setText(tag);


        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClick(holder.itemView, holder.getLayoutPosition()));
        }


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvMovieLabel;

        ViewHolder(View view) {
            super(view);
            tvMovieLabel = view.findViewById(R.id.tv_movie_label);

        }
    }


}
