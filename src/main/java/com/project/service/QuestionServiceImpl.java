package com.project.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.domain.QuestionVO;
import com.project.mapper.QuestionMapper;

@Service("questionService")
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public QuestionVO getQuestion(int question_num) throws Exception {

		QuestionMapper questionMapper = sqlSession.getMapper(QuestionMapper.class);
		QuestionVO question = questionMapper.getQuestion(question_num);

		return question;
	}

}