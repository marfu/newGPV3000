package ci.prosuma.prosumagpv.metier.dao.mysql;

import ci.prosuma.prosumagpv.entity.commande.DetailSuggestionCommande;
import ci.prosuma.prosumagpv.entity.commande.EnteteSuggestionCommande;
import java.util.List;

public interface IDetailSuggestionCommandeDAO {

	public DetailSuggestionCommande createOrUpdateDSC(DetailSuggestionCommande entity);

	public DetailSuggestionCommande getDSC(long entityId);

	public boolean removeDSC(DetailSuggestionCommande entity);
        
        public List<DetailSuggestionCommande> getAllDetailSugestionWithquantityByEnteteSug(EnteteSuggestionCommande entete);

	
}
