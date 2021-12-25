package com.project.domain;

public class NickNameVO {

	private String adjective;
	private String noun;

	public String getAdjective() {
		return adjective;
	}

	public void setAdjective(String adjective) {
		this.adjective = adjective;
	}

	public String getNoun() {
		return noun;
	}

	public void setNoun(String noun) {
		this.noun = noun;
	}

	@Override
	public String toString() {
		return "NickNameVO [adjective=" + adjective + ", noun=" + noun + "]";
	}

}