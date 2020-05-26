package com.uw.alice.ui.navigationB;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.uw.alice.R;
import com.uw.alice.data.model.Movie;

import java.util.List;

public class MovieShowingUpAdapter extends RecyclerView.Adapter <MovieShowingUpAdapter.ViewHolder> {

    private Context mContext;
    private List<Movie.SubjectsBean> subjectsBeanList;
    private OnItemClickListener onItemClickListener;


    @NonNull
    @Override
    public MovieShowingUpAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        mContext = parent.getContext();
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_showing_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieShowingUpAdapter.ViewHolder holder, int position)
    {
        Movie.SubjectsBean listBean = subjectsBeanList.get(position);
        Glide.with(mContext).load(listBean.getImages().getLarge()).placeholder(R.mipmap.icon_placeholder).into(holder.ivMoviePoster);
        holder.tvMovieName.setText(listBean.getTitle());
        holder.tvMovieScore.setText(String.valueOf(listBean.getRating().getAverage()));
        if (listBean.getRating().getAverage() >= 9.5 && listBean.getRating().getAverage() <= 10){
            holder.layoutFiveStarts.setVisibility(View.VISIBLE);
        }else if (listBean.getRating().getAverage() >= 8.5 && listBean.getRating().getAverage() <= 9.4){
            holder.layoutFourAndHalfStarts.setVisibility(View.VISIBLE);
        }else if (listBean.getRating().getAverage() >= 7.5 && listBean.getRating().getAverage() <= 8.4){
            holder.layoutFourStarts.setVisibility(View.VISIBLE);
        }else if (listBean.getRating().getAverage() >= 6.5 && listBean.getRating().getAverage() <= 7.4){
            holder.layoutThreeAndHalfStarts.setVisibility(View.VISIBLE);
        }else if (listBean.getRating().getAverage() >= 5.5 && listBean.getRating().getAverage() <= 6.4){
            holder.layoutThreeStarts.setVisibility(View.VISIBLE);
        }else if (listBean.getRating().getAverage() >= 4.5 && listBean.getRating().getAverage() <= 5.4){
            holder.layoutTwoAndHalfStarts.setVisibility(View.VISIBLE);
        }else if (listBean.getRating().getAverage() >= 3.5 && listBean.getRating().getAverage() <= 4.4){
            holder.layoutTwoStarts.setVisibility(View.VISIBLE);
        }else if (listBean.getRating().getAverage() >= 2.5 && listBean.getRating().getAverage() <= 3.4){
            holder.layoutOneAndHalfStart.setVisibility(View.VISIBLE);
        }else if (listBean.getRating().getAverage() >= 1.5 && listBean.getRating().getAverage() <= 2.4){
            holder.layoutOneStart.setVisibility(View.VISIBLE);
        }else if (listBean.getRating().getAverage() >= 0.5 && listBean.getRating().getAverage() <= 1.4){
            holder.layoutOneStart.setVisibility(View.VISIBLE);
        }else {
            holder.layoutNoStart.setVisibility(View.VISIBLE);
        }


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
        return subjectsBeanList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView ivMoviePoster;
        TextView tvMovieName ;
        TextView tvMovieScore;

        LinearLayout layoutFiveStarts;
        LinearLayout layoutFourStarts;
        LinearLayout layoutThreeStarts;
        LinearLayout layoutTwoStarts;
        LinearLayout layoutOneStart;
        LinearLayout layoutNoStart;

        LinearLayout layoutFourAndHalfStarts;
        LinearLayout layoutThreeAndHalfStarts;
        LinearLayout layoutTwoAndHalfStarts;
        LinearLayout layoutOneAndHalfStart;



        ViewHolder(View view)
        {
            super(view);
            ivMoviePoster = view.findViewById(R.id.iv_movie_poster);
            tvMovieName = view.findViewById(R.id.tv_movie_name);
            tvMovieScore = view.findViewById(R.id.tv_movie_score);

            layoutFiveStarts = view.findViewById(R.id.layout_five_starts);
            layoutFourStarts = view.findViewById(R.id.layout_four_starts);
            layoutThreeStarts = view.findViewById(R.id.layout_three_starts);
            layoutTwoStarts = view.findViewById(R.id.layout_two_starts);
            layoutOneStart = view.findViewById(R.id.layout_one_start);
            layoutNoStart = view.findViewById(R.id.layout_no_start);

            layoutFourAndHalfStarts = view.findViewById(R.id.layout_four_and_half_starts);
            layoutThreeAndHalfStarts = view.findViewById(R.id.layout_three_and_half_starts);
            layoutTwoAndHalfStarts = view.findViewById(R.id.layout_two_and_half_starts);
            layoutOneAndHalfStart = view.findViewById(R.id.layout_one_and_half_starts);


        }
    }

    MovieShowingUpAdapter(List<Movie.SubjectsBean> listBeans){
        subjectsBeanList = listBeans;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener)
    {
        this.onItemClickListener = onItemClickListener;
    }
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }







}
