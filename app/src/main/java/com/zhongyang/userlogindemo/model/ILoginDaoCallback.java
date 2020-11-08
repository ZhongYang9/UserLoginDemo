package com.zhongyang.userlogindemo.model;

/**
 * @项目名称 UserLoginDemo
 * @类名 ILoginDaoCallback
 * @包名 com.zhongyang.userlogindemo.model
 * @创建时间 2020/11/8 20:07
 * @作者 钟阳
 * @描述
 */
public interface ILoginDaoCallback {

    /**
     * 校验密码的结果
     *
     * @param isCorrect 是否正确
     */
    void onCheckPwdResult(boolean isCorrect);

    /**
     * 校验用户的结果
     *
     * @param isExistence 是否存在
     */
    void onCheckUserResult(boolean isExistence);
}
