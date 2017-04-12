package ci.prosuma.prosumagpv.metier.dao.mysql;

import java.util.List;

import ci.prosuma.prosumagpv.entity.PointDeVente;
import ci.prosuma.prosumagpv.entity.stock.Depot;

public interface IPointDeVenteDAO {

	public PointDeVente createOrUpdatePVT(PointDeVente entity);

	public PointDeVente getPVT(String entity);

	public boolean removePVT(PointDeVente entity);

	public List<PointDeVente> getAllPVTActive();

	public PointDeVente getPvtByLdapOU(String ldapOU);

	public List<PointDeVente> getAllPVTDB();

	public PointDeVente getPvtByMotDirecteur(String motDirecteur);
	
	public List<Depot>  getAllDepotForMag(String pvtCode);

	public Depot getDepotPrincipalForMag(String pvtCode);

}
