package com.study.core.config;


import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.study.core.config.properties.DruidProperties;
import com.study.core.config.properties.MutiDataSourceProperties;
import com.study.core.datascope.DataScopeInterceptor;
import com.study.core.mutidatasource.DynamicDataSource;
import com.study.core.mutidatasource.aop.MultiSourceExAop;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.sql.SQLException;
import java.util.HashMap;

/**
 * 多数据源配置<br/>
 * <p>
 * 注：由于引入多数据源，所以让spring事务的aop要在多数据源切换aop的后面
 */
@Configuration
@ConditionalOnProperty(prefix = "study.muti-datasource", name = "open", havingValue = "true")
@EnableTransactionManagement(order = 2)
@MapperScan(basePackages = {"com.study.modular.*.dao"})
public class MultiDataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "study.muti-datasource")
    public MutiDataSourceProperties mutiDataSourceProperties(){
        return new MutiDataSourceProperties();
    }

    @Bean
    public MultiSourceExAop multiSourceExAop(){
        return new MultiSourceExAop();
    }

    /**
     * study数据源
     * @param druidProperties
     * @return
     */
    private DruidDataSource dataSource(DruidProperties druidProperties) {
        DruidDataSource dataSource = new DruidDataSource();
        druidProperties.config(dataSource);
        return dataSource;
    }


    /**
     * 多数据源，第二个数据源
     * @param druidProperties
     * @param mutiDataSourceProperties
     * @return
     */
    private DruidDataSource bizDataSource(DruidProperties druidProperties, MutiDataSourceProperties mutiDataSourceProperties) {
        DruidDataSource dataSource = new DruidDataSource();
        druidProperties.config(dataSource);
        mutiDataSourceProperties.config(dataSource);
        return dataSource;
    }


    /**
     * 多数据源连接池配置
     * @param druidProperties
     * @param mutiDataSourceProperties
     * @return
     */
    @Bean
    public DynamicDataSource mutiDataSource(DruidProperties druidProperties, MutiDataSourceProperties mutiDataSourceProperties) {
        DruidDataSource dataSourceStudy = dataSource(druidProperties);
        DruidDataSource bizDataSource = bizDataSource(druidProperties, mutiDataSourceProperties);


        try {
            dataSourceStudy.init();
            bizDataSource.init();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put(mutiDataSourceProperties.getDataSourceNames()[0], dataSourceStudy);
        hashMap.put(mutiDataSourceProperties.getDataSourceNames()[1], bizDataSource);
        dynamicDataSource.setTargetDataSources(hashMap);
        dynamicDataSource.setDefaultTargetDataSource(dataSourceStudy);
        return dynamicDataSource;

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
     * 数据分为mybatis插件
     * @return
     */
    @Bean
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


    /**
     * 事务配置
     * @param mutiDataSource
     * @return
     */
    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(DynamicDataSource mutiDataSource) {
        return new DataSourceTransactionManager(mutiDataSource);
    }


}
