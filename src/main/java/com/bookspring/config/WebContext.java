package com.bookspring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
     WebContext는  servlet-context.xml 을 대신하는 클래스이다.
*/
/* .xml version
<!-- @Controller 애노테이션 사용 -->
<annotation-driven/>

<!-- Component Scan 패키지 설정 -->
<context:component-scan base-package="com.sedong.bookspring.controller"/>
*/
@Configuration // 설정 파일 명시하는 annotation
@EnableWebMvc // WebMvc 패턴을 사용을  명시하는 annotation
@ComponentScan("com.bookspring.controller") // () 안에 있는 패키지 스캔을 명시하는 annotation
public class WebContext implements WebMvcConfigurer {

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry){
        WebMvcConfigurer.super.configureViewResolvers(registry);
        //ViewResolver 설정
        registry.jsp("/WEB-INF/views/",".jsp");

        /* .xml version
        <!-- 뷰 prefix, suffix 설정 -->
        <beans:bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
            <beans:property name="prefix" value="/WEB-INF/views/"/>
            <beans:property name="suffix" value=".jsp"/>
        </beans:bean>
        */
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);
        // Static Resources 설정
        registry.addResourceHandler("/**").addResourceLocations("/resources/");

        /* .xml version
        <!-- 정적 리소스 경로 설정 -->
        <resources mapping="/**" location="/resources/"/>
        */
    }
}


