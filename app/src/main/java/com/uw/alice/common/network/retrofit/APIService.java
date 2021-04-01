package com.uw.alice.common.network.retrofit;


import com.uw.alice.data.model.BingWallpaper;
import com.uw.alice.data.model.Chat;
import com.uw.alice.data.model.DynamicGif;
import com.uw.alice.data.model.HotSpot;
import com.uw.alice.data.model.Idiom;
import com.uw.alice.data.model.IdiomKeyword;
import com.uw.alice.data.model.MobilePhone;
import com.uw.alice.data.model.News;
import com.uw.alice.data.model.NewsChannel;
import com.uw.alice.data.model.NewsSearch;
import com.uw.alice.data.model.PictureJoke;
import com.uw.alice.data.model.Quotations;
import com.uw.alice.data.model.TaoGirls;
import com.uw.alice.data.model.TaoModelStyle;
import com.uw.alice.data.model.TextJoke;
import com.uw.alice.data.model.Wallpaper;
import com.uw.alice.data.model.Weather;

import io.reactivex.Observable;
import io.reactivex.SingleObserver;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIService {


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
     * 京东万象  按照频道获取新闻数据
     * https://way.jd.com/jisuapi/get?channel=头条&num=10&start=0&appkey=您申请的APPKEY
     */
    @POST("jisuapi/get")
    Observable<News> getNews(@Query("channel") String channel, @Query("num") String num, @Query("start") String start, @Query("appkey") String appkey);


    /**
     * 京东万象 获取新闻频道
     * https://way.jd.com/jisuapi/channel?appkey=您申请的APPKEY
     */
    @POST("jisuapi/channel")
    Observable<NewsChannel> getNewsChannel(@Query("appkey") String appkey);


    /**
     * 京东万象  关键词查询新闻
     * https://way.jd.com/jisuapi/newSearch?keyword=爱丽丝&appkey=您申请的APPKEY
     */
    @POST("jisuapi/newSearch")
    //                                这里的字段之前没换为appkey  导致接口无法调用！！！
    Observable<NewsSearch> getSearchNews(@Query("keyword") String keyword, @Query("appkey") String appkey);


    /**
     * 京东万象  查询热词排行   限3000次/天
     * https://way.jd.com/showapi/rcInfo?typeId=1&appkey=您申请的APPKEY
     * "id":"1","name":"实时热点"
     */
    @POST("showapi/rcInfo")
    Observable<HotSpot> getHotSpotRanking(@Query("typeId") String typeId, @Query("appkey") String appkey);


    //笑话大全   限3000次/天

    /**
     * 京东万象  动态搞笑图
     * https://way.jd.com/showapi/dtgxt?page=1&maxResult=20&appkey=您申请的APPKEY
     * 2020-1-10测试数据发现  当前总页数992  972页之后的数据标题涉黄 还是个标题党 图片就那样 坚决不能调用
     */
    @POST("showapi/dtgxt")
    Observable<DynamicGif> getDynamicGif(@Query("page") int page, @Query("maxResult") int maxResult, @Query("appkey") String appkey);


    /**
     * 京东万象  图片笑话
     * https://way.jd.com/showapi/tpxh?time=2015-07-10&page=1&maxResult=20&appkey=您申请的APPKEY
     * 2020-1-11测试数据发现   当前总页数828  826(包含826页)页之后的数据 图片格式不对 无jpg后缀格式  最早的一条数据 来自2019-02-06
     * 所以只采用最后页为825
     */
    @POST("showapi/tpxh")
    Observable<PictureJoke> getPictureJoke(@Query("time") String time, @Query("page") int page,
                                           @Query("maxResult") int maxResult, @Query("appkey") String appkey);


    /**
     * 京东万象  文本笑话
     * https://way.jd.com/showapi/wbxh?time=2015-07-10&page=1&maxResult=20&showapi_sign=bd0592992b4d4050bfc927fe7a4db9f3&appkey=您申请的APPKEY
     */
    @POST("showapi/wbxh")
    Observable<TextJoke> getTextJoke(@Query("time") String time, @Query("page") int page,
                                     @Query("maxResult") String maxResult, @Query("showapi_sign") String showapi_sign, @Query("appkey") String appkey);


    /**
     * 京东万象  手机号码归属地查询    限1000次/天
     * https://way.jd.com/jisuapi/query4?shouji=13456755448&appkey=您申请的APPKEY
     */
    @POST("jisuapi/query4")
    Observable<MobilePhone> queryMobilePhoneNumberHome(@Query("shouji") String shouji, @Query("appkey") String appkey);


    /**
     * 京东万象  全国天气预报    限2000次/天
     * https://way.jd.com/jisuapi/weather1?appkey=您申请的APPKEY
     * https://way.jd.com/jisuapi/weather?city=安顺&cityid=111&citycode=101260301&appkey=bd1ee420d53dcd93f21d338cd6bebba3
     * 城市（city、cityid、citycode三者任选其一）
     */
    @POST("jisuapi/weather1")
    Observable<Weather> fetchWeatherForecast(@Query("city") String city, @Query("appkey") String appkey);

    /**
     * 万维易源API  英文励志语录   2020-1-11至2-11  当前为30天适用套餐
     * https://route.showapi.com/1211-1?showapi_appid=136754&showapi_sign=4b0c074ea24f4360a5f21905acab9b81&count=10
     */
    @POST("1211-1")
    Observable<Quotations> queryEnglishQuotations(@Query("showapi_appid") String showapi_appid, @Query("showapi_sign") String showapi_sign,
                                                  @Query("count") String count);


    /**
     * 万维易源API  淘女郎模特风格  2020-1-11至2-11  当前为30天适用套餐
     * https://route.showapi.com/126-1?showapi_appid=136754&showapi_sign=4b0c074ea24f4360a5f21905acab9b81
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
     * 万维易源API  淘女郎模特  2020-1-11至2-11  当前为30天适用套餐
     * https://route.showapi.com/126-2?showapi_appid=136754&showapi_sign=4b0c074ea24f4360a5f21905acab9b81&type=可爱&page=1
     */
    @POST("126-2")
    Observable<TaoGirls> queryTaoModelDataList(@Query("showapi_appid") String showapi_appid, @Query("showapi_sign") String showapi_sign,
                                               @Query("type") String type, @Query("page") String page);


    /**
     * 万维易源API  根据成语查注释    2020-1-11至2-11  当前为30天适用套餐
     * https://route.showapi.com/1196-2?showapi_appid=136754&showapi_sign=4b0c074ea24f4360a5f21905acab9b81&keyword=掩耳盗铃
     */
    @POST("1196-2")
    Observable<Idiom> queryIdiomAnnotation(@Query("showapi_appid") String showapi_appid, @Query("showapi_sign") String showapi_sign,
                                           @Query("keyword") String keyword);


    /**
     * 万维易源API  根据关键字查成语   2020-1-11至2-11  当前为30天适用套餐
     * https://route.showapi.com/1196-1?showapi_appid=136754&showapi_sign=4b0c074ea24f4360a5f21905acab9b81&keyword=爱&page=1
     * rows	String  10 不必填  返回的记录条数，默认为10
     */
    @POST("1196-1")
    Observable<IdiomKeyword> queryIdiomList(@Query("showapi_appid") String showapi_appid, @Query("showapi_sign") String showapi_sign,
                                            @Query("keyword") String keyword, @Query("page") String page);


    /**
     * 万维易源API  必应每日壁纸     2020-1-11至2-11  当前为30天适用套餐
     * https://route.showapi.com/1287-1?showapi_appid=136754&showapi_sign=4b0c074ea24f4360a5f21905acab9b81
     */
    @POST("1287-1")
    Observable<Wallpaper> queryBingDailyWallpaper(@Query("showapi_appid") String showapi_appid, @Query("showapi_sign") String showapi_sign);


    /**
     * 青云客智能机器人API      本API完全免费使用（建议频率控制在1000次/1小时以内）
     * https://api.qingyunke.com/api.php?key=free&appid=0&msg=关键词
     * <p>
     * 支持功能：天气、翻译、藏头诗、笑话、歌词、计算、域名信息/备案/收录查询、IP查询、手机号码归属、人工智能聊天。
     * 接口地址：http://api.qingyunke.com/api.php?key=free&appid=0&msg=关键词
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
     * 官方必应每日壁纸API
     * https://cn.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1&mkt=zh-CN
     * <p>
     * 今日壁纸
     * https://cn.bing.com/HPImageArchive.aspx?format=js&n=1
     * <p>
     * 近日壁纸 8张
     * https://cn.bing.com/HPImageArchive.aspx?format=js&n=8
     * <p>
     * 参数名称	值含义
     * format  （非必需）
     * 返回数据格式，不存在返回xml格式
     * js (一般使用这个，返回json格式)
     * xml（返回xml格式）
     * <p>
     * idx  (非必需)
     * 请求图片截止天数
     * 0 今天
     * -1 截止中明天 （预准备的）
     * 1 截止至昨天，类推（目前最多获取到7天前的图片）
     * <p>
     * n （必需）
     * 1-8 返回请求数量，目前最多一次获取8张
     * <p>
     * mkt  （非必需）
     * 地区
     * zh-CN
     */
    @POST("HPImageArchive.aspx")
    Observable<BingWallpaper> queryOfficialBingWallpaper(@Query("format") String format, @Query("n") String n);


}
