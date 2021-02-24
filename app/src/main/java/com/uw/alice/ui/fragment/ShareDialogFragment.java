package com.uw.alice.ui.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uw.alice.R;

/**
 *    ItemListDialogFragment.newInstance(30).show(getSupportFragmentManager(), "dialog");
 */
public class ShareDialogFragment extends BottomSheetDialogFragment {

    private static final String TAG = "ShareDialogFragment";
    private static final String ARG_ITEM_COUNT = "item_count";

    public static ShareDialogFragment newInstance(/*int itemCount*/) {
        final ShareDialogFragment fragment = new ShareDialogFragment();
        final Bundle args = new Bundle();
        //args.putInt(ARG_ITEM_COUNT, itemCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_share_bottom_sheet_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    }



}
