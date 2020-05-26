package com.uw.alice.common;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import androidx.fragment.app.FragmentActivity;

public class Function {


    private static final String TAG = "Function";

    /**
     * 设置系统状态栏颜色
     */
    public static void setSystemStatusBarColor(FragmentActivity activity, int colorId) {

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




    /**
     * 获取状态栏高度
     */
    public static int getStatusBarHeight(Context context) {
        int statusBarHeight = 0;
        Resources res = context.getResources();
        int resourceId = res.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = res.getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }






    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int  px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }




    /**
     * 3列网格列表视图，返回最佳间距
     */
    public static int  getGridListSpace(Context context, int width) {
        Log.d(TAG, "width1: "+width); //返回手机像素宽度  ex:720px
        int dp = px2dip(context,width); //px转dp 手机屏幕dp宽度
        Log.d(TAG, "dp: "+dp);
        int space =  (dp - 300) / 4;  //剩余的dp除去4份
        Log.d(TAG, "space: "+space);
        return space;
    }



}
