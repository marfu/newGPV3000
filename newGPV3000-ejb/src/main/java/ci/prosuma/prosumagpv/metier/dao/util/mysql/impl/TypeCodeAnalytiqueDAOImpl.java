package ci.prosuma.prosumagpv.metier.dao.util.mysql.impl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ci.prosuma.prosumagpv.entity.util.CodeAnalytique;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeCodeAnalytiqueDAO;

@Stateless
@Local(ITypeCodeAnalytiqueDAO.class)
public class TypeCodeAnalytiqueDAOImpl  implements ITypeCodeAnalytiqueDAO , Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	protected EntityManager em;

	
	@Override
	public CodeAnalytique createOrUpdateCodeAnalytique(CodeAnalytique ei) {
		CodeAnalytique temp = getCodeAnalytique(ei.getCodeAnalytique());
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
	public CodeAnalytique getCodeAnalytique(String code) {
		try{
		return em.find(CodeAnalytique.class, code);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean removeCodeAnalytique(CodeAnalytique ei) {
		CodeAnalytique a = getCodeAnalytique(ei.getCodeAnalytique());
		if (a != null) {
			em.remove(a);
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CodeAnalytique> getAllCodeAnalytique() {
		Query query = em
				.createQuery("SELECT u  FROM CodeAnalytique u ");
		return query.getResultList();
	}

}
