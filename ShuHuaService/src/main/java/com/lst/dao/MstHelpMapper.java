package com.lst.dao;

import com.lst.model.MstHelp;

public interface MstHelpMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MstHelp record);

    int insertSelective(MstHelp record);

    MstHelp selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MstHelp record);

    int updateByPrimaryKey(MstHelp record);
}