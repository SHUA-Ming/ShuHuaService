package com.lst.dao;

import com.lst.model.UsePlan;

public interface UsePlanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UsePlan record);

    int insertSelective(UsePlan record);

    UsePlan selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UsePlan record);

    int updateByPrimaryKey(UsePlan record);
}