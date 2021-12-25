package com.project.domain;

import java.util.Date;

public class DiaryVO {

	private int question_num;
	// private date question_date;
	private String question;

	private int answer_num;
	private Date answer_year;
	private Date answer_date;
	private String answer;
	private String public_answer;

	public int getQuestion_num() {
		return question_num;
	}

	public void setQuestion_num(int question_num) {
		this.question_num = question_num;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public int getAnswer_num() {
		return answer_num;
	}

	public void setAnswer_num(int answer_num) {
		this.answer_num = answer_num;
	}

	public Date getAnswer_year() {
		return answer_year;
	}

	public void setAnswer_year(Date answer_year) {
		this.answer_year = answer_year;
	}

	public Date getAnswer_date() {
		return answer_date;
	}

	public void setAnswer_date(Date answer_date) {
		this.answer_date = answer_date;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getPublic_answer() {
		return public_answer;
	}

	public void setPublic_answer(String public_answer) {
		this.public_answer = public_answer;
	}

}