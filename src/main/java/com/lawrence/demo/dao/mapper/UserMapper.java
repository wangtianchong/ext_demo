package com.lawrence.demo.dao.mapper;

import java.util.List;
import java.util.Map;

import com.lawrence.demo.po.User;

public interface UserMapper extends Mapper<Integer,User>{
    long count(Map<String,Object> params);
    List<User> queryUser(Map<String,Object> params);
}
