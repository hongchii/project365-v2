package com.project.service;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.mapper.MemberMapper;

@Service("memberService")
public class MemberServiceImpl implements MemberService {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insertMember(HashMap<String, Object> userInfo) throws Exception {

		MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
		memberMapper.insertMember(userInfo);
		return 0;
	}

	@Override
	public int getMember(Object id) throws Exception {

		MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
		int res = memberMapper.getMember(id);

		return res;
	}

	@Override
	public int getMemberNum(Object id) throws Exception {

		MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
		int res = memberMapper.getMemberNum(id);

		return res;
	}

}
