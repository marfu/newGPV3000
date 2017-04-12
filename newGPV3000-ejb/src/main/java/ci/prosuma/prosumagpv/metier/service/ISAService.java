package ci.prosuma.prosumagpv.metier.service;

import java.util.List;

import ci.prosuma.prosumagpv.entity.Article;
import ci.prosuma.prosumagpv.entity.Promo;


public interface ISAService  {
	
	
	
	public void uploadToTPVAll(String ip, String pvtCode) ;
	
	public void majTENDV();
	
	public void uploadToTPVArticle(String codeArticle,String ip, String pvtCode) ;
	
	public void uploadToTPVListArticle(List<Article> listArticles,String ip, String pvtCode) ;

	public void uploadToTPVPromo(String promo, String pvtCode);
	
	public void uploadToTPVListPromo(List<Promo> listPromo, String pvtCode);
	
	public void uploadToTPVUpdate(String ip, String pvtCode);
		
	public String downoadFromTPV(String mag, String ip, String date) throws Exception ;

	public void beginToTPVUpdate(String ip, String pvtCode);
	
	public void beginToTPVUpdateAuto(String ip, String pvtCode);
	
	public void generateMAJART(List<Article> listArticles, String ip,String pvtCode);
	
	public void generateAllArticle(String pvtCode);
	

}
