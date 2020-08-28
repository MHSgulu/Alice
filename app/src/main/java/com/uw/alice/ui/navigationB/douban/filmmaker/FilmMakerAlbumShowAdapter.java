package com.uw.alice.ui.navigationB.douban.filmmaker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.uw.alice.R;
import com.uw.alice.data.model.FilmMaker;
import com.uw.alice.interfaces.OnItemClickListener;

import java.util.List;


public class FilmMakerAlbumShowAdapter extends RecyclerView.Adapter <FilmMakerAlbumShowAdapter.ViewHolder> {

    private Context mContext;
    private List<FilmMaker.PhotosBean> mDataList;
    private OnItemClickListener onItemClickListener;

    @NonNull
    @Override
    public FilmMakerAlbumShowAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_filmmaker_album_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final FilmMakerAlbumShowAdapter.ViewHolder holder, int position) {
        FilmMaker.PhotosBean photosBean = mDataList.get(position);
        Glide.with(mContext).load(photosBean.getImage()).into(holder.ivActorPhoto);

        if (onItemClickListener != null){
            //返回ViewHolder在最新布局过程中的位置。
            //当RecyclerView懒惰地处理适配器更新时，此位置主要由RecyclerView组件用来保持一致。
            //出于性能和动画原因，RecyclerView会在下一个布局过程中批处理所有适配器更新。
            //这可能会导致项目的适配器位置与其在最新布局计算中的位置不匹配。
            //LayoutManagers在根据项目位置进行计算时应始终调用此方法。
            int pos = holder.getLayoutPosition();
            holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClick(holder.itemView,pos));
        }


    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivActorPhoto;

        ViewHolder(View view) {
            super(view);
            ivActorPhoto = view.findViewById(R.id.iv_actor_photo);
        }
    }

    public FilmMakerAlbumShowAdapter(List<FilmMaker.PhotosBean> data){
        mDataList = data;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }





}
