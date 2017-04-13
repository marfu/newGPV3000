/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.dao;


import com.nov.hotel.entities.TCategorieChambre;
import com.nov.hotel.entities.TOffreTarifaire;
import com.nov.hotel.entities.TTarif;





/**
 *
 * @author manukey
 */
public interface TTarifDao extends GenericDao<TTarif>{
    
    public TTarif findTarifByCatAndOffre(long idOffre,long idCat);
    public TTarif createOrUpdate(TTarif u);

    public TTarif getTTarif(long id);
   // public TTarif findOffreTarifaireByLib(TOffreTarifaire toffreTarifaire,TCategorieChambre tcategorie);
}
