package com.lawrence.demo.po;

public class Menu extends BaseEntity<Integer>{
    private Integer id;
    private String name;
    private Integer parentId;
    private Boolean leaf;
    private String url;
    
    public Boolean getLeaf() {
        return leaf;
    }
    public void setLeaf(Boolean leaf) {
        this.leaf = leaf;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    @Override
    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getParentId() {
        return parentId;
    }
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    
    
}
