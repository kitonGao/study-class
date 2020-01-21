package com.study.core.base.warpper;

import java.util.List;
import java.util.Map;

/**
 * 控制器查询结果的包装基类
 */
public abstract class BaseControllerWarpper {

    public Object obj = null;

    public BaseControllerWarpper(Object o){
        this.obj =o;
    }


    @SuppressWarnings("unchecked")
    public Object warp(){
        if (this.obj instanceof List) {
            List<Map<String,Object>> list = (List<Map<String,Object>>) this.obj;
            list.stream().forEach(x -> {
                warpTheMap(x);
            });
            return list;
        }else if (this.obj instanceof Map) {
            Map<String,Object> map = (Map<String, Object>) this.obj;
            warpTheMap(map);
            return map;
        } else {
            return this.obj;
        }
    }


    protected abstract void warpTheMap(Map<String, Object> map);

}
