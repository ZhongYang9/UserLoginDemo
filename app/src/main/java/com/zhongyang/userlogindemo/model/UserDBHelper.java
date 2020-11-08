package com.zhongyang.userlogindemo.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.zhongyang.userlogindemo.utils.Constants;

/**
 * @项目名称 UserLoginDemo
 * @类名 UserDBHelper
 * @包名 com.zhongyang.userlogindemo.model
 * @创建时间 2020/11/8 16:47
 * @作者 钟阳
 * @描述 用户数据库的帮助类
 */
public class UserDBHelper extends SQLiteOpenHelper {

    /**
     * 重写构造方法
     *
     * @param context
     */
    public UserDBHelper(Context context) {
        super(context, Constants.USER_DB_NAME, null, Constants.VERSION_CODE);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*建表*/
        String userTb = "create table " + Constants.USER_TB_NAME + "(_id integer primary key autoincrement,account varchar,password varchar)";
        db.execSQL(userTb);
        /*添加几条数据*/
        for (int i = 0; i < 20; i++) {
            String insertTest = "insert into " + Constants.USER_TB_NAME + " (account,password) values ('28257" + i + "','111111')";
            db.execSQL(insertTest);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
