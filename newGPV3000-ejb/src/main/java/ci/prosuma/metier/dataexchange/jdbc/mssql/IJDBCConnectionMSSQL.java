package ci.prosuma.metier.dataexchange.jdbc.mssql;

import java.sql.ResultSet;

public interface IJDBCConnectionMSSQL {
	
	public ResultSet executeQuery(String ipServeur ,String request);
	
	public int executeUpdate(String ipServeur , String request);
	
	

}
