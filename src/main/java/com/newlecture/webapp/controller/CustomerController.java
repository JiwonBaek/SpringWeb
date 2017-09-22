package com.newlecture.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/*/customer ���� url�� �ִ� �͸� ��� �ִ� ���� ��Ʈ�ѷ�*/
@Controller
@RequestMapping("/customer/*")
public class CustomerController {
   
   @RequestMapping("notice")
   @ResponseBody/*�並 ã�� �ʰ� �Լ��� ���*/
   public String notice(@RequestParam(value="p", defaultValue="1") Integer p, @RequestParam(value="q", defaultValue="") String q) 
   {
	  /* @RequestParam(value="p", defaultValue="1") �⺻������ �����Ͽ� �Ѱ��ش�.*/
	   
	   
	   String output = String.format("p:%s, q:%s", p, q);
		return output;
	}
   
   @RequestMapping("notice/{id}")
   @ResponseBody/*�並 ã�� �ʰ� �Լ��� ���*/
  
   public String noticeDetail( @PathVariable("id") String id) {
	   
		return id+"��° ��������";
	}
   
   
  
}