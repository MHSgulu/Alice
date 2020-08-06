package com.uw.alice.ui.navigationD.idiom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.uw.alice.R;
import com.uw.alice.data.model.IdiomKeyword;
import com.uw.alice.databinding.ItemIdiomListBinding;


import java.util.List;

public class IdiomsListAdapter extends RecyclerView.Adapter <IdiomsListAdapter.ViewHolder> {

    private Context mContext;
    private List<IdiomKeyword.ShowapiResBodyBean.DataBean> mlistBeans;
    private OnItemClickListener onItemClickListener;


    @NonNull
    @Override
    public IdiomsListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        mContext = parent.getContext();
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_idiom_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final IdiomsListAdapter.ViewHolder holder, int position)
    {
        IdiomKeyword.ShowapiResBodyBean.DataBean listBean = mlistBeans.get(position);
        holder.mBinding.setList(listBean);

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

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        ItemIdiomListBinding mBinding;

        ViewHolder(View view)
        {
            super(view);
            mBinding = DataBindingUtil.bind(view);
        }
    }

    IdiomsListAdapter(List<IdiomKeyword.ShowapiResBodyBean.DataBean> listBean){
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
