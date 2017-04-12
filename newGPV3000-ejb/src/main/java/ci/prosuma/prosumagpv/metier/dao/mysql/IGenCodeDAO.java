package ci.prosuma.prosumagpv.metier.dao.mysql;

import java.util.List;

import ci.prosuma.prosumagpv.entity.Article;
import ci.prosuma.prosumagpv.entity.GenCode;
import ci.prosuma.prosumagpv.entity.dto.GenCodeDTO;
import ci.prosuma.prosumagpv.entity.util.EnumerationParam.CategorieGenCode;

public interface IGenCodeDAO {

	public GenCode createOrUpdateGenCode(GenCode entity);
        
        public List<GenCode> getAllGenCodeByCodeAndPVT(String gcCode, String pvt);
	
	public GenCode updateGenCode(GenCode entity);
	
	public GenCode createGenCode(GenCode entity);

	public GenCode getGenCode(long id);
	
	public void removeBarCode(String code);

	public boolean removeGenCode(GenCode entity);

	public List<GenCode> getAllGenCodeForArticle(String codeArticle, String pvtCode);
        
        public List<GenCodeDTO> getAllGenCodeDTOForArticle(String codeArticle, String pvtCode);

	public List<GenCode> getAllGenCodeByType(CategorieGenCode cat, String pvtCode);

	public GenCode getGenCodeForArtAndGenCode(String genCode, String codeArticle, String pvtCode);

	public Article getArticleForGenCodeAndPvt(String gc, String pvt);

	public GenCode getGenCodeByCodeAndPVT(String gcCode, String pvt);

	public List<GenCode> getAllGenCodeForArticleInPromo(String pvt, String promo);
	
	public List<GenCode> getAllGenCodeInGisement(String pvt, long gisementDebut , long gisementFin);
	
	public List<GenCode> getAllGenCodeInSecteur(String pvt, String sec);
	public List<GenCode> getAllGenCodeInRayon(String pvt, String ray);
	public List<GenCode> getAllGenCodeInFamille(String pvt, String famille);
	public List<GenCode> getAllGenCodeInSousFamille(String pvt,String souFamille);

	GenCode getFirstGenCodeForArticle(String codeArticle, String pvtCode);
        
        
        public List<GenCode> getAllGenCodeForArticleWithoutFetch(String codeArticle,String pvtCode);

}
