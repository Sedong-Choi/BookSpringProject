package com.bookspring.config;

import org.springframework.context.annotation.*;

/*
    RootContext는 root-context.xml을 대신하는 클래스이다
*/
@Configuration//현재 class를 config class임을 spring에 알려준다
@Import({
        ContextDataSource.class,
        ContextSqlMapper.class
})
//@ComponentScan(basePackages = {"com.sedong.book"})
public class RootContext { //초기에는 설정 할 필요가 없다. 프로젝트 진행야 따라 빈을 등록하면 된다.
    //책에서는 STS를 이용함으로 root-context.xml에 namespaces 를 추가 하여 aop, beans ...을 추가했다
    //현재 나는 java로 구현을 하기 때문에 bean, aop, jdbc 등을 직접 등록해야 한다.
    //config package에 각각 클래스를 만들고 현재 class(RootContext)에서 @Import 를해야 정상 작동한다.



}
