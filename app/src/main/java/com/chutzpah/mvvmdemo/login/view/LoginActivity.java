package com.chutzpah.mvvmdemo.login.view;

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
import com.chutzpah.mvvmdemo.book.view.BookListActivity;
import com.chutzpah.mvvmdemo.databinding.ActivityLoginBinding;
import com.chutzpah.mvvmdemo.login.model.LoginBean;
import com.chutzpah.mvvmdemo.login.vm.LoginVM;

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

                        //可以携带携带数据跳转到列表页，可以调用 startActivityForResult 等
                        //若是放在 vm 不合适，
                        //1. context 无法使用 startActivityForResult
                        //2. vm 持有 context 可能会导致内存泄露
                        //3. vm 越少知道 android 包下的东西越有利于跑单元测试

                        startActivity(BookListActivity.prepareIntent(loginBeanState.getData().getUserId(),
                                LoginActivity.this));

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
