package com.rayenyang.webpj.mapper;

import com.rayenyang.webpj.entity.user.User;
import com.rayenyang.webpj.entity.user.UserInfo;

import java.util.List;

/**
 * description:
 * Created by rayenyang on 2017/7/13.
 */
public interface UserMapper {
    User selectUser(String name);
    
    List<User> listUser();
    
    void insert(User user);
    
    UserInfo selectUserInfoById(Integer id);
}
