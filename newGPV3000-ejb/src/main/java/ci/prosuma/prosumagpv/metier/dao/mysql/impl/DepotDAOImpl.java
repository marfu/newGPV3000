package ci.prosuma.prosumagpv.metier.dao.mysql.impl;

import java.io.Serializable;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ci.prosuma.prosumagpv.entity.stock.Depot;
import ci.prosuma.prosumagpv.metier.dao.mysql.IDepotDAO;
import javax.persistence.Query;

@Stateless
@Local(IDepotDAO.class)
public class DepotDAOImpl implements IDepotDAO , Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@PersistenceContext
	protected EntityManager em;

	
	@Override
	public Depot createOrUpdateDepot(Depot a) {
		Depot temp = getDepot(a.getId());
		if (temp != null) {
			em.merge(a);
			em.flush();
			return a;
		} else {
			em.persist(a);
			return a;
		}
	}

	@Override
	public Depot getDepot(long id) {
		try{
		return em.find(Depot.class, id);
	} catch (Exception e) {
		return null;
	}
	}

	
	

	@Override
	public boolean removeDepot(Depot entity) {
		Depot a = getDepot(entity.getId());
		if(a != null){
			em.remove(a);
			return true;
		}else{
			return false;
		}
	}

    @Override
    public Depot getActiveDepot() {
        Query query = em.createQuery("SELECT d from Depot d where d.actif=TRUE");
        return (Depot) query.getResultList().get(0);
    }

	
	

}
