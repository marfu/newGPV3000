package ci.prosuma.prosumagpv.metier.service;

import java.io.BufferedReader;
import java.text.ParseException;
import java.util.ArrayList;

import ci.prosuma.prosumagpv.entity.util.CategorieClient;
import ci.prosuma.prosumagpv.entity.util.ModeReglement;
import ci.prosuma.prosumagpv.entity.util.TypeDepot;
import ci.prosuma.prosumagpv.entity.util.TypeMouvementStock;

public interface IVersionService {
	
	public String parseFacture(BufferedReader br,String mag,String user);

	public boolean parseAndCreateEntity(ArrayList<String> s);

	public void updateGisement(String s, String mag);
        
        public void creationGenerique(String s, String mag);
        
        public void updateStock(String s, String mag);
        
        public void updateGencode(String s, String mag);

	public void updateSecteur(String s);

	public void updateRayon(String s);

	public void updateFamille(String s);

	public void updateSousFamille(String s);

	public void updateIFL(String s);

	public void updateTTV(String s);

	public void updateTTT(String s);

	public void updateOMV(String s);

	public TypeMouvementStock updateCMV(String s);

	public CategorieClient updateCAC(String s);

	public ModeReglement updateRGL(String s);

	public TypeDepot updateDEP(String s);

	public void updateFournisseur(String s);

	public void updateThemePromo(String s, String pvtCode) throws ParseException;

	public void updateArticle(String s, String pvt) throws Exception;

	public void updateCodeBarre(String s, String pvt) throws Exception;

	public void resetAllTablesForIntegration(String codeMag);

	public void updateLienArticleFournisseur(String s, String pvt) throws Exception;

	public boolean integreVersion(String mag) throws Exception;
	
	public void integrationFournisseurAX(String pvt);
	
	public void integrationArticlesAX(String pvt);
	
	public void integrationArticlesFournisseursAX(String pvt);
	
	public void integrationGenCodeAX(String pvt);
	
	public boolean integrationVersionAX(String mag) throws Exception;
	

}
