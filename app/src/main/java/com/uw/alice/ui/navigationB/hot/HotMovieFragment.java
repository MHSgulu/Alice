package com.uw.alice.ui.navigationB.hot;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.uw.alice.R;
import com.uw.alice.common.Function;
import com.uw.alice.data.model.Movie;
import com.uw.alice.data.util.Util;
import com.uw.alice.interfaces.OnItemClickListener;
import com.uw.alice.network.retrofit.SingletonRetrofit;
import com.uw.alice.ui.navigationB.MovieShowingUpAdapter;
import com.uw.alice.ui.navigationB.movie.MovieDetailsActivity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class HotMovieFragment extends Fragment {

    private static final String TAG = "HotMovieFragment";
    private Context mContext;
    private static final String ARG_TYPE = "type";

    private RecyclerView mRecyclerView;
    private HotMovieListAdapter mAdapter;
    private List<Movie.SubjectsBean> mDataList = new ArrayList<>();
    private int type;
    private int start = 0; //当前索引
    private int total = 0; //全部数量
    boolean isLoading = false; //是否加载中

    /**
     * 用于实例化片段的片段管理器的强制空构造函数(e.g. 屏幕方向改变时).
     */
    public HotMovieFragment() {
    }


    public static HotMovieFragment newInstance(int type) {
        HotMovieFragment fragment = new HotMovieFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getInt(ARG_TYPE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        mContext = getContext();
        mRecyclerView = view.findViewById(R.id.list);
        initScrollListener();
        requestData(0);

        return view;
    }


    /**
     * list滑动监听
     */
    private void initScrollListener() {

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE){
                    Log.d(TAG, "监听点位: 当前列表不滚动");
                    if (!mRecyclerView.canScrollVertically(1)){
                        Log.d(TAG, "监听点位: 当前列表无法向下滚动");
                        if (start < total){
                            Log.d(TAG, "监听点位: 当前数据尚未加载完毕");
                            if (!isLoading){
                                Log.d(TAG, "监听点位:  当前并未加载数据中，进入加载数据状态");
                                isLoading = true;
                                requestData(start + 20);
                            }else {
                                Log.d(TAG, "监听点位: 当前正在加载数据状态");
                            }
                        }else {
                            Log.d(TAG, "监听点位: 当前数据尚已加载完毕");
                        }
                    }else {
                        Log.d(TAG, "监听点位: 当前列表可以向下滚动");
                    }
                }else {
                    Log.d(TAG, "监听点位: 当前列表滚动中");
                }
                isLoading = false;
            }
        });

    }

    /**
     * 发起网络请求
     */
    private void requestData(int index) {

        Observer<Movie> movieObserver = new Observer<Movie>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(final Movie movie) {
                start = movie.getStart();
                total = movie.getTotal();
                Log.i(TAG, "数据点位:  start: " + start);
                Log.i(TAG, "数据点位:  total: " + total);
                if (!movie.getSubjects().isEmpty()){
                    if (index == 0){
                        mDataList = movie.getSubjects();
                        mAdapter = new HotMovieListAdapter(mDataList);
                        mRecyclerView.setAdapter(mAdapter);
                    }else {
                        mDataList.addAll(movie.getSubjects());
                        mAdapter.notifyDataSetChanged();
                    }
                }

                mAdapter.setOnItemClickListener((view, position) -> {
                    //Toast.makeText(mContext, movie.getSubjects().get(position).getId(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mContext,MovieDetailsActivity.class);
                    Log.i(TAG, "数据点位： id:" + movie.getSubjects().get(position).getId());
                    intent.putExtra("MovieId",movie.getSubjects().get(position).getId());
                    intent.putExtra("MoviePoster",movie.getSubjects().get(position).getImages().getMedium());
                    startActivity(intent);
                });

            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError:" + e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        };
        if (type == 1){
            SingletonRetrofit.getInstance().queryMovieOnShow(movieObserver, Util.DOUBAN_APIKEY, index,20);
        }else {
            SingletonRetrofit.getInstance().queryMovieUpcoming(movieObserver, Util.DOUBAN_APIKEY, index,20);
        }


    }


}
