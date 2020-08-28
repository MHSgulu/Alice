package com.uw.alice.network.retrofit;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.uw.alice.data.model.BingWallpaper;
import com.uw.alice.data.model.Chat;
import com.uw.alice.data.model.DynamicGif;
import com.uw.alice.data.model.FilmMaker;
import com.uw.alice.data.model.FilmmakerPhoto;
import com.uw.alice.data.model.HotSpot;
import com.uw.alice.data.model.Idiom;
import com.uw.alice.data.model.IdiomKeyword;
import com.uw.alice.data.model.MTimeActorDetail;
import com.uw.alice.data.model.MTimeComingMovie;
import com.uw.alice.data.model.MTimeInTheatersMovie;
import com.uw.alice.data.model.MTimeMovieDetail;
import com.uw.alice.data.model.MobilePhone;
import com.uw.alice.data.model.Movie;
import com.uw.alice.data.model.MovieDetails;
import com.uw.alice.data.model.News;
import com.uw.alice.data.model.NewsChannel;
import com.uw.alice.data.model.NewsSearch;
import com.uw.alice.data.model.PictureJoke;
import com.uw.alice.data.model.Quotations;
import com.uw.alice.data.model.TaoGirls;
import com.uw.alice.data.model.TaoModelStyle;
import com.uw.alice.data.model.TextJoke;
import com.uw.alice.data.model.Wallpaper;
import com.uw.alice.data.util.Util;

import org.reactivestreams.Subscriber;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SingletonRetrofit  {

    private Retrofit retrofit,retrofit1,retrofit2,retrofit3,retrofit4,retrofit5,retrofit6,retrofit7;
    private final OkHttpClient okHttpClient;
    private APIService apiService,apiService1,apiService2,apiService3,apiService4,apiService5,apiService6,apiService7;


    private SingletonRetrofit() {

        //OkHttp支持连接，写入，读取和完整的呼叫超时
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .callTimeout(10,TimeUnit.SECONDS)
                /*.addNetworkInterceptor()*/
                .build();


        //服务器地址，基础请求路径，最好以"/"结尾
        //配置转化库，采用Gson  开启后，会自动把请求返回的结果（json字符串）自动转化成与其结构相符的实体。
        //配置回调库，采用RxJava2

        //空 基地址 不允许
        /*retrofit = new Retrofit.Builder()
                *//*.client(okHttpClient)*//*
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();*/

        //豆瓣API
        retrofit1 = new Retrofit.Builder()
                .baseUrl(Util.DOUBAN_URL_HTTPS)
                /*.client(okHttpClient)*/
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //京东万象API
        retrofit2 = new Retrofit.Builder()
                .baseUrl(Util.JDAPI_URL)
                /*.client(okHttpClient)*/
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //万维易源API
        retrofit3 = new Retrofit.Builder()
                .baseUrl(Util.SHOW_API_URL_HTTPS)
                /*.client(okHttpClient)*/
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //青云客智能机器人API
        retrofit4 = new Retrofit.Builder()
                .baseUrl(Util.QING_YUN_KE_API_URL)
                /*.client(okHttpClient)*/
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //必应每日壁纸API
        retrofit5 = new Retrofit.Builder()
                .baseUrl(Util.BING_API_URL)
                /*.client(okHttpClient)*/
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //时光网 API  1
        retrofit6 = new Retrofit.Builder()
                .baseUrl(Util.M_TIME_API_URL)
                /*.client(okHttpClient)*/
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //时光网 API  2
        retrofit7 = new Retrofit.Builder()
                .baseUrl(Util.MT_TIME_API_URL)
                /*.client(okHttpClient)*/
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        //豆瓣Service
        apiService1 = retrofit1.create(APIService.class);
        //京东万象Service
        apiService2 = retrofit2.create(APIService.class);
        //万维易源Service
        apiService3 = retrofit3.create(APIService.class);
        //青云客智能机器人Service
        apiService4 = retrofit4.create(APIService.class);
        //必应每日壁纸Service
        apiService5 = retrofit5.create(APIService.class);
        //时光网Service 1
        apiService6 = retrofit6.create(APIService.class);
        //时光网Service 2
        apiService7 = retrofit7.create(APIService.class);

    }

    //获取单例
    public static SingletonRetrofit getInstance(){
        return Wonderland.instance;
    }


    //单例  静态内部类模式    在访问SingletonRetrofit时创建单例
    private static class Wonderland{
        private static final SingletonRetrofit instance = new SingletonRetrofit();
    }



    /**
     * 查询 豆瓣电影Top250
     * @param observer  由调用者传过来的观察者对象
     * @param apiKey  key值
     * @param start  起始元素
     * @param count  返回结果的数量
     */
    public void queryTop250Movie(Observer<Movie> observer, String apiKey, int start, int count){
        apiService1.getTop250Movie(apiKey,start,count)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    /**
     * 查询 正在上映
     * @param observer  由调用者传过来的观察者对象
     * @param apiKey  key值
     */
    public void queryMovieOnShow(Observer<Movie> observer, String apiKey,int start,int count){
        apiService1.getFilmsOnShow(apiKey,start,count)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    /**
     * 查询 即将上映
     * @param observer  由调用者传过来的观察者对象
     * @param apiKey  key值
     */
    public void queryMovieUpcoming(Observer<Movie> observer, String apiKey,int start,int count){
        apiService1.getMovieComingSoon(apiKey,start,count)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }



    /**
     * 查询 电影条目信息
     * @param observer  由调用者传过来的观察者对象
     * @param apiKey  key值
     */
    public void requestFetchMovieDetails(Observer<MovieDetails> observer, String movieId,String apiKey){
        apiService1.fetchMovieDetails(movieId,apiKey)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    /**
     * 查询 影人条目信息
     * @param observer  由调用者传过来的观察者对象
     * @param apiKey  key值
     */
    public void requestFetchActorDetails(Observer<FilmMaker> observer, String actorId, String apiKey){
        apiService1.fetchActorDetails(actorId,apiKey)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }



    /**
     * 查询 影人相册全部图片
     * @param observer  由调用者传过来的观察者对象
     * @param apiKey  key值
     */
    public void requestFetchFilmmakerPhoto(Observer<FilmmakerPhoto> observer, String celebrityId, String apiKey,int start){
        apiService1.getFetchFilmmakerPhoto(celebrityId,apiKey,start,20)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }




    /**
     * 查询 豆瓣音乐搜索
     * @param observer 由调用者传过来的观察者对象
     *| q | 查询关键字 | q和tag必传其一 | q=%E7%99%BD%E6%97%A5%E6%A2%A6%E8%93%9D  白日梦蓝
     * | tag | 查询的tag | q和tag必传其一 |
     * | start | 取结果的offset | 默认为0 | 可不写
     * | count | 取结果的条数 | |           取1 比较好
     */
   /* public void querySearchMusic(Observer<Music> observer, String q, int start, int count){
        apiService1.searchMusic(q,start,count)
                .subscribeOn(Schedulers.io())
               // .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }*/



    /**
     * 获取 新闻
     * @param observer  由调用者传过来的观察者对象
     * @param channel  频道
     * @param num  数量 默认10，最大40
     * @param start  起始位置，默认0
     * @param appKey key值
     */
    public void getNews(Observer<News> observer, String channel, String num, String start, String appKey){
        apiService2.getNews(channel,num,start,appKey)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    /**
     * 获取 新闻频道
     * @param observer  由调用者传过来的观察者对象
     * @param appKey key值
     */
    public void getNewsChannel(Observer<NewsChannel> observer, String appKey){
        apiService2.getNewsChannel(appKey)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    /**
     * 获取 关键词查询新闻
     * @param observer  由调用者传过来的观察者对象
     * @param keyword 关键词
     * @param appKey key值
     */
    public void getSearchNews(Observer<NewsSearch> observer, String keyword, String appKey){
        apiService2.getSearchNews(keyword,appKey)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    /**
     *查询  热词排行
     * @param observer  由调用者传过来的观察者对象
     * @param typeId 分类id
     * @param appKey key值
     */
    public void getHotSpotRanking(Observer<HotSpot> observer, String typeId, String appKey){
        apiService2.getHotSpotRanking(typeId,appKey)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }



    /**
     *查询  动态搞笑图
     * @param observer 由调用者传过来的观察者对象
     * @param page 第几页
     * @param maxResult 最大结果集   20
     * @param appKey key值
     */
    public void getDynamicGif(Observer<DynamicGif> observer, int page, int maxResult, String appKey){
        apiService2.getDynamicGif(page,maxResult,appKey)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    /**
     *查询  图片笑话
     * @param observer 由调用者传过来的观察者对象
     * @param time 该时间以来最新的笑话. 格式：yyyy-MM-dd
     * @param page 第几页
     * @param maxResult 最大结果集   20
     * @param appKey key值
     */
    public void getPictureJoke(Observer<PictureJoke> observer, String time, int page, int maxResult, String appKey){
        apiService2.getPictureJoke(time,page,maxResult,appKey)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    /**
     *查询  文本笑话
     * @param observer 由调用者传过来的观察者对象
     * @param time 该时间以来最新的笑话. 格式：yyyy-MM-dd
     * @param page 第几页
     * @param maxResult 最大结果集   20
     * @param showapi_sign 固定值
     * @param appKey key值
     */
    public void getTextJoke(Observer<TextJoke> observer, String time, int page, String maxResult, String showapi_sign,String appKey){
        apiService2.getTextJoke(time,page,maxResult,showapi_sign,appKey)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    /**
     *查询  手机号码归属地查询
     * @param observer  由调用者传过来的观察者对象
     * @param shouji 手机号
     * @param appKey key值
     */
    public void queryMobilePhoneNumberHome(Observer<MobilePhone> observer, String shouji, String appKey){
        apiService2.queryMobilePhoneNumberHome(shouji,appKey)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    /**
     *查询  英文励志语录
     * @param observer  由调用者传过来的观察者对象
     * @param showapi_appid 易源应用id
     * @param showapi_sign 易源应用密钥
     * @param count 每次返回的句子数量，至少1条，最多10条 不必填 默认1
     */
    public void queryEnglishQuotations(Observer<Quotations> observer, String showapi_appid, String showapi_sign,String count){
        apiService3.queryEnglishQuotations(showapi_appid,showapi_sign,count)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    /**
     *查询  淘女郎模特风格
     * @param observer  由调用者传过来的观察者对象
     * @param showapi_appid 易源应用id
     * @param showapi_sign 易源应用密钥
     */
    public void queryTaoModelStyle(Observer<TaoModelStyle> observer, String showapi_appid, String showapi_sign){
        apiService3.queryTaoModelStyle(showapi_appid,showapi_sign)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    /**
     *查询  淘女郎模特
     * @param observer  由调用者传过来的观察者对象
     * @param showapi_appid 易源应用id
     * @param showapi_sign 易源应用密钥
     * @param type 淘女郎风格
     * @param page 查询第几页
     */
    public void queryTaoModelDataList(Observer<TaoGirls> observer, String showapi_appid, String showapi_sign,String type,String page){
        apiService3.queryTaoModelDataList(showapi_appid,showapi_sign,type,page)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    /**
     *查询  根据成语查注释
     * @param observer  由调用者传过来的观察者对象
     * @param showapi_appid 易源应用id
     * @param showapi_sign 易源应用密钥
     * @param keyword 要搜索的成语
     */
    public void queryIdiomAnnotation(Observer<Idiom> observer, String showapi_appid, String showapi_sign, String keyword){
        apiService3.queryIdiomAnnotation(showapi_appid,showapi_sign,keyword)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    /**
     *查询  根据关键字查成语
     * @param observer  由调用者传过来的观察者对象
     * @param showapi_appid 易源应用id
     * @param showapi_sign 易源应用密钥
     * @param keyword 要搜索的关键字
     * @param page 当前分页码，默认为1
     */
    public void queryIdiomList(Observer<IdiomKeyword> observer, String showapi_appid, String showapi_sign, String keyword, String page){
        apiService3.queryIdiomList(showapi_appid,showapi_sign,keyword,page)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    /**
     * 查询  必应每日壁纸
     * @param observer  由调用者传过来的观察者对象
     * @param showapi_appid 易源应用id
     * @param showapi_sign 易源应用密钥
     */
    public void queryBingDailyWallpaper(Observer<Wallpaper> observer, String showapi_appid, String showapi_sign){
        apiService3.queryBingDailyWallpaper(showapi_appid,showapi_sign)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    /**
     * 对话 青云客智能机器人
     * @param observer  由调用者传过来的观察者对象
     * @param key 固定参数free
     * @param msg 关键词
     */
    public void chatWithRobot(Observer<Chat> observer, String key, String msg){
        apiService4.chatWithRobot(key,msg)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    /**
     * 查询  官方必应每日壁纸
     * @param observer  由调用者传过来的观察者对象
     * @param format 返回数据格式
     * @param n 返回请求数量
     */
    public void queryOfficialBingWallpaper(Observer<BingWallpaper> observer, String format, String n){
        apiService5.queryOfficialBingWallpaper(format,n)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }



    /**
     * 时光网API 正在热映
     * @param observer  由调用者传过来的观察者对象
     * @param locationId 城市ID
     */
    public void fetchMTimeMovieInTheaters(Observer<MTimeInTheatersMovie> observer, String locationId){
        apiService6.getMTimeMovieInTheaters(locationId)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    /**
     * 时光网API 即将上映
     * @param observer  由调用者传过来的观察者对象
     * @param locationId 城市ID
     */
    public void fetchMTimeMovieComingSoon(Observer<MTimeComingMovie> observer, String locationId){
        apiService6.getMTimeMovieComingSoon(locationId)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    /**
     * 时光网API 影片详情
     * @param observer  由调用者传过来的观察者对象
     * @param locationId 城市ID
     * @param movieId    电影ID
     */
    public void fetchMTimeMovieDetail(Observer<MTimeMovieDetail> observer, String locationId,int movieId){
        apiService7.getMTimeMovieDetail(locationId,movieId)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    /**
     * 时光网API 影人详情
     * @param observer  由调用者传过来的观察者对象
     * @param cityId 城市ID
     * @param personId 影人ID
     */
    public void fetchMTimeActorDetail(Observer<MTimeActorDetail> observer, String personId, String cityId){
        apiService7.getMTimeActorDetail(personId,cityId)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }



}
