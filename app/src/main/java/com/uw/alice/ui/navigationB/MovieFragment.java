package com.uw.alice.ui.navigationB;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.uw.alice.R;
import com.uw.alice.data.model.Movie;
import com.uw.alice.data.model.MovieRankingList;
import com.uw.alice.data.util.Util;
import com.uw.alice.network.retrofit.SingletonRetrofit;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MovieFragment extends Fragment {
    //tag标记
    private static final String TAG = "MovieFragment";
    private Context mContext;

    private TextView tvMovieShowingNumber,tvMovieShownSoonNumber;
    private RecyclerView mRecyclerView1,mRecyclerView2,mRecyclerView3;
    private List<MovieRankingList> mDataList = new ArrayList<>();
    private MovieRankingListAdapter mAdapter;
    private MovieShowingUpAdapter mAdapter1,mAdapter2;

    public static MovieFragment newInstance() {
        //Bundle args = new Bundle();
        //fragment.setArguments(args);
        return new MovieFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_movie, container, false);
        //mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_dynamic_gif, container, false);
        mContext = getContext();

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
     * */
    private void queryMovieOnShowing() {

        Observer<Movie> movieObserver = new Observer<Movie>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(final Movie movie) {
                if (movie.getTotal() > 0){
                    //Toast.makeText(mContext, "S!", Toast.LENGTH_SHORT).show();
                    tvMovieShowingNumber.setText(String.valueOf(movie.getTotal()));
                    mRecyclerView1.setLayoutManager(new LinearLayoutManager(mContext,RecyclerView.HORIZONTAL,false));
                    mAdapter1 = new MovieShowingUpAdapter(movie.getSubjects());
                    mRecyclerView1.setAdapter(mAdapter1);
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
                    mRecyclerView2.setLayoutManager(new LinearLayoutManager(mContext,RecyclerView.HORIZONTAL,false));
                    mAdapter2 = new MovieShowingUpAdapter(movie.getSubjects());
                    mRecyclerView2.setAdapter(mAdapter2);
                }else {
                    Toast.makeText(mContext, "目前暂无即将上映的电影", Toast.LENGTH_SHORT).show();
                }

                mAdapter2.setOnItemClickListener(new MovieShowingUpAdapter.OnItemClickListener() {
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





}
