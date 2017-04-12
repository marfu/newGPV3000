package ci.prosuma.prosumagpv.metier.dao.mysql.impl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ci.prosuma.prosumagpv.entity.PointDeVente;
import ci.prosuma.prosumagpv.entity.commande.EnteteBonCommandeFournisseur;
import ci.prosuma.prosumagpv.entity.commande.HistoriqueEnvoiEBCF;
import ci.prosuma.prosumagpv.metier.dao.mysql.IHistoriqueEnvoiDAO;

@Stateless
@Local(IHistoriqueEnvoiDAO.class)
public class HistoriqueEnvoiDAOImpl implements IHistoriqueEnvoiDAO,
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	protected EntityManager em;

	@Override
	public HistoriqueEnvoiEBCF createOrUpdateHistoriqueEnvoiEBCF(HistoriqueEnvoiEBCF a) {
		HistoriqueEnvoiEBCF temp = getHistoriqueEnvoiEBCF(a.getId());
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
	public HistoriqueEnvoiEBCF getHistoriqueEnvoiEBCF(long entityId) {
		HistoriqueEnvoiEBCF ebcf = em.find(HistoriqueEnvoiEBCF.class, entityId);
		if(ebcf != null && ebcf.getListEntete() != null)
			ebcf.getListEntete().size();
		return ebcf;
	}

	@Override
	public boolean removeHistoriqueEnvoiEBCF(HistoriqueEnvoiEBCF entity) {
		HistoriqueEnvoiEBCF a = getHistoriqueEnvoiEBCF(entity.getId());
		if (a != null) {
			em.remove(a);
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HistoriqueEnvoiEBCF> getAllHistoriqueEnvoiEBCFsForMag( PointDeVente pvt) {
		Query  q = em.createQuery("SELECT h FROM HistoriqueEnvoiEBCF h WHERE h.pvt.pvtCode=:code ORDER BY h.dateTransmission DESC");
		q.setParameter("code", pvt.getPvtCode());
		
		return  q.getResultList();
	}

    @Override
    public List<EnteteBonCommandeFournisseur> getAllEnteteBCFByHistoId(long id) {
        Query  q = em.createNativeQuery("SELECT e.* from BON_COMMANDE_FOURNISSEUR_ENTETE e join lien_historique_bcf lbcf on lbcf.listEntete=e.NUMERO_CMD "
                + "join HISTORIQUE_EBCF hbcf on hbcf.HISTO_EBCF_PK=lbcf.HistoriqueEnvoiEBCF_HISTO_EBCF_PK "
                + " Where hbcf.HISTO_EBCF_PK=:id",EnteteBonCommandeFournisseur.class);
		q.setParameter("id", id);
		return  q.getResultList();
    }



	

}
