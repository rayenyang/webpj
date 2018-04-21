package com.rayenyang.webpj.service.impl;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * description:
 * Created by rayenyang on 2017/6/5.
 */
@Service
public class CacheService {

    private CacheManager cacheManager;

    @PostConstruct
    public void prepare() {
        cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build();
        cacheManager.init();
        cacheManager.createCache("myCache",
                CacheConfigurationBuilder.newCacheConfigurationBuilder(Integer.class, Integer.class, ResourcePoolsBuilder.heap(10)));
    }

    @PreDestroy
    public void close() {
        cacheManager.close();
    }

    public void cache(Integer key, Integer value) {
        Cache<Integer, Integer> myCache = cacheManager.getCache("myCache", Integer.class, Integer.class);
        myCache.put(key, value);
    }

    public Integer getFromCache(Integer key) {
        final Cache<Integer, Integer> myCache = cacheManager.getCache("myCache", Integer.class, Integer.class);
        return myCache.get(key);
    }
}
