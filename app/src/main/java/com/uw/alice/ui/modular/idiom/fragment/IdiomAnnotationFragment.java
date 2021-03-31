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

import com.uw.alice.R;
import com.uw.alice.data.model.Idiom;
import com.uw.alice.common.Constant;
import com.uw.alice.databinding.FragmentIdiomAnnotationBinding;
import com.uw.alice.common.network.retrofit.SingletonRetrofit;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class IdiomAnnotationFragment extends Fragment implements View.OnClickListener{

    private static final String TAG = "IdiomAnnotationFragment";
    private Context mContext;
    private FragmentIdiomAnnotationBinding mBinding;

    public static IdiomAnnotationFragment newInstance() {
        return new IdiomAnnotationFragment();
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //View root = inflater.inflate(R.layout.fragment_dynamic_gif, container, false);
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_idiom_annotation, container, false);
        mBinding.setOnClickListener(this);
        mContext = getContext();


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
                mBinding.cvContent.setVisibility(View.GONE);
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
                    Toast.makeText(mContext, "完整输入要查询的成语,不得有非中文字符。", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    //查询成语注释
                    queryIdiomAnnotation();
                }
                break;

        }
    }

    private void queryIdiomAnnotation() {

        Observer<Idiom> idiomObserver = new Observer<Idiom>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Idiom idiom) {
                if (idiom.getShowapi_res_code() == 0) {
                    if (idiom.getShowapi_res_body().getRet_code()==0 || idiom.getShowapi_res_body().getRet_message().equals("Success")){
                        mBinding.cvContent.setVisibility(View.VISIBLE);
                        mBinding.tvTitle.setText(idiom.getShowapi_res_body().getData().getTitle());
                        mBinding.tvSpell.setText(idiom.getShowapi_res_body().getData().getSpell());
                        mBinding.tvContent.setText(idiom.getShowapi_res_body().getData().getContent());
                        mBinding.tvDerivation.setText(idiom.getShowapi_res_body().getData().getDerivation());
                        mBinding.tvSamples.setText(idiom.getShowapi_res_body().getData().getSamples());
                    }else{
                        Toast.makeText(mContext,idiom.getShowapi_res_body().getRet_message(),Toast.LENGTH_SHORT).show();
                    }

                }
                else {
                    Toast.makeText(mContext, "showapi_res_code: " + idiom.getShowapi_res_code() + "请前往数据提供平台参照公共参数错误码", Toast.LENGTH_SHORT).show();
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
        SingletonRetrofit.getInstance().queryIdiomAnnotation(idiomObserver, Constant.ShowApi_AppId, Constant.ShowApi_Secret,mBinding.
                etInput.getText().toString().trim());

    }


}
