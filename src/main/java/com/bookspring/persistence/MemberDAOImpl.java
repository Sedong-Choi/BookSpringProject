package com.bookspring.persistence;

import com.bookspring.domain.MemberVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAOImpl implements MemberDAO{

    @Autowired
    private SqlSession sqlSession;

    private static final String namespace ="com.bookspring.mapper.MemberMapper";




    @Override
    public String getTime() {
        return sqlSession.selectOne(namespace+".getTime");
    }

//    @Override
//    public void insertMember(MemberVO vo) {
//        sqlSession.insert(namespace+".insertMember",vo);
//    }
}
