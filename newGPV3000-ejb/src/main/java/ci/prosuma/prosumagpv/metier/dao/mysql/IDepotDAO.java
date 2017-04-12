package ci.prosuma.prosumagpv.metier.dao.mysql;

import ci.prosuma.prosumagpv.entity.stock.Depot;

public interface IDepotDAO {

	public Depot createOrUpdateDepot(Depot entity);

	public Depot getDepot(long id);

        public Depot getActiveDepot();
        
	public boolean removeDepot(Depot entity);
}
