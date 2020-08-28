package com.uw.alice.ui.navigationB.mtime.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.uw.alice.R;
import com.uw.alice.data.model.MTimeMovieDetail;
import com.uw.alice.data.util.Util;
import com.uw.alice.ui.navigationB.mtime.MTimeActorDetailsActivity;

import java.util.List;

public class MTimeMovieCastAdapter extends RecyclerView.Adapter<MTimeMovieCastAdapter.ViewHolder> {

    private Context mContext;
    private MTimeMovieDetail.DataBean.BasicBean.DirectorBean directorsBean;
    private List<MTimeMovieDetail.DataBean.BasicBean.ActorsBean> castsBeanList;


    public MTimeMovieCastAdapter(MTimeMovieDetail.DataBean.BasicBean.DirectorBean data, List<MTimeMovieDetail.DataBean.BasicBean.ActorsBean> list) {
        directorsBean = data;
        castsBeanList = list;
    }


    @NonNull
    @Override
    public MTimeMovieCastAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_time_movie_cast_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MTimeMovieCastAdapter.ViewHolder holder, int position) {
        if (position == 0) {
            if (directorsBean.getImg() == null) {
                holder.ivCastAvatar.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                Glide.with(mContext).load(R.mipmap.icon_no_img).into(holder.ivCastAvatar);
            } else {
                Glide.with(mContext).load(directorsBean.getImg()).placeholder(R.mipmap.icon_no_img).into(holder.ivCastAvatar);
            }
            holder.tvCastName.setText(directorsBean.getName());
            holder.tvCastPosition.setText("导演");

            holder.itemView.setOnClickListener(v -> {
                Toast.makeText(mContext, "点击的都是导演", Toast.LENGTH_SHORT).show();
                    /*Intent intent = new Intent(mContext, FilmmakerDetailsActivity.class);
                    intent.putExtra("actorId", directorsBean.getId());
                    intent.putExtra("actorCover", directorsBean.getAvatars().getLarge());
                    mContext.startActivity(intent);*/
            });


        }
        else {
            final MTimeMovieDetail.DataBean.BasicBean.ActorsBean castsBean = castsBeanList.get(position - 1);
            if (castsBean.getImg() == null) {
                holder.ivCastAvatar.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                Glide.with(mContext).load(R.mipmap.icon_no_img).into(holder.ivCastAvatar);
            } else {
                Glide.with(mContext).load(castsBean.getImg()).placeholder(R.mipmap.icon_no_img).into(holder.ivCastAvatar);
            }
            holder.tvCastName.setText(castsBean.getName());
            if (TextUtils.isEmpty(castsBean.getRoleName())){
                holder.tvCastPosition.setText("");
            }else {
                holder.tvCastPosition.setText(String.format("饰 %s", castsBean.getRoleName()));
            }

            holder.itemView.setOnClickListener(v -> {
                Toast.makeText(mContext, "点击的都是演员", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mContext, MTimeActorDetailsActivity.class);
                    intent.putExtra(Util.ARG_ActorId, String.valueOf(castsBean.getActorId()));
                    intent.putExtra(Util.ARG_ActorCover, castsBean.getImg());
                    mContext.startActivity(intent);
            });

        }

    }

    @Override
    public int getItemCount() {
        return Math.min(6,castsBeanList.size() + 1);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivCastAvatar;
        TextView tvCastName;
        TextView tvCastPosition;

        ViewHolder(View view) {
            super(view);
            ivCastAvatar = view.findViewById(R.id.iv_cast_avatar);
            tvCastName = view.findViewById(R.id.tv_cast_name);
            tvCastPosition = view.findViewById(R.id.tv_cast_position);
        }
    }


}
