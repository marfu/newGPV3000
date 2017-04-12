package ci.prosuma.prosumagpv.metier.dao.util.mysql;

import java.util.List;

import ci.prosuma.prosumagpv.entity.util.TypeMvtStatDem;

public interface ITypeMvtStatDemDAO {
	
	
	public TypeMvtStatDem  createOrUpdateTypeMvtStatDem(TypeMvtStatDem ei);
	
	public TypeMvtStatDem  getTypeMvtStatDem(long code);
	
	public boolean removeTypeMvtStatDem(TypeMvtStatDem ei);
	
	public List<TypeMvtStatDem> getAllTypeMvtStatDem( String mag );
	
	public List<TypeMvtStatDem> getAllTypeMvtStatDem(String nomColonne, String mag );

}
