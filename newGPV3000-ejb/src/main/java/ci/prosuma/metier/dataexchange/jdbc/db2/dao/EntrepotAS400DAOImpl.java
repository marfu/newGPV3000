package ci.prosuma.metier.dataexchange.jdbc.db2.dao;
//package ci.prosuma.dataexchange.jdbc.db2.dao;
//
//import java.io.Serializable;
//import java.sql.ResultSet;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.ejb.EJB;
//import javax.ejb.Local;
//import javax.ejb.Stateless;
//
//import ci.prosuma.dataexchange.jdbc.db2.IJDBCConnectionAS400;
//import ci.prosuma.prosumagpv.entity.stock.Depot;
//import ci.prosuma.prosumagpv.metier.dao.jdbc.IJDBCMySQLConnection;
//
//@Stateless
//@Local(IEntrepotAS400DAO.class)
//public class EntrepotAS400DAOImpl implements IEntrepotAS400DAO , Serializable {
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//
//	@EJB(name = "GPV3000Ear/JDBCConnectionAS400Impl/local")
//	private IJDBCConnectionAS400 jdbcAS400Connection;
//
//	@EJB(name = "GPV3000Ear/JDBCConnectionMySQLImpl/local")
//	private IJDBCMySQLConnection jdbcMySQLConnection;
//
//	public List<Depot> getAllEntrepotAS400() {
//		try {
//			ResultSet rs = jdbcAS400Connection.executeQuery(jdbcMySQLConnection.getSqlRequestByLibelle("entrepot.select.all"));
//			List<Depot> entList = new ArrayList<Depot>();
//			while (rs.next()) {
//				Depot ve = new Depot();
//				ve.setCode((rs.getString("NENTEN")));
//				ve.setLibelle((rs.getString("LENTEN")));
//				entList.add(ve);
//
//			}
//			return entList;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//}
