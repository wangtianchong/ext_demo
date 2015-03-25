package com.lawrence.demo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lawrence.demo.common.JsonData;
import com.lawrence.demo.common.PageParams;
import com.lawrence.demo.dao.IUserDAO;
import com.lawrence.demo.po.User;
import com.lawrence.demo.service.UserManagerService;

@Service
public class UserManagerServiceImpl implements UserManagerService{
    @Autowired
    private IUserDAO userDAO;
    
    @Override
    public JsonData userList(PageParams params, String name, String address) {
        List<User> users=userDAO.queryUser(name, address, params);
        long count=userDAO.count(name, address);
        List<Map<String,Object>> data=new ArrayList<Map<String,Object>>();
        for(User user:users){
            Map<String,Object> map=new HashMap<String,Object>(3);
            map.put("name", user.getName());
            map.put("address", user.getAddress());
            map.put("mail", user.getMail());
            map.put("id", user.getId());
            data.add(map);
        }
        JsonData jd=JsonData.getJsonData(data);
        jd.setTotalCount(count);
        return jd;
    }
    @Transactional(rollbackFor=java.lang.Exception.class,propagation=Propagation.REQUIRED)
    public void addUser(User user){
        userDAO.add(user);
    }
    @Transactional(rollbackFor=java.lang.Exception.class,propagation=Propagation.REQUIRED)
    @Override
    public void deleteUser(Integer id) {
        User u=userDAO.findByID(id);
        if(u==null)
            throw new RuntimeException("该用户已不存在!");
        userDAO.deleteById(id);
        
    }
    @Transactional(rollbackFor=java.lang.Exception.class,propagation=Propagation.REQUIRED)
    @Override
    public void updateUser(User user) {
        User u=userDAO.findByID(user.getId());
        if(u==null)
            throw new RuntimeException("该用户已不存在!");
        u.setAddress(user.getAddress());
        u.setName(user.getName());
        u.setMail(user.getMail());
        userDAO.merge(u);
        
    }
    
}
