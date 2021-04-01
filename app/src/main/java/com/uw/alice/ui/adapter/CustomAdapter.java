package com.uw.alice.ui.adapter;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.uw.alice.R;

import org.jetbrains.annotations.NotNull;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private final String[] localDataSet;
    private static final String TAG = "CustomAdapter";

    /**
     * 提供对您正在使用的视图类型的参考（自定义ViewHolder）。
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        public ViewHolder(View view) {
            super(view);
            // 为ViewHolder的视图定义点击监听器
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
                }
            });
            textView = view.findViewById(R.id.textView);
        }

        public TextView getTextView() {
            return textView;
        }
    }

    /**
     * 初始化适配器的数据集。
     *
     * @param dataSet String []包含用于填充要由RecyclerView使用的视图的数据。
     */
    public CustomAdapter(String[] dataSet) {
        localDataSet = dataSet;
    }

    // 创建新视图（由布局管理器调用）
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // 创建一个新视图，该视图定义列表项的UI
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.text_row_item, viewGroup, false);

        return new ViewHolder(view);
    }

    // 替换视图的内容（由布局管理器调用）
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // 从此位置的数据集中获取元素，然后用该元素替换视图的内容
        viewHolder.getTextView().setText(localDataSet[position]);
    }

    // 返回数据集的大小（由布局管理器调用）
    @Override
    public int getItemCount() {
        return localDataSet.length;
    }
}

