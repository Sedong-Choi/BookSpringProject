# TEST PROJECT START NOVE 12th
---------------
> 코드로 배우는 스프링 웹프로젝트 책을 보며 작성하였습니다.
---------------
## 프로젝트를 하는 목적
> 책에 있는 예제를 만들면서 SPRING PROJECT를 다시 복습하고
> 개인 프로젝트를 같이 진행할 예정입니다.
>> 부족한 점이 있지만 계속 보완해 나갈 예정입니다.


##log
> 처음 Intellij 로 spring MVC 프로젝트 생성 및 초기설정 참조 (2020.11.12~13)
>> 출처 https://atoz-develop.tistory.com/entry/IntelliJ-Spring-Web-MVC-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-%EC%83%9D%EC%84%B1-%EB%B0%8F-%EC%84%B8%ED%8C%85-%EC%9E%90%EB%B0%94-%EC%84%A4%EC%A0%95-%EC%82%AC%EC%9A%A9



### daily detail
>2020-11-13
>> web.xml 을 대신한 class 파일 설정 및 오류 해결
>> 1. WebConfig.class 생성 implements WebApplicationInitializer
>> 2. @Override onStartup();
>> 3. onStartup() { DispatcherServlet, Root Context Config, Filter 설정 }
>> 4. WebContext.class 생성 implements WebMvcConfigurer
>> 5. @Configuration ,@EnableWebMvc ,@ComponentScan("packageName") annotation 설정
>> 6. @Override configureViewResolvers(), addResourceHandlers() 
>> 7. Bean 등록을 위한 RootContext.class 생성
>> 8. HelloController 및 hello.jsp 생성
>> 8. Tomcat을 이용하여 작동하는지 확인(after run : 404 error)
>> 9. shutdown port -1 로 되어있어 tomcat 내부 conf/server.xml 의 shutdown port 8005로 변경
>> 10. IntelliJ Tomcat Server URL : 'http://localhost:8080/' , Application context : '/' 설정 변경
>> 11. index.jsp 및 hello.jsp 확인 가능




