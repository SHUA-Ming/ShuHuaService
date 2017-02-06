package com.lst.dao;

import com.lst.model.UserMachine;

public interface UserMachineMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserMachine record);

    int insertSelective(UserMachine record);

    UserMachine selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserMachine record);

    int updateByPrimaryKey(UserMachine record);
}