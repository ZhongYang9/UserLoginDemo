package com.zhongyang.userlogindemo.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.zhongyang.userlogindemo.R;
import com.zhongyang.userlogindemo.base.BaseActivity;

public class RegisterActivity extends BaseActivity {

    //------------------------实现父类的方法----------------------
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_register;
    }

    @Override
    protected int getStatusBarColor() {
        return R.color.colorTransparent;
    }
    //------------------------实现父类的方法 end----------------------
}