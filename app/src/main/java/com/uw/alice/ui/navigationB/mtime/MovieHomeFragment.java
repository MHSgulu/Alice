package com.uw.alice.ui.navigationB.mtime;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uw.alice.R;
import com.uw.alice.assist.MoviePosterAdapter;
import com.uw.alice.common.Function;
import com.uw.alice.data.entity.MovieEntity;
import com.uw.alice.data.model.MTimeComingMovie;
import com.uw.alice.data.util.Util;
import com.uw.alice.network.retrofit.SingletonRetrofit;
import com.uw.alice.ui.navigationB.mtime.adapter.MTimeMovieComingAdapter;
import com.uw.alice.ui.navigationB.mtime.adapter.MovieShowingAdapter;
import com.uw.alice.ui.navigationB.mtime.hot.HotMTimeMovieListActivity;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MovieHomeFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "MTimeMovieHomeFragment";
    private Context mContext;

    private LinearLayout llSearchBox, llBoxOffice;
    private RelativeLayout rlHot, rlSoon;

    private Banner banner;
    private List<String> moviePosterList = new ArrayList<>();

    private TextView tvMovieShowingNumber, tvMovieShownSoonNumber;
    private RecyclerView mRecyclerView1, mRecyclerView2;
    private MovieShowingAdapter mAdapter1;
    private MTimeMovieComingAdapter mAdapter2;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "生命周期点位：  MTimeMovieHomeFragment onCreate");
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "生命周期点位：  MTimeMovieHomeFragment onCreateView");
        requireActivity().getWindow().setStatusBarColor(requireActivity().getColor(R.color.colorNavigationB));
        View root = inflater.inflate(R.layout.fragment_home_time_movie, container, false);
        mContext = getContext();
        llSearchBox = root.findViewById(R.id.ll_search_box);
        llSearchBox.setOnClickListener(this);
        llBoxOffice = root.findViewById(R.id.ll_box_office);
        llBoxOffice.setOnClickListener(this);

        rlHot = root.findViewById(R.id.rl_hot);
        rlHot.setOnClickListener(this);
        rlSoon = root.findViewById(R.id.rl_soon);
        rlSoon.setOnClickListener(this);

        tvMovieShowingNumber = root.findViewById(R.id.tv_movieShowing_number);
        tvMovieShownSoonNumber = root.findViewById(R.id.tv_movieShownSoon_number);
        mRecyclerView1 = root.findViewById(R.id.list_is_showing_up);
        mRecyclerView2 = root.findViewById(R.id.list_shown_soon);

        banner = root.findViewById(R.id.banner);
        banner.addBannerLifecycleObserver(this)  //自动控制生命周期
                .setAdapter(new MoviePosterAdapter(moviePosterList))//设置适配器
                .setIndicator(new CircleIndicator(mContext))//设置指示器
                .start();

        getMoviePoster();
        getMovieOnShowing();
        //getUpcomingFilms();

        return root;
    }

    /**
     * 轮播图数据
     */
    private void getMoviePoster() {
        moviePosterList.add("https://img1.doubanio.com/view/photo/l/public/p2201327958.webp");
        moviePosterList.add("https://img9.doubanio.com/view/photo/m/public/p2559579036.webp");
        moviePosterList.add("https://img9.doubanio.com/view/photo/l/public/p2540924496.webp");
    }

    /**
     * 正在上映
     */
    private void getMovieOnShowing() {
        List<MovieEntity> hotMovieDataList = new ArrayList<>();
        hotMovieDataList.add(new MovieEntity("送你一朵小红花", 7.5, "https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2618247457.webp"));
        hotMovieDataList.add(new MovieEntity("心灵奇旅", 8.9, "https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2626308994.webp"));
        hotMovieDataList.add(new MovieEntity("温暖的抱抱", 5.6, "https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2618446242.webp"));
        hotMovieDataList.add(new MovieEntity("拆弹专家2", 7.9, "https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2621379901.webp"));
        hotMovieDataList.add(new MovieEntity("紧急救援", 6.3, "https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2627025706.webp"));
        hotMovieDataList.add(new MovieEntity("缉魂", 7.1, "https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2629413230.webp"));

        tvMovieShowingNumber.setText(String.valueOf(hotMovieDataList.size()));
        /*
         * 如果RecyclerView可以事先知道RecyclerView的大小不受适配器内容的影响，则可以执行一些优化。
         * RecyclerView仍可以根据其他因素（例如其父级的大小）更改其大小，但是此大小计算不能取决于其子级的大小或适配器的内容（适配器中的项目数除外）。
         * 如果您对RecyclerView的使用属于此类，请将其设置为{@code true}。 当适配器内容更改时，它将允许RecyclerView避免使整个布局无效。
         * @param hasFixedSize 如果适配器更改不能影响RecyclerView的大小，则为true。
         */
        mRecyclerView1.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 3);
        gridLayoutManager.setSpanCount(3);
        mRecyclerView1.setLayoutManager(gridLayoutManager);
        mAdapter1 = new MovieShowingAdapter(hotMovieDataList);
        mRecyclerView1.setAdapter(mAdapter1);

        /*mAdapter1.setOnItemClickListener((view, position) -> {
            Intent intent = new Intent(mContext, MTimeMovieDetailsActivity.class);
            // Log.d(TAG, "测试参数 传id:" + movie.getSubjects().get(position).getId());
            intent.putExtra(Util.ARG_MovieId, t.getMs().get(position).getMovieId());
            intent.putExtra(Util.ARG_MoviePoster, t.getMs().get(position).getImg());
            startActivity(intent);
        });*/

        //获取最佳间距
        //final int space = Function.getGridListSpace(mContext, gridLayoutManager.getWidth());

    }

    /**
     * 即将上映
     */
    private void getUpcomingFilms() {
        Observer<MTimeComingMovie> mTimeComingMovieObserver = new Observer<MTimeComingMovie>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(MTimeComingMovie t) {

                if (!t.getMoviecomings().isEmpty()) {
                    tvMovieShownSoonNumber.setText(String.valueOf(t.getMoviecomings().size()));
                    mRecyclerView2.setHasFixedSize(true);
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 3);
                    mRecyclerView2.setLayoutManager(gridLayoutManager);

                    mAdapter2 = new MTimeMovieComingAdapter(t.getMoviecomings());
                    mRecyclerView2.setAdapter(mAdapter2);

                    mAdapter2.setOnItemClickListener((view, position) -> {
                        //Toast.makeText(mContext, movie.getSubjects().get(position).getTitle(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(mContext, MTimeMovieDetailsActivity.class);
                        //Log.d(TAG, "测试参数 传id:" + movie.getSubjects().get(position).getId());
                        intent.putExtra(Util.ARG_MovieId, t.getMoviecomings().get(position).getId());
                        intent.putExtra(Util.ARG_MoviePoster, t.getMoviecomings().get(position).getImage());
                        startActivity(intent);
                    });

                    //获取最佳间距
                    final int space = Function.getGridListSpace(mContext, gridLayoutManager.getWidth());

                    mRecyclerView2.addItemDecoration(new RecyclerView.ItemDecoration() {
                        @Override
                        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                            super.getItemOffsets(outRect, view, parent, state);
                            int index = parent.getChildAdapterPosition(view);
                            //在360dp宽度的红米7手机调试出的最佳间距dp距离 无法达到适应所有机型的完美效果
                            if (index % 3 == 0) {
                                outRect.left = space * 2; //30
                                outRect.right = 0;
                            } else if ((index - 1) % 3 == 0) {
                                outRect.left = (int) (space * 1.33);  //20
                                outRect.right = (int) (space * 1.33); //20
                            } else if ((index - 2) % 3 == 0) {
                                outRect.left = (int) (space / 1.5);  //10
                                outRect.right = (int) (space * 1.33); //20
                            }
                        }
                    });
                } else {
                    Toast.makeText(mContext, "目前暂无即将上映的电影", Toast.LENGTH_SHORT).show();
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
        SingletonRetrofit.getInstance().fetchMTimeMovieComingSoon(mTimeComingMovieObserver, Util.LocationId);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {

            case R.id.ll_search_box:
                Toast.makeText(mContext, "电影搜索", Toast.LENGTH_SHORT).show();
                break;

            case R.id.ll_box_office:
                Toast.makeText(mContext, "实时票房", Toast.LENGTH_SHORT).show();
                break;

            case R.id.rl_hot:
                Intent intent1 = new Intent(mContext, HotMTimeMovieListActivity.class);
                intent1.putExtra(Util.ARG_MovieType, 1);
                startActivity(intent1);
                break;

            case R.id.rl_soon:
                Intent intent2 = new Intent(mContext, HotMTimeMovieListActivity.class);
                intent2.putExtra(Util.ARG_MovieType, 2);
                startActivity(intent2);
                break;

        }
    }


    @Override
    public void onStart() {
        super.onStart();
    }


    @Override
    public void onStop() {
        super.onStop();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}