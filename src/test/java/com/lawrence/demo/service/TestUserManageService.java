package com.lawrence.demo.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lawrence.demo.common.JsonData;
import com.lawrence.demo.common.PageParams;
import com.lawrence.demo.po.User;

@RunWith(SpringJUnit4ClassRunner.class) // 整合 
@ContextConfiguration(locations="classpath:spring-applicationContext.xml") // 加载配置
public class TestUserManageService {
    @Resource
    private UserManagerService userManagerService;
    @Test(expected  =  Exception. class )
    public void testAddInvalidUser(){
        User u=new User();
        userManagerService.addUser(u);
    }
    @Test
    public void test(){
        JsonData jd=userManagerService.userList(new PageParams(), null, null);
        if(jd.getTotalCount()>0){
            List<Map<String,Object>> list=jd.getData();
            Assert.assertTrue(jd.getTotalCount()>=list.size());
            Assert.assertTrue(list.size()>0);
            Map<String,Object> obj=list.get(0);
            Integer id=(Integer) obj.get("id");
            String name=(String) obj.get("name");
            User user=new User();
            user.setId(id);
            user.setName(name);
            user.setMail("---");
            userManagerService.updateUser(user);
            userManagerService.deleteUser(user.getId());
            user.setMail(obj.get("mail").toString());
            user.setAddress(obj.get("address").toString());
            userManagerService.addUser(user);
           
        }
    }
}
