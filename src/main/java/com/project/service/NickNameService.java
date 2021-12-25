package com.project.service;

import java.util.List;

import com.project.domain.NickNameVO;

public interface NickNameService {
	public List<NickNameVO> getAdj() throws Exception;

	public List<NickNameVO> getNoun() throws Exception;
}