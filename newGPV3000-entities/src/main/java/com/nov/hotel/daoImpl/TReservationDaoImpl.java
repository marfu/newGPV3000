/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.daoImpl;

import com.nov.hotel.entities.TReservation;
import com.nov.hotel.dao.TReservationDao;
import com.nov.hotel.dto.ReservationDto;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author manukey
 */
@Stateless
public class TReservationDaoImpl extends GenericDaoImpl<TReservation> implements TReservationDao {

    public TReservationDaoImpl() {
    }

    public TReservationDaoImpl(Class<TReservation> entityClass) {
        super(entityClass);
    }

    @Override
    public TReservation createOrUpdateTReservation(TReservation u) {

        TReservation temp = getTReservation(u.getResId());
        if (temp != null) {
            em.merge(u);
            em.flush();
            return u;
        } else {
            em.persist(u);
            return u;
        }
    }

    @Override
    public TReservation getTReservation(long id) {
        try {
            TReservation u = em.find(TReservation.class, id);
//			if (u.getPvt() != null)
//				u.getPvt().size();
            return u;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<ReservationDto> getAllReservation() {

        List<ReservationDto> dtoAll = new ArrayList<ReservationDto>();

        try {
            List<Object[]> resultObject = (List<Object[]>) em.createNativeQuery("SELECT r.TRES_NUM_RESERVATION,r.TRES_DATE_CREATE,cli.TCLI_NOM,cli.TCLI_PRENOM,cli.TCLI_RAISON_SOCIALE,cli.TCLI_MSISDN,cli.TCLI_ID,r.TRES_ID,r.TRES_ETAT FROM t_reservation r,t_client cli where cli.TCLI_ID=r.TRES_CLIENT_ID").getResultList();

            for (Object[] temp : resultObject) {
                ReservationDto dto = new ReservationDto();

                dto.setNumeroReservation((temp[0]).toString());
                dto.setReservation((Date) temp[1]);

                if (temp[2] != null) {
                    dto.setNomClient((temp[2]).toString() + " " + (temp[3]).toString());
                } else {
                    dto.setNomClient((temp[4]).toString());
                }

                dto.setTelephone((temp[5]).toString());
                dto.setIdClient(((BigInteger) temp[6]).longValue());
                dto.setIdReservation(((BigInteger) temp[7]).longValue());
                dto.setStatut((temp[8]).toString());
                dtoAll.add(dto);

            }
//                edto.setDateCreation((Date) temp[0]);}
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }

        return dtoAll;
    }

    @Override
    public TReservation getLastReservation() {
        TReservation results = (TReservation) em.createQuery("SELECT cr FROM TReservation cr  ORDER BY cr.resId DESC").setMaxResults(1).getSingleResult();
        return results;
    }
}
