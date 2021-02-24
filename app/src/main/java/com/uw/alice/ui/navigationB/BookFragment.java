package com.uw.alice.ui.navigationB;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.uw.alice.R;

public class BookFragment extends Fragment {

    private static final String TAG = "BookFragment";
    private Context mContext;

    public static BookFragment newInstance() {
        //Bundle args = new Bundle();
        //fragment.setArguments(args);
        return new BookFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_book, container, false);
        //mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_dynamic_gif, container, false);
        mContext = getContext();



        return root;
    }






}
