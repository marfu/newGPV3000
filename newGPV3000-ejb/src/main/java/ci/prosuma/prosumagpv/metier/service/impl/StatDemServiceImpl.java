package ci.prosuma.prosumagpv.metier.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.transaction.UserTransaction;

import ci.prosuma.metier.dataexchange.jdbc.mysql.IJDBCConnectionMySQL;
import ci.prosuma.prosumagpv.entity.Article;
import ci.prosuma.prosumagpv.entity.dto.StatDem;
import ci.prosuma.prosumagpv.entity.stock.DetailMouvement;
import ci.prosuma.prosumagpv.entity.util.TypeMvtStatDem;
import ci.prosuma.prosumagpv.metier.dao.mysql.IArticleDAO;
import ci.prosuma.prosumagpv.metier.dao.mysql.IDetailMouvementDAO;
import ci.prosuma.prosumagpv.metier.dao.mysql.IStatDemDAO;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeFestifDAO;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeMvtStatDemDAO;
import ci.prosuma.prosumagpv.metier.service.IStatDemService;

@Stateless
@Local(IStatDemService.class)
@TransactionManagement(TransactionManagementType.BEAN)
public class StatDemServiceImpl implements IStatDemService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private IStatDemDAO statDAO;

	@Resource
	public UserTransaction tx;

	@EJB
	public IJDBCConnectionMySQL mySql;

	@EJB
	private ITypeMvtStatDemDAO typeMvtStatDemDAO;

	@EJB
	private ITypeFestifDAO typeFestifDAO;

	@EJB
	public IDetailMouvementDAO detailMouvDAO;

	@EJB
	private IArticleDAO articleDAO;

	@Override
	public StatDem createOrUpdateStatDem(StatDem a) {
		return statDAO.createOrUpdateStatDem(a);
	}

	@Override
	public StatDem getStatDem(long id) {
		return statDAO.getStatDem(id);
	}

	private void resetTable(String pvtCode) {
		try {
			tx.begin();
			mySql.resetStatDem(pvtCode);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* PROPHYL.COM */
	@Override
	public boolean process(int type, Date dateDebut, Date dateFin,
			long gisementDebut, long gisementFin, String pvtCode,
			String secteur, String rayon, int typeProduit) {

		List<TypeMvtStatDem> filtreLivrer = typeMvtStatDemDAO
				.getAllTypeMvtStatDem("qteLivrer", pvtCode);
		List<TypeMvtStatDem> filtreCasse = typeMvtStatDemDAO
				.getAllTypeMvtStatDem("qteCasse", pvtCode);
		List<TypeMvtStatDem> filtreDemarq = typeMvtStatDemDAO
				.getAllTypeMvtStatDem("qteDemarque", pvtCode);

		List<DetailMouvement> listDM1 = null;
		Map<String, StatDem> mapStatDem = new HashMap<>();

		resetTable(pvtCode);

		List<String> qteLivrer = new ArrayList<>();
		List<String> qteCasse = new ArrayList<>();
		List<String> qteDemarq = new ArrayList<>();
		List<String> qteFiltre = new ArrayList<>();

		for (TypeMvtStatDem mvt : filtreLivrer) {
			qteLivrer.add(mvt.getTypeMouvement());
		}

		for (TypeMvtStatDem mvt : filtreCasse) {
			qteCasse.add(mvt.getTypeMouvement());
		}

		for (TypeMvtStatDem mvt : filtreDemarq) {
			qteDemarq.add(mvt.getTypeMouvement());
		}

		qteFiltre.addAll(qteLivrer);
		qteFiltre.addAll(qteCasse);
		qteFiltre.addAll(qteDemarq);

		if (type == 1) {
			listDM1 = detailMouvDAO.getAllDetailMouvementToUpdateStatDem(((secteur != null) ? secteur : "%"),((rayon != null) ? rayon : "%"), pvtCode, dateDebut,dateFin, qteFiltre);
		} else {
			if (type == 2) {
				listDM1 = detailMouvDAO.getAllDetailMouvementForGisementStatDem(gisementDebut,gisementFin, pvtCode, dateDebut, dateFin,qteFiltre);
			}
		}
		List<DetailMouvement> listDMFinal = new ArrayList<>();
		if (listDM1 != null) listDMFinal.addAll(listDM1);

		if(typeProduit == 0){
			for (DetailMouvement dm : listDM1){
                            if(typeFestifDAO.isArticleFestif(dm.getArticle().getCodeArticle(), pvtCode)){
                                listDMFinal.remove(dm);
                            }
					
                        }
				
		}else if(typeProduit == 1){
			for (DetailMouvement dm : listDM1)
				if(!typeFestifDAO.isArticleFestif(dm.getArticle().getCodeArticle(), pvtCode))
					listDMFinal.remove(dm);			
		}
		
		if (listDM1 != null) {
			for (DetailMouvement dm : listDMFinal) {

				Article artPrincipal = articleDAO.getArticlePrincipal(dm
						.getArticle());

				String key = artPrincipal.getCodeArticle() + "_"
						+ dm.getArtPrixVente() + "_" + dm.getSens();
				if (!mapStatDem.containsKey(key)) {
					// general
					StatDem temp = new StatDem(key);
					temp.setCodeArticle(key);
					temp.setCodeGisement(dm.getArticle().getCodeGisement());
					temp.setColissage(Integer.parseInt(dm.getArticle()
							.getColisage()));
					temp.setDesignation(dm.getArticle().getDesignation());
					temp.setPrixReviensUnitaire((int) dm.getArtPrixReviens());
					temp.setPrixVenteUnitaire((int) dm.getArtPrixVente());
					temp.setSecteur(dm.getArticle().getSecteur());
					temp.setRayon(dm.getArticle().getRayon());
					temp.setFamille(dm.getArticle().getFamille());
					temp.setSousFamille(dm.getArticle().getSousFamille());
					temp.setCodeAnal(dm.getArticle().getCodeAnalytique());
					temp.setDate(dm.getDateMouvement());
					temp.setDuree(1);
					temp.setSensMvt(dm.getSens());
					temp.setCodeMag(pvtCode);

					// type LIVRAISON
					if (qteLivrer.contains(dm.getTypeMouvement().getCode())) {
						temp.setQteLivrer(dm.getQteMvt());
					}

					// type DEMARQUE
					if (qteDemarq.contains(dm.getTypeMouvement().getCode())) {
						temp.setQteDemarque(dm.getQteMvt());
					}

					// type CASSE
					if (qteCasse.contains(dm.getTypeMouvement().getCode())) {
						temp.setQteCasse(dm.getQteMvt());
					}

					// Qte Sortie
					temp.setQteSortie(dm.getQteMvt());

					temp.setQteAvantMvt(dm.getQtePhysiqueAvantMouvement());

					if (dm.getArticle() != null) {
						temp.setPrixVenteInitialUnitaire(artPrincipal
								.getPrixVenteTTCEnCours());
						temp.setPrixReviensInitialUnitaire(artPrincipal
								.getPrixReviensTTCEnCours());
					}
					mapStatDem.put(key, temp);
				} else {
					StatDem temp = mapStatDem.get(key);
					temp.setDuree(dm.getDateMouvement().compareTo(
							temp.getDate()));
					if (temp.getDate().before(dm.getDateMouvement())) {
						temp.setDate(dm.getDateMouvement());
					}

					// type LIVRAISON
					if (qteLivrer.contains(dm.getTypeMouvement().getCode())) {
						temp.setQteLivrer(temp.getQteLivrer() + dm.getQteMvt());
					}

					// type DEMARQUE
					if (qteDemarq.contains(dm.getTypeMouvement().getCode())) {
						temp.setQteDemarque(temp.getQteDemarque()
								+ dm.getQteMvt());
					}

					// type CASSE
					if (qteCasse.contains(dm.getTypeMouvement().getCode())) {
						temp.setQteCasse(temp.getQteCasse() + dm.getQteMvt());
					}

					// Qte Sortie
					temp.setQteSortie(temp.getQteSortie() + dm.getQteMvt());

					temp.setQteAvantMvt(temp.getQteAvantMvt()
							+ dm.getQtePhysiqueAvantMouvement());

					mapStatDem.put(key, temp);
				}
			}

			for (String t : mapStatDem.keySet()) {
				StatDem st = mapStatDem.get(t);
				st.setCodeArticle(t.substring(0, 9));
				try {
					tx.begin();
					statDAO.createOrUpdateStatDem(st);
					tx.commit();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			return true;
		}
		return false;
	}

}
