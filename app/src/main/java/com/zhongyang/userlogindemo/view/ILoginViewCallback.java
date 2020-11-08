package com.zhongyang.userlogindemo.view;

/**
 * @项目名称 UserLoginDemo
 * @类名 ILoginViewCallback
 * @包名 com.zhongyang.userlogindemo.view
 * @创建时间 2020/11/8 19:54
 * @作者 钟阳
 * @描述
 */
public interface ILoginViewCallback {

    /**
     * 校验密码的结果
     *
     * @param isCorrect 是否正确
     */
    void onCheckPwdResult(boolean isCorrect);

    /**
     * 校验用户是否存在
     *
     * @param isExistence 是否存在
     */
    void onCheckUserResult(boolean isExistence);
}
