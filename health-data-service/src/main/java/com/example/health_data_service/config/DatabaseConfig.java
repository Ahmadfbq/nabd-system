package com.example.health_data_service.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(
    basePackages = "com.example.health_data_service.command",
    entityManagerFactoryRef = "commandEntityManager",
    transactionManagerRef = "commandTransactionManager"
)
public class DatabaseConfig {

    @Primary
    @Bean(name = "commandDataSource")
    public DataSource commandDataSource() {
        return DataSourceBuilder.create()
            .url("jdbc:postgresql://localhost:5332/postgres")
            .username("IotUser")
            .password("password")
            .driverClassName("org.postgresql.Driver")
            .build();
    }

    @Bean(name = "queryDataSource")
    public DataSource queryDataSource() {
        return DataSourceBuilder.create()
            .url("jdbc:postgresql://localhost:5332/postgres")
            .username("IotUser")
            .password("password")
            .driverClassName("org.postgresql.Driver")
            .build();
    }

    @Primary
    @Bean(name = "commandEntityManager")
    public LocalContainerEntityManagerFactoryBean commandEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(commandDataSource());
        em.setPackagesToScan("com.example.health_data_service.command.model");
        
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.put("hibernate.hbm2ddl.auto", "update");
        em.setJpaPropertyMap(properties);
        
        return em;
    }

    @Bean(name = "queryEntityManager")
    public LocalContainerEntityManagerFactoryBean queryEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(queryDataSource());
        em.setPackagesToScan("com.example.health_data_service.query.model");
        
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.put("hibernate.hbm2ddl.auto", "update");
        em.setJpaPropertyMap(properties);
        
        return em;
    }

    @Primary
    @Bean(name = "commandTransactionManager")
    public PlatformTransactionManager commandTransactionManager(
            @Qualifier("commandEntityManager") LocalContainerEntityManagerFactoryBean commandEntityManager) {
        return new JpaTransactionManager(commandEntityManager.getObject());
    }

    @Bean(name = "queryTransactionManager")
    public PlatformTransactionManager queryTransactionManager(
            @Qualifier("queryEntityManager") LocalContainerEntityManagerFactoryBean queryEntityManager) {
        return new JpaTransactionManager(queryEntityManager.getObject());
    }
} 