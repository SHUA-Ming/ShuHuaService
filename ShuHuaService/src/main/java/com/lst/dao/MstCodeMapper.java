package com.lst.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lst.model.MstCode;

public interface MstCodeMapper
{
    int deleteByPrimaryKey(Integer id);


    int insert(MstCode record);


    int insertSelective(MstCode record);


    MstCode selectByPrimaryKey(Integer id);


    int updateByPrimaryKeySelective(MstCode record);


    List<MstCode> selectmedaltype(Map<String, Object> map, PageBounds pageBounds);


    int updateByPrimaryKey(MstCode record);
}