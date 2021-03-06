package com.newlecture.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.newlecture.webapp.dao.NoticeDao;
import com.newlecture.webapp.entity.NoticeView;

/*/customer 이후 url만 있는 것만 담겨 있는 폴더 콘트롤러*/
@Controller
@RequestMapping("/customer/*")
public class CustomerController {

	/*
	 * @Autowired private SqlSessionTemplate sqlSession;
	 */

	@Autowired
	private NoticeDao noticeDao;

	@RequestMapping("notice")
	/* @ResponseBody 뷰를 찾지 않고 함수를 출력 */
	public String notice(@RequestParam(value = "p", defaultValue = "1") Integer p,
			@RequestParam(value = "f", defaultValue = "title") String f,
			@RequestParam(value = "q", defaultValue = "") String q, Model model) {

		/*
		 * @RequestParam(value="p", defaultValue="1") 기본값으로 설정하여 넘겨준다.
		 * 
		 * String output = String.format("p:%s, q:%s\n\n", p, q); output +=
		 * String.format("title : %s\n", list.get(0).getTitle());
		 * 
		 * return "customer/notice"; tiles 설정 전
		 */

		List<NoticeView> list = noticeDao.getList(p, f, q);

		model.addAttribute("list", list);

		return "customer.notice.list";
	}

	@RequestMapping("notice-ajax")
	@ResponseBody /* 뷰를 찾지 않고 함수를 출력 */
	public String noticeAjax(@RequestParam(value = "p", defaultValue = "1") Integer p,
			@RequestParam(value = "f", defaultValue = "title") String f,
			@RequestParam(value = "q", defaultValue = "") String q, Model model) {

		/*
		  NoticeDao noticeDao = sqlSession.getMapper(NoticeDao.class); NoticeView
		  noticeView = noticeDao.get(aaid);
		  
		  return aaid + "번째 공지사항" + "" + noticeView.getTitle();
		 */

		List<NoticeView> list = noticeDao.getList(p, f, q);

		String json = "";
		
		Gson gson = new Gson();
		json = gson.toJson(list);
		
		try {
			Thread.sleep(3000);
		}catch (InterruptedException e) {
			e.printStackTrace();
		
		}

	/*	StringBuilder builder = new StringBuilder();
		builder.append("[");
		builder.append("{},");
		builder.append("{}");
		builder.append("]");

		json = builder.toString();*/

		return json;
	}

	@RequestMapping("notice/{id}")
	@ResponseBody /* 뷰를 찾지 않고 함수를 출력 */
	public String noticeDetail(@PathVariable("id") String id, Model model) {

		/*
		  NoticeDao noticeDao = sqlSession.getMapper(NoticeDao.class); NoticeView
		  noticeView = noticeDao.get(aaid);
		  
		  return aaid + "번째 공지사항" + "" + noticeView.getTitle();
		 */

		model.addAttribute("n", noticeDao.get(id));
		model.addAttribute("prev", noticeDao.getPrev(id));
		model.addAttribute("next", noticeDao.getNext(id));

		return "customer.notice.detail";
	}

}