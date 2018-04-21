package com.rayenyang.webpj.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * description:
 * Created by rayenyang on 2017/3/29.
 */
@Service("cart2")
@Scope(value = WebApplicationContext.SCOPE_SESSION,proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart2 {
    private static final Log logger = LogFactory.getLog(Cart2.class);
    private int tag = (int) (Math.random()*100);
    
    @Autowired
    private ICart cart;

    public int get(){
        final StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement stackTraceElement : stackTrace) {
            System.out.println(stackTraceElement);
        }
        return tag;
    }
    
    
    @PostConstruct
    private void sessionCreate(){
        logger.info("Session Cart2 create");
    }
    
    @PreDestroy
    private void sessionDestroy(){
        logger.info("Session Cart2 destroy");
        System.out.println(cart == null);
        cart.get();
    }
    
    
    public void printTime(){
        logger.info(new DateTime().toString("yyyy-MM-dd HH:mm:ss"));
    }
}
