package ci.prosuma.prosumagpv.metier.dao.mysql;

import java.util.List;

import ci.prosuma.prosumagpv.entity.PointDeVente;
import ci.prosuma.prosumagpv.entity.commande.EnteteBonCommandeFournisseur;
import ci.prosuma.prosumagpv.entity.commande.HistoriqueEnvoiEBCF;

public interface IHistoriqueEnvoiDAO {

	public HistoriqueEnvoiEBCF createOrUpdateHistoriqueEnvoiEBCF(HistoriqueEnvoiEBCF entity);

	public HistoriqueEnvoiEBCF getHistoriqueEnvoiEBCF(long entityId);

	public boolean removeHistoriqueEnvoiEBCF(HistoriqueEnvoiEBCF entity);
	
	public List<HistoriqueEnvoiEBCF> getAllHistoriqueEnvoiEBCFsForMag(PointDeVente pvt);
        
        public List<EnteteBonCommandeFournisseur> getAllEnteteBCFByHistoId(long id);


}
