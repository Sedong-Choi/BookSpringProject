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
>> create src/main/resources/database.properties setting
>>
>> BasicDataSource 못불러옴!! 
>>> -  maven org.apache.commons:commons-dbcp2 등록!  해결!!
>>>
>>
>> dataSource()와 transactionManager() Bean으로 등록
>>
>> DataSourceTest.class @Inject 없음!
>>> - maven javax.inject 등록 (but @Inject 과 @Autowire 차이가 없다 함)
>> - @Inject 이 문제가 아니었다. RootContext.class가 잘못되었다.
>> config package 에 ContextDataSource.class 를 생생 후 RootContext에 @Import를 사용하니
>> DataSource 객체에 주입되었다.
>>
>> - DataSourceTest에서 @ContextConfiguration(classes={})에서 불필요한 WebContext.class를 써서
>> error가 생겼었다. 제거하면 정상 작동한다.

---------
#2020-11-17
>To connect MyBatis
>> Create ContextSqlMapper.class >> RootContest(@Import) + ContextSqlMapper 추가
>>
>> MyBatisTest 추가(@Configration 에는 WebConfig, RootContext 만 사용 다른 class 사용시 오류발생)
>>
>> 기본적인 MVC 패턴 세팅 끝난듯합니다. 이제부터 controller, service 등을 만들예정입니다.
>
> Create Controller, set slf4j,log4j maven
>> result.jsp 에서 EL 적용이 안됨 (**해결못함**) 
>>
>> jstl, el maven 설치 후 아직 안됨 
>> - 해결방법!! <%@ page contentType="text/html;charset=UTF-8" language="java"%>에서
>> laguage 부분을 제거 하니 잘 작동한다.
>>
>> 왜 그럴까? IntelliJ에서 problems에 "redundant default attribute value assignment"
>> 어딘가에서 중복으로 기본값을 java로 설정해놓은거 같다.(나중에 찾아봐야지)
>
> package 변경 후(../com/sedong/bookspring => ../com/bookspring ) tomcat 실행이 안됨...
>>  build 완료 후 war exploded 할때 error 발생
>> - org.apache.tomcat.util.modeler.BaseModelMBean.invoke 메소드 [manageApp]을(를) 호출하는 중 예외 발생
>> - servlet.api maven 다시 불러옴 ( 해결X )
>> - build java version 과 project java version sync ( 해결X )
>>
#2020-11-18
  
>>문제를 찾은 것 같다!(2020-11-18)
>>
>> **ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", dispatcherServlet);**
>>
>> addServlet에서 이미 dispatcher란 key 값을 가지고 있기 때문에 안된듯 하다.
>>(docs를 보면 값이 있으면 null을 반환한다.)
>> 
>> servletContext.addServlet("dispatcher",dispatcherServlet) 이 저장되는 곳을 찾아야 할듯하다.
>>
>> dispatcherServlet 으로 바꾸니 이 오류를 넘어갔다.(servletContext가 정의되어 있는 파일을 삭제 후 실행하면 될듯하다.)
>> 
>> **filter.setInitParameter("encoding", "UTF-8");**
>> 이제 이부분이 문제다.
>
> **해결!!** 결론부터 말하면 프로젝트 package경로 수정 후 target에 예전 경로가 남아있어서 안됐다.
> project 경로에서 남아있던 package를 삭제해 주면 위의 key값을 바꾸지 않아도 된다!!
>> 하지만 controller가 작동 안한다.. (이것도 package 경로 문제였다.)
>
> 
> RedirectAttributes => SampleController4