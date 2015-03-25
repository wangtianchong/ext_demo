package com.lawrence.demo.dao;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.lawrence.demo.common.PageParams;
import com.lawrence.demo.po.User;
@RunWith(SpringJUnit4ClassRunner.class) // 整合 
@ContextConfiguration(locations="classpath:spring-applicationContext.xml") // 加载配置
@TransactionConfiguration(transactionManager="transactionManager")
public class TestUserDAO {
    @Resource
    private IUserDAO userDAO;
    @Test
    @Rollback(true)
    @Transactional
    public void testAddUser() {
        User user=new User();
        user.setAddress("杭州市西湖区");
        user.setMail("user@xxx.com");
        user.setName("userxxx");
        userDAO.add(user);
    }
    @Rollback(true)
    @Transactional
    @Test
    public void testModUser() {
        User user=new User();
        user.setAddress("杭州市西湖区");
        user.setMail("user@xxx.com");
        user.setName("userxxx");
        userDAO.add(user);
        user.setName("userxxx1111");
        userDAO.merge(user);
    }
    @Rollback(true)
    @Transactional
    @Test
    public void testDeleteUser() {
        User user=new User();
        user.setAddress("杭州市西湖区");
        user.setMail("user@xxx.com");
        user.setName("userxxx");
        userDAO.add(user);
        userDAO.deleteById(user.getId());
    }
    @Rollback(true)
    @Transactional
    @Test
    public void testQueryUser(){
        User user=new User();
        user.setAddress("杭州市西湖区");
        user.setMail("user@xxx.com");
        user.setName("userxxx");
        userDAO.add(user);
        User u2=userDAO.findByID(user.getId());
        Assert.assertTrue(user.equals(u2));
        long l=userDAO.count(null, null);
        Assert.assertTrue(l>0);
        PageParams params=new PageParams();
        List<User> list=userDAO.queryUser(null, null,params);
        Assert.assertTrue(l>=list.size());
    }
    
}
