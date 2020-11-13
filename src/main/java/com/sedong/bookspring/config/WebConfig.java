package com.sedong.bookspring.config;


import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

//java class 로 설정을 할때 WebConfig 는 web.xml 역할을 한다.
public class WebConfig implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // DispatcherServlet 설정 -s
        // 1. DispatcherServlet WebApplicationContext 객체 생성
        AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
        webContext.register(WebContext.class);

        // 2. DispatcherServlet 객체 생성 및 추가
        DispatcherServlet dispatcherServlet = new DispatcherServlet(webContext);
        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", dispatcherServlet);

        // 3. 서블릿 매핑 및 부가 설정
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);
        // DispatcherServlet 설정 -e

        /* DispatcherServlet 설정 web.xml version
        <servlet>
          <servlet-name>appServlet</servlet-name>
          <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
          <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>/WEB-INF/config/webmvc-context.xml</param-value>
          </init-param>
          <load-on-startup>1</load-on-startup>
        </servlet>
        <servlet-mapping>
          <servlet-name>appServlet</servlet-name>
          <url-pattern>/</url-pattern>
        </servlet-mapping>

         */

        // Root Context Config 설정 -s
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(RootContext.class);

        ContextLoaderListener listener = new ContextLoaderListener(rootContext);
        servletContext.addListener(listener);
        // Root Context Config 설정 -e

        /* Root Context Config 설정 web.xml version
        <context-param>
          <param-name>contextConfigLocation</param-name>
          <param-value>/WEB-INF/config/root-context.xml</param-value>
        </context-param>

        <listener>
          <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
        </listener>

        */

        // Filter 설정 -s
        FilterRegistration.Dynamic filter = servletContext.addFilter("encodingFilter", CharacterEncodingFilter.class);
        filter.setInitParameter("encoding", "UTF-8");
        filter.addMappingForServletNames(null, false, "dispatcher");
        // Filter 설정 -e


        /* Filter 설정 web.xml version
        <filter>
          <filter-name>encodingFilter</filter-name>
          <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
          <init-param>
            <param-name>encoding</param-name>
            <param-value>UTF-8</param-value>
          </init-param>
          <init-param>
            <param-name>forceEncoding</param-name>
            <param-value>true</param-value>
          </init-param>
        </filter>

        <filter-mapping>
          <filter-name>encodingFilter</filter-name>
          <url-pattern>/*</url-pattern>
        </filter-mapping>

        */

    }
}
