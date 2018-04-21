package com.rayenyang.webpj.entity.user;

import com.fasterxml.jackson.annotation.JsonView;

import java.util.Date;

/**
 * Created by rayenyang on 2017/2/9.
 */
public class User {
    public interface WithPassword{}
    public interface WithOutPassword{}
    private int userId;
    private String userName;
    private String password;
    private int credits;
    private Date lastVisit;
    private String lastIp;
    
    public User() {
    }
    
    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
    
    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", credits=" + credits +
                ", lastVisit=" + lastVisit +
                ", lastIp='" + lastIp + '\'' +
                '}';
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @JsonView(WithPassword.class)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public Date getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(Date lastVisit) {
        this.lastVisit = lastVisit;
    }

    public String getLastIp() {
        return lastIp;
    }

    public void setLastIp(String lastIp) {
        this.lastIp = lastIp;
    }
}
