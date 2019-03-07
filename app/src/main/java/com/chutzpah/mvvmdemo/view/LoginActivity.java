package com.chutzpah.mvvmdemo.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.chutzpah.mvvmdemo.R;
import com.chutzpah.mvvmdemo.databinding.ActivityLoginBinding;
import com.chutzpah.mvvmdemo.vm.LoginVM;

/**
 * @author xiaowu
 * @date 2019/03/07
 */
public class LoginActivity extends AppCompatActivity {

    private String TAG = LoginActivity.class.getName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoginBinding loginBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_login);

        //指定当前的活动生命周期的所有者。
        loginBinding.setLifecycleOwner(this);


        LoginVM loginVM = ViewModelProviders.of(this).get(LoginVM.class);

        loginBinding.setVm(loginVM);


        //视图监听数据的变化，监听用户名的变换

        loginVM.userName.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Log.d(TAG, s);
            }
        });


    }
}
