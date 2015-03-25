package com.lawrence.demo.common;

import java.util.List;
import java.util.Map;

public class JsonData {
    private List<Map<String,Object>> data;
    private boolean success;
    private String message;
    private String errorMsg;
    private long totalCount;
    public static JsonData getJsonData(List<Map<String,Object>> data){
        JsonData jd=new JsonData();
        jd.setData(data);
        jd.setSuccess(true);
        return jd;
    }
    public List<Map<String, Object>> getData() {
        return data;
    }
    public void setData(List<Map<String, Object>> data) {
        this.data = data;
    }
    public boolean isSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getErrorMsg() {
        return errorMsg;
    }
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
    public long getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }
    
}
