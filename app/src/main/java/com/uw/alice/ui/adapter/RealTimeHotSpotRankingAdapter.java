package com.uw.alice.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.uw.alice.R;
import com.uw.alice.data.model.HotSpot;;
import com.uw.alice.databinding.ItemRealTimeHotSpotListBinding;

import java.util.List;

public class RealTimeHotSpotRankingAdapter extends RecyclerView.Adapter <RealTimeHotSpotRankingAdapter.ViewHolder> {

    private Context mContext;
    private List<HotSpot.ResultBean.ShowapiResBodyBean.ListBean> mlistBeans;
    private OnItemClickListener onItemClickListener;


    @NonNull
    @Override
    public RealTimeHotSpotRankingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        mContext = parent.getContext();
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_real_time_hot_spot_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final RealTimeHotSpotRankingAdapter.ViewHolder holder, int position)
    {
        HotSpot.ResultBean.ShowapiResBodyBean.ListBean listBean = mlistBeans.get(position);
        holder.mBinding.setList(listBean);

        if (listBean.getTrend().equals("rise")){
            holder.mBinding.ivTvHotSpotTrend.setBackgroundResource(R.mipmap.up);
        }else {
            holder.mBinding.ivTvHotSpotTrend.setBackgroundResource(R.mipmap.decline);
        }


        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(v -> {
                int pos = holder.getLayoutPosition();
                onItemClickListener.onItemClick(holder.itemView, pos);
            });
        }


    }

    @Override
    public int getItemCount()
    {
        return mlistBeans.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemRealTimeHotSpotListBinding mBinding;

        ViewHolder(View view) {
            super(view);
            mBinding = DataBindingUtil.bind(view);
        }
    }

    public RealTimeHotSpotRankingAdapter(List<HotSpot.ResultBean.ShowapiResBodyBean.ListBean> listBeans){
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
