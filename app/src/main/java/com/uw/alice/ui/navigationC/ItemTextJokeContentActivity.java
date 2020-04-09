package com.uw.alice.ui.navigationC;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.uw.alice.R;
import com.uw.alice.data.util.Util;

public class ItemTextJokeContentActivity extends AppCompatActivity  {

    private static final String TAG = "ItemNewsDetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_text_joke_content);

        final LinearLayout llBack = findViewById(R.id.ll_back);
        llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final TextView tvTitle = findViewById(R.id.tv_title);
        tvTitle.setText(getIntent().getStringExtra(Util.TextJokeTitle));

        final TextView tvContent = findViewById(R.id.tv_content);
        tvContent.setText(getIntent().getStringExtra(Util.TextJokeContent));

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
