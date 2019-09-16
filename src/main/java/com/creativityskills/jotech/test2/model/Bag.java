package com.creativityskills.jotech.test2.model;

import java.util.HashMap;
import java.util.Map;

public class Bag<T> {
    private Map<T, Integer> bag = new HashMap<>();

    public int getQuantity(T item) {
        Integer value = bag.get(item);
        return value == null ? 0 : value;
    }

    public Map<T, Integer> getBag() {
        return bag;
    }

    public void add(T item){
        int count = bag.get(item);
        bag.put(item, count+1);
    }

    public void deduct(T item) {
        if (hasItem(item)) {
            int count = bag.get(item);
            bag.put(item, count - 1);
        }
    }

    public boolean hasItem(T item){
        return getQuantity(item) > 0;
    }

    public void clear(){
        bag.clear();
    }

    public void put(T item, int quantity) {
        bag.put(item, quantity);
    }

}
