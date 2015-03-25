package com.lawrence.demo.dao;

import java.util.List;

import com.lawrence.demo.po.Menu;

public interface IMenuDAO extends BaseDAO<Integer,Menu>{
    List<Menu> getChildrenMenu(Integer id);
    List<Menu> getMainMenu();
}
