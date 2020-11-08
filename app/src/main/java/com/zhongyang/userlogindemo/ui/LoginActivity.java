package com.zhongyang.userlogindemo.ui;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zhongyang.userlogindemo.R;
import com.zhongyang.userlogindemo.base.BaseActivity;
import com.zhongyang.userlogindemo.presenter.impl.LoginPresenterImpl;
import com.zhongyang.userlogindemo.view.ILoginViewCallback;

import butterknife.BindView;

public class LoginActivity extends BaseActivity implements ILoginViewCallback {

    private static final String TAG = "LoginActivity";
    @BindView(R.id.et_loginAccount)
    EditText et_loginAccount;
    @BindView(R.id.et_loginPwd)
    EditText et_loginPwd;
    @BindView(R.id.btn_login)
    Button btn_login;
    @BindView(R.id.tv_loginToRegister)
    TextView tv_loginToRegister;
    private LoginPresenterImpl mLoginPresenter;
    private String mAccount;
    private String mPassword;

    //------------------------实现父类的方法----------------------
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initPresenterEvent() {
        //获取逻辑层操作对象
        mLoginPresenter = LoginPresenterImpl.getLoginPresenter();
        //注册接口
        mLoginPresenter.registerViewCallback(this);
    }

    @Override
    protected void initActivityEvent() {
        //注册文字点击事件
        tv_loginToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到登录界面
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
        //登录按钮点击事件
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //登录事件
                loginEvent();
            }
        });
    }

    @Override
    protected int getStatusBarColor() {
        return R.color.colorTransparent;
    }

    @Override
    protected void release() {
        //释放资源
        if (mLoginPresenter != null) {
            mLoginPresenter.unRegisterViewCallback(this);
        }
    }

    //------------------------实现父类的方法 end----------------------

    /**
     * 登录事件方法
     */
    private void loginEvent() {
        /*获取输入框内容*/
        mAccount = et_loginAccount.getText().toString();
        mPassword = et_loginPwd.getText().toString();
        /*校验输入框内容*/
        if (mAccount.isEmpty() || mPassword.isEmpty()) {
            Toast.makeText(this, "相关数据不能为空", Toast.LENGTH_SHORT).show();
        } else {
            //校验用户是否存在
            if (mLoginPresenter != null) {
                mLoginPresenter.checkUser(mAccount);
            }
        }
    }

    //---------------------------------------注册逻辑层实现的方法---------------
    @Override
    public void onCheckPwdResult(boolean isCorrect) {
        //Log.d(TAG, "密码是否正确 ==> " + isCorrect);
        //判断密码正确
        if (isCorrect) {
            //跳转到主界面
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
            //清空相关数据
            et_loginAccount.setText("");
            et_loginPwd.setText("");
            //禁止按钮点击
            btn_login.setEnabled(false);
        } else {
            Toast.makeText(this, "密码错误", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCheckUserResult(boolean isExistence) {
        //Log.d(TAG, "校验用户是否存在的结果 ==> " + isExistence);
        //判断
        if (isExistence) {
            //继续校验密码
            if (mLoginPresenter != null) {
                mLoginPresenter.checkPwd(mAccount, mPassword);
            }
        } else {
            Toast.makeText(this, "账号未注册", Toast.LENGTH_SHORT).show();
        }
    }
    //---------------------------------------注册逻辑层实现的方法 end---------------
}