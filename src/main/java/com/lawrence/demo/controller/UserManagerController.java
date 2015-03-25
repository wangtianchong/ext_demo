package com.lawrence.demo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lawrence.demo.common.JsonData;
import com.lawrence.demo.common.PageParams;
import com.lawrence.demo.po.User;
import com.lawrence.demo.service.UserManagerService;

@Controller
@RequestMapping("/grid")
public class UserManagerController extends BaseController{
    private static final Logger log=LoggerFactory.getLogger(UserManagerController.class);
    @Resource
    private UserManagerService userManagerService;
    @RequestMapping("test1")
    public String test1(){
        return "userManager";
    }
    @RequestMapping(value="/usersList",method=RequestMethod.POST)
    @ResponseBody
    public JsonData userList(PageParams params,String name,String address){
        JsonData jd;
        try{
            jd=userManagerService.userList(params, name, address);
        }catch(Exception e){
            jd=new JsonData();
            log.error("userList exception!", e);
            jd.setSuccess(false);
            jd.setErrorMsg(e.getMessage());
            jd.setMessage("操作失败!");
        }
        return jd;
    }
    @RequestMapping(value="/addUser",method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addUser(User user){
        Map<String,Object> result=new HashMap<String,Object>();
        try {
            userManagerService.addUser(user);
            setSuccess(result, true);
            setMessage(result, "新增用户信息成功!");
        } catch (Exception e) {
            log.error("addUser exception!",e);
            setSuccess(result, false);
            setMessage(result, "新增用户信息失败!");
            setErrorMessage(result, e.getMessage());
        }
        return result;
    }
    @RequestMapping(value="/deleteUser",method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> deleteUser(Integer id){
        Map<String,Object> result=new HashMap<String,Object>();
        try {
            userManagerService.deleteUser(id);
            setSuccess(result, true);
            setMessage(result, "删除用户信息成功!");
        } catch (Exception e) {
            log.error("deleteUser exception!",e);
            setSuccess(result, false);
            setMessage(result, "删除用户信息失败!");
            setErrorMessage(result, e.getMessage());
        }
        return result;
    }
    @RequestMapping(value="/updateUser",method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> updateUser(User user){
        Map<String,Object> result=new HashMap<String,Object>();
        try {
            userManagerService.updateUser(user);
            setSuccess(result, true);
            setMessage(result, "更新用户信息成功!");
        } catch (Exception e) {
            log.error("deleteUser exception!",e);
            setSuccess(result, false);
            setMessage(result, "更新用户信息失败!");
            setErrorMessage(result, e.getMessage());
        }
        return result;
    }
}
