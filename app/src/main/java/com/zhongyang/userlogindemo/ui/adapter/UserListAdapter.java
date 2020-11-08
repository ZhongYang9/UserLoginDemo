package com.zhongyang.userlogindemo.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zhongyang.userlogindemo.R;
import com.zhongyang.userlogindemo.model.domain.User;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @项目名称 UserLoginDemo
 * @类名 UserListAdapter
 * @包名 com.zhongyang.userlogindemo.ui.adapter
 * @创建时间 2020/11/8 22:25
 * @作者 钟阳
 * @描述
 */
public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {

    private List<User> mUsersData = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //载布局
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //绑定数据
        User user = mUsersData.get(position);
        holder.setItemData(user);
    }

    @Override
    public int getItemCount() {
        return mUsersData.size();
    }

    /**
     * 外部设置的用户数据进来
     *
     * @param users
     */
    public void setUserData(List<User> users) {
        mUsersData.clear();
        mUsersData.addAll(users);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_userAccount)
        TextView tv_userAccount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setItemData(User user) {
            //设置账号
            tv_userAccount.setText(user.getAccount());
        }
    }
}
