package ci.prosuma.prosumagpv.metier.service;

import java.util.List;

import ci.prosuma.prosumagpv.entity.util.OrigineMouvement;

public interface IOrigineMouvementService {
	
	
	public OrigineMouvement  createOrUpdateOrigineMouvement(OrigineMouvement ei);
	
	public OrigineMouvement  getOrigineMouvement(String code);
	
	public boolean removeOrigineMouvement(OrigineMouvement ei);
	
	public List<OrigineMouvement> getAllOrigineMouvement();

}
