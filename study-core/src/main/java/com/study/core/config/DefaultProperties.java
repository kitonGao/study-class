package com.study.core.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 默认的配置
 */
@Configuration
@PropertySource("classpath:/default-config.properties")
public class DefaultProperties {

    public DruidDataSource druidDataSource(){
        return new DruidDataSource();
    }

}
