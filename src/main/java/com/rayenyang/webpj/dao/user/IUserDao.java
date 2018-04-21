package com.rayenyang.webpj.dao.user;

import com.rayenyang.webpj.entity.user.User;

/**
 * Created by rayenyang on 2017/2/9.
 */
public interface IUserDao {

    int getMatchCount(String userName,String password);
    User findUserByUsername(String userName);
    void updateLoginLog(User user);
}
