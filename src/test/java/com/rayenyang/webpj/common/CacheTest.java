package com.rayenyang.webpj.common;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.MemoryUnit;

import java.util.Iterator;

/**
 * description:
 * Created by rayenyang on 2017/6/6.
 */
public class CacheTest {
    public static void main(String[] args) {
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build();
        cacheManager.init();
        final Cache<Integer, Integer> cache = cacheManager.createCache("cache",
                CacheConfigurationBuilder.newCacheConfigurationBuilder(Integer.class, Integer.class, ResourcePoolsBuilder.heap(2).offheap(1024, MemoryUnit.KB)));
        cache.put(1,1);
        cache.put(2,2);
//        System.out.println(cache.get(1));
        cache.put(3,3);
        System.out.println("------------------------------");
        final Iterator<Cache.Entry<Integer, Integer>> iterator = cache.iterator();
        iterator.forEachRemaining(entry->{
            System.out.println(entry.getKey() + ":" + entry.getValue());
        });
    }
}
