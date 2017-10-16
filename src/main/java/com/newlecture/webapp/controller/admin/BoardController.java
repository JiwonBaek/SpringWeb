package com.newlecture.webapp.controller.admin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mysql.jdbc.Connection;
import com.newlecture.webapp.dao.NoticeDao;
import com.newlecture.webapp.dao.NoticeFileDao;
import com.newlecture.webapp.entity.Notice;
import com.newlecture.webapp.entity.NoticeFile;
import com.newlecture.webapp.entity.NoticeView;

@Controller
@RequestMapping("/admin/board/*")
public class BoardController {

	@Autowired
	private NoticeDao noticeDao;
	
	@Autowired
	private NoticeFileDao noticeFileDao;

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

	@RequestMapping(value = "notice/reg", method = RequestMethod.GET)
	public String noticeReg() {

		return "admin.board.notice.reg";

	}

	@RequestMapping(value = "notice/reg", method = RequestMethod.POST)
	public String noticeReg(Notice notice, String aa, MultipartFile file, HttpServletRequest request)
			throws IOException {

		// title = new String(title.getBytes("ISO-8859-1"),"UTF-8"); //입력받은 데이터를 다시 꺼내서
		// utf-8로 변환 후 전송
		// content = new String(title.getBytes("ISO-8859-1"),"UTF-8");

		// 날짜 얻는 법1
		// Date curDate = new Date();

		// 날짜 얻는 법2
		Calendar cal = Calendar.getInstance();
		Date curDate2 = cal.getTime();
		int year = cal.get(Calendar.YEAR);

		// 날짜 얻는 법3
		/*
		 * SimpleDateFormat fmt = new SimpleDateFormat("yyyy"); String year
		 * =fmt.format(curDate);
		 */

		String nextId = noticeDao.getNextId();

		ServletContext ctx = request.getServletContext();
		String path = ctx.getRealPath(String.format("/resource/customer/notice/" + year + "/" + nextId));

		String fileName = file.getOriginalFilename();
		System.out.println(fileName);

		System.out.println(path);

		File f = new File(path);

		if (!f.exists()) {
			if (!f.mkdirs())
				System.out.println("디렉토리를 생성할 수가 없습니다.");
		}

		path += File.separator + file.getOriginalFilename();// path경로에 파일의 실제 이름을 더함
		File f2 = new File(path);

		InputStream fis = file.getInputStream();
		OutputStream fos = new FileOutputStream(f2);

		byte[] buf = new byte[1024];

		int size = 0;
		while ((size = fis.read(buf)) > 0)
			fos.write(buf, 0, size);

		fos.close();
		fis.close();

		String writerId = "newlec";
		notice.setWriterId(writerId);
		System.out.println(notice.getTitle());

		// int row =noticeDao.insert(title,content,writerId);

		int row = noticeDao.insert(notice);
	
		noticeFileDao.insert(new NoticeFile(null,fileName,nextId));

		Connection con;
		
		Statement st = con.createStatement();
		
		return "redirect:../notice";

	}

}
