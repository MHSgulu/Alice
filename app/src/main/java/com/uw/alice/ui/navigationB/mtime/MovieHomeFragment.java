package com.uw.alice.ui.navigationB.mtime;

import android.content.Context;
import android.content.Intent;
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
import com.uw.alice.data.entity.MovieEntity;
import com.uw.alice.common.Constant;
import com.uw.alice.ui.navigationB.mtime.adapter.MovieShowingAdapter;
import com.uw.alice.ui.navigationB.mtime.hot.HotMTimeMovieListActivity;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.List;


public class MovieHomeFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "MTimeMovieHomeFragment";
    private Context mContext;

    private LinearLayout llSearchBox, llBoxOffice;
    private RelativeLayout rlHot;

    private Banner banner;
    private List<String> moviePosterList = new ArrayList<>();

    private TextView tvMovieShowingNumber;
    private RecyclerView mRecyclerView1;
    private MovieShowingAdapter mAdapter1;

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

        tvMovieShowingNumber = root.findViewById(R.id.tv_movieShowing_number);
        mRecyclerView1 = root.findViewById(R.id.list_is_showing_up);

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
        hotMovieDataList.add(new MovieEntity("你好，李焕英", 8.2, "https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2629056068.webp"));
        hotMovieDataList.add(new MovieEntity("唐人街探案3", 5.7, "https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2622388983.webp"));
        hotMovieDataList.add(new MovieEntity("刺杀小说家", 7.0, "https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2628440875.webp"));
        hotMovieDataList.add(new MovieEntity("人潮汹涌", 7.2, "https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2632862664.webp"));
        hotMovieDataList.add(new MovieEntity("新神榜：哪吒重生", 7.4, "https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2631711326.webp"));
        hotMovieDataList.add(new MovieEntity("侍神令", 5.9, "https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2629260713.webp"));

        tvMovieShowingNumber.setText(String.valueOf(hotMovieDataList.size()));
        /*
         * 如果RecyclerView可以事先知道RecyclerView的大小不受适配器内容的影响，则可以执行一些优化。
         * RecyclerView仍可以根据其他因素（例如其父级的大小）更改其大小，但是此大小计算不能取决于其子级的大小或适配器的内容（适配器中的项目数除外）。
         * 如果您对RecyclerView的使用属于此类，请将其设置为{@code true}。 当适配器内容更改时，它将允许RecyclerView避免使整个布局无效。
         * @param hasFixedSize 如果适配器更改不能影响RecyclerView的大小，则为true。
         */
        mRecyclerView1.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 3);
        //gridLayoutManager.setSpanCount(3);
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
                intent1.putExtra(Constant.ARG_MovieType, 1);
                startActivity(intent1);
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