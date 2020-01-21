package com.study.core.cache;

import java.util.List;

/**
 * 缓存工具类
 */
public class CacheKit {

    private static ICache defaultFactory = new EhcacheFactory();


    public static void put(String cacheName, Object key, Object value) {
        defaultFactory.put(cacheName, key, value);
    }


    public static <T> T get(String cacheName, Object key) {
        return defaultFactory.get(cacheName, key);
    }


    @SuppressWarnings("rawtypes")
    public static List getKeys(String cacheName){
        return defaultFactory.getKeys(cacheName);
    }


    public static void remove(String cacheName, Object key){
        defaultFactory.remove(cacheName, key);
    }

    public static void removeAll(String cacheName) {
        defaultFactory.removeAll(cacheName);
    }

    public static <T> T get(String cacheName, Object key, ILoader iLoader){
        return defaultFactory.get(cacheName, key, iLoader);
    }

    public static <T> T get(String cacheName, Object key, Class<? extends ILoader> iLoaderClass) {
        return defaultFactory.get(cacheName, key, iLoaderClass);
    }



}
