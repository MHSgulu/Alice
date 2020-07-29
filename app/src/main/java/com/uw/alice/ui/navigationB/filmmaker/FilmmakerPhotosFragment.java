package com.uw.alice.ui.navigationB.filmmaker;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.uw.alice.R;
import com.uw.alice.common.Function;
import com.uw.alice.data.model.FilmmakerPhoto;
import com.uw.alice.data.util.Util;
import com.uw.alice.interfaces.OnItemClickListener;
import com.uw.alice.network.retrofit.SingletonRetrofit;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import cc.shinichi.library.ImagePreview;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class FilmmakerPhotosFragment extends BottomSheetDialogFragment {

    private static final String TAG = "FilmmakerPhotosFragment";
    private Context mContext;
    private TextView tvTitle;
    private RecyclerView mRecyclerView;
    private FilmMakerPhotoAdapter mAdapter;

    private List<FilmmakerPhoto.PhotosBean> mDataList = new ArrayList<>();
    private String filmmakerId; //影人ID
    private int currentIndex; //当前索引
    private int totalCount; //全部数量
    boolean isLoading = false; //加载中

    public FilmmakerPhotosFragment() {
    }

    public static FilmmakerPhotosFragment newInstance(String param) {
        Bundle args = new Bundle();
        args.putString(Util.ARG_FilmmakerId,param);
        FilmmakerPhotosFragment fragment = new FilmmakerPhotosFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_filmmaker_photos, container, false);
        mContext = getContext();
        tvTitle = root.findViewById(R.id.tv_title);
        mRecyclerView = root.findViewById(R.id.list_photo);

        LinearLayout llBack = root.findViewById(R.id.ll_back);
        llBack.setOnClickListener(v -> dismiss());

        if (getArguments() != null){
            filmmakerId = getArguments().getString(Util.ARG_FilmmakerId);
            fetchPhotos(0);
        }

        initListener();

        return root;
    }



    private void initListener() {

        //列表添加分割 位于此处会出现 不会出现布局错乱 间隙一点点变大现象
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                int index = parent.getChildAdapterPosition(view); //返回给定子视图对应的适配器位置。
                if(index % 3 == 0){
                    outRect.top = 1;
                    outRect.bottom = 1;
                    outRect.left = 4;
                    outRect.right = 0;
                }else if((index - 1) % 3 == 0){
                    outRect.top = 1;
                    outRect.bottom = 1;
                    outRect.left = 2;
                    outRect.right = 2;
                }else if((index - 2) % 3 == 0){
                    outRect.top = 1;
                    outRect.bottom = 1;
                    outRect.left = 0;
                    outRect.right = 0;
                }
            }
        });


        //列表滑动监听位于此处会出现 不会出现加倍请求此处现象
        /**
         *  mRecyclerView.canScrollVertically()
         *  检查此视图是否可以沿特定方向垂直滚动.
         *  @param direction 负号表示向上滚动，正号表示向下滚动。
         *  @return 如果可以沿指定方向滚动此视图，则为true，否则为false。
         */
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //判断当前列表状态         0表示RecyclerView当前不滚动。
                if (newState == RecyclerView.SCROLL_STATE_IDLE){
                    Log.d(TAG, "监听点位: 当前列表不滚动");
                    if (!mRecyclerView.canScrollVertically(1)){
                        Log.d(TAG, "监听点位: 当前列表无法向下滚动");
                        if (currentIndex < totalCount){
                            Log.d(TAG, "监听点位: 当前数据尚未加载完毕");
                            if (!isLoading){
                                Log.d(TAG, "监听点位:  当前并未加载数据中，进入加载数据状态");
                                isLoading = true;
                                fetchPhotos(currentIndex + 20);
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


    @Override
    public void onStart() {
        super.onStart();
        BottomSheetDialog dialog = (BottomSheetDialog) getDialog();
        //dialog.getWindow().findViewById(com.google.android.material.R.id.design_bottom_sheet);
        FrameLayout frameLayout = dialog.getDelegate().findViewById(com.google.android.material.R.id.design_bottom_sheet);
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) frameLayout.getLayoutParams();
        int height  = getResources().getDisplayMetrics().heightPixels;
        Log.d(TAG, "数据点位: 当前的高度px大小" + height);
        layoutParams.height = height; //布局的高度(决定最终的高度)
        BottomSheetBehavior.from(frameLayout).setPeekHeight(height); //初始弹出时的高度(决定初始的高度)
        //BottomSheetBehavior.from(frameLayout).setState(BottomSheetBehavior.STATE_EXPANDED);

    }

    /**
     * 获取影人照片信息
     */
    private void fetchPhotos(int index) {

        Observer<FilmmakerPhoto> filmmakerPhotoObserver = new Observer<FilmmakerPhoto>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(FilmmakerPhoto filmmakerPhoto) {
                tvTitle.setText(String.format("%s的照片(%s)",filmmakerPhoto.getCelebrity().getName(),filmmakerPhoto.getTotal()));
                if (!filmmakerPhoto.getPhotos().isEmpty()){
                    currentIndex = filmmakerPhoto.getStart();
                    Log.d(TAG, "数据点位: 图片的开始索引start: currentIndex： " + currentIndex);
                    totalCount = filmmakerPhoto.getTotal();
                    Log.d(TAG, "数据点位: 全部图片 totalCount： " + totalCount);
                    if (index == 0){
                        //设置item布局宽度方在onNext方法中方可生效，放在这个位置不会出现 每次加载完成 列表跳转到头部
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext,3);
                        mRecyclerView.setLayoutManager(gridLayoutManager);
                        mDataList = filmmakerPhoto.getPhotos();
                        mAdapter = new FilmMakerPhotoAdapter(mDataList,Function.getGridListItemWidth(mContext,gridLayoutManager.getWidth()));
                        mRecyclerView.setAdapter(mAdapter);
                    }else {
                        mDataList.addAll(filmmakerPhoto.getPhotos());
                        mAdapter.notifyDataSetChanged();
                    }


                    List<String> imgUrlList = mDataList.stream().map(FilmmakerPhoto.PhotosBean :: getImage).collect(Collectors.toList());

                    mAdapter.setOnItemClickListener((view, position) -> {
                        ImagePreview.getInstance()
                                .setContext(mContext)
                                .setIndex(position)
                                .setImageList(imgUrlList)
                                .setShowCloseButton(true)
                                .setEnableDragClose(true)
                                .setEnableUpDragClose(true)
                                .start();
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
        SingletonRetrofit.getInstance().requestFetchFilmmakerPhoto(filmmakerPhotoObserver,filmmakerId, Util.DOUBAN_APIKEY,index);

    }






}
