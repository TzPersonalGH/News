package com.example.administrator.newsclient;

import android.content.Intent;
import android.os.SystemClock;

public class StartActivity extends BaseActivity {

    @Override
    protected void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(1500);
                    enterGuideActivity();
            }
        }).start();

    }

    private void enterGuideActivity() {
        Intent intent = new Intent(this, GuideActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_start;
    }
}
