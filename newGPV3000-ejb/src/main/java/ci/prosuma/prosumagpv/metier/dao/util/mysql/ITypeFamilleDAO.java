package ci.prosuma.prosumagpv.metier.dao.util.mysql;

import java.util.List;

import ci.prosuma.prosumagpv.entity.util.Famille;

public interface ITypeFamilleDAO {
	
	
	public Famille  createOrUpdateFamille(Famille ei);
	
	public Famille  getFamille(String code);
	
	public boolean removeFamille(Famille ei);
	
	public List<Famille> getAllFamille();

	public List<Famille> getAllFamilleBySecteurAndRayon(String codeRayon);
	
	


	
	

}
