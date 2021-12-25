package com.project.mapper;

import java.util.HashMap;
import java.util.List;

import com.project.domain.AnswerCountVO;
import com.project.domain.AnswerVO;

public interface AnswerMapper {

	public void insertAnswer(AnswerVO answer);

	// public void selectAnswer(AnswerVO answer);

	public List<AnswerVO> selectAnswer(HashMap<String, Integer> map);

	public AnswerVO updateAnswerPage(int answer_num);

	// public AnswerVO updateAnswer(HashMap<String, Integer> map);

	public void updateAnswer(AnswerVO answer);

	public void updateDelete(AnswerVO answer);

	public void publicAnswer(AnswerVO answer);

	public int trashUpdate(AnswerVO answer);

	public List<AnswerVO> readTrash(int member_num);

	// public String count(AnswerCountVO answercount);
	
	public Object count(AnswerCountVO answercount);

	public void setCount(AnswerCountVO answercount);

	public int updateCountUp(AnswerCountVO answercount);

	public int updateCountDown(AnswerCountVO answercount);

	public void deleteAnswer(AnswerVO answer);

	public int countSelect(AnswerCountVO answercount);

	public List<String> readRandomAnswer(int question_num);

	public int deleteAnswerInt(String delete_date);

	public void deleteDateCount(int member_num);
	
	// public List<AnswerVO> deleteDateCount(HashMap<String, Object> map);

	// public void test(AnswerVO answer);

	// public void deleteAnswer(int answer_num);

}