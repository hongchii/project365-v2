package com.project.service;

import java.util.HashMap;

public interface MemberService {

	public int insertMember(HashMap<String, Object> userInfo) throws Exception;

	public int getMember(Object id) throws Exception;

	public int getMemberNum(Object id) throws Exception;
}