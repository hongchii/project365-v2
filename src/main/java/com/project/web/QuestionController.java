package com.project.web;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.project.domain.QuestionVO;
import com.project.service.QuestionService;

@CrossOrigin(origins = "*")
@RestController
public class QuestionController {

	@Autowired
	private QuestionService questionService;
	
	/*
	 *  메인페이지, 답변 작성 페이지에서 오늘 질문을 불러온다.
	 */
	@GetMapping(value = "/question/{question_num}", produces = "application/json; charset=utf-8")
	public ResponseEntity<QuestionVO> getQuestion(@PathVariable("question_num") int question_num) throws Exception {
		System.out.println("넘어온 질문번호 :: " + question_num);
		// 받아온 질문 번호와 오늘 날짜를 비교하기 위해
		Calendar today = Calendar.getInstance();
		System.out.print("이 해의 며칠 :: ");
		System.out.println(today.get(Calendar.DAY_OF_YEAR));
		
		QuestionVO question = new QuestionVO();
		question = questionService.getQuestion(question_num);
		
		if (question_num != today.get(Calendar.DAY_OF_YEAR)) {
			System.out.println("오늘 아님!");
			question = null;
			return new ResponseEntity<>(question, HttpStatus.BAD_REQUEST);
		} else {
			System.out.println(question);
			return new ResponseEntity<>(question, HttpStatus.OK);
		}
	}
	
	/*
	 * 내 일기장, 휴지통 오늘 외의 모든 질문 불러오기. 
	 */
	@GetMapping(value = "/question/calendars/{question_num}", produces = "application/json; charset=utf-8")
	public ResponseEntity<QuestionVO> getQuestions(@PathVariable("question_num") int question_num) throws Exception {
		System.out.println("넘어온 질문번호 :: " + question_num);

		QuestionVO question = new QuestionVO();
		question = questionService.getQuestion(question_num);
		
		
		if (question == null) {
			System.out.println("366개의 질문이 아님. 잘못된 요청");
			return new ResponseEntity<>(question, HttpStatus.BAD_REQUEST);
		} else {
			System.out.println(question);
			return new ResponseEntity<>(question, HttpStatus.OK);
		}
		
	}
	
}
	
	/*
	@GetMapping(value = "/{question_num}", produces = "application/json; charset=utf-8")
	public ResponseEntity<QuestionVO> getQuestion(@PathVariable("question_num") int question_num) throws Exception {
		System.out.println("넘어온 질문번호 :: " + question_num);
		Calendar today = Calendar.getInstance();
		// 올해 연도
		System.out.print("이 해의 연도 :: ");
		System.out.println(today.get(Calendar.YEAR));
		
		QuestionVO question = new QuestionVO();
		question = questionService.getQuestion(question_num);
		
		int year = today.get(Calendar.YEAR);
		//int year = 2020; // 윤년 test
		//yearChk(year); // 1: 윤년 2: 평년
		// 평년인지 체크 평년이면 true, 윤년이면 false
		if (yearChk(year) && question_num < 60) { // 평년이면서 1~2월
			
			//Calendar today = Calendar.getInstance();
			if (question_num != today.get(Calendar.DAY_OF_YEAR) + 1) {
				System.out.println("오늘 아님!1");
				question = null;
				return new ResponseEntity<>(question, HttpStatus.BAD_REQUEST);
				
			} else {
				System.out.println("오늘 맞음!1");
				return new ResponseEntity<>(question, HttpStatus.OK);
			}
		
		} else if (yearChk(year) && question_num > 60){ // 평년이면서 3~12월
			
			if (question_num != today.get(Calendar.DAY_OF_YEAR) + 2) {
				System.out.println("오늘 아님!2");
				question = null;
				return new ResponseEntity<>(question, HttpStatus.BAD_REQUEST);
				
			} else {
				System.out.println("오늘 맞음!2");
				return new ResponseEntity<>(question, HttpStatus.OK);
			}
			
			
			
		} else { // 윤년일떄
			
			if (question_num != today.get(Calendar.DAY_OF_YEAR) + 1) {
				System.out.println("오늘 아님!3");
				question = null;
				return new ResponseEntity<>(question, HttpStatus.BAD_REQUEST);
				
			} else {
				System.out.println("오늘 맞음!3");
				return new ResponseEntity<>(question, HttpStatus.OK);
			}
			
		}
	
	}
	
	
	// 나의 일기장, 전체 조회 페이지 - 질문
	@GetMapping(value = "calendars/{question_num}", produces = "application/json; charset=utf-8")
	public ResponseEntity<QuestionVO> getQuestions(@PathVariable("question_num") int question_num) throws Exception {
		System.out.println("넘어온 질문번호 :: " + question_num);
		
		Calendar today = Calendar.getInstance();
		// 올해 연도
		System.out.print("이 해의 연도 :: ");
		System.out.println(today.get(Calendar.YEAR));
		
		QuestionVO question = new QuestionVO();
		question = questionService.getQuestion(question_num);
		
		int year = today.get(Calendar.YEAR);
		
		// 평년인지 체크 평년이면 true, 윤년이면 false
		if (yearChk(year) && question_num < 60) { // 평년이면서 1~2월
			
			//Calendar today = Calendar.getInstance();
			if (question_num != today.get(Calendar.DAY_OF_YEAR) + 1) {
				System.out.println("오늘 아님!1");
				question = null;
				return new ResponseEntity<>(question, HttpStatus.BAD_REQUEST);
				
			} else {
				System.out.println("오늘 맞음!1");
				return new ResponseEntity<>(question, HttpStatus.OK);
			}
		
		} else if (yearChk(year) && question_num > 60){ // 평년이면서 3~12월
			
			if (question_num != today.get(Calendar.DAY_OF_YEAR) + 2) {
				System.out.println("오늘 아님!2");
				question = null;
				return new ResponseEntity<>(question, HttpStatus.BAD_REQUEST);
				
			} else {
				System.out.println("오늘 맞음!2");
				return new ResponseEntity<>(question, HttpStatus.OK);
			}
			
			
			
		} else { // 윤년일떄
			
			if (question_num != today.get(Calendar.DAY_OF_YEAR) + 1) {
				System.out.println("오늘 아님!3");
				question = null;
				return new ResponseEntity<>(question, HttpStatus.BAD_REQUEST);
				
			} else {
				System.out.println("오늘 맞음!3");
				return new ResponseEntity<>(question, HttpStatus.OK);
			}
			
		}
	
		
		/*
		  	if (question == null) {
		 
			System.out.println("잘못된 질문 번호. 잘못된 요청");
			return new ResponseEntity<>(question, HttpStatus.BAD_REQUEST);
		} else {
			System.out.println(question);
			return new ResponseEntity<>(question, HttpStatus.OK);
		}
		*/
	/*
	}
	
	// 윤년 || 평년 check
	static boolean yearChk(int year){
		// int yearResult = 0;
		
		if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0 ) {
			// 윤년
			System.out.println("올해는 윤년입니다.");
			return false;
		}else {
			// 평년
			System.out.println("올해는 평년입니다.");
			return true;
		}
	}
	*/
	/*
	// 오늘인지 check - 메인화면에 오늘 질문만 뿌려주기 위해.
	static boolean todayChk(int question_num) {
		
		Calendar today = Calendar.getInstance();
		if (question_num != today.get(Calendar.DAY_OF_YEAR) + 1) {
			System.out.println("오늘 아님!");
			return false;
			
		} else {
			System.out.println("오늘 맞음!");
			return true;
		}
	}
	*/
	// 윤년 leap year 평년 common year
	/*
	static int commonYear(int question_num) {
		
		if (60 > question_num) {
			
			if (question_num != today.get(Calendar.DAY_OF_YEAR) + 1) {
				System.out.println("오늘 아님!");
				question = null;
				return new ResponseEntity<>(question, HttpStatus.BAD_REQUEST);
			} else {
				System.out.println(question);
				return new ResponseEntity<>(question, HttpStatus.OK);
			}
		} 
		
		if (60 < question_num) {
			
			if (question_num != today.get(Calendar.DAY_OF_YEAR) + 1) {
				System.out.println("오늘 아님!");
				question = null;
				return new ResponseEntity<>(question, HttpStatus.BAD_REQUEST);
			} else {
				System.out.println(question);
				return new ResponseEntity<>(question, HttpStatus.OK);
			}
			
		}
		
		return 0;
	}
	
	static int leapYear (int yearResult) {
		
		return 0;
	}
}
	 */
