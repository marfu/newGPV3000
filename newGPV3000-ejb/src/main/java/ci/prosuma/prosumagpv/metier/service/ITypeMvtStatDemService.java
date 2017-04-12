package ci.prosuma.prosumagpv.metier.service;

import java.util.List;

import ci.prosuma.prosumagpv.entity.util.TypeMouvementStock;
import ci.prosuma.prosumagpv.entity.util.TypeMvtStatDem;

public interface ITypeMvtStatDemService {
	
	
	public TypeMvtStatDem  createOrUpdateTypeMvtStatDem(TypeMvtStatDem ei);
	
	public TypeMvtStatDem  getTypeMvtStatDem(long code);
	
	public boolean removeTypeMvtStatDem(TypeMvtStatDem ei);
	
	public List<TypeMvtStatDem> getAllTypeMvtStatDem(String mag );
	
	public List<TypeMvtStatDem> getAllTypeMvtStatDem(String nomColonne , String mag );

	public void process(String mag, List<TypeMouvementStock> filtreLivrer,
			List<TypeMouvementStock> filtreCasse, List<TypeMouvementStock> filtreDemarque);
	

}
