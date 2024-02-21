package com.example.assignment5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DBInit {

    private static final Logger log = LoggerFactory.getLogger(DBInit.class);

    @Bean
    public CommandLineRunner initialiseDatabase(JdbcTemplate jdbcTemplate) {
        log.info("Initialise Database...");

        return args -> {
            log.info("Creating tasks table");
            jdbcTemplate.execute(
                    "CREATE TABLE IF NOT EXISTS Tasks(taskID BIGINT auto_increment PRIMARY KEY, taskName VARCHAR(50), priority INT, completed BOOL)"
            );
        };
    }
}
