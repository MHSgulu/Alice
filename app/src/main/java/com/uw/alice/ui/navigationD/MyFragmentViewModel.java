package com.uw.alice.ui.navigationD;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyFragmentViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MyFragmentViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is my fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

}
