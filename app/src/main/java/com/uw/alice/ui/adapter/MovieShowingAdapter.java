package com.uw.alice.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.uw.alice.R;
import com.uw.alice.data.entity.MovieEntity;
import com.uw.alice.databinding.ItemMovieShowingBinding;
import com.uw.alice.interfaces.OnItemClickListener;

import java.util.List;

public class MovieShowingAdapter extends RecyclerView.Adapter<MovieShowingAdapter.ViewHolder> {

    private Context mContext;
    private final List<MovieEntity> mList;
    private OnItemClickListener onItemClickListener;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemMovieShowingBinding viewBinding;

        ViewHolder(View view) {
            super(view);
            viewBinding = ItemMovieShowingBinding.bind(view);
        }
    }

    public MovieShowingAdapter(List<MovieEntity> listBeans) {
        mList = listBeans;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public MovieShowingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_showing, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieShowingAdapter.ViewHolder holder, int position) {
        MovieEntity result = mList.get(position);
        Glide.with(mContext).load(result.getMoviePosterUrl()).into(holder.viewBinding.ivMoviePoster);
        holder.viewBinding.tvMovieName.setText(result.getMovieName());
        holder.viewBinding.tvMovieScore.setText(String.valueOf(result.getMovieRating()));
        holder.viewBinding.simpleRatingBar.setRating((float) result.getMovieRating() / 2);

        if (onItemClickListener != null) {
            holder.viewBinding.ivMoviePoster.setOnClickListener(v -> onItemClickListener.onItemClick(holder.itemView, holder.getLayoutPosition()));
        }

    }

    @Override
    public int getItemCount() {
        return Math.min(mList.size(), 6);
    }


}
