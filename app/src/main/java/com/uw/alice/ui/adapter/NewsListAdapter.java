package com.uw.alice.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.uw.alice.R;
import com.uw.alice.data.model.News;
import com.uw.alice.databinding.ItemNewsListBinding;


import java.util.List;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.ViewHolder> {

    private Context mContext;
    private List<News.ResultBeanX.ResultBean.ListBean> mlistBeans;
    private OnItemClickListener onItemClickListener;


    @NonNull
    @Override
    public NewsListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final NewsListAdapter.ViewHolder holder, int position) {
        News.ResultBeanX.ResultBean.ListBean listBean = mlistBeans.get(position);
        holder.mBinding.setList(listBean);

        Glide.with(mContext).load(listBean.getPic()).into(holder.iv_cover);
        //holder.tv_userNickName.setText(listBean.getNickname());

        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClick(holder.itemView, holder.getLayoutPosition()));
        }


    }

    @Override
    public int getItemCount() {
        return mlistBeans.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemNewsListBinding mBinding;
        ImageView iv_cover;
        //TextView tv_userNickName;

        ViewHolder(View view) {
            super(view);
            mBinding = DataBindingUtil.bind(view);
            iv_cover = view.findViewById(R.id.iv_cover);

        }
    }

    public NewsListAdapter(List<News.ResultBeanX.ResultBean.ListBean> listBeans) {
        mlistBeans = listBeans;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }


}
