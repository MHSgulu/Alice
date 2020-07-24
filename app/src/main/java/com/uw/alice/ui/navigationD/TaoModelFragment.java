package com.uw.alice.ui.navigationD;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import com.uw.alice.R;
import com.uw.alice.data.model.TaoGirls;
import com.uw.alice.data.util.Util;

import com.uw.alice.databinding.FragmentTaoModelBinding;
import com.uw.alice.network.retrofit.SingletonRetrofit;


import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * 包含视图的占位符片段。
 */
public class TaoModelFragment extends Fragment implements View.OnClickListener{

    private static final String TAG = "TaoModelFragment";
    private static final String ARG_PAGE_INDEX = "section_number";
    private Context mContext;
    private FragmentTaoModelBinding mBinding;
    private int index = 0;
    private String style;
    private List<TaoGirls.ShowapiResBodyBean.PagebeanBean.ContentlistBean> mDataList = new ArrayList<>();
    private TaoModelListAdapter mAdapter;
    //下一页 标志 从第二页开始
    private int nextPage = 2;
    //数据当前页数
    private int currentPage = 0;
    //数据总页数
    private int totalPage = 0;


    static TaoModelFragment newInstance(int index) {
        TaoModelFragment fragment = new TaoModelFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_PAGE_INDEX, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_PAGE_INDEX);
        }
     

        if (index==1){
            style = getString(R.string.style_tab_title_1);
        }else if (index==2){
            style = getString(R.string.style_tab_title_2);
        }else if (index==3){
            style = getString(R.string.style_tab_title_3);
        }else if (index==4){
            style = getString(R.string.style_tab_title_4);
        }else if (index==5){
            style = getString(R.string.style_tab_title_5);
        }else if (index==6){
            style = getString(R.string.style_tab_title_6);
        }else if (index==7){
            style = getString(R.string.style_tab_title_7);
        }else if (index==8){
            style = getString(R.string.style_tab_title_8);
        }else if (index==9){
            style = getString(R.string.style_tab_title_9);
        }else if (index==10) {
            style = getString(R.string.style_tab_title_10);
        }else if (index==11){
            style = getString(R.string.style_tab_title_11);
        }else if (index==12){
            style = getString(R.string.style_tab_title_12);
        }else if (index==13){
            style = getString(R.string.style_tab_title_13);
        }else if (index==14){
            style = getString(R.string.style_tab_title_14);
        }else if (index==15) {
            style = getString(R.string.style_tab_title_15);
        }else if (index==16){
            style = getString(R.string.style_tab_title_16);
        }else if (index==17){
            style = getString(R.string.style_tab_title_17);
        }else if (index==18){
            style = getString(R.string.style_tab_title_18);
        }

         //获取数据
         getTaoModelDataList(1);


    }



    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
       mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_tao_model, container, false);
       //View root = inflater.inflate(R.layout.fragment_tao_model, container, false);

        //下拉刷新监听
        mBinding.refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(1500);
            }
        });

        //上拉加载监听
        mBinding.refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (nextPage <= totalPage){
                    if (nextPage <= 20){
                        getTaoModelDataList(nextPage);
                        refreshLayout.finishLoadMore(1500);
                    }
                    else{
                        final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        builder.setTitle(R.string.AlertDialog_Tips_Title);
                        builder.setMessage(R.string.AlertDialog_Tips_Content_1);
                        builder.setPositiveButton(R.string.AlertDialog_Tips_Positive, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                builder.create().dismiss();
                            }
                        });
                        builder.create().show();
                        refreshLayout.finishLoadMoreWithNoMoreData();
                    }
                }else{
                    refreshLayout.finishLoadMoreWithNoMoreData();
                    //最后页最后数据缺少字段 导致崩溃  给与提醒
                    final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setTitle(R.string.AlertDialog_Tips_Title);
                    builder.setMessage(R.string.AlertDialog_Tips_ErrorPrompt);
                    builder.setPositiveButton(R.string.AlertDialog_Tips_Understood, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            builder.create().dismiss();
                        }
                    });
                    builder.create().show();
                }

            }
        });



        return mBinding.getRoot();
    }

    private void getTaoModelDataList(final int page) {

        Observer<TaoGirls> taoGirlsObserver = new Observer<TaoGirls>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(TaoGirls taoGirls) {
                if (taoGirls.getShowapi_res_code() == 0) {
                    //currentPage = taoGirls.getShowapi_res_body().getPagebean().getCurrentPage();
                    totalPage = taoGirls.getShowapi_res_body().getPagebean().getAllPages();
                  if (page==1){
                      mDataList = taoGirls.getShowapi_res_body().getPagebean().getContentlist();
                      mAdapter = new TaoModelListAdapter(mDataList);
                      mBinding.recycleList.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                      mBinding.recycleList.setAdapter(mAdapter);
                  }else{
                      mDataList.addAll(taoGirls.getShowapi_res_body().getPagebean().getContentlist());
                      mAdapter.notifyDataSetChanged();
                      nextPage = page+1;
                  }
                } else {
                    Toast.makeText(mContext, "showapi_res_code: " + taoGirls.getShowapi_res_code() + "请前往数据提供平台参照公共参数错误码", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
                //报错信息效果一致
                Log.e(TAG, "onError1:"+ e.getMessage());
                Log.e(TAG, "onError5:"+ e.getCause());
                Log.e(TAG, "onError6:"+ e.getLocalizedMessage());
                //报错信息效果一致
                Log.e(TAG, "onError2:"+ e.toString());
                Log.e(TAG, "onError4:"+ e.fillInStackTrace());
            }

            @Override
            public void onComplete() {

            }
        };
        SingletonRetrofit.getInstance().queryTaoModelDataList(taoGirlsObserver,Util.ShowApi_TestAppId,Util.ShowApi_TestSecret,style,String.valueOf(page));

    }


    @Override
    public void onClick(View v) {

    }



}