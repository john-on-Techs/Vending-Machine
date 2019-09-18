package com.creativityskills.jotech.bean;

import javax.ejb.Local;
import javax.ejb.Stateless;

@Local
@Stateless
public class Bean<T> implements BeanI<T> {
    @Override
    public T create(T t) {
        return null;
    }

    @Override
    public T read(long id) {
        return null;
    }

    @Override
    public T update(T t) {
        return null;
    }

    @Override
    public boolean delete(T t) {
        return false;
    }
}
