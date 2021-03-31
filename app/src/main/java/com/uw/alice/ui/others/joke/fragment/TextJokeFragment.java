package com.uw.alice.ui.others.joke.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.uw.alice.R;
import com.uw.alice.data.model.TextJoke;
import com.uw.alice.common.Constant;
import com.uw.alice.databinding.FragmentTextJokeBinding;
import com.uw.alice.common.network.retrofit.SingletonRetrofit;
import com.uw.alice.ui.others.joke.ItemTextJokeContentActivity;
import com.uw.alice.ui.others.joke.adapter.TextJokeAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class TextJokeFragment extends Fragment {

    private static final String TAG = "TextJokeFragment";
    private FragmentTextJokeBinding mBinding;
    private Context mContext;
    private TextJokeAdapter mAdapter;

    private List<TextJoke.ResultBean.ShowapiResBodyBean.ContentlistBean> mDataList = new ArrayList<>();
    private int nextPage = 2;  //下一页 标志 从第二页开始


    public static TextJokeFragment newInstance() {
        return new TextJokeFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //View root = inflater.inflate(R.layout.fragment_picture_joke, container, false);
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_text_joke,container,false);
        mContext = getContext();

        //请求第一页的文本列表数据
        getPictureJokeListData(1);

        initOnListener();

        return mBinding.getRoot();
    }


    /**
     * 初始化监听器
     */
    private void initOnListener() {

        mBinding.refreshLayout.setOnRefreshListener(refreshLayout -> {
            //请求第一页的文本列表数据
            getPictureJokeListData(1);
            refreshLayout.finishRefresh(2500);
            //下拉刷新，重置上拉加载的数据 使得用户在上拉加载达到上限时，重新浏览此页面的数据
            if (nextPage > 10){
                //重置 上拉加载变量标志
                nextPage = 2;
            }

        });

        mBinding.refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            if (nextPage <= 10){
                //请求第一页的文本列表数据
                getPictureJokeListData(nextPage);
                refreshLayout.finishLoadMore(1000);
            }else{
                final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle(R.string.AlertDialog_Tips_Title);
                builder.setMessage(R.string.AlertDialog_Tips_Content);
                builder.setPositiveButton(R.string.AlertDialog_Tips_Positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        builder.create().dismiss();
                    }
                });
                builder.create().show();
                refreshLayout.finishLoadMoreWithNoMoreData();
            }

        });

    }


    private void getPictureJokeListData(final int page) {

        Observer<TextJoke> textJokeObserver = new Observer<TextJoke>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(TextJoke textJoke) {
                if (textJoke.getCode().equals(Constant.QUERY_SUCCESS_CODE)){
                    if (textJoke.getResult().getShowapi_res_code()==0){
                        if (page==1){
                            //清空上拉加载存放的数据
                            mDataList.clear();
                            mDataList = textJoke.getResult().getShowapi_res_body().getContentlist();
                            mAdapter = new TextJokeAdapter(mDataList);
                            mBinding.recycleList.setLayoutManager(new LinearLayoutManager(mContext));
                            mBinding.recycleList.setAdapter(mAdapter);
                        }else{
                            mDataList.addAll(textJoke.getResult().getShowapi_res_body().getContentlist());
                            mAdapter.notifyDataSetChanged();
                            nextPage = page+1;
                        }
                    }else{
                        Toast.makeText(mContext, "showapi_res_code："+textJoke.getResult().getShowapi_res_code(), Toast.LENGTH_SHORT).show();
                    }
                }else if (textJoke.getCode().equals(Constant.ERROR_CODE_LIMIT)){
                    Toast.makeText(mContext, "笑话大全数据的调用次数超过每天限量3000次/天，请明天继续", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(mContext, "code："+textJoke.getCode()+"请前往数据提供平台参照公共参数错误码", Toast.LENGTH_SHORT).show();
                }

                mAdapter.setOnItemClickListener((view, position) -> {
                    Intent intent = new Intent(mContext, ItemTextJokeContentActivity.class);
                    intent.putExtra(Constant.TextJokeTitle,mDataList.get(position).getTitle());
                    intent.putExtra(Constant.TextJokeContent,mDataList.get(position).getText());
                    startActivity(intent);
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
        SingletonRetrofit.getInstance().getTextJoke(textJokeObserver, Constant.Time,page, Constant.MaxResult_String, Constant.Showapi_Sign, Constant.JDAPI_KEY);

    }

}
