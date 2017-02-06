package com.lst.dao;

import com.lst.model.OpRecord;

public interface OpRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OpRecord record);

    int insertSelective(OpRecord record);

    OpRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OpRecord record);

    int updateByPrimaryKeyWithBLOBs(OpRecord record);

    int updateByPrimaryKey(OpRecord record);
}