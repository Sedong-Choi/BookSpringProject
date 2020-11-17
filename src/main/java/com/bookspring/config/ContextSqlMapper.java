package com.bookspring.config;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import javax.sql.DataSource;
import java.io.IOException;

@Configuration
//@MapperScan(basePackages={"service package경로 입력"})
public class ContextSqlMapper {

    @Autowired
    ApplicationContext applicationContext;

    @Bean  //xml 에서 ref="DataSource"
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws IOException{

        //DataSource를 받아와 sqlsessionFactory에 적용.
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);

        //스프링이 동작할 때 mybatis-config.xml이 같이 동작하도록 설정
//        factoryBean.setConfigLocation(applicationContext.getResource("classpath:/mybatis/mybatis-config.xml"));

        //*.xml 파일을 사용하여 mapping하기 위한 코드
//        factoryBean.setMapperLocations(applicationContext.getResource("classpath:/mybatis/mappers/*.xml"));
        return factoryBean;
    }

    @Bean
    public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }


}
