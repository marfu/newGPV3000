package ci.prosuma.prosumagpv.metier.dao.util.mysql;

import java.util.List;

import ci.prosuma.prosumagpv.entity.util.TypeDepot;

public interface ITypeDepotDAO {
	
	
	public TypeDepot  createOrUpdateTypeDepot(TypeDepot ei);
	
	public TypeDepot  getTypeDepot(String code);
	
	public boolean removeTypeDepot(TypeDepot ei);
	
	public List<TypeDepot> getAllTypeDepot();
	
	


	
	

}
