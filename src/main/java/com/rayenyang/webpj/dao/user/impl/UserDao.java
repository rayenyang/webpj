package com.rayenyang.webpj.dao.user.impl;

import com.rayenyang.webpj.dao.user.IUserDao;
import com.rayenyang.webpj.entity.user.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;

/**
 * Created by rayenyang on 2017/2/9.
 */

@Repository("userDao")
public class UserDao implements IUserDao {
    @Inject
    private JdbcTemplate jdbcTemplate;


    @Override
    public int getMatchCount(String userName, String password) {
        String sql = "SELECT count(*) FROM t_user WHERE user_name=? AND password=?";
        return jdbcTemplate.queryForObject(sql, Integer.class, userName, password);
    }

    @Override
    public User findUserByUsername(String userName) {
        String sql = "SELECT * FROM t_user WHERE user_name=?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            User user = new User();
            user.setUserId(rs.getInt("user_id"));
            user.setUserName(rs.getString("user_name"));
            user.setPassword(rs.getString("password"));
            user.setCredits(rs.getInt("credits"));
            user.setLastVisit(rs.getDate("last_visit"));
            user.setLastIp(rs.getString("last_ip"));
            return user;
        }, userName);
    }

    @Override
    public void updateLoginLog(User user) {
        String sql = "UPDATE t_user SET last_visit=?,last_ip=?,credits=? WHERE user_id=?";
//        jdbcTemplate.update(sql,user.getLastVisit(),user.getLastIp(),user.getCredits(),user.getUserId());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            final PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setTimestamp(1, new Timestamp(user.getLastVisit().getTime()));
            preparedStatement.setString(2, user.getLastIp());
            preparedStatement.setInt(3, user.getCredits());
            preparedStatement.setInt(4, user.getUserId());
            return preparedStatement;
        }, keyHolder);
        System.out.println(keyHolder.getKey().intValue());
    }
}
