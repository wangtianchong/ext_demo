package com.lawrence.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lawrence.demo.dao.impl.MenuDAO;
import com.lawrence.demo.po.Menu;
import com.lawrence.demo.po.User;
import com.lawrence.demo.service.MenuService;
import com.lawrence.demo.vo.TreeNode;

@Service
public class MenuServiceImpl implements MenuService {
    @Resource
    private MenuDAO menuDAO;

    @Override
    public List<TreeNode> getMenus(User user) {
        List<Menu> menus = menuDAO.getMainMenu();
        return toTreeNodeList(menus);
    }

    @Override
    public List<TreeNode> getChildrenMenus(User user, Integer parentId) {
        List<Menu> m = menuDAO.getChildrenMenu(parentId);
        return toTreeNodeList(m);
    }

    private List<TreeNode> toTreeNodeList(List<Menu> menus) {
        List<TreeNode> tn = new ArrayList<TreeNode>(menus.size());
        for (Menu menu : menus) {
            TreeNode node = new TreeNode();
            node.setExpanded(false);
            node.setLeaf(menu.getLeaf());
            node.setText(menu.getName());
            node.setId(menu.getId().toString());
            node.setUrl(menu.getUrl());
            tn.add(node);
        }
        return tn;
    }

}
