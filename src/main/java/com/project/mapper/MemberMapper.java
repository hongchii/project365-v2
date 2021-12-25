package com.project.mapper;

import java.util.HashMap;

public interface MemberMapper {

	public void insertMember(HashMap<String, Object> userInfo);

	public int getMember(Object id);

	public int getMemberNum(Object id);
}