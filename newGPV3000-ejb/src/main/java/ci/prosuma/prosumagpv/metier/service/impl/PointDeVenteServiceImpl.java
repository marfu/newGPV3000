package ci.prosuma.prosumagpv.metier.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import ci.prosuma.metier.dataexchange.jdbc.db2.dao.IPointDeVenteAS400DAO;
import ci.prosuma.prosumagpv.entity.PointDeVente;
import ci.prosuma.prosumagpv.entity.stock.Depot;
import ci.prosuma.prosumagpv.metier.dao.mysql.IPointDeVenteDAO;
import ci.prosuma.prosumagpv.metier.service.IPointDeVenteService;

@Stateless
@Local(IPointDeVenteService.class)
public class PointDeVenteServiceImpl implements IPointDeVenteService , Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private IPointDeVenteDAO  pointDeVenteDAO;
	
	@EJB
	private IPointDeVenteAS400DAO  pointDeVenteAS400DAO;

	@Override
	public PointDeVente createOrUpdatePVT(PointDeVente entity) {
		return pointDeVenteDAO.createOrUpdatePVT(entity);
	}

	@Override
	public PointDeVente getPVT(String entity) {
		return pointDeVenteDAO.getPVT(entity);
	}

	@Override
	public boolean removePVT(PointDeVente entity) {
		return pointDeVenteDAO.removePVT(entity);
	}

	@Override
	public List<PointDeVente> getAllPVTActive() {
		return pointDeVenteDAO.getAllPVTActive();
	}

	@Override
	public PointDeVente getPvtByLdapOU(String ldapOU) {
		return pointDeVenteDAO.getPvtByLdapOU(ldapOU);
	}

	@Override
	public List<PointDeVente> getAllPVTDB() {
		return pointDeVenteDAO.getAllPVTDB();
	}

	@Override
	public PointDeVente getPvtByMotDirecteur(String motDirecteur) {
		return pointDeVenteDAO.getPvtByMotDirecteur(motDirecteur);
	}

	@Override
	public List<Depot> getAllDepotForMag(String pvtCode) {
		return pointDeVenteDAO.getAllDepotForMag(pvtCode);
	}

	@Override
	public List<PointDeVente> getAllPvtAS400() {
		return pointDeVenteAS400DAO.getAllPvtAS400();
	}

	@Override
	public Depot getDepotPrincipalForMag(String pvtCode) {
		return pointDeVenteDAO.getDepotPrincipalForMag(pvtCode);
	}

}
