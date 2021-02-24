package com.uw.alice.ui.navigationB.douban.filmmaker;

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
import com.uw.alice.data.model.FilmmakerPhoto;
import com.uw.alice.common.Constant;
import com.uw.alice.network.retrofit.SingletonRetrofit;

import java.util.List;
import java.util.stream.Collectors;

import cc.shinichi.library.ImagePreview;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class FilmmakerDetailsActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "FilmmakerDetailsActivity";
    private Context mContext;
    private MaterialToolbar toolbar;
    private RecyclerView mRecyclerViewFilmWork;
    private RecyclerView mRecyclerViewActorAlbum;
    private ImageView ivCover,ivNext1,ivNext2;
    private TextView tvBirthday,tvConstellation,tvBirthplace,tvChineseName,tvForeignName,
            tvBriefIntroduction,tvWorksNumber,tvPhotosNumber;
    private LinearLayout llOpen;

    private FilmMakerAlbumShowAdapter mAdapter;

    private String actorId;
    private String actorName;

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

        toolbar.setNavigationOnClickListener(v -> finish());

        llOpen = findViewById(R.id.ll_open);
        llOpen.setOnClickListener(this);
        ivNext1 = findViewById(R.id.iv_next_1);
        ivNext1.setOnClickListener(this);
        ivNext2 = findViewById(R.id.iv_next_2);
        ivNext2.setOnClickListener(this);
        tvWorksNumber = findViewById(R.id.tv_works_number);
        tvWorksNumber.setOnClickListener(this);
        tvPhotosNumber = findViewById(R.id.tv_photos_number);
        tvPhotosNumber.setOnClickListener(this);

        mRecyclerViewFilmWork = findViewById(R.id.list_film_works);
        mRecyclerViewActorAlbum = findViewById(R.id.list_actor_album);


        if (getIntent() != null){
            //Log.d(TAG,"actorId: "+ actorId);
            //String cover = getIntent().getStringExtra("actorCover");
            //Log.d(TAG,"cover: "+ cover);
            actorId = getIntent().getStringExtra("actorId");
            Glide.with(mContext).load(getIntent().getStringExtra("actorCover")).into(ivCover);
            fetchActorDetails(actorId);
            fetchPhotos(actorId);

        }

        //设置系统状态半透明
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.ll_open:
                tvBriefIntroduction.setMaxLines(999);
                llOpen.setVisibility(View.GONE);
                break;

            case R.id.tv_works_number:

            case R.id.iv_next_2:
                FilmmakerPhotosFragment.newInstance(actorId,actorName).show(getSupportFragmentManager(),"FilmmakerPhotosFragment");
                break;

            case R.id.tv_photos_number:
                FilmmakerPhotosFragment.newInstance(actorId,actorName).show(getSupportFragmentManager(),"FilmmakerPhotosFragment");
                break;

            case R.id.iv_next_1:
                FilmmakerPhotosFragment.newInstance(actorId,actorName).show(getSupportFragmentManager(),"FilmmakerPhotosFragment");
                break;

        }

    }


    /**
     * 获取影人条目信息
     */
    private void fetchActorDetails(String id) {

        Observer<FilmMaker> filmMakerObserver = new Observer<FilmMaker>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(FilmMaker filmMaker) {
                //Toast.makeText(mContext, "S!", Toast.LENGTH_SHORT).show();
                actorName = filmMaker.getName();
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
        SingletonRetrofit.getInstance().requestFetchActorDetails(filmMakerObserver,id, Constant.DOUBAN_APIKEY);

    }


    /**
     * 获取影人照片信息
     */
    private void fetchPhotos(String actorId) {

        Observer<FilmmakerPhoto> filmmakerPhotoObserver = new Observer<FilmmakerPhoto>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(FilmmakerPhoto filmmakerPhoto) {
                if (filmmakerPhoto.getTotal() > 0){
                    tvPhotosNumber.setText(String.format("全部照片 %s", filmmakerPhoto.getTotal()));
                }
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(mContext, "onError:" + e.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onError:" + e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        };
        SingletonRetrofit.getInstance().requestFetchFilmmakerPhoto(filmmakerPhotoObserver,actorId, Constant.DOUBAN_APIKEY,0);

    }



}
