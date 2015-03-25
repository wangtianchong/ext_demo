package com.lawrence.demo.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@RunWith(SpringJUnit4ClassRunner.class) // 整合 
@ContextConfiguration(locations="classpath:spring-applicationContext.xml") // 加载配置
@TransactionConfiguration(transactionManager="transactionManager")
public class TestMenuService {
    @Resource
    private MenuService menuService;
    @Test
    public void testGetMenus(){
        menuService.getMenus(null);
    }
    @Test
    public void test(){
        menuService.getChildrenMenus(null, null);
        menuService.getChildrenMenus(null, 1);
    }
}
