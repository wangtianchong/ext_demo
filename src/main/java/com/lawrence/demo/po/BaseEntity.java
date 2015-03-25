package com.lawrence.demo.po;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public abstract class BaseEntity<ID> {
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BaseEntity) {
            BaseEntity<?> b = (BaseEntity<?>) obj;
            return b.getId().equals(this.getId());
        }
        return false;
    }

    public abstract ID getId();

    @Override
    public String toString() {

        return ReflectionToStringBuilder.toString(this);
    }
}
