package ci.prosuma.prosumagpv.metier.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import ci.prosuma.prosumagpv.entity.util.OrigineMouvement;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeOrigineMouvementDAO;
import ci.prosuma.prosumagpv.metier.service.IOrigineMouvementService;

@Stateless
@Local(IOrigineMouvementService.class)
public class OrigineMouvementServiceImpl  implements IOrigineMouvementService , Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4343568232395931848L;
	
	@EJB
	private ITypeOrigineMouvementDAO typeOrigineMouvementDAO;

	@Override
	public OrigineMouvement createOrUpdateOrigineMouvement(OrigineMouvement ei) {
		return typeOrigineMouvementDAO.createOrUpdateOrigineMouvement(ei);
	}

	@Override
	public OrigineMouvement getOrigineMouvement(String code) {
		return typeOrigineMouvementDAO.getOrigineMouvement(code);
	}

	@Override
	public boolean removeOrigineMouvement(OrigineMouvement ei) {
		return typeOrigineMouvementDAO.removeOrigineMouvement(ei);
	}

	@Override
	public List<OrigineMouvement> getAllOrigineMouvement() {
		return typeOrigineMouvementDAO.getAllOrigineMouvement();
	}

	
}
