package com.lst.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lst.model.MstMedal;

public interface MstMedalMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(MstMedal record);

	int insertSelective(MstMedal record);

	MstMedal selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(MstMedal record);

	List<MstMedal> selectmedalbysql(Map<String, Object> map,
			PageBounds pageBounds);

	int updateByPrimaryKey(MstMedal record);

	/**
	 * 
	 * @Title: selectMedal
	 * @Description: 查询勋章
	 * @param @return 设定文件
	 * @return List<MstMedal> 返回类型
	 * @throws
	 */
	List<MstMedal> selectMedal();

	List<MstMedal> selectByUserMedal(Integer userId);
}