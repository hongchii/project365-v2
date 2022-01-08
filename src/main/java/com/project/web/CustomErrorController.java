package com.project.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin(origins = "*")
@Controller
public class CustomErrorController {

	@RequestMapping("/error")
	public String handleError() {
		return "index.html";
	}

}