package com.lst.dao;

import java.util.List;

import com.lst.model.SystemSetting;

public interface SystemSettingMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(SystemSetting record);

	int insertSelective(SystemSetting record);

	SystemSetting selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(SystemSetting record);

	int updateByPrimaryKeyWithBLOBs(SystemSetting record);

	int updateByPrimaryKey(SystemSetting record);

	List<SystemSetting> selectByurl();
}