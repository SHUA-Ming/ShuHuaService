package com.lst.dao;

import java.util.List;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lst.model.UserMotionRecord;

public interface UserMotionRecordMapper
{
	//int insert(UserMotionReocrd userMotionReocrd);
	int insertSelective(UserMotionRecord userMotionReocrd);
	List<UserMotionRecord> selectByUserid(Integer userid, PageBounds pageBounds);
	UserMotionRecord selectById(Integer id);
}