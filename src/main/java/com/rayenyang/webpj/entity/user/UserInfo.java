package com.rayenyang.webpj.entity.user;

/**
 * description:
 * Created by rayenyang on 2017/9/4.
 */
public class UserInfo {
    private Integer userId;
    private String name;
    private String loginIp;
    
    @Override
    public String toString() {
        return "UserInfo{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", loginIp='" + loginIp + '\'' +
                '}';
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getLoginIp() {
        return loginIp;
    }
    
    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }
}
