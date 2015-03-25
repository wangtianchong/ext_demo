package com.lawrence.demo.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.lawrence.demo.po.User;


public abstract class BaseController {
    public static final String USER="user";
    public static final String IS_SUCCESS="success";
    public static final String MESSAGE="message";
    public static final String ERR_MESSAGE="errorMsg";
    public User getUser(HttpSession session){
        Object obj=session.getAttribute(USER);
        if(obj instanceof User&&obj!=null){
            return (User)obj;
        }
        return null;
    }
    
    public void setSuccess(Map<String,Object> map,boolean success){
        map.put(IS_SUCCESS, success);
    }
    public void setErrorMessage(Map<String,Object> map,String errmsg){
        map.put(ERR_MESSAGE, errmsg);
    }
    public void setMessage(Map<String,Object> map,String msg){
        map.put(MESSAGE, msg);
    }
}
