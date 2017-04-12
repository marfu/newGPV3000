/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.daoImpl;



import com.nov.hotel.dao.GenericDao;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author tagsergi
 * @param <T>
 */
public class GenericDaoImpl<T> implements GenericDao<T> {

    @PersistenceContext(unitName = "hotelNovPu")
    protected EntityManager em;

    private Class<T> entityClass;

    public Class<T> getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public GenericDaoImpl() {
    }

    public GenericDaoImpl(Class<T> entityClass) {
        //  this.entityClass = entityClass;

        this.entityClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public List<T> findAll() {
        this.entityClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
        //System.out.print(entityClass.getName());
        //return em.createQuery("SELECT e FROM Region e").getResultList();
        return em.createQuery("SELECT e FROM " + getEntityClass().getName() + " e").getResultList();
    }

    @Override
    public T update(T entity) {
        return em.merge(entity);
    }

    @Override
    public T create(T entity) {
        em.persist(entity);
        em.flush();
        return entity;
    }

    @Override
    public void delete(T entity) {
        em.remove(entity);
    }

    @Override
    public T findById(Long id) {
        this.entityClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
        return em.find(getEntityClass(), id);
    }

}
