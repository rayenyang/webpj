package com.rayenyang.webpj.service;

import com.rayenyang.webpj.config.ApplicationConfig;
import com.rayenyang.webpj.entity.Argument;
import com.rayenyang.webpj.entity.Fruit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * description:
 * Created by rayenyang on 2017/6/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class TestServiceTest {
   
    
    @Autowired
    private TestService testService;
    
    private static TestService staticService;
    
    @PostConstruct
    private void init(){
        staticService = testService;
    }
    
    @Test
    public void testTransation() throws Exception {
        staticService.testTransation("gg24");
    }
    
    
    @Test
    public void test1() throws Exception {
        final List<String> list = testService.test("aa");
        System.out.println("test:" + list);
    }
    
    @Test
    public void testValid(){
        Argument arg = new Argument();
        arg.setMaxAge(3);
        arg = null;
        testService.printArg(arg);
    }
    
    @Test
    public void testAutoBoxing(){
        testService.autoBox1(1,"1");
        testService.autoBox2(2);
        testService.test("autoBoxing");
        
    }
    
    
    @Test
    public void eventTest(){
        Fruit fruit = new Fruit();
        testService.createEvent("yrh",2);
    }
    
    
    
}