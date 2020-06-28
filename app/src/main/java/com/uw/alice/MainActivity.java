package com.uw.alice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.uw.alice.data.util.Util;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //以下代码Navigation进行配置关联
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setElevation(10);
        //将菜单项的背景设置为给定资源。这将删除由创建的任何波纹背景ERROR(/setItemRippleColor())。相关的XML属性：BottomNavigationView_item背景
       // navView.setItemBackgroundResource(R.color.white);

        // /将每个菜单ID作为一组ID传递，因为菜单应被视为顶级目的地
        //AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.navigation_home,R.id.navigation_dashboard,R.id.navigation_notifications,R.id.navigation_4).build();
        NavController navController = Navigation.findNavController(this,R.id.nav_host_fragment);
        //NavigationUI.setupActionBarWithNavController(this,navController,appBarConfiguration);
        NavigationUI.setupWithNavController(navView,navController);


    }












    }
