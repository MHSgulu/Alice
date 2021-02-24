package com.uw.alice.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.uw.alice.R;
import com.uw.alice.data.model.NewsSearch;
import com.uw.alice.data.util.Util;
import com.uw.alice.databinding.ActivitySearchNewsBinding;
import com.uw.alice.network.retrofit.SingletonRetrofit;
import com.uw.alice.ui.adapter.NewsSearchListAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SearchNewsActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "SearchNewsActivity";
    private ActivitySearchNewsBinding mBinding;
    private Context mContext;

    private List<NewsSearch.ResultBeanX.ResultBean.ListBean> mDataList = new ArrayList<>();
    private NewsSearchListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = SearchNewsActivity.this;
        //setContentView(R.layout.activity_item_news_detail);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_search_news);
        mBinding.setOnClickListener(this);

        String string = getIntent().getStringExtra(Util.HotWordName);
        if (string != null) {
            mBinding.etInput.setText(string);
            mBinding.llSearch.setEnabled(false);
            mBinding.progressBar.setVisibility(View.VISIBLE);
            Toast.makeText(mContext, "已自动搜索，请稍等片刻", Toast.LENGTH_SHORT).show();
            //搜索新闻
            searchNews();
        }


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
                if (s.toString().isEmpty()){
                    mBinding.ivReset.setVisibility(View.GONE);
                }else{
                    mBinding.ivReset.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length()==8){
                    Toast.makeText(mContext, "关键词的最大长度为8个字符", Toast.LENGTH_SHORT).show();
                }
                if (s.toString().isEmpty()){
                    mBinding.ivReset.setVisibility(View.GONE);
                }else{
                    mBinding.ivReset.setVisibility(View.VISIBLE);
                }
            }
        });


    }



    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){

            case R.id.ll_back: //返回
                finish();
                break;

            case R.id.iv_reset:
                mBinding.etInput.setText("");
                break;

            case R.id.ll_search:
                //trim()返回一个无空格字符的字符串
                //返回一个字符串，其值为该字符串，任何前导和尾随为白色 已删除空格，或者如果此字符串没有前导或 尾随空白。
                if (TextUtils.isEmpty(mBinding.etInput.getText().toString().trim())){
                    Toast.makeText(mContext, "关键词为空", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    //进度条与数据列表的显示与隐藏，搜索是否 可以点击 进行UI上的细节优化，增强用户体验，
                    mBinding.llSearch.setEnabled(false);
                    mBinding.searchList.setVisibility(View.GONE);
                    mBinding.progressBar.setVisibility(View.VISIBLE);
                    //Toast.makeText(mContext, "该数据源加载缓慢，点击之后后请耐心等待,若等待超时，请再次尝试", Toast.LENGTH_SHORT).show();
                    //搜索新闻
                    searchNews();
                }
                break;

        }
    }

    private void searchNews() {

        Observer<NewsSearch> newsSearchObserver = new Observer<NewsSearch>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(NewsSearch newsSearch) {
                if (newsSearch.getCode().equals(Util.QUERY_SUCCESS_CODE)||newsSearch.getMsg().equals("查询成功")){
                   //进度条与数据列表的显示与隐藏，搜索是否 可以点击 进行UI上的细节优化，增强体验，
                    mBinding.llSearch.setEnabled(true);
                    mBinding.progressBar.setVisibility(View.GONE);
                    mBinding.searchList.setVisibility(View.VISIBLE);
                    Toast.makeText(mContext, "已搜索到"+newsSearch.getResult().getResult().getNum()+"条关键词新闻", Toast.LENGTH_SHORT).show();
                    mDataList.clear();
                    mDataList = newsSearch.getResult().getResult().getList();
                    mAdapter = new NewsSearchListAdapter(mDataList);
                    mBinding.searchList.setLayoutManager(new LinearLayoutManager(mContext));
                    mBinding.searchList.setAdapter(mAdapter);
                }else if (newsSearch.getCode().equals(Util.ERROR_CODE_LIMIT)){
                    Toast.makeText(mContext, "新闻数据的调用次数超过每天限量1000次/天，请明天继续", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(mContext, "code："+newsSearch.getCode()+"请前往数据提供平台参照公共参数错误码", Toast.LENGTH_SHORT).show();
                }


                mAdapter.setOnItemClickListener(new NewsSearchListAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                       // Toast.makeText(mContext, "当前position："+position, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(mContext, ItemNewsDetailActivity.class);
                        intent.putExtra(Util.NewsTitle,mDataList.get(position).getTitle());
                        intent.putExtra(Util.NewsSrc,mDataList.get(position).getSrc());
                        intent.putExtra(Util.NewsTime,mDataList.get(position).getTime());
                        intent.putExtra(Util.NewsContent,mDataList.get(position).getContent());
                        startActivity(intent);
                    }
                });


            }

            @Override
            public void onError(Throwable e) {
                mBinding.llSearch.setEnabled(true);
                mBinding.progressBar.setVisibility(View.GONE);
                Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onError:"+e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        };
        SingletonRetrofit.getInstance().getSearchNews(newsSearchObserver,mBinding.etInput.getText().toString(),Util.JDAPI_KEY);

    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onStop() {
        super.onStop();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }



}
