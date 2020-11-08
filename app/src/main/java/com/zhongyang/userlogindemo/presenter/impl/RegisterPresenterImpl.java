package com.zhongyang.userlogindemo.presenter.impl;

import android.util.Log;

import com.zhongyang.userlogindemo.model.IRegisterDaoCallback;
import com.zhongyang.userlogindemo.model.RegisterDao;
import com.zhongyang.userlogindemo.model.domain.User;
import com.zhongyang.userlogindemo.presenter.IRegisterPresenter;
import com.zhongyang.userlogindemo.view.IRegisterViewCallback;

/**
 * @项目名称 UserLoginDemo
 * @类名 RegisterPresenterImpl
 * @包名 com.zhongyang.userlogindemo.presenter.impl
 * @创建时间 2020/11/8 16:36
 * @作者 钟阳
 * @描述 注册接口实现类
 */
public class RegisterPresenterImpl implements IRegisterPresenter, IRegisterDaoCallback {

    private static final String TAG = "RegisterPresenterImpl";
    private IRegisterViewCallback mRegisterViewCallback = null;
    private final RegisterDao mRegisterUserDao;

    //单例
    private RegisterPresenterImpl() {
        //持有逻辑层对象
        mRegisterUserDao = RegisterDao.getRegisterUserDao();
        //注册接口
        mRegisterUserDao.setCallback(this);
    }

    private static RegisterPresenterImpl sRegisterPresenter = null;

    public static RegisterPresenterImpl getRegisterPresenter() {
        if (sRegisterPresenter == null) {
            synchronized (RegisterPresenterImpl.class) {
                if (sRegisterPresenter == null) {
                    sRegisterPresenter = new RegisterPresenterImpl();
                }
            }
        }
        return sRegisterPresenter;
    }


    //-------------------------------注册接口后实现的方法----------------------
    @Override
    public void registerUser(User user) {
        if (mRegisterUserDao != null) {
            mRegisterUserDao.addUser(user);
        }
    }

    @Override
    public void checkRegistered(String account) {
        if (mRegisterUserDao != null) {
            mRegisterUserDao.checkUser(account);
        }
    }

    @Override
    public void registerViewCallback(IRegisterViewCallback callback) {
        this.mRegisterViewCallback = callback;
    }

    @Override
    public void unRegisterViewCallback(IRegisterViewCallback callback) {
        this.mRegisterViewCallback = null;
    }
    //-------------------------------注册接口后实现的方法 end----------------------

    //-------------------------------注册M层接口后实现的方法----------------------
    @Override
    public void onAddUserLoaded(boolean isSuccess) {
        Log.d(TAG, "添加用户的结果 ==> " + isSuccess);
        //通知UI
        if (mRegisterViewCallback != null) {
            mRegisterViewCallback.onAddUserResult(isSuccess);
        }
    }

    @Override
    public void onCheckUserLoaded(boolean isRegistered) {
        //Log.d(TAG, "校验用户是否已经注册 ==> " + isRegistered);
        if (mRegisterViewCallback != null) {
            mRegisterViewCallback.onCheckUserLoaded(isRegistered);
        }
    }
    //-------------------------------注册M层接口后实现的方法 end----------------------
}
