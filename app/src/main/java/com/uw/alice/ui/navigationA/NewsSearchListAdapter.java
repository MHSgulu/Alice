package com.uw.alice.ui.navigationA;

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
import com.uw.alice.data.model.NewsSearch;
import com.uw.alice.databinding.ItemNewsListBinding;
import com.uw.alice.databinding.ItemNewsSearchListBinding;

import java.util.List;

public class NewsSearchListAdapter extends RecyclerView.Adapter <NewsSearchListAdapter.ViewHolder> {

    private Context mContext;
    private List<NewsSearch.ResultBeanX.ResultBean.ListBean> mlistBeans;
    private OnItemClickListener onItemClickListener;


    @NonNull
    @Override
    public NewsSearchListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        mContext = parent.getContext();
        return new NewsSearchListAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_search_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final NewsSearchListAdapter.ViewHolder holder, int position)
    {
        NewsSearch.ResultBeanX.ResultBean.ListBean listBean = mlistBeans.get(position);
        holder.mBinding.setList(listBean);

        Glide.with(mContext).load(listBean.getPic()).into(holder.iv_cover);
        //holder.tv_userNickName.setText(listBean.getNickname());


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
        ItemNewsSearchListBinding mBinding;
        ImageView iv_cover;
        //TextView tv_userNickName;

        ViewHolder(View view)
        {
            super(view);
            mBinding = DataBindingUtil.bind(view);
            iv_cover = view.findViewById(R.id.iv_cover);

        }
    }

    NewsSearchListAdapter(List<NewsSearch.ResultBeanX.ResultBean.ListBean> listBeans){
        mlistBeans = listBeans;
    }

    void setOnItemClickListener(OnItemClickListener onItemClickListener)
    {
        this.onItemClickListener = onItemClickListener;
    }
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

}
