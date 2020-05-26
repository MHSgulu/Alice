package com.uw.alice.ui.navigationB;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uw.alice.R;
import com.uw.alice.assist.MoviePosterAdapter;
import com.uw.alice.common.Function;
import com.uw.alice.data.model.Movie;
import com.uw.alice.data.model.MoviePoster;
import com.uw.alice.data.model.MovieRankingList;
import com.uw.alice.data.util.Util;
import com.uw.alice.network.retrofit.SingletonRetrofit;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.transformer.AlphaPageTransformer;
import com.youth.banner.util.BannerUtils;


import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class DashboardFragment extends Fragment implements View.OnClickListener{

    private static final String TAG = "DashboardFragment";
    private Context mContext;

    private LinearLayout llSearchBox,llBoxOffice;

    private Banner banner;
    private List<MoviePoster> moviePosterList = new ArrayList<>();


    private TextView tvMovieShowingNumber,tvMovieShownSoonNumber;
    private RecyclerView mRecyclerView1,mRecyclerView2,mRecyclerView3;
    private List<MovieRankingList> mDataList = new ArrayList<>();
    private MovieRankingListAdapter mAdapter;
    private MovieShowingUpAdapter mAdapter1;
    private MovieComingSoonAdapter mAdapter2;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        //设置系统状态栏颜色
        Function.setSystemStatusBarColor(requireActivity(),R.color.colorNavigationB);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        //海报数据
        TestMovieBanner();

        banner = root.findViewById(R.id.banner);


        banner.setAdapter(new MoviePosterAdapter(moviePosterList))//设置适配器
                .addBannerLifecycleObserver(this)//添加生命周期观察者
                .setIndicator(new CircleIndicator(mContext))//设置指示器
                .start();


        llSearchBox = root.findViewById(R.id.ll_search_box);
        llSearchBox.setOnClickListener(this);
        llBoxOffice = root.findViewById(R.id.ll_box_office);
        llBoxOffice.setOnClickListener(this);

        tvMovieShowingNumber = root.findViewById(R.id.tv_movieShowing_number);
        tvMovieShownSoonNumber = root.findViewById(R.id.tv_movieShownSoon_number);
        mRecyclerView1 = root.findViewById(R.id.list_is_showing_up);
        mRecyclerView2 = root.findViewById(R.id.list_shown_sonn);
        mRecyclerView3 = root.findViewById(R.id.list_movie_ranking);



        //初始化豆瓣榜单
        initMovieRankingList();

        //查询正在上映的电影
        queryMovieOnShowing();

        //查询即将上映的电影
        queryUpcomingFilms();




        return root;
    }

    private void TestMovieBanner() {

        MoviePoster moviePoster1 = new MoviePoster("https://img9.doubanio.com/view/photo/l/public/p2576400566.webp");
        moviePosterList.add(moviePoster1);

        MoviePoster moviePoster2 = new MoviePoster("https://img9.doubanio.com/view/photo/l/public/p2554842075.webp");
        moviePosterList.add(moviePoster2);

        MoviePoster moviePoster3 = new MoviePoster("https://img1.doubanio.com/view/photo/l/public/p2524501979.webp");
        moviePosterList.add(moviePoster3);


    }


    private void initMovieRankingList() {

        MovieRankingList movieRankingList1 = new MovieRankingList("豆瓣新片榜","",R.drawable.img_bg4);
        MovieRankingList movieRankingList2 = new MovieRankingList("一周口碑电影","",R.drawable.img_bg1);
        MovieRankingList movieRankingList3 = new MovieRankingList("豆瓣电影Top250","",R.drawable.img_bg3);
        mDataList.add(movieRankingList1);
        mDataList.add(movieRankingList2);
        mDataList.add(movieRankingList3);

        mRecyclerView3.setLayoutManager(new LinearLayoutManager(mContext,RecyclerView.HORIZONTAL,false));
        mAdapter = new MovieRankingListAdapter(mDataList);
        mRecyclerView3.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new MovieRankingListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(mContext, "p:"+position, Toast.LENGTH_SHORT).show();
                if (position==0){
                    Toast.makeText(mContext, "豆瓣新片榜", Toast.LENGTH_SHORT).show();
                }if(position==1){
                    Toast.makeText(mContext, "一周口碑电影", Toast.LENGTH_SHORT).show();
                }if(position==2){
                    Toast.makeText(mContext, "豆瓣电影Top250", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }



    /*
     * 正在上映
    */
    private void queryMovieOnShowing() {

        Observer<Movie> movieObserver = new Observer<Movie>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(final Movie movie) {
                if (movie.getSubjects() != null){
                    //Toast.makeText(mContext, "S!", Toast.LENGTH_SHORT).show();
                    tvMovieShowingNumber.setText(String.valueOf(movie.getTotal()));
                    mRecyclerView1.setHasFixedSize(true);
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext,3);
                    mRecyclerView1.setLayoutManager(gridLayoutManager);
                    if (movie.getTotal() <= 6){
                        mAdapter1 = new MovieShowingUpAdapter(movie.getSubjects());
                        mAdapter.notifyDataSetChanged();
                        mRecyclerView1.setAdapter(mAdapter1);
                    }else {
                        mAdapter1 = new MovieShowingUpAdapter(movie.getSubjects().subList(0,5));
                        mAdapter.notifyDataSetChanged();
                        mRecyclerView1.setAdapter(mAdapter1);
                    }
                    //获取最佳间距
                    final int space = Function.getGridListSpace(mContext,gridLayoutManager.getWidth());

                    mRecyclerView1.addItemDecoration(new RecyclerView.ItemDecoration() {
                        @Override
                        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                            super.getItemOffsets(outRect, view, parent, state);
                            int index = parent.getChildAdapterPosition(view);
                            //在360dp宽度的红米7手机调试出的最佳间距dp距离 无法达到适应所有机型的完美效果
                            if(index % 3 == 0){
                                outRect.left = space*2; //30
                                outRect.right = 0;
                            }else if((index - 1) % 3 == 0){
                                outRect.left = (int) (space * 1.33);  //20
                                outRect.right = (int) (space * 1.33); //20
                            }else if((index - 2) % 3 == 0){
                                outRect.left = (int) (space / 1.5);  //10
                                outRect.right = (int) (space * 1.33); //20
                            }

                        }
                    });


                }else {
                    Toast.makeText(mContext, "目前暂无正在上映的电影", Toast.LENGTH_SHORT).show();
                }

                mAdapter1.setOnItemClickListener(new MovieShowingUpAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        //Toast.makeText(mContext,movie.getSubjects().get(position).getTitle(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(mContext,MovieDetailsActivity.class);
                        intent.putExtra("MovieTitle",movie.getSubjects().get(position).getTitle());
                        intent.putExtra("MovieOriginalTitle",movie.getSubjects().get(position).getOriginal_title());
                        if (movie.getSubjects().get(position).getDirectors().get(0).getName_en().equals("")){
                            intent.putExtra("MovieDirector", movie.getSubjects().get(position).getDirectors().get(0).getName());
                        }else {
                            intent.putExtra("MovieDirector", movie.getSubjects().get(position).getDirectors().get(0).getName() +"/" + movie.getSubjects().get(position).getDirectors().get(0).getName_en());
                        }                        intent.putStringArrayListExtra("MovieGenren", (ArrayList<String>) movie.getSubjects().get(position).getGenres());
                        intent.putStringArrayListExtra("MovieLength", (ArrayList<String>) movie.getSubjects().get(position).getDurations());
                        intent.putExtra("MovieReleaseDate",movie.getSubjects().get(position).getMainland_pubdate());
                        intent.putExtra("MovieRating",movie.getSubjects().get(position).getRating().getAverage());
                        intent.putExtra("MoviePoster",movie.getSubjects().get(position).getImages().getMedium());
                        startActivity(intent);
                    }
                });

            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(mContext, "请求豆瓣正在上映的电影数据失败", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onError:" + e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        };
        SingletonRetrofit.getInstance().queryMovieOnShow(movieObserver, Util.DOUBAN_APIKEY);

    }


    /*
     * 即将上映
     * */
    private void queryUpcomingFilms() {

        Observer<Movie> movieObserver = new Observer<Movie>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(final Movie movie) {
                if (movie.getTotal() > 0){
                    //Toast.makeText(mContext, "S!", Toast.LENGTH_SHORT).show();
                    tvMovieShownSoonNumber.setText(String.valueOf(movie.getTotal()));
                    mRecyclerView2.setHasFixedSize(true);
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext,3);
                    mRecyclerView2.setLayoutManager(gridLayoutManager);
                    mAdapter2 = new MovieComingSoonAdapter(movie.getSubjects());
                    mRecyclerView2.setAdapter(mAdapter2);

                    //获取最佳间距
                    final int space = Function.getGridListSpace(mContext,gridLayoutManager.getWidth());

                    mRecyclerView2.addItemDecoration(new RecyclerView.ItemDecoration() {
                        @Override
                        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                            super.getItemOffsets(outRect, view, parent, state);
                            int index = parent.getChildAdapterPosition(view);
                            //在360dp宽度的红米7手机调试出的最佳间距dp距离 无法达到适应所有机型的完美效果
                            if(index % 3 == 0){
                                outRect.left = space*2; //30
                                outRect.right = 0;
                            }else if((index - 1) % 3 == 0){
                                outRect.left = (int) (space * 1.33);  //20
                                outRect.right = (int) (space * 1.33); //20
                            }else if((index - 2) % 3 == 0){
                                outRect.left = (int) (space / 1.5);  //10
                                outRect.right = (int) (space * 1.33); //20
                            }

                        }
                    });




                }else {
                    Toast.makeText(mContext, "目前暂无即将上映的电影", Toast.LENGTH_SHORT).show();
                }

                mAdapter2.setOnItemClickListener(new MovieComingSoonAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        //Toast.makeText(mContext, movie.getSubjects().get(position).getTitle(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(mContext,MovieDetailsActivity.class);
                        intent.putExtra("MovieTitle",movie.getSubjects().get(position).getTitle());
                        intent.putExtra("MovieOriginalTitle",movie.getSubjects().get(position).getOriginal_title());
                        if (movie.getSubjects().get(position).getDirectors().get(0).getName_en().equals("")){
                            intent.putExtra("MovieDirector", movie.getSubjects().get(position).getDirectors().get(0).getName());
                        }else {
                            intent.putExtra("MovieDirector", movie.getSubjects().get(position).getDirectors().get(0).getName() +"/" + movie.getSubjects().get(position).getDirectors().get(0).getName_en());
                        }
                        intent.putStringArrayListExtra("MovieGenren", (ArrayList<String>) movie.getSubjects().get(position).getGenres());
                        intent.putStringArrayListExtra("MovieLength", (ArrayList<String>) movie.getSubjects().get(position).getDurations());
                        intent.putExtra("MovieReleaseDate",movie.getSubjects().get(position).getMainland_pubdate());
                        intent.putExtra("MovieRating",movie.getSubjects().get(position).getRating().getAverage());
                        intent.putExtra("MoviePoster",movie.getSubjects().get(position).getImages().getMedium());
                        startActivity(intent);
                    }
                });




            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(mContext, "请求豆瓣即将上映的电影数据失败", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onError:" + e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        };
        SingletonRetrofit.getInstance().queryMovieUpcoming(movieObserver,Util.DOUBAN_APIKEY);

    }



    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){

            case R.id.ll_search_box:
                Toast.makeText(mContext, "电影搜索", Toast.LENGTH_SHORT).show();
                break;

            case R.id.ll_box_office:
                Toast.makeText(mContext, "实时票房", Toast.LENGTH_SHORT).show();
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







        //查询豆瓣电影Top250
    //queryMovieTop250();
    private void queryMovieTop250() {

        io.reactivex.Observer<Movie> movieObserver = new io.reactivex.Observer<Movie>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Movie movie) {
                Toast.makeText(mContext, "S!", Toast.LENGTH_SHORT).show();
                //Log.d(TAG, "movie:"+movie.getSubjects().get(0).getTitle());
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onError:"+e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        };
        SingletonRetrofit.getInstance().queryTop250Movie(movieObserver, Util.DOUBAN_APIKEY,0,1);


    }



}