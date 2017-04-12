package ci.prosuma.prosumagpv.metier.dao.mysql.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ci.prosuma.prosumagpv.entity.Article;
import ci.prosuma.prosumagpv.entity.dto.ArticleDTO;
import ci.prosuma.prosumagpv.entity.dto.CommandeDTO;
import ci.prosuma.prosumagpv.entity.pk.ArticleMagRefPK;
import ci.prosuma.prosumagpv.entity.util.EnumerationParam.TypeArticle;
import ci.prosuma.prosumagpv.metier.dao.mysql.IArticleDAO;
import ci.prosuma.prosumagpv.metier.dao.mysql.IStockArticleDAO;
import java.util.ArrayList;

@Stateless
@Local(IArticleDAO.class)
@SuppressWarnings("unchecked")
public class ArticleDAOImpl implements IArticleDAO, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @PersistenceContext
    protected EntityManager em;

    @EJB
    IStockArticleDAO stockArticleDAO;

    @Override
    public Article createOrUpdateArticle(Article a) {
        try {
            Article temp = getArticle(new ArticleMagRefPK(a.getPvtCode(), a.getCodeArticle()));
            if (temp != null) {
                em.merge(a);
                em.flush();
                return a;
            } else {
                em.persist(a);
                em.flush();
                return a;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Article getArticle(ArticleMagRefPK entityId) {

        Query query = em.createQuery("SELECT a FROM Article a where a.pvtCode=:pvtCode AND a.codeArticle=:codeArticle");
        
        query.setParameter("pvtCode", entityId.getPvtCode());
        query.setParameter("codeArticle", entityId.getCodeArticle());
        try {
            //System.out.println("@@@@@@@@@@@@@@@@@@@@@@@  pvtCode : "+query.getParameterValue("pvtCode"));
           // System.out.println("@@@@@@@@@@@@@@@@@@@@@@@  codeArticle : "+query.getParameterValue("codeArticle"));
            return (Article) query.getSingleResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    @Override
    public Article getArticleNative(ArticleMagRefPK entityId) {

        Query query = em.createNativeQuery("SELECT a.* FROM article a where a.CODE_MAGASIN_PK=:pvtCode AND a.CODE_ARTICLE_PK=:codeArticle");
        
        query.setParameter("pvtCode", entityId.getPvtCode());
        query.setParameter("codeArticle", entityId.getCodeArticle());
        try {
            //System.out.println("@@@@@@@@@@@@@@@@@@@@@@@  pvtCode : "+query.getParameterValue("pvtCode"));
            //System.out.println("@@@@@@@@@@@@@@@@@@@@@@@  codeArticle : "+query.getParameterValue("codeArticle"));
            return (Article) query.getSingleResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    @Override
    public Article getArticleWithStock(ArticleMagRefPK entityId) {

        Query query = em.createQuery("SELECT a FROM Article a JOIN FETCH a.stock  where a.pvtCode=:pvtCode AND a.codeArticle=:codeArticle");
        query.setParameter("pvtCode", entityId.getPvtCode());
        query.setParameter("codeArticle", entityId.getCodeArticle());
        try {
            return (Article) query.getSingleResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean removeArticle(Article entity) {
        Article a = getArticle(new ArticleMagRefPK(entity.getPvtCode(),
                entity.getCodeArticle()));
        if (a != null) {
            em.remove(a);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Article> getAllArticleForMag(String pvtCode) {
        Query query = em.createQuery("SELECT a FROM Article a "
                + "WHERE a.pvtCode=:pvtCode AND a.prixVenteTTCEnCours != 0 "
                + " ORDER BY a.codeGisement,a.codeArticle ASC ");
        query.setParameter("pvtCode", pvtCode);
        return query.getResultList();

    }

    @Override
    public List<String> getAllCodeArticleForMag(String pvtCode) {
        Query query = em.createQuery("SELECT a.codeArticle FROM Article a "
                + "WHERE a.pvtCode=:pvtCode AND a.prixVenteTTCEnCours != 0 "
                + "ORDER BY a.codeArticle ASC ");
        query.setParameter("pvtCode", pvtCode);
        return query.getResultList();
    }

    @Override
    public List<Article> getAllArticleBySecteurAndMag(String codeSecteur,
            String pvtCode) {
        Query query = em.createQuery("SELECT a FROM Article a "
                + "WHERE a.pvtCode=:pvtCode AND a.secteur=:codeSecteur"
                + " AND a.actif = TRUE AND a.prixVenteTTCEnCours != 0  AND a.inventaire=:rep "
                + "ORDER BY a.codeGisement,a.codeArticle ASC ");
        query.setParameter("codeSecteur", codeSecteur);
        query.setParameter("pvtCode", pvtCode);
        query.setParameter("rep", false);
        return query.getResultList();
    }

    @Override
    public List<Article> getAllArticleByRayonAndMag(String codeRayon,
            String pvtCode) {
        Query query = em
                .createQuery("SELECT a FROM Article a "
                        + "WHERE a.pvtCode=:pvtCode AND a.rayon=:codeRayon AND a.actif = TRUE "
                        + "AND a.prixVenteTTCEnCours != 0  ORDER BY a.codeGisement,a.codeArticle ASC  ");
        query.setParameter("codeRayon", codeRayon);
        query.setParameter("pvtCode", pvtCode);
        return query.getResultList();
    }

    @Override
    public List<Article> getAllArticleInGisementBySecteurAndMag(
            String codeSecteur, long codeGisementDebut, long codeGisementFin,
            String pvtCode) {
        Query query = em
                .createQuery("SELECT a FROM Article a "
                        + "WHERE ( a.codeGisement  BETWEEN :codeGisementDebut AND  :codeGisementFin)"
                        + " AND a.pvtCode=:pvtCode AND a.secteur=:codeSecteur AND a.actif = TRUE "
                        + " AND a.prixVenteTTCEnCours != 0  ORDER BY a.codeGisement,a.codeArticle ASC ");
        query.setParameter("codeGisementDebut", codeGisementDebut);
        query.setParameter("codeGisementFin", codeGisementFin);
        query.setParameter("codeSecteur", codeSecteur);
        query.setParameter("pvtCode", pvtCode);
        return query.getResultList();
    }
    
     @Override
    public List<Article> getAllArticleBySecteurAndMagForInventaire(String codeSecteur,
            String pvtCode) {
        Query query = em.createQuery("SELECT a FROM Article a JOIN FETCH a.stock "
                + "WHERE a.pvtCode=:pvtCode AND a.secteur=:codeSecteur"
                + " AND a.actif = TRUE AND a.prixVenteTTCEnCours != 0  AND a.inventaire=:rep "
                + "ORDER BY a.codeGisement,a.codeArticle ASC ");
        query.setParameter("codeSecteur", codeSecteur);
        query.setParameter("pvtCode", pvtCode);
        query.setParameter("rep", false);
        return query.getResultList();
    }

    @Override
    public List<Article> getAllArticleByRayonAndMagForInventaire(String codeRayon,String pvtCode) {
        Query query = em
                .createQuery("SELECT a FROM Article a JOIN FETCH a.stock "
                        + "WHERE a.pvtCode=:pvtCode AND a.rayon=:codeRayon AND a.actif = TRUE AND a.inventaire=:rep "
                        + "AND a.prixVenteTTCEnCours != 0  ORDER BY a.codeGisement,a.codeArticle ASC  ");
        query.setParameter("codeRayon", codeRayon);
        query.setParameter("pvtCode", pvtCode);
        query.setParameter("rep", false);
        return query.getResultList();
    }

    @Override
    public List<Article> getAllArticleInGisementBySecteurAndMagForInventaire(
            String codeSecteur, long codeGisementDebut, long codeGisementFin,
            String pvtCode) {
        Query query = em
                .createQuery("SELECT a FROM Article a JOIN FETCH a.stock "
                        + "WHERE ( a.codeGisement  BETWEEN :codeGisementDebut AND  :codeGisementFin)"
                        + " AND a.pvtCode=:pvtCode AND a.secteur=:codeSecteur AND a.actif = TRUE AND a.inventaire=:rep"
                        + " AND a.prixVenteTTCEnCours != 0  ORDER BY a.codeGisement,a.codeArticle ASC ");
        query.setParameter("codeGisementDebut", codeGisementDebut);
        query.setParameter("codeGisementFin", codeGisementFin);
        query.setParameter("codeSecteur", codeSecteur);
        query.setParameter("pvtCode", pvtCode);
        query.setParameter("rep", false);
        return query.getResultList();
    }
    
     @Override
    public List<Article> getAllArticleInGisementByRayonAndMagForInventaire(String codeRayon,
            long codeGisementDebut, long codeGisementFin, String pvtCode) {
        Query query = em.createQuery("SELECT a FROM Article a JOIN FETCH a.stock "
                + "WHERE ( a.codeGisement  BETWEEN :codeGisementDebut AND  :codeGisementFin) "
                + "AND a.pvtCode=:pvtCode AND a.rayon=:codeRayon AND a.actif = TRUE AND a.inventaire=:rep "
                + "AND a.prixVenteTTCEnCours != 0  ORDER BY a.codeGisement,a.codeArticle ASC  ");
        query.setParameter("codeGisementDebut", codeGisementDebut);
        query.setParameter("codeGisementFin", codeGisementFin);
        query.setParameter("codeRayon", codeRayon);
        query.setParameter("pvtCode", pvtCode);
        query.setParameter("rep", false);
        return query.getResultList();
    }

    @Override
    public List<Article> getAllArticleInGisementByRayonAndMag(String codeRayon,
            long codeGisementDebut, long codeGisementFin, String pvtCode) {
        Query query = em.createQuery("SELECT a FROM Article a "
                + "WHERE ( a.codeGisement  BETWEEN :codeGisementDebut AND  :codeGisementFin) "
                + "AND a.pvtCode=:pvtCode AND a.rayon=:codeRayon AND a.actif = TRUE  "
                + "AND a.prixVenteTTCEnCours != 0  ORDER BY a.codeGisement,a.codeArticle ASC  ");
        query.setParameter("codeGisementDebut", codeGisementDebut);
        query.setParameter("codeGisementFin", codeGisementFin);
        query.setParameter("codeRayon", codeRayon);
        query.setParameter("pvtCode", pvtCode);
        return query.getResultList();
    }

    @Override
    public List<Article> getAllArticleInGisementBySecRayFamSousFamAndMag(
            Date dateDebut, Date dateFin, String codeSecteur, String codeRayon,
            String codeFamille, String codeSousFamille, long codeGisementDebut,
            long codeGisementFin, String pvtCode) {

        Query query = em
                .createQuery("SELECT a FROM Article a "
                        + "WHERE ( a.codeGisement  BETWEEN :gisDebut AND  :gisFin) "
                        + "AND a.pvtCode=:pvtCode AND a.secteur like :secteur "
                        + "AND a.rayon like :rayon AND a.famille like :famille "
                        + "AND a.sousFamille like :sousFamille AND a.actif = TRUE "
                        + "AND a.prixVenteTTCEnCours != 0  ORDER BY a.codeGisement,a.codeArticle ASC  ");

        query.setParameter("gisDebut", codeGisementDebut);
        query.setParameter("gisFin", codeGisementFin);
        query.setParameter("secteur", codeSecteur);
        query.setParameter("rayon", codeRayon);
        query.setParameter("famille", codeFamille);
        query.setParameter("sousFamille", codeSousFamille);
        query.setParameter("pvtCode", pvtCode);

        return query.getResultList();

    }

    @Override
    public List<Article> getAllArticleByFamilleAndMag(String codeFamille,
            String pvtCode) {
        Query query = em.createQuery("SELECT a FROM Article a "
                + "WHERE a.pvtCode=:pvtCode AND a.famille=:codeFamille "
                + "AND a.actif = TRUE AND a.prixVenteTTCEnCours != 0  "
                + "ORDER BY a.codeGisement,a.codeArticle ASC ");
        query.setParameter("codeFamille", codeFamille);
        query.setParameter("pvtCode", pvtCode);
        return query.getResultList();
    }

    @Override
    public List<Article> getAllArticleBySousFamilleAndMag(
            String codeSousFamille, String pvtCode) {
        Query query = em
                .createQuery("SELECT a FROM Article a "
                        + "WHERE a.pvtCode=:pvtCode AND a.sousFamille=:codeSousFamille "
                        + "AND a.actif = TRUE  AND a.prixVenteTTCEnCours != 0  "
                        + "ORDER BY a.codeGisement,a.codeArticle ASC");
        query.setParameter("codeSousFamille", codeSousFamille);
        query.setParameter("pvtCode", pvtCode);
        return query.getResultList();
    }

    @Override
    public List<Article> getAllArticleForMagActifMagasin(String pvtCode) {
        Query query = em.createQuery("SELECT a FROM Article a JOIN FETCH a.stock   WHERE a.actif = TRUE AND a.pvtCode=:pvtCode "
                + "AND a.prixVenteTTCEnCours != 0  AND a.inventaire=:rep "
                + "ORDER BY a.codeGisement,a.codeArticle ASC ");
        query.setParameter("pvtCode", pvtCode);
        query.setParameter("rep", false);
        return query.getResultList();
    }

    @Override
    public List<Article> getAllArticleForMagCommandableCentrale(String pvtCode) {
        Query query = em.createQuery("SELECT a FROM Article a "
                + "WHERE a.commandableCentrale = TRUE AND a.pvtCode=:pvtCode "
                + "AND a.prixVenteTTCEnCours != 0 ");
        query.setParameter("pvtCode", pvtCode);
        return query.getResultList();
    }

    @Override
    public List<Article> getAllArticleByTypeForMag(String pvtCode,
            TypeArticle typeArticle) {
        Query query = em.createQuery("SELECT a FROM Article a "
                + "WHERE a.typeArticle=:typeArticle AND a.pvtCode=:pvtCode "
                + "AND a.prixVenteTTCEnCours != 0 ");
        query.setParameter("typeArticle", typeArticle);
        query.setParameter("pvtCode", pvtCode);
        return query.getResultList();
    }

    @Override
    public List<Article> getAllArticleForCodeAnalityqueAndPvt(
            String codeAnalityque, String pvtCode) {
        Query query = em
                .createQuery("SELECT a FROM Article a "
                        + "WHERE a.codeAnalityque=:codeAnalityque AND a.pvtCode=:pvtCode "
                        + "AND a.prixVenteTTCEnCours != 0 ");
        query.setParameter("pvtCode", pvtCode);
        query.setParameter("codeAnalityque", codeAnalityque);
        return query.getResultList();
    }

    @Override
    public List<Article> getAllArticleForConsigneAndPvt(String pvtCode) {
        Query query = em
                .createQuery("SELECT a FROM Article a "
                        + "WHERE a.articeConsigne != '000000000' AND a.pvtCode=:pvtCode "
                        + "AND a.prixVenteTTCEnCours != 0 ");
        query.setParameter("pvtCode", pvtCode);
        return query.getResultList();
    }

    @Override
    public List<Article> getAllArticleInGisementAndPvt(long codeGisementDebut,
            long codeGisementFin, String pvtCode) {
        Query query = em
                .createQuery("SELECT a FROM Article a "
                        + "WHERE ( a.codeGisement  BETWEEN :codeGisementDebut AND  :codeGisementFin)  "
                        + "  AND a.pvtCode=:pvtCode AND a.prixVenteTTCEnCours != 0  "
                        + " ORDER BY a.codeGisement,a.codeArticle ASC");
        query.setParameter("codeGisementDebut", codeGisementDebut);
        query.setParameter("codeGisementFin", codeGisementFin);
        query.setParameter("pvtCode", pvtCode);
        return query.getResultList();
    }
    
    @Override
    public List<Article> getAllArticleInGisementAndPvtForInventaire(long codeGisementDebut,
            long codeGisementFin, String pvtCode) {
        Query query = em
                .createQuery("SELECT a FROM Article a JOIN FETCH a.stock"
                        + "WHERE ( a.codeGisement  BETWEEN :codeGisementDebut AND  :codeGisementFin)  "
                        + "  AND a.pvtCode=:pvtCode AND a.prixVenteTTCEnCours != 0  AND a.inventaire=:rep "
                        + " ORDER BY a.codeGisement,a.codeArticle ASC");
        query.setParameter("codeGisementDebut", codeGisementDebut);
        query.setParameter("codeGisementFin", codeGisementFin);
        query.setParameter("pvtCode", pvtCode);
        query.setParameter("rep", false);
        return query.getResultList();
    }

    @Override
    public List<Article> executeQueryForMag(String query, String pvtCode) {

        Query q = em.createQuery(query).setParameter("pvtCode", pvtCode);
        return q.getResultList();
    }

    @Override
    public void UpdatePrixZero() {
        Query query = em.createQuery("UPDATE Article a SET promo = null "
                + "WHERE a.promo <> '' AND a.prixVentePromoTTC = '0'");
        query.executeUpdate();
    }

    @Override
    public List<Article> getAllArticleForPromoAndPVT(String promo,
            String pvtCode) {
        Query query = em.createQuery("SELECT a FROM Article a "
                + "WHERE a.promo=:promo  AND a.pvtCode=:pvtCode "
                + "AND a.prixVenteTTCEnCours != 0 "
                + "ORDER BY a.codeGisement,a.codeArticle ASC ");
        query.setParameter("promo", promo);
        query.setParameter("pvtCode", pvtCode);
        return query.getResultList();
    }

    @Override
    public String getDesignationForArticle(String pvtCode, String codeArticle) {
        try {
            Query query = em
                    .createQuery("SELECT a.designation FROM Article a WHERE  "
                            + " a.codeArticle=:codeArticle "
                            + "AND a.pvtCode=:pvtCode AND a.prixVenteTTCEnCours != 0 ");
            query.setParameter("codeArticle", codeArticle);
            query.setParameter("pvtCode", pvtCode);
            return (String) query.getSingleResult();
        } catch (Exception e) {
            return "pas de designation";
        }
    }

    @Override
    public List<String> getAllCodeArticleForMagUpdated(String pvtCode) {
        try {
            System.out.println("getALL ART MAG Updated  " + pvtCode);
            Query query = em.createQuery("SELECT a.codeArticle FROM Article a "
                    + "WHERE a.pvtCode=:pvtCode  AND (a.modifier = TRUE "
                    + "OR a.nouvelArticle = TRUE OR a.prixModifier = TRUE ) "
                    + "AND a.prixVenteTTCEnCours != 0 ");
            query.setParameter("pvtCode", pvtCode);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
     * PROPHYL.COM
     */
    @Override
    public Article getArticlePrincipal(final Article entitySecondaire) {
        Article artPrincipal;
        if (entitySecondaire.isPrincipal()) {
            artPrincipal = getArticleWithStock(new ArticleMagRefPK(entitySecondaire.getPvtCode(),entitySecondaire.getCodeArticle()));
        } else {
            artPrincipal = getArticleWithStock(new ArticleMagRefPK(
                    entitySecondaire.getPvtCode(),
                    entitySecondaire.getArticlePrincipal()));
            if (!artPrincipal.isPrincipal()) {
                while (!artPrincipal.isPrincipal()) {
                    artPrincipal = getArticleWithStock(new ArticleMagRefPK(
                            entitySecondaire.getPvtCode(),
                            artPrincipal.getArticlePrincipal()));
                }
            }
        }
        return artPrincipal;
    }

    @Override
    public List<Article> getAllArticleSecondaire(final Article entity) {
        Query query = em
                .createQuery("SELECT a FROM Article a JOIN FETCH a.stock "
                        + "WHERE a.pvtCode=:pvtCode AND a.articlePrincipal=:artPrincipal "
                        + "AND a.prixVenteTTCEnCours != 0 ORDER BY a.codeArticle ASC ");
        query.setParameter("pvtCode", entity.getPvtCode());
        query.setParameter("artPrincipal", entity.getCodeArticle());
        return query.getResultList();
    }

    @Override
    public List<Article> getLastArticleSecondaire(final Article entity,
            int limit) {
        Query query = em
                .createQuery("SELECT a FROM Article a JOIN FETCH a.stock "
                        + "WHERE a.pvtCode=:pvtCode AND a.articlePrincipal=:artPrincipal "
                        + "AND a.prixVenteTTCEnCours != 0 ORDER BY a.codeArticle DESC ");
        query.setParameter("pvtCode", entity.getPvtCode());
        query.setParameter("artPrincipal", entity.getCodeArticle());
        query.setMaxResults(limit);
        return query.getResultList();
    }

    @Override
    public List<Article> getListArticleStockZero(Date dateDebut, Date dateFin,
            String codeSecteur, String codeRayon, String codeFamille,
            String codeSousFamille, long codeGisementDebut,
            long codeGisementFin, String pvtCode) {

        Query query = em
                .createQuery("SELECT a FROM Article a JOIN FETCH a.stock "
                        + " WHERE ( a.codeGisement  BETWEEN :gisDebut AND  :gisFin)"
                        + " AND a.pvtCode=:pvtCode AND a.secteur like :secteur"
                        + " AND a.rayon like :rayon AND a.famille like :famille"
                        + " AND a.sousFamille like :sousFamille AND a.actif = TRUE"
                        + " AND a.prixVenteTTCEnCours != 0 AND a.stock.qteComptable = 0"
                        + // " AND ( a.stock.dateDerniereEntrer BETWEEN :dateDebut AND :dateFin )"
                        // +
                        " ORDER BY a.secteur, a.rayon, a.famille, a.sousFamille ");

        // query.setParameter("dateDebut", dateDebut);
        // query.setParameter("dateFin", dateFin);
        query.setParameter("gisDebut", codeGisementDebut);
        query.setParameter("gisFin", codeGisementFin);
        query.setParameter("secteur", codeSecteur);
        query.setParameter("rayon", codeRayon);
        query.setParameter("famille", codeFamille);
        query.setParameter("sousFamille", codeSousFamille);
        query.setParameter("pvtCode", pvtCode);

        return query.getResultList();
    }

    @Override
    public List<String> getAllArticleForMagAndPVTAndLAP(String promo,
            String pvtCode) {
        List<String> list = null;
        try {
            Query query = em
                    .createNativeQuery("SELECT DISTINCT(a.CODE_ARTICLE_PK) "
                            + "FROM Article a JOIN table_liaison_promotion_pvt p "
                            + "ON (a.CODE_MAGASIN_PK = p.CODE_MAGASIN_FK) "
                            + "JOIN lien_article_promo l "
                            + "ON (a.CODE_ARTICLE_PK = l.CODE_ARTICLE_FK AND p.CODE_PROMO_FK = l.PROMO_FK) "
                            + "WHERE a.PROMO_FK is null "
                            + "AND a.CODE_MAGASIN_PK=:pvtCode "
                            + "AND p.CODE_PROMO_FK=:promo "
                            + "AND a.PRIX_VENTE_TTC_EN_COURS != 0 "
                            + "ORDER BY a.CODE_ARTICLE_PK ASC ");
            query.setParameter("promo", promo);
            query.setParameter("pvtCode", pvtCode);
            list = (List<String>) query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<String> getAllArticleForLienPromoAndPVT(String promo, String pvtCode) {
        List<String> list = null;
        try {
            Query query = em
                    .createNativeQuery("SELECT DISTINCT a.CODE_ARTICLE_PK "
                            + "FROM article a, lien_article_promo l, table_liaison_promotion_pvt t, promotion p "
                            + "WHERE a.CODE_ARTICLE_PK = l.CODE_ARTICLE_FK "
                            + "AND l.PROMO_FK = t.CODE_PROMO_FK "
                            + "AND t.CODE_MAGASIN_FK = a.CODE_MAGASIN_PK "
                            + "AND p.CODE_PROMO_PK = l.PROMO_FK "
                            + "AND a.CODE_MAGASIN_PK = :pvtCode "
                            + "AND p.CODE_PROMO_PK like :promo "
                            + "AND a.PRIX_VENTE_TTC_EN_COURS != 0 "
                            + "ORDER BY a.CODE_GISEMENT,a.CODE_ARTICLE_PK");

            query.setParameter("promo", promo);
            query.setParameter("pvtCode", pvtCode);
            list = (List<String>) query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

    @Override
    public List<String> findAllArticleInInventaireNonClot(String pvtCode) {
        List<String> list = null;
        try {
            Query query = em
                    .createNativeQuery("select d.CODE_ARTICLE_FK "
                            + "from gpv3000.inventaire_entete e "
                            + "join gpv3000.table_liaison_inventaire_entete_detail l "
                            + "on e.ENTETE_INVENTAIRE_PK=l.ENTETE_INVENTAIRE_FK "
                            + "join gpv3000.inventaire_detail d "
                            + "on d.DETAIL_INVENTAIRE_PK=l.DETAIL_INVENTAIRE_FK "
                            + "where e.SI_CLOTURER != 0 AND e.SI_LANCER = 1 "
                            + "AND e.CODE_MAGASIN_FK like :pvtCode ");

            query.setParameter("pvtCode", pvtCode);
            list = (List<String>) query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

    @Override
    public List<Article> getAllArticleByMagForUpdate(String pvt) {
        try {
            System.out.println("getALL ART MAG Updated  " + pvt);
            Query query = em.createQuery("SELECT a FROM Article a "
                    + "WHERE a.pvtCode=:pvtCode  AND (a.modifier = TRUE "
                    + "OR a.nouvelArticle = TRUE OR a.prixModifier = TRUE ) "
                    + "AND a.prixVenteTTCEnCours != 0 ");
            query.setParameter("pvtCode", pvt);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Article> getAllArticleForPromoAndMag(String promo,
            String pvtCode) {
        Query query = em.createQuery("SELECT a FROM Article a "
                + "WHERE a.promo=:promo  AND a.pvtCode=:pvtCode  "
                + "ORDER BY a.codeGisement,a.codeArticle ASC ");
        query.setParameter("promo", promo);
        query.setParameter("pvtCode", pvtCode);
        return query.getResultList();
    }

    @Override
    public List<Article> getAllArticleByMagAndPromo(String promo, String pvtCode) {
        Query query = em.createQuery("SELECT a FROM Article a "
                + "WHERE a.promo=:promo  AND a.pvtCode=:pvtCode AND a.prixVenteTTCEnCours != 0");
        query.setParameter("promo", promo);
        query.setParameter("pvtCode", pvtCode);
        return query.getResultList();
    }

    @Override
    public List<String> getAllCodeArticleByMagAndPromo(String promo, String pvtCode) {
        Query query = em.createNativeQuery("SELECT a.CODE_ARTICLE_PK FROM ARTICLE a "
                + "WHERE a.PROMO_FK=:promo  AND a.CODE_MAGASIN_PK=:pvtCode ");
        query.setParameter("promo", promo);
        query.setParameter("pvtCode", pvtCode);
        return (List<String>) query.getResultList();
    }

    @Override
    public List<String> getAllArticleByHistorique(String promo, String pvtCode, Date debutPromo) {
        List<String> list = null;
        try {
            Query query = em
                    .createNativeQuery("SELECT h.CODE_ARTICLE_FK FROM historique_promo h where h.CODE_PROMO_PK=:promo "
                            + "and h.CODE_MAGASIN_FK=:pvtCode AND h.DATE_DEBUT_PROMO=:debutPromo");

            query.setParameter("promo", promo);
            query.setParameter("pvtCode", pvtCode);
            query.setParameter("debutPromo", debutPromo);
            list = (List<String>) query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public String getArticleByCodeAndMag(String mag, String codeArticle) {
        Query query2 = em.createNativeQuery("SELECT DESIGNATION FROM article where CODE_MAGASIN_PK =:mag and CODE_ARTICLE_PK =:codeArticle ;");
        query2.setParameter("mag", mag);
        query2.setParameter("codeArticle", codeArticle);
        String lib = (String) query2.getSingleResult();
        return lib;
    }

    @Override
    public List<Article> findAllArticleByCodeArticleKeyWord(String keyword, String pvtCode) {
        Query query = em.createQuery("SELECT a FROM Article a JOIN FETCH a.stock  where a.pvtCode=:pvtCode AND a.prixVenteTTCEnCours != 0 AND a.designation LIKE :keyword ");
        query.setParameter("pvtCode", pvtCode);
        query.setParameter("keyword", keyword + "%");
        return query.getResultList();
    }

    @Override
    public List<ArticleDTO> getAllArticleDTOForMag(String pvtCode) {

        List<ArticleDTO> resultList = new ArrayList<>();
        Query query = em.createNativeQuery("SELECT a.CODE_ARTICLE_PK,a.CODE_MAGASIN_PK, a.ARTICLE_CONSIGNE, a.LIBELLE_REDUIT, "
                + " a.FAMILLE_FK, a.SOUS_FAMILLE_FK, a.RAYON_FK, a.SECTEUR_FK, a.PRIX_DU_LOT, a.QUANTITE_DANS_LOT,  "
                + "a.PRIX_VENTE_TTC_EN_COURS,a.PRIX_VENTE_PROMO_TTC, a.PROMO_FK ,a.TYPE_PROMO,a.TAUX_TVA_FK "
                + "FROM  article a WHERE a.CODE_MAGASIN_PK=:pvtCode ");
        query.setParameter("pvtCode", pvtCode);
        List<Object[]> resultObject = query.getResultList();
        if (!resultObject.isEmpty()) {
            resultObject.stream().map((o) -> {
                ArticleDTO art = new ArticleDTO();
                art.setArticeConsigne((String) o[2]);
                art.setCodeArticle((String) o[0]);
                art.setFamille((String) o[4]);
                art.setLibelReduit((String) o[3]);
                art.setPrixDuLot((Integer) o[8]);
                art.setPrixVentePromoTTC((Integer) o[11]);
                art.setPrixVenteTTCEnCours((Integer) o[10]);
                art.setPromo((String) o[12]);
                art.setPvtCode((String) o[1]);
                art.setQuantiteLot((Integer) o[9]);
                art.setRayon((String) o[6]);
                art.setSecteur((String) o[7]);
                art.setSousFamille((String) o[5]);
                art.setTauxTVA((String) o[14]);
                art.setTypePromo((Integer) o[13]);
                return art;
            }).forEach((art) -> {
                //System.out.println("@@@@@@@@@@@@@@ art : "+art.toString());
                resultList.add(art);
            });
        }
        return resultList;
    }

    @Override
    public List<CommandeDTO> findAllArticleForPromoAndMag(String promo, String pvtCode) {
        List<CommandeDTO> resultList = new ArrayList<>();
        Query query = em.createNativeQuery("SELECT a.CODE_GISEMENT , a.CODE_ARTICLE_PK,a.CODE_MAGASIN_PK, a.ARTICLE_CONSIGNE, a.LIBELLE_REDUIT, "
                + " a.FAMILLE_FK, a.SOUS_FAMILLE_FK, a.RAYON_FK, a.SECTEUR_FK, a.PRIX_DU_LOT, a.QUANTITE_DANS_LOT,  "
                + "a.PRIX_VENTE_TTC_EN_COURS,a.PRIX_VENTE_PROMO_TTC, a.PROMO_FK ,a.TYPE_PROMO,a.TAUX_TVA_FK "
                + "FROM  article a WHERE a.CODE_MAGASIN_PK=:pvtCode ");
        query.setParameter("pvtCode", pvtCode);
        List<Object[]> resultObject = query.getResultList();
        return resultList;
    }

}
