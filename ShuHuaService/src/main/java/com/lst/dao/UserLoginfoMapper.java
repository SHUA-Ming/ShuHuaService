package com.lst.dao;

import com.lst.model.UserLoginfo;

public interface UserLoginfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserLoginfo record);

    int insertSelective(UserLoginfo record);

    UserLoginfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserLoginfo record);

    int updateByPrimaryKey(UserLoginfo record);
}