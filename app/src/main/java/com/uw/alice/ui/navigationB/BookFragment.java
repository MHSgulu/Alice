package com.uw.alice.ui.navigationB;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uw.alice.R;
import com.uw.alice.data.model.Movie;
import com.uw.alice.data.model.MovieRankingList;
import com.uw.alice.data.util.Util;
import com.uw.alice.network.retrofit.SingletonRetrofit;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

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
