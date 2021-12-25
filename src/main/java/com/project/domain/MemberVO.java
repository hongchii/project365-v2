package com.project.domain;

public class MemberVO {

	private String id;
	private String nickname;
	private String email;
	private String gender;
	private String age_range;
	private String token;
	private String joindate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAge_range() {
		return age_range;
	}

	public void setAge_range(String age_range) {
		this.age_range = age_range;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getJoindate() {
		return joindate;
	}

	public void setJoindate(String joindate) {
		this.joindate = joindate;
	}

	@Override
	public String toString() {
		return "MemberDTO [id=" + id + ", nickname=" + nickname + ", email=" + email + ", gender=" + gender
				+ ", age_range=" + age_range + ", token=" + token + ", joindate=" + joindate + "]";
	}

}