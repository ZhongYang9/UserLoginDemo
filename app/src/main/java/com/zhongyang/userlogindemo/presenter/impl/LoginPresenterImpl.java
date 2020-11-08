package com.zhongyang.userlogindemo.presenter.impl;

import com.zhongyang.userlogindemo.model.ILoginDaoCallback;
import com.zhongyang.userlogindemo.model.LoginDao;
import com.zhongyang.userlogindemo.presenter.ILoginPresenter;
import com.zhongyang.userlogindemo.view.ILoginViewCallback;

/**
 * @项目名称 UserLoginDemo
 * @类名 LoginPresenterImpl
 * @包名 com.zhongyang.userlogindemo.presenter.impl
 * @创建时间 2020/11/8 20:01
 * @作者 钟阳
 * @描述
 */
public class LoginPresenterImpl implements ILoginPresenter, ILoginDaoCallback {

    private ILoginViewCallback mLoginViewCallback = null;
    private final LoginDao mLoginDao;

    private LoginPresenterImpl() {
        //获取M层操作对象
        mLoginDao = LoginDao.getLoginDao();
        //注册接口，持有应用
        mLoginDao.setCallback(this);
    }

    private static LoginPresenterImpl sLoginPresenter = null;

    public static LoginPresenterImpl getLoginPresenter() {
        if (sLoginPresenter == null) {
            sLoginPresenter = new LoginPresenterImpl();
        }
        return sLoginPresenter;
    }

    //------------------------------------注册实现接口实现的方法--------------------------
    @Override
    public void checkPwd(String account, String password) {
        if (mLoginDao != null) {
            mLoginDao.checkPwd(account, password);
        }
    }

    @Override
    public void checkUser(String account) {
        if (mLoginDao != null) {
            mLoginDao.checkUser(account);
        }
    }

    @Override
    public void registerViewCallback(ILoginViewCallback callback) {
        //保存注册接口
        this.mLoginViewCallback = callback;
    }

    @Override
    public void unRegisterViewCallback(ILoginViewCallback callback) {
        //移除接口
        this.mLoginViewCallback = null;
    }
    //------------------------------------注册实现接口实现的方法 end--------------------------

    //------------------------------------注册M层接口实现的方法--------------------------
    @Override
    public void onCheckPwdResult(boolean isCorrect) {
        //将结果通知到UI
        if (mLoginViewCallback != null) {
            mLoginViewCallback.onCheckPwdResult(isCorrect);
        }
    }

    @Override
    public void onCheckUserResult(boolean isExistence) {
        //将结果通知到UI
        if (mLoginViewCallback != null) {
            mLoginViewCallback.onCheckUserResult(isExistence);
        }
    }
    //------------------------------------注册M层接口实现的方法 end--------------------------
}
