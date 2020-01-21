package com.study.core.mutidatasource.aop;

import com.study.core.config.properties.MutiDataSourceProperties;
import com.study.core.mutidatasource.DataSourceContextHolder;
import com.study.core.mutidatasource.annotion.DataSource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;

import java.lang.reflect.Method;


@Aspect
public class MultiSourceExAop implements Ordered {

    private Logger log = LoggerFactory.getLogger(MultiSourceExAop.class);

    @Autowired
    private MutiDataSourceProperties mutiDataSourceProperties;


    @Pointcut(value = "@annotation(com.study.core.mutidatasource.annotion.DataSource)")
    public void cut(){}

    public Object around(ProceedingJoinPoint point) throws Throwable{
        Signature signature = point.getSignature();
        MethodSignature methodSignature = null;
        if ( !(signature instanceof MethodSignature) ){
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        methodSignature = (MethodSignature) signature;

        Object target = point.getTarget();
        Method currentMethod = target.getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
        DataSource dataSource = currentMethod.getAnnotation(DataSource.class);
        if (dataSource != null){
            DataSourceContextHolder.setDataSourceType(dataSource.name());
            log.debug("设置数据源为：" + dataSource.name());
        } else {
            DataSourceContextHolder.setDataSourceType(mutiDataSourceProperties.getDataSourceNames()[0]);
            log.debug("设置数据源为：dataSourceCurrent");
        }

        try {
            return point.proceed();
        }finally {
            log.debug("清空数据源信息");
            DataSourceContextHolder.clearDataSourceType();
        }

    }


    /**
     * aop的顺序要早于Spring的事务
     * @return
     */
    @Override
    public int getOrder() {
        return 1;
    }


}
