package com.uw.alice.data.util;

import android.content.Context;
import android.content.res.Resources;
import android.view.Window;
import android.view.WindowManager;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.uw.alice.MainActivity;
import com.uw.alice.R;

public class Util {

    //豆瓣Url http 版
    public static final String DOUBAN_URL = "http://api.douban.com/v2/";
    //豆瓣Url https 版
    public static final String DOUBAN_URL_HTTPS = "https://api.douban.com/v2/";
    //京东万象API
    public static final String JDAPI_URL = "https://way.jd.com/";
    //万维易源API  http
    public static final String SHOW_API_URL_HTTP = "http://route.showapi.com/";
    //万维易源API  https
    public static final String SHOW_API_URL_HTTPS = "https://route.showapi.com/";
    //青云客智能机器人API
    public static final String QING_YUN_KE_API_URL = "http://api.qingyunke.com/";
    //官方必应每日壁纸API
    public static final String BING_API_URL = "https://cn.bing.com/";



    //京东万象apikey
    public static final String JDAPI_KEY = "bd1ee420d53dcd93f21d338cd6bebba3";

    //万维易源 密钥secret
    public static final String ShowApi_AppId = "136754";
    //万维易源 密钥secret
    public static final String ShowApi_Secret = "4b0c074ea24f4360a5f21905acab9b81";

    //成功码
    public static final String QUERY_SUCCESS_CODE = "10000";
    //调用数据达到上限
    public static final String ERROR_CODE_LIMIT = "10040";

    //豆瓣apikey
    public static final String DOUBAN_APIKEY = "0b2bdeda43b5688921839c8ecb20399b";

    //intent 标识符
    public static final String NewsTitle = "NewsTitle";
    public static final String NewsSrc = "NewsSrc";
    public static final String NewsTime = "NewsTime";
    public static final String NewsContent = "NewsContent";
    public static final String HotWordName = "HotWordName";
    public static final String TextJokeTitle = "TextJokeTitle";
    public static final String TextJokeContent = "TextJokeContent";

    //笑话大全1页数据量的最大结果集
    public static final int MaxResult = 20;
    public static final String MaxResult_String = "20";
    //笑话大全  该时间以来最新的笑话. 格式：yyyy-MM-dd
    public static final String Time = "2019-01-01";
    //文本笑话 showapi_sign
    public static final String Showapi_Sign = "bd0592992b4d4050bfc927fe7a4db9f3";

    //英文励志语录1页数据量的最大数量
    public static final String Max_Count = "10";




    //设置系统状态栏颜色
    //setSystemStatusBarColor();
    public static void setSystemStatusBarColor(FragmentActivity activity,int colorId) {

        /*
         * APi 19   Android 4.4.2以上  <item name="android:windowTranslucentStatus">true</item>
         * */
        /*Window window = MainActivity.this.getWindow();
        ViewGroup decorViewGroup = (ViewGroup) window.getDecorView();
        View statusBarView = new View(window.getContext());
        int statusBarHeight = getStatusBarHeight(window.getContext());
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, statusBarHeight);
        params.gravity = Gravity.TOP;
        statusBarView.setLayoutParams(params);
        statusBarView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        decorViewGroup.addView(statusBarView);*/

        /*
         * API 21  Android 5.0.1以上  window.setStatusBarColor(color);
         * */
        Window window = activity.getWindow();
        //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //设置状态栏颜色
        window.setStatusBarColor(activity.getColor(colorId));


    }




    //获取状态栏高度
    public static int getStatusBarHeight(Context context) {
        int statusBarHeight = 0;
        Resources res = context.getResources();
        int resourceId = res.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = res.getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }







}
