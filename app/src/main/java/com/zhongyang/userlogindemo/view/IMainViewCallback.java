package com.zhongyang.userlogindemo.view;

import com.zhongyang.userlogindemo.model.domain.User;

import java.util.List;

/**
 * @项目名称 UserLoginDemo
 * @类名 IMainViewCallback
 * @包名 com.zhongyang.userlogindemo.view
 * @创建时间 2020/11/8 21:46
 * @作者 钟阳
 * @描述
 */
public interface IMainViewCallback {

    /**
     * 获取用户列表的结果
     *
     * @param users
     */
    void onGetUserListLoaded(List<User> users);
}
