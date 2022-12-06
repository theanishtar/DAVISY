package com.dao;

import java.util.List;

public abstract class DAVISY<E, K> {

    abstract public void insert(E entity);

    abstract public void update(E entity);

    abstract public void delete(K key);
    abstract public void delete2(K key1,K key2);

    abstract public List<E> selectAll();

    abstract public E selectById(K key);

    abstract protected List<E> selectBySql(String sql, Object... args);
}
