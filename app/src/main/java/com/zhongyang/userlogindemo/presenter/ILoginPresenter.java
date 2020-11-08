package com.zhongyang.userlogindemo.presenter;

import com.zhongyang.userlogindemo.base.IBasePresenter;
import com.zhongyang.userlogindemo.view.ILoginViewCallback;

/**
 * @项目名称 UserLoginDemo
 * @类名 ILoginPresenter
 * @包名 com.zhongyang.userlogindemo.presenter
 * @创建时间 2020/11/8 19:54
 * @作者 钟阳
 * @描述
 */
public interface ILoginPresenter extends IBasePresenter<ILoginViewCallback> {

    /**
     * 校验密码是否正确
     *
     * @param password
     */
    void checkPwd(String account, String password);

    /**
     * 校验用户是否在存在
     *
     * @param account
     */
    void checkUser(String account);
}
