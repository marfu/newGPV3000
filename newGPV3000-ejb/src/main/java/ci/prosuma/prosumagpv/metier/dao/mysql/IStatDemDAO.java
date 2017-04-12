package ci.prosuma.prosumagpv.metier.dao.mysql;

import ci.prosuma.prosumagpv.entity.dto.StatDem;

public interface IStatDemDAO {

	public StatDem createOrUpdateStatDem(StatDem a);
	
	public StatDem getStatDem(long id);

	

}
