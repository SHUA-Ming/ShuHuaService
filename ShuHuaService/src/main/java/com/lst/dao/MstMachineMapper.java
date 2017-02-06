package com.lst.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lst.model.MstMachine;

public interface MstMachineMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(MstMachine record);

	int insertSelective(MstMachine record);

	MstMachine selectByPrimaryKey(Integer id);

	List<MstMachine> selectmachinebysql(Map<String, Object> map,
			PageBounds pageBounds);

	int updateByPrimaryKeySelective(MstMachine record);

	int updateByPrimaryKeyWithBLOBs(MstMachine record);

	int updateByPrimaryKey(MstMachine record);

	/**
	 * 
	 * @Title: selectByModel
	 * @Description: 根据设备型号查询设备信息
	 * @param @param record
	 * @param @return 设定文件
	 * @return MstMachine 返回类型
	 * @throws
	 */
	MstMachine selectByModel(MstMachine record);

}