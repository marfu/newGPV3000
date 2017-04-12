package ci.prosuma.prosumagpv.metier.dao.util.mysql;

import java.util.List;

import ci.prosuma.prosumagpv.entity.util.SousFamille;

public interface ITypeSousFamilleDAO {
	
	
	public SousFamille  createOrUpdateSousFamille(SousFamille ei);
	
	public SousFamille  getSousFamille(String code);
	
	public boolean removeSousFamille(SousFamille ei);
	
	public List<SousFamille> getAllSousFamille();

	public List<SousFamille> getAllSousFamilleBySecteurAndRayonAndFamille( String codeFamille);
	
	


	
	

}
