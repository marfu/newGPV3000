package ci.prosuma.prosumagpv.metier.dao.mysql.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ci.prosuma.prosumagpv.entity.stock.DetailMouvement;
import ci.prosuma.prosumagpv.metier.dao.mysql.IDetailMouvementDAO;

@Stateless
@Local(IDetailMouvementDAO.class)
public class DetailMouvementDAOImpl implements IDetailMouvementDAO,
        Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @PersistenceContext
    protected EntityManager em;

    @Override
    public DetailMouvement createOrUpdateDetailMouvement(DetailMouvement ei) {
        DetailMouvement temp = getDetailMouvement(ei.getId());
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
    public DetailMouvement getDetailMouvement(long id) {
        try {
            return em.find(DetailMouvement.class, id);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean removeDetailMouvement(DetailMouvement ei) {
        DetailMouvement a = getDetailMouvement(ei.getId());
        if (a != null) {
            em.remove(a);
            return true;
        } else {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DetailMouvement> getAllDetailMouvementForEntete(long enteteId) {
        Query query = em.createQuery("SELECT d FROM DetailMouvement d  WHERE d.enteteMouvement=:id");
        query.setParameter("id", enteteId);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DetailMouvement> getAllDetailMouvement() {
        Query query = em.createQuery("SELECT d FROM EnteteMouvement  d ");
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DetailMouvement> getAllDetailMouvementForGisement(
            long gisementDebut, long gisementFin, String codeMag,
            Date dateDebut, Date dateFin) {
        Query query = em.createQuery("SELECT d FROM DetailMouvement d  JOIN d.enteteMouvement e "
                + "WHERE (e.dateMouvement BETWEEN :dateDebut AND :dateFin) "
                + "AND e.pvt.pvtCode=:codeMag  "
                + "AND d.article.codeGisement >=:gisementDebut "
                + "AND d.article.codeGisement <=:gisementFin  "
                + "AND ( "
                + "e.origineMouvement.code='DEMAR' "
                + "OR e.origineMouvement.code='CAISS'  "
                + "OR e.origineMouvement.code='RECEP' "
                + "OR e.origineMouvement.code='RECD'" + " )  ");
        query.setParameter("gisementDebut", gisementDebut);
        query.setParameter("gisementFin", gisementFin);
        query.setParameter("codeMag", codeMag);
        query.setParameter("dateDebut", dateDebut);
        query.setParameter("dateFin", dateFin);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DetailMouvement> getAllDetailMouvementForSecteur(
            String secteur, String codeMag, Date dateDebut, Date dateFin) {
//		Query query = em
//				.createQuery("SELECT d FROM  Article a ,  EnteteMouvement  e  JOIN e.mouvements  d "
//						+ "WHERE (e.dateMouvement BETWEEN :dateDebut AND :dateFin) "
//						+ "AND e.pvt.pvtCode=:codeMag "
//						+ "AND a.codeArticle = d.article.codeArticle "
//						+ "AND  a.secteur=:secteur  "
//						+ "AND ( "
//						+ "e.origineMouvement.code='DEMAR' "
//						+ "OR e.origineMouvement.code='CAISS'  "
//						+ "OR e.origineMouvement.code='RECEP' "
//						+ "OR e.origineMouvement.code='RECD' " + ")   ");
//		query.setParameter("secteur", secteur);
//		query.setParameter("codeMag", codeMag);
//		query.setParameter("dateDebut", dateDebut);
//		query.setParameter("dateFin", dateFin);
        Query query = em
                .createQuery("SELECT d FROM DetailMouvement d  JOIN d.enteteMouvement e join d.article a "
                        + "WHERE (e.dateMouvement BETWEEN :dateDebut AND :dateFin) "
                        + "AND e.pvt.pvtCode=:codeMag "
                        + "AND a.codeArticle = d.article.codeArticle "
                        + "AND  a.secteur=:secteur  "
                        + "AND ( "
                        + "e.origineMouvement.code='DEMAR' "
                        + "OR e.origineMouvement.code='CAISS'  "
                        + "OR e.origineMouvement.code='RECEP' "
                        + "OR e.origineMouvement.code='RECD' " + ")   ");
        query.setParameter("secteur", secteur);
        query.setParameter("codeMag", codeMag);
        query.setParameter("dateDebut", dateDebut);
        query.setParameter("dateFin", dateFin);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DetailMouvement> getAllDetailMouvementForRayon(String rayon,
            String codeMag, Date dateDebut, Date dateFin) {
        Query query = em
                .createQuery("SELECT d FROM Article a ,  EnteteMouvement  e  JOIN e.mouvements  d "
                        + "WHERE (e.dateMouvement BETWEEN :dateDebut AND :dateFin) "
                        + "AND e.pvt.pvtCode=:codeMag "
                        + "AND a.codeArticle = d.article.codeArticle "
                        + "AND  a.rayon=:rayon  "
                        + "AND ( "
                        + "e.origineMouvement.code='DEMAR' "
                        + "OR e.origineMouvement.code='CAISS'  "
                        + "OR e.origineMouvement.code='RECEP' "
                        + "OR e.origineMouvement.code='RECD'" + " )   ");
        query.setParameter("rayon", rayon);
        query.setParameter("codeMag", codeMag);
        query.setParameter("dateDebut", dateDebut);
        query.setParameter("dateFin", dateFin);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DetailMouvement> getAllDetailMouvementForArticle(
            String codeArticle, String codeMag) {
        Query query = em
                .createQuery("SELECT d FROM  EnteteMouvement  a  JOIN a.mouvements  d "
                        + "WHERE  d.article.pvtCode=:codeMag "
                        + "AND d.article.codeArticle=:codeArticle  ");
        query.setParameter("codeArticle", codeArticle);
        query.setParameter("codeMag", codeMag);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DetailMouvement> getAllDetailMouvementForSecteurFilterRayon(
            String secteur, String rayon, String codeMag, Date dateDebut,
            Date dateFin) {
        Query query = em
                .createQuery("SELECT d FROM Article a , EnteteMouvement e JOIN e.mouvements d "
                        + "WHERE ( DATE(e.dateMouvement) BETWEEN :dateDebut AND :dateFin) "
                        + "AND e.pvt.pvtCode=:codeMag "
                        + "AND a.codeArticle = d.article.codeArticle "
                        + "AND a.secteur=:secteur "
                        + "AND a.rayon=:rayon "
                        + "AND ( "
                        + "e.origineMouvement.code='DEMAR' "
                        + "OR e.origineMouvement.code='CAISS' "
                        + "OR e.origineMouvement.code='RECEP' "
                        + "OR e.origineMouvement.code='RECD'" + " ) ");
        query.setParameter("secteur", secteur);
        query.setParameter("rayon", rayon);
        query.setParameter("codeMag", codeMag);
        query.setParameter("dateDebut", dateDebut);
        query.setParameter("dateFin", dateFin);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DetailMouvement> getAllDetailMouvementBySecteurAndRayon(
            String secteur, String rayon, String codeMag, Date dateDebut,
            Date dateFin) {
        Query query = em
                .createQuery("SELECT d FROM Article a , EnteteMouvement e JOIN e.mouvements d "
                        + "WHERE ( DATE(e.dateMouvement) BETWEEN :dateDebut AND :dateFin) "
                        + "AND e.pvt.pvtCode=:codeMag "
                        + "AND a.codeArticle = d.article.codeArticle "
                        + "AND a.secteur LIKE:secteur "
                        + "AND a.rayon LIKE:rayon "
                        + "AND ( "
                        + "e.origineMouvement.code='DEMAR' "
                        + "OR e.origineMouvement.code='CAISS' "
                        + "OR e.origineMouvement.code='RECEP' "
                        + "OR e.origineMouvement.code='RECD' " + ") ");
        query.setParameter("secteur", secteur);
        query.setParameter("rayon", rayon);
        query.setParameter("codeMag", codeMag);
        query.setParameter("dateDebut", dateDebut);
        query.setParameter("dateFin", dateFin);
        return query.getResultList();
    }

    /**
     * PROPHYL.COM
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<DetailMouvement> getAllDetailMouvementToUpdateStatDem(
            String secteur, String rayon, String codeMag, Date dateDebut,
            Date dateFin) {
        List<DetailMouvement> list = null;
        try {
            Query query = em
                    .createNativeQuery(
                            "SELECT detailmouv3_.* "
                            + "FROM ARTICLE article0_ "
                            + "JOIN DETAIL_MOUVEMENT detailmouv3_ "
                            + "ON "
                            + "detailmouv3_.CODE_ARTICLE_FK=article0_.CODE_ARTICLE_PK "
                            + "AND  detailmouv3_.CODE_MAGASIN_FK=article0_.CODE_MAGASIN_PK "
                            + "JOIN TABLE_LIAISON_MOUVEMENT_ENTETE_DETAIL mouvements2_ "
                            + "ON "
                            + "mouvements2_.DETAIL_MOUVEMENT_FK=detailmouv3_.DETAIL_MOUVEMENT_PK  "
                            + "JOIN ENTETE_MOUVEMENT entetemouv1_ "
                            + "ON "
                            + "entetemouv1_.ENTETE_MOUVEMENT_PK=mouvements2_.ENTETE_MOUVEMENT_FK "
                            + "AND detailmouv3_.CODE_MAGASIN_FK=entetemouv1_.CODE_MAGASIN_FK "
                            + "WHERE  "
                            + "( DATE(entetemouv1_.DATE_MOUVEMENT) BETWEEN :dateDebut AND :dateFin ) "
                            + "AND entetemouv1_.CODE_MAGASIN_FK LIKE:codeMag "
                            + "AND article0_.SECTEUR_FK LIKE:secteur "
                            + "AND article0_.RAYON_FK LIKE:rayon  "
                            + "AND "
                            + "detailmouv3_.TYPE_MOUVEMENT_FK IN ('E+', 'E-', 'D+',"
                            + "'D-', 'CLIEN', 'ECHGR', 'EXPER', 'MANUT', 'PERIM', "
                            + "'DLV+' " + ") ", DetailMouvement.class);

            query.setParameter("rayon", rayon);
            query.setParameter("secteur", secteur);
            query.setParameter("codeMag", codeMag);
            query.setParameter("dateDebut", dateDebut);
            query.setParameter("dateFin", dateFin);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    /*
     * PROPHYL.COM
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<DetailMouvement> getAllDetailMouvementForGisementStatDem(
            long gisementDebut, long gisementFin, String codeMag,
            Date dateDebut, Date dateFin) {
        List<DetailMouvement> list = null;
        try {
            Query query = em
                    .createNativeQuery(
                            "SELECT detailmouv3_.* "
                            + "FROM ARTICLE article0_ "
                            + "JOIN DETAIL_MOUVEMENT detailmouv3_ "
                            + "ON "
                            + "detailmouv3_.CODE_ARTICLE_FK=article0_.CODE_ARTICLE_PK "
                            + "AND  detailmouv3_.CODE_MAGASIN_FK=article0_.CODE_MAGASIN_PK "
                            + "JOIN TABLE_LIAISON_MOUVEMENT_ENTETE_DETAIL mouvements2_ "
                            + "ON "
                            + "mouvements2_.DETAIL_MOUVEMENT_FK=detailmouv3_.DETAIL_MOUVEMENT_PK  "
                            + "JOIN ENTETE_MOUVEMENT entetemouv1_ "
                            + "ON "
                            + "entetemouv1_.ENTETE_MOUVEMENT_PK=mouvements2_.ENTETE_MOUVEMENT_FK "
                            + "AND detailmouv3_.CODE_MAGASIN_FK=entetemouv1_.CODE_MAGASIN_FK "
                            + "WHERE  "
                            + "( DATE(entetemouv1_.DATE_MOUVEMENT) BETWEEN :dateDebut AND :dateFin ) "
                            + "AND entetemouv1_.CODE_MAGASIN_FK LIKE:codeMag "
                            + "AND ( article0_.code_gisement BETWEEN :gisementDebut AND :gisementFin ) "
                            + "AND "
                            + "detailmouv3_.TYPE_MOUVEMENT_FK IN ('E+', 'E-', 'D+', "
                            + "'D-', 'CLIEN', 'ECHGR', 'EXPER', 'MANUT', 'PERIM', "
                            + "'DLV+' " + ") ", DetailMouvement.class);

            query.setParameter("gisementDebut", gisementDebut);
            query.setParameter("gisementFin", gisementFin);
            query.setParameter("codeMag", codeMag);
            query.setParameter("dateDebut", dateDebut);
            query.setParameter("dateFin", dateFin);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DetailMouvement> getAllDetailMouvementToUpdateStatDem(
            String secteur, String rayon, String codeMag, Date dateDebut,
            Date dateFin, List<String> qteFiltre) {
        List<DetailMouvement> list = null;
        try {
            Query query = em
                    .createNativeQuery(
                            "SELECT detailmouv3_.* "
                            + "FROM ARTICLE article0_ "
                            + "JOIN DETAIL_MOUVEMENT detailmouv3_ "
                            + "ON "
                            + "detailmouv3_.CODE_ARTICLE_FK=article0_.CODE_ARTICLE_PK "
                            + "AND  detailmouv3_.CODE_MAGASIN_FK=article0_.CODE_MAGASIN_PK "
                            + "JOIN ENTETE_MOUVEMENT entetemouv1_ "
                            + "ON "
                            + "entetemouv1_.ENTETE_MOUVEMENT_PK=detailmouv3_.ENTETE_MOUVEMENT_FK "
                            + "WHERE  "
                            + "( DATE(entetemouv1_.DATE_MOUVEMENT) BETWEEN :dateDebut AND :dateFin ) "
                            + "AND entetemouv1_.CODE_MAGASIN_FK =:codeMag "
                            + "AND article0_.SECTEUR_FK LIKE:secteur "
                            + "AND article0_.RAYON_FK LIKE:rayon  "
                            + "AND ( "
                            + "detailmouv3_.TYPE_MOUVEMENT_FK IN (:qteFiltre) )",
                            DetailMouvement.class);

            query.setParameter("rayon", rayon);
            query.setParameter("secteur", secteur);
            query.setParameter("codeMag", codeMag);
            query.setParameter("dateDebut", dateDebut);
            query.setParameter("dateFin", dateFin);
            query.setParameter("qteFiltre", qteFiltre);

            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DetailMouvement> getAllDetailMouvementForGisementStatDem(
            long gisementDebut, long gisementFin, String codeMag,
            Date dateDebut, Date dateFin, List<String> qteFiltre) {
        List<DetailMouvement> list = null;
        try {
            Query query = em
                    .createNativeQuery(
                            "SELECT detailmouv3_.* "
                            + "FROM ARTICLE article0_ "
                            + "JOIN DETAIL_MOUVEMENT detailmouv3_ "
                            + "ON "
                            + "detailmouv3_.CODE_ARTICLE_FK=article0_.CODE_ARTICLE_PK "
                            + "AND  detailmouv3_.CODE_MAGASIN_FK=article0_.CODE_MAGASIN_PK "
                            + "JOIN TABLE_LIAISON_MOUVEMENT_ENTETE_DETAIL mouvements2_ "
                            + "ON "
                            + "mouvements2_.DETAIL_MOUVEMENT_FK=detailmouv3_.DETAIL_MOUVEMENT_PK  "
                            + "JOIN ENTETE_MOUVEMENT entetemouv1_ "
                            + "ON "
                            + "entetemouv1_.ENTETE_MOUVEMENT_PK=mouvements2_.ENTETE_MOUVEMENT_FK "
                            + "AND detailmouv3_.CODE_MAGASIN_FK=entetemouv1_.CODE_MAGASIN_FK "
                            + "WHERE  "
                            + "( DATE(entetemouv1_.DATE_MOUVEMENT) BETWEEN :dateDebut AND :dateFin ) "
                            + "AND entetemouv1_.CODE_MAGASIN_FK LIKE:codeMag "
                            + "AND ( article0_.code_gisement BETWEEN :gisementDebut AND :gisementFin ) "
                            + "AND ( "
                            + "detailmouv3_.TYPE_MOUVEMENT_FK IN (:qteFiltre) )",
                            DetailMouvement.class);

            query.setParameter("gisementDebut", gisementDebut);
            query.setParameter("gisementFin", gisementFin);
            query.setParameter("codeMag", codeMag);
            query.setParameter("dateDebut", dateDebut);
            query.setParameter("dateFin", dateFin);
            query.setParameter("qteFiltre", qteFiltre);

            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DetailMouvement> getAllDetailMouvementForIntervalDate(
            String codeMag, Date dateDebut, Date dateFin, List<String> qteFiltre) {
        List<DetailMouvement> list = null;
        try {
            Query query = em
                    .createNativeQuery(
                            "SELECT detailmouv3_.* "
                            + "FROM ARTICLE article0_ "
                            + "JOIN DETAIL_MOUVEMENT detailmouv3_ "
                            + "ON "
                            + "detailmouv3_.CODE_ARTICLE_FK=article0_.CODE_ARTICLE_PK "
                            + "AND  detailmouv3_.CODE_MAGASIN_FK=article0_.CODE_MAGASIN_PK "
                            + "JOIN TABLE_LIAISON_MOUVEMENT_ENTETE_DETAIL mouvements2_ "
                            + "ON "
                            + "mouvements2_.DETAIL_MOUVEMENT_FK=detailmouv3_.DETAIL_MOUVEMENT_PK  "
                            + "JOIN ENTETE_MOUVEMENT entetemouv1_ "
                            + "ON "
                            + "entetemouv1_.ENTETE_MOUVEMENT_PK=mouvements2_.ENTETE_MOUVEMENT_FK "
                            + "AND detailmouv3_.CODE_MAGASIN_FK=entetemouv1_.CODE_MAGASIN_FK "
                            + "WHERE  "
                            + "( DATE(entetemouv1_.DATE_MOUVEMENT) BETWEEN :dateDebut AND :dateFin ) "
                            + "AND entetemouv1_.CODE_MAGASIN_FK LIKE:codeMag "
                            + "AND ( "
                            + "detailmouv3_.TYPE_MOUVEMENT_FK IN (:qteFiltre) )",
                            DetailMouvement.class);

            query.setParameter("codeMag", codeMag);
            query.setParameter("dateDebut", dateDebut);
            query.setParameter("dateFin", dateFin);
            query.setParameter("qteFiltre", qteFiltre);

            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DetailMouvement> getAllDetailMouvementForIntervalDate(
            String codeMag, Date dateDebut, Date dateFin,
            List<String> qteFiltre, String secteur, String rayon,
            String famille, String sousFamille, String gisDebut, String gisFin) {
        List<DetailMouvement> list = null;
        try {
            Query query = em
                    .createNativeQuery(
                            "SELECT detailmouv3_.* "
                            + "FROM ARTICLE article0_ "
                            + "JOIN DETAIL_MOUVEMENT detailmouv3_ "
                            + "ON "
                            + "detailmouv3_.CODE_ARTICLE_FK=article0_.CODE_ARTICLE_PK "
                            + "AND  detailmouv3_.CODE_MAGASIN_FK=article0_.CODE_MAGASIN_PK "
                            + "JOIN TABLE_LIAISON_MOUVEMENT_ENTETE_DETAIL mouvements2_ "
                            + "ON "
                            + "mouvements2_.DETAIL_MOUVEMENT_FK=detailmouv3_.DETAIL_MOUVEMENT_PK  "
                            + "JOIN ENTETE_MOUVEMENT entetemouv1_ "
                            + "ON "
                            + "entetemouv1_.ENTETE_MOUVEMENT_PK=mouvements2_.ENTETE_MOUVEMENT_FK "
                            + "AND detailmouv3_.CODE_MAGASIN_FK=entetemouv1_.CODE_MAGASIN_FK "
                            + "WHERE ( DATE(entetemouv1_.DATE_MOUVEMENT) BETWEEN :dateDebut AND :dateFin ) "
                            + "AND entetemouv1_.CODE_MAGASIN_FK LIKE:codeMag "
                            + "AND ( detailmouv3_.TYPE_MOUVEMENT_FK IN (:qteFiltre) ) "
                            + "AND article0_.SECTEUR_FK LIKE :secteur "
                            + "AND article0_.RAYON_FK LIKE :rayon "
                            + "AND article0_.FAMILLE_FK LIKE :famille "
                            + "AND article0_.SOUS_FAMILLE_FK LIKE :sousFamille "
                            + "AND article0_.CODE_GISEMENT BETWEEN :gisDebut AND :gisFin "
                            + " ", DetailMouvement.class);

            query.setParameter("codeMag", codeMag);
            query.setParameter("dateDebut", dateDebut);
            query.setParameter("dateFin", dateFin);
            query.setParameter("qteFiltre", qteFiltre);
            query.setParameter("secteur", secteur);
            query.setParameter("rayon", rayon);
            query.setParameter("famille", famille);
            query.setParameter("sousFamille", sousFamille);
            query.setParameter("gisDebut", gisDebut);
            query.setParameter("gisFin", gisFin);

            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

}
