package com.lst.dao;

import com.lst.model.UserMedal;

public interface UserMedalMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserMedal record);

    int insertSelective(UserMedal record);

    UserMedal selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserMedal record);

    int updateByPrimaryKey(UserMedal record);
}