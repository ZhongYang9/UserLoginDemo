package com.zhongyang.userlogindemo.model;

import com.zhongyang.userlogindemo.model.domain.User;

import java.util.List;

/**
 * @项目名称 UserLoginDemo
 * @类名 IUserDaoCallback
 * @包名 com.zhongyang.userlogindemo.model
 * @创建时间 2020/11/8 16:46
 * @作者 钟阳
 * @描述 用户DAO的回调接口
 */
public interface IUserDaoCallback {

    /**
     * 添加用户的结果
     *
     * @param isSuccess
     */
    void onAddUserLoaded(boolean isSuccess);


    /**
     * 校验用户是否存在的结果
     *
     * @param isRegistered
     */
    void onCheckUserLoaded(boolean isRegistered);
}
