package com.zhongyang.userlogindemo.model;

import com.zhongyang.userlogindemo.model.domain.User;

/**
 * @项目名称 UserLoginDemo
 * @类名 IRegisterDao
 * @包名 com.zhongyang.userlogindemo.model
 * @创建时间 2020/11/8 16:47
 * @作者 钟阳
 * @描述 用户注册的DAO接口
 */
public interface IRegisterDao {

    /**
     * 设置回调接口
     *
     * @param callback
     */
    void setCallback(IRegisterDaoCallback callback);

    /**
     * 添加用户
     *
     * @param user
     */
    void addUser(User user);

    /**
     * 校验用户是否已经注册
     */
    void checkUser(String account);
}
