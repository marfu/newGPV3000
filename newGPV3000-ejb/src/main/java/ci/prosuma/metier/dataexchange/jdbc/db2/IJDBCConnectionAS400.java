package ci.prosuma.metier.dataexchange.jdbc.db2;

import java.sql.ResultSet;

public interface IJDBCConnectionAS400 {
	
	public ResultSet executeQuery(String request);
	
	public int executeUpdate(String request);
	

}
