package com.zhongyang.userlogindemo.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.zhongyang.userlogindemo.base.BaseApplication;
import com.zhongyang.userlogindemo.utils.Constants;

/**
 * @项目名称 UserLoginDemo
 * @类名 LoginDao
 * @包名 com.zhongyang.userlogindemo.model
 * @创建时间 2020/11/8 20:13
 * @作者 钟阳
 * @描述
 */
public class LoginDao implements ILoginDao {
    private ILoginDaoCallback mViewCallback = null;
    private final UserDBHelper mHelper;

    //单例
    private LoginDao() {
        //获取到数据库操作对象
        mHelper = new UserDBHelper(BaseApplication.getAppContext());
    }

    private static LoginDao sLoginDao = null;

    public static LoginDao getLoginDao() {
        if (sLoginDao == null) {
            sLoginDao = new LoginDao();
        }
        return sLoginDao;
    }

    @Override
    public void setCallback(ILoginDaoCallback callback) {
        this.mViewCallback = callback;
    }

    @Override
    public void checkPwd(String account, String password) {
        SQLiteDatabase db = null;
        boolean isCorrect = false;//定义是否正确标示量
        try {
            /*打开数据库*/
            db = mHelper.getReadableDatabase();
            /*开启事务*/
            db.beginTransaction();
            /*查询数据库*/
            Cursor cursor = db.query(Constants.USER_TB_NAME, null, "account =? and password =?", new String[]{account, password}, null, null, null);
            if (cursor.moveToNext()) {
                //密码正确
                isCorrect = true;
            } else {
                isCorrect = false;
            }

            /*事务成功*/
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
            /*修改标示量*/
            isCorrect = false;
        } finally {
            /*关闭数据库*/
            if (db != null) {
                /*结束事务*/
                db.endTransaction();
                db.close();
            }
            /*通知结果*/
            if (mViewCallback != null) {
                mViewCallback.onCheckPwdResult(isCorrect);
            }
        }
    }

    @Override
    public void checkUser(String account) {
        SQLiteDatabase db = null;
        boolean isExistence = false;//定义用户是否存在的标示量
        try {
            /*打开数据库*/
            db = mHelper.getReadableDatabase();
            /*开启事务*/
            db.beginTransaction();
            /*查询数据库*/
            Cursor cursor = db.query(Constants.USER_TB_NAME, null, "account =?", new String[]{account}, null, null, null);
            if (cursor.getCount() == 0) {
                isExistence = false;
            } else {
                isExistence = true;
            }

            /*事务成功*/
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
            /*修改标示量*/
            isExistence = true;
        } finally {
            /*关闭数据库*/
            if (db != null) {
                /*结束事务*/
                db.endTransaction();
                db.close();
            }
            /*通知结果*/
            if (mViewCallback != null) {
                mViewCallback.onCheckUserResult(isExistence);
            }
        }
    }
}
