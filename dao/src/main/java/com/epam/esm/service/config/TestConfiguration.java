package com.epam.esm.service.config;

import org.eclipse.jdt.internal.compiler.env.ISourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;


@Configuration
@ComponentScan(basePackages = "com.epam.esm")
@PropertySource("classpath:dbconfig.properties")
@Profile("dev")
public class TestConfiguration {
    public static final String DRIVER_CLASS_NAME = "h2.driver";
    public static final String DB_URL = "h2.url";
    public static final String USER_NAME = "h2.user";
    public static final String PASSWORD = "h2.password";

    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(environment.getProperty(DRIVER_CLASS_NAME));
        driverManagerDataSource.setUrl(environment.getProperty(DB_URL));
        driverManagerDataSource.setUsername(environment.getProperty(USER_NAME));
        driverManagerDataSource.setPassword(environment.getProperty(PASSWORD));
        return driverManagerDataSource;
    }

        @Bean
        public JdbcTemplate jdbcTemplate(DataSource embeddedDatabase) {
            return new JdbcTemplate(embeddedDatabase);
        }
    }

