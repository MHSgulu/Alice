package com.uw.alice.ui.navigationD;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.uw.alice.R;
import com.uw.alice.data.model.TaoGirls;
import com.uw.alice.databinding.ItemTaoGirlsListBinding;

import java.util.List;

import cc.shinichi.library.ImagePreview;

public class TaoModelListAdapter extends RecyclerView.Adapter <TaoModelListAdapter.ViewHolder>{

    private static final String TAG = "TaoModelListAdapter";
    private Context mContext;
    private List<TaoGirls.ShowapiResBodyBean.PagebeanBean.ContentlistBean> mlistBeans;
    private OnItemClickListener onItemClickListener;


    @NonNull
    @Override
    public TaoModelListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        //TaoModelsActivity@fd9eb13
        mContext = parent.getContext();
       // Log.d(TAG, "mContext:"+mContext);
        return new TaoModelListAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tao_girls_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final TaoModelListAdapter.ViewHolder holder, int position)
    {
        final TaoGirls.ShowapiResBodyBean.PagebeanBean.ContentlistBean listBean = mlistBeans.get(position);
        holder.mBinding.setList(listBean);

        holder.mBinding.ivAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePreview.getInstance()
                        .setContext(mContext)
                        .setIndex(0)
                        .setImage(listBean.getAvatarUrl()).start();
            }
        });

        holder.mBinding.ivPicture1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listBean.getImgList().size()==0){
                    ImagePreview.getInstance().setContext(mContext).setIndex(0).setImage(listBean.getAvatarUrl()).start();
                }else{
                    ImagePreview.getInstance()
                            .setContext(mContext)
                            .setIndex(0)
                            .setImageList((List<String>) listBean.getImgList())
                            .start();
                }
            }
        });

        //empty String!!!!!!!!    把身高体重有问题的数据在实体类那边 在实体类里注释掉相关数据 原因是 不清楚 数据源里对这些类型的定义是int还是其他
       /* holder.mBinding.tvHeight.setText(String.format("身高:%s", listBean.getHeight()));*/
       /* holder.mBinding.tvWeight.setText(String.format("体重:%s", listBean.getWeight()));*/
        holder.mBinding.tvFansNum.setText(String.format("粉丝数:%s", listBean.getTotalFanNum()));

        Glide.with(mContext).load(listBean.getAvatarUrl()).placeholder(R.mipmap.icon_placeholder).into(holder.mBinding.ivAvatar);
        Glide.with(mContext).load(listBean.getAvatarUrl()).placeholder(R.mipmap.icon_placeholder).into(holder.mBinding.ivPicture1);


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
        ItemTaoGirlsListBinding mBinding;

        ViewHolder(View view)
        {
            super(view);
            mBinding = DataBindingUtil.bind(view);
        }
    }

    TaoModelListAdapter(List<TaoGirls.ShowapiResBodyBean.PagebeanBean.ContentlistBean> listBeans){
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
