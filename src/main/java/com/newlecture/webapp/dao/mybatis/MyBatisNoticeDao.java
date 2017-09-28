package com.newlecture.webapp.dao.mybatis;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.newlecture.webapp.dao.NoticeDao;
import com.newlecture.webapp.entity.NoticeView;

public class MyBatisNoticeDao implements NoticeDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public NoticeView get(String id) {
		NoticeDao noticeDao = sqlSession.getMapper(NoticeDao.class);
		NoticeView noticeView = noticeDao.get(id);
		
		return noticeView;
	}

	@Override
	public int update(String id, String title, String content) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<NoticeView> getList(int page, String filed, String query) {
	
		NoticeDao noticeDao = sqlSession.getMapper(NoticeDao.class);
		List<NoticeView> list = noticeDao.getList(page, filed, query);
		
		return list;
	
	}

}
