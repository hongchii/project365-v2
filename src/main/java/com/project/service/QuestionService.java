package com.project.service;

import com.project.domain.QuestionVO;

public interface QuestionService {

	public QuestionVO getQuestion(int question_num) throws Exception;
}