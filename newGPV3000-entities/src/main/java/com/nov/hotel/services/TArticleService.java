/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.services;



import com.nov.hotel.dao.TArticleDao;
import com.nov.hotel.entities.TArticle;


import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author manukey
 */
@Stateless
public class TArticleService {

    @EJB
    private TArticleDao tArticleDao;


   

    public List<TArticle> listTArticle() {
        return tArticleDao.findAll();

    }

   
    
    public TArticle CreerOrUpdate(TArticle c) {
        
      return tArticleDao.createOrUpdate(c);
       
    }
    public TArticle findArticlebyID(long ID) {
        
      return tArticleDao.findById(ID);
       
    }
    public TArticle findByLibArticle(String ID) {
        
      return tArticleDao.findByLibArticle(ID);
       
    }
    

    public void deleteTService() {

    }

   
}
