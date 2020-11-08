package com.zhongyang.userlogindemo.ui;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zhongyang.userlogindemo.R;
import com.zhongyang.userlogindemo.base.BaseActivity;

import butterknife.BindView;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.et_loginAccount)
    EditText et_loginAccount;
    @BindView(R.id.et_loginPwd)
    EditText et_loginPwd;
    @BindView(R.id.btn_login)
    Button btn_login;
    @BindView(R.id.tv_loginToRegister)
    TextView tv_loginToRegister;

    //------------------------实现父类的方法----------------------
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_login;
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

    //------------------------实现父类的方法 end----------------------

    /**
     * 登录事件方法
     */
    private void loginEvent() {
        /*获取输入框内容*/
        String account = et_loginAccount.getText().toString();
        String password = et_loginPwd.getText().toString();
        /*校验输入框内容*/
        if (account.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "相关数据不能为空", Toast.LENGTH_SHORT).show();
        } else {
            //TODO 操作数据库进行登录
        }
    }
}