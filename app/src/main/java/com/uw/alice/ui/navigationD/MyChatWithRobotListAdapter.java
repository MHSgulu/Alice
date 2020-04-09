package com.uw.alice.ui.navigationD;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.uw.alice.R;
import com.uw.alice.data.model.BingWallpaper;
import com.uw.alice.data.model.SingleChat;
import com.uw.alice.data.util.Util;
import com.uw.alice.databinding.ItemBingLatelyWallpaperBinding;
import com.uw.alice.databinding.ItemMyChatWithRobotListBinding;
import com.uw.alice.databinding.ItemRobotChatWithMyListBinding;

import java.util.List;

import cc.shinichi.library.ImagePreview;

public class MyChatWithRobotListAdapter extends RecyclerView.Adapter <RecyclerView.ViewHolder> {

    private Context mContext;
    private List<SingleChat> mlistBeans;
    private OnItemClickListener onItemClickListener;


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        mContext = parent.getContext();
        if (viewType==1){
            return new ViewHolder_Left(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_chat_with_robot_list,parent,false));
        }else{
            return new ViewHolder_Right(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_robot_chat_with_my_list,parent,false));

        }


    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position)
    {
        SingleChat singleChat = mlistBeans.get(position);

        if (holder instanceof ViewHolder_Left){
           ((ViewHolder_Left) holder).textView1.setText(singleChat.getContent());
        }else{
            ((ViewHolder_Right) holder).textView2.setText(singleChat.getContent());
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
    public int getItemViewType(int position) {
        if (mlistBeans.get(position).getSign()==1) {
            return 1;
        } else {
            return 2;
        }
    }

    @Override
    public int getItemCount()
    {
        return mlistBeans.size();
    }

    class ViewHolder_Left extends RecyclerView.ViewHolder
    {
        TextView textView1;

        ViewHolder_Left(View view)
        {
            super(view);
            textView1 = view.findViewById(R.id.tv_content);
        }
    }

    class ViewHolder_Right extends RecyclerView.ViewHolder
    {
        TextView textView2;

        ViewHolder_Right(View view)
        {
            super(view);
            textView2 = view.findViewById(R.id.tv_content);
        }
    }



    MyChatWithRobotListAdapter(List<SingleChat> listBean){
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
