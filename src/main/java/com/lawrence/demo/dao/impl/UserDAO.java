package com.lawrence.demo.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawrence.demo.common.PageParams;
import com.lawrence.demo.dao.IUserDAO;
import com.lawrence.demo.dao.mapper.UserMapper;
import com.lawrence.demo.po.User;
@Repository
public class UserDAO implements IUserDAO {
    @Autowired
    private UserMapper userMapper;

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public void add(User t) {
        userMapper.add(t);
    }

    @Override
    public User findByID(Integer id) {
        return userMapper.findByID(id);
    }

    @Override
    public void deleteById(Integer id) {
        userMapper.deleteById(id);
    }

    @Override
    public void merge(User t) {
        userMapper.merge(t);
    }

    @Override
    public List<User> queryUser(String name, String address, PageParams pageParams) {
        Map<String,Object> map=new HashMap<String,Object>();
//        Integer start=(pageParams.getPage()-1)
        map.put("name", name);
        map.put("address", address);
        map.put("orders", pageParams.getOrders());
        map.put("start", pageParams.getStart());
        map.put("limit",pageParams.getLimit());
        return userMapper.queryUser(map);
    }

    @Override
    public long count(String name, String address) {
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("name", name);
        map.put("address", address);
        return userMapper.count(map);
    }

}
