package com.project.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.domain.AnswerCountVO;
import com.project.domain.AnswerVO;
import com.project.mapper.AnswerMapper;

@Service("answerService")

public class AnswerServiceImpl implements AnswerService {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insertAnswer(AnswerVO answer) throws Exception {

		AnswerMapper answerMapper = sqlSession.getMapper(AnswerMapper.class);
		answerMapper.insertAnswer(answer);
		return 1;
	}

	@Override
	public List<AnswerVO> readAnswer(int question_num, int member_num) throws Exception {

		AnswerMapper answerMapper = sqlSession.getMapper(AnswerMapper.class);

		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("question_num", question_num);
		map.put("member_num", member_num);

		System.out.println("question_num" + question_num);
		System.out.println("member_num" + member_num);
		List<AnswerVO> result = answerMapper.selectAnswer(map);
		return result;
	}

	@Override
	public AnswerVO updateAnswerPage(int answer_num) {

		System.out.println("수정 페이지/ service name: UpdateAnswerPage");

		AnswerMapper answerMapper = sqlSession.getMapper(AnswerMapper.class);

		AnswerVO result = answerMapper.updateAnswerPage(answer_num);
		return result;
	}

	@Override
	public void updateAnswer(AnswerVO answer) {

		System.out.println("수정 하기!/ service name: UpdateAnswer");

		AnswerMapper answerMapper = sqlSession.getMapper(AnswerMapper.class);

		answerMapper.updateAnswer(answer);

	}

	@Override
	public int updateDelete(AnswerVO answer) {

		System.out.println("삭제 여부 수정 하기!/ service name: UpdateDelete");

		AnswerMapper answerMapper = sqlSession.getMapper(AnswerMapper.class);

		answerMapper.updateDelete(answer);

		return 1;// 성공

	}

	@Override
	public int publicAnswer(AnswerVO answer) {
		System.out.println("공개 여부 수정 하기!/ service name: publicAnswer");

		AnswerMapper answerMapper = sqlSession.getMapper(AnswerMapper.class);

		answerMapper.publicAnswer(answer);

		return 1; // 성공

	}

	@Override
	public int trashUpdate(AnswerVO answer) {
		System.out.println("답변 복원 하기 !/ service name: TrashPublic");

		AnswerMapper answerMapper = sqlSession.getMapper(AnswerMapper.class);

		answerMapper.trashUpdate(answer);

		return 1;// 성공

	}

	@Override
	public List<AnswerVO> readTrash(int member_num) {
		System.out.println("휴지통조회 시작 / service name: readTrash");
		AnswerMapper answerMapper = sqlSession.getMapper(AnswerMapper.class);

		// int res = answerMapper.deleteAnswerInt(delete_date);
		// if res
		List<AnswerVO> result = answerMapper.readTrash(member_num);
		return result;

	}

	// answer_count테이블에 답변이 존재하는지 확인
	
	@Override
	public int count(AnswerCountVO answercount) {
		System.out.println("답변 있는지확인 !/ service name: count");
		
		AnswerMapper answerMapper = sqlSession.getMapper(AnswerMapper.class);
		//Object result = answerMapper.count(answercount);
		//String result = answerMapper.count(answercount);
		
		//System.out.println("result.isEmpty(): "+result.isEmpty());
		//System.out.println("db에서 확인한 result의 값은 : " + result);
		//if(result.equals(null) || result.equals(0)) {
		
		try {
			Object result = answerMapper.count(answercount);
			System.out.println("db에서 확인한 result의 값은 : " + result);
			System.out.println("up또는 down으로");
			return 0;//up또는 down으로
			
		}
		catch(NullPointerException e) {
			System.out.println("컬럼이 없어요. 새롭게 컬럼을 추가해야 합니다.");
			return 1;//setting으로
		}
		
		
	}

	// 달력에서 뿌려주기위한 답변갯수 조회
	@Override
	public int countSelect(AnswerCountVO answercount) {
		System.out.println("답변 갯수를 확인 !/ service name: countSelect");

		AnswerMapper answerMapper = sqlSession.getMapper(AnswerMapper.class);
		Object result = answerMapper.count(answercount);

		return (int) result;// 성공
	}

	// 최초 답변등록시 answer_count테이블 insert
	@Override
	public int setCount(AnswerCountVO answercount) {
		AnswerMapper answerMapper = sqlSession.getMapper(AnswerMapper.class);
		answerMapper.setCount(answercount);

		return 1;
	}

	// 답변 횟수 업(등록)
	@Override
	public int updateCountUp(AnswerCountVO answercount) {
		System.out.println("답변count를 증가시킵니다!/ service name: updateCount====");
		
		AnswerMapper answerMapper = sqlSession.getMapper(AnswerMapper.class);
		
		int result = answerMapper.updateCountUp(answercount);
		
		if(result==1) { //count +1 성공
			System.out.println("count+1 처리에 성공했습니다 !");
			return 1;
		}
		else {//count +1실패
			System.out.println("count+1 처리에 실패!!!했습니다!!!!");
			return 0;
		}
		
	}

	// 답변횟수 다운 (삭제)
	@Override
	public int updateCountDown(AnswerCountVO answercount) {
		System.out.println("답변count를 감소시킵니다/ service name: updateCountDown====");
		AnswerMapper answerMapper = sqlSession.getMapper(AnswerMapper.class);
		
		int result=answerMapper.updateCountDown(answercount);
		System.out.println("result: "+result);
		if(result==1) { //count -1 성공
			System.out.println("count-1 처리에 성공했습니다 !");
			return 1;
		}
		else {//count -1실패
			System.out.println("count-1 처리에 실패!!!했습니다!!!!");
			return 0;
		}
		//System.out.println("answercount");
		//return 1;// 성공
	}

	
	//휴지통에 있는 답변을 영구삭제하기
	@Override
	public int deleteAnswer(AnswerVO answer) {
		AnswerMapper answerMapper = sqlSession.getMapper(AnswerMapper.class);
		answerMapper.deleteAnswer(answer);
		return 1;// 성공
	}

	// 경과일 확인후 7일지난것 삭제하기
	@Override
	public void deleteDateCount(int member_num) {
		System.out.println("날짜 지난것들 삭제 !/ service name: deleteDateCount");
		AnswerMapper answerMapper = sqlSession.getMapper(AnswerMapper.class);

		System.out.println("member_num" + member_num);

		// System.out.println("delete_date"+delete_date);
		answerMapper.deleteDateCount(member_num);
		// return result;

		// answerMapper.deleteDateCount(member_num,delete_date);
		// return 1;//성공
	}

	@Override
	public List<String> readRandomAnswer(int question_num) {
		AnswerMapper answerMapper = sqlSession.getMapper(AnswerMapper.class);
		List<String> str = answerMapper.readRandomAnswer(question_num);
		System.out.println("답변count !/ service name: readRandomAnswer");
		for (String st : str) {
			System.out.println("st: " + st);
		}

		return str; // 성공
	}

	@Override
	public int countRead(int month) {
		AnswerMapper answerMapper = sqlSession.getMapper(AnswerMapper.class);
		//int count = answerMapper.countRead(month);
		System.out.println("한달 답변 count !/ service name: countRead");
		
		try {
			int count = answerMapper.countRead(month);
			System.out.println("db에서 확인한 답변갯수는 : " + count);
			return count;//답변갯수를 반환, 0일수도 있다.
			
		}
		catch(NullPointerException e) {
			System.out.println("해당 날짜에대한 답변은 없어요.");
			return 0;//0개, 아무것도 작성하지 않은 경우에 해당한다.
		}
		
		//return count;
	}

	
	
	
	
	
	
	@Override
	public void test(AnswerVO answer) {
		AnswerMapper answerMapper = sqlSession.getMapper(AnswerMapper.class);
		answerMapper.insertAnswer(answer);

	}
	
	
	
}