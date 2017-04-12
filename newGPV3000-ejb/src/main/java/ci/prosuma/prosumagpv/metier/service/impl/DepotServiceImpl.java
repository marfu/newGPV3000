package ci.prosuma.prosumagpv.metier.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import ci.prosuma.prosumagpv.entity.stock.Depot;
import ci.prosuma.prosumagpv.metier.dao.mysql.IDepotDAO;
import ci.prosuma.prosumagpv.metier.dao.mysql.IPointDeVenteDAO;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeDepotDAO;
import ci.prosuma.prosumagpv.metier.service.IDepotService;

@Stateless
@Local(IDepotService.class)
public class DepotServiceImpl implements IDepotService , Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	@EJB
//	IEntrepotAS400DAO entrepotAS400DAO;
	
	@EJB
	IPointDeVenteDAO  pvtDAO;
	
	@EJB 
	IDepotDAO  depotDAO;
	
	@EJB
	IPointDeVenteDAO pointDeVenteDAO;
	
	@EJB 
	ITypeDepotDAO  typeDepotDAO;

	
	
	
	
	

	@Override
	public Depot createOrUpdateDepot(Depot entity) {
		return depotDAO.createOrUpdateDepot(entity);
	}

	@Override
	public Depot getDepot(long id) {
		return depotDAO.getDepot(id);
	}

	

	@Override
	public boolean removeDepot(Depot entity) {
		return depotDAO.removeDepot(entity);
	}

	@Override
	public List<Depot> getAllEntrepotAS400() {
	return null;
		//	return entrepotAS400DAO.getAllEntrepotAS400();
	}

}
