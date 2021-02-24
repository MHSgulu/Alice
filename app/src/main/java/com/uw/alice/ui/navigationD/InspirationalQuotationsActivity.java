package com.uw.alice.ui.navigationD;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.uw.alice.R;
import com.uw.alice.common.Widget;
import com.uw.alice.data.model.Quotations;
import com.uw.alice.common.Constant;
import com.uw.alice.databinding.ActivityInspirationalQuotationsBinding;
import com.uw.alice.network.retrofit.SingletonRetrofit;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class InspirationalQuotationsActivity extends AppCompatActivity {

    private static final String TAG = "InspirationalQuotations";
    private Context mContext;
    private ActivityInspirationalQuotationsBinding mBinding;
    //private QuotationsListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_inspirational_quotations);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_inspirational_quotations);
        mContext = InspirationalQuotationsActivity.this;


        Widget.showMaterialAlertDialog(mContext,"提示","点击英语短句卡片即可控制显隐中文翻译，长按即可选择复制英文短句或中文翻译至谷歌翻译查询。");


        //Come On!发起网络请求
        queryQuotations();


        mBinding.llBack.setOnClickListener(v -> finish());

        //简单测试  头部刷新标题不支持中文
        /*
          using a string, support: A-Z 0-9 - .     使用字符串, 支持A-Z, 0-7 以及 - .
          you can add more letters by {@link in.srain.cube.views.ptr.header.StoreHousePath#addChar}
         */
        mBinding.refreshStoreHouseHeader.initWithString(getResources().getString(R.string.refreshStoreHouseHeaderTitle));

        mBinding.refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(1000);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        queryQuotations();
                    }
                },1000);

            }
        });

    }

    private void queryQuotations() {

        Observer<Quotations> quotationsObserver = new Observer<Quotations>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Quotations quotations) {
                if (quotations.getShowapi_res_code()==0){
                    mBinding.recycleList.setLayoutManager(new LinearLayoutManager(mContext));
                    mBinding.recycleList.setAdapter(new QuotationsListAdapter(quotations.getShowapi_res_body().getData()));
                }else{
                    Toast.makeText(mContext, "showapi_res_code: "+quotations.getShowapi_res_code()+"请前往数据提供平台参照公共参数错误码", Toast.LENGTH_SHORT).show();
                }

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
        SingletonRetrofit.getInstance().queryEnglishQuotations(quotationsObserver, Constant.ShowApi_AppId, Constant.ShowApi_Secret, Constant.Max_Count);

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
