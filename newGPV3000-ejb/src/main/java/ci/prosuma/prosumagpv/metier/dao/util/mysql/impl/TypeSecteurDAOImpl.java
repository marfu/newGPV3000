package ci.prosuma.prosumagpv.metier.dao.util.mysql.impl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ci.prosuma.prosumagpv.entity.util.Secteur;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeSecteurDAO;

@Stateless
@Local(ITypeSecteurDAO.class)
public class TypeSecteurDAOImpl  implements ITypeSecteurDAO , Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
        @PersistenceContext
        protected EntityManager em;

	
	@Override
	public Secteur createOrUpdateSecteur(Secteur ei) {
		System.out.println( "########  | secteur : "+ei.toString());
		Secteur temp = getSecteur(ei.getCode());
		if (temp != null) {
			em.merge(ei);
			em.flush();
			return ei;
		} else {
			em.persist(ei);
			em.flush();
			return ei;
		}
	}

	@Override
	public Secteur getSecteur(String code) {
		try{
		return em.find(Secteur.class, code);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean removeSecteur(Secteur ei) {
		Secteur a = getSecteur(ei.getCode());
		if (a != null) {
			em.remove(a);
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Secteur> getAllSecteur() {
		Query query = em.createQuery("SELECT u  FROM Secteur u ");
		
		List<Secteur> list = query.getResultList();
		System.out.println("&&&&&&&&&&&&&&&&&   | list : "+list.toString());
		return list;
	}

}
