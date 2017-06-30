package com.example.administrator.newsclient.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/6/27.
 */

public abstract class BaseFragment extends Fragment {
    protected View mRootView;
    protected Activity mActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = LayoutInflater.from(mActivity).inflate(getLayoutRes(), container, false);

            initView();
            initListener();
            initData();
        }
        return mRootView;
    }

    public abstract void initData();

    public abstract void initListener();

    public abstract void initView();

    public abstract int getLayoutRes();

    private Toast mToast;
    public void showToast(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(mActivity, "", Toast.LENGTH_SHORT);
        }
        mToast.setText(msg);
        mToast.show();
    }


}
