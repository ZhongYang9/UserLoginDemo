package com.zhongyang.userlogindemo.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.zhongyang.userlogindemo.R;
import com.zhongyang.userlogindemo.base.BaseActivity;
import com.zhongyang.userlogindemo.model.domain.User;
import com.zhongyang.userlogindemo.presenter.impl.RegisterPresenterImpl;
import com.zhongyang.userlogindemo.view.IRegisterViewCallback;

import butterknife.BindView;

public class RegisterActivity extends BaseActivity implements IRegisterViewCallback {

    private static final String TAG = "RegisterActivity";
    private RegisterPresenterImpl mRegisterPresenter;

    @BindView(R.id.iv_registerBack)
    ImageView iv_registerBack;

    @BindView(R.id.et_registerAccount)
    EditText et_registerAccount;

    @BindView(R.id.et_registerPwd)
    EditText et_registerPwd;

    @BindView(R.id.et_registerConfigPwd)
    EditText et_registerConfigPwd;

    @BindView(R.id.btn_register)
    Button btn_register;
    private String mAccount;
    private String mPassword;

    //------------------------实现父类的方法----------------------
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_register;
    }

    @Override
    protected int getStatusBarColor() {
        return R.color.colorTransparent;
    }

    @Override
    protected void initActivityEvent() {
        //返回图标点击事件
        iv_registerBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //注册按钮点击事件
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //注册事件
                registerEvent();
            }
        });
    }

    private void registerEvent() {
        //获取输入框内容
        mAccount = et_registerAccount.getText().toString();
        mPassword = et_registerPwd.getText().toString();
        String configPwd = et_registerConfigPwd.getText().toString();
        //校验输入框内容
        if (mAccount.isEmpty() || mPassword.isEmpty() || configPwd.isEmpty()) {
            Toast.makeText(this, "请将数据输入完整", Toast.LENGTH_SHORT).show();
        } else if (!mPassword.equals(configPwd)) {
            Toast.makeText(this, "两次密码不一致", Toast.LENGTH_SHORT).show();
        } else {
            //校验用户
            if (mRegisterPresenter != null) {
                mRegisterPresenter.checkRegistered(mAccount);
            }
        }
    }

    @Override
    protected void initPresenterEvent() {
        //获取逻辑层操作对象
        mRegisterPresenter = RegisterPresenterImpl.getRegisterPresenter();
        mRegisterPresenter.registerViewCallback(this);
    }

    @Override
    protected void release() {
        //释放资源
        if (mRegisterPresenter != null) {
            mRegisterPresenter.unRegisterViewCallback(this);
        }
    }
    //------------------------实现父类的方法 end----------------------

    //--------------------------实现接口后实现的方法------------------
    @Override
    public void onAddUserResult(boolean isSuccess) {
        //判断
        if (isSuccess) {
            Toast.makeText(this, "注册成功，请登录", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "注册失败，请稍后重试", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCheckUserLoaded(boolean isRegistered) {
        //Log.d(TAG, "注册结果 ==> " + isRegistered);
        if (isRegistered) {
            Toast.makeText(this, "用户已存在", Toast.LENGTH_SHORT).show();
        } else {
            //封装数据
            User user = new User();
            user.setAccount(mAccount);
            user.setPassword(mPassword);
            //调用注册方法
            if (mRegisterPresenter != null) {
                mRegisterPresenter.registerUser(user);
            }
        }
    }
    //--------------------------实现接口后实现的方法 end------------------
}