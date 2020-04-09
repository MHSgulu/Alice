package com.uw.alice.ui.navigationD;

import android.content.Context;
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
import com.uw.alice.data.model.MobilePhone;
import com.uw.alice.data.util.Util;
import com.uw.alice.databinding.ActivityQueryPhoneNumberBinding;
import com.uw.alice.network.retrofit.SingletonRetrofit;
import com.uw.alice.ui.navigationA.NewsSearchListAdapter;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class QueryPhoneNumberActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "QueryPhoneNumberActivit";
    private ActivityQueryPhoneNumberBinding mBinding;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = QueryPhoneNumberActivity.this;
        //setContentView(R.layout.activity_item_news_detail);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_query_phone_number);
        mBinding.setOnClickListener(this);


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
                if (TextUtils.isEmpty(mBinding.etInput.getText().toString().trim())){
                    Toast.makeText(mContext, "手机号码为空", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    //查询手机号码归属地
                    queryPhoneNumber();
                }
                break;

        }
    }

    private void queryPhoneNumber() {

        Observer<MobilePhone> mobilePhoneObserver = new Observer<MobilePhone>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(MobilePhone mobilePhone) {
                if (mobilePhone.getCode().equals(Util.QUERY_SUCCESS_CODE)){
                    //mBinding.cvContent.setVisibility(View.VISIBLE);
                    mBinding.tvPhoneNUmber.setText(mobilePhone.getResult().getResult().getShouji());
                    mBinding.tvPlace.setText(String.format("%s %s", mobilePhone.getResult().getResult().getProvince(),
                            mobilePhone.getResult().getResult().getCity()));
                    mBinding.tvOperator.setText(mobilePhone.getResult().getResult().getCompany());
                    mBinding.tvCardType.setText(mobilePhone.getResult().getResult().getCardtype());
                    mBinding.tvAreaCode.setText(mobilePhone.getResult().getResult().getAreacode());
                }else if (mobilePhone.getCode().equals(Util.ERROR_CODE_LIMIT)){
                    Toast.makeText(mContext, "查询手机号码归属地数据的调用次数超过每天限量1000次/天，请明天继续", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(mContext, "code："+mobilePhone.getCode()+"请前往数据提供平台参照公共参数错误码", Toast.LENGTH_SHORT).show();
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
        SingletonRetrofit.getInstance().queryMobilePhoneNumberHome(mobilePhoneObserver,mBinding.etInput.getText().toString(), Util.JDAPI_KEY);

    }


}
