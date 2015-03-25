package com.lawrence.demo.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lawrence.demo.common.PageParams;
import com.lawrence.demo.po.User;
import com.lawrence.demo.service.MenuService;
import com.lawrence.demo.service.UserManagerService;
import com.lawrence.demo.vo.TreeNode;

@Controller
public class IndexController extends BaseController{
    private static Logger log=LoggerFactory.getLogger(IndexController.class);
    @Resource
    private MenuService menuService;
    @Resource
    private UserManagerService userManagerService;
    @RequestMapping("/")
    public String index(){
        return "index";
    }
    @ResponseBody
    @RequestMapping(value="/getMenus")
    public List<TreeNode> getMenus(PageParams params,Integer id,HttpSession session){
        User user=getUser(session);
        List<TreeNode> list=null;
        try{
            list = menuService.getChildrenMenus(user, id);
        }catch(Exception e){
            log.error("getMenus exception!", e);
        }
        return list;
        
    }
}
