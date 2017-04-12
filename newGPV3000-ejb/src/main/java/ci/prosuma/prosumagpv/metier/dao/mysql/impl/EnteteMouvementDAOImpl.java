package ci.prosuma.prosumagpv.metier.dao.mysql.impl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ci.prosuma.prosumagpv.entity.pk.ArticleMagRefPK;
import ci.prosuma.prosumagpv.entity.stock.DetailMouvement;
import ci.prosuma.prosumagpv.entity.stock.EnteteMouvement;
import ci.prosuma.prosumagpv.entity.stock.StockArticle;
import ci.prosuma.prosumagpv.metier.dao.interceptors.MouvementInterceptor;
import ci.prosuma.prosumagpv.metier.dao.mysql.IArticleDAO;
import ci.prosuma.prosumagpv.metier.dao.mysql.IEnteteMouvementDAO;
import ci.prosuma.prosumagpv.metier.dao.mysql.IPointDeVenteDAO;
import ci.prosuma.prosumagpv.metier.dao.mysql.IStockArticleDAO;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeMouvementStockDAO;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeOrigineMouvementDAO;

@Stateless
@Local(IEnteteMouvementDAO.class)
public class EnteteMouvementDAOImpl implements IEnteteMouvementDAO, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	protected EntityManager em;

	@EJB
	ITypeOrigineMouvementDAO origineMouvementDAO;

	@EJB
	ITypeMouvementStockDAO typeMouvementStockDAO;

	@EJB
	IArticleDAO articleDAO;

	@EJB
	IStockArticleDAO stockDao;

	@EJB
	IPointDeVenteDAO pointDeVenteDAO;

	@Override
	@Interceptors(MouvementInterceptor.class)
	public EnteteMouvement createOrUpdateEnteteMouvement(EnteteMouvement ei) {
		EnteteMouvement temp = getEnteteMouvement(ei.getId());

		if (temp != null) {
			em.merge(ei);
			em.flush();
			return ei;
		} else {
			em.persist(ei);
			return ei;
		}
	}
	
	public EnteteMouvement createOrUpdateEnteteMouvementWithoutMAJStock(EnteteMouvement ei) {
		EnteteMouvement temp = getEnteteMouvement(ei.getId());
		
		if (temp != null) {
			em.merge(ei);
			em.flush();
			return ei;
		} else {
			em.persist(ei);
			return ei;
		}
	}

	@Override
	public EnteteMouvement getEnteteMouvement(long id) {
		EnteteMouvement en = em.find(EnteteMouvement.class, id);
		if (en != null) {
			if (en.getMouvements() != null)
				en.getMouvements().size();
		}
		return en;

	}

	@Override
	public boolean removeEnteteMouvement(EnteteMouvement ei) {
		EnteteMouvement a = getEnteteMouvement(ei.getId());
		if (a != null) {
			em.remove(a);
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EnteteMouvement> getAllEnteteMouvementForMag(String pvtCode) {
		Query query = em.createQuery("SELECT u  FROM EnteteMouvement u WHERE u.pvt.pvtCode=:pvtCode ORDER BY u.id DESC");
		query.setParameter("pvtCode", pvtCode);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EnteteMouvement> getAllEnteteMouvementForMagByOrigineMouvement(String pvtCode, String codeOrigine) {
		Query query = em.createQuery("SELECT u  FROM EnteteMouvement u WHERE u.origineMouvement.code=:codeOrigine AND  u.pvt.pvtCode=:pvtCode");
		query.setParameter("codeOrigine", codeOrigine);
		query.setParameter("pvtCode", pvtCode);
		return query.getResultList();
	}
        
        
        @Override
	public List<EnteteMouvement> getAllEnteteMouvementForMagByOrigineMouvementAndDate(String pvtCode, String codeOrigine,Date dateDebut , Date dateFin) {
		Query query = em.createNativeQuery("SELECT u.*  FROM ENTETE_MOUVEMENT u WHERE u.ORIGINE_MOUVEMENT_FK=:codeOrigine AND  u.CODE_MAGASIN_FK=:pvtCode  AND (u.DATE_MOUVEMENT BETWEEN :dateDebut AND :dateFin)",EnteteMouvement.class);
		query.setParameter("codeOrigine", codeOrigine);
		query.setParameter("pvtCode", pvtCode);
		query.setParameter("dateDebut", dateDebut);
		query.setParameter("dateFin", dateFin);
		return query.getResultList();
	}
        
//        public List<EnteteMouvement> getAllEnteteMouvementForMagByOrigineMouvementAndDate(String pvtCode, String codeOrigine,Date dateDebut , Date dateFin) {
//		Query query = em.createQuery("SELECT u  FROM EnteteMouvement u WHERE u.origineMouvement.code=:codeOrigine AND  u.pvt.pvtCode=:pvtCode  AND (u.dateMouvement BETWEEN :dateDebut AND :dateFin)");
//		query.setParameter("codeOrigine", codeOrigine);
//		query.setParameter("pvtCode", pvtCode);
//		query.setParameter("dateDebut", dateDebut);
//		query.setParameter("dateFin", dateFin);
//		return query.getResultList();
//	}
	
	
	
	

	@SuppressWarnings("unused")
	@Override
	public String createMouvementReceptionFromFact(String codeMag, List<String> lineList, String user) {
		try {

			boolean avoir = false;
			boolean livDir = false;
			boolean mvntFin = false;

			String codMvnt = "";
			String obs = "";
			String obs2 = "";
			EnteteMouvement em = null;
			List<DetailMouvement> listDm = new ArrayList<>();

			String codMvnt2 = "";
			String obsDoss = "";
			String obsDoss2 = "";
			EnteteMouvement em2 = null;
			List<DetailMouvement> listDm2 = new ArrayList<>();

			String themePromo = null;
			String dateFacturation = null;
			String mag = null;
			String expediteur = null;
			String numFac = null;
			String observation = null;

			for (String a : lineList) {
				if (a.startsWith("E")) {
					if (a.substring(152, 153).equals("C"))
						avoir = true;
					if (!a.substring(181, 187).equals("000000"))
						livDir = true;
					if (a.substring(180, 181).equals("F"))
						mvntFin = true;

					// traitement entete
					dateFacturation = "20" + a.substring(18, 24);
					mag = a.substring(1, 4);
					expediteur = "GPV3000";
					numFac = a.substring(24, 26) + "/" + a.substring(26, 34);
					observation = a.substring(112, 152);
					
					// cr�ation ent�te mouvement

					em = new EnteteMouvement();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
					em.setDateMouvement(sdf.parse(dateFacturation));
					em.setOrigineMouvement(origineMouvementDAO.getOrigineMouvement("RECEP"));

					obs = a.substring(112, 142);
					obs2 = "";

					if (avoir) {
						obs2 = "A" + a.substring(24, 26) + a.substring(26, 34) + "E" + a.substring(153, 161) + "R" + a.substring(189, 197);
						codMvnt = "E-";
						em.setNumFac("A" + (a.substring(24, 26) + a.substring(26, 34)).trim());
					} else {
						obs2 = "F" + a.substring(24, 26) + a.substring(26, 34) + "E" + a.substring(153, 161) + "R" + a.substring(189, 197);
						codMvnt = "E+";
						em.setNumFac("F" + (a.substring(24, 26) + a.substring(26, 34)).trim());
					}

					em.setObservations(obs + " " + obs2);
					em.setUserCreation(user);
					em.setDateMouvement(new Date(System.currentTimeMillis()));
					em.setPvt(pointDeVenteDAO.getPVT(mag));
					
					// em = createOrUpdateEnteteMouvement(em);

					themePromo = a.substring(35, 39);

					// GENERATION DOSSIER 2
					if (livDir) {
						em2 = new EnteteMouvement();
						em2.setDateMouvement(sdf.parse(dateFacturation));

						obsDoss = "Annulation LD";
						em2.setOrigineMouvement(origineMouvementDAO.getOrigineMouvement("RECD"));
						obsDoss2 = "F" + a.substring(24, 26) + a.substring(26, 34) + "E" + a.substring(153, 161) + "R" + a.substring(189, 197);
						codMvnt2 = "LD-";
						em2.setNumFac("F" + (a.substring(24, 26) + a.substring(26, 34)).trim());

						if (avoir) {
							obsDoss = "Annulation DAV";
							em2.setOrigineMouvement(origineMouvementDAO.getOrigineMouvement("DAV"));
							obsDoss2 = "A" + a.substring(24, 26) + a.substring(26, 34) + "E" + a.substring(153, 161) + "R" + a.substring(189, 197);
							codMvnt2 = "AVP+";
							em2.setNumFac("A" + (a.substring(24, 26) + a.substring(26, 34)).trim());

						}
						em2.setObservations(obsDoss + " " + obsDoss2);
						em2.setUserCreation(user);
						em2.setDateMouvement(new Date(System.currentTimeMillis()));
						em2.setPvt(pointDeVenteDAO.getPVT(mag));
						// em2 = createOrUpdateEnteteMouvement(em2);
					}
				}

				if (a.startsWith("L")) {

					DetailMouvement dm = new DetailMouvement();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
					dm.setDateMouvement(sdf.parse(dateFacturation));
                                        dm.setEnteteMouvement(em);
					dm.setArticle(articleDAO.getArticle(new ArticleMagRefPK(codeMag, a.substring(1, 10))));
					dm.setTypeMouvement(typeMouvementStockDAO.getTypeMouvementStock(codMvnt));
					dm.setQteMvt((Float.parseFloat(a.substring(60, 71)) / 1000));
					if (mvntFin) {

						dm.setQteMvt((Float.parseFloat(a.substring(60, 71)) / 1000));
						dm.setQtePhysiqueAvantMouvement(dm.getArticle().getStock().getQteComptable());
						dm.setArtPrixReviens((Long.parseLong(a.substring(82, 93)) / 1000) * (Long.parseLong(a.substring(60, 71)) / 1000));
						dm.setArtPrixVente((Long.parseLong(a.substring(93, 104)) / 1000) * (Long.parseLong(a.substring(60, 71)) / 1000));
						dm.setSens(1);
						if (avoir) {
							dm.setArtPrixReviens(dm.getArtPrixReviens() * -1);
							dm.setArtPrixVente(dm.getArtPrixVente() * -1);
							dm.setSens(-1);
						}
					} else {
						if (avoir) {
							dm.setQteMvt((Float.parseFloat(a.substring(60, 71)) / 1000));
							dm.setSens(-1);
						} else {
							dm.setQteMvt((Float.parseFloat(a.substring(60, 71)) / 1000));
							dm.setSens(1);
						}

						dm.setArtPrixReviens(Long.parseLong(a.substring(82, 93)) / 1000);
						dm.setArtPrixVente(Long.parseLong(a.substring(93, 104)) / 1000);
						
						dm.setQtePhysiqueAvantMouvement(0);

						StockArticle sa = dm.getArticle().getStock();
						if (sa != null) {
							dm.setQtePhysiqueAvantMouvement(sa.getQteComptable());

						}

					}
					dm.setDepot(pointDeVenteDAO.getDepotPrincipalForMag(codeMag));
					listDm.add(dm);

					if (livDir) {
						DetailMouvement dm2 = new DetailMouvement();
                                                dm2.setEnteteMouvement(em2);
						dm2.setArticle(articleDAO.getArticle(new ArticleMagRefPK(codeMag, a.substring(1, 10))));
						dm2.setTypeMouvement(typeMouvementStockDAO.getTypeMouvementStock(codMvnt2));
						dm2.setQteMvt((Float.parseFloat(a.substring(60, 71)) / 1000));
						dm2.setSens(-1);
						dm2.setDateMouvement(sdf.parse(dateFacturation));
						dm2.setObservations("MOUVEMENT ANNULATION LD : "+dm2.getArticle().getCodeArticle()+ " QTE : "+dm2.getQteMvt());
						dm2.setArtPrixReviens(Long.parseLong(a.substring(82, 93)) / 1000);
						dm2.setArtPrixVente(Long.parseLong(a.substring(93, 104)) / 1000);
						dm2.setQtePhysiqueAvantMouvement(0);
						StockArticle sa2 = dm.getArticle().getStock();
						if (sa2 != null) {
							dm2.setQtePhysiqueAvantMouvement(sa2.getQteComptable());
						}

						dm2.setDepot(pointDeVenteDAO.getDepotPrincipalForMag(codeMag));
						listDm2.add(dm2);
					}

				}

				if (em != null) {
					em.setMouvements(listDm);
					createOrUpdateEnteteMouvement(em);
				}
				if (em2 != null) {
					em2.setMouvements(listDm2);
					createOrUpdateEnteteMouvement(em2);
				}

			}
			return "SUCCESS";
		} catch (Exception e) {
			e.printStackTrace();
			return "FAILURE";
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EnteteMouvement> executeQuery(String s) {
		try{
		return em.createQuery(s).getResultList();
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DetailMouvement> executeQueryForDetail(String s) {
		Query query = em.createQuery(s);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EnteteMouvement> executeLazyQuery(String query, int first, int pageSize) {
		Query q = em.createQuery(query);

		q.setFirstResult(first);
		q.setMaxResults(pageSize);

		return q.getResultList();
	}

	@Override
	public Long getRowCount(String query) {
		Query q = em.createQuery(query);
		return (Long) q.getSingleResult();
	}

}
