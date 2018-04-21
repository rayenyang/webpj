package com.rayenyang.webpj.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * description:
 * Created by rayenyang on 2017/5/26.
 */
@Configuration
public class SpringContextHolder implements ApplicationContextAware{

    public static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
