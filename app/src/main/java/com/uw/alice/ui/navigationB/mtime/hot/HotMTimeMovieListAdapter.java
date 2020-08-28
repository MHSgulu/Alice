package com.uw.alice.ui.navigationB.mtime.hot;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.uw.alice.R;
import com.uw.alice.assist.MoviePosterAdapter;
import com.uw.alice.data.model.MTimeComingMovie;
import com.uw.alice.data.model.MTimeInTheatersMovie;
import com.uw.alice.data.model.MTimeMovieDetail;
import com.uw.alice.data.util.Util;
import com.uw.alice.interfaces.OnItemClickListener;
import com.uw.alice.network.retrofit.SingletonRetrofit;
import com.willy.ratingbar.BaseRatingBar;
import com.youth.banner.Banner;
import com.youth.banner.config.IndicatorConfig;
import com.youth.banner.indicator.CircleIndicator;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class HotMTimeMovieListAdapter extends RecyclerView.Adapter<HotMTimeMovieListAdapter.ViewHolder> {

    private static final String TAG = "HotMTimeMovieListAdapter";
    private Context mContext;
    private List<MTimeInTheatersMovie.MsBean> mList;
    private List<MTimeComingMovie.MoviecomingsBean> mList2;
    private OnItemClickListener onItemClickListener;
    private int mType = 0;
    private double mSign = 0;

    HotMTimeMovieListAdapter(List<MTimeInTheatersMovie.MsBean> list,int type) {
        mList = list;
        mType = type;
    }

    HotMTimeMovieListAdapter(List<MTimeComingMovie.MoviecomingsBean> list,double type) {
        mList2 = list;
        mSign = type;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }


    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hot_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NotNull final ViewHolder holder, int position) {
        float density = mContext.getResources().getDisplayMetrics().density;
        int widthPixels = mContext.getResources().getDisplayMetrics().widthPixels;
        float bannerWidthPx =  widthPixels - (15 + 100 + 10 + 15) * density;
        float bannerHeightPx =  150 * density;
        //Log.i(TAG, "数据点位: bannerWidthPx: " + bannerWidthPx);
        //Log.i(TAG, "数据点位: bannerHeightPx: " + bannerHeightPx);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int)bannerWidthPx, (int)bannerHeightPx);
        holder.cardView.setLayoutParams(params);

        if (mType == 1){
            MTimeInTheatersMovie.MsBean result = mList.get(position);
            Glide.with(mContext).load(result.getImg()).placeholder(R.mipmap.icon_no_img).into(holder.ivCover);
            holder.tvMovieName.setText(result.getTCn());
            holder.tvYear.setText(String.format("(%s)",result.getYear()));
            holder.baseRatingBar.setRating((float) result.getR() / 2);
            holder.tvMovieScore.setText(String.valueOf(result.getR()));

            Observer<MTimeMovieDetail> mTimeMovieDetailObserver = new Observer<MTimeMovieDetail>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(MTimeMovieDetail t) {
                    if (t.getCode().equals("1") || t.getMsg().equals("成功")){
                        holder.tvMovieIntroduction.setText(t.getData().getBasic().getStory());
                        if (!t.getData().getBasic().getType().isEmpty()){
                            holder.labelRecyclerView.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
                            holder.labelRecyclerView.setAdapter(new HotMovieLabelAdapter(t.getData().getBasic().getType()));
                        }

                        List<String> moviePosterList;
                        if (t.getData().getBasic().getStageImg().getList().size() > 4){
                            moviePosterList = t.getData().getBasic().getStageImg().getList()
                                    .stream().map(MTimeMovieDetail.DataBean.BasicBean.StageImgBean.ListBean::getImgUrl)
                                    .collect(Collectors.toList())
                                    .subList(0,4);
                        }else {
                            moviePosterList = t.getData().getBasic().getStageImg().getList()
                                    .stream().map(MTimeMovieDetail.DataBean.BasicBean.StageImgBean.ListBean::getImgUrl)
                                    .collect(Collectors.toList());
                        }
                        holder.banner.setAdapter(new MoviePosterAdapter(moviePosterList))
                                .setIndicator(new CircleIndicator(mContext))
                                .setIndicatorSelectedColorRes(R.color.colorNavigationB)
                                .setIndicatorGravity(IndicatorConfig.Direction.LEFT)
                                .isAutoLoop(false);
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
            SingletonRetrofit.getInstance().fetchMTimeMovieDetail(mTimeMovieDetailObserver, Util.LocationId,result.getId());
        }
        else if (mSign == 2){
            MTimeComingMovie.MoviecomingsBean result = mList2.get(position);
            Glide.with(mContext).load(result.getImage()).placeholder(R.mipmap.icon_no_img).into(holder.ivCover);
            holder.tvMovieName.setText(result.getTitle());
            holder.tvYear.setText(String.format("(%s)",result.getRYear()));
            holder.baseRatingBar.setVisibility(View.INVISIBLE);
            holder.tvMovieScore.setVisibility(View.GONE);
            holder.llDate.setVisibility(View.VISIBLE);
            holder.tvDate.setText(result.getReleaseDate());

            Observer<MTimeMovieDetail> mTimeMovieDetailObserver = new Observer<MTimeMovieDetail>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(MTimeMovieDetail t) {
                    if (t.getCode().equals("1") || t.getMsg().equals("成功")){
                        holder.tvMovieIntroduction.setText(t.getData().getBasic().getStory());
                        if (!t.getData().getBasic().getType().isEmpty()){
                            holder.labelRecyclerView.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
                            holder.labelRecyclerView.setAdapter(new HotMovieLabelAdapter(t.getData().getBasic().getType()));
                        }

                        List<String> moviePosterList;
                        if (t.getData().getBasic().getStageImg().getList().size() > 4){
                            moviePosterList = t.getData().getBasic().getStageImg().getList()
                                    .stream().map(MTimeMovieDetail.DataBean.BasicBean.StageImgBean.ListBean::getImgUrl)
                                    .collect(Collectors.toList())
                                    .subList(0,4);
                        }else {
                            moviePosterList = t.getData().getBasic().getStageImg().getList()
                                    .stream().map(MTimeMovieDetail.DataBean.BasicBean.StageImgBean.ListBean::getImgUrl)
                                    .collect(Collectors.toList());
                        }
                        holder.banner.setAdapter(new MoviePosterAdapter(moviePosterList))
                                .setIndicator(new CircleIndicator(mContext))
                                .setIndicatorSelectedColorRes(R.color.colorNavigationB)
                                .setIndicatorGravity(IndicatorConfig.Direction.LEFT)
                                .isAutoLoop(false);
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
            SingletonRetrofit.getInstance().fetchMTimeMovieDetail(mTimeMovieDetailObserver, Util.LocationId,result.getId());
        }



        if (onItemClickListener != null){
            holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClick(holder.itemView,holder.getLayoutPosition()));
        }



    }

    @Override
    public int getItemCount() {
        if (mType ==1){
            return mList.size();
        }else if (mSign == 2){
            return mList2.size();
        }
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView ivCover;
        final Banner banner;
        final TextView tvMovieName,tvYear,tvMovieScore,tvMovieIntroduction,tvDate;
        final BaseRatingBar baseRatingBar;
        final RecyclerView labelRecyclerView;
        final CardView cardView;
        final LinearLayout llDate;

        public ViewHolder(View view) {
            super(view);
            ivCover = view.findViewById(R.id.iv_cover);
            banner = view.findViewById(R.id.banner);
            tvMovieName = view.findViewById(R.id.tv_movie_name);
            tvYear = view.findViewById(R.id.tv_year);
            baseRatingBar = view.findViewById(R.id.baseRatingBar);
            tvMovieScore = view.findViewById(R.id.tv_movie_score);
            tvMovieIntroduction = view.findViewById(R.id.tv_movie_introduction);
            labelRecyclerView = view.findViewById(R.id.list_label);
            cardView = view.findViewById(R.id.card_banner);
            llDate = view.findViewById(R.id.ll_date);
            tvDate = view.findViewById(R.id.tv_date);
        }

    }

}
