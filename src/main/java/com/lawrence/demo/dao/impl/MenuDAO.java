package com.lawrence.demo.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.lawrence.demo.dao.IMenuDAO;
import com.lawrence.demo.dao.mapper.MenuMapper;
import com.lawrence.demo.po.Menu;
@Repository
public class MenuDAO implements IMenuDAO {
    @Resource
    private MenuMapper menuMapper;
    @Override
    public void add(Menu t) {
        menuMapper.add(t);
    }

    @Override
    public Menu findByID(Integer id) {
        return menuMapper.findByID(id);
    }

    @Override
    public void deleteById(Integer id) {
        menuMapper.deleteById(id);
    }

    @Override
    public void merge(Menu t) {
        menuMapper.merge(t);
    }
    @Override
    public List<Menu> getChildrenMenu(Integer id){ 
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("parentId", id);
        List<Map<String,String>> orders=new ArrayList<Map<String,String>>();
        Map<String,String> order=new HashMap<String,String>(); 
        order.put("sort", "id");
        order.put("desc", "asc");
        orders.add(order);
        params.put("orders", orders);
        return menuMapper.findMenuByParenId(params);
    }

    @Override
    public List<Menu> getMainMenu() {
        
        return menuMapper.findMenuByParenId(null);
    }
    
}
