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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.lawrence.demo.common.PageParams;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp")
@ContextHierarchy({ @ContextConfiguration(name = "parent", locations = "classpath:spring-applicationContext.xml"),
        @ContextConfiguration(name = "child", locations = "classpath:spring-mvc.xml") })
public class TestIndexController {
    @Resource
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testIndex() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/")).andDo(MockMvcResultHandlers.print()).andReturn();
        String viewName = result.getModelAndView().getViewName();
        Assert.assertTrue("index".equals(viewName));
    }

    @Test
    public void testGetMenus() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/getMenus", new PageParams(), null))
                .andDo(MockMvcResultHandlers.print()).andReturn();

    }
}
