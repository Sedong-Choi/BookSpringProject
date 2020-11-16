package com.sedong.bookspring.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

/*
    RootContext는 root-context.xml을 대신하는 클래스이다
*/
@Configuration  //현재 class를 config class임을 spring에 알려준다
public class RootContext { //초기에는 설정 할 필요가 없다. 프로젝트 진행야 따라 빈을 등록하면 된다.
    //책에서는 STS를 이용함으로 root-context.xml에 namespaces 를 추가 하여 aop, beans ...을 추가했다
    //현재 나는 java로 구현을 하기 때문에 bean, aop, jdbc 등을 직접 등록해야 한다.

    //MySql과의 연결을 담당하는 DataSource 설정
    @Configuration
    @PropertySource("classpath:/database.properties") // classpath == src/main/resources/
    public class DataBaseConfig{
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
        public PlatformTransactionManager transactionManager(){
            DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
            dataSourceTransactionManager.setDataSource(dataSource());
            return dataSourceTransactionManager;
        }

    }


}
