/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.daoImpl;




import com.nov.hotel.entities.TChambreReservation;
import com.nov.hotel.dao.TChambreReservationDao;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author manukey
 */
@Stateless
public class TChambreReservationDaoImpl extends GenericDaoImpl<TChambreReservation> implements TChambreReservationDao{
    
    public TChambreReservationDaoImpl() {
    }
    public TChambreReservationDaoImpl(Class<TChambreReservation> entityClass) {
        super(entityClass);
    }

    @Override
    public List<TChambreReservation> getAllChambreReservationByIdResev(long id) {
        
        List<TChambreReservation> results = em.createQuery("SELECT cr FROM TChambreReservation cr where cr.reservation.resId="+id).getResultList();

        return results;
    }

    @Override
    public TChambreReservation findChambreReser(long idRes, long idCh) {

       TChambreReservation results = (TChambreReservation) em.createQuery("SELECT cr FROM TChambreReservation cr where cr.reservation.resId="+idRes+" and cr.chambre.chId="+idCh).getSingleResult();

        return results;    }

    @Override
    public void deleteChambreReservation(long idRes, long idCh) {
        em.createNativeQuery("DELETE FROM t_ch_reservation WHERE TCH_RES_CHAMBRE="+idCh+" AND TCH_RES_RESERVATION="+idRes).executeUpdate();
    }

    @Override
    public TChambreReservation findChambreOccReser(String idRes, long idCh) {
       TChambreReservation results = (TChambreReservation) em.createQuery("SELECT cr FROM TChambreReservation cr where cr.reservation.resNumReservation = '"+idRes+"' and cr.chambre.chId = "+idCh).getSingleResult();

        return results;    
}
    
    
}
