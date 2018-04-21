package com.rayenyang.webpj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * description:
 * Created by rayenyang on 2017/6/5.
 */
@Service
public class CacheTest {

    @Autowired
    private CacheService cacheService;

    public int getDouble(int i) {
        final Integer fromCache = cacheService.getFromCache(i);
        if (fromCache != null) {
            System.out.println("get from cache....");
            return fromCache;
        }
        System.out.println("get double....");
        final int result = i * 2;
        cacheService.cache(i, result);
        return result;
    }
}
