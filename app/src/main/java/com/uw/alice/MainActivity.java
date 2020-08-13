package com.uw.alice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;


import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";
    private NavController navController;
    private NavController.OnDestinationChangedListener onDestinationChangedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //navView.setElevation(10);
        //将菜单项的背景设置为给定资源。这将删除由创建的任何波纹背景ERROR(/setItemRippleColor())。相关的XML属性：BottomNavigationView_item背景
        // navView.setItemBackgroundResource(R.color.white);
        // /将每个菜单ID作为一组ID传递，因为菜单应被视为顶级目的地
        //AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.navigation_home,R.id.navigation_dashboard,R.id.navigation_notifications,R.id.navigation_4).build();
        //NavigationUI.setupActionBarWithNavController(this,navController,appBarConfiguration);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navController = Navigation.findNavController(this,R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView,navController);

        initOnListener();
        initCallback();

    }



    /**
     * 初始化监听器
     */
    private void initOnListener() {
        onDestinationChangedListener = (controller, destination, arguments) -> {
            switch (destination.getId()) {
                case R.id.navigation_1:
                    //Toast.makeText(MainActivity.this, "新闻版块", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.navigation_2:
                    //Toast.makeText(MainActivity.this, "电影版块", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.navigation_3:
                    //Toast.makeText(MainActivity.this, "待定版块", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.navigation_4:
                    //Toast.makeText(MainActivity.this, "功能版块", Toast.LENGTH_SHORT).show();
                    break;
            }
        };
        /**
         * 将{@link OnDestinationChangedListener}添加到此控制器以接收回调
         * 每当{@link #getCurrentDestination（）}或其参数更改时。
         */
        navController.addOnDestinationChangedListener(onDestinationChangedListener);
    }


    /**
     * 初始化回调
     */
    private void initCallback() {
        /*OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                finish();
            }
        };
        getOnBackPressedDispatcher().addCallback(this,callback);*/
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        /*if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0){
            Toast.makeText(MainActivity.this, "你点击物理返回按钮" +  navController.popBackStack(), Toast.LENGTH_SHORT).show();
        }*/
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (navController != null){
            if (onDestinationChangedListener != null){
                navController.removeOnDestinationChangedListener(onDestinationChangedListener);
                Log.i(TAG, "监听点位: 已移除onDestinationChangedListener");
            }
        }
    }



}
