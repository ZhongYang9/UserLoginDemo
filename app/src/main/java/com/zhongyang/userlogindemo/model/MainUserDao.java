package com.zhongyang.userlogindemo.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zhongyang.userlogindemo.base.BaseApplication;
import com.zhongyang.userlogindemo.model.domain.User;
import com.zhongyang.userlogindemo.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * @项目名称 UserLoginDemo
 * @类名 MainDao
 * @包名 com.zhongyang.userlogindemo.model
 * @创建时间 2020/11/8 21:55
 * @作者 钟阳
 * @描述
 */
public class MainUserDao implements IMainDao {

    private final UserDBHelper mHelper;
    private IMainDaoCallback mViewCallback = null;

    //单例
    private MainUserDao() {
        //获取数据库操作对象
        mHelper = new UserDBHelper(BaseApplication.getAppContext());
    }

    private static MainUserDao sMainDao = null;

    public static MainUserDao getMainDao() {
        if (sMainDao == null) {
            sMainDao = new MainUserDao();
        }
        return sMainDao;
    }

    @Override
    public void setViewCallback(IMainDaoCallback callback) {
        this.mViewCallback = callback;
    }

    @Override
    public void getUserList() {
        //定义一个集合用于保存取出的数据
        List<User> userData = new ArrayList<>();
        SQLiteDatabase db = null;
        try {
            /*打开数据库*/
            db = mHelper.getReadableDatabase();
            /*开启事务*/
            db.beginTransaction();
            /*查询数据库*/
            Cursor cursor = db.query(Constants.USER_TB_NAME, null, null, null, null, null, "_id desc");
            while (cursor.moveToNext()) {
                /*实例化数据实体类*/
                User user = new User();
                /*封装数据*/
                //账号
                String account = cursor.getString(cursor.getColumnIndex("account"));
                user.setAccount(account);
                //密码
                String password = cursor.getString(cursor.getColumnIndex("password"));
                user.setPassword(password);
                /*添加到集合*/
                userData.add(user);
            }
            /*关闭游标*/
            cursor.close();

            /*事务成功*/
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            /*关闭数据库*/
            if (db != null) {
                /*结束事务*/
                db.endTransaction();
                db.close();
            }
            /*通知结果*/
            if (mViewCallback != null) {
                mViewCallback.getUserListLoaded(userData);
            }
        }
    }
}
