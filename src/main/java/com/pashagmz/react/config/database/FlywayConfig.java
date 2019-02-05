package com.pashagmz.react.config.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

/**
 * @author Kadach Alexey
 */
@Configuration
public class FlywayConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public FlywayMigrationStrategy primaryMigrateStrategy() {
        return flyway -> {
            flyway.setDataSource(dataSource);
            flyway.clean();
            flyway.migrate();
        };
    }

}
