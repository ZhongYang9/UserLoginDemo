package com.zhongyang.userlogindemo.presenter;

import com.zhongyang.userlogindemo.base.IBasePresenter;
import com.zhongyang.userlogindemo.model.domain.User;
import com.zhongyang.userlogindemo.view.IMainViewCallback;

import java.util.List;

/**
 * @项目名称 UserLoginDemo
 * @类名 IMainPresenter
 * @包名 com.zhongyang.userlogindemo.presenter
 * @创建时间 2020/11/8 21:46
 * @作者 钟阳
 * @描述
 */
public interface IMainPresenter extends IBasePresenter<IMainViewCallback> {

    /**
     * 获取用户列表
     *
     * @param users
     */
    void getUserList();
}
