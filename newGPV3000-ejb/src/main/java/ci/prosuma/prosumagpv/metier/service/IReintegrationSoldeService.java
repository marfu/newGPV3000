package ci.prosuma.prosumagpv.metier.service;

import ci.prosuma.prosumagpv.entity.Article;

public interface IReintegrationSoldeService {
	
	public void process(Article selectedArticle, Article artSecondaire, float qteStock);

}
