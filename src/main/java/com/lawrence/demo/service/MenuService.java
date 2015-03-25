package com.lawrence.demo.service;

import java.util.List;

import com.lawrence.demo.po.User;
import com.lawrence.demo.vo.TreeNode;

public interface MenuService {
    List<TreeNode> getMenus(User user);
    List<TreeNode> getChildrenMenus(User user,Integer parentId);
}
