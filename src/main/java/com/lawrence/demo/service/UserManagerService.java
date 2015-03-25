package com.lawrence.demo.service;

import com.lawrence.demo.common.JsonData;
import com.lawrence.demo.common.PageParams;
import com.lawrence.demo.po.User;

public interface UserManagerService {
    JsonData userList(PageParams params,String name,String address);
    void addUser(User user);
    void deleteUser(Integer id);
    void updateUser(User user);
}
