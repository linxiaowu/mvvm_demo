package com.chutzpah.mvvmdemo.view;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.chutzpah.mvvmdemo.R;
import com.chutzpah.mvvmdemo.databinding.ActivityLoginBinding;

/**
 *
 * @author xiaowu
 * @date 2019/03/07
 */
public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoginBinding  loginBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_login);



    }
}
