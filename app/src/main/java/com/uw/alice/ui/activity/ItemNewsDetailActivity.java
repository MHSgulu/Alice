package com.uw.alice.ui.activity;


import android.annotation.SuppressLint;
import android.os.Bundle;
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
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_item_news_detail);
        mBinding.setOnClickListener(this);

        if (getIntent() != null) {
            mBinding.tvTitle.setText(getIntent().getStringExtra(Util.NewsTitle));
            mBinding.tvFrom.setText(getIntent().getStringExtra(Util.NewsSrc));
            mBinding.tvTime.setText(getIntent().getStringExtra(Util.NewsTime));
            mBinding.tvContent.setHtml(Objects.requireNonNull(getIntent().getStringExtra(Util.NewsContent)), new HtmlHttpImageGetter(mBinding.tvContent));
        }


    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
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
