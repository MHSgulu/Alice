package com.uw.alice.ui.navigationD.joke;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.uw.alice.R;
import com.uw.alice.common.Constant;

public class ItemTextJokeContentActivity extends AppCompatActivity  {

    //private static final String TAG = "ItemNewsDetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_text_joke_content);

        final LinearLayout llBack = findViewById(R.id.ll_back);
        llBack.setOnClickListener(v -> finish());
        final TextView tvTitle = findViewById(R.id.tv_title);
        final TextView tvContent = findViewById(R.id.tv_content);

        if (getIntent() != null){
            tvTitle.setText(getIntent().getStringExtra(Constant.TextJokeTitle));
            tvContent.setText(getIntent().getStringExtra(Constant.TextJokeContent));
        }


    }

}
