package com.uw.alice.common;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.fragment.app.FragmentActivity;
import androidx.palette.graphics.Palette;

import com.uw.alice.R;


public class Function {

    private static final String TAG = "Function";

    //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR); //设置系统状态黑色字体


    /**
     * 窗口标志：显示此窗口时，隐藏所有屏幕装饰（例如状态栏）。 这允许窗口自己使用整个显示空间
     * 状态栏将在以下情况下隐藏
     * 设置了此标志的应用程序窗口位于顶层。
     * 全屏窗口将忽略该窗口的{@link # SOFT_INPUT_ADJUST_RESIZE}字段的值{@link ＃ softInputMode}
     * 窗口将保持全屏显示，并且不会调整大小
     */
    public static void setWindowFullScreen(Activity activity){
        Window window = activity.getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }


    /**
     *  延伸显示区域到刘海 适用于全屏显示 启动页 大图 游戏 视频 等特殊场景
     */
    public static void setAdaptationDisplayCutout(Activity activity){
        Window window = activity.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        window.setAttributes(lp);
        // 设置页面全屏显示
        final View decorView = window.getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
    }


    /**
     * 获取显示器显示指标
     * @param context 上下文
     */
    public static void getDisplayMetrics(Context context){
        Log.i(TAG, "数据点位: 上下文 context: " + context);
        /*
          显示的逻辑密度。这是密度独立像素单位的比例因子，其中一个DIP是大约160 dpi屏幕（例如240x320、1.5“ x2”屏幕）上的一个像素，提供系统显示的基线。
          因此，在160dpi的屏幕上，此密度值将为1。在120 dpi屏幕上为0.75；等等
          此值不完全符合实际屏幕尺寸（由xdpi和给出 ydpi），而是用于根据显示dpi的总体变化来逐步缩放整个UI的大小。
          例如，一个240x320的屏幕即使其宽度为1.8“，1.3”等，其密度也将为1。
          但是，如果将屏幕分辨率提高到320x480，但屏幕尺寸保持为1.5“ x2”，则密度将增加（可能是1.5）
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
        Palette.Swatch dominant = palette.getDominantSwatch();   //Dominant 返回调色板中的主要色板。主要色板定义为调色板中人口（频率）最大的色板
        //Palette.Swatch s1 = palette.getVibrantSwatch();       //Vibrant  获取到充满活力的这种色调 返回调色板中最有活力的色板。 可能为空。
        //Palette.Swatch s2 = palette.getDarkVibrantSwatch();    //Dark Vibrant 获取充满活力的黑 从调板返回暗淡而充满活力的色板。 可能为空。
        //Palette.Swatch s3 = palette.getLightVibrantSwatch();   //Light Vibrant 获取充满活力的亮 从调板中返回明亮且充满活力的色板。 可能为空。
        Palette.Swatch muted = palette.getMutedSwatch(); //Muted 获取柔和的色调 从调色板返回一个静音的色板。 可能为空
        Palette.Swatch darkMuted = palette.getDarkMutedSwatch(); //Dark Muted 从调色板返回一个静音的深色色板。 可能为空。
        //Palette.Swatch s6 = palette.getLightMutedSwatch();    //Light Muted 获取柔和的亮 从调色板返回一个静音的浅色样。 可能为空。

        /**
         * 第一种 取颜色值方法 首选图片主色调 好看基本不为空但是会经常取到浅色调  通过第一轮测试 基本满意
         */
        if (dominant != null) {
            int color = dominant.getRgb();
            Log.i(TAG, "数据点位:  当前Dominant颜色为：" + color);
            if (Function.getColorBrightness(color) < 0.5) {
                Log.i(TAG, "判断点位:  当前颜色为浅色,准备更换为其他颜色");
                if (darkMuted != null){
                    Log.i(TAG, "数据点位:  当前darkMuted颜色为：" + darkMuted.getRgb());
                    return darkMuted.getRgb();
                }else if (muted != null){
                    Log.i(TAG, "数据点位:  darkMuted颜色为空，更换muted颜色：");
                    Log.i(TAG, "数据点位:  当前muted颜色为：" + muted.getRgb());
                    return muted.getRgb();
                }
            }
            else {
                Log.i(TAG, "判断点位:  当前颜色为深色");
                return dominant.getRgb();
            }
            return R.color.darkGrey;
        }else {
            return R.color.darkGrey;
        }


        /**
         * 第二种 取颜色值方法 首选图片darkMuted深色  不太好看但通常都是深色调
         */
        /*if (darkMuted != null){
            Log.i(TAG, "数据点位:  当前darkMuted颜色为：" + darkMuted.getRgb());
            return darkMuted.getRgb();
        }else{
            if (muted != null) {
                Log.i(TAG, "数据点位:  当前muted颜色为：" + muted.getRgb());
                return muted.getRgb();
            }
            else {
                return R.color.darkGrey;
            }
        }*/



    }


    /**
     * 判断当前颜色为浅色还是深色 亮色还是暗色
     */
    public static double getColorBrightness(int color) {
        return 1 - (0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(color)) / 255;
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



    /**
     * 如需打开网页，请使用 ACTION_VIEW 操作，并在 Intent 数据中指定网址。
     */
    public static void openWebPage(Context context,String weUrl) {
        Uri webPage = Uri.parse(weUrl);
        Intent intent = new Intent(Intent.ACTION_VIEW, webPage);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }
    }



    /**
     * 打开拨号器或电话应用。
     */
    public static void dialPhoneNumber(Context context,String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }
    }



}
