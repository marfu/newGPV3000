package ci.prosuma.metier.dataexchange.jdbc.db2.dao;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import ci.prosuma.metier.dataexchange.jdbc.db2.IJDBCConnectionAS400;
import ci.prosuma.metier.dataexchange.jdbc.mysql.IJDBCConnectionMySQL;
import ci.prosuma.prosumagpv.entity.PointDeVente;

@Stateless
@Local(IPointDeVenteAS400DAO.class)
public class PointDeVenteAS400DAOImpl implements IPointDeVenteAS400DAO , Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private IJDBCConnectionAS400 jdbcAS400Connection;

	@EJB
	private IJDBCConnectionMySQL jdbcMySQLConnection;
	

	@Override
	public List<PointDeVente> getAllPvtAS400() {
		try {
			ResultSet rs = jdbcAS400Connection.executeQuery(jdbcMySQLConnection
					.getSqlRequestByLibelle("pos.select.all"));
			List<PointDeVente> pvtList = new ArrayList<PointDeVente>();
			while (rs.next()) {
				PointDeVente pvt = new PointDeVente();
				pvt.setPvtCode(rs.getString("CIDIPN"));
				pvt.setMotDirecteur(rs.getString("MDIRPN"));
				pvt.setRaisonSocialFournisseur(rs.getString("RSOCPN"));
				pvt.setAdresse1(rs.getString("ADR1PN"));
				pvt.setAdresse2(rs.getString("ADR2PN"));
				pvt.setCodePostal(rs.getString("CPOSPN"));
				pvt.setVille(rs.getString("VILLPN"));
				pvt.setPays(rs.getString("CPAYPN"));
				pvt.setPhoneNumber(rs.getString("NTELPN"));
				pvt.setFaxNumber(rs.getString("NTEFPN"));
				pvt.setNumGerant(rs.getString("NGERPN"));
				pvt.setNomGerant(rs.getString("NOMGPN"));
				pvt.setGestionPrixVente(rs.getString("ORDIPN").equals("1") ? true
						: false);
				pvt.setActif(rs.getString("CSTVPN").equals("AC") ? true : false);
				pvt.setTypePointDeVente(rs.getString("TYPVPN"));
				pvtList.add(pvt);

			}
			return pvtList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	

}
