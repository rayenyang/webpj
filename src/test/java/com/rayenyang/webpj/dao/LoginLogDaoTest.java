package com.rayenyang.webpj.dao;

import com.rayenyang.webpj.config.ApplicationConfig;
import com.rayenyang.webpj.dao.log.ILoginLogDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.io.*;
import java.util.Date;

/**
 * Created by rayenyang on 2017/2/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class LoginLogDaoTest {
    @Inject
    private ILoginLogDao dao;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    public void testInsertLoginLog() throws FileNotFoundException {
        dao.insertLog(1,"192.168.229.222",new Date());
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("")));
    }

    @Test
    public void testUnsignedInt(){
        jdbcTemplate.query("select dev_id from acglog.20160914_group_flux_webapp order by dev_id desc limit 10", resultSet -> {
            System.out.println(resultSet.getLong(1));
        });

        jdbcTemplate.update(null,new GeneratedKeyHolder());
    }

    @Test
    public void test(){

    }
}
