package com.lawrence.demo.controller;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp")
@ContextHierarchy({ @ContextConfiguration(name = "parent", locations = "classpath:spring-applicationContext.xml"),
        @ContextConfiguration(name = "child", locations = "classpath:spring-mvc.xml") })
public class TestUserManagerController {
    @Resource
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testTest1() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/grid/test1")).andDo(MockMvcResultHandlers.print()).andReturn();
        String viewName = result.getModelAndView().getViewName();
        Assert.assertTrue("userManager".equals(viewName));
        
    }

    @Test
    public void testUsersList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/grid/usersList").param("name", " 小明")
                .param("address", "杭州").param("start", "0").param("limit", "1"))
                .andExpect(MockMvcResultMatchers.handler().handlerType(UserManagerController.class))
                .andExpect(MockMvcResultMatchers.handler().methodName("userList"))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
    }
    @Test
    public void testAddUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/grid/addUser").param("name", " 小明")
                .param("address", "杭州").param("mail", "xiaoming@xxx.com"))
                .andExpect(MockMvcResultMatchers.handler().handlerType(UserManagerController.class))
                .andExpect(MockMvcResultMatchers.handler().methodName("addUser"))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
    }
    @Test
    public void testDeleteUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/grid/deleteUser").param("id", "5"))
                .andExpect(MockMvcResultMatchers.handler().handlerType(UserManagerController.class))
                .andExpect(MockMvcResultMatchers.handler().methodName("deleteUser"))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
    }
    @Test
    public void testUpdateUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/grid/updateUser").param("id", "5")
                .param("name", " 小明").param("address", "杭州").param("mail", "xiaoming@xxx.com"))
                .andExpect(MockMvcResultMatchers.handler().handlerType(UserManagerController.class))
                .andExpect(MockMvcResultMatchers.handler().methodName("updateUser"))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
    }
    
}
