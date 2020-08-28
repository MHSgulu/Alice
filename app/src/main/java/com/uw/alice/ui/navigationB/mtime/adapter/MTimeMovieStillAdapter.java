package com.uw.alice.ui.navigationB.mtime.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.uw.alice.R;
import com.uw.alice.data.model.MTimeMovieDetail;
import com.uw.alice.data.model.MovieDetails;

import java.util.List;

import cc.shinichi.library.ImagePreview;

public class MTimeMovieStillAdapter extends RecyclerView.Adapter<MTimeMovieStillAdapter.ViewHolder> {

    private Context mContext;
    private MTimeMovieDetail.DataBean.BasicBean.VideoBean videoBean;
    private List<MTimeMovieDetail.DataBean.BasicBean.StageImgBean.ListBean> imgList;


    public MTimeMovieStillAdapter(MTimeMovieDetail.DataBean.BasicBean.VideoBean data, List<MTimeMovieDetail.DataBean.BasicBean.StageImgBean.ListBean> list) {
        videoBean = data;
        imgList = list;
    }

    @NonNull
    @Override
    public MTimeMovieStillAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_time_movie_still_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MTimeMovieStillAdapter.ViewHolder holder, int position) {
        if (videoBean != null) {
            if (position == 0) {
                Glide.with(mContext).load(videoBean.getImg()).placeholder(R.mipmap.icon_no_img).into(holder.ivMovieStill);
                holder.tvFlag.setVisibility(View.VISIBLE);
                holder.rlPlay.setVisibility(View.VISIBLE);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, "电影预告", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            else {
                MTimeMovieDetail.DataBean.BasicBean.StageImgBean.ListBean dataBean = imgList.get(position - 1);
                Glide.with(mContext).load(dataBean.getImgUrl()).placeholder(R.mipmap.icon_no_img).into(holder.ivMovieStill);
                holder.tvFlag.setVisibility(View.GONE);
                holder.rlPlay.setVisibility(View.GONE);
                holder.itemView.setOnClickListener(v -> ImagePreview.getInstance()
                        .setContext(mContext)
                        .setIndex(0)
                        .setImage(dataBean.getImgUrl())
                        .setDownIconResId(R.mipmap.icon_download)
                        .start());
            }
        } else {
            MTimeMovieDetail.DataBean.BasicBean.StageImgBean.ListBean dataBean = imgList.get(position);
            Glide.with(mContext).load(dataBean.getImgUrl()).placeholder(R.mipmap.icon_no_img).into(holder.ivMovieStill);
            holder.tvFlag.setVisibility(View.GONE);
            holder.rlPlay.setVisibility(View.GONE);
            holder.itemView.setOnClickListener(v -> ImagePreview.getInstance()
                    .setContext(mContext)
                    .setIndex(0)
                    .setImage(dataBean.getImgUrl())
                    .setDownIconResId(R.mipmap.icon_download)
                    .start());
        }


    }

    @Override
    public int getItemCount() {
        if (videoBean != null) {
            return imgList.size() + 1;
        } else {
            return imgList.size();
        }

    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivMovieStill;
        TextView tvFlag;
        RelativeLayout rlPlay;

        ViewHolder(View view) {
            super(view);
            ivMovieStill = view.findViewById(R.id.iv_movie_still);
            tvFlag = view.findViewById(R.id.tv_flag);
            rlPlay = view.findViewById(R.id.rl_play);
        }
    }


}
