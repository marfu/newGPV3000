/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.dao;

import com.nov.hotel.entities.TChambreReservation;
import java.util.List;

/**
 *
 * @author manukey
 */
public interface TChambreReservationDao extends GenericDao<TChambreReservation> {

    public List<TChambreReservation> getAllChambreReservationByIdResev(long id);

    public TChambreReservation findChambreReser(long idRes, long idCh);

    public TChambreReservation findChambreOccReser(String idRes, long idCh);

    public void deleteChambreReservation(long idRes, long idCh);
}
