package com.bookspring.config;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import javax.sql.DataSource;
import java.io.IOException;

@Configuration
//@MapperScan을 사용하기 위해서는 *mapper.xml 을 사용하는 것이 아닌 *Mapper.java 를 만들어야 하는듯 하다.
//@MapperScan basePackages 는 딱 mapper.java 가 있는곳 까지만 해주면 된다.
// 직관적 이름이었는데 왜 com.boospring.mapper.*Mapper.java라고 했을까
@MapperScan(basePackages ={"com.bookspring.mapper"})
public class ContextSqlMapper {

    @Autowired
    ApplicationContext applicationContext;

    @Bean  //xml 에서 ref="DataSource"
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws IOException{

        //DataSource를 받아와 sqlsessionFactory에 적용.
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);

        // p.132에 대응하는 것
        //스프링이 동작할 때 mybatis-config.xml이 같이 동작하도록 설정
        //mybatis-spring 공식문서를 보면 현재 class가 mybatis-config.xml 을 대체한다고 얘기 하는듯 하다.
        // http://mybatis.org/spring/ko/getting-started.html 참조
        // 따라서 현재 프로젝트에서는 .setConfigLocation과 .setMapperLocations를 사용 안하고 @MapperScan만 사용해야 할듯 하다.
        // 오류에서도 @MapperScan을 사용하지 않으면
//        factoryBean.setConfigLocation(applicationContext.getResource("classpath:/mybatis-config.xml"));

        //*.xml 파일을 사용하여 mapping하기 위한 코드
//        factoryBean.setMapperLocations(applicationContext.getResource("classpath:/mappers/**/*Mapper.xml"));
//        RootContext.xml에 있어야 함
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
        /* RootContext.xml에 있어야 함
            <bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate"
                destroy-method="clearCash">
                <constructor-arg name="sqlSessionFactory"
                    ref="sqlSessionFactory"/>
            </bean>
        */
        return new SqlSessionTemplate(sqlSessionFactory);
    }


}
