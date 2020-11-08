package com.zhongyang.userlogindemo.base;

import android.app.Application;
import android.content.Context;

/**
 * @项目名称 UserLoginDemo
 * @类名 BaseApplication
 * @包名 com.zhongyang.userlogindemo.base
 * @创建时间 2020/11/8 17:13
 * @作者 钟阳
 * @描述 用于全局获取上下文
 */
public class BaseApplication extends Application {

    private static Context appContext;


    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getBaseContext();
    }


    public static Context getAppContext() {
        return appContext;
    }
}
