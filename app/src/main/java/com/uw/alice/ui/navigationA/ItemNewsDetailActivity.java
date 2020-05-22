package com.uw.alice.ui.navigationA;


import android.os.Bundle;
import android.text.Html;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.uw.alice.R;
import com.uw.alice.data.util.Util;
import com.uw.alice.databinding.ActivityItemNewsDetailBinding;

import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;

import java.util.Objects;


public class ItemNewsDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "ItemNewsDetailActivity";
    private ActivityItemNewsDetailBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_item_news_detail);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_item_news_detail);
        mBinding.setOnClickListener(this);

        /*Log.d(TAG, "onCreate:"+getIntent().getStringExtra(Util.NewsTitle));
        Log.d(TAG, "onCreate:"+getIntent().getStringExtra(Util.NewsSrc));
        Log.d(TAG, "onCreate:"+getIntent().getStringExtra(Util.NewsTime));
        Log.d(TAG, "onCreate:"+getIntent().getStringExtra(Util.NewsContent));*/

        mBinding.tvTitle.setText(getIntent().getStringExtra(Util.NewsTitle));
        mBinding.tvFrom.setText(getIntent().getStringExtra(Util.NewsSrc));
        mBinding.tvTime.setText(getIntent().getStringExtra(Util.NewsTime));
        mBinding.tvContent.setHtml(Objects.requireNonNull(getIntent().getStringExtra(Util.NewsContent)),new HtmlHttpImageGetter(mBinding.tvContent));
    }



    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){

            case R.id.ll_back: //返回
                finish();
                break;

        }
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
