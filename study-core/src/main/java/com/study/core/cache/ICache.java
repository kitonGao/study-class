package com.study.core.cache;

import java.util.List;

/**
 * 通用缓存的接口
 */
public interface ICache {

    void put(String cacheName, Object key, Object value);


    <T> T get(String cacheName, Object key);


    //SuppressWarning 压制警告，即去除警告。
    // rawtypes是说传参时也要传递带泛型的参数
    @SuppressWarnings("rawtypes")
    List getKeys(String cacheName);


    void remove(String cacheName, Object key);

    void removeAll(String cacheName);


    <T> T get(String cacheName, Object key, ILoader iLoader);


    <T> T get(String cacheName, Object key, Class< ? extends  ILoader> iLoaderClass);


}
