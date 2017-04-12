package ci.prosuma.metier.dataexchange.jdbc.mssql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import ci.prosuma.metier.dataexchange.jdbc.mysql.IJDBCConnectionMySQL;

@Stateless
@Local(IJDBCConnectionMSSQL.class)
public class JDBCConnectionMSSQLImpl implements IJDBCConnectionMSSQL {

	String driverMSSQL = null;
	String urlMSSQL = null;
	Connection conn = null;


	@EJB
	private IJDBCConnectionMySQL mySQlDao;

	public void initialize(String ipServeur) {
		try {
			System.out.println("INITIALIZE JDBCConnectionMSSQL");
			driverMSSQL = mySQlDao.getSqlRequestByLibelle("mssql.driver");
			urlMSSQL = mySQlDao.getSqlRequestByLibelle("mssql.url");
			Class.forName(driverMSSQL);
			System.out.println("INITIALIZE JDBCConnectionMSSQL class for name ");
			conn = DriverManager.getConnection(urlMSSQL.replaceAll("ipServeurCaisse", ipServeur));
			
			System.out.println("INITIALIZE JDBCConnectionMSSQL getConnection");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ResultSet executeQuery(String ipServeur, String request) {

		if (conn == null) {
			initialize(ipServeur);
		}

		Statement state = null;
		ResultSet resultQuery = null;
		try {

			state = conn.createStatement();
			resultQuery = state.executeQuery(request);

			return resultQuery;
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			
		}
		return null;
	}

	@Override
	public int executeUpdate(String ipServeur, String request) {
		if (conn == null) {
			initialize(ipServeur);
		}
		Statement state = null;
		int resultUpdate = 0;
		try {
			state = conn.createStatement();

			resultUpdate = state.executeUpdate(request);

			return resultUpdate;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int[] executeBatch(String ipServeur, String request,
			List<String> listSQLBatch) {
		if (conn == null) {
			initialize(ipServeur);
		}
		Statement state = null;
		try {
			conn.setAutoCommit(false);
			state = conn.createStatement();

			for (String s : listSQLBatch) {
				state.addBatch(s);
			}

			return state.executeBatch();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

}
