package com.newlecture.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/*")
public class HomeController {

	@RequestMapping("/index")
	/*@ResponseBody*/
	public String index() {
		
		//return "index"; WEB-INF/views/index.jsp ��ȯ -->webservlet�� ����
		return "home.index";
	}

}