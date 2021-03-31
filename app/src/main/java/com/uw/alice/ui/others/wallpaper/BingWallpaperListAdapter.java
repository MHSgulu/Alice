package com.uw.alice.ui.others.wallpaper;

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
import com.uw.alice.common.Constant;
import com.uw.alice.databinding.ItemBingLatelyWallpaperBinding;

import java.util.List;

import cc.shinichi.library.ImagePreview;

public class BingWallpaperListAdapter extends RecyclerView.Adapter <BingWallpaperListAdapter.ViewHolder> {

    private Context mContext;
    private List<BingWallpaper.ImagesBean> mDataList;


    @NonNull
    @Override
    public BingWallpaperListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        mContext = parent.getContext();
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bing_lately_wallpaper, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final BingWallpaperListAdapter.ViewHolder holder, int position)
    {
        final BingWallpaper.ImagesBean listBean = mDataList.get(position);
        holder.mBinding.tvCopyright.setText(listBean.getCopyright());
        StringBuilder stringBuilder = new StringBuilder(listBean.getEnddate());
        stringBuilder.insert(4,"-");
        stringBuilder.insert(7,"-");
        holder.mBinding.tvDate.setText(stringBuilder);
        Glide.with(mContext).load(Constant.BING_API_URL + listBean.getUrl()).into(holder.mBinding.ivWallpaper);

        //点击 版权文字
        holder.mBinding.tvCopyright.setOnClickListener(v -> {
            Uri uri = Uri.parse(listBean.getCopyrightlink());
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            if (intent.resolveActivity(mContext.getPackageManager()) != null) {
                mContext.startActivity(intent);
            }
        });

        //点击 壁纸图片
        holder.mBinding.ivWallpaper.setOnClickListener(v ->
                ImagePreview.getInstance()
                .setContext(mContext)
                .setIndex(0)
                .setImage(Constant.BING_API_URL + listBean.getUrl()).start());

    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemBingLatelyWallpaperBinding mBinding;

        ViewHolder(View view) {
            super(view);
            mBinding = DataBindingUtil.bind(view);
        }
    }

    BingWallpaperListAdapter(List<BingWallpaper.ImagesBean> listBean){
        mDataList = listBean;
    }



}
