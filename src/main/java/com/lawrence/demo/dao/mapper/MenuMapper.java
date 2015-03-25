package com.lawrence.demo.dao.mapper;

import java.util.List;
import java.util.Map;

import com.lawrence.demo.po.Menu;

public interface MenuMapper extends Mapper<Integer,Menu>{
    List<Menu> findMenuByParenId(Map<String,Object> params);
}
