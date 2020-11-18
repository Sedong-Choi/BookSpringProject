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

    final
    ApplicationContext applicationContext;

    public ContextSqlMapper(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean  //xml 에서 ref="DataSource"
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws IOException{

        //DataSource를 받아와 sqlsessionFactory에 적용.
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);

        // p.132에 대응하는 것
        //스프링이 동작할 때 mybatis-config.xml이 같이 동작하도록 설정
        factoryBean.setConfigLocation(applicationContext.getResource("classpath:/mybatis-config.xml"));

        //*.xml 파일을 사용하여 mapping하기 위한 코드
        factoryBean.setMapperLocations(applicationContext.getResource("classpath:/mappers/**/*Mapper.xml"));
//            <bean id="sqlSessionFactory
//                class="org.mybatis.spring.SqlSessionFactoryBean">
//
//                <property name="dataSource" ref='dataSource" />
//                <property name="configLocation"
//                    value="classpath:/mybatis-config.xml"/>
//                <property name="mapperLocation"
//                    value="classpath:mappers/**/*Mapper.xml"/>
//            </bean>


        return factoryBean;
    }

    @Bean // p.134 대응
    public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory){
        /*
            <bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate"
                destroy-method="clearCash">
                <constructor-arg name="sqlSessionFactory"
                    ref="sqlSessionFactory"/>
            </bean>
        */
        return new SqlSessionTemplate(sqlSessionFactory);
    }


}
