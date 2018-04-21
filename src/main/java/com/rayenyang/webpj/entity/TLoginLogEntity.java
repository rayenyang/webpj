package com.rayenyang.webpj.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * description:
 * Created by rayenyang on 2017/3/27.
 */
@Entity
@Table(name = "t_login_log", schema = "webpj", catalog = "")
public class TLoginLogEntity {
    private Integer loginLogId;
    private Integer userId;
    private String ip;
    private Date loginTime;
    private String detail;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "login_log_id", nullable = false)
    public Integer getLoginLogId() {
        return loginLogId;
    }

    public void setLoginLogId(Integer loginLogId) {
        this.loginLogId = loginLogId;
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "ip", nullable = true, length = 23)
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Basic
    @Column(name = "login_time", nullable = true)
    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    @Basic
    @Column(name = "detail", nullable = true, length = -1)
    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TLoginLogEntity that = (TLoginLogEntity) o;

        if (loginLogId != null ? !loginLogId.equals(that.loginLogId) : that.loginLogId != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (ip != null ? !ip.equals(that.ip) : that.ip != null) return false;
        if (loginTime != null ? !loginTime.equals(that.loginTime) : that.loginTime != null) return false;
        if (detail != null ? !detail.equals(that.detail) : that.detail != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = loginLogId != null ? loginLogId.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (ip != null ? ip.hashCode() : 0);
        result = 31 * result + (loginTime != null ? loginTime.hashCode() : 0);
        result = 31 * result + (detail != null ? detail.hashCode() : 0);
        return result;
    }
}
