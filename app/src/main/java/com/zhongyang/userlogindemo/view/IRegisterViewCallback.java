package com.zhongyang.userlogindemo.view;

/**
 * @项目名称 UserLoginDemo
 * @类名 IRegisterViewCallback
 * @包名 com.zhongyang.userlogindemo.view
 * @创建时间 2020/11/8 16:23
 * @作者 钟阳
 * @描述
 */
public interface IRegisterViewCallback {

    /**
     * 添加用户的结果
     *
     * @param isSuccess
     */
    void onAddUserResult(boolean isSuccess);

    /**
     * 校验用户是否存在的结果
     *
     * @param isRegistered
     */
    void onCheckUserLoaded(boolean isRegistered);
}
