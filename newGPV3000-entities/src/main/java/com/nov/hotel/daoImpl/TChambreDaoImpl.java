/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.daoImpl;

import com.nov.hotel.entities.TChambre;
import com.nov.hotel.dao.TChambreDao;
import com.nov.hotel.dto.InfoChambreDto;
import com.nov.hotel.entities.EtatChambreEnum;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
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
public class TChambreDaoImpl extends GenericDaoImpl<TChambre> implements TChambreDao {

    public TChambreDaoImpl() {
    }

    public TChambreDaoImpl(Class<TChambre> entityClass) {
        super(entityClass);
    }

    @Override
    public InfoChambreDto findDetailsChambre(long id, Date deb, Date fin) {
        System.out.println("Id xxxx" + id);

        String newstring = new SimpleDateFormat("yyyy-MM-dd").format(deb);
        String newstring2 = new SimpleDateFormat("yyyy-MM-dd").format(fin);

        InfoChambreDto dto = new InfoChambreDto();
        int totalLibre = 0;
        int totalOccupe = 0;
        int totalReserve = 0;
        int totalhorsService = 0;
        int total = 0;

        try {
//            List<Object[]> resultObject = (List<Object[]>) em.createNativeQuery("SELECT COUNT(`tch_etat`) AS nbre, `tch_etat` AS etat FROM t_chambre c WHERE c.tch_categorie =" + id + " GROUP BY  `tch_etat`").getResultList();
            List<Object[]> resultObject = (List<Object[]>) em.createNativeQuery("SELECT COUNT(  `tch_etat` ) AS nbre,  `tch_etat` AS etat FROM t_chambre c LEFT JOIN t_occupation o ON o.tocc_ch_id = c.tch_id and o.TOCC_DATE_ARR > " + newstring + " and o.TOCC_DATE_DEP  < " + newstring2 + " WHERE c.tch_categorie = " + id).getResultList();

            for (Object[] temp : resultObject) {
                if (temp[1] != null) {
                    String valueType = temp[1].toString();
                    if (valueType.equals("LIBRE")) {
                        // totalLibre = (int) temp[0];
                        totalLibre = ((BigInteger) temp[0]).intValue();

                    }
                    if (valueType.equals("OCCUPEE")) {
                        // totalOccupe = (int) temp[0];
                        totalOccupe = ((BigInteger) temp[0]).intValue();

                    }

                    if (valueType.equals("RESERVEE")) {
                        // totalReserve = (int) temp[0];
                        totalReserve = ((BigInteger) temp[0]).intValue();
                    }
                    if (valueType.equals("HORS_SERVICE")) {
                        //totalhorsService = (int) temp[0];
                        totalhorsService = ((BigInteger) temp[0]).intValue();

                    }
                    total = totalLibre + totalOccupe + totalReserve + totalhorsService;
                    //InfoChambreDto edto = new InfoChambreDto();
                    //System.out.println(temp);
                }
//                edto.setDateCreation((Date) temp[0]);}
            }
            dto.setTotalChambre(total);
            dto.setTotalChambreLibre(totalLibre);
            dto.setTotalChambreReserve(totalReserve);
            dto.setTotalChambreOccupe(totalOccupe);

        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(dto.toString());
        return dto;

    }

    @Override
    public List<TChambre> findDetailsChambreByEtatLibre(EtatChambreEnum etat) {
        List<TChambre> results = em.createQuery("SELECT u FROM TChambre u where u.etat=:code").setParameter("code", etat).getResultList();

        return results;
    }

    
    
     @Override
    public TChambre createOrUpdate(TChambre u) {
        
        TChambre temp = getChambre(u.getChId());
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
	public TChambre getChambre(long id) {
		try {
			TChambre u = em.find(TChambre.class, id);
//			if (u.getPvt() != null)
//				u.getPvt().size();
			return u;
		} catch (Exception e) {
			return null;
		}
	}
}
