package com.lawrence.demo.common;

import java.util.List;
import java.util.Map;

public class PageParams {
    public static final String ORDER_PARAM="sort";
    public static final String ORDER_BY="dir";
    private Integer start=0;
    private Integer limit=20;
    private Integer page=1;
    private List<Map<String,String>> orders;
    public Integer getStart() {
        return start;
    }
    public void setStart(Integer start) {
        this.start = start;
    }
    public Integer getLimit() {
        return limit;
    }
    public void setLimit(Integer limit) {
        this.limit = limit;
    }
    public List<Map<String, String>> getOrders() {
        return orders;
    }
    public void setOrders(List<Map<String, String>> orders) {
        this.orders = orders;
    }
    public Integer getPage() {
        return page;
    }
    public void setPage(Integer page) {
        this.page = page;
    }
    
}
