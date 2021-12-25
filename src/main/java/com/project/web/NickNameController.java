package com.project.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.domain.NickNameVO;
import com.project.service.NickNameService;

@CrossOrigin(origins = "*")
@RestController
public class NickNameController {

	@Autowired
	private NickNameService nickNameService;

	@RequestMapping(value = "/random", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<String> randomNick() throws Exception {
		System.out.println("main controller");
		List<NickNameVO> nickAdj = new ArrayList<NickNameVO>();
		nickAdj = nickNameService.getAdj();
		List<NickNameVO> nickNoun = new ArrayList<NickNameVO>();
		nickNoun = nickNameService.getNoun();

		List<String> nickName = new ArrayList<String>();

		for (int i = 0; i < nickAdj.size(); i++) {
			// DB에서 가져온 8개의 형용사, 명사 합쳐주기.
			String adjective = String.valueOf(nickAdj.get(i));
			String noun = String.valueOf(nickNoun.get(i));
			String add = adjective + " " + noun;
			nickName.add(add);

		}

		System.out.println("random nickName :::   " + nickName);
		return nickName;
	}
}