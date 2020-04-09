package com.uw.alice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

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

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        //AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.navigation_home,R.id.navigation_dashboard,R.id.navigation_notifications,R.id.navigation_4).build();
        NavController navController = Navigation.findNavController(this,R.id.nav_host_fragment);
        //NavigationUI.setupActionBarWithNavController(this,navController,appBarConfiguration);
        NavigationUI.setupWithNavController(navView,navController);


    }
}
