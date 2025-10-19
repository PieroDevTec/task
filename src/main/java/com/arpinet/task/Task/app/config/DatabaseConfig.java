package com.arpinet.task.Task.app.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Bean(name = "desaDataSource")
    @ConfigurationProperties(prefix = "desa.datasource")
    public DataSource desaDataSource(){
        return DataSourceBuilder.create().build();
    }
    @Bean(name = "prodDataSource")
    @Primary
    @ConfigurationProperties(prefix = "prod.datasource")
    public DataSource prodDataSource(){
        return DataSourceBuilder.create().build();
    }
    @Bean(name = "desaJdbcTemplate")
    public JdbcTemplate desaJdbcTemplate(@Qualifier("desaDataSource") DataSource datasource){
        return  new JdbcTemplate(datasource);
    }
    @Bean(name = "prodJdbcTemplate")
    public JdbcTemplate prodJdbcTemplate(@Qualifier("prodDataSource") DataSource dataSource){
        return  new JdbcTemplate(dataSource);
    }

}
