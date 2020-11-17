package com.bookspring.config;


import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:/database.properties") // classpath == src/main/resources/
public class ContextDataSource {
        //MySql과의 연결을 담당하는 DataSource 설정
        @Value("${jdbc.driver}")
        private String driver;
        @Value("${jdbc.url}")
        private String url;
        @Value("${jdbc.username}")
        private String username;
        @Value("${jdbc.password}")
        private String password;

        @Bean  //현재 method를 bean으로 등록한다
        public DataSource dataSource(){
            BasicDataSource basicDataSource= new BasicDataSource();
            basicDataSource.setDriverClassName(driver);
            basicDataSource.setUrl(url);
            basicDataSource.setUsername(username);
            basicDataSource.setPassword(password);
            return basicDataSource;
        }

        @Bean
        public DataSourceTransactionManager transactionManager(){
            DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
            dataSourceTransactionManager.setDataSource(dataSource());
            return dataSourceTransactionManager;
        }

    }

