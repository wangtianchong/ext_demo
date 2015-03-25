package com.lawrence.demo.dao;

import com.lawrence.demo.po.BaseEntity;



public interface BaseDAO<ID,T extends BaseEntity<ID>>{
    void add(T t);
    T findByID(ID id);
    void deleteById(ID id);
    void merge(T t);
}
