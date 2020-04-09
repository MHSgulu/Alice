package com.uw.alice.data;

import com.uw.alice.data.model.News;

/**
 * 该类从远程数据源请求获取数据
 */
public class NewsRepository {

    private static volatile NewsRepository instance;
    private NewsDataSource dataSource;

    private News news = null;

    // private constructor : singleton access
    //私有构造函数：单例访问
    private NewsRepository(NewsDataSource dataSource){
        this.dataSource = dataSource;
    }

    public static NewsRepository getInstance(NewsDataSource dataSource){
        if (instance == null){
            instance = new NewsRepository(dataSource);
        }
        return instance;
    }


    private void setNewsStorage(News newsStorage){
        this.news = newsStorage;
    }


    public Result<News> getNewsData(){

        Result<News> result = dataSource.getNewsData();
        if (result instanceof Result.Success){
            setNewsStorage(((Result.Success<News>) result).getData());
        }
        return result;

    }




}



