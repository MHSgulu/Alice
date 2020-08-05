package com.uw.alice.ui.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.uw.alice.MainActivity;
import com.uw.alice.R;
import com.uw.alice.common.Function;



/**
 *  Module:   SplashActivity
 *  Function: 闪屏页面，只是显示一张图
 *  Note：
 */
public class SplashActivity extends AppCompatActivity {

    private static final String TAG = "SplashActivity";
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_splash);
        mContext = SplashActivity.this;
        //Log.i(TAG, "数据点位： view: " + view)

        if (!isTaskRoot() && getIntent().hasCategory(Intent.CATEGORY_LAUNCHER) && getIntent().getAction() != null && getIntent().getAction().equals(Intent.ACTION_MAIN)) {
            finish();
            return;
        }

        Function.getDisplayMetrics(mContext);

        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                jumpToHomeActivity();
            }
        }, 2000);


    }


    /**
     *  跳转到主界面
     */
    private void jumpToHomeActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }




}
