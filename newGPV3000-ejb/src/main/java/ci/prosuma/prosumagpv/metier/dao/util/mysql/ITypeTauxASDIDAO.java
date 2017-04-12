package ci.prosuma.prosumagpv.metier.dao.util.mysql;

import java.util.List;

import ci.prosuma.prosumagpv.entity.util.TauxASDI;

public interface ITypeTauxASDIDAO {
	
	
	public TauxASDI  createOrUpdateTauxASDI(TauxASDI ei);
	
	public TauxASDI  getTauxASDI(String code);
	
	public boolean removeTauxASDI(TauxASDI ei);
	
	public List<TauxASDI> getAllTauxASDI();
	
	


	
	

}
