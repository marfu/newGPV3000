package ci.prosuma.prosumagpv.metier.dao.mysql;

import java.util.List;

import ci.prosuma.prosumagpv.entity.commande.EnteteSuggestionCommande;
import ci.prosuma.prosumagpv.entity.pk.EnteteSuggestionPK;

public interface IEnteteSuggestionCommandeDAO {

	public EnteteSuggestionCommande createOrUpdateESC(EnteteSuggestionCommande entity);

	public EnteteSuggestionCommande getESC(EnteteSuggestionPK entityId);

	public boolean removeESC(EnteteSuggestionCommande entity);

	public List<EnteteSuggestionCommande> getAllESCForMag(String mag);
        
        


}
