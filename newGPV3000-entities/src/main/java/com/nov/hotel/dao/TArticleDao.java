/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.dao;


import com.nov.hotel.entities.TArticle;

import java.util.List;





/**
 *
 * @author manukey
 */
public interface TArticleDao extends GenericDao<TArticle>{
    public List<TArticle> findServiceParent();
    public List<TArticle> findServiceIntermediaire(long id);
    public List<TArticle> findServiceTerminal(long id);
      public TArticle createOrUpdate(TArticle u); 
      
       public TArticle findByLibArticle(String u);         
     public TArticle getTArticle(long id);
}
