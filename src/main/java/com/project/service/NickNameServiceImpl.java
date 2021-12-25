package com.project.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.domain.NickNameVO;
import com.project.mapper.NickNameMapper;

@Service("NickNameService")
public class NickNameServiceImpl implements NickNameService {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<NickNameVO> getAdj() throws Exception {
		System.out.println("serviceImpl");
		NickNameMapper nickMapper = sqlSession.getMapper(NickNameMapper.class);
		List<NickNameVO> nickAdj = nickMapper.getAdj();
		return nickAdj;
	}

	@Override
	public List<NickNameVO> getNoun() throws Exception {
		NickNameMapper nickMapper = sqlSession.getMapper(NickNameMapper.class);
		List<NickNameVO> nickNoun = nickMapper.getNoun();
		return nickNoun;
	}

}