package ci.prosuma.prosumagpv.metier.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import ci.prosuma.prosumagpv.entity.PointDeVente;
import ci.prosuma.prosumagpv.entity.commande.EnteteBonCommandeFournisseur;
import ci.prosuma.prosumagpv.entity.commande.HistoriqueEnvoiEBCF;
import ci.prosuma.prosumagpv.metier.dao.mysql.IHistoriqueEnvoiDAO;
import ci.prosuma.prosumagpv.metier.service.IHistoriqueEnvoiService;


@Stateless
@Local(IHistoriqueEnvoiService.class)
public class HistoriqueEnvoiServiceImpl implements IHistoriqueEnvoiService , Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private IHistoriqueEnvoiDAO  historiqueEnvoiDAO;

	@Override
	public HistoriqueEnvoiEBCF createOrUpdateHistoriqueEnvoiEBCF(HistoriqueEnvoiEBCF entity) {
		return historiqueEnvoiDAO.createOrUpdateHistoriqueEnvoiEBCF(entity);
	}

	@Override
	public HistoriqueEnvoiEBCF getHistoriqueEnvoiEBCF(long entityId) {
		return historiqueEnvoiDAO.getHistoriqueEnvoiEBCF(entityId);
	}

	@Override
	public boolean removeHistoriqueEnvoiEBCF(HistoriqueEnvoiEBCF entity) {
		return historiqueEnvoiDAO.removeHistoriqueEnvoiEBCF(entity);
	}

	@Override
	public List<HistoriqueEnvoiEBCF> getAllHistoriqueEnvoiEBCFsForMag(
			PointDeVente pvt) {
		return historiqueEnvoiDAO.getAllHistoriqueEnvoiEBCFsForMag(pvt);
	}

    @Override
    public List<EnteteBonCommandeFournisseur> getAllEnteteBCFByHistoId(long id) {
        return historiqueEnvoiDAO.getAllEnteteBCFByHistoId(id);
    }


}
