package com.newlecture.webapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.newlecture.webapp.entity.NoticeView;


public interface NoticeDao {
List<NoticeView>getList(@Param("page")int page,String filed, String query);
int getCount();
/*@Select("select * from Notice where id=#{id}") 이렇게 쿼리문 작성 가능*/
NoticeView get(String id);
int update(String id, String title, String content);
}
