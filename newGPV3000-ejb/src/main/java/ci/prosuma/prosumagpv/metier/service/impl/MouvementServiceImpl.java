package ci.prosuma.prosumagpv.metier.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import ci.prosuma.prosumagpv.entity.stock.DetailMouvement;
import ci.prosuma.prosumagpv.entity.stock.EnteteMouvement;
import ci.prosuma.prosumagpv.metier.dao.mysql.IArticleDAO;
import ci.prosuma.prosumagpv.metier.dao.mysql.IDetailMouvementDAO;
import ci.prosuma.prosumagpv.metier.dao.mysql.IEnteteMouvementDAO;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeTauxTVADAO;
import ci.prosuma.prosumagpv.metier.service.IMouvementService;

@Stateless
@Local(IMouvementService.class)
public class MouvementServiceImpl implements IMouvementService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private IArticleDAO articleDAO;

	@EJB
	public ITypeTauxTVADAO tauxTVADAO;

	@EJB
	private IEnteteMouvementDAO enteteMouvementDAO;

	@EJB
	private IDetailMouvementDAO detailMouvementDAO;

	@Override
	public EnteteMouvement createOrUpdateEnteteMouvement(EnteteMouvement ei) {
		return enteteMouvementDAO.createOrUpdateEnteteMouvement(ei);
	}

	@Override
	public EnteteMouvement getEnteteMouvement(long id) {
		return enteteMouvementDAO.getEnteteMouvement(id);
	}

	@Override
	public boolean removeEnteteMouvement(EnteteMouvement ei) {
		return enteteMouvementDAO.removeEnteteMouvement(ei);
	}

	@Override
	public List<EnteteMouvement> getAllEnteteMouvementForMag(String pvtCode) {
		return enteteMouvementDAO.getAllEnteteMouvementForMag(pvtCode);
	}

	@Override
	public List<EnteteMouvement> getAllEnteteMouvementForMagByOrigineMouvement(
			String pvtCode, String codeOrigine) {
		return enteteMouvementDAO
				.getAllEnteteMouvementForMagByOrigineMouvement(pvtCode,
						codeOrigine);
	}

	@Override
	public DetailMouvement createOrUpdateDetailMouvement(DetailMouvement ei) {
		return detailMouvementDAO.createOrUpdateDetailMouvement(ei);
	}

	@Override
	public DetailMouvement getDetailMouvement(long id) {
		return detailMouvementDAO.getDetailMouvement(id);
	}

	@Override
	public boolean removeDetailMouvement(DetailMouvement ei) {
		return detailMouvementDAO.removeDetailMouvement(ei);
	}

	@Override
	public List<EnteteMouvement> executeQuery(String s) {
		return enteteMouvementDAO.executeQuery(s);
	}

	@Override
	public List<DetailMouvement> executeQueryForDetail(String s) {
		return enteteMouvementDAO.executeQueryForDetail(s);
	}

	@Override
	public List<DetailMouvement> getAllDetailMouvementForEntete(long enteteId) {
		return detailMouvementDAO.getAllDetailMouvementForEntete(enteteId);
	}

	@Override
	public String createMouvementReceptionFromFact(String codeMag,
			List<String> lineList, String user) {
		return enteteMouvementDAO.createMouvementReceptionFromFact(codeMag,
				lineList, user);
	}

	@Override
	public List<EnteteMouvement> executeLazyQuery(String query, int first,
			int pageSize) {
		return enteteMouvementDAO.executeLazyQuery(query, first, pageSize);
	}

	@Override
	public Long getRowCount(String query) {
		return enteteMouvementDAO.getRowCount(query);
	}

	@Override
	public EnteteMouvement createOrUpdateEnteteMouvementWithoutMAJStock(
			EnteteMouvement ei) {
		return enteteMouvementDAO
				.createOrUpdateEnteteMouvementWithoutMAJStock(ei);
	}

	@Override
	public List<DetailMouvement> getAllDetailMouvementForArticle(
			String codeArticle, String codeMag) {
		return detailMouvementDAO.getAllDetailMouvementForArticle(codeArticle,
				codeMag);
	}

	@Override
	public List<EnteteMouvement> getAllEnteteMouvementForMagByOrigineMouvementAndDate(String pvtCode, String codeOrigine, Date dateDebut, Date dateFin) {
		return enteteMouvementDAO.getAllEnteteMouvementForMagByOrigineMouvementAndDate(pvtCode,
						codeOrigine, dateDebut, dateFin);
	}

	@Override
	public List<DetailMouvement> getAllDetailMouvementForIntervalDate(
			String codeMag, Date dateDebut, Date dateFin, List<String> qteFiltre) {
		return detailMouvementDAO.getAllDetailMouvementForIntervalDate(codeMag,
				dateDebut, dateFin, qteFiltre);
	}

	@Override
	public List<DetailMouvement> getAllDetailMouvementForIntervalDate(String codeMag,
			Date dateDebut, Date dateFin, List<String> qteFiltre,
			String secteur, String rayon, String famille, String sousFamille,
			String gisDebut, String gisFin) {
		return detailMouvementDAO.getAllDetailMouvementForIntervalDate(codeMag,
				dateDebut, dateFin, qteFiltre, secteur, rayon, famille,
				sousFamille, gisDebut, gisFin);
	}
}
