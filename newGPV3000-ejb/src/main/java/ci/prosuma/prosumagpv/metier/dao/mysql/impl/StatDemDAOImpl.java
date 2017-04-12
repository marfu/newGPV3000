package ci.prosuma.prosumagpv.metier.dao.mysql.impl;

import java.io.Serializable;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ci.prosuma.prosumagpv.entity.dto.StatDem;
import ci.prosuma.prosumagpv.metier.dao.mysql.IStatDemDAO;

@Stateless
@Local(IStatDemDAO.class)
public class StatDemDAOImpl implements IStatDemDAO, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	protected EntityManager em;


	@Override
	public StatDem createOrUpdateStatDem(StatDem a) {
		try {
			//StatDem temp = getStatDem(a.getCodeArticle());
			StatDem temp = getStatDem(a.getId());
			if (temp != null) {
				em.merge(a);
				em.flush();
				return a;
			} else {
				em.persist(a);
				em.flush();
				return a;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public StatDem getStatDem(long id) {
		try {
			return em.find(StatDem.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	

}
