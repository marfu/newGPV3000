package ci.prosuma.prosumagpv.metier.service;


public interface IUtilService {

	public boolean sendMailCBMag(String codeArticle,String pvtCode) throws Exception;
	
	public void executeUpdateSequence(String libelle,int req);
	
	public String getSqlRequestByLibelle(String libelle);

	public String getAllNewGenCodeToDay(String codeMag, String lineSeparator);

	public void executeUpdateSequence(String sql);
	
}
