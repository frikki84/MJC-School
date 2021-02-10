package com.epam.esm.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.epam.esm")
@PropertySource("classpath:dbconfig.properties")
public class DbConfiguration {
    public static final String DRIVER_CLASS_NAME = "db.driver";
    public static final String DB_URL = "db.url";
    public static final String USER_NAME = "db.user";
    public static final String PASSWORD = "db.password";

    @Autowired
    private Environment environment;


    @Bean
    @Profile("prod")
    public DataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(environment.getProperty(DRIVER_CLASS_NAME));
        driverManagerDataSource.setUrl(environment.getProperty(DB_URL));
        driverManagerDataSource.setUsername(environment.getProperty(USER_NAME));
        driverManagerDataSource.setPassword(environment.getProperty(PASSWORD));
        return driverManagerDataSource;
    }

    @Bean
    public JdbcTemplate template(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate;
    }

    @Bean
    public PlatformTransactionManager txManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }


}
