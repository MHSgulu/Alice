package com.uw.alice.ui.navigationB.mtime.hot;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.uw.alice.R;
import com.uw.alice.data.model.Movie;

import java.util.ArrayList;
import java.util.List;



public class HotMTimeMovieFragment extends Fragment {

    private static final String TAG = "HotMTimeMovieFragment";
    private static final String ARG_TYPE = "type";

    private Context mContext;
    private RecyclerView mRecyclerView;
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




}
