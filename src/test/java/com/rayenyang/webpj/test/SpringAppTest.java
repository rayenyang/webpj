package com.rayenyang.webpj.test;

import com.rayenyang.webpj.config.ApplicationConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

/**
 * description:
 * Created by rayenyang on 2017/8/25.
 */
public abstract class SpringAppTest{
    public static void main(String[] args) {
        AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
//        TestService testService = (TestService) configApplicationContext.getBean("pj_test");
//        System.out.println(testService);
//        testService.autoBox2(2);
        final Environment env = configApplicationContext.getBean(Environment.class);
        final String[] activeProfiles = env.getActiveProfiles();
        final String[] defaultProfiles = env.getDefaultProfiles();
       
        final String yrh = env.getProperty("yrh");
        final String pwd = env.getProperty("pwd");
        final String yrh123 = env.getProperty("yrh123");
        System.out.println(yrh);
        System.out.println(pwd);
        System.out.println(yrh123);
    }
}
