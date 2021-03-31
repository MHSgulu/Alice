package com.uw.alice.ui.modular.idiom.fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.uw.alice.R;
import com.uw.alice.common.Function;
import com.uw.alice.data.model.IdiomKeyword;
import com.uw.alice.common.Constant;
import com.uw.alice.databinding.FragmentIdiomKeywordBinding;
import com.uw.alice.common.network.retrofit.SingletonRetrofit;
import com.uw.alice.ui.modular.idiom.adapter.IdiomsListAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class IdiomKeywordFragment extends Fragment implements View.OnClickListener{


    private static final String TAG = "IdiomKeywordFragment";
    private Context mContext;
    private FragmentIdiomKeywordBinding mBinding;
    private List<IdiomKeyword.ShowapiResBodyBean.DataBean> mDataList = new ArrayList<>();
    private IdiomsListAdapter mAdapter;
    private int currentPage = 1;
    private int totalPage = 0;

    public static IdiomKeywordFragment newInstance() {

        return new IdiomKeywordFragment();
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //View root = inflater.inflate(R.layout.fragment_idiom_keyword, container, false);
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_idiom_keyword, container, false);
        mBinding.setOnClickListener(this);
        mContext = getContext();


        mBinding.refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(1000);
            }
        });

        mBinding.refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (currentPage < totalPage){
                    //请求下一页的列表数据
                    queryIdiomDataList(currentPage + 1);
                    refreshLayout.finishLoadMore(1000);
                }else{
                   refreshLayout.finishLoadMoreWithNoMoreData();
                }
            }
        });


        mBinding.etInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (s.toString().isEmpty()){
                    mBinding.ivReset.setVisibility(View.GONE);
                }else{
                    mBinding.ivReset.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mBinding.recycleList.setVisibility(View.GONE);
                if (s.toString().isEmpty()){
                    mBinding.ivReset.setVisibility(View.GONE);
                }else{
                    mBinding.ivReset.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().isEmpty()){
                    mBinding.ivReset.setVisibility(View.GONE);
                }else{
                    mBinding.ivReset.setVisibility(View.VISIBLE);
                }
            }
        });

        return mBinding.getRoot();
    }




    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){

            case R.id.iv_reset:
                mBinding.etInput.setText("");
                break;

            case R.id.ll_search:
                if (TextUtils.isEmpty(mBinding.etInput.getText().toString().trim())){
                    Toast.makeText(mContext, "不得含有非中文字符。", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    //查询成语关键字
                    queryIdiomDataList(1);
                }
                break;
        }
    }


    private void queryIdiomDataList(final int page) {

        Observer<IdiomKeyword> idiomKeywordObserver = new Observer<IdiomKeyword>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(final IdiomKeyword idiomKeyword) {
                if (idiomKeyword.getShowapi_res_code() == 0) {
                    if (idiomKeyword.getShowapi_res_body().getRet_code() == 0 || idiomKeyword.getShowapi_res_body().getRet_message().equals("Success")){
                        mBinding.recycleList.setVisibility(View.VISIBLE);
                        currentPage = page;
                        //Log.d(TAG, "currentPage:"+currentPage);
                        totalPage = idiomKeyword.getShowapi_res_body().getLast_page();
                        if (page==1){
                            Toast.makeText(mContext, "查询结果："+idiomKeyword.getShowapi_res_body().getTotal()+"个成语包含此关键字", Toast.LENGTH_SHORT).show();
                            mDataList = idiomKeyword.getShowapi_res_body().getData();
                            mAdapter = new IdiomsListAdapter(mDataList);
                            mBinding.recycleList.setLayoutManager(new LinearLayoutManager(mContext));
                            mBinding.recycleList.setAdapter(mAdapter);
                        }else{
                            mDataList.addAll(idiomKeyword.getShowapi_res_body().getData());
                            mAdapter.notifyDataSetChanged();
                        }
                    }else{
                        Toast.makeText(mContext, idiomKeyword.getShowapi_res_body().getRet_message(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(mContext, "showapi_res_code: " + idiomKeyword.getShowapi_res_code() + "请前往数据提供平台参照公共参数错误码", Toast.LENGTH_SHORT).show();
                }

                mAdapter.setOnItemClickListener(new IdiomsListAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Function.setTextToClipboard(mContext,mDataList.get(position).getTitle());
                    }
                });

            }
            @Override
            public void onError(Throwable e) {
                Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onError:"+e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        };
        SingletonRetrofit.getInstance().queryIdiomList(idiomKeywordObserver, Constant.ShowApi_AppId, Constant.ShowApi_Secret,
                mBinding.etInput.getText().toString().trim(),String.valueOf(page));

    }


}
