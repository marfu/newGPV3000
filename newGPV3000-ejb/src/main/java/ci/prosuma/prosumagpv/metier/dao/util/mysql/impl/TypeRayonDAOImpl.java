package ci.prosuma.prosumagpv.metier.dao.util.mysql.impl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ci.prosuma.prosumagpv.entity.util.Rayon;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeRayonDAO;

@Stateless
@Local(ITypeRayonDAO.class)
public class TypeRayonDAOImpl  implements ITypeRayonDAO , Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
        @PersistenceContext
	protected EntityManager em;

	
	@Override
	public Rayon createOrUpdateRayon(Rayon ei) {
		Rayon temp = getRayon(ei.getCode());
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
	public Rayon getRayon(String code) {
		try{
		return em.find(Rayon.class, code);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean removeRayon(Rayon ei) {
		Rayon a = getRayon(ei.getCode());
		if (a != null) {
			em.remove(a);
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Rayon> getAllRayon() {
		Query query = em
				.createQuery("SELECT u  FROM Rayon u ");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Rayon> getAllRayonBySecteur(String codeSecteur) {
		Query query = em.createQuery("SELECT u  FROM Rayon u  WHERE u.secteur=:codeSecteur ");
		query.setParameter("codeSecteur", codeSecteur);
		return query.getResultList();
	}

}
