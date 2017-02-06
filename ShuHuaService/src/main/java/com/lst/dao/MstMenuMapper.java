package com.lst.dao;

import java.util.List;

import com.lst.model.MstMenu;

public interface MstMenuMapper
{
    int deleteByPrimaryKey(Integer id);


    int insert(MstMenu record);


    int insertSelective(MstMenu record);


    MstMenu selectByPrimaryKey(Integer id);


    List<MstMenu> selectMenu();


    int updateByPrimaryKeySelective(MstMenu record);


    int updateByPrimaryKey(MstMenu record);
}