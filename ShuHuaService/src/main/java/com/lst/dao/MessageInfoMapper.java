package com.lst.dao;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lst.model.MessageInfo;

public interface MessageInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MessageInfo record);

    int insertSelective(MessageInfo record);

    MessageInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MessageInfo record);

    int updateByPrimaryKeyWithBLOBs(MessageInfo record);

    int updateByPrimaryKey(MessageInfo record);
    
	List<MessageInfo> findAll(Integer id, PageBounds page);
}