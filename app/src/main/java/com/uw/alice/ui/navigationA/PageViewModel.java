package com.uw.alice.ui.navigationA;

import android.util.Log;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.uw.alice.data.NewsDataSource;
import com.uw.alice.data.NewsRepository;
import com.uw.alice.data.Result;
import com.uw.alice.data.model.News;


public class PageViewModel extends ViewModel {

    private static final String TAG = "PageViewModel";
    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();
    private MutableLiveData<News> mNews = new MutableLiveData<>();
    private NewsRepository newsRepository;

    private LiveData<String> mText = Transformations.map(mIndex, new Function<Integer, String>() {
        @Override
        public String apply(Integer input) {
            Log.d(TAG, "input:"+input);
            return input.toString();
        }
    });

    void setIndex(int index) {
        mIndex.setValue(index);
    }


    public LiveData<String> getText() {
        return mText;
    }

    LiveData<News> getNews() {
        return mNews;
    }



    public void getNewsData(){
        // can be launched in a separate asynchronous job
        //可以在单独的异步作业中启动
        newsRepository = NewsRepository.getInstance(new NewsDataSource());
        Result<News> result = newsRepository.getNewsData();

        if (result instanceof Result.Success){
            mNews.setValue(((Result.Success<News>) result).getData());
        }

    }







}