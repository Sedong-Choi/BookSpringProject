package com.bookspring.mapper;


import com.bookspring.domain.MemberVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

// memberMapper.xml에 입력하듯 이쪽에 적어야 한다.
public interface MemberMapper {


    @Select("select now()")
    public String getTime();

    @Insert(" insert into tbl_member (userid,userpw,username, email) " +
            "values (#{userid},#{userpw}, #{username}, #{email})")
    public void insertMember();

    @Select("select * from tbl_member" +
            " where userid =#{userid}")
    public MemberVO readMember();

    @Select("select * from tbl_member" +
            " where userid=#{userid} and userpw=#{userpw}")
    public MemberVO readWithPW();


}
