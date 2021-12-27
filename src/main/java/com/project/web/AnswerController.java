package com.project.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.domain.AnswerCountVO;
import com.project.domain.AnswerVO;
import com.project.service.AnswerService;

@CrossOrigin(origins = "*")
@RestController
public class AnswerController {
   
   SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy");
   SimpleDateFormat format2 = new SimpleDateFormat ( "MMdd");
         
   Date time = new Date();
         
   String year = format1.format(time);
   String date = format2.format(time);
         
   
   
   @Autowired
   private AnswerService answerService;

   //=================================================
   //==================내 일기장 ==========================
   //=================================================
   //답변 작성하기 ,POST
   @PostMapping("/answers/new")
   public int write(@RequestBody AnswerVO answer) throws Exception {
      System.out.println("답변작성 페이지/ controller name: write");
      System.out.println("year:"+year);
      System.out.println("date:"+date);
         
      System.out.println("answerVO"+answer);
      System.out.println("answerVO.getAnswer_year: "+answer.getAnswer_year());
      System.out.println("answerVO.getanswer: "+answer.getAnswer());
      System.out.println("answerVO.getPublic_answer: "+answer.getPublic_answer());
      //answervo.setDelete_date(null);
      System.out.println("++++++++++++++++++++++++++");
      int result = answerService.insertAnswer(answer);
      int result2=0;
      int result3=1;
      
      //insetAnswer성공시 (답변등록 성공시) 기존에 답변이 있으면 count+1, 없으면 컬럼을 새로 만들어준다.
      /*if (result == 1 ) {
         
         AnswerCountVO answercount = new AnswerCountVO();
         answercount.setMember_num(answer.getMember_num());
         answercount.setQuestion_num(answer.getQuestion_num());
         
         result2 = answerService.count(answercount);
         
         //answer_count테이블에 해당 질문에대한 답변이 존재하는지 확인
         if (result2==1) { //셋팅
            answerService.setCount(answercount);
            result3 =1;
         }
         else {//업또는 다운
            answerService.updateCountUp(answercount);
            result3 =1;
            
         }
         //AnswerCountVO answercount = new AnswerCountVO();
         //answercount.setMember_num(answer.getMember_num());
         //answercount.setQuestion_num(answer.getQuestion_num());
         
         //result2 = answerService.updateCountUp(answercount);
         
      }
      else {
         System.out.println("실패!!!!!!!!!!!!");
         result3=0;
      }*/
      //return result2;
      System.out.println("성공 1, 실패 0 : " + result3);
      return result3;
      
   }
   
   //내답변(일기장) 전체 조회         
   @GetMapping("/answers/{question_num}/{member_num}")
   public List<AnswerVO> read(@PathVariable("question_num") int question_num,@PathVariable("member_num") int member_num) throws Exception {
      System.out.println("수정 페이지/ controller name: read");
      System.out.println("question_num: "+question_num);
      System.out.println("member_num: "+member_num);
      
      List<AnswerVO> answer = answerService.readAnswer(question_num,member_num);
      System.out.println("--2-");
   
      //콘솔찍어보기
      for (AnswerVO data:answer) {
         System.out.println("answer: "+ answer);
      }
      System.out.println("--3-");
      
      return answer;
   }
   
   //메인> 랜덤 답변 8개 출력하기
   @GetMapping("/random/{question_num}")
   public List<String> readRandomAnswer(@PathVariable("question_num") int question_num) throws Exception {
      System.out.println("랜덤 답변 출력하기/ controller name: readRandomAnswer");
      System.out.println("question_num: "+question_num);
      
      List<String> result = new ArrayList<String>();
      
      System.out.println("=====");
      
      result.addAll(answerService.readRandomAnswer(question_num));
      System.out.println("--2-");
      
      for(String str : result) {
         //result.add(str);
         System.out.println("str: "+str);
      }
      //System.out.println("result: "+str);
      return result;
   }
   
   //내답변 수정버튼>수정페이지
   @GetMapping("/answers/pages/{answer_num}")
   public AnswerVO UpdatePage(@PathVariable("answer_num") int answer_num) throws Exception {
      System.out.println("수정 페이지/ controller name: updatePage");
      
      
      AnswerVO result = answerService.updateAnswerPage(answer_num);
      
      return result;
      
   }
   
   //수정페이지 > 내용수정후 > 수정버튼
   //@RequestMapping(value="/answers/pages/{answer_num}/{member_num}", method = {RequestMethod.GET, RequestMethod.PATCH} )
   @RequestMapping(value="/answers/pages", method = {RequestMethod.GET, RequestMethod.PATCH} )
   //public void update(@PathVariable("answer_num") int answer_num, @PathVariable("member_num") int member_num)throws Exception {
   public void update(@RequestBody AnswerVO answer)throws Exception {
      System.out.println("수정기능 시작! : controller name : update");
      
      //AnswerVO answer = new AnswerVO();
      /*answer.setAnswer("이건 바뀔 내용이에요 !!!!");
      answer.setPublic_answer("Y");
      answer.setAnswer_num(answer_num);
      answer.setMember_num(member_num);*/
      
      System.out.println("Answer: "+answer.getAnswer());
      System.out.println("public_answer: "+answer.getPublic_answer());
      System.out.println("Answer_num: " +answer.getAnswer_num());
      
      answerService.updateAnswer(answer);
      System.out.println("=========수정완료=========");
   
   }
   
   //내일기장 > 공개여부 버튼
   //@RequestMapping(value="/settings/{answer_num}/{member_num}", method = {RequestMethod.GET, RequestMethod.PATCH} )
   //public int publicAnswer(@RequestBody AnswerVO answer, @PathVariable("answer_num") int answer_num, @PathVariable("member_num") int member_num)throws Exception {
   @RequestMapping(value="/settings", method = {RequestMethod.GET, RequestMethod.PATCH} )
   public int publicAnswer(@RequestBody AnswerVO answer)throws Exception {   
      System.out.println("공개여부 변경 시작! : controller name : publicAnswer");
      
      int result=0;
      //answer.setAnswer_num(answer_num);
      //answer.setMember_num(member_num);
      
      System.out.println("public_answer: "+answer.getPublic_answer());
      System.out.println("Answer_num: " +answer.getAnswer_num());
      System.out.println("Member_num: " +answer.getMember_num());
      
      if(answer.getAnswer_num()!=0 && answer.getPublic_answer()!=null && answer.getPublic_answer()!="") {
         result=answerService.publicAnswer(answer);
      }else {
         result =0;
      }
      System.out.println("성공 1, 실패 0 : " + result);
      
      return result;
   }
   
   
   //일기장> 내답변 삭제(휴지통으로)
   //@RequestMapping(value="/answers/trashes/{answer_num}/{member_num}", method= {RequestMethod.GET, RequestMethod.PATCH})
   @RequestMapping(value="/answers/trashes", method= {RequestMethod.GET, RequestMethod.PATCH})
   //public int updateDelete(@PathVariable("answer_num") int answer_num, @PathVariable("member_num") int member_num) {
   public int updateDelete(@RequestBody AnswerVO answer) {
      
      System.out.println("삭제 수정기능 시작! : controller name : updateDelete");
      
      //answer.setAnswer_num(answer_num);
      //answer.setMember_num(member_num);
      
      System.out.println("Answer_delete: "+answer.getAnswer_delete());
      System.out.println("Delete_date: "+answer.getDelete_date());
      System.out.println("Answer_num: " +answer.getAnswer_num());
      System.out.println("member_num: " +answer.getMember_num());
      
      
      int result=0;
      String str =answer.getAnswer_delete();
      System.out.println("str: "+str);
      if (str.equals("N")) {
         result = answerService.updateDelete(answer);
      }
      else {
         result=0;
         System.out.println("삭제는 answer_delete값이 N여야 Y로 바꿀수 있어요.");
         
      }
      //System.out.println("=========삭제 수정완료=========");
      
      //int result = answerService.insertAnswer(answer);
      int result2=1;
      
      //answer_delete값 변경 완료시 실행
      /*if (result == 1 ) {
         
         AnswerCountVO answercount = new AnswerCountVO();
         answercount.setMember_num(answer.getMember_num());
         answercount.setQuestion_num(answer.getQuestion_num());
         
         System.out.println("answer.getMember_num(): "+answer.getMember_num());
         System.out.println("answer.getQuestion_num(): "+answer.getQuestion_num());
         result2 = answerService.updateCountDown(answercount);   
         
      }
      else {
         System.out.println("실패!!!!!!!!!!!!");
         result2=0;
      }*/
      System.out.println("성공 1, 실패 0 : " + result2);
      return result2;
      //return result;
      
      
   }
   
   
   //달력>날짜에 저장된 답변갯수 불러오기
   @GetMapping("/numbers/{question_num}/{member_num}")
   public int countSelect(@PathVariable("question_num") int question_num, @PathVariable("member_num") int member_num) {
      System.out.println("날짜에 저장된 답변갯수 불러오기 : controller name : countSelect");
      
      int result=0;
      int result2=0;
      
      AnswerCountVO answercount = new AnswerCountVO();
      answercount.setMember_num(member_num);
      answercount.setQuestion_num(question_num);
      
      result=answerService.countSelect(answercount);
      
      //성공실패 확인
      if(result>-1) {
         result2=1;
      }
      else {
         result2=0;
         System.out.println("countSelect 실패.");
      }
      System.out.println("성공 1, 실패 0 : " + result2);
      return result;
   }
   
   //=================================================
   //================== 휴지통 ==========================
   //=================================================
   //휴지통 > 되돌리기 버튼(답변 복구)
   @RequestMapping(value="/trashes/settings/{answer_num}/{member_num}", method= {RequestMethod.GET, RequestMethod.PATCH})
   public ResponseEntity<Integer> trashPublic(@RequestBody AnswerVO answer,@PathVariable("answer_num") int answer_num, @PathVariable("member_num") int member_num) {
      
      System.out.println("답변 복원하기 시작! : controller name : TrashPublic");
      
      answer.setAnswer_num(answer_num);
      answer.setMember_num(member_num);
      
      
      System.out.println("Answer_delete: "+answer.getAnswer_delete());
      System.out.println("Delete_date: "+answer.getDelete_date());
      System.out.println("Answer_num: " +answer.getAnswer_num());
      System.out.println("Member_num: " +answer.getMember_num());
      
      int result=0;
      //int result = answerService.trashPublic(answer);
      
      String str =answer.getAnswer_delete();
      String date = answer.getDelete_date();
      System.out.println("str: "+str);
      //answer_delete가 Y인 답변인지(즉 휴지통에있는 답변이맞는지) 확인후 해당 답변복구를 시작한다.
      if (str.equals("Y") && date != null ) {
         //result = answerService.trashPublic(answer);
         result = answerService.trashUpdate(answer);
      }
      else {
         result=0;
         System.out.println("삭제는 answer_delete값이 Y인 값이고, dalete_date값이 null이 아니어야 합니다.");
         
      }
      
      
      System.out.println("=========삭제 수정완료=========");
      System.out.println("=========답변 횟수를 수정합니다=========");
      
      
      //answer_delete값 변경 완료시 실행(다시 내일기장으로 답변 복구)
      /*if (result == 1 ) {
         
         AnswerCountVO answercount = new AnswerCountVO();
         answercount.setMember_num(answer.getMember_num());
         answercount.setQuestion_num(answer.getQuestion_num());
         System.out.println("member_num: " +answer.getMember_num());
         System.out.println("Question_num: " +answer.getQuestion_num());
         
         result2 = answerService.updateCountUp(answercount);   
         
      }
      else {
         System.out.println("먼저 answer_delete부터 맞추고 오세요. 실패!!!!!!!!!!!!");
         result2=0;
      }*/
      if (result ==1) {
         System.out.println("성공 1, 실패 0 : " + result);
         return new ResponseEntity<>(result, HttpStatus.OK);
         
      } else {
         System.out.println("성공 1, 실패 0 : " + result);
         return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
      }
      
   }
   

   //휴지통 > 삭제한 답변 모두 보기
   //@GetMapping("/trashes/{member_num}")
   //public List<AnswerVO> trashRead(@RequestBody AnswerVO answer,@PathVariable("member_num") int member_num) throws Exception {
   /*public List<AnswerVO> trashRead(@PathVariable("member_num") int member_num) throws Exception {
      System.out.println("휴지통 전체 보기! : controller name : trashRead");
      System.out.println("member_num: "+member_num);
      //System.out.println("delete_date: "+delete_date);
      
      //List<AnswerVO> answer = answerService.readTrash(member_num);
      //List<Integer> answerInt = new ArrayList<Integer>(); //경과일을 받아올 list
      List<AnswerVO> list =  new ArrayList<AnswerVO>();
      //answer = answerService.readTrash(member_num);
      
      System.out.println("=====");
      //7일이 지난 답변은 삭제해야한다.
      //List<Integer> result = new ArrayList<Integer>();
      
      //for(int i=0; i<result.size(); i++) {
         answerService.deleteDateCount(member_num);
      //}
      
      
      //
      list.addAll(answerService.readTrash(member_num));
      System.out.println("--2-");
      
      for(AnswerVO str : list) {
         //result.add(str);
         System.out.println("str: "+str);
      }
      //System.out.println("result: "+str);
      return list;
   
      
   }*/
   //휴지통 > 휴지통 전체 조회
      //answer_delete가 "Y"인 해당 맴버의 모든 질문을 조회
      @GetMapping("/trashes/{member_num}")
      //public List<AnswerVO> trashRead(@RequestBody AnswerVO answer,@PathVariable("member_num") int member_num) throws Exception {
      public List<AnswerVO> trashRead(@PathVariable("member_num") int member_num) throws Exception {
         System.out.println("휴지통 전체 조회! : controller name : trashRead");
         System.out.println("member_num: "+member_num);
         
         List<AnswerVO> list =  new ArrayList<AnswerVO>();
         
         //answer = answerService.readTrash(member_num);
         
         System.out.println("--------------------");
         //7일이 지난 답변은 삭제해야한다.
         answerService.deleteDateCount(member_num);
            
         //
         list.addAll(answerService.readTrash(member_num));
         //list.add(answerService.readTrash(member_num).get(member_num).getQuestionVO().getQuestion());
         System.out.println("--2-");
         
         for(AnswerVO str : list) {
            //result.add(str);
            System.out.println("str: "+str);
         }
         //System.out.println("result: "+str);
         return list;
      
         
      }
   
   //휴지통 > 삭제하기 (영구삭제)
      //휴지통에서 조회된 모든 답변중 선택한 답변을 영구삭제
      @DeleteMapping("/trashes/{answer_num}")
      public  ResponseEntity<Integer> deleteAnswer(@RequestBody AnswerVO answer,@PathVariable("answer_num") int answer_num) {
         System.out.println("답변 삭제 시작! : controller name : deleteAnswer");
         System.out.println("answer_num: "+answer_num);
         
         //AnswerVO answer = new AnswerVO();
         answer.setAnswer_num(answer_num);;
         //answer.setMember_num(2);
         //answer.setAnswer_num(answer_num);
         
         int result2=answerService.deleteAnswer(answer);
         //int result2=1;
         
         //답변이 삭제되면 카운트를 -1시켜줍니다.
         /*if (result == 1 ) {
            
            
            AnswerCountVO answercount = new AnswerCountVO();
            answercount.setMember_num(answer.getMember_num());
            answercount.setQuestion_num(answer.getQuestion_num());
            System.out.println("answercount.getMember_num: " +answercount.getMember_num());
            System.out.println("answercount.getQuestion_num: " +answercount.getQuestion_num());
            
            result2 = answerService.updateCountDown(answercount);   
            
         }
         else {
            System.out.println("실패!!!!!!!!!!!!");
            result2=0;
         }*/
         if (result2==1) { 
            
            System.out.println("성공 1, 실패 0 : " + result2);
            return new ResponseEntity<>(result2, HttpStatus.OK);
            
         } else {
            
            System.out.println("성공 1, 실패 0 : " + result2);
            return new ResponseEntity<>(result2, HttpStatus.BAD_REQUEST);
         }
         
      }
      
      //휴지통 > 휴지통 비우기(전체 삭제)
      //휴지통 > 진짜 삭제하기
         @DeleteMapping("/trashes/all")
         public ResponseEntity<Integer> allDeleteAnswer(@RequestBody List<AnswerVO> answerlist) {
            System.out.println("휴지통 비우기 시작! : controller name : allDeleteAnswer");
            System.out.println("answerlist: "+answerlist);
            
            int result2=0;
            String str = null;
            for (int i=0; i<answerlist.size(); i++) {
               str = answerlist.get(i).getAnswer_delete();
               System.out.println("--------------------------");
               System.out.println("str: "+ str );
               
               if(str.equals("Y")) {
                  //for(AnswerVO an : answerlist) {
                     
                     System.out.println(i+"번째 for문을 실행합니다");
                     System.out.println("--------------------------");
                     
                     AnswerVO answer = answerlist.get(i);
                     int result=answerService.deleteAnswer(answer);
                     
                     result2=1;
                     
               }   //}
               else {
                  System.out.println("휴지통 데이터가 아닙니다!");
                  System.out.println("--------------------------");
                  
                  result2=0;
               }
               
            }
            
            if (result2==1) {
               
               System.out.println("성공 1, 실패 0 : " + result2);
               return new ResponseEntity<>(result2, HttpStatus.OK);
               
            } else {
               
               System.out.println("성공 1, 실패 0 : " + result2);
               return new ResponseEntity<>(result2, HttpStatus.BAD_REQUEST);
            }
               
               
               //답변이 삭제되면 카운트를 -1시켜줍니다.
               /*if (result == 1 ) {
                  
                  
                  AnswerCountVO answercount = new AnswerCountVO();
                  answercount.setMember_num(answer.getMember_num());
                  answercount.setQuestion_num(answer.getQuestion_num());
                  System.out.println("answercount.getMember_num: " +answercount.getMember_num());
                  System.out.println("answercount.getQuestion_num: " +answercount.getQuestion_num());
                  
                  result2 = answerService.updateCountDown(answercount);   
                  count++;
               }
               else {
                  System.out.println("실패!!!!!!!!!!!!");
                  result2=0;
               }*/
               
            //}      
            
         }//all 
   
   
      
      
      
      
      
      
}