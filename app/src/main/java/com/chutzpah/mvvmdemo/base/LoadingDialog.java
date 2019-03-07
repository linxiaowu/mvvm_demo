package com.chutzpah.mvvmdemo.base;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatDialog;
import android.view.KeyEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.chutzpah.mvvmdemo.R;

/**
 * Created by xiaowu on 2019/03/07.
 */
public class LoadingDialog extends AppCompatDialog {

    public LoadingDialog(Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context) {
        setContentView(R.layout.loading_dialog);

        ImageView loadingImageView = findViewById(R.id.loading_image_view);

        if (loadingImageView != null) {

            Animation operatingAnim = AnimationUtils.loadAnimation(context,
                    R.anim.dialog_loading_animation);
            LinearInterpolator lin = new LinearInterpolator();
            operatingAnim.setInterpolator(lin);
            loadingImageView.startAnimation(operatingAnim);
        }

        setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialogInterface, int keyCode, KeyEvent keyEvent) {
                return keyCode == KeyEvent.KEYCODE_BACK && keyEvent.getRepeatCount() == 0;
            }
        });
        setCanceledOnTouchOutside(false);
        setCancelable(false);
    }
}
