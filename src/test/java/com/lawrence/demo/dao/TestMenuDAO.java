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

import com.lawrence.demo.po.Menu;
@RunWith(SpringJUnit4ClassRunner.class) // 整合 
@ContextConfiguration(locations="classpath:spring-applicationContext.xml") // 加载配置
@TransactionConfiguration(transactionManager="transactionManager")
public class TestMenuDAO {
    @Resource
    private IMenuDAO menuDAO;
    @Test
    @Transactional
    @Rollback(true)
    public void testAddMenu() {
        Menu m=new Menu();
        m.setLeaf(false);
        m.setName("test");
        m.setParentId(null);
        m.setUrl(null);
        menuDAO.add(m);
        Assert.assertNotNull(m.getId());
    }
    @Test
    @Transactional
    @Rollback(true)
    public void testDeleteMenu(){
        Menu m=new Menu();
        m.setLeaf(false);
        m.setName("test");
        m.setParentId(null);
        m.setUrl(null);
        menuDAO.add(m);
        menuDAO.deleteById(m.getId());
        Assert.assertNotNull(m.getId());
        Menu m1=menuDAO.findByID(m.getId());
        Assert.assertNull(m1);
    }
    @Test
    @Transactional
    @Rollback(true)
    public void testMergeMenu(){
        Menu m=new Menu();
        m.setLeaf(false);
        m.setName("test");
        m.setParentId(null);
        m.setUrl(null);
        menuDAO.add(m);
        m.setLeaf(true);
        menuDAO.merge(m);
        Assert.assertTrue(menuDAO.findByID(m.getId()).getLeaf()==m.getLeaf());
    }
    @Test
    @Transactional
    @Rollback(true)
    public void testGetMainMenu(){
        List<Menu> list1=menuDAO.getMainMenu();
        if(list1.size()>0){
            for(Menu m:list1){
                Assert.assertTrue(m.getParentId()==null);
            }
        }
    }
    @Test
    @Transactional
    @Rollback(true)
    public void testGetChildrenMenu(){
        Menu m=new Menu();
        m.setLeaf(false);
        m.setName("test");
        m.setParentId(null);
        m.setUrl(null);
        menuDAO.add(m);
        Menu c1=new Menu();
        c1.setLeaf(true);
        c1.setName("child1");
        c1.setParentId(m.getId());
        c1.setUrl("/c1");
        Menu c2=new Menu();
        c2.setLeaf(true);
        c2.setName("child2");
        c2.setParentId(m.getId());
        c2.setUrl("/c2");
        menuDAO.add(c1);
        menuDAO.add(c2);
        List<Menu> list=menuDAO.getChildrenMenu(m.getId());
        Assert.assertTrue(list.size()==2);
        Assert.assertTrue(list.get(0).equals(c1));
        Assert.assertTrue(list.get(1).equals(c2));
    }
}
