package ci.prosuma.prosumagpv.metier.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import ci.prosuma.prosumagpv.entity.util.TypeMouvementStock;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeMouvementStockDAO;
import ci.prosuma.prosumagpv.metier.service.IMouvementStockService;

@Stateless
@Local(IMouvementStockService.class)
public class MouvementStockServiceImpl implements IMouvementStockService,
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4341944115119698472L;

	@EJB
	private ITypeMouvementStockDAO typeMouvementStockDAO;

	@Override
	public TypeMouvementStock createOrUpdateTypeMouvementStock(
			TypeMouvementStock ei) {
		return typeMouvementStockDAO.createOrUpdateTypeMouvementStock(ei);
	}

	@Override
	public TypeMouvementStock getTypeMouvementStock(String code) {
		return typeMouvementStockDAO.getTypeMouvementStock(code);
	}

	@Override
	public boolean removeTypeMouvementStock(TypeMouvementStock ei) {
		return typeMouvementStockDAO.removeTypeMouvementStock(ei);
	}

	@Override
	public List<TypeMouvementStock> getAllTypeMouvementStock() {
		return typeMouvementStockDAO.getAllTypeMouvementStock();
	}

	@Override
	public List<TypeMouvementStock> getAllTypeMouvementStockByOrigineMouvement(
			String origineMouvement) {
		return typeMouvementStockDAO
				.getAllTypeMouvementStockByOrigineMouvement(origineMouvement);
	}

}
