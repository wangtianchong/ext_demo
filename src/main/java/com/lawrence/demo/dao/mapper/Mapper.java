package com.lawrence.demo.dao.mapper;

import com.lawrence.demo.po.BaseEntity;

public interface Mapper<ID,T extends BaseEntity<ID>> {
    T findByID(ID id);
    void add(T t);
    void deleteById(ID id);
    void merge(T t);
}
