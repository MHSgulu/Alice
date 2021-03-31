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
import com.uw.alice.data.model.PictureJoke;
import com.uw.alice.databinding.ItemPictureJokeListBinding;
import com.uw.alice.interfaces.OnItemClickListener;

import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;

import java.util.List;

public class PictureJokeAdapter extends RecyclerView.Adapter<PictureJokeAdapter.ViewHolder> {

    private Context mContext;
    private List<PictureJoke.ResultBean.ShowapiResBodyBean.ContentlistBean> mListBeans;
    private OnItemClickListener onItemClickListener;

    public PictureJokeAdapter(List<PictureJoke.ResultBean.ShowapiResBodyBean.ContentlistBean> listBeans) {
        mListBeans = listBeans;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    @NonNull
    @Override
    public PictureJokeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_picture_joke_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final PictureJokeAdapter.ViewHolder holder, int position) {
        PictureJoke.ResultBean.ShowapiResBodyBean.ContentlistBean listBean = mListBeans.get(position);
        holder.mBinding.tvTitle.setHtml(listBean.getTitle(),new HtmlHttpImageGetter(holder.mBinding.tvTitle));
        Glide.with(mContext).load(listBean.getImg()).placeholder(R.mipmap.icon_placeholder).into(holder.mBinding.ivGif);


        // 如果设置了回调，则设置点击事件
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClick(holder.itemView, holder.getLayoutPosition()));
        }


    }

    @Override
    public int getItemCount() {
        return mListBeans.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemPictureJokeListBinding mBinding;

        ViewHolder(View view) {
            super(view);
            mBinding = DataBindingUtil.bind(view);
        }
    }


}
