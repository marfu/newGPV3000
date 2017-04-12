package ci.prosuma.prosumagpv.metier.dao.util.mysql;

import java.util.List;

import ci.prosuma.prosumagpv.entity.util.TauxTVA;

public interface ITypeTauxTVADAO {
	
	
	public TauxTVA  createOrUpdateTauxTVA(TauxTVA ei);
	
	public TauxTVA  getTauxTVA(String code);
	
	public boolean removeTauxTVA(TauxTVA ei);
	
	public List<TauxTVA> getAllTauxTVA();
	
	


	
	

}
