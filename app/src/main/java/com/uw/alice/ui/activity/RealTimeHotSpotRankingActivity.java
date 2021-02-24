package com.uw.alice.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.uw.alice.R;
import com.uw.alice.data.model.HotSpot;
import com.uw.alice.data.util.Util;
import com.uw.alice.databinding.ActivityRealTimeHotSpotRankingBinding;
import com.uw.alice.network.retrofit.SingletonRetrofit;
import com.uw.alice.ui.adapter.RealTimeHotSpotRankingAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RealTimeHotSpotRankingActivity extends AppCompatActivity {

    private static final String TAG = "RealTimeHotSpotActivity";
    private ActivityRealTimeHotSpotRankingBinding mBinding;
    private Context mContext;

    private List<HotSpot.ResultBean.ShowapiResBodyBean.ListBean> mDataList = new ArrayList<>();
    private RealTimeHotSpotRankingAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_real_time_hot_spot_ranking);
        mContext = RealTimeHotSpotRankingActivity.this;
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_real_time_hot_spot_ranking);
        //mBinding.setOnClickListener(this);

        mBinding.llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //热词排行
        queryHotSpotRankingList();



    }

    private void queryHotSpotRankingList() {

        Observer<HotSpot> hotSpotObserver = new Observer<HotSpot>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(final HotSpot hotSpot) {
                if (hotSpot.getCode().equals(Util.QUERY_SUCCESS_CODE)||hotSpot.getMsg().equals("查询成功")){
                    mDataList = hotSpot.getResult().getShowapi_res_body().getList();
                    mAdapter = new RealTimeHotSpotRankingAdapter(mDataList);
                    mBinding.hotSpotList.setLayoutManager(new LinearLayoutManager(mContext));
                    mBinding.hotSpotList.setAdapter(mAdapter);
                }else if (hotSpot.getCode().equals(Util.ERROR_CODE_LIMIT)){
                    Toast.makeText(mContext, "实时热点数据的调用次数超过每天限量3000次/天，请明天继续", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(mContext, "code："+hotSpot.getCode()+"请前往数据提供平台参照公共参数错误码", Toast.LENGTH_SHORT).show();
                }

                mAdapter.setOnItemClickListener((view, position) -> {
                    Intent intent = new Intent(mContext, SearchNewsActivity.class);
                    intent.putExtra(Util.HotWordName,mDataList.get(position).getName());
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
        SingletonRetrofit.getInstance().getHotSpotRanking(hotSpotObserver,"1", Util.JDAPI_KEY);




    }
}
