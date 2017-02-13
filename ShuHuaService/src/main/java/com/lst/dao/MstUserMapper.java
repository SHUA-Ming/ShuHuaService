package com.lst.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lst.model.MstUser;

public interface MstUserMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(MstUser record);

	int insertSelective(MstUser record);

	MstUser selectByPrimaryKey(Integer id);

	List<MstUser> login(Map<String, String> map);

	List<MstUser> selectuserbysql(Map<String, Object> map, PageBounds pageBounds);

	List<MstUser> checkname(Map<String, Object> map, PageBounds pageBounds);

	int updateByPrimaryKeySelective(MstUser record);

	int updateByPrimaryKey(MstUser record);

	MstUser selectByNikename(MstUser mstUser);
	
	MstUser selectById(MstUser mstUser);
	
	MstUser selectByQQName(MstUser mstUser);
	
	MstUser selectByWeiXinName(MstUser mstUser);
	
	MstUser selectByWeiBoName(MstUser mstUser);
	
	MstUser selectByMobileno(MstUser mstUser);

	int updateResetPwd(MstUser mstuser);
	
	int forgetPwd(MstUser mstuser);
	
	int updateIcon(MstUser mstuser);
	
	List<MstUser> selectTop10();
	
	List<MstUser> selectUser();
}