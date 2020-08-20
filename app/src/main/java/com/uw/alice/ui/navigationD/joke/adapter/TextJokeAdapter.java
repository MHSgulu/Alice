package com.uw.alice.ui.navigationD.joke.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.uw.alice.R;
import com.uw.alice.data.model.TextJoke;
import com.uw.alice.databinding.ItemTextJokeListBinding;
import com.uw.alice.interfaces.OnItemClickListener;

import java.util.List;

public class TextJokeAdapter extends RecyclerView.Adapter<TextJokeAdapter.ViewHolder> {

    private List<TextJoke.ResultBean.ShowapiResBodyBean.ContentlistBean> mListBeans;
    private OnItemClickListener onItemClickListener;


    @NonNull
    @Override
    public TextJokeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text_joke_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final TextJokeAdapter.ViewHolder holder, int position) {
        TextJoke.ResultBean.ShowapiResBodyBean.ContentlistBean listBean = mListBeans.get(position);
        holder.mBinding.setList(listBean);

        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClick(holder.itemView, holder.getLayoutPosition()));
        }

    }

    @Override
    public int getItemCount() {
        return mListBeans.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemTextJokeListBinding mBinding;

        ViewHolder(View view) {
            super(view);
            mBinding = DataBindingUtil.bind(view);
        }
    }

    public TextJokeAdapter(List<TextJoke.ResultBean.ShowapiResBodyBean.ContentlistBean> listBeans) {
        mListBeans = listBeans;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }



}
