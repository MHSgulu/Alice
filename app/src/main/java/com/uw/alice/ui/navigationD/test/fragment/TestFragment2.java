package com.uw.alice.ui.navigationD.test.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.uw.alice.R;

import custom.MyTabLayout;


public class TestFragment2 extends Fragment {


    public TestFragment2() {

    }


    public static TestFragment2 newInstance() {
        return new TestFragment2();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_test_2, container, false);

        LinearLayout llBack = root.findViewById(R.id.ll_back);
        llBack.setOnClickListener(v -> requireActivity().finish());

        MyTabLayout myTab = root.findViewById(R.id.myTabLayout);
        myTab.addTab(myTab.newTab().setText("哈哈"));
        myTab.addTab(myTab.newTab().setText("啦啦"));


        return root;
    }
}
