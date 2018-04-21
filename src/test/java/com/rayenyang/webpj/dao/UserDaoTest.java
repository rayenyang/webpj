//package com.rayenyang.webpj.dao;
//
//import com.rayenyang.webpj.config.ApplicationConfig;
//import com.rayenyang.webpj.dao.user.impl.UserDao;
//import com.rayenyang.webpj.entity.user.User;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.PreparedStatementSetter;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import javax.annotation.Resource;
//import javax.inject.Inject;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.util.Date;
//
///**
// * Created by rayenyang on 2017/2/9.
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = ApplicationConfig.class)
//public class UserDaoTest {
//    @Resource
//    private UserDao dao;
//
//    @Inject
//    private JdbcTemplate jdbcTemplate;
//
//    @Test
//    public void testLogin() {
//        final int matchCount = dao.getMatchCount("test", "aaa");
//        Assert.assertTrue(matchCount > 0);
//    }
//
//    @Test
//    public void testFindUser() {
//        final User user = dao.findUserByUsername("test");
//        System.out.println(user);
//        Assert.assertNotNull(user);
//    }
//
//    @Test
//    public void testUpdateLoginlog() {
//        User user = new User();
//        user.setUserId(1);
//        user.setLastVisit(new Date());
//        user.setLastIp("192.168.229.222");
//        user.setCredits(10);
//        dao.updateLoginLog(user);
//    }
//
//    @Test
//    public void testDelete() {
//        System.out.println(jdbcTemplate.update("DELETE FROM t_user WHERE user_id=23"));
//        jdbcTemplate.update("", new PreparedStatementSetter() {
//            @Override
//            public void setValues(PreparedStatement ps) throws SQLException {
//                ps.setObject(1, "");
//            }
//        });
//    }
//
//    @Test
//    public void test() {
//        final String username = jdbcTemplate.queryForObject("SELECT user_name FROM t_user WHERE user_id=100", String.class);
//        System.out.println(username);
//    }
//}
