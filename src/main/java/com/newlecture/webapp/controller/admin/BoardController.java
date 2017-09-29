package com.newlecture.webapp.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.newlecture.webapp.dao.NoticeDao;
import com.newlecture.webapp.entity.NoticeView;

@Controller
@RequestMapping("/admin/board/*")
public class BoardController {

	

	@Autowired
	private NoticeDao noticeDao;

	@RequestMapping("notice")
	/* @ResponseBody 뷰를 찾지 않고 함수를 출력 */
	public String notice(@RequestParam(value = "p", defaultValue = "1") Integer p,
			@RequestParam(value = "f", defaultValue = "title") String f,
			@RequestParam(value = "q", defaultValue = "") String q, Model model) {


		List<NoticeView> list = noticeDao.getList(p, f, q);

		model.addAttribute("list", list);
		
		return "admin.board.notice.list";
	}

	@RequestMapping("notice/{id}") 
	public String noticeDetail(@PathVariable("id") String id, Model model) {

		
		model.addAttribute("n", noticeDao.get(id));
		model.addAttribute("prev", noticeDao.getPrev(id));
		model.addAttribute("next", noticeDao.getNext(id));

		return "admin.board.notice.detail";
	}
}
