package com.uw.alice.ui.navigationB;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.MaterialToolbar;
import com.uw.alice.R;
import com.uw.alice.data.model.FilmMaker;
import com.uw.alice.data.util.Util;
import com.uw.alice.network.retrofit.SingletonRetrofit;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class FilmmakerDetailsActivity extends AppCompatActivity {

    private Context mContext;
    private static final String TAG = "FilmmakerDetailsActivity";

    private MaterialToolbar toolbar;
    private ImageView ivCover;

    private TextView tvBirthday,tvConstellation,tvBirthplace,tvChineseName,tvForeignName,tvBriefIntroduction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filmmaker_details);
        mContext = FilmmakerDetailsActivity.this;
        toolbar = findViewById(R.id.mdToolbar);
        ivCover = findViewById(R.id.iv_cover);
        tvBirthday = findViewById(R.id.tv_birthday);
        tvConstellation = findViewById(R.id.tv_constellation);
        tvBirthplace = findViewById(R.id.tv_birthplace);
        tvChineseName = findViewById(R.id.tv_chinese_name);
        tvForeignName = findViewById(R.id.tv_foreign_name);
        tvBriefIntroduction = findViewById(R.id.tv_brief_introduction);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(mContext, "返回", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


        if (getIntent() != null){
            //Log.d(TAG,"actorId: "+ actorId);
            //String cover = getIntent().getStringExtra("actorCover");
            //Log.d(TAG,"cover: "+ cover);
            String actorId = getIntent().getStringExtra("actorId");
            Glide.with(mContext).load(getIntent().getStringExtra("actorCover")).into(ivCover);
            fetchActorDetails(actorId);
        }


        //设置系统状态半透明
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

    }

    private void fetchActorDetails(String id) {

        Observer<FilmMaker> filmMakerObserver = new Observer<FilmMaker>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(FilmMaker filmMaker) {
                //Toast.makeText(mContext, "S!", Toast.LENGTH_SHORT).show();
                toolbar.setTitle(filmMaker.getName());
                tvBirthday.setText(filmMaker.getBirthday());
                tvConstellation.setText(filmMaker.getConstellation());
                tvBirthplace.setText(filmMaker.getBorn_place());
                tvChineseName.setText(filmMaker.getAka().toString());
                tvForeignName.setText(filmMaker.getAka_en().toString());
                tvBriefIntroduction.setText(filmMaker.getSummary());

            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(mContext, "F!", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onError:" + e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        };
        SingletonRetrofit.getInstance().requestFetchActorDetails(filmMakerObserver,id, Util.DOUBAN_APIKEY);

    }
}
