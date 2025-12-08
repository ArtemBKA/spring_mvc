package com.bka.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author Neil Alishev
 */
@Configuration
@ComponentScan("com.bka")
@PropertySource("classpath:database.properties")
@EnableTransactionManagement
@EnableJpaRepositories("com.bka.repositories")
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    private final ApplicationContext applicationContext;
    private final Environment env;

    @Autowired
    public WebConfig(ApplicationContext applicationContext, Environment env) {
        this.applicationContext = applicationContext;
        this.env = env;
        System.out.println("INSIDE WEB CONFIG");
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/views/");
        templateResolver.setSuffix(".html");
        templateResolver.setCharacterEncoding("UTF-8");
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        resolver.setCharacterEncoding("UTF-8");

        registry.viewResolver(resolver);
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(env.getRequiredProperty("db.connection.driver"));
        dataSource.setUrl(env.getRequiredProperty("db.connection.url"));
        dataSource.setUsername(env.getRequiredProperty("db.connection.user"));
        dataSource.setPassword(env.getRequiredProperty("db.connection.pass"));

        return dataSource;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", env.getRequiredProperty("hibernate.format_sql"));

        return properties;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("com.bka.entity");

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(hibernateProperties());

        return em;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }
}

//package com.bka.config;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.orm.hibernate5.HibernateTransactionManager;
//import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.thymeleaf.spring5.SpringTemplateEngine;
//import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
//import org.thymeleaf.spring5.view.ThymeleafViewResolver;
//
//import javax.sql.DataSource;
//import java.sql.DriverManager;
//import java.util.Objects;
//import java.util.Properties;
//@EnableTransactionManagement
//@EnableWebMvc
//@EnableJpaRepositories("com.bka.repositories")
//@Configuration
//@PropertySource("classpath:database.properties")
//@ComponentScan(value = "com.bka")
//public class WebConfig implements WebMvcConfigurer {
//    private Environment env;
//    private ApplicationContext applicationContext;
//
//    @Autowired
//    public WebConfig(Environment env, ApplicationContext applicationContext) {
//        this.applicationContext = applicationContext;
//        this.env = env;
//    }
//
//    @Bean
//    public SpringResourceTemplateResolver templateResolver() {
//        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
//        templateResolver.setApplicationContext(applicationContext);
//        templateResolver.setPrefix("/WEB-INF/views/");
//        templateResolver.setSuffix(".html");
//        templateResolver.setCharacterEncoding("UTF-8");
////        templateResolver.setTemplateMode("HTML");
////        templateResolver.setCacheable(false);
//        return templateResolver;
//    }
//
//    @Bean
//    public SpringTemplateEngine templateEngine() {
//        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//        templateEngine.setTemplateResolver(templateResolver());
//        templateEngine.setEnableSpringELCompiler(true);
//        return templateEngine;
//    }
////
////    @Bean
////    public ThymeleafViewResolver viewResolver() {
////        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
////        viewResolver.setTemplateEngine(templateEngine());
////        viewResolver.setCharacterEncoding("UTF-8");
////        viewResolver.setOrder(1);
////        return viewResolver;
////    }
//
//    @Override
//    public void configureViewResolvers(ViewResolverRegistry registry) {
//        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
//        viewResolver.setTemplateEngine(templateEngine());
//        viewResolver.setCharacterEncoding("UTF-8");
//        registry.viewResolver(viewResolver);
//    }
//
//    // ***************************************
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
//    public Properties hibernateProperties() {
//        Properties properties = new Properties();
//        properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
//        properties.put("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
//        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
////        properties.put("hibernate.current_session_context_class", env.getProperty("hibernate.current_session_context_class"));
//        return properties;
//    }
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean sessionFactory() {
//        LocalContainerEntityManagerFactoryBean factory
//                = new LocalContainerEntityManagerFactoryBean();
//        factory.setDataSource(dataSource());
//        factory.setPackagesToScan("com.bka.entity");
//
//        final HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
//
//        factory.setJpaVendorAdapter(adapter);
//        factory.setJpaProperties(hibernateProperties());
//        return factory;
//    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager() {
//        JpaTransactionManager txManager = new JpaTransactionManager();
//        txManager.setEntityManagerFactory(sessionFactory().getObject());
//        return txManager;
//    }
//
//    @Bean
//    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
//        return new JdbcTemplate(dataSource);
//    }
//
//}
