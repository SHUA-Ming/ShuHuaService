package com.lst.dao;

import java.util.HashMap;
import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lst.model.MaxReocrd;
import com.lst.model.SummaryReocrd;
import com.lst.model.UserReocrd;

public interface UserReocrdMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(UserReocrd record);

	int insertSelective(UserReocrd record);

	UserReocrd selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(UserReocrd record);

	int updateByPrimaryKey(UserReocrd record);

	List<UserReocrd> selectByUserid(Integer userid, PageBounds pageBounds);

	List<SummaryReocrd> getSummaryByUserid(HashMap<String, Object> map);

	MaxReocrd getMaxRecordByUserid(HashMap<String, Object> map);
}