package com.lawrence.demo.dao;

import java.util.List;

import com.lawrence.demo.common.PageParams;
import com.lawrence.demo.po.User;

public interface IUserDAO extends BaseDAO<Integer, User> {
    List<User> queryUser(String name,String address,PageParams pageParams);
    long count(String name,String address);
}
