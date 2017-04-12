package ci.prosuma.prosumagpv.metier.dao.util.mysql;

import java.util.List;

import ci.prosuma.prosumagpv.entity.util.Secteur;

public interface ITypeSecteurDAO {
	
	
	public Secteur  createOrUpdateSecteur(Secteur ei);
	
	public Secteur  getSecteur(String code);
	
	public boolean removeSecteur(Secteur ei);
	
	public List<Secteur> getAllSecteur();
	
	


	
	

}
