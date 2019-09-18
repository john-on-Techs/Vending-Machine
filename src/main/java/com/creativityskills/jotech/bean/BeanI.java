package com.creativityskills.jotech.bean;

public interface BeanI<T> {
    T create(T t);
    T read(long id);
    T update(T t);
    boolean delete(T t);
}
