package com.bookspring.persistence;

import com.bookspring.domain.MemberVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class MemberDAOImpl implements MemberDAO{

    @Autowired
    private SqlSession sqlSession;

    private static final String namespace ="com.bookspring.mapper.MemberMapper";




    @Override
    public String getTime() {
        return sqlSession.selectOne(namespace+".getTime");
    }

    @Override
    public void insertMember(MemberVO vo) {
        sqlSession.insert(namespace+".insertMember",vo);
    }

    @Override
    public MemberVO readMember(String userid) throws Exception {
        return sqlSession.selectOne(namespace+".readMember",userid);
    }

    @Override
    public MemberVO readWithPW(String userid, String userpw) throws Exception {
        Map<String,Object> paramMap = new HashMap<String, Object>();

        paramMap.put("userid",userid);
        paramMap.put("userpw",userpw);
        return sqlSession.selectOne(namespace+".readWithPW",paramMap);
    }

}
