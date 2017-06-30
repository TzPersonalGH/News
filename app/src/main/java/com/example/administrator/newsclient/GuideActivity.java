package com.example.administrator.newsclient;

import android.animation.Animator;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;

public class GuideActivity extends BaseActivity {
    private ImageView iv01;
    private Button btnGo;
    private MediaPlayer mMediaPlayer;

    private int index = 0;

    private int[] imagesArray = new int[]{
            R.drawable.ad_new_version1_img1,
            R.drawable.ad_new_version1_img2,
            R.drawable.ad_new_version1_img3,
            R.drawable.ad_new_version1_img4,
            R.drawable.ad_new_version1_img5,
            R.drawable.ad_new_version1_img6,
            R.drawable.ad_new_version1_img7,
    };

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    startAnimation();
                    break;
            }
        }
    };

    @Override
    protected void initData() {
        startAnimation();
    }

    private void startAnimation() {
        index++;
        index = index % imagesArray.length;
        iv01.setBackgroundResource(imagesArray[index]);

        iv01.setScaleX(1.0f);
        iv01.setScaleY(1.0f);

        iv01.animate()
                .scaleX(1.2f)
                .scaleY(1.2f)
                .setDuration(3500)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mHandler.sendEmptyMessageDelayed(0, 1000);

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                }).start();
    }

    @Override
    protected void initListener() {
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterMainActivity();
            }
        });
    }

    private void enterMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void initView() {
        iv01 = (ImageView) findViewById(R.id.iv_01);
        btnGo = (Button) findViewById(R.id.btn_go);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_guide;
    }

}
