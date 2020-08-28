package com.uw.alice.ui.navigationB.mtime;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.MaterialToolbar;
import com.uw.alice.R;
import com.uw.alice.data.model.FilmMaker;
import com.uw.alice.data.model.FilmmakerPhoto;
import com.uw.alice.data.model.MTimeActorDetail;
import com.uw.alice.data.util.Util;
import com.uw.alice.network.retrofit.SingletonRetrofit;
import com.uw.alice.ui.navigationB.douban.filmmaker.FilmMakerAlbumShowAdapter;
import com.uw.alice.ui.navigationB.douban.filmmaker.FilmMakerWorkShowAdapter;
import com.uw.alice.ui.navigationB.douban.filmmaker.FilmmakerPhotosFragment;

import java.util.List;
import java.util.stream.Collectors;

import cc.shinichi.library.ImagePreview;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MTimeActorDetailsActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "MTimeActorDetailsActivity";
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
        setContentView(R.layout.activity_time_actor_details);
        mContext = MTimeActorDetailsActivity.this;
        initBindViewId();

        if (getIntent() != null){
            actorId = getIntent().getStringExtra(Util.ARG_ActorId);
            Log.i(TAG,"数据点位 时光网演员Id: "+ actorId);
            Glide.with(mContext).load(getIntent().getStringExtra(Util.ARG_ActorCover)).into(ivCover);
            fetchActorDetails(actorId);
            //fetchPhotos(actorId);

        }
        //设置系统状态半透明
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }


    private void initBindViewId() {
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
        Observer<MTimeActorDetail> mTimeActorDetailObserver = new Observer<MTimeActorDetail>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(MTimeActorDetail t) {
                String code = t.getCode();
                String msg = t.getMsg();
                Log.i(TAG,"数据点位 code: "+ code);
                Log.i(TAG,"数据点位 msg: "+ msg);
                //Toast.makeText(mContext, t.getCode(), Toast.LENGTH_SHORT).show();
               // if (TextUtils.equals(t.getCode(),"1")){
                    /*Toast.makeText(mContext, "S!", Toast.LENGTH_SHORT).show();
                    actorName = t.getData().getBackground().getNameCn();
                    toolbar.setTitle(t.getData().getBackground().getNameCn());
                    tvBirthday.setText(String.format("%s-%s-%s",
                                    t.getData().getBackground().getBirthYear(),
                                    t.getData().getBackground().getBirthMonth(),
                                    t.getData().getBackground().getBirthDay()));

                    //tvConstellation.setText(t.getConstellation());
                    tvBirthplace.setText(t.getData().getBackground().getAddress());
                    //tvChineseName.setText(t.getAka().toString());
                    tvForeignName.setText(t.getData().getBackground().getNameEn());
                    tvBriefIntroduction.setText(t.getData().getBackground().getContent());
                    tvBriefIntroduction.post(() -> {
                        if (tvBriefIntroduction.getLayout().getEllipsisCount(tvBriefIntroduction.getLineCount()-1) > 0){
                            runOnUiThread(() -> llOpen.setVisibility(View.VISIBLE));
                        }
                    });*/

                    /*if (!t.getWorks().isEmpty()){
                        mRecyclerViewFilmWork.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
                        mRecyclerViewFilmWork.setAdapter(new tWorkShowAdapter(t.getWorks()));
                    }*/

                    /*if (!t.getPhotos().isEmpty()){
                        mRecyclerViewActorAlbum.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
                        mAdapter = new tAlbumShowAdapter(t.getPhotos());
                        mRecyclerViewActorAlbum.setAdapter(mAdapter);

                        List<String> imgUrlList = t.getPhotos().stream().map(t.PhotosBean::getImage).collect(Collectors.toList());

                        mAdapter.setOnItemClickListener((view, position) -> ImagePreview.getInstance()
                                .setContext(mContext)
                                //.setIndex(0) //单张
                                //.setImage(t.getPhotos().get(position).getImage())  //单张
                                .setIndex(position)
                                .setImageList(imgUrlList)
                                .setShowCloseButton(true) //显示关闭按钮
                                .setEnableDragClose(true) //启用下拉关闭
                                .setEnableUpDragClose(true) //启用上拉关闭
                                .start());
                    }*/

                }
                /*else {
                    Toast.makeText(mContext, mTimeActorDetail.getMsg(), Toast.LENGTH_SHORT).show();
                }*/


            @Override
            public void onError(Throwable e) {
                Toast.makeText(mContext, "onError: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, "监控点位 onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        };
        SingletonRetrofit.getInstance().fetchMTimeActorDetail(mTimeActorDetailObserver,id,Util.LocationId);


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
        SingletonRetrofit.getInstance().requestFetchFilmmakerPhoto(filmmakerPhotoObserver,actorId,Util.DOUBAN_APIKEY,0);

    }



}
