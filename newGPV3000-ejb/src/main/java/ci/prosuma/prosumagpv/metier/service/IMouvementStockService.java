package ci.prosuma.prosumagpv.metier.service;

import java.util.List;

import ci.prosuma.prosumagpv.entity.util.TypeMouvementStock;

public interface IMouvementStockService {
	
	
	public TypeMouvementStock  createOrUpdateTypeMouvementStock(TypeMouvementStock ei);
	
	public TypeMouvementStock  getTypeMouvementStock(String code);
	
	public boolean removeTypeMouvementStock(TypeMouvementStock ei);
	
	public List<TypeMouvementStock> getAllTypeMouvementStock();
	
	
	public List<TypeMouvementStock> getAllTypeMouvementStockByOrigineMouvement(String origineMouvement);

	
	

}
