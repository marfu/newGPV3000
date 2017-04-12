package ci.prosuma.prosumagpv.metier.dao.util.mysql;

import java.util.List;

import ci.prosuma.prosumagpv.entity.util.OrigineMouvement;

public interface ITypeOrigineMouvementDAO {
	
	
	public OrigineMouvement  createOrUpdateOrigineMouvement(OrigineMouvement ei);
	
	public OrigineMouvement  getOrigineMouvement(String code);
	
	public boolean removeOrigineMouvement(OrigineMouvement ei);
	
	public List<OrigineMouvement> getAllOrigineMouvement();
	
	


	
	

}
