package com.uw.alice.network.retrofit;



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

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {


    /**
     *   查询 豆瓣电影Top250
     *   http://api.douban.com/v2/movie/top250?apikey=0b2bdeda43b5688921839c8ecb20399b&start=0&count=1
     *   https://api.douban.com/v2/movie/top250?apikey=0b2bdeda43b5688921839c8ecb20399b&start=0&count=1
     *   突发奇想 https也可以 安卓9.0以上禁止http请求访问网站，禁止明文通信。
     */
    @GET("movie/top250")
    Observable<Movie> getTop250Movie(@Query("apikey") String apikey, @Query("start") int start, @Query("count") int count);


    /**
     * 正在热映
     *
     * city：所在城市，例如北京、上海等
     * start：分页使用，表示第几页
     * count：分页使用，表示数量
     * client：客户端信息。可为空
     * udid：用户 id。可为空
     *
     * 简：https://api.douban.com/v2/movie/in_theaters?apikey=0b2bdeda43b5688921839c8ecb20399b&start=0&count=200
     * 全：https://api.douban.com/v2/movie/in_theaters?apikey=0b2bdeda43b5688921839c8ecb20399b&city=%E5%8C%97%E4%BA%AC&start=0&count=100&client=&udid=
     */
    @GET("movie/in_theaters")
    Observable<Movie> getFilmsOnShow(@Query("apikey") String apiKey,@Query("start") int start,@Query("count") int count);



    /**
     * 即将上映
     *
     * city：所在城市，例如北京、上海等
     * start：分页使用，表示第几页
     * count：分页使用，表示数量
     * client：客户端信息。可为空
     * udid：用户 id。可为空
     *
     * 简：https://api.douban.com/v2/movie/coming_soon?apikey=0b2bdeda43b5688921839c8ecb20399b
     * 全：https://api.douban.com/v2/movie/coming_soon?apikey=0b2bdeda43b5688921839c8ecb20399b&city=%E5%8C%97%E4%BA%AC&start=0&count=100&client=&udid=
     */
    @GET("movie/coming_soon")
    Observable<Movie> getMovieComingSoon(@Query("apikey") String apiKey,@Query("start") int start,@Query("count") int count);




    /**
     * 电影条目信息
     *
     * 注解类型 网络请求方法是GET不是POST！！！！！ 导致http 403
     */

    @GET("movie/subject/{movieId}")
    Observable<MovieDetails> fetchMovieDetails(@Path("movieId") String movieId, @Query("apikey") String apiKey);



    /**
     * 影人条目信息
     *
     * http://api.douban.com/v2/movie/celebrity/1274254?apikey=0b2bdeda43b5688921839c8ecb20399b张子枫
     */

    @GET("movie/celebrity/{actorId}")
    Observable<FilmMaker> fetchActorDetails(@Path("actorId") String actorId, @Query("apikey") String apiKey);


    /**
     * 影人 全部图片
     * 1274254 张子枫
     * https://api.douban.com/v2/movie/celebrity/1274254/photos?apikey=0b2bdeda43b5688921839c8ecb20399b&start=0
     * https://api.douban.com/v2/movie/celebrity/1274254/photos?apikey=0b2bdeda43b5688921839c8ecb20399b&start=0&count=20
     */
    //                            此处少了一个 / 报错404
    @GET("movie/celebrity/{celebrityId}/photos")
    Observable<FilmmakerPhoto> getFetchFilmmakerPhoto(@Path("celebrityId") String celebrityId,
                                                      @Query("apikey") String apiKey, @Query("start") int start,@Query("count") int count
    );




    /**
     *   搜索 音乐
     *   https://api.douban.com/v2/music/search?q=%E7%99%BD%E6%97%A5%E6%A2%A6%E8%93%9D&start=0&count=1
     */
   /* @GET("music/search")
    Observable<Music> searchMusic(@Query("q") String q, @Query("start") int start, @Query("count") int count);*/





   /*
   *   新闻API     限1000次/天
   */

    /**
     *   京东万象  按照频道获取新闻数据
     *   https://way.jd.com/jisuapi/get?channel=头条&num=10&start=0&appkey=您申请的APPKEY
     */
    @POST("jisuapi/get")
    Observable<News> getNews(@Query("channel") String channel, @Query("num") 	String num, @Query("start") 	String start, @Query("appkey") String appkey);


    /**
     *   京东万象 获取新闻频道
     *   https://way.jd.com/jisuapi/channel?appkey=您申请的APPKEY
     */
    @POST("jisuapi/channel")
    Observable<NewsChannel> getNewsChannel(@Query("appkey") String appkey);


    /**
     *   京东万象  关键词查询新闻
     *   https://way.jd.com/jisuapi/newSearch?keyword=爱丽丝&appkey=您申请的APPKEY
     */
    @POST("jisuapi/newSearch")
    //                                这里的字段之前没换为appkey  导致接口无法调用！！！
    Observable<NewsSearch> getSearchNews(@Query("keyword") String keyword, @Query("appkey") String appkey);


    /**
     *   京东万象  查询热词排行   限3000次/天
     *   https://way.jd.com/showapi/rcInfo?typeId=1&appkey=您申请的APPKEY
     *   "id":"1","name":"实时热点"
     */
    @POST("showapi/rcInfo")
    Observable<HotSpot> getHotSpotRanking(@Query("typeId") String typeId, @Query("appkey") String appkey);



    //笑话大全   限3000次/天

    /**
     *   京东万象  动态搞笑图
     *   https://way.jd.com/showapi/dtgxt?page=1&maxResult=20&appkey=您申请的APPKEY
     *   2020-1-10测试数据发现  当前总页数992  972页之后的数据标题涉黄 还是个标题党 图片就那样 坚决不能调用
     */
    @POST("showapi/dtgxt")
    Observable<DynamicGif> getDynamicGif(@Query("page") int page, @Query("maxResult") int maxResult, @Query("appkey") String appkey);


    /**
     *   京东万象  图片笑话
     *   https://way.jd.com/showapi/tpxh?time=2015-07-10&page=1&maxResult=20&appkey=您申请的APPKEY
     *   2020-1-11测试数据发现   当前总页数828  826(包含826页)页之后的数据 图片格式不对 无jpg后缀格式  最早的一条数据 来自2019-02-06
     *   所以只采用最后页为825
     */
    @POST("showapi/tpxh")
    Observable<PictureJoke> getPictureJoke(@Query("time") String time, @Query("page") int page,
                                           @Query("maxResult") int maxResult, @Query("appkey") String appkey);


    /**
     *   京东万象  文本笑话
     *   https://way.jd.com/showapi/wbxh?time=2015-07-10&page=1&maxResult=20&showapi_sign=bd0592992b4d4050bfc927fe7a4db9f3&appkey=您申请的APPKEY
     */
    @POST("showapi/wbxh")
    Observable<TextJoke> getTextJoke(@Query("time") String time, @Query("page") int page,
                                     @Query("maxResult") String maxResult, @Query("showapi_sign") String showapi_sign, @Query("appkey") String appkey);


    /**
     *   京东万象  手机号码归属地查询    限1000次/天
     *   https://way.jd.com/jisuapi/query4?shouji=13456755448&appkey=您申请的APPKEY
     */
    @POST("jisuapi/query4")
    Observable<MobilePhone> queryMobilePhoneNumberHome(@Query("shouji") String shouji, @Query("appkey") String appkey);


    /**
     *   万维易源API  英文励志语录   2020-1-11至2-11  当前为30天适用套餐
     *   https://route.showapi.com/1211-1?showapi_appid=136754&showapi_sign=4b0c074ea24f4360a5f21905acab9b81&count=10
     */
    @POST("1211-1")
    Observable<Quotations> queryEnglishQuotations(@Query("showapi_appid") String showapi_appid, @Query("showapi_sign") String showapi_sign,
                                                  @Query("count") String count);


    /**
     *   万维易源API  淘女郎模特风格  2020-1-11至2-11  当前为30天适用套餐
     *   https://route.showapi.com/126-1?showapi_appid=136754&showapi_sign=4b0c074ea24f4360a5f21905acab9b81
     */
    @POST("126-1")
    Observable<TaoModelStyle> queryTaoModelStyle(@Query("showapi_appid") String showapi_appid, @Query("showapi_sign") String showapi_sign);

    /*
    *   2020-1-14 测试数据
    *   欧美  704页
    *   韩版  535页
    *   日系  245页
    *   英伦  40页
    *   OL风  45页
    *   学院  48页
    *   学院  75页
    *   性感  34页
    *   复古  17页
    *   街头  14页
    *   休闲  20页
    *   民族  3页
    *   甜美  26页
    *   运动  2页
    *   可爱  6页
    *   大码  3页
    *   中老年  3页
    *   其他  1页  两条数据
    */

    //2020-1-14 测试数据！！！  天坑！！！！ 最后一页的数据基本没有imglist这个字段 所有每当加载到最后页数据 就报空指针！！ 慎用且处理好最后数据

    /**
     *   万维易源API  淘女郎模特  2020-1-11至2-11  当前为30天适用套餐
     *   https://route.showapi.com/126-2?showapi_appid=136754&showapi_sign=4b0c074ea24f4360a5f21905acab9b81&type=可爱&page=1
     */
    @POST("126-2")
    Observable<TaoGirls> queryTaoModelDataList(@Query("showapi_appid") String showapi_appid, @Query("showapi_sign") String showapi_sign,
                                               @Query("type") String type, @Query("page") String page);


    /**
     *   万维易源API  根据成语查注释    2020-1-11至2-11  当前为30天适用套餐
     *   https://route.showapi.com/1196-2?showapi_appid=136754&showapi_sign=4b0c074ea24f4360a5f21905acab9b81&keyword=掩耳盗铃
     */
    @POST("1196-2")
    Observable<Idiom> queryIdiomAnnotation(@Query("showapi_appid") String showapi_appid, @Query("showapi_sign") String showapi_sign,
                                           @Query("keyword") String keyword);


    /**
     *   万维易源API  根据关键字查成语   2020-1-11至2-11  当前为30天适用套餐
     *   https://route.showapi.com/1196-1?showapi_appid=136754&showapi_sign=4b0c074ea24f4360a5f21905acab9b81&keyword=爱&page=1
     *   rows	String  10 不必填  返回的记录条数，默认为10
     */
    @POST("1196-1")
    Observable<IdiomKeyword> queryIdiomList(@Query("showapi_appid") String showapi_appid, @Query("showapi_sign") String showapi_sign,
                                            @Query("keyword") String keyword, @Query("page") String page);


    /**
     *   万维易源API  必应每日壁纸     2020-1-11至2-11  当前为30天适用套餐
     *   https://route.showapi.com/1287-1?showapi_appid=136754&showapi_sign=4b0c074ea24f4360a5f21905acab9b81
     */
    @POST("1287-1")
    Observable<Wallpaper> queryBingDailyWallpaper(@Query("showapi_appid") String showapi_appid, @Query("showapi_sign") String showapi_sign);


    /**
     *   青云客智能机器人API      本API完全免费使用（建议频率控制在1000次/1小时以内）
     *   https://api.qingyunke.com/api.php?key=free&appid=0&msg=关键词
     *
     *   支持功能：天气、翻译、藏头诗、笑话、歌词、计算、域名信息/备案/收录查询、IP查询、手机号码归属、人工智能聊天。
     *   接口地址：http://api.qingyunke.com/api.php?key=free&appid=0&msg=关键词
     * 　　　　　key　固定参数free
     * 　　　　　appid 设置为0，表示智能识别，可忽略此参数
     * 　　　　　msg　关键词，请参考下方参数示例，该参数可智能识别，该值请经过 urlencode 处理后再提交
     * 返回结果：{"result":0,"content":"内容"}
     * 　　　　　result　状态，0表示正常，其它数字表示错误
     * 　　　　　content　信息内容
     */
    @POST("api.php")
    Observable<Chat> chatWithRobot(@Query("key") String key, @Query("msg") String msg);



    /**
     *   官方必应每日壁纸API
     *   https://cn.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1&mkt=zh-CN
     *
     *    今日壁纸
     *   https://cn.bing.com/HPImageArchive.aspx?format=js&n=1
     *
     *    近日壁纸 8张
     *   https://cn.bing.com/HPImageArchive.aspx?format=js&n=8
     *
     * 参数名称	值含义
     * format  （非必需）
     * 返回数据格式，不存在返回xml格式
     * js (一般使用这个，返回json格式)
     * xml（返回xml格式）
     *
     * idx  (非必需)
     * 请求图片截止天数
     * 0 今天
     * -1 截止中明天 （预准备的）
     * 1 截止至昨天，类推（目前最多获取到7天前的图片）
     *
     * n （必需）
     * 1-8 返回请求数量，目前最多一次获取8张
     *
     * mkt  （非必需）
     * 地区
     * zh-CN
     */
    @POST("HPImageArchive.aspx")
    Observable<BingWallpaper> queryOfficialBingWallpaper(@Query("format") String format, @Query("n") String n);



    /**
     * 时光网API 正在热映
     * url：https://api-m.mtime.cn/Showtime/LocationMovies.api?locationId=?
     *
     * 示例 url：https://api-m.mtime.cn/Showtime/LocationMovies.api?locationId=290
     *
     * 参数名称	值含义
     * bImg：???
     * date：日期
     * lid：ms 数量 x 10？
     * ms：具体正在热映电影信息
     * aN1：演员1
     * aN2：演员2
     * cC：今日上映该电影的影院数量，同NearestCinemaCount
     * d：影片时长
     * dN：导演
     * def：???
     * id：影片 id，需要提供给影片详情
     * r：影片评分
     * rd：影片上映时间
     * sC：???
     * t：影片名
     * tCn：影片中文名
     * tEn：影片英文名
     * versions：影片观影类型，如 3D、IMAX 等
     */
    @GET("Showtime/LocationMovies.api")
    Observable<MTimeInTheatersMovie> getMTimeMovieInTheaters(@Query("locationId") String locationId);


    /**
     * 时光网API 即将上映
     * url：`https://api-m.mtime.cn/Movie/MovieComingNew.api?locationId=?`
     *
     * 示例 url：https://api-m.mtime.cn/Movie/MovieComingNew.api?locationId=290
     *
     * 参数名称	值含义
     *首先大体分为两个部分 ——
     *
     * attention：最受关注
     * moviecomings：即将上映
     * 接下来就是里面具体的字段：
     *
     * videos：预告片，不过我们无权访问
     */
    @GET("Movie/MovieComingNew.api")
    Observable<MTimeComingMovie> getMTimeMovieComingSoon(@Query("locationId") String locationId);


    /**
     * 时光网API 影片详情
     * url：https://ticket-api-m.mtime.cn/movie/detail.api?locationId=?&movieId=?
     *
     * 示例 url：https://ticket-api-m.mtime.cn/movie/detail.api?locationId=290&movieId=125805
     *
     * 参数名称	值含义
     * basic：具体内容
     * actors：演员信息
     * actorId：演员 id
     * img：演员照片
     * name：演员名
     * nameEn：演员英文名
     * roleImg：影片中饰演角色图片
     * roleName：影片中饰演角色名字
     * award：获得的奖项？
     * commentSpecial：一句话总结该电影
     * community：???
     * director：导演信息
     * hotRanking：热映排行榜
     * img：剧照
     * releaseArea：上映地区
     * stageImg：影片剧照
     * story：剧情简介
     * video：预告片
     * boxOffice：专业解读内容
     * ranking：票房排名
     * todayBox：今日实时票房量
     * todayBoxDes 和 todayBoxDesUnit：今日实时票房量
     * totalBox 和 totalBoxUnit：累计票房量
     */
    @GET("movie/detail.api")
    Observable<MTimeMovieDetail> getMTimeMovieDetail(@Query("locationId") String locationId, @Query("movieId") int movieId);


    /**
     * 时光网API 影人详情
     * url：https://ticket-api-m.mtime.cn/person/detail.api?personId=?&cityId=？
     *
     * 示例 url：https://ticket-api-m.mtime.cn/person/detail.api?personId=892908&cityId=290
     * 示例 url：https://api-m.mtime.cn/person/detail.api?personId=915334&cityId=292
     * 以上示例返回的数据字段略有不同导致实体类不对应，导致取出的数据为null,造成空指针报错，排查了很久。
     */
    @GET("person/detail.api")
    Observable<MTimeActorDetail> getMTimeActorDetail(@Query("personId") String personId, @Query("cityId") String cityId);





}
