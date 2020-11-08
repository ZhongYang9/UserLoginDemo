package com.zhongyang.userlogindemo.presenter;

import com.zhongyang.userlogindemo.base.IBasePresenter;
import com.zhongyang.userlogindemo.model.domain.User;
import com.zhongyang.userlogindemo.view.IRegisterViewCallback;

/**
 * @项目名称 UserLoginDemo
 * @类名 IRegisterPresenter
 * @包名 com.zhongyang.userlogindemo.presenter
 * @创建时间 2020/11/8 16:22
 * @作者 钟阳
 * @描述
 */
public interface IRegisterPresenter extends IBasePresenter<IRegisterViewCallback> {

    /**
     * 注册用户
     */
    void registerUser(User user);

    /**
     * 校验用户是否已经注册
     *
     * @param account
     */
    void checkRegistered(String account);
}
