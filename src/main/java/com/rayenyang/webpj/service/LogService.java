package com.rayenyang.webpj.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

/**
 * description:
 * Created by rayenyang on 2017/6/26.
 */
@Service
public class LogService {
    private final Log log = LogFactory.getLog(getClass());
    
    public void testLog(){
        log.info("test info ");
        log.debug("test debug");
        log.error("test error");
        log.fatal("test fatal");
        System.out.println("finish~");
    }
}
