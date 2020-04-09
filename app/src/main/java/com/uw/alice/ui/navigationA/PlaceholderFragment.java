package com.uw.alice.ui.navigationA;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.uw.alice.R;
import com.uw.alice.data.model.News;
import com.uw.alice.data.util.Util;
import com.uw.alice.databinding.FragmentPlaceholderBinding;
import com.uw.alice.network.retrofit.SingletonRetrofit;


import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;


/**
 * 包含视图的占位符片段。
 */
public class PlaceholderFragment extends Fragment {

    private static final String TAG = "PlaceholderFragment";
    private static final String ARG_SECTION_NUMBER = "section_number";
    private PageViewModel pageViewModel;
    private io.reactivex.Observer<News>  newsObserver;
    private Context mContext;
    private FragmentPlaceholderBinding mBinding;
    private int index = 0;
    private String channel;
    private List<News.ResultBeanX.ResultBean.ListBean> mDataList = new ArrayList<>();
    private NewsListAdapter mAdapter;

    static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // Log.d(TAG, "生命周期测试;"+"onCreate执行了");
        mContext = getContext();
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
            //Log.d(TAG, "index:"+index);
        }
        //pageViewModel.setIndex(index);

        /*pageViewModel.getNewsData();

        pageViewModel.getNews().observe(this, new Observer<News>() {
            @Override
            public void onChanged(News news) {
                mBinding.recycleList.setLayoutManager(new LinearLayoutManager(mContext));
                mBinding.recycleList.setAdapter(new NewsListAdapter(news.getResult().getResult().getList()));
            }
        });*/

        if (index==1){
            //channel = "头条";
            channel = getString(R.string.tab_title_1);
            //channel = getResources().getString(R.string.tab_title_1);
           // Log.d(TAG, "Test1:"+getString(R.string.tab_title_1));
            //Log.d(TAG, "Test2:"+getResources().getString(R.string.tab_title_1));
        }else if (index==2){
            channel = getString(R.string.tab_title_2);
        }else if (index==3){
            channel = getString(R.string.tab_title_3);
        }else if (index==4){
            channel = getString(R.string.tab_title_4);
        }else if (index==5){
            channel = getString(R.string.tab_title_5);
        }
        else if (index==6){
            channel = getString(R.string.tab_title_6);
        }else if (index==7){
            channel = getString(R.string.tab_title_7);
        }else if (index==8){
            channel = getString(R.string.tab_title_8);
        }else if (index==9){
            channel = getString(R.string.tab_title_9);
        } else if (index==10)
        {
            channel = getString(R.string.tab_title_10);
        }else if (index==11){
            channel = getString(R.string.tab_title_11);
        }else if (index==12){
            channel = getString(R.string.tab_title_12);
        }else if (index==13){
            channel = getString(R.string.tab_title_13);
        }else if (index==14){
            channel = getString(R.string.tab_title_14);
        }else if (index==15)
        {
            channel = getString(R.string.tab_title_15);
        }else if (index==16){
            channel = getString(R.string.tab_title_16);
        }else if (index==17){
            channel = getString(R.string.tab_title_17);
        }


        //获取新闻列表数据
        getNewsListData(10,0);


    }

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_placeholder, container, false);
        //View root = inflater.inflate(R.layout.fragment_placeholder, container, false);

        //下拉刷新监听
        mBinding.refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                //获取全部新闻列表数据
                getNewsListData(40,0);
                refreshLayout.finishRefresh(2500,true,true);
                //refreshLayout.autoRefreshAnimationOnly();
                //mAdapter.notifyDataSetChanged();
            }
        });

        //上拉加载监听
        mBinding.refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                //获取剩余新闻列表数据
                getNewsListData(30,10);
                refreshLayout.finishLoadMore(2500,true,true);
                //refreshLayout.finishLoadMoreWithNoMoreData();
            }
        });



        return mBinding.getRoot();
    }

    private void getNewsListData(int num, final int start) {

        //发送网络请求数据
        newsObserver = new io.reactivex.Observer<News>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(final News news) {
                if (news.getCode().equals(Util.QUERY_SUCCESS_CODE)) {
                    if (start==0){
                        mDataList = news.getResult().getResult().getList();
                        mAdapter = new NewsListAdapter(mDataList);
                        mBinding.recycleList.setLayoutManager(new LinearLayoutManager(mContext));
                        mBinding.recycleList.setAdapter(mAdapter);
                    }else{
                        mDataList.addAll(news.getResult().getResult().getList());
                        mAdapter.notifyDataSetChanged();
                    }

                    mAdapter.setOnItemClickListener(new NewsListAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            Intent intent = new Intent(mContext,ItemNewsDetailActivity.class);
                            intent.putExtra(Util.NewsTitle,mDataList.get(position).getTitle());
                            intent.putExtra(Util.NewsSrc,mDataList.get(position).getSrc());
                            intent.putExtra(Util.NewsTime,mDataList.get(position).getTime());
                            intent.putExtra(Util.NewsContent,mDataList.get(position).getContent());
                            startActivity(intent);
                        }
                    });

                }
                else if (news.getCode().equals(Util.ERROR_CODE_LIMIT)){
                    Toast.makeText(mContext, "新闻数据的调用次数超过每天限量1000次/天，请明天继续", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(mContext, "code："+news.getCode()+"请前往数据提供平台参照公共参数错误码", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onError:" + e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        };
        SingletonRetrofit.getInstance().getNews(newsObserver,channel,String.valueOf(num),String.valueOf(start),Util.JDAPI_KEY);


    }


}