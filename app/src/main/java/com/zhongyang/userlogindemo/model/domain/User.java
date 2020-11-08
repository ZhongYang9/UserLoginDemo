package com.zhongyang.userlogindemo.model.domain;

/**
 * @项目名称 UserLoginDemo
 * @类名 User
 * @包名 com.zhongyang.userlogindemo.model.domain
 * @创建时间 2020/11/8 16:57
 * @作者 钟阳
 * @描述 用户实体类
 */
public class User {

    String account;
    String password;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
