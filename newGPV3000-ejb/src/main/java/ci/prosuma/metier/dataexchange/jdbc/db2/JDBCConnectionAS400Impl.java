package ci.prosuma.metier.dataexchange.jdbc.db2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import ci.prosuma.metier.dataexchange.jdbc.mysql.IJDBCConnectionMySQL;



@Stateless
@Local(IJDBCConnectionAS400.class)
public class JDBCConnectionAS400Impl implements IJDBCConnectionAS400 {
	
	
	@EJB
	private IJDBCConnectionMySQL  mySQlDao;

	String driverAS400 = null;
	String urlAS400 = null;

	public void initialize() {
		try {
			driverAS400 = mySQlDao.getSqlRequestByLibelle("as400.driver");
			urlAS400 = mySQlDao.getSqlRequestByLibelle("as400.url");
			Class.forName(driverAS400);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ResultSet executeQuery(String request) {
		initialize();
		Connection conn = null;
		Statement state = null;
		ResultSet resultQuery = null;
		try {
			conn = DriverManager.getConnection(urlAS400);
			state = conn.createStatement();
			resultQuery = state.executeQuery(request);

			return resultQuery;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int executeUpdate(String request) {
		initialize();
		Connection conn = null;
		Statement state = null;
		int resultUpdate = 0;
		try {
			conn = DriverManager.getConnection(urlAS400);
			state = conn.createStatement();

			resultUpdate = state.executeUpdate(request);

			return resultUpdate;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	


}
