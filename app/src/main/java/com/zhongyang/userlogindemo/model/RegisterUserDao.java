package com.zhongyang.userlogindemo.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zhongyang.userlogindemo.base.BaseApplication;
import com.zhongyang.userlogindemo.model.domain.User;
import com.zhongyang.userlogindemo.utils.Constants;

/**
 * @项目名称 UserLoginDemo
 * @类名 RegisterUserDao
 * @包名 com.zhongyang.userlogindemo.model
 * @创建时间 2020/11/8 17:03
 * @作者 钟阳
 * @描述 注册用户的DAO实现类
 */
public class RegisterUserDao implements IUserDao {

    private IUserDaoCallback mViewCallback = null;
    private UserDBHelper mHelper;

    //单例
    private RegisterUserDao() {
        //获取数据库操作类对象
        mHelper = new UserDBHelper(BaseApplication.getAppContext());
    }

    private static RegisterUserDao sRegisterUserDao = null;

    public static RegisterUserDao getRegisterUserDao() {
        if (sRegisterUserDao == null) {
            synchronized (RegisterUserDao.class) {
                if (sRegisterUserDao == null) {
                    sRegisterUserDao = new RegisterUserDao();
                }
            }
        }
        return sRegisterUserDao;
    }

    @Override
    public void setCallback(IUserDaoCallback callback) {
        this.mViewCallback = callback;
    }

    @Override
    public void addUser(User user) {
        SQLiteDatabase db = null;
        boolean isSuccess = false;
        try {
            /*打开数据库*/
            db = mHelper.getWritableDatabase();
            /*开启事务*/
            db.beginTransaction();
            /*添加数据*/
            ContentValues values = new ContentValues();
            values.put("account", user.getAccount());
            values.put("password", user.getPassword());
            db.insert(Constants.USER_TB_NAME, null, values);

            /*事务成功*/
            db.setTransactionSuccessful();
            //设置标示量
            isSuccess = true;
        } catch (Exception e) {
            e.printStackTrace();
            //设置标示量
            isSuccess = false;
        } finally {
            /*关闭数据库*/
            if (db != null) {
                /*结束事务*/
                db.endTransaction();
                db.close();
            }
            /*通知结果*/
            if (mViewCallback != null) {
                mViewCallback.onAddUserLoaded(isSuccess);
            }
        }
    }

    @Override
    public void checkUser(String account) {
        SQLiteDatabase db = null;
        boolean isRegistered = false;
        try {
            /*打开数据库*/
            db = mHelper.getReadableDatabase();
            /*开启事务*/
            db.beginTransaction();
            /*执行查询语句*/
            Cursor cursor = db.query(Constants.USER_TB_NAME, null, "account =?", new String[]{account}, null, null, null);
            if (cursor.getCount() == 0) {
                //说明用户已存在
                isRegistered = false;
            } else {
                isRegistered = true;
            }

            /*事务成功*/
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
            //
            isRegistered = true;
        } finally {
            /*关闭数据库*/
            if (db != null) {
                /*结束事务*/
                db.endTransaction();
                db.close();
            }
            /*通知查询结果*/
            if (mViewCallback != null) {
                mViewCallback.onCheckUserLoaded(isRegistered);
            }
        }
    }
}
