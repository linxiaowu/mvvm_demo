package com.chutzpah.mvvmdemo.login.vm;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.Handler;
import android.util.Log;

import com.chutzpah.mvvmdemo.base.State;
import com.chutzpah.mvvmdemo.login.model.LoginBean;


/**
 * @author xiaowu
 * @date 2019/03/07
 * 登录页的 viewModel
 */
public class LoginVM extends ViewModel {

    private String TAG = LoginVM.class.getName();

    public final MutableLiveData<String> userName;

    public final MutableLiveData<String> password;

    public final MutableLiveData<State<LoginBean>> loginLiveData;

    public LoginVM() {
        this.userName = new MutableLiveData<>();
        this.password = new MutableLiveData<>();

        this.loginLiveData = new MutableLiveData<>();

    }


    //登录
    public void onLogin() {

        if (userName.getValue() == null || userName.getValue().length() == 0) {
            loginLiveData.setValue(State.<LoginBean>error("请输入用户名"));
            return;
        }

        if (password.getValue() == null || password.getValue().length() == 0) {
            loginLiveData.setValue(State.<LoginBean>error("请输入密码"));
            return;
        }

        //先 loading
        loginLiveData.setValue(State.<LoginBean>loading());

        //模拟耗时网络请求,这部分可以放在 model 的 biz
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if ("123456".equals(password.getValue())) {
                    LoginBean loginBean = new LoginBean();
                    loginBean.setUserId(1);
                    loginBean.setUserName("xiaowu");

                    loginLiveData.setValue(State.success(loginBean));

                } else {
                    loginLiveData.setValue(State.<LoginBean>error("密码错误"));
                }

            }
        }, 2000);


    }


    @Override
    protected void onCleared() {

        Log.d(TAG, "onCleared");

        super.onCleared();
    }

}
