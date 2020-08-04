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

import androidx.fragment.app.FragmentActivity;
import androidx.palette.graphics.Palette;

import com.uw.alice.R;


public class Function {

    private static final String TAG = "Function";


    /**
     * 获取显示器显示指标
     * @param context 上下文
     */
    public static void getDisplayMetrics(Context context){
        Log.i(TAG, "数据点位: 上下文 context: " + context);
        /**
         * 显示的逻辑密度。这是密度独立像素单位的比例因子，其中一个DIP是大约160 dpi屏幕（例如240x320、1.5“ x2”屏幕）上的一个像素，提供系统显示的基线。
         * 因此，在160dpi的屏幕上，此密度值将为1。在120 dpi屏幕上为0.75；等等
         *
         * 此值不完全符合实际屏幕尺寸（由xdpi和给出 ydpi），而是用于根据显示dpi的总体变化来逐步缩放整个UI的大小。
         * 例如，一个240x320的屏幕即使其宽度为1.8“，1.3”等，其密度也将为1。
         * 但是，如果将屏幕分辨率提高到320x480，但屏幕尺寸保持为1.5“ x2”，则密度将增加（可能是1.5）
         */
        float density = context.getResources().getDisplayMetrics().density;
        int densityDpi = context.getResources().getDisplayMetrics().densityDpi; //屏幕密度表示为每英寸点数
        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;//显示屏上显示的字体的比例因子。 这与密度density相同，不同之处在于可以在运行时根据用户对字体大小的喜好以较小的增量进行调整。
        int widthPixels = context.getResources().getDisplayMetrics().widthPixels; //可用显示尺寸的绝对宽度（以像素为单位）。
        int heightPixels = context.getResources().getDisplayMetrics().heightPixels; //可用显示尺寸的绝对高度（以像素为单位）。
        float xdpi = context.getResources().getDisplayMetrics().xdpi; //X尺寸屏幕每英寸的确切物理像素。
        float ydpi = context.getResources().getDisplayMetrics().ydpi; //屏幕上每英寸在Y维度上的确切物理像素。
        Log.i(TAG, "数据点位: 密度 density: " + density);
        Log.i(TAG, "数据点位: 密度Dpi densityDpi: " + densityDpi);
        Log.i(TAG, "数据点位: 标度密度 scaledDensity: " + scaledDensity);
        Log.i(TAG, "数据点位: 宽度像素 widthPixels: " + widthPixels);
        Log.i(TAG, "数据点位: 高度像素 scaledDensity: " + heightPixels);
        Log.i(TAG, "数据点位: XDPI xdpi: " + xdpi);
        Log.i(TAG, "数据点位: YDPI ydpi: " + ydpi);

    }


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
        final float density = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / density);
    }


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float density = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * density);
    }



    /**
     * 3列网格列表视图，返回最佳间距
     */
    public static int  getGridListSpace(Context context, int width) {
        Log.d(TAG, "数据点位：调用者Context: " + context);
        Log.d(TAG, "数据点位：手机像素宽度: " + width); //返回手机像素宽度  ex:720px
        int dp = px2dip(context,width); //px转dp 手机屏幕dp宽度
        Log.d(TAG, "数据点位：手机屏幕dp宽度: " + dp);
        int space =  (dp - 300) / 4;  //剩余的dp除去4份
        Log.d(TAG, "数据点位：空隙space: "+space);
        return space;
    }


    /**
     * 影人全部照片 返回item列表间隙
     */
    public static int getGridListItemWidth(Context context, int width) {
        Log.d(TAG, "数据点位：调用者Context: " + context);
        Log.d(TAG, "测试点位: 获取RecycleView布局宽度px " + width);
        int dp = px2dip(context,width);
        Log.d(TAG, "测试点位: 获取RecycleView布局宽度dp " + dp);
        int layoutWidth = (dp - 4) / 3;
        Log.d(TAG, "测试点位: 计算出item中合适的ImageView布局宽度dp(已减去空隙长度) " + layoutWidth);
        return layoutWidth;
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
