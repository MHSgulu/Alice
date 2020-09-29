package com.uw.alice.ui.navigationD.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.uw.alice.R;
import com.uw.alice.data.util.Util;
import com.uw.alice.ui.navigationD.test.fragment.TestFragment1;


public class TestWidgetActivity extends AppCompatActivity {

    private static final String TAG = "TestWidgetActivity";
    private Context mContext;

    private LinearLayout llBack;
    private CardView cardView1, cardView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_widget);
        mContext = TestWidgetActivity.this;

        initBindViewId();
        initOnListener();
    }


    private void initBindViewId() {
        llBack = findViewById(R.id.ll_back);
        cardView1 = findViewById(R.id.card_1);
        cardView2 = findViewById(R.id.card_2);
    }

    private void initOnListener() {
        llBack.setOnClickListener(v -> finish());
        cardView1.setOnClickListener(v -> jumpToFragmentContainerActivity(1));
        cardView2.setOnClickListener(v -> jumpToFragmentContainerActivity(2));
    }


    /**
     * 跳转到 FragmentContainerActivity
     * @param tag 标志符
     */
    private void jumpToFragmentContainerActivity(int tag) {
        Intent intent = new Intent(mContext,FragmentContainerActivity.class);
        intent.putExtra(Util.ARG_Flag,tag);
        startActivity(intent);
    }


}
