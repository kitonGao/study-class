package com.study.core.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.study.core.config.properties.DruidProperties;
import com.study.core.datascope.DataScopeInterceptor;
import org.springframework.context.annotation.Bean;

public class SingleDataSourceConfig {


    /**
     * 但数据源连接池配置
     * @param druidProperties
     * @return
     */
    @Bean
    public DruidDataSource dataSource(DruidProperties druidProperties){
        DruidDataSource dataSource = new DruidDataSource();
        druidProperties.config(dataSource);
        return dataSource;
    }


    /**
     * mybatis-plus分页插件
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }


    /**
     * 数据范围mybatis插件
     * @return
     */
    public DataScopeInterceptor dataScopeInterceptor(){
        return new DataScopeInterceptor();
    }


    /**
     * 乐观锁mybatis插件
     * @return
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor(){
        return new OptimisticLockerInterceptor();
    }

}
