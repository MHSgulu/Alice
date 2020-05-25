package com.uw.alice.ui.navigationD;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.uw.alice.R;
import com.uw.alice.data.model.BingWallpaper;
import com.uw.alice.data.util.Util;
import com.uw.alice.databinding.ItemBingLatelyWallpaperBinding;
import com.uw.alice.databinding.ItemIdiomListBinding;

import java.util.Date;
import java.util.List;

import cc.shinichi.library.ImagePreview;

public class BingLatelyWallpaperAdapter extends RecyclerView.Adapter <BingLatelyWallpaperAdapter.ViewHolder> {

    private Context mContext;
    private List<BingWallpaper.ImagesBean> mlistBeans;
    private OnItemClickListener onItemClickListener;


    @NonNull
    @Override
    public BingLatelyWallpaperAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        mContext = parent.getContext();
        return new BingLatelyWallpaperAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bing_lately_wallpaper,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final BingLatelyWallpaperAdapter.ViewHolder holder, int position)
    {
        final BingWallpaper.ImagesBean listBean = mlistBeans.get(position);
        holder.mBinding.tvCopyright.setText(listBean.getCopyright());
        holder.mBinding.tvDate.setText(listBean.getEnddate());
        Glide.with(mContext).load(Util.BING_API_URL + listBean.getUrl()).into(holder.mBinding.ivWallpaper);

        //点击 版权文字
        holder.mBinding.tvCopyright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri webpage = Uri.parse(listBean.getCopyrightlink());
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                if (intent.resolveActivity(mContext.getPackageManager()) != null) {
                    mContext.startActivity(intent);
                }
            }
        });

        //点击 壁纸图片
        holder.mBinding.ivWallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePreview.getInstance()
                        .setContext(mContext)
                        .setIndex(0)
                        .setImage(Util.BING_API_URL + listBean.getUrl()).start();
            }
        });

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
        ItemBingLatelyWallpaperBinding mBinding;

        ViewHolder(View view)
        {
            super(view);
            mBinding = DataBindingUtil.bind(view);
        }
    }

    BingLatelyWallpaperAdapter(List<BingWallpaper.ImagesBean> listBean){
        mlistBeans = listBean;
    }



    public void setOnItemClickListener(OnItemClickListener onItemClickListener)
    {
        this.onItemClickListener = onItemClickListener;
    }
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

}
