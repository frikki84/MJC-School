package com.epam.esm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;


@Configuration
@ComponentScan(basePackages = "com.epam.esm")
@Profile("dev")
public class TestConfiguration {
        public static final String DB_SCRIPT = "classpath:mydb.sql";

        @Bean
        public EmbeddedDatabase dataSource() {
            return new EmbeddedDatabaseBuilder()
                    .addScript(DB_SCRIPT)
                    .setType(EmbeddedDatabaseType.H2)
                    .build();
        }

        @Bean
        public JdbcTemplate jdbcTemplate(EmbeddedDatabase embeddedDatabase) {
            return new JdbcTemplate(embeddedDatabase);
        }
    }

