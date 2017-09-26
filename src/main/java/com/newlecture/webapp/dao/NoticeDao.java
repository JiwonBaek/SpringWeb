package com.newlecture.webapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.newlecture.webapp.entity.NoticeView;


public interface NoticeDao {
List<NoticeView>getList(@Param("page")int page,String filed, String query);
int getCount();
NoticeView get(String id);
int update(String id, String title, String content);
}
