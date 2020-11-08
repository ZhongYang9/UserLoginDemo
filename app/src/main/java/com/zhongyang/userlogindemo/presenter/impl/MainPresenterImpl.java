package com.zhongyang.userlogindemo.presenter.impl;

import com.zhongyang.userlogindemo.model.IMainDaoCallback;
import com.zhongyang.userlogindemo.model.MainUserDao;
import com.zhongyang.userlogindemo.model.domain.User;
import com.zhongyang.userlogindemo.presenter.IMainPresenter;
import com.zhongyang.userlogindemo.view.IMainViewCallback;

import java.util.List;

/**
 * @项目名称 UserLoginDemo
 * @类名 MainPresenterImpl
 * @包名 com.zhongyang.userlogindemo.presenter.impl
 * @创建时间 2020/11/8 21:49
 * @作者 钟阳
 * @描述 主界面接口实现类
 */
public class MainPresenterImpl implements IMainPresenter, IMainDaoCallback {

    private IMainViewCallback mMainViewCallback = null;
    private final MainUserDao mMainDao;

    //单例
    private MainPresenterImpl() {
        //获取M层操作对象
        mMainDao = MainUserDao.getMainDao();
        //注册回调
        mMainDao.setViewCallback(this);
    }

    private static MainPresenterImpl sMainPresenter = null;

    public static MainPresenterImpl getMainPresenter() {
        if (sMainPresenter == null) {
            sMainPresenter = new MainPresenterImpl();
        }
        return sMainPresenter;
    }

    //--------------------------------实现接口后的方法----------------------
    @Override
    public void getUserList() {
        if (mMainDao != null) {
            mMainDao.getUserList();
        }
    }

    @Override
    public void registerViewCallback(IMainViewCallback callback) {
        this.mMainViewCallback = callback;
    }

    @Override
    public void unRegisterViewCallback(IMainViewCallback callback) {
        this.mMainViewCallback = null;
    }
    //--------------------------------实现接口后的方法 end----------------------

    @Override
    public void getUserListLoaded(List<User> users) {
        if (mMainViewCallback != null) {
            mMainViewCallback.onGetUserListLoaded(users);
        }
    }
}
