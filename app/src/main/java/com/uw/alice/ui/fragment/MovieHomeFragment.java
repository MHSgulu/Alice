package com.uw.alice.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.uw.alice.R;
import com.uw.alice.assist.MoviePosterAdapter;
import com.uw.alice.data.entity.MovieEntity;
import com.uw.alice.databinding.FragmentMovieHomeBinding;
import com.uw.alice.ui.adapter.MovieShowingAdapter;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.List;


public class MovieHomeFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "MTimeMovieHomeFragment";
    private Context mContext;
    private List<String> moviePosterList = new ArrayList<>();
    private MovieShowingAdapter mAdapter1;
    private FragmentMovieHomeBinding viewBinding; //范围为片段视图的生命周期（在onCreateView和onDestroyView之间）

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "生命周期点位：  MTimeMovieHomeFragment onCreate");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "生命周期点位：  MTimeMovieHomeFragment onCreateView");
        //View root = inflater.inflate(R.layout.fragment__movie_home, container, false);
        requireActivity().getWindow().setStatusBarColor(requireActivity().getColor(R.color.colorNavigationB));
        //注意：inflate() 方法会要求您传入布局膨胀器。如果布局已膨胀，您可以调用绑定类的静态 bind() 方法。
        // 见下方 onViewCreated() 方法中
        viewBinding = FragmentMovieHomeBinding.inflate(inflater, container, false);
        View view = viewBinding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //第二种视图绑定的方式
        //viewBinding = FragmentMovieHomeBinding.bind(view);
        initLoad();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //注意：Fragment 的存在时间比其视图长。请务必在 Fragment 的 onDestroyView() 方法中清除对绑定类实例的所有引用。
        viewBinding = null;
    }

    private void initLoad() {
        mContext = getContext();

        viewBinding.searchMovieBox.setOnClickListener(this);
        viewBinding.llBoxOffice.setOnClickListener(this);

        viewBinding.banner.addBannerLifecycleObserver(this)  //自动控制生命周期
                .setAdapter(new MoviePosterAdapter(moviePosterList))//设置适配器
                .setIndicator(new CircleIndicator(mContext))//设置指示器
                .start();

        getMoviePoster();
        getMovieOnShowing();
    }

    //电影横幅海报 轮播图
    private void getMoviePoster() {
        moviePosterList.add("https://img1.doubanio.com/view/photo/l/public/p2201327958.webp");
        moviePosterList.add("https://img9.doubanio.com/view/photo/m/public/p2559579036.webp");
        moviePosterList.add("https://img9.doubanio.com/view/photo/l/public/p2540924496.webp");
    }

    //正在热映的电影数据
    private void getMovieOnShowing() {
        List<MovieEntity> hotMovieDataList = new ArrayList<>();
        hotMovieDataList.add(new MovieEntity("你好，李焕英", 8.2, "https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2629056068.webp"));
        hotMovieDataList.add(new MovieEntity("唐人街探案3", 5.7, "https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2622388983.webp"));
        hotMovieDataList.add(new MovieEntity("刺杀小说家", 7.0, "https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2628440875.webp"));
        hotMovieDataList.add(new MovieEntity("人潮汹涌", 7.2, "https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2632862664.webp"));
        hotMovieDataList.add(new MovieEntity("新神榜：哪吒重生", 7.4, "https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2631711326.webp"));
        hotMovieDataList.add(new MovieEntity("侍神令", 5.9, "https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2629260713.webp"));

        viewBinding.tvMovieShowingNumber.setText(String.valueOf(hotMovieDataList.size()));
        /*
         * 如果RecyclerView可以事先知道RecyclerView的大小不受适配器内容的影响，则可以执行一些优化。
         * RecyclerView仍可以根据其他因素（例如其父级的大小）更改其大小，但是此大小计算不能取决于其子级的大小或适配器的内容（适配器中的项目数除外）。
         * 如果您对RecyclerView的使用属于此类，请将其设置为{@code true}。 当适配器内容更改时，它将允许RecyclerView避免使整个布局无效。
         * @param hasFixedSize 如果适配器更改不能影响RecyclerView的大小，则为true。
         */
        viewBinding.movieRecyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 3);
        viewBinding.movieRecyclerView.setLayoutManager(gridLayoutManager);
        mAdapter1 = new MovieShowingAdapter(hotMovieDataList);
        viewBinding.movieRecyclerView.setAdapter(mAdapter1);

        /*mAdapter1.setOnItemClickListener((view, position) -> {
            Intent intent = new Intent(mContext, MTimeMovieDetailsActivity.class);
            // Log.d(TAG, "测试参数 传id:" + movie.getSubjects().get(position).getId());
            intent.putExtra(Util.ARG_MovieId, t.getMs().get(position).getMovieId());
            intent.putExtra(Util.ARG_MoviePoster, t.getMs().get(position).getImg());
            startActivity(intent);
        });*/


    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {

            case R.id.search_movie_box:
                Toast.makeText(mContext, "电影搜索", Toast.LENGTH_SHORT).show();
                break;

            case R.id.ll_box_office:
                Toast.makeText(mContext, "实时票房", Toast.LENGTH_SHORT).show();
                break;
        }
    }


}