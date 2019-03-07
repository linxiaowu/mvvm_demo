package com.chutzpah.mvvmdemo.vm;


import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

/**
 * Created by xiaowu on 2019/02/14.
 */
public class MainVM extends ViewModel {

    private MutableLiveData<String> currentName;

    public MutableLiveData<String> getCurrentName() {
        if (currentName == null) {
            currentName = new MutableLiveData<>();
        }
        return currentName;
    }




}
