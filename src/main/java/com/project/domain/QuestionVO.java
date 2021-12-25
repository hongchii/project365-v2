package com.project.domain;

public class QuestionVO {

	private int question_num;
	private String question;

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

	@Override
	public String toString() {
		return "Question [question_num=" + question_num + ", question=" + question + "]";
	}

}