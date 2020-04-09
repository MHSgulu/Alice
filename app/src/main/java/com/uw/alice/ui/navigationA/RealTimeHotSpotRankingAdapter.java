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
import com.uw.alice.data.model.HotSpot;
import com.uw.alice.data.model.NewsSearch;
import com.uw.alice.databinding.ItemNewsSearchListBinding;
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
        return new RealTimeHotSpotRankingAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_real_time_hot_spot_list,parent,false));
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
        ItemRealTimeHotSpotListBinding mBinding;
        //TextView tv_userNickName;

        ViewHolder(View view)
        {
            super(view);
            mBinding = DataBindingUtil.bind(view);
            //iv_cover = view.findViewById(R.id.iv_cover);

        }
    }

    RealTimeHotSpotRankingAdapter(List<HotSpot.ResultBean.ShowapiResBodyBean.ListBean> listBeans){
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
