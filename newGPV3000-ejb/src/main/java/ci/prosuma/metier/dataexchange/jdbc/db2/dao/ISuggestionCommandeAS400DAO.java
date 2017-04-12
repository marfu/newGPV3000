package ci.prosuma.metier.dataexchange.jdbc.db2.dao;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import ci.prosuma.prosumagpv.entity.commande.DetailSuggestionCommande;
import ci.prosuma.prosumagpv.entity.commande.EnteteSuggestionCommande;

public interface ISuggestionCommandeAS400DAO {
	
	public List<EnteteSuggestionCommande> getAllSuggestionDeCommandeAvailable(String mag);
	
	
	public List<DetailSuggestionCommande> getDetailForCommandeAS400(String numDossier,String mag);

	public ResultSet InitCursorEnteteSuggestionCommande(String mag);
	
	public EnteteSuggestionCommande nextEnteteSuggestionCommande(String mag, ResultSet cursor);
	
	public Map<String,EnteteSuggestionCommande> allOneEnteteSuggestionCommande(String mag);
}
