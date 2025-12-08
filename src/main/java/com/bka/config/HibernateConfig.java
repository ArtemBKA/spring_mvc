package com.bka.config;

import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


//@Configuration
//@EnableTransactionManagement
//@PropertySource("classpath:database.properties")
public class HibernateConfig {
//    private Environment env;
//
//    @Autowired
//    public HibernateConfig(Environment env) {
//        this.env = env;
//    }
//
//    @Bean
//    public LocalSessionFactoryBean sessionFactory() {
//        LocalSessionFactoryBean factory = new LocalSessionFactoryBean();
//        factory.setPackagesToScan("com.bka.entity");
//        factory.setDataSource(dataSource());
//        factory.setHibernateProperties(hibernateProperties());
//        return factory;
//    }
//
//    @Bean
//    public Properties hibernateProperties() {
//        Properties properties = new Properties();
//        properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
//        properties.put("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
//        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
//        properties.put("hibernate.current_session_context_class", env.getProperty("hibernate.current_session_context_class"));
//        return properties;
//    }
//
//    @Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setUsername(env.getRequiredProperty("db.connection.user"));
//        dataSource.setPassword(env.getRequiredProperty("db.connection.pass"));
//        dataSource.setUrl(env.getRequiredProperty("db.connection.url"));
//        dataSource.setDriverClassName(env.getRequiredProperty("db.connection.driver"));
//        return dataSource;
//    }
//
//    @Bean
//    public HibernateTransactionManager transactionManager() {
//        HibernateTransactionManager txManager = new HibernateTransactionManager();
//        txManager.setSessionFactory(sessionFactory().getObject());
//        return txManager;
//    }
//
//    @Bean
//    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
//        return new JdbcTemplate(dataSource);
//    }
}
