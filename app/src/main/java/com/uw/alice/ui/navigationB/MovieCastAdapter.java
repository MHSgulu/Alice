package com.uw.alice.ui.navigationB;

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
import com.uw.alice.data.model.MovieDetails;

import java.util.List;

public class MovieCastAdapter extends RecyclerView.Adapter <MovieCastAdapter.ViewHolder> {

    private Context mContext;
    private List<MovieDetails.DirectorsBean> directorsBeanList;
    private List<MovieDetails.CastsBean> castsBeanList;
    private OnItemClickListener onItemClickListener;


    @NonNull
    @Override
    public MovieCastAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        mContext = parent.getContext();
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_cast_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieCastAdapter.ViewHolder holder, int position)
    {
        if (!directorsBeanList.isEmpty() && !castsBeanList.isEmpty()){
            if (position <= directorsBeanList.size() - 1){
                final MovieDetails.DirectorsBean directorsBean = directorsBeanList.get(position);
                Glide.with(mContext).load(directorsBean.getAvatars().getLarge()).fallback(R.mipmap.icon_no_img).into(holder.ivCastAvatar);
                holder.tvCastName.setText(directorsBean.getName());
                holder.tvCastPosition.setText("导演");

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Toast.makeText(mContext, "点击的都是导演", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(mContext,FilmmakerDetailsActivity.class);
                        intent.putExtra("actorId",directorsBean.getId());
                        intent.putExtra("actorCover",directorsBean.getAvatars().getLarge());
                        mContext.startActivity(intent);
                    }
                });


            }else{
                final MovieDetails.CastsBean castsBean = castsBeanList.get(position - directorsBeanList.size());
                if (castsBean.getAvatars() == null){
                    holder.ivCastAvatar.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    Glide.with(mContext).load(R.mipmap.icon_no_img).into(holder.ivCastAvatar);
                }else {
                    //holder.ivCastAvatar.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    Glide.with(mContext).load(castsBean.getAvatars().getLarge()).fallback(R.mipmap.icon_no_img).into(holder.ivCastAvatar);
                }
                holder.tvCastName.setText(castsBean.getName());
                holder.tvCastPosition.setText("演员");

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (castsBean.getId() == null){
                            Toast.makeText(mContext, "未收录到该影人的条目信息", Toast.LENGTH_SHORT).show();
                        }else {
                            //Toast.makeText(mContext, "点击的都是演员", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(mContext,FilmmakerDetailsActivity.class);
                            intent.putExtra("actorId",castsBean.getId());
                            intent.putExtra("actorCover",castsBean.getAvatars().getLarge());
                            mContext.startActivity(intent);
                        }
                    }
                });


            }
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
        return castsBeanList.size() + directorsBeanList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView ivCastAvatar;
        TextView tvCastName;
        TextView tvCastPosition;

        ViewHolder(View view)
        {
            super(view);
            ivCastAvatar = view.findViewById(R.id.iv_cast_avatar);
            tvCastName = view.findViewById(R.id.tv_cast_name);
            tvCastPosition = view.findViewById(R.id.tv_cast_position);


        }
    }

    MovieCastAdapter(List<MovieDetails.DirectorsBean> list1,List<MovieDetails.CastsBean> list2){
        directorsBeanList = list1;
        castsBeanList = list2;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener)
    {
        this.onItemClickListener = onItemClickListener;
    }
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }







}
