package ci.prosuma.prosumagpv.metier.dao.util.mysql;

import java.util.List;

import ci.prosuma.prosumagpv.entity.util.Rayon;

public interface ITypeRayonDAO {
	
	
	public Rayon  createOrUpdateRayon(Rayon ei);
	
	public Rayon  getRayon(String code);
	
	public boolean removeRayon(Rayon ei);
	
	public List<Rayon> getAllRayon();

	public List<Rayon> getAllRayonBySecteur(String codeSecteur);
	
	


	
	

}
