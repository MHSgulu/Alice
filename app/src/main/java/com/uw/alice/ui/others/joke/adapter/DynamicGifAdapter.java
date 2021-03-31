package com.uw.alice.ui.others.joke.adapter;

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
import com.uw.alice.databinding.ItemDynamicGifListBinding;
import com.uw.alice.interfaces.OnItemClickListener;

import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;

import java.util.List;

public class DynamicGifAdapter extends RecyclerView.Adapter<DynamicGifAdapter.ViewHolder> {

    private Context mContext;
    private List<DynamicGif.ResultBean.ShowapiResBodyBean.ContentlistBean> mListBeans;
    private OnItemClickListener onItemClickListener;

    @NonNull
    @Override
    public DynamicGifAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dynamic_gif_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final DynamicGifAdapter.ViewHolder holder, int position) {
        DynamicGif.ResultBean.ShowapiResBodyBean.ContentlistBean listBean = mListBeans.get(position);
        holder.mBinding.tvTitle.setHtml(listBean.getTitle(),new HtmlHttpImageGetter(holder.mBinding.tvTitle));
        Glide.with(mContext).load(listBean.getImg())/*.placeholder(R.mipmap.icon_placeholder)*/.into(holder.mBinding.ivGif);

        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClick(holder.itemView, holder.getLayoutPosition()));
        }


    }

    @Override
    public int getItemCount() {
        return mListBeans.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemDynamicGifListBinding mBinding;

        ViewHolder(View view) {
            super(view);
            mBinding = DataBindingUtil.bind(view);
        }
    }

    public DynamicGifAdapter(List<DynamicGif.ResultBean.ShowapiResBodyBean.ContentlistBean> listBeans) {
        mListBeans = listBeans;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }



}
