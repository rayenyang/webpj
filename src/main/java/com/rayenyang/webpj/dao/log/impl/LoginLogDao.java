package com.rayenyang.webpj.dao.log.impl;

import com.rayenyang.webpj.dao.log.ILoginLogDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.Date;

/**
 * Created by rayenyang on 2017/2/9.
 */
@Repository
public class LoginLogDao implements ILoginLogDao {

    @Inject
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insertLog(int user_id, String ip, Date loginTime) {
        String sql = "INSERT INTO t_login_log(user_id,ip,login_time) VALUES (?,?,?)";
        jdbcTemplate.update(sql,user_id,ip,loginTime);
    }
}
