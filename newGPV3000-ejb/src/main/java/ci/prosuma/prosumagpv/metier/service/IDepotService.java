package ci.prosuma.prosumagpv.metier.service;

import java.util.List;

import ci.prosuma.prosumagpv.entity.stock.Depot;

public interface IDepotService {
	
	
	public Depot createOrUpdateDepot(Depot entity);

	public Depot getDepot(long id);


	public boolean removeDepot(Depot entity);
	
	
	public List<Depot> getAllEntrepotAS400();

}
