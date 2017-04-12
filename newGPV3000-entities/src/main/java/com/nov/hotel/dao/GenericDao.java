/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.dao;

import java.util.List;

/**
 *
 * @author tagsergi
 * @param <T>
 */

public interface GenericDao<T> {
    
    public List<T> findAll();

    public T update(T entity);
	
    public T create(T entity);

    public void delete(T entity);

    public T findById(Long id);
    
    
    
    
}
