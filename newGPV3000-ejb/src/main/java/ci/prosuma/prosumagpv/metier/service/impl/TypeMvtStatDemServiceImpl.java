package ci.prosuma.prosumagpv.metier.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.transaction.UserTransaction;

import ci.prosuma.metier.dataexchange.jdbc.mysql.IJDBCConnectionMySQL;
import ci.prosuma.prosumagpv.entity.util.TypeMouvementStock;
import ci.prosuma.prosumagpv.entity.util.TypeMvtStatDem;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeMvtStatDemDAO;
import ci.prosuma.prosumagpv.metier.service.ITypeMvtStatDemService;

@Stateless
@Local(ITypeMvtStatDemService.class)
@TransactionManagement(TransactionManagementType.BEAN)
public class TypeMvtStatDemServiceImpl implements ITypeMvtStatDemService,
		Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6132075617525662995L;
	
	
	@EJB
	private ITypeMvtStatDemDAO typeMvtStatDemDAO;

	@Resource
	public UserTransaction tx;

	@EJB
	public IJDBCConnectionMySQL mySql;

	@Override
	public TypeMvtStatDem createOrUpdateTypeMvtStatDem(TypeMvtStatDem ei) {
		return typeMvtStatDemDAO.createOrUpdateTypeMvtStatDem(ei);
	}


	@Override
	public TypeMvtStatDem getTypeMvtStatDem(long code) {
		return typeMvtStatDemDAO.getTypeMvtStatDem(code);
	}


	@Override
	public boolean removeTypeMvtStatDem(TypeMvtStatDem ei) {
		return typeMvtStatDemDAO.removeTypeMvtStatDem(ei);
	}


	@Override
	public List<TypeMvtStatDem> getAllTypeMvtStatDem(String mag ) {
		return typeMvtStatDemDAO.getAllTypeMvtStatDem(mag);
	}


	@Override
	public List<TypeMvtStatDem> getAllTypeMvtStatDem(String nomColonne, String mag ) {
		return typeMvtStatDemDAO.getAllTypeMvtStatDem(nomColonne, mag );
	}


	@Override
	public void process(String mag, List<TypeMouvementStock> filtreLivrer,
			List<TypeMouvementStock> filtreCasse, List<TypeMouvementStock> filtreDemarque) {

		resetTable(mag);
		
		for (TypeMouvementStock livree : filtreLivrer) {
			TypeMvtStatDem tmp = new TypeMvtStatDem();
			tmp.setNomColonne("qteLivrer");
			tmp.setTypeMouvement(livree.getCode());
			tmp.setNumMagasin(mag);
			createOrUpdateTypeMvtStatDem(tmp);
		}
		
		for (TypeMouvementStock casse : filtreCasse) {
			TypeMvtStatDem tmp = new TypeMvtStatDem();
			tmp.setNomColonne("qteCasse");
			tmp.setTypeMouvement(casse.getCode());
			tmp.setNumMagasin(mag);
			createOrUpdateTypeMvtStatDem(tmp);
		}
		
		for (TypeMouvementStock demarque : filtreDemarque) {
			TypeMvtStatDem tmp = new TypeMvtStatDem();
			tmp.setNomColonne("qteDemarque");
			tmp.setTypeMouvement(demarque.getCode());
			tmp.setNumMagasin(mag);
			createOrUpdateTypeMvtStatDem(tmp);
		}
		
	}
	
	private void resetTable(String mag) {
		List<TypeMvtStatDem> listToRemove = getAllTypeMvtStatDem(mag);
		for (TypeMvtStatDem typeMvtStatDem : listToRemove) {
			removeTypeMvtStatDem(typeMvtStatDem);
		}
		/*
		try {
			tx.begin();
			mySql.resetParamStatDem();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
	}

}
