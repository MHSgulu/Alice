package com.uw.alice.data;

import android.util.Log;
import android.widget.Toast;

import com.uw.alice.data.model.News;
import com.uw.alice.network.retrofit.SingletonRetrofit;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class NewsDataSource {

    private static final String TAG = "NewsDataSource";
    private Result.Success<News> newsSuccess;
    private Result.Error error;

    public Result<News> getNewsData(){

        Observer<News> observer = new Observer<News>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(News news) {
                if (news != null){
                    newsSuccess = new Result.Success<>(news);
                }
            }

            @Override
            public void onError(Throwable e) {
                if (e != null){
                    error = new Result.Error(e);
                    Log.e(TAG, "onError:"+e.getMessage());
                }
            }

            @Override
            public void onComplete() {

            }
        };
        SingletonRetrofit.getInstance().getNews(observer,"头条","5","0","bd1ee420d53dcd93f21d338cd6bebba3");

        if (newsSuccess == null){
            return  error;
        }

        return newsSuccess;

    }


}
