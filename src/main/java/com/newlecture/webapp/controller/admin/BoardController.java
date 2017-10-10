package com.newlecture.webapp.controller.admin;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.newlecture.webapp.dao.NoticeDao;
import com.newlecture.webapp.entity.Notice;
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
	
	@RequestMapping(value="notice/reg", method=RequestMethod.GET) 
	public String noticeReg() {


		return "admin.board.notice.reg";

	}
	
	@RequestMapping(value="notice/reg", method=RequestMethod.POST) 
	public String noticeReg(String title, String content) throws UnsupportedEncodingException {
		
		//title = new String(title.getBytes("ISO-8859-1"),"UTF-8"); //입력받은 데이터를 다시 꺼내서 utf-8로 변환 후 전송
		//content = new String(title.getBytes("ISO-8859-1"),"UTF-8");
		
		String writerId ="newlec";
		
		System.out.println(title);
		
		int row  =noticeDao.insert(title,content,writerId);
		//noticeDao.insert(new Notice(title,content,writerId));


		return "redirect:../notice";

	}
	
}
