package com.bookspring.mapper;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface MemberMapper {

//    @Insert(" insert into tbl_member (userid,userpw,username, email) " +
//            "values (#{userid},#{userpw}, #{username}, #{email})")
//    public void insertMember();

    @Select("select now()")
    public String getTime();
}
