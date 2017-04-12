package ci.prosuma.prosumagpv.metier.service;

import java.sql.Connection;

public interface IWinstore  {
	
	public void uploadToTPV(String ip,String pvtCode) ;
	
	public void uploadToTPVReset(String ip,String pvtCode) ;

	public String downoadFromTPV(String  pvt,String ip,String date) throws Exception ;
	
	public void uploadArtToTPV(String codeArt , String pvtCode , String ip,Connection conn);
	
	public void uploadUpdateToTPV(String pvtCode,String ip);
	
	public void uploadPromoToTPV(String pvtCode, String ip,String promo);
	public void uploadArticleToTPV(String s , String pvtCode, String ip);
	
//	public void getAllClientFromWinstore(String pvtCode,String ip);
	
//	public void downloadTicketFromTPV(String mag,String ip ) throws Exception;
	
}
