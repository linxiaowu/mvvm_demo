package com.chutzpah.mvvmdemo.vm;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

/**
 * @author xiaowu
 * @date 2019/03/07
 * 登录页的 viewModel
 */
public class LoginVM extends ViewModel {

    private String TAG = LoginVM.class.getName();

    public final MutableLiveData<String> userName;

    public final MutableLiveData<String> password;

    public LoginVM() {
        this.userName = new MutableLiveData<>();
        this.password = new MutableLiveData<>();
    }


    //登录
    public void onLogin() {
        Log.d(TAG, userName.getValue());
        Log.d(TAG, password.getValue());

        //进行网络请求校验

    }


    @Override
    protected void onCleared() {

        Log.d(TAG, "onCleard");

        super.onCleared();
    }

}
