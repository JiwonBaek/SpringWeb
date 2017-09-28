package com.newlecture.webapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newlecture.webapp.dao.NoticeDao;


/*/customer ���� url�� �ִ� �͸� ��� �ִ� ���� ��Ʈ�ѷ�*/
@Controller
@RequestMapping("/customer/*")
public class CustomerController {

/*	@Autowired
	private SqlSessionTemplate sqlSession;*/
	
	@Autowired
	private NoticeDao noticeDao;

	@RequestMapping("notice")
	/* @ResponseBody �並 ã�� �ʰ� �Լ��� ��� */
	public String notice(@RequestParam(value = "p", defaultValue = "1") Integer p,
			@RequestParam(value = "f", defaultValue = "title") String f,
			@RequestParam(value = "q", defaultValue = "") String q,
			Model model) {
		
		/*
		  @RequestParam(value="p", defaultValue="1") �⺻������ �����Ͽ� �Ѱ��ش�.
		  
		 String output = String.format("p:%s, q:%s\n\n", p, q); output +=
		 String.format("title : %s\n", list.get(0).getTitle());
		 
	
		 
		 return "customer/notice"; tiles ���� ��
		 */
		 model.addAttribute("list", noticeDao.getList(p, f, q));

		return "customer.notice.list";
	}

	@RequestMapping("notice/{id}")
	@ResponseBody /* �並 ã�� �ʰ� �Լ��� ��� */
	public String noticeDetail(@PathVariable("id") String aaid) {

		/*NoticeDao noticeDao = sqlSession.getMapper(NoticeDao.class);
		NoticeView noticeView = noticeDao.get(aaid);
*/
		/*return aaid + "��° ��������" + "" + noticeView.getTitle();*/
		
		return "customer.notice.detail";
	}

}