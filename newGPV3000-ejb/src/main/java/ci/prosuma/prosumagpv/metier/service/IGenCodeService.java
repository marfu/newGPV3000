package ci.prosuma.prosumagpv.metier.service;

import java.util.List;

import ci.prosuma.prosumagpv.entity.Article;
import ci.prosuma.prosumagpv.entity.GenCode;
import ci.prosuma.prosumagpv.entity.PointDeVente;
import ci.prosuma.prosumagpv.entity.util.EnumerationParam.CategorieGenCode;

public interface IGenCodeService {

	public GenCode createOrUpdateGenCode(GenCode entity);

	public GenCode getGenCode(long id);

	public boolean removeGenCode(GenCode entity);

	public List<GenCode> getAllGenCodeForArticle(String codeArticle,
			String pvtCode);

	public GenCode getGenCodeForArtAndGenCode(String genCode,
			String codeArticle, String pvtCode);

	public Article getArticleForGenCodeAndPvt(String gc, String pvt);

	public GenCode getGenCodeByCodeAndPVT(String gcCode, String pvt);

	public GenCode createGenCodeSecondaire(Article a, long genCode,int caractereControle, String user);
	
	//public GenCode createGenCodeSecondaire(Article a, String genCode, int caractereControle, String user);

	public GenCode createGenCodeDLV(Article a, int price, float qte,
			String user, PointDeVente pvt);

	public GenCode createGenCodeGros(Article a, int price, int prixSuggerer,
			int colissage, String user, PointDeVente pvt);

	public GenCode createGenCodeSurcond(Article a, int price, int prixSuggerer,
			int colissage, String user, PointDeVente pvt);

	public GenCode createGenCodeSousCond(Article a, int price,
			int prixSuggerer, int colissage, int proportion, String user,
			PointDeVente pvt);

	public List<GenCode> getAllGenCodeForArticleInPromo(String pvt, String promo);

	public List<GenCode> getAllGenCodeByType(CategorieGenCode cat,
			String pvtCode);

	public List<GenCode> getAllGenCodeInGisement(String pvt,
			long gisementDebut, long gisementFin);

	public List<GenCode> getAllGenCodeInSecteur(String pvt, String sec);

	public List<GenCode> getAllGenCodeInRayon(String pvt, String ray);

	public List<GenCode> getAllGenCodeInFamille(String pvt, String famille);

	public List<GenCode> getAllGenCodeInSousFamille(String pvt,
			String souFamille);

	public GenCode getFirstGenCodeForArticle(String codeArticle, String pvtCode);

	public boolean logImpression(List<GenCode> listGenCode, String typeEtiquette,
			String pvtCode, String user, String typeLog);
}
