package com.uw.alice.ui.navigationD;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.uw.alice.R;
import com.uw.alice.common.Function;
import com.uw.alice.data.model.Quotations;
import com.uw.alice.common.Constant;
import com.uw.alice.databinding.ItemQuotationsListBinding;

import java.util.List;

public class QuotationsListAdapter extends RecyclerView.Adapter <QuotationsListAdapter.ViewHolder> {

    private static final String TAG = "QuotationsListAdapter";
    private Context mContext;
    private List<Quotations.ShowapiResBodyBean.DataBean> mListBeans;
    private Boolean isVisible = false;
    @NonNull
    @Override
    public QuotationsListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        mContext = parent.getContext();
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quotations_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final QuotationsListAdapter.ViewHolder holder, int position)
    {
        final Quotations.ShowapiResBodyBean.DataBean listBean = mListBeans.get(position);
        holder.mBinding.setList(listBean);

        holder.itemView.setOnClickListener(v -> {
            //Log.d(TAG,"触发点击事件");
            if (!isVisible){
                //Log.d(TAG,"显示翻译");
                isVisible = true;
                holder.mBinding.tvTime.setVisibility(View.VISIBLE);
            }else {
                //Log.d(TAG,"隐藏翻译");
                isVisible = false;
                holder.mBinding.tvTime.setVisibility(View.GONE);
            }

        });

        holder.itemView.setOnLongClickListener(v -> {
            Log.d(TAG,"测试点位：触发长按点击事件");
            CharSequence[] items = {"复制英文语句","复制中文翻译"};
            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(mContext);
            builder.setTitle("选择项");
            builder.setItems(items, (dialog, which) -> {
                if (which == 0){
                    Toast.makeText(mContext, "已将英语短句复制到剪贴板中", Toast.LENGTH_SHORT).show();
                    Function.setTextToClipboard(mContext,listBean.getEnglish());
                    Function.openWebPage(mContext, Constant.GOOGLE_T_URL);
                }else{
                    Toast.makeText(mContext, "已将中文翻译复制到剪贴板中", Toast.LENGTH_SHORT).show();
                    Function.setTextToClipboard(mContext,listBean.getChinese());
                    Function.openWebPage(mContext, Constant.GOOGLE_T_URL);
                }
            });
            builder.create().show();
            return false;
        });

    }

    @Override
    public int getItemCount() {
        return mListBeans.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemQuotationsListBinding mBinding;

        ViewHolder(View view) {
            super(view);
            mBinding = DataBindingUtil.bind(view);
        }
    }

    QuotationsListAdapter(List<Quotations.ShowapiResBodyBean.DataBean> listBean){
        mListBeans = listBean;
    }


}
