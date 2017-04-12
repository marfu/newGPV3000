package ci.prosuma.metier.dataexchange.jdbc.mysql;



public interface IJDBCConnectionMySQL {

	public String getSqlRequestByLibelle(String libelle);
	
	public void executeUpdate(String codeMag);
	
	public void resetStatDem();

	public void executeUpdateSequence(String libelle,int req);

	public void resetParamStatDem();

	public void resetStatDem(String codeMag);
	
	public void addInconnuGencode(String gencode, String codeMag);

	public String getAllNewGenCodeToDay(String codeMag, String lineSeparator);

	public void executeSql(String sql);

}
