package com.project.mapper;

import java.util.List;

import com.project.domain.NickNameVO;

public interface NickNameMapper {

	public List<NickNameVO> getAdj() throws Exception;

	public List<NickNameVO> getNoun() throws Exception;

}