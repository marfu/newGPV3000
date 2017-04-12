package ci.prosuma.prosumagpv.metier.dao.mysql.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ci.prosuma.prosumagpv.entity.dto.CAArticleDTO;
import ci.prosuma.prosumagpv.entity.dto.CAFamilleDTO;
import ci.prosuma.prosumagpv.entity.dto.CARayonDTO;
import ci.prosuma.prosumagpv.entity.dto.CASecteurDTO;
import ci.prosuma.prosumagpv.entity.dto.CASousFamilleDTO;
import ci.prosuma.prosumagpv.entity.vente.VenteCaisse;
import ci.prosuma.prosumagpv.metier.dao.mysql.IVenteCaisseDAO;

@Stateless
@Local(IVenteCaisseDAO.class)
public class VenteCaisseDAOImpl implements IVenteCaisseDAO,
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	protected EntityManager em;

	@Override
	public VenteCaisse createOrUpdateVenteCaisse(
			VenteCaisse a) {
		VenteCaisse temp = getVenteCaisse(a.getId());
		if (temp != null) {
			em.merge(a);
			em.flush();
			return a;
		} else {
			em.persist(a);
			em.flush();
			return a;
		}
	}

	@Override
	public VenteCaisse getVenteCaisse(long entityId) {
		VenteCaisse ebcf = em.find(VenteCaisse.class, entityId);
		return ebcf;
	}

	@Override
	public boolean removeVenteCaisse(VenteCaisse entity) {
		VenteCaisse a = getVenteCaisse(entity.getId());
		if (a != null) {
			em.remove(a);
			return true;
		} else {
			return false;
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<VenteCaisse> getAllVenteCaisseForMagAndDateBySecteur(
			String pvtCode, Date dateDebut, Date dateFin, String secteurCode) {

		Query query = em
				.createQuery("SELECT u  FROM VenteCaisse u WHERE u.article.pvtCode=:pvtCode AND ( u.dateVente BETWEEN :dateDebut AND :dateFin)  AND u.article.secteur.code=:secteurCode   ");
		query.setParameter("pvtCode", pvtCode);
		query.setParameter("dateDebut", dateDebut);
		query.setParameter("dateFin", dateFin);
		query.setParameter("secteurCode", secteurCode);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VenteCaisse> getAllVenteCaisseForMagAndDateByRayon(
			String pvtCode, Date dateDebut, Date dateFin, String rayonCode) {
		Query query = em
				.createQuery("SELECT u  FROM VenteCaisse u WHERE u.article.pvtCode=:pvtCode AND ( u.dateVente BETWEEN :dateDebut AND :dateFin)  AND u.article.rayon.code=:rayonCode   ");
		query.setParameter("pvtCode", pvtCode);
		query.setParameter("dateDebut", dateDebut);
		query.setParameter("dateFin", dateFin);
		query.setParameter("rayonCode", rayonCode);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VenteCaisse> getAllVenteCaisseForMagAndDateArticle(
			String pvtCode, Date dateDebut, Date dateFin, String codeArticle) {
		Query query = em
				.createQuery("SELECT u  FROM VenteCaisse u WHERE u.article.pvtCode=:pvtCode AND ( u.dateVente BETWEEN :dateDebut AND :dateFin)  AND u.article.codeArticle=:codeArticle   ");
		query.setParameter("pvtCode", pvtCode);
		query.setParameter("dateDebut", dateDebut);
		query.setParameter("dateFin", dateFin);
		query.setParameter("codeArticle", codeArticle);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VenteCaisse> getAllVenteCaisseForMagAndDate(String pvtCode,
			Date dateDebut, Date dateFin) {
		Query query = em
				.createQuery("SELECT u  FROM VenteCaisse u WHERE u.article.pvtCode=:pvtCode AND ( u.dateVente BETWEEN :dateDebut AND :dateFin)  ");
		query.setParameter("pvtCode", pvtCode);
		query.setParameter("dateDebut", dateDebut);
		query.setParameter("dateFin", dateFin);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VenteCaisse> getAllVenteCaisseForMagAndDateByFamille(
			String pvtCode, Date dateDebut, Date dateFin, String familleCode) {
		Query query = em
				.createQuery("SELECT u  FROM VenteCaisse u WHERE u.article.pvtCode=:pvtCode AND ( u.dateVente BETWEEN :dateDebut AND :dateFin) AND u.article.famille.code=:familleCode ");
		query.setParameter("pvtCode", pvtCode);
		query.setParameter("dateDebut", dateDebut);
		query.setParameter("dateFin", dateFin);
		query.setParameter("familleCode", familleCode);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VenteCaisse> getAllVenteCaisseForMagAndDateBySousFamille(
			String pvtCode, Date dateDebut, Date dateFin, String sousFamilleCode) {
		Query query = em
				.createQuery("SELECT u  FROM VenteCaisse u WHERE u.article.pvtCode=:pvtCode AND ( u.dateVente BETWEEN :dateDebut AND :dateFin)  AND u.article.sousFamille=:sousFamilleCode ");
		query.setParameter("pvtCode", pvtCode);
		query.setParameter("dateDebut", dateDebut);
		query.setParameter("dateFin", dateFin);
		query.setParameter("sousFamilleCode", sousFamilleCode);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VenteCaisse> getAllVenteCaisseForMagAndDateByGisement(
			String pvtCode, Date dateDebut, Date dateFin, long gisementDebut,
			long gisementFin) {
		Query query = em
				.createQuery("SELECT u  FROM VenteCaisse u WHERE u.article.pvtCode=:pvtCode AND ( u.dateVente BETWEEN :dateDebut AND :dateFin) AND (u.codeGisement >= :gisementDebut AND u.codeGisement <= :gisementFin)  ");
		query.setParameter("pvtCode", pvtCode);
		query.setParameter("dateDebut", dateDebut);
		query.setParameter("dateFin", dateFin);
		query.setParameter("gisementDebut", gisementDebut);
		query.setParameter("gisementFin", gisementFin);
		return query.getResultList();
	}

	@Override
	public VenteCaisse createVenteCaisse(VenteCaisse a) {
			em.persist(a);
			//em.flush();
			return a;
	}

	@Override
	public void saveVenteCaisse(VenteCaisse entity) {
		em.persist(entity);
		//em.flush();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CASecteurDTO> getCaSecteurByDate(Date debut, Date fin, String codeMag) {
		List<CASecteurDTO> result = new ArrayList<>();
		Query query = em.createNativeQuery("SELECT SUM(-1*sens*QTE_MOUVEMENTE*detail_mouvement.artPrixReviens) as pf,SUM(-1*sens*QTE_MOUVEMENTE*detail_mouvement.artPrixVente) as pv," +
				"SUM(-1*sens*QTE_MOUVEMENTE*detail_mouvement.artPrixVente)-SUM(-1*sens*QTE_MOUVEMENTE*detail_mouvement.artPrixReviens) , detail_mouvement.secteur " +
				" FROM detail_mouvement  WHERE detail_mouvement.dateMouvement BETWEEN :debut  " +
				"AND :fin AND detail_mouvement.CODE_MAGASIN_FK =:codemag  " +
				"AND (TYPE_MOUVEMENT_FK='C-'  OR TYPE_MOUVEMENT_FK='C+')" +
				"GROUP BY detail_mouvement.secteur " +
				"ORDER BY detail_mouvement.secteur ASC;");
		query.setParameter("debut", debut);
		query.setParameter("fin", fin);
		query.setParameter("codemag", codeMag);
		
		List<Object[]> listca = query.getResultList();
		for(Object[] ca : listca){
			CASecteurDTO dto = new CASecteurDTO();
			dto.setCodeSecteur((String) ca[3]);
			//dto.setLibSecteur((String) ca[4]);
			BigDecimal fact = new BigDecimal((Double)ca[0]);
			BigDecimal vent = new BigDecimal((Double)ca[1]);
			BigDecimal marge = new BigDecimal((Double)ca[2]);
			dto.setPrixFacturation(fact);
			dto.setPrixVente(vent);
			dto.setMarge(marge);
			result.add(dto);
		}
//		Query query2 = em.createNativeQuery("SELECT SUM(-1*sens*QTE_MOUVEMENTE*detail_mouvement.artPrixVente) as pf" +
//				 " FROM detail_mouvement WHERE detail_mouvement.dateMouvement BETWEEN :debut  AND :fin " + 
//				 " AND detail_mouvement.CODE_MAGASIN_FK =:codemag  AND (TYPE_MOUVEMENT_FK='C-' OR TYPE_MOUVEMENT_FK='C+')" +
//				 " AND CODE_ARTICLE_FK='INCONNU' ;");
//		query2.setParameter("debut", debut);
//		query2.setParameter("fin", fin);
//		query2.setParameter("codemag", codeMag);
//		Double inconnu = (Double) query2.getSingleResult();
//		CASecteurDTO secteurInconnu = new CASecteurDTO();
//		secteurInconnu.setCodeSecteur("XX");
//		secteurInconnu.setLibSecteur("INCONNU");
//		BigDecimal ventInconnu = new BigDecimal((Double)inconnu);
//		secteurInconnu.setPrixVente(ventInconnu);
//		result.add(secteurInconnu);
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CARayonDTO> getCaRayonBySecteurAndDate(Date debut, Date fin,
			String codeMag, String secteur) {
		List<CARayonDTO> result = new ArrayList<>();
		Query query = em.createNativeQuery("SELECT  SUM(-1*sens*QTE_MOUVEMENTE*detail_mouvement.artPrixReviens) as pf,SUM(-1*sens*QTE_MOUVEMENTE*detail_mouvement.artPrixVente) as pv," +
				" SUM(-1*sens*QTE_MOUVEMENTE*detail_mouvement.artPrixVente)-SUM(-1*sens*QTE_MOUVEMENTE*detail_mouvement.artPrixReviens)," +
				" detail_mouvement.secteur, detail_mouvement.rayon" +
				" FROM detail_mouvement " +
				"WHERE detail_mouvement.dateMouvement BETWEEN :debut  " +
				"AND :fin " +
				"AND detail_mouvement.CODE_MAGASIN_FK =:codemag  " +
				"AND detail_mouvement.secteur=:secteur "+
				"AND (TYPE_MOUVEMENT_FK='C-' OR TYPE_MOUVEMENT_FK='C+') "+
				"GROUP BY detail_mouvement.secteur, detail_mouvement.rayon " +
				"ORDER BY detail_mouvement.rayon ASC;");
		query.setParameter("debut", debut);
		query.setParameter("fin", fin);
		query.setParameter("codemag", codeMag);
		query.setParameter("secteur", secteur);
		
		List<Object[]> listca = query.getResultList();
		for(Object[] ca : listca){
			CARayonDTO dto = new CARayonDTO();
			dto.setCodeSecteur((String) ca[3]);
			
			BigDecimal fact = new BigDecimal((Double)ca[0]);
			BigDecimal vent = new BigDecimal((Double)ca[1]);
			BigDecimal marge = new BigDecimal((Double)ca[2]);
			
			dto.setCodeRayon((String) ca[4]);
			//dto.setLibRayon((String) ca[5]);
			dto.setPrixFacturation(fact);
			dto.setPrixVente(vent);
			dto.setMarge(marge);
			result.add(dto);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CAFamilleDTO> getCaFamilleByRayonAndDate(Date debut, Date fin,String codeMag, String rayon) {
		List<CAFamilleDTO> result = new ArrayList<>();
		Query query = em.createNativeQuery("SELECT SUM(-1*sens*QTE_MOUVEMENTE*detail_mouvement.artPrixReviens) as pf,SUM(-1*sens*QTE_MOUVEMENTE*detail_mouvement.artPrixVente) as pv, " +
				"SUM(-1*sens*QTE_MOUVEMENTE*detail_mouvement.artPrixVente)-SUM(-1*sens*QTE_MOUVEMENTE*detail_mouvement.artPrixReviens), detail_mouvement.secteur, " +
				" detail_mouvement.rayon, detail_mouvement.famille" +
				" FROM detail_mouvement " +
				"WHERE detail_mouvement.dateMouvement BETWEEN :debut  " +
				"AND :fin " +
				"AND detail_mouvement.CODE_MAGASIN_FK =:codemag  " +
				"AND detail_mouvement.rayon=:rayon "+
				"AND (TYPE_MOUVEMENT_FK='C-' OR TYPE_MOUVEMENT_FK='C+') "+
				"GROUP BY detail_mouvement.secteur,detail_mouvement.rayon,detail_mouvement.famille " +
				"ORDER BY detail_mouvement.famille ASC;");
		query.setParameter("debut", debut);
		query.setParameter("fin", fin);
		query.setParameter("codemag", codeMag);
		query.setParameter("rayon", rayon);
		
		List<Object[]> listca = query.getResultList();
		for(Object[] ca : listca){
			CAFamilleDTO dto = new CAFamilleDTO();
			dto.setCodeSecteur((String) ca[3]);
			dto.setCodeRayon((String) ca[4]);
			dto.setCodeFamille((String) ca[5]);
			//dto.setLibFamille((String) ca[6]);
			
			BigDecimal fact = new BigDecimal((Double)ca[0]);
			BigDecimal vent = new BigDecimal((Double)ca[1]);
			BigDecimal marge = new BigDecimal((Double)ca[2]);
//			
//			BigDecimal fact = (BigDecimal)ca[0];
//			BigDecimal vent = (BigDecimal)ca[1];
//			BigDecimal marge = new BigDecimal((Double)ca[2]);
			
			dto.setPrixFacturation(fact);
			dto.setPrixVente(vent);
			dto.setMarge(marge);
			result.add(dto);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CASousFamilleDTO> getCaSousFamilleByFamilleAndDate(Date debut,
			Date fin, String codeMag, String famille) {
		List<CASousFamilleDTO> result = new ArrayList<>();
		Query query = em.createNativeQuery("SELECT SUM(-1*sens*QTE_MOUVEMENTE*detail_mouvement.artPrixReviens) as pf,SUM(-1*sens*QTE_MOUVEMENTE*detail_mouvement.artPrixVente) as pv, " +
				"SUM(-1*sens*QTE_MOUVEMENTE*detail_mouvement.artPrixVente)-SUM(-1*sens*QTE_MOUVEMENTE*detail_mouvement.artPrixReviens), detail_mouvement.secteur, " +
				" detail_mouvement.rayon, detail_mouvement.famille, detail_mouvement.sousFamille FROM detail_mouvement" +
				" WHERE detail_mouvement.dateMouvement BETWEEN :debut  AND :fin " +
				" AND detail_mouvement.CODE_MAGASIN_FK =:codemag  " +
				" AND detail_mouvement.famille=:famille "+
				" AND (TYPE_MOUVEMENT_FK='C-' OR TYPE_MOUVEMENT_FK='C+') "+
				" GROUP BY detail_mouvement.secteur, detail_mouvement.rayon, detail_mouvement.famille, detail_mouvement.sousFamille " +
				" ORDER BY detail_mouvement.sousFamille ASC;");
		query.setParameter("debut", debut);
		query.setParameter("fin", fin);
		query.setParameter("codemag", codeMag);
		query.setParameter("famille", famille);
		
		List<Object[]> listca = query.getResultList();
		for(Object[] ca : listca){
			CASousFamilleDTO dto = new CASousFamilleDTO();
			dto.setCodeSecteur((String) ca[3]);
			dto.setCodeRayon((String) ca[4]);
			dto.setCodeFamille((String) ca[5]);
			//dto.setLibSousFamille((String) ca[7]);
			dto.setCodeSousFamille((String) ca[6]);
			//System.out.println("CODE SOUS FAMILLE : "+ dto.getCodeSousFamille());
			BigDecimal fact = new BigDecimal((Double)ca[0]);
			BigDecimal vent = new BigDecimal((Double)ca[1]);
			BigDecimal marge = new BigDecimal((Double)ca[2]);
			
//			BigDecimal fact = (BigDecimal)ca[0];
//			BigDecimal vent = (BigDecimal)ca[1];
//			BigDecimal marge = new BigDecimal((Double)ca[2]);
			
			dto.setPrixFacturation(fact);
			dto.setPrixVente(vent);
			dto.setMarge(marge);
			result.add(dto);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CAArticleDTO> getCaArticleBySousFamilleAndDate(Date debut,
			Date fin, String codeMag, String sousfamille) {
		List<CAArticleDTO> result = new ArrayList<>();
		Query query = em.createNativeQuery("SELECT SUM(-1*sens*QTE_MOUVEMENTE*detail_mouvement.artPrixReviens) as pf,SUM(-1*sens*QTE_MOUVEMENTE*detail_mouvement.artPrixVente) as pv, " +
				"SUM(-1*sens*QTE_MOUVEMENTE*detail_mouvement.artPrixVente)-SUM(-1*sens*QTE_MOUVEMENTE*detail_mouvement.artPrixReviens), detail_mouvement.secteur, " +
				" detail_mouvement.rayon,detail_mouvement.famille,detail_mouvement.sousFamille,detail_mouvement.CODE_ARTICLE_FK " +
				" FROM detail_mouvement " +
				" WHERE detail_mouvement.dateMouvement BETWEEN :debut  AND :fin " +
				" AND detail_mouvement.CODE_MAGASIN_FK =:codemag  " +
				" AND detail_mouvement.sousfamille=:sousfamille "+
				" AND (TYPE_MOUVEMENT_FK='C-' OR TYPE_MOUVEMENT_FK='C+') "+
				" GROUP BY detail_mouvement.secteur,detail_mouvement.rayon,detail_mouvement.famille,detail_mouvement.sousFamille,detail_mouvement.CODE_ARTICLE_FK " +
				" ORDER BY detail_mouvement.CODE_ARTICLE_FK ASC;");
		query.setParameter("debut", debut);
		query.setParameter("fin", fin);
		query.setParameter("codemag", codeMag);
		query.setParameter("sousfamille", sousfamille);
		
		List<Object[]> listca = query.getResultList();
		for(Object[] ca : listca){
			CAArticleDTO dto = new CAArticleDTO();
			dto.setCodeSecteur((String) ca[3]);
			dto.setCodeRayon((String) ca[4]);
			dto.setCodeFamille((String) ca[5]);
			dto.setCodeSousFamille((String) ca[6]);
			dto.setCodeArticle((String) ca[7]);
			//dto.setLibArticle((String) ca[8]);
			
			BigDecimal fact = new BigDecimal((Double)ca[0]);
			BigDecimal vent = new BigDecimal((Double)ca[1]);
			BigDecimal marge = new BigDecimal((Double)ca[2]);
			
//			BigDecimal fact = (BigDecimal)ca[0];
//			BigDecimal vent = (BigDecimal)ca[1];
//			BigDecimal marge = new BigDecimal((Double)ca[2]);
			
			dto.setPrixFacturation(fact);
			dto.setPrixVente(vent);
			dto.setMarge(marge);
			result.add(dto);
		}
		return result;
	}

	

}
