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
--------------
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
>
> JSON => @ResponseBody view level 로 직접 데이터를 보낸다.
>> 브라우저 개발자페이지(F12)를 눌러 NetWork / Response Headers 부분을 보면 Content-Type : application/json
>> 임을 확인 할 수 있다.
>
> was없이 컨트롤러 테스트하기
>> servlet-api version은 3.1이상 사용할것 (현재 4.0.1)
>>
>> test중 pageNotFound - No mapping for GET {url} error발생 .get .post 둘다 안됨
>
>> Controller을 annotation확인( 있음 ) @RequestMapping("~") ( 있음 )
>> 실제 실행 후 확인하면 jsp 페이지 열리지만 test에서는 안된다고 뜸
>> url-mapping 문제라는데 이상이 없다.(나중에 해결하자 ㅠ)
>> 
> **mybatis를 이용해보자!**
>> xml 사용시
>> - 장점 : xml만 수정하면 되어서 유지 보수가 좋다
>> - 단점 : 코드의 양이 많아지고, 복잡성 증가
>> 
>> annotation과 interface 사용시
>> - 장점 : 별도의 DAO 없이도 개발이 가능하기 때문에 생산성 증가
>> - 단점 : SQL문을 애노테이션으로 작성하므로, 수정시 다시 컴파일 해야함
>>
>> 같이 사용 
>> - 장점 : 간단한 SQL은 annotation, 복잡한 SQL문은 xml으로 유연하게 만들 수 있음
>> - 단점 : 개발자간 스타일 차이로 유지보수가 어려워질 수 있음.
>
> **Table 생성**, **DAO 생성**, **xml mapper 생성**
> - Create Table 
>> resources/DB/schema.sql => query 실행 
>> 
> - DAO 생성
>> java/com/bookspring/persistence/MemeberDAO , MemberDAOImpl
> - xml mapper 생성
>> resources/mappers/*.xml 
> 
> **MemberDAO Test**
>> - **ERROR** config를 java로 했더니 엄청난 error가 발생한다 ㅠㅠ
>> - org.springframework.context.support.GenericApplicationContext - Exception encountered during context initialization - cancelling refresh attempt: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'memberDAOImpl': Unsatisfied dependency expressed through field 'sqlSession'; nested exception is org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'sqlSession' defined in com.bookspring.config.ContextSqlMapper: Unsatisfied dependency expressed through method 'sqlSession' parameter 0; nested exception is org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'sqlSessionFactory' defined in com.bookspring.config.ContextSqlMapper: Invocation of init method failed; nested exception is org.apache.ibatis.builder.BuilderException: Error creating document instance.  Cause: org.xml.sax.SAXParseException; lineNumber: 3; columnNumber: 131; 문서 루트 요소 "beans"은(는) DOCTYPE 루트 "null"과(와) 일치해야 합니다.
>> - 해결!!
>
> **해결방법**
>> 나는 계속해서 java로 config 파일을 만들어왔다.
>> 1. mybatis-config.xml과 *Mapper.xml을 만들 필요가 없었다.
>> 1. mapper package를 만들어 mapper interface를 정의한다.
>> 1. ContextSqlMapper.java  에서 factorybean.setconfigLocation과 .setMapperLocation을 할 필요가 없었다.
>> 1. MemberDAO interface를 만들고 MemberDAOImpl에서 @Autowire sqlSession을 해주고 namespace설정을 한 후 
>>  sqlSession.select() 처럼 사용하면된다.
>
>> - 참조
>> - http://mybatis.org/spring/ko/mappers.html
>> - https://sora-muck.tistory.com/41
>

----------------
#2020-11-19
> log4jdbc 설정!
>> jdbc dirver 경로를 변경한다.(database.properties)
>>> - jdbc.driver=com.mysql.jdbc.Driver => jdbc.driver=net.sf.log4jdbc.sql.jdbcapi.DriverSpy
>>> - jdbc.url=jdbc:mysql://127.0.0.1:3306/book_ex => jdbc.url=jdbc:log4jdbc:mysql://127.0.0.1:3306/book_ex
>> resources/log4jdbc.log4j2.properties, logback.xml 생성

> - 이번에는 바로 성공!