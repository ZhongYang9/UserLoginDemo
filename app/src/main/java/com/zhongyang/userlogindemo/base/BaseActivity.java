package com.zhongyang.userlogindemo.base;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.zhongyang.userlogindemo.R;
import com.zhongyang.userlogindemo.utils.StatusBarUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @项目名称 UserLoginDemo
 * @类名 BaseActivity
 * @包名 com.zhongyang.userlogindemo
 * @创建时间 2020/11/8 14:49
 * @作者 钟阳
 * @描述 Activity的公共类
 */
public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder mBind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        //绑定黄油刀
        mBind = ButterKnife.bind(this);
        //初始化控件方法
        initActivityView();
        //初始化事件方法
        initActivityEvent();
        //设置状态字体颜色
        setStatusBar();
    }

    /**
     * 初始化活动事件的方法，由子类根据情况自行实现
     */
    protected void initActivityEvent() {

    }

    /**
     * 初始化获取组件的方法，由子类根据情况自行实现
     */
    protected void initActivityView() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //释放资源
        if (mBind != null) {
            mBind.unbind();
        }
        //调用子类释放资源方法
        this.release();
    }

    /**
     * 释放资源方法，由子类根据情况自行实现
     */
    protected void release() {

    }

    /**
     * 获取子类视图的方法
     *
     * @return
     */
    protected abstract int getLayoutResId();

    /**
     * 设置状态栏颜色方法，由子类根据情况实现
     */
    private void setStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (isUseFullScreenMode()) {
                StatusBarUtil.transparencyBar(this);
            } else {
                StatusBarUtil.setStatusBarColor(this, getStatusBarColor());
            }

            if (isUseBlackFontWithStatusBar()) {
                StatusBarUtil.setLightStatusBar(this, true, isUseFullScreenMode());
            }
        }
    }

    /**
     * 是否设置成透明状态栏，即就是全屏模式
     */
    protected boolean isUseFullScreenMode() {
        return true;
    }

    /**
     * 更改状态栏颜色，只有非全屏模式下有效
     */
    protected int getStatusBarColor() {
        return R.color.colorTransparent;
    }

    /**
     * 是否改变状态栏文字颜色为黑色，默认为黑色
     */
    protected boolean isUseBlackFontWithStatusBar() {
        return true;
    }

}
