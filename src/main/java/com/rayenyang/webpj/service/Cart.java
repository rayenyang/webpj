package com.rayenyang.webpj.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
@Service("cart")
@Scope(value = WebApplicationContext.SCOPE_SESSION,proxyMode = ScopedProxyMode.INTERFACES)
public class Cart implements ICart{
    private static final Log logger = LogFactory.getLog(Cart.class);
    private int tag = (int) (Math.random()*100);
    

    public int get(){
        return tag;
    }
    
    
    @PostConstruct
    private void sessionCreate(){
        logger.info("Session Cart create");
    }
    
    @PreDestroy
    private void sessionDestroy(){
        logger.info("Session Cart destroy");
    }
}
