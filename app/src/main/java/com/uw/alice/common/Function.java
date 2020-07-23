package com.uw.alice.common;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.palette.graphics.Palette;

import com.uw.alice.R;


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


    /**
     * 获取图片主色调 同步方式
     */
    public static int fetchPaletteColor(Bitmap bitmap) {

        Palette palette = Palette.from(bitmap).generate();
        Palette.Swatch dominantSwatch = palette.getDominantSwatch();   //独特的一种
        /*Palette.Swatch s1 = palette.getVibrantSwatch();       //获取到充满活力的这种色调
        Palette.Swatch s2 = palette.getDarkVibrantSwatch();    //获取充满活力的黑
        Palette.Swatch s3 = palette.getLightVibrantSwatch();   //获取充满活力的亮
        Palette.Swatch s4 = palette.getMutedSwatch(); //获取柔和的色调
        Palette.Swatch s5 = palette.getDarkMutedSwatch(); //获取柔和的黑
        Palette.Swatch s6 = palette.getLightMutedSwatch();    //获取柔和的亮*/
        if (dominantSwatch != null) {
            return dominantSwatch.getRgb();
        }else {
            return R.color.black;
        }

    }


    /**
     * 打开手机自带浏览器启动url网址
     */
    public static void launchWebUrl(Context context,String weUrl) {
        //如需打开网页，请使用 ACTION_VIEW 操作，并在 Intent 数据中指定网址。
        Uri webUri = Uri.parse(weUrl);
        Intent intent = new Intent(Intent.ACTION_VIEW, webUri);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }
    }


    /**
     * 存放文本至剪贴板 长按输入框点击粘贴或者输入法智能提示粘贴到输入框中
     */
    public static void setTextToClipboard(Context context,String text) {
        //获取剪贴板服务的句柄。
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        //创建新的文本剪辑以放在剪贴板上
        ClipData clip = ClipData.newPlainText("label:"+text, text);
        if (clipboard != null) {
            //设置剪贴板的主剪辑
            clipboard.setPrimaryClip(clip);
        }
    }




}
