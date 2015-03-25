package com.lawrence.demo.po;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public abstract class BaseEntityID extends BaseEntity<BaseEntityID>{

    @Override
    public BaseEntityID getId() {
        return this;
    }
    @Override
    public String toString() {

        return ReflectionToStringBuilder.toString(this);
    }
    @Override
    public boolean equals(Object obj) {
        
        return this.hashCode()==obj.hashCode();
    }
    
}
