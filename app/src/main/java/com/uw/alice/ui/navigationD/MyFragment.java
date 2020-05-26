package com.uw.alice.ui.navigationD;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.uw.alice.R;
import com.uw.alice.common.Function;
import com.uw.alice.data.util.Util;
import com.uw.alice.databinding.FragmentMyBinding;

public class MyFragment extends Fragment implements View.OnClickListener{

    private MyFragmentViewModel myFragmentViewModel;
    private FragmentMyBinding mBinding;
    private Context mContext;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        //设置系统状态栏颜色
        Function.setSystemStatusBarColor(requireActivity(),R.color.colorNavigationD);
    }


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //myFragmentViewModel = ViewModelProviders.of(this).get(MyFragmentViewModel.class);
        //View root = inflater.inflate(R.layout.fragment_my, container, false);
        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_my,container,false);
        mBinding.setClicklistener(this);

        //final TextView textView = root.findViewById(R.id.text_home);
       /* myFragmentViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        return mBinding.getRoot();
    }



    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){

            case R.id.cv_queryMobilePhoneNumberHome:
                startActivity(new Intent(mContext,QueryPhoneNumberActivity.class));
                break;

            case R.id.cv_inspirationalQuotations:
                startActivity(new Intent(mContext,InspirationalQuotationsActivity.class));
                break;

            case R.id.cv_queryTaoModels:
                startActivity(new Intent(mContext, TaoModelsActivity.class));
                break;

            case R.id.cv_corpus_of_idioms:
                startActivity(new Intent(mContext, CorpusOfIdiomsActivity.class));
                break;

            case R.id.cv_dailyWallpaper:
                startActivity(new Intent(mContext, BingDailyWallpaperActivity.class));
                break;

            case R.id.cv_intelligent_chat_robot:
                startActivity(new Intent(mContext, IntelligentChatRobotActivity.class));
                break;

        }
    }






}
