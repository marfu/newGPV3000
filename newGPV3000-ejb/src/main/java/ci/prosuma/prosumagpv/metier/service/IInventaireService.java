package ci.prosuma.prosumagpv.metier.service;

import java.util.Date;
import java.util.List;

import ci.prosuma.prosumagpv.entity.Article;
import ci.prosuma.prosumagpv.entity.stock.DetailInventaire;
import ci.prosuma.prosumagpv.entity.stock.EnteteInventaire;

public interface IInventaireService {

	public EnteteInventaire createOrUpdateEnteteInventaire(EnteteInventaire ei);

	public EnteteInventaire getEnteteInventaire(long id);

	public EnteteInventaire getEnteteInventaireWithLazyLoad(long id);

	public boolean removeEnteteInventaire(EnteteInventaire ei);

	public List<EnteteInventaire> getAllEnteteInventaireForMag(String pvtCode);

	public List<EnteteInventaire> getAllEnteteInventaireForMagBySecteurAndRayon(String pvtCode, String codeSecteur, String codeRayon);

	public List<EnteteInventaire> getAllEnteteInventaireForMagGenerer(String pvtCode);

	public List<EnteteInventaire> getAllEnteteInventaireForMagLancer(String pvtCode);

	public List<EnteteInventaire> getAllEnteteInventaireForMagCloturer(String pvtCode);

	public List<EnteteInventaire> getAllEnteteInventaireForMagByGisement(String pvtCode, String gisementDebut, String gisementFin);

	public List<EnteteInventaire> getAllEnteteInventaireForMagByDateRange(String pvtCode, Date dateDebut, Date dateFin);

	public List<DetailInventaire> getAllDetailInvetaireForEntete(long enteteId);
	
	public List<Article> getAllArticleForEnteteInventaire(long enteteId);

	public DetailInventaire createOrUpdateDetailInventaire(DetailInventaire ei);

	public DetailInventaire getDetailInventaire(long id);

	public boolean removeDetailInventaire(DetailInventaire ei);

	public DetailInventaire getDetailForInventaireAndArticle(long enteteInv, String codeArticle);

	public List<EnteteInventaire> executeLazyQuery(String query, int first, int pageSize);

	public Long getRowCount(String query);

	public boolean uploadInventaire(EnteteInventaire enteteInv, String pvtCode);

	public List<DetailInventaire> downloadInventaire(EnteteInventaire enteteInv, String pvt);

	public Boolean checkArticleExistInInventaireOutstanding(String codeArticle,
			String pvtCode);

	public List<DetailInventaire> findAllDetailInventaireForArticle(
			String codeArticle, String pvtCode);

	public List<EnteteInventaire> getAllEnteteInventaireForMagNonCloturer(
			String pvtCode);
        
        public EnteteInventaire updateInventaire(EnteteInventaire ei);

}
