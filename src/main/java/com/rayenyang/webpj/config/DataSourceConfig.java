package com.rayenyang.webpj.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.hibernate.SessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * 配置数据源
 * Created by rayenyang on 2017/1/6.
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = {"com.rayenyang.webpj.mapper"})
//@ImportResource("classpath:spring-transaction.xml")
public class DataSourceConfig {
    @Value("${db.driver.class.name}")
    private String dbDriverName;
    @Value("${db.user}")
    private String dbUser;
    @Value("${db.password}")
    private String dbPassword;
    @Value("${db.url}")
    private String dbUrl;
    
    @Autowired
    private ResourceLoader resourceLoader;


    @Bean
    public DataSource dataSource(){
        System.out.println(dbUrl);
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(dbDriverName);
        druidDataSource.setUsername(dbUser);
        druidDataSource.setPassword(dbPassword);
        druidDataSource.setUrl(dbUrl);
        return druidDataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public PlatformTransactionManager txManager(SessionFactory sessionFactory){
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        return  transactionManager;
    }
    
    @Profile("test")
    @Bean
    public PlatformTransactionManager mybatisTxManager(DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource){
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(dataSource);
        localSessionFactoryBean.setPackagesToScan("com.rayenyang.webpj.entity");
        localSessionFactoryBean.setHibernateProperties(hibernateProps());
        return localSessionFactoryBean;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws IOException {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
//        sessionFactoryBean.setTypeAliasesPackage("com.rayenyang.webpj.entity");
        sessionFactoryBean.setDataSource(dataSource);
        final ResourcePatternResolver loader = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
//        sessionFactoryBean.setMapperLocations(loader.getResources("classpath*:mapper/*.xml"));
        return sessionFactoryBean;
    }
    
//    @Bean
//    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
//        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(this.sqlSessionFactory(dataSource()).getObject());
//        return sqlSessionTemplate;
//    }
//
//    @Bean
//    public MapperScannerConfigurer mapperScannerConfigurer(){
//        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
//        configurer.setBasePackage("com.rayenyang.webpj.mapper");
//        return configurer;
//    }
    

    private Properties hibernateProps(){
        return new Properties(){
            {
                setProperty("hibernate.dialect","org.hibernate.dialect.MySQLDialect");
            }
        };
    }
}
