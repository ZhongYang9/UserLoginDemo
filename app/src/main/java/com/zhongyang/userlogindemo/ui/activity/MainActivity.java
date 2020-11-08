package com.zhongyang.userlogindemo.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.zhongyang.userlogindemo.R;
import com.zhongyang.userlogindemo.base.BaseActivity;
import com.zhongyang.userlogindemo.model.domain.User;
import com.zhongyang.userlogindemo.presenter.impl.MainPresenterImpl;
import com.zhongyang.userlogindemo.ui.adapter.UserListAdapter;
import com.zhongyang.userlogindemo.view.IMainViewCallback;

import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements IMainViewCallback {

    private static final String TAG = "MainActivity";
    @BindView(R.id.rv_userList)
    RecyclerView rv_userList;
    private MainPresenterImpl mMainPresenter;
    private UserListAdapter mUserListAdapter;

    //------------------------------复写父类方法-----------------------
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected int getStatusBarColor() {
        return R.color.colorTransparent;
    }

    @Override
    protected void initPresenterEvent() {
        //获取逻辑层操作对象
        mMainPresenter = MainPresenterImpl.getMainPresenter();
        //注册接口
        mMainPresenter.registerViewCallback(this);
        //获取用户列表
        mMainPresenter.getUserList();
    }

    @Override
    protected void initActivityView() {
        //设置适配器相关
        rv_userList.setLayoutManager(new LinearLayoutManager(this));
        mUserListAdapter = new UserListAdapter();
        rv_userList.setAdapter(mUserListAdapter);
    }

    @Override
    protected void release() {
        //释放资源
        if (mMainPresenter != null) {
            mMainPresenter.unRegisterViewCallback(this);
        }
    }
    //------------------------------复写父类方法 end-----------------------

    @Override
    public void onGetUserListLoaded(List<User> users) {
        //Log.d(TAG, "用户列表信息 ==> " + users);
        //将数据设置到适配器
        if (mUserListAdapter != null) {
            mUserListAdapter.setUserData(users);
        }
    }
}