package com.rayenyang.webpj.entity;

import org.hibernate.annotations.GeneratorType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ValueGenerationType;

import javax.persistence.*;
import java.util.Date;

/**
 * description:
 * Created by rayenyang on 2017/3/27.
 */
@Entity
@Table(name = "t_user", schema = "webpj", catalog = "")
public class TUserEntity {
    private Integer userId;
    private String userName;
    private Integer credits;
    private String password;
    private Date lastVisit;
    private String lastIp;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "user_name", nullable = false, length = 30)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "credits", nullable = true)
    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    @Basic
    @Column(name = "password", nullable = true, length = 32)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "last_visit", nullable = true)
    public Date getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(Date lastVisit) {
        this.lastVisit = lastVisit;
    }

    @Basic
    @Column(name = "last_ip", nullable = true, length = 1000)
    public String getLastIp() {
        return lastIp;
    }

    public void setLastIp(String lastIp) {
        this.lastIp = lastIp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TUserEntity that = (TUserEntity) o;

        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        if (credits != null ? !credits.equals(that.credits) : that.credits != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (lastVisit != null ? !lastVisit.equals(that.lastVisit) : that.lastVisit != null) return false;
        if (lastIp != null ? !lastIp.equals(that.lastIp) : that.lastIp != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (credits != null ? credits.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (lastVisit != null ? lastVisit.hashCode() : 0);
        result = 31 * result + (lastIp != null ? lastIp.hashCode() : 0);
        return result;
    }
}
