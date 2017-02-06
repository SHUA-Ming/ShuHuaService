package com.lst.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lst.model.SuggestInfo;

public interface SuggestInfoMapper
{
    int deleteByPrimaryKey(Integer id);


    int insert(SuggestInfo record);


    int insertSelective(SuggestInfo record);


    SuggestInfo selectByPrimaryKey(Integer id);


    List<SuggestInfo> selectsuggestbysql(Map<String, Object> map,
            PageBounds pageBounds);


    int updateByPrimaryKeySelective(SuggestInfo record);


    int updateByPrimaryKeyWithBLOBs(SuggestInfo record);


    int updateByPrimaryKey(SuggestInfo record);
}