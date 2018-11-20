package ru.academits.dao;

import org.hibernate.criterion.Order;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Anna on 26.07.2017.
 */
public interface GenericDao<T, PK extends Serializable> {

    @Transactional
    void saveOrUpdate(T obj);

    @Transactional
    void remove(T obj);

    T getById(PK id);

    List<T> findAllByMulti(Map<String, Object> condition);

    @Transactional
    List<T> findAll();

    @Transactional
    @SuppressWarnings("unchecked")
    List<T> findAll(Order order);
}
