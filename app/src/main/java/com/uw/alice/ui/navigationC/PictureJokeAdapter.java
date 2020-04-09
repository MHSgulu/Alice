package com.uw.alice.ui.navigationC;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.uw.alice.R;
import com.uw.alice.data.model.DynamicGif;
import com.uw.alice.data.model.PictureJoke;
import com.uw.alice.databinding.ItemDynamicGifListBinding;
import com.uw.alice.databinding.ItemPictureJokeListBinding;

import java.util.List;

public class PictureJokeAdapter extends RecyclerView.Adapter <PictureJokeAdapter.ViewHolder> {

    private Context mContext;
    private List<PictureJoke.ResultBean.ShowapiResBodyBean.ContentlistBean> mlistBeans;
    private OnItemClickListener onItemClickListener;


    @NonNull
    @Override
    public PictureJokeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        mContext = parent.getContext();
        return new PictureJokeAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_picture_joke_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final PictureJokeAdapter.ViewHolder holder, int position)
    {
        PictureJoke.ResultBean.ShowapiResBodyBean.ContentlistBean listBean = mlistBeans.get(position);
        holder.mBinding.setList(listBean);

        Glide.with(mContext).load(listBean.getImg()).placeholder(R.mipmap.icon_placeholder).into(holder.mBinding.ivGif);

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
        ItemPictureJokeListBinding mBinding;

        ViewHolder(View view)
        {
            super(view);
            mBinding = DataBindingUtil.bind(view);
        }
    }

    PictureJokeAdapter(List<PictureJoke.ResultBean.ShowapiResBodyBean.ContentlistBean> listBeans){
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
