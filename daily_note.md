#2020-11-13
>WebConfig.class 생성 implements WebApplicationInitializer
>>@Override onStartup();
>>  onStartup() { DispatcherServlet, Root Context Config, Filter 설정 }
>
> WebContext.class 생성 implements WebMvcConfigurer
>> @Configuration ,@EnableWebMvc ,@ComponentScan("packageName") annotation 설정
>> @Override configureViewResolvers(), addResourceHandlers()
> 
> Bean 등록을 위한 RootContext.class 생성
>
> HelloController 및 hello.jsp 생성
>
> Tomcat을 이용하여 작동하는지 확인(after run : 404 error)
>> shutdown port -1 로 되어있어 tomcat 내부 conf/server.xml 의 shutdown port 8005로 변경
>> IntelliJ Tomcat Server URL : 'http://localhost:8080/' , Application context : '/' 설정 변경
> 
>index.jsp 및 hello.jsp 확인 가능

---
#2020-11-16
>Install mysql
>
>Modify pom.xml
>> Add mysql dependency
>
>jUnit Test
>> Failed! jdbc not found!
>>
>> Download from 'dev.mysql.com/downloads/connector/j'
>> 
>> Add Libraries 'mysql-connector-java-5.1.49.jar' 
>>
>> Success!
>
>Set MyBatis & Another
>>  mybatis v3.5.6
>>  mybatis-spring v2.0.6
>>  spring-jdbc v5.2.6
>>  spring-test v5.2.6
>
>Add RootContext config
>> create sr/main/resources/database.properties setting
>>
>> BasicDataSource 못불러옴!! 
>>> -  maven org.apache.commons:commons-dbcp2 등록!  해결!!
>>>
>>
>> dataSource()와 transactionManager() Bean으로 등록
>>
>>  