package com.dmx.demo.config;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInterceptor;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.datasource.pooled.PoolState;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author Matthew
 * Create Time: 2019-07-18 23:22
 */
@Configuration
@MapperScan(basePackages = "com.dmx.demo.mapper.test1", sqlSessionTemplateRef  = "test1SqlSessionTemplate")
@EnableTransactionManagement
public class Test1DataSourceConfig {

    @Value("${test1.mapperXmlPath}")
    private String test1MapperXmlPath;

    @Bean(name = "test1DataSource")
    @Primary
    @ConfigurationProperties(prefix = "test1.datasource.hikari")
    public DataSource dataSource(){
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean(name = "test1TransactionManager")
    @Primary
    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("test1DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "test1SqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("test1DataSource") DataSource dataSource) throws Exception {

        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);

        /*设置mapper文件位置*/
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(test1MapperXmlPath));

        /*设置实体类映射规则: 下划线 -> 驼峰*/
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        sessionFactory.setConfiguration(configuration);
        return sessionFactory.getObject();
    }

    @Bean(name = "test1SqlSessionTemplate")
    @Primary
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("test1SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}