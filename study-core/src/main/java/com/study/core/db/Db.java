package com.study.core.db;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.study.core.util.SpringContextHolder;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 便捷数据库操作类
 * <p>
 * <p>
 * 本类的使用情景：
 * <p>
 * 1.单纯想创建现有的Mapper
 * <p>例如:
 * Db.getMapper(UserLoginMapper.class).selectById("14779707158513204");<br>
 * <p>
 */
@SuppressWarnings("all")
public class Db<T> {

    /*每个DB类，包装一个Mapper接口，这个clazz就是接口的类类型，例如UserMapper.class*/
    private Class<T> clazz;

    /*Mapper的父接口*/
    private BaseMapper<?> baseMapper;


    private Db(Class clazz) {
        this.clazz = clazz;
        this.baseMapper = (BaseMapper<?>) SpringContextHolder.getBean(clazz);
    }


    /**
     * 创建包含指定mapper的Db工具类，使用本类的第一种用法
     * @param clazz mapper的类类型
     * @param <T>
     * @return
     */
    public static <T> Db<T> create(Class<T> clazz) {
        return new Db<>(clazz);
    }


    /**
     * 获取一个mapper的快捷方法
     * @return
     */
    public BaseMapper<?> getBaseMapper() {
        return this.baseMapper;
    }


    /**
     * 获取一个mapper的快捷方式
     * @param clazz  mapper类的类对象
     * @param <T>
     * @return
     */
    public static <T> T getMapper(Class<T> clazz) {
        return SpringContextHolder.getBean(clazz);
    }


    /**
     * 通过一个条件获取数据库中的一条记录(会返回null)
     * @param condition
     * @param value
     * @param <E>
     * @return
     */
    public <E> E selectOneByCondition(String condition, Object value) {
        List<?> result = selectOneByConList(condition, value);
        if (result != null || result.size() != 0) {
            return (E)result.get(0);
        }
        return null;
    }


    /**
     * 通过一个条件获取一堆记录(会返回null)
     * @param condition
     * @param value
     * @param <E>
     * @return
     */
    public <E> List<E> selectOneByConList(String condition, Object value) {
        HashMap<String, Object> conditionMap = new HashMap<String, Object>();
        conditionMap.put(condition, value);

        List<E> list = (List<E>)this.baseMapper.selectByMap(conditionMap);
        if (list == null || list.size() == 0) {
            return null;
        }
        return list;
    }


}
