package com.rayenyang.webpj.service;

import com.rayenyang.webpj.config.ApplicationConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * description:
 * Created by rayenyang on 2017/6/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class LogServiceTest {
    
    @Autowired
    private LogService logService;
    
    @Test
    public void testLog() throws Exception {
        logService.testLog();
    }
    
}