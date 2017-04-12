package ci.prosuma.prosumagpv.metier.dao.util.mysql;

import java.util.List;

import ci.prosuma.prosumagpv.entity.Article;
import ci.prosuma.prosumagpv.entity.util.Festif;

public interface ITypeFestifDAO {
	
	public Festif createOrUpdateFestif(Festif ei);
	
	public Festif getFestif(long code);
	
	public boolean removeFestif(Festif ei);
	
	public List<Festif> getAllFestif();

	public List<Article> getAllArticleFestif(String pvtCode);

	public boolean isArticleFestif(String codeArticle, String pvtCode);

	public Festif getFestif(String codeArticle, String mag);
	
}
