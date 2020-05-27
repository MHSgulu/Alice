package com.uw.alice.ui.navigationB;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.uw.alice.R;
import com.uw.alice.data.model.Movie;
import com.uw.alice.data.model.MovieDetails;

import java.util.List;

public class MovieLabelAdapter extends RecyclerView.Adapter <MovieLabelAdapter.ViewHolder> {

    private Context mContext;
    private List<String> stringList;
    private OnItemClickListener onItemClickListener;


    @NonNull
    @Override
    public MovieLabelAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        mContext = parent.getContext();
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_label_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieLabelAdapter.ViewHolder holder, int position)
    {
        String tag = stringList.get(position);
        holder.tvMovieLabel.setText(tag);


        // 如果设置了回调，则设置点击事件
        if (onItemClickListener != null)
        {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    onItemClickListener.onItemClick(holder.itemView, pos);
                }
            });
        }


    }

    @Override
    public int getItemCount()
    {
        return stringList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder
    {

        TextView tvMovieLabel;

        ViewHolder(View view)
        {
            super(view);
            tvMovieLabel = view.findViewById(R.id.tv_movie_label);

        }
    }

    MovieLabelAdapter(List<String> listBeans){
        stringList = listBeans;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener)
    {
        this.onItemClickListener = onItemClickListener;
    }
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }







}
