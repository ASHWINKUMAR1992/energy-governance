/****************************************************************
 * Copyright (C) Lowe's Companies, Inc. All rights reserved. This file is for internal use only at
 * Lowe's Companies, Inc.
 ****************************************************************/

/**
 * @author dev
 *
 */
package com.ashwin.energygovernance.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * This class contains the configuration of the database
 * 
 * @author agrover
 *
 */
@Configuration
public class DBConfig {


  /**
   * 
   * @return New instance of DataSource
   */
  @Bean
  @ConditionalOnMissingBean
  public HikariDataSource getDatasource(@Value("${dbusername}") final String username,
      @Value("${dbpassword}") final String password,
      @Value("jdbc:postgresql://${dburl}:${dbport}/${dbschema}") final String dburl,
      @Value("${dbdriverclass}") final String driverClass) {
    HikariConfig hikariConfig = new HikariConfig();
    // Config values are read from vault.
    hikariConfig.setUsername(username);
    hikariConfig.setPassword(password);
    hikariConfig.setJdbcUrl(dburl);
    // Explicitly set the driver class name for Hikari to load the class.
    hikariConfig.setDriverClassName(driverClass);
    // Return new instance of Hikari Connection.
    return new HikariDataSource(hikariConfig);

  }

}
