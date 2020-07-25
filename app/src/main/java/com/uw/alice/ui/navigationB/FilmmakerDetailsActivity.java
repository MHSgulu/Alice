package com.uw.alice.ui.navigationB;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.MaterialToolbar;
import com.uw.alice.R;
import com.uw.alice.data.model.FilmMaker;
import com.uw.alice.data.util.Util;
import com.uw.alice.network.retrofit.SingletonRetrofit;
import com.uw.alice.data.adapter.filmmaker.FilmMakerAlbumShowAdapter;
import com.uw.alice.data.adapter.filmmaker.FilmMakerWorkShowAdapter;

import java.util.List;
import java.util.stream.Collectors;

import cc.shinichi.library.ImagePreview;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class FilmmakerDetailsActivity extends AppCompatActivity {

    private static final String TAG = "FilmmakerDetailsActivity";
    private Context mContext;
    private MaterialToolbar toolbar;
    private RecyclerView mRecyclerViewFilmWork;
    private RecyclerView mRecyclerViewActorAlbum;
    private ImageView ivCover;
    private TextView tvBirthday,tvConstellation,tvBirthplace,tvChineseName,tvForeignName,tvBriefIntroduction;
    private LinearLayout llOpen;

    private FilmMakerAlbumShowAdapter mAdapter;


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
        llOpen = findViewById(R.id.ll_open);
        llOpen.setOnClickListener(v -> {
            tvBriefIntroduction.setMaxLines(999);
            llOpen.setVisibility(View.GONE);
        });
        mRecyclerViewFilmWork = findViewById(R.id.list_film_works);
        mRecyclerViewActorAlbum = findViewById(R.id.list_actor_album);

        toolbar.setNavigationOnClickListener(v -> finish());


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
                tvBriefIntroduction.post(() -> {
                    if (tvBriefIntroduction.getLayout().getEllipsisCount(tvBriefIntroduction.getLineCount()-1) > 0){
                        runOnUiThread(() -> llOpen.setVisibility(View.VISIBLE));
                    }
                });



                if (!filmMaker.getWorks().isEmpty()){
                    mRecyclerViewFilmWork.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
                    mRecyclerViewFilmWork.setAdapter(new FilmMakerWorkShowAdapter(filmMaker.getWorks()));
                }

                if (!filmMaker.getPhotos().isEmpty()){
                    mRecyclerViewActorAlbum.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
                    mAdapter = new FilmMakerAlbumShowAdapter(filmMaker.getPhotos());
                    mRecyclerViewActorAlbum.setAdapter(mAdapter);

                    List<String> imgUrlList = filmMaker.getPhotos().stream().map(FilmMaker.PhotosBean::getImage).collect(Collectors.toList());

                    mAdapter.setOnItemClickListener((view, position) -> ImagePreview.getInstance()
                            .setContext(mContext)
                            //.setIndex(0) //单张
                            //.setImage(filmMaker.getPhotos().get(position).getImage())  //单张
                            .setIndex(position)
                            .setImageList(imgUrlList)
                            .setShowCloseButton(true) //显示关闭按钮
                            .setEnableDragClose(true) //启用下拉关闭
                            .setEnableUpDragClose(true) //启用上拉关闭
                            .start());


                }


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
