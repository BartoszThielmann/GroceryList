package com.bthielmann.grocerylist.ui.lists;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ListsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ListsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is lists fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}