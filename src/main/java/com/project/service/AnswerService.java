package com.project.service;

import java.util.List;

import com.project.domain.AnswerCountVO;
import com.project.domain.AnswerVO;

public interface AnswerService {

	//creat=============
		public int insertAnswer(AnswerVO answer) throws Exception;//답변 작성
		
		
		
		//read================
		public List<AnswerVO> readAnswer(int question_num, int member_num) throws Exception;
		//일기장전체조회
		public AnswerVO updateAnswerPage(int answer_num); //수정페이지 불러오기
		public int trashUpdate(AnswerVO answer);//휴지통에서 복구-> 일기장으로
		public List<AnswerVO> readTrash(int member_num);//휴지통전체조회

		public List<String> readRandomAnswer(int question_num);//메인페이지 : 랜덤 답변 8개 보여주기

		
		
		//update==============
		public void updateAnswer(AnswerVO answer); //답변수정하기
		public int updateDelete(AnswerVO answer); //답변 삭제-> 휴지통으로
		public int publicAnswer(AnswerVO answer); //공개여부 수정


		//delete=============
		public int deleteAnswer(AnswerVO answer); //진짜 삭제
		public void deleteDateCount(int member_num);//휴지통에 담은지 7일이경과된것은 영구삭제.
		
		
		
		//answer_count에 필요한 것들===============
		public int countSelect(AnswerCountVO answercount); //달력에 뿌리기위해 답변 갯수 조회
		
		public int count(AnswerCountVO answercount); //하나라도 답변이 존재하는지 판단
		public int setCount(AnswerCountVO answercount);//컬럼이 없는 q.n에 해당하는 컬럼을 생성
		public int updateCountUp(AnswerCountVO answercount);//등록, 복구시 count+1
		public int updateCountDown(AnswerCountVO answercount);//삭제시 count-1
		
		public int countRead(int month);//한달 답변불러오기
		
		
		/////
		//public List<AnswerVO> readTrash(int member_num, int delete_date);
		//public int deleteAnswer(int answer_num);
		//public int count(int question_num, int member_num);
		//public List ReadAnswer(int question_num) throws Exception;
		//public AnswerVO UpdateAnswer(int question_num);
		//public AnswerVO UpdateAnswer(int answer_num, int member_num);
		//public int deleteAnswerInt(int answer); //삭제테스트
		//public Integer deleteDateCount(String delete_date);
		public void test(AnswerVO answer);
 

}