package com.uw.alice.ui.navigationD.test.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.uw.alice.R;


public class TestFragment1 extends Fragment {


    public TestFragment1() {

    }


    public static TestFragment1 newInstance() {
        return new TestFragment1();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_test_1, container, false);


        LinearLayout llBack = root.findViewById(R.id.ll_back);
        llBack.setOnClickListener(v -> requireActivity().finish());


        return root;
    }
}
