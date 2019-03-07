package com.chutzpah.mvvmdemo.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.chutzpah.mvvmdemo.R;
import com.chutzpah.mvvmdemo.base.LoadingDialog;
import com.chutzpah.mvvmdemo.base.State;
import com.chutzpah.mvvmdemo.databinding.ActivityLoginBinding;
import com.chutzpah.mvvmdemo.model.LoginBean;
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


        final LoadingDialog loadingDialog = new LoadingDialog(this);

        //视图监听数据的变化，监听登录状态的变换
        loginVM.loginLiveData.observe(this, new Observer<State<LoginBean>>() {
            @Override
            public void onChanged(@Nullable State<LoginBean> loginBeanState) {
                if (loginBeanState == null) return;

                switch (loginBeanState.getStatus()) {
                    case State.LOADING:
                        loadingDialog.show();
                        break;
                    case State.SUCCESS:

                        loadingDialog.dismiss();

                        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_LONG).show();

                        //TODO 携带数据跳转到列表页

                        break;
                    case State.ERROR:
                        loadingDialog.dismiss();
                        Toast.makeText(LoginActivity.this, loginBeanState.getMessage(), Toast.LENGTH_LONG).show();
                        break;

                }

            }
        });


    }
}
