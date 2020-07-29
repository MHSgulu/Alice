package com.uw.alice.ui.navigationB.filmmaker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.uw.alice.R;
import com.uw.alice.data.model.FilmmakerPhoto;
import com.uw.alice.interfaces.OnItemClickListener;

import java.util.List;


public class FilmMakerPhotoAdapter extends RecyclerView.Adapter <FilmMakerPhotoAdapter.ViewHolder> {

    private Context mContext;
    private List<FilmmakerPhoto.PhotosBean> mDataList;
    private int size;
    private OnItemClickListener onItemClickListener;

    @NonNull
    @Override
    public FilmMakerPhotoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_filmmaker_photo_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final FilmMakerPhotoAdapter.ViewHolder holder, int position) {
        holder.ivPhoto.setLayoutParams(new ViewGroup.LayoutParams(size*2,size*2)); //此时根布局调整为ImageView,单位PX
        FilmmakerPhoto.PhotosBean photosBean = mDataList.get(position);
        Glide.with(mContext).load(photosBean.getImage()).into(holder.ivPhoto);


        if (onItemClickListener != null){
            holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClick(holder.itemView,holder.getLayoutPosition()));
        }


    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPhoto;

        ViewHolder(View view) {
            super(view);
            ivPhoto = view.findViewById(R.id.iv_photo);
        }
    }

    public FilmMakerPhotoAdapter(List<FilmmakerPhoto.PhotosBean> list,int width){
        mDataList = list;
        size = width;
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }



}
