package com.uw.alice.ui.navigationC;

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
import com.uw.alice.data.model.PictureJoke;
import com.uw.alice.data.util.Util;
import com.uw.alice.databinding.FragmentPictureJokeBinding;
import com.uw.alice.network.retrofit.SingletonRetrofit;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import cc.shinichi.library.ImagePreview;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class PictureJokeFragment extends Fragment {


    private static final String TAG = "PictureJokeFragment";
    private FragmentPictureJokeBinding mBinding;
    private Context mContext;
    private List<PictureJoke.ResultBean.ShowapiResBodyBean.ContentlistBean> mDataList = new ArrayList<>();
    private PictureJokeAdapter mAdapter;
    //下一页 标志 从第二页开始
    private int nextPage = 2;


    static PictureJokeFragment newInstance() {
        return new PictureJokeFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //View root = inflater.inflate(R.layout.fragment_picture_joke, container, false);
        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_picture_joke,container,false);
        mContext = getContext();

        //请求第一页的图片列表数据
        getPictureJokeListData(1);

        mBinding.refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                //请求第一页的图片列表数据
                getPictureJokeListData(1);
                refreshLayout.finishRefresh(2500);
                //下拉刷新，重置上拉加载的数据 使得用户在上拉加载达到上限时，重新浏览此页面的数据
                if (nextPage > 10){
                    //重置 上拉加载变量标志
                    nextPage = 2;
                }

            }
        });

        mBinding.refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (nextPage <= 10){
                    //请求下一页的动态图列表数据
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

            }
        });



        return mBinding.getRoot();
    }



    private void getPictureJokeListData(final int page) {

        Observer<PictureJoke> pictureJokeObserver = new Observer<PictureJoke>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(PictureJoke pictureJoke) {
                if (pictureJoke.getCode().equals(Util.QUERY_SUCCESS_CODE)){
                    if (pictureJoke.getResult().getShowapi_res_code()==0){
                        if (page==1){
                            //清空上拉加载存放的数据
                            mDataList.clear();
                            mDataList = pictureJoke.getResult().getShowapi_res_body().getContentlist();
                            mAdapter = new PictureJokeAdapter(mDataList);
                            mBinding.recycleList.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                            mBinding.recycleList.setAdapter(mAdapter);
                        }else{
                            mDataList.addAll(pictureJoke.getResult().getShowapi_res_body().getContentlist());
                            mAdapter.notifyDataSetChanged();
                            nextPage = page+1;
                        }
                    }else{
                        Toast.makeText(mContext, "showapi_res_code："+pictureJoke.getResult().getShowapi_res_code(), Toast.LENGTH_SHORT).show();
                    }
                }else if (pictureJoke.getCode().equals(Util.ERROR_CODE_LIMIT)){
                    Toast.makeText(mContext, "笑话大全数据的调用次数超过每天限量3000次/天，请明天继续", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(mContext, "code："+pictureJoke.getCode()+"请前往数据提供平台参照公共参数错误码", Toast.LENGTH_SHORT).show();
                }


                mAdapter.setOnItemClickListener(new PictureJokeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        ImagePreview.getInstance()
                                .setContext(Objects.requireNonNull(getActivity()))
                                .setIndex(0)
                                .setImage(mDataList.get(position).getImg()).start();
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
        SingletonRetrofit.getInstance().getPictureJoke(pictureJokeObserver,Util.Time,page,Util.MaxResult,Util.JDAPI_KEY);

    }



}
