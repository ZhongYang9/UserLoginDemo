package com.zhongyang.userlogindemo.model;

import com.zhongyang.userlogindemo.model.domain.User;

import java.util.List;

/**
 * @项目名称 UserLoginDemo
 * @类名 IMainDaoCallback
 * @包名 com.zhongyang.userlogindemo.model
 * @创建时间 2020/11/8 21:52
 * @作者 钟阳
 * @描述
 */
public interface IMainDaoCallback {

    /**
     * 获取用户列表的结果
     *
     * @param users
     */
    void getUserListLoaded(List<User> users);
}
