package com.uw.alice.ui.navigationD;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.uw.alice.R;
import com.uw.alice.data.model.TaoModelStyle;
import com.uw.alice.data.util.Util;
import com.uw.alice.databinding.ActivityTaoModelsBinding;
import com.uw.alice.network.retrofit.SingletonRetrofit;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class TaoModelsActivity extends AppCompatActivity {

    private static final String TAG = "InspirationalQuotations";
    private ActivityTaoModelsBinding mBinding;
    private Context mContext;
    private ArrayList<String> tab_title_list = new ArrayList<>();//存放标签页标题
    private ArrayList<Fragment> fragment_list = new ArrayList<>();//存放ViewPager下的Fragment
    private SectionsModelPagerAdapter pagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_tao_models);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_tao_models);
        mContext = TaoModelsActivity.this;

        mBinding.llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mBinding.tabs.setElevation(5);

        //初始化Tab
        initTab();

    }

    private void initTab() {

        Observer<TaoModelStyle> taoModelStyleObserver = new Observer<TaoModelStyle>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(TaoModelStyle taoModelStyle) {
                if (taoModelStyle.getShowapi_res_code() == 0) {
                    tab_title_list = (ArrayList<String>) taoModelStyle.getShowapi_res_body().getAllTypeList();
                    for (int i = 0; i < tab_title_list.size(); i++) {
                        mBinding.tabs.addTab(mBinding.tabs.newTab().setText(tab_title_list.get(i)));
                        fragment_list.add(TaoModelFragment.newInstance(i+1));
                        pagerAdapter = new SectionsModelPagerAdapter(getSupportFragmentManager(), tab_title_list, fragment_list);
                        mBinding.viewPager.setAdapter(pagerAdapter);
                        mBinding.tabs.setupWithViewPager(mBinding.viewPager);
                    }
                } else {
                    Toast.makeText(mContext, "showapi_res_code: " + taoModelStyle.getShowapi_res_code() + "请前往数据提供平台参照公共参数错误码", Toast.LENGTH_SHORT).show();
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
        SingletonRetrofit.getInstance().queryTaoModelStyle(taoModelStyleObserver, Util.ShowApi_AppId,Util.ShowApi_Secret);

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
