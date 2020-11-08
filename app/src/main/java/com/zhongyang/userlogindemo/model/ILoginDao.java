package com.zhongyang.userlogindemo.model;

/**
 * @项目名称 UserLoginDemo
 * @类名 ILoginDao
 * @包名 com.zhongyang.userlogindemo.model
 * @创建时间 2020/11/8 20:08
 * @作者 钟阳
 * @描述
 */
public interface ILoginDao {

    /**
     * 设置回调接口
     *
     * @param callback
     */
    void setCallback(ILoginDaoCallback callback);

    /**
     * 校验密码是否正确
     *
     * @param account  账号
     * @param password 密码
     */
    void checkPwd(String account, String password);

    /**
     * 校验用户是否存在
     *
     * @param account 账号
     */
    void checkUser(String account);
}
