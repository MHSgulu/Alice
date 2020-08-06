package com.uw.alice.ui.navigationD;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.uw.alice.R;
import com.uw.alice.databinding.FragmentMyBinding;
import com.uw.alice.ui.navigationD.idiom.CorpusOfIdiomsActivity;
import com.uw.alice.ui.navigationD.model.TaoModelsActivity;
import com.uw.alice.ui.navigationD.wallpaper.WallpaperHorizontalPageActivity;
import com.uw.alice.ui.navigationD.wallpaper.BingWallpaperListActivity;
import com.uw.alice.ui.navigationD.wallpaper.WallpaperVerticalPageActivity;
import com.uw.alice.ui.test.TestWidgetActivity;

public class MyFragment extends Fragment implements View.OnClickListener{

    private FragmentMyBinding mBinding;
    private Context mContext;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        //设置系统状态栏颜色
        //Function.setSystemStatusBarColor(requireActivity(),R.color.colorNavigationD);
        requireActivity().getWindow().setStatusBarColor(requireActivity().getColor(R.color.colorNavigationD));
    }


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //myFragmentViewModel = ViewModelProviders.of(this).get(MyFragmentViewModel.class);
        //View root = inflater.inflate(R.layout.fragment_my, container, false);
        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_my,container,false);
        mBinding.setClickListener(this);

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
                CharSequence[] items = {"横向滑动浏览壁纸","垂直滑动浏览壁纸","列表滑动浏览壁纸"};
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(mContext);
                builder.setTitle("请选择浏览方式");
                builder.setItems(items, (dialog, which) -> {
                    switch (which) {
                        case 0:
                            startActivity(new Intent(mContext, WallpaperHorizontalPageActivity.class));
                            break;

                        case 1:
                            startActivity(new Intent(mContext, WallpaperVerticalPageActivity.class));
                            break;

                        case 2:
                            startActivity(new Intent(mContext, BingWallpaperListActivity.class));
                            break;
                    }
                });
                builder.create().show();
                break;

            case R.id.cv_intelligent_chat_robot:
                startActivity(new Intent(mContext, IntelligentChatRobotActivity.class));
                break;

            case R.id.card_view_testWidget:
                startActivity(new Intent(mContext, TestWidgetActivity.class));
                break;

        }
    }






}
