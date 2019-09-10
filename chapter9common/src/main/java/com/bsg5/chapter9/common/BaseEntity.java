package com.bsg5.chapter9.common;

public interface BaseEntity<ID> {
    ID getId();

    void setId(ID id);
}
