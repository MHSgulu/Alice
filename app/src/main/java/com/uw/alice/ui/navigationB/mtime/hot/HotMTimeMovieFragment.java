package com.uw.alice.ui.navigationB.mtime.hot;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.uw.alice.R;
import com.uw.alice.data.model.MTimeComingMovie;
import com.uw.alice.data.model.MTimeInTheatersMovie;
import com.uw.alice.data.model.Movie;
import com.uw.alice.data.util.Util;
import com.uw.alice.interfaces.OnItemClickListener;
import com.uw.alice.network.retrofit.SingletonRetrofit;
import com.uw.alice.ui.navigationB.douban.movie.MovieDetailsActivity;
import com.uw.alice.ui.navigationB.mtime.MTimeMovieDetailsActivity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class HotMTimeMovieFragment extends Fragment {

    private static final String TAG = "HotMTimeMovieFragment";
    private static final String ARG_TYPE = "type";

    private Context mContext;
    private RecyclerView mRecyclerView;
    private HotMTimeMovieListAdapter mAdapter;
    private List<Movie.SubjectsBean> mDataList = new ArrayList<>();
    private int type;
    private int start = 0; //当前索引
    private int total = 0; //全部数量
    private boolean isLoading = false; //是否加载中

    /**
     * 用于实例化片段的片段管理器的强制空构造函数(e.g. 屏幕方向改变时).
     */
    public HotMTimeMovieFragment() {
    }


    public static HotMTimeMovieFragment newInstance(int type) {
        HotMTimeMovieFragment fragment = new HotMTimeMovieFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        if (getArguments() != null) {
            type = getArguments().getInt(ARG_TYPE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        mRecyclerView = view.findViewById(R.id.list);
        initScrollListener();
        requestData();

        return view;
    }


    /**
     * list滑动监听
     */
    private void initScrollListener() {

        /*mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE){
                    if (!mRecyclerView.canScrollVertically(1)){
                        if (start < total){
                            if (!isLoading){
                                isLoading = true;
                                requestData(start + 20);
                            }
                        }
                    }
                }
                isLoading = false;
            }
        });*/

    }


    /**
     * 发起网络请求
     */
    private void requestData() {
        switch (type) {
            case 1:
                Observer<MTimeInTheatersMovie> mTimeInTheatersMovieObserver = new Observer<MTimeInTheatersMovie>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MTimeInTheatersMovie t) {
                        if (!t.getMs().isEmpty()){
                            mAdapter = new HotMTimeMovieListAdapter(t.getMs(),1);
                            mRecyclerView.setAdapter(mAdapter);

                            mAdapter.setOnItemClickListener((view, position) -> {
                                Log.i(TAG, "数据点位: 电影名称： " + t.getMs().get(position).getTCn());
                                Log.i(TAG, "数据点位: movieId： " + t.getMs().get(position).getMovieId());
                                Toast.makeText(mContext, "movieId: " + t.getMs().get(position).getMovieId(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(mContext, MTimeMovieDetailsActivity.class);
                                intent.putExtra(Util.ARG_MovieId, t.getMs().get(position).getMovieId());
                                intent.putExtra(Util.ARG_MoviePoster, t.getMs().get(position).getImg());
                                startActivity(intent);
                            });

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
                SingletonRetrofit.getInstance().fetchMTimeMovieInTheaters(mTimeInTheatersMovieObserver, Util.LocationId);
                break;
            case 2:
                Observer<MTimeComingMovie> mTimeComingMovieObserver = new Observer<MTimeComingMovie>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MTimeComingMovie t) {
                        if (!t.getMoviecomings().isEmpty()){
                            mAdapter = new HotMTimeMovieListAdapter(t.getMoviecomings(),2.0);
                            mRecyclerView.setAdapter(mAdapter);

                            /*mAdapter.setOnItemClickListener((view, position) -> {
                                Log.i(TAG, "数据点位: 电影名称： " + t.getMs().get(position).getTCn());
                                Log.i(TAG, "数据点位: movieId： " + t.getMs().get(position).getMovieId());
                                Toast.makeText(mContext, "movieId: " + t.getMs().get(position).getMovieId(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(mContext, MTimeMovieDetailsActivity.class);
                                intent.putExtra(Util.ARG_MovieId, t.getMs().get(position).getMovieId());
                                intent.putExtra(Util.ARG_MoviePoster, t.getMs().get(position).getImg());
                                startActivity(intent);
                            })*/;

                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                };
                SingletonRetrofit.getInstance().fetchMTimeMovieComingSoon(mTimeComingMovieObserver,Util.LocationId);
                break;
        }

    }








}
