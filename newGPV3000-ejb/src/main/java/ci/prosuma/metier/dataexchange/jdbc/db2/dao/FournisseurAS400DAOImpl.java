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
import ci.prosuma.prosumagpv.entity.Fournisseur;

@Stateless
@Local(IFournisseurAS400DAO.class)
public class FournisseurAS400DAOImpl  implements IFournisseurAS400DAO , Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private IJDBCConnectionAS400 jdbcAS400Connection;

	@EJB
	private IJDBCConnectionMySQL jdbcMySQLConnection;
	
	
	

	@Override
	public List<Fournisseur> getAllFournisseurAS400() {
		try {
			ResultSet rs = jdbcAS400Connection.executeQuery(jdbcMySQLConnection
					.getSqlRequestByLibelle("fournisseur.select.all"));
			List<Fournisseur> fourList = new ArrayList<Fournisseur>();
			while (rs.next()) {
				Fournisseur vf = new Fournisseur();
				vf.setAdresse(rs.getString("ADR1FO") + "  "
						+ rs.getString("ADR2FO"));
				vf.setBp(rs.getString("CPOSFO"));
				vf.setContact(rs.getString("NMCOFO"));
				vf.setEmail(getEmailByFournisseurAS400(rs.getString("NFOUFO")));
				vf.setEnable(true);

				if (rs.getString("FODIFO").equals("1")) {
					vf.setLivraisonDirecte(true);
				}
				if (rs.getString("FOCEFO").equals("1")) {
					vf.setLivraisonCentrale(true);
				}

				vf.setModeReglement(rs.getString("CREGFO"));
				vf.setMotDirecteur(rs.getString("MDIRFO"));
				vf.setNumCell(rs.getString("NTELFO"));
				vf.setNumFax(rs.getString("NTEFFO"));
				vf.setNumTel(rs.getString("NTELFO"));
				vf.setPays(rs.getString("CPAYFO"));
				vf.setRaisonSocial(rs.getString("RSOCFO"));
				vf.setRefFournisseur(rs.getString("NFOUFO"));
				vf.setRegistreCommerce(rs.getString("RCOMFO"));
				vf.setRaisonSocial(rs.getString("NMCOFO"));
				vf.setVille(rs.getString("VILLFO"));
				fourList.add(vf);

			}
			return fourList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}



	@Override
	public String getEmailByFournisseurAS400(String refFour) {
		String email = null;
		String query = jdbcMySQLConnection
				.getSqlRequestByLibelle("fournisseur.select.email");

		try {
			ResultSet rs = jdbcAS400Connection.executeQuery(query.replaceAll(
					":numFour", refFour));

			while (rs.next()) {
				email = rs.getString("EMAIED");

			}

		} catch (Exception e) {
			return email;
		}
		return email;
	}
}
