package com.uw.alice.ui.navigationB;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.uw.alice.R;
import com.uw.alice.data.model.DynamicGif;
import com.uw.alice.data.model.Movie;
import com.uw.alice.databinding.ItemDynamicGifListBinding;

import java.util.List;

public class MovieShowingUpAdapter extends RecyclerView.Adapter <MovieShowingUpAdapter.ViewHolder> {

    private Context mContext;
    private List<Movie.SubjectsBean> mlistBeans;
    private OnItemClickListener onItemClickListener;


    @NonNull
    @Override
    public MovieShowingUpAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        mContext = parent.getContext();
        return new MovieShowingUpAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_showing_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieShowingUpAdapter.ViewHolder holder, int position)
    {
        Movie.SubjectsBean listBean = mlistBeans.get(position);
        Glide.with(mContext).load(listBean.getImages().getLarge()).placeholder(R.mipmap.icon_placeholder).into(holder.ivMoviePoster);
        holder.tvMovieName.setText(listBean.getTitle());
        holder.tvMovieScore.setText(String.valueOf(listBean.getRating().getAverage()));

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
        return mlistBeans.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView ivMoviePoster;
        TextView tvMovieName ;
        TextView tvMovieScore;

        ViewHolder(View view)
        {
            super(view);
            ivMoviePoster = view.findViewById(R.id.iv_movie_poster);
            tvMovieName = view.findViewById(R.id.tv_movie_name);
            tvMovieScore = view.findViewById(R.id.tv_movie_score);
        }
    }

    MovieShowingUpAdapter(List<Movie.SubjectsBean> listBeans){
        mlistBeans = listBeans;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener)
    {
        this.onItemClickListener = onItemClickListener;
    }
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }


}
