package com.uw.alice.ui.navigationB.mtime;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.MaterialToolbar;
import com.uw.alice.R;
import com.uw.alice.data.model.FilmmakerPhoto;
import com.uw.alice.common.Constant;
import com.uw.alice.network.retrofit.SingletonRetrofit;
import com.uw.alice.ui.navigationB.douban.filmmaker.FilmMakerAlbumShowAdapter;
import com.uw.alice.ui.navigationB.douban.filmmaker.FilmmakerPhotosFragment;

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
            actorId = getIntent().getStringExtra(Constant.ARG_ActorId);
            Log.i(TAG,"数据点位 时光网演员Id: "+ actorId);
            Glide.with(mContext).load(getIntent().getStringExtra(Constant.ARG_ActorCover)).into(ivCover);

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
