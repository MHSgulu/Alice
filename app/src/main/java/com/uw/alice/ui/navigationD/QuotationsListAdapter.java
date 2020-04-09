package com.uw.alice.ui.navigationD;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.uw.alice.R;
import com.uw.alice.data.model.Quotations;
import com.uw.alice.databinding.ItemQuotationsListBinding;

import java.util.List;

public class QuotationsListAdapter extends RecyclerView.Adapter <QuotationsListAdapter.ViewHolder> {

    private Context mContext;
    private List<Quotations.ShowapiResBodyBean.DataBean> mlistBeans;
    private OnItemClickListener onItemClickListener;


    @NonNull
    @Override
    public QuotationsListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        mContext = parent.getContext();
        return new QuotationsListAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quotations_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final QuotationsListAdapter.ViewHolder holder, int position)
    {
        Quotations.ShowapiResBodyBean.DataBean listBean = mlistBeans.get(position);
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

    class ViewHolder extends RecyclerView.ViewHolder
    {
        ItemQuotationsListBinding mBinding;

        ViewHolder(View view)
        {
            super(view);
            mBinding = DataBindingUtil.bind(view);
        }
    }

    QuotationsListAdapter(List<Quotations.ShowapiResBodyBean.DataBean> listBean){
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
