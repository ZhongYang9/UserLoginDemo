package com.zhongyang.userlogindemo.base;

import com.zhongyang.userlogindemo.view.IRegisterViewCallback;

/**
 * @项目名称 UserLoginDemo
 * @类名 IBasePresenter
 * @包名 com.zhongyang.userlogindemo.base
 * @创建时间 2020/11/8 16:26
 * @作者 钟阳
 * @描述 实现接口的公接口
 */
public interface IBasePresenter<T> {

    /**
     * 注册方法
     *
     * @param t 回调接口对象
     */
    void registerViewCallback(T t);

    /**
     * @param t 回调接口对象
     */
    void unRegisterViewCallback(T t);


}
