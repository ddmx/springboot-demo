package com.dmx.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
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
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author Matthew
 * Create Time: 2019-07-18 23:22
 */
@Configuration
@MapperScan(basePackages = "com.dmx.demo.mapper.test2", sqlSessionTemplateRef  = "test2SqlSessionTemplate")
@EnableTransactionManagement
public class Test2DataSourceConfig {

    @Value("${test2.mapperXmlPath}")
    private String test2MapperXmlPath;

    @Bean(name = "test2DataSource")
    @ConfigurationProperties(prefix = "test2.datasource.druid")
    public DataSource dataSource(){
        return DataSourceBuilder.create().type(DruidDataSource.class).build();
    }

    @Bean(name = "test2TransactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("test2DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "test2SqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("test2DataSource") DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);

        /*设置mapper文件位置*/
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(test2MapperXmlPath));
        /*设置实体类映射规则: 下划线 -> 驼峰*/
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        sessionFactory.setConfiguration(configuration);
        return sessionFactory.getObject();
    }

    @Bean(name = "test2SqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("test2SqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}