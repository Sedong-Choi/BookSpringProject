# TEST PROJECT START NOVE 12th
---------------
> '코드로 배우는 스프링 웹프로젝트' 책을 보며 작성하였습니다.
>> 설정파일은 xml 대신 java를 이용해서 구현해 보았습니다.(모두 java는 아님)
---------------
## 프로젝트를 하는 목적
> 책에 있는 예제를 만들면서 SPRING PROJECT를 다시 복습하고
> 개인 프로젝트를 같이 진행할 예정입니다.
>> 부족한 점이 있지만 계속 보완해 나갈 예정입니다.


##log
> 처음 Intellij 로 spring MVC 프로젝트 생성 및 초기설정 참조 (2020.11.12~13)
>> 출처 https://atoz-develop.tistory.com/entry/IntelliJ-Spring-Web-MVC-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-%EC%83%9D%EC%84%B1-%EB%B0%8F-%EC%84%B8%ED%8C%85-%EC%9E%90%EB%B0%94-%EC%84%A4%EC%A0%95-%EC%82%AC%EC%9A%A9

#Project
>java version 1.8
>tomcat version 9.0.39

### daily detail
>2020-11-13
>> web.xml 을 대신한 class 파일 설정 및 오류 해결
>
>2020-11-16
>> connect mysql DB 
>> 
>> jUnit test
>>
>> Add MyBatis
>>
>> Set WebConfig(web.xml), WebContext(servlet-context.xml), RootContext(root-context.xml)
>> 
>> ContextDataSource, Rootcontext(@Import 사용)
>
>2020-11-17
>> 
>> MyBatis 사용을 위한 ContextSqlMapper.class 생성 및 Test
>
>2020-11-18
>
>> package 변경 후 오류 수정
>> 
>> 기본적인 Controller, jsp test 
>> 
>> mybatis + spring 중 오류 발생(DAO, *Mapper.xml 생성후 test중 발생)
>>
>> MemberDAOTest - mybatis를 이용한 data insert 오류 해결 

> 2020-11-19
>>
>> log4jdbc 및 기본적인 설정 완료! 
>> MemberDAO, MemberDAOImpl 로 database에 접근하기 까지 완료.
>> IntelliJ 에서 spring 프로젝트 기본 설정 하는법을 끝냈다.
>> 
-----------
# 후기
> IntelliJ 에 익숙하지 않고, Spring 프로젝트를 java config 로 하는법도 처음이라
> 정보를 찾고 오류 수정하고 머리속에서 정리하는게 생각했던 시간보다 오래걸렸다.
> 바로 복습을 해서 정확하게 내것으로 만들어야겠다. 


