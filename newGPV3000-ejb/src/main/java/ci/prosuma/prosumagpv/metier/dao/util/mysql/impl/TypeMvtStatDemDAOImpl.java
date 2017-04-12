package ci.prosuma.prosumagpv.metier.dao.util.mysql.impl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ci.prosuma.prosumagpv.entity.util.TypeMvtStatDem;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeMvtStatDemDAO;

@Stateless
@Local(ITypeMvtStatDemDAO.class)
public class TypeMvtStatDemDAOImpl implements ITypeMvtStatDemDAO, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	protected EntityManager em;

	@Override
	public TypeMvtStatDem createOrUpdateTypeMvtStatDem(TypeMvtStatDem ei) {
		TypeMvtStatDem temp = getTypeMvtStatDem(ei.getId());
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
	public TypeMvtStatDem getTypeMvtStatDem(long code) {
		try {
			return em.find(TypeMvtStatDem.class, code);
		} catch (Exception e) {
			return null;
		}
	}

	/*
	 * private TypeMvtStatDem getTypeMvtStatDemForNomType(TypeMvtStatDem ei) {
	 * try { Query query = em .createQuery(
	 * "SELECT u  FROM TypeMvtStatDem u WHERE u.nomColonne=:nomCol AND u.numMagasin=:mag AND u.typeMouvement=:typeMvt"
	 * ); query.setParameter("mag", ei.getNumMagasin());
	 * query.setParameter("nomCol", ei.getNomColonne());
	 * query.setParameter("typeMvt", ei.getTypeMouvement()); return
	 * (TypeMvtStatDem) query.getSingleResult(); } catch (Exception e) { return
	 * null; } }
	 */

	@Override
	public boolean removeTypeMvtStatDem(TypeMvtStatDem ei) {
		TypeMvtStatDem a = getTypeMvtStatDem(ei.getId());
		if (a != null) {
			em.remove(a);
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TypeMvtStatDem> getAllTypeMvtStatDem(String mag) {
		Query query = em
				.createQuery("SELECT u  FROM TypeMvtStatDem u WHERE  u.numMagasin=:mag ");
		query.setParameter("mag", mag);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TypeMvtStatDem> getAllTypeMvtStatDem(String nomCol, String mag) {
		Query query = em
				.createQuery("SELECT u  FROM TypeMvtStatDem u WHERE u.nomColonne=:nomCol AND u.numMagasin=:mag ");
		query.setParameter("nomCol", nomCol);
		query.setParameter("mag", mag);
		return query.getResultList();
	}

}
