package com.uw.alice.ui.modular.idiom.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.tabs.TabLayout;
import com.uw.alice.R;
import com.uw.alice.ui.modular.idiom.fragment.IdiomAnnotationFragment;
import com.uw.alice.ui.modular.idiom.fragment.IdiomKeywordFragment;
import com.uw.alice.ui.modular.idiom.adapter.IdiomSectionsPagerAdapter;

import java.util.ArrayList;

public class CorpusOfIdiomsActivity extends AppCompatActivity {

    private Context mContext;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ArrayList<Fragment> fragment_list = new ArrayList<>();//存放ViewPager下的Fragment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corpus_of_idioms);
        mContext = CorpusOfIdiomsActivity.this;
        tabLayout = findViewById(R.id.tabs);
        tabLayout.setElevation(0);
        viewPager = findViewById(R.id.view_pager);

        final LinearLayout ll_back = findViewById(R.id.ll_back);
        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //初始化Tablayout+ViewPage+Fragment
        initTab();

    }


    private void initTab() {
        tabLayout.addTab(tabLayout.newTab().setText(R.string.navigation_d_idiom_tab_title_1));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.navigation_d_idiom_tab_title_2));
        fragment_list.add(IdiomAnnotationFragment.newInstance());
        fragment_list.add(IdiomKeywordFragment.newInstance());
        IdiomSectionsPagerAdapter adapter = new IdiomSectionsPagerAdapter(getSupportFragmentManager(),mContext,fragment_list);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }



}
