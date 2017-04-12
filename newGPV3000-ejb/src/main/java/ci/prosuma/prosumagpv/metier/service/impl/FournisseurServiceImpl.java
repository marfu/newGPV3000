package ci.prosuma.prosumagpv.metier.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import ci.prosuma.prosumagpv.entity.Fournisseur;
import ci.prosuma.prosumagpv.entity.LienArtFour;
import ci.prosuma.prosumagpv.metier.dao.mysql.IFournisseurDAO;
import ci.prosuma.prosumagpv.metier.dao.mysql.ILienArtFourDAO;
import ci.prosuma.prosumagpv.metier.service.IFournisseurService;

@Stateless
@Local(IFournisseurService.class)
public class FournisseurServiceImpl implements IFournisseurService,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private IFournisseurDAO fournisseurDAO;

	
	
	@EJB
	private ILienArtFourDAO  lienArtFourDAO;

	@Override
	public Fournisseur createOrUpdateFournisseur(Fournisseur a) {
		return fournisseurDAO.createOrUpdateFournisseur(a);
	}

	@Override
	public Fournisseur getFournisseur(String refFournisseur) {
		return fournisseurDAO.getFournisseur(refFournisseur);
	}

	@Override
	public boolean removeFournisseur(Fournisseur entity) {
		return fournisseurDAO.removeFournisseur(entity);
	}

	@Override
	public List<Fournisseur> getAllFournisseur() {
		return fournisseurDAO.getAllFournisseur();
	}

	@Override
	public List<Fournisseur> findByRaisonSocialKeyWord(String keyword) {
		return fournisseurDAO.findByRaisonSocialKeyWord(keyword);
	}

	@Override
	public List<Fournisseur> findByRefKeyWord(String keyword) {
		return fournisseurDAO.findByRefKeyWord(keyword);
	}

	@Override
	public LienArtFour createOrUpdateLAF(LienArtFour entity) {
		return lienArtFourDAO.createOrUpdateLAF(entity);
	}

	@Override
	public LienArtFour getLAF(long id) {
		return lienArtFourDAO.getLAF(id);
	}

	@Override
	public boolean removeLAF(LienArtFour entity) {
		return lienArtFourDAO.removeLAF(entity);
	}

	@Override
	public List<Fournisseur> getAllFournisseurForArticle(String codeArticle) {
		return lienArtFourDAO.getAllFournisseurForArticle(codeArticle);
	}

	@Override
	public List<String> getAllArticleForFournisseur(String refFournisseur) {
		return lienArtFourDAO.getAllArticleForFournisseur(refFournisseur);
	}

	@Override
	public List<String> getAllArticleCommandableForFournisseur(
			String refFournisseur) {
		List<String> list =  lienArtFourDAO.getAllArticleCommandableForFournisseur(refFournisseur);
		System.out.println("lkiste lien t"+list);
		return list;
	}

	@Override
	public LienArtFour getLienArtFourByFourANdArt(String codeArticle,
			String refFournisseur) {
		return lienArtFourDAO.getLienArtFourByFourANdArt(codeArticle, refFournisseur);
	}

	@Override
	public String loadFournisseurDesignation(String refFour) {
		return fournisseurDAO.loadFournisseurDesignation(refFour);
	}

	@Override
	public List<Fournisseur> findFourByRSorRefKeyword(String keyword) {
		return fournisseurDAO.findFourByRSorRefKeyword(keyword);
	}

	@Override
	public List<Fournisseur> getAllFournisseurOrderBy() {
		return fournisseurDAO.getAllFournisseurOrderBy();
	}

}
