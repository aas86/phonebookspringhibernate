package ru.academits.dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.academits.controller.PhoneBookController;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Anna on 26.07.2017.
 */
@Transactional
public class GenericDaoImpl<T, PK extends Serializable> implements GenericDao<T, PK>{
    private static final Logger logger = LoggerFactory.getLogger(PhoneBookController.class);

    @Autowired
    private SessionFactory sessionFactory;

    protected Class<T> clazz;

    public GenericDaoImpl(Class<T> type) {
        this.clazz = type;
    }

    @Transactional
    @Override
    public void saveOrUpdate(T obj) {
        sessionFactory.getCurrentSession().saveOrUpdate(obj);

    }

    @Override
    public void remove(T obj) {
        sessionFactory.getCurrentSession().delete(obj);
    }


    @Override
    public T getById(PK id) {
        return (T) sessionFactory.getCurrentSession().get(clazz, id);
    }


    @Transactional
    @SuppressWarnings("unchecked")
    @Override
    public List<T> findAllByMulti(Map<String, Object> condition) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(clazz);
        condition.forEach((k, v) -> {
            if(k != null) {
                criteria.add(Restrictions.eq(k, v));
            }
        });
        return (List<T>) criteria.list();
    }

    @Transactional
    @Override
    public List<T> findAll() {
        return findAll(null);
    }

    @Transactional
    @SuppressWarnings("unchecked")
    @Override
    public List<T> findAll(Order order) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(clazz);
        if (order != null) {
            criteria.addOrder(order);
        }
        List<T> list =(List<T>) criteria.list();
        return list;
    }

}
