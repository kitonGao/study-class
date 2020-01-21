package com.study.core.config;

import com.study.core.mutidatasource.aop.MultiSourceExAop;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

/**
 * 多数据源配置
 */
@Configuration
@ConditionalOnProperty(prefix = "study", name = "muti-datasource-open", havingValue = "true")
public class DefaultMultiConfig {

    public MultiSourceExAop multiSourceExAop(){
        return new MultiSourceExAop();
    }


}
