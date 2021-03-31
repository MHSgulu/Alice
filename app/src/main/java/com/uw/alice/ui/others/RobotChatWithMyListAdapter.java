package com.uw.alice.ui.others;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.uw.alice.R;
import com.uw.alice.databinding.ItemRobotChatWithMyListBinding;

import java.util.List;

public class RobotChatWithMyListAdapter extends RecyclerView.Adapter <RobotChatWithMyListAdapter.ViewHolder> {

    private Context mContext;
    private List<String> mlistBeans;
    private OnItemClickListener onItemClickListener;


    @NonNull
    @Override
    public RobotChatWithMyListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        mContext = parent.getContext();
        return new RobotChatWithMyListAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_robot_chat_with_my_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final RobotChatWithMyListAdapter.ViewHolder holder, int position)
    {
        String string = mlistBeans.get(position);
        holder.mBinding.tvContent.setText(string);

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
        ItemRobotChatWithMyListBinding mBinding;

        ViewHolder(View view)
        {
            super(view);
            mBinding = DataBindingUtil.bind(view);
        }
    }

    RobotChatWithMyListAdapter(List<String> listBean){
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
