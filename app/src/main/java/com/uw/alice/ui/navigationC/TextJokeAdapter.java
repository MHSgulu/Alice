package com.uw.alice.ui.navigationC;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.uw.alice.R;
import com.uw.alice.data.model.TextJoke;
import com.uw.alice.databinding.ItemTextJokeListBinding;

import java.util.List;

public class TextJokeAdapter extends RecyclerView.Adapter <TextJokeAdapter.ViewHolder> {

    private Context mContext;
    private List<TextJoke.ResultBean.ShowapiResBodyBean.ContentlistBean> mlistBeans;
    private OnItemClickListener onItemClickListener;


    @NonNull
    @Override
    public TextJokeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        mContext = parent.getContext();
        return new TextJokeAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text_joke_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final TextJokeAdapter.ViewHolder holder, int position)
    {
        TextJoke.ResultBean.ShowapiResBodyBean.ContentlistBean listBean = mlistBeans.get(position);
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
        ItemTextJokeListBinding mBinding;

        ViewHolder(View view)
        {
            super(view);
            mBinding = DataBindingUtil.bind(view);
        }
    }

    TextJokeAdapter(List<TextJoke.ResultBean.ShowapiResBodyBean.ContentlistBean> listBeans){
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
