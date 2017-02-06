package com.lst.dao;

import com.lst.model.UseWeight;

public interface UseWeightMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UseWeight record);

    int insertSelective(UseWeight record);

    UseWeight selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UseWeight record);

    int updateByPrimaryKey(UseWeight record);
}