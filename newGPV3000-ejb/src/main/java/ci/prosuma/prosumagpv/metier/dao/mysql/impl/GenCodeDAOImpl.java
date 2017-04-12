package ci.prosuma.prosumagpv.metier.dao.mysql.impl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ci.prosuma.prosumagpv.entity.Article;
import ci.prosuma.prosumagpv.entity.GenCode;
import ci.prosuma.prosumagpv.entity.dto.GenCodeDTO;
import ci.prosuma.prosumagpv.entity.util.EnumerationParam.CategorieGenCode;
import ci.prosuma.prosumagpv.metier.dao.mysql.IGenCodeDAO;
import java.math.BigInteger;
import java.util.ArrayList;

@Stateless
@Local(IGenCodeDAO.class)
public class GenCodeDAOImpl implements IGenCodeDAO, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @PersistenceContext
    protected EntityManager em;

    @Override
    public GenCode createOrUpdateGenCode(GenCode entity) {
        GenCode temp = getGenCode(entity.getId());
        if (temp != null) {
            em.merge(entity);
            em.flush();
            return entity;
        } else {
            em.persist(entity);
            em.flush();
            return entity;
        }
    }

    @Override
    public GenCode getGenCode(long id) {

        Query query = em.createQuery("SELECT g FROM GenCode g JOIN FETCH  g.article   where g.id=:id");
        query.setParameter("id", id);
        try {
            return (GenCode) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
//        try {
//            return em.find(GenCode.class, id);
//        } catch (Exception e) {
//            System.out.println("DAO null");
//            return null;
//
//        }
    }

    @Override
    public boolean removeGenCode(GenCode entity) {
        GenCode a = getGenCode(entity.getId());
        if (null != a) {
            em.remove(a);
            return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<GenCode> getAllGenCodeForArticle(String codeArticle,String pvtCode) {
        Query q = em.createQuery("SELECT  g From GenCode g JOIN FETCH g.article  WHERE g.article.codeArticle=:codeArticle AND g.article.pvtCode=:pvtCode");
        q.setParameter("codeArticle", codeArticle);
        q.setParameter("pvtCode", pvtCode);
        return q.getResultList();

    }

    @Override
    public GenCode getFirstGenCodeForArticle(String codeArticle, String pvtCode) {
        try {
            Query q = em
                    .createQuery("SELECT g From GenCode g JOIN FETCH g.article  WHERE g.article.codeArticle=:codeArticle AND g.article.pvtCode=:pvtCode AND g.article.typeArticle='PRINCIPAL'");
            q.setParameter("codeArticle", codeArticle);
            q.setParameter("pvtCode", pvtCode);
            return (GenCode) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public GenCode getGenCodeForArtAndGenCode(String genCode, String codeArticle, String pvtCode) {
        try {
            Query q = em.createQuery("SELECT g From GenCode g  WHERE g.code=:genCode AND  g.article.codeArticle=:codeArticle AND g.article.pvtCode=:pvtCode");
            q.setParameter("genCode", genCode);
            q.setParameter("codeArticle", codeArticle);
            q.setParameter("pvtCode", pvtCode);
            return (GenCode) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Article getArticleForGenCodeAndPvt(String gc, String pvt) {
        try {
            Query q = em
                    .createQuery("SELECT g.article From GenCode g JOIN FETCH g.article  WHERE g.code=:genCode  AND g.article.pvtCode=:pvtCode");
            q.setParameter("genCode", gc);
            q.setParameter("pvtCode", pvt);
            return (Article) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public GenCode getGenCodeByCodeAndPVT(String gcCode, String pvt) {
        try {
            Query q = em
                    .createQuery("SELECT g From GenCode g JOIN FETCH g.article  WHERE g.code=:genCode AND   g.article.pvtCode=:pvtCode");
            q.setParameter("genCode", gcCode);
            q.setParameter("pvtCode", pvt);
            q.setMaxResults(1);
            return (GenCode) q.getSingleResult();
        } catch (Exception e) {
            System.out.println("ERREUR GENCODE INTROUVABLE : " + gcCode + " "+ pvt);
            return null;
        }
    }
    
    @Override
    public List<GenCode> getAllGenCodeByCodeAndPVT(String gcCode, String pvt) {
        try {
            Query q = em
                    .createQuery("SELECT g From GenCode g JOIN FETCH g.article  WHERE g.code=:genCode AND   g.article.pvtCode=:pvtCode");
            q.setParameter("genCode", gcCode);
            q.setParameter("pvtCode", pvt);
            return q.getResultList();
        } catch (Exception e) {
            System.out.println("ERREUR GENCODE INTROUVABLE : " + gcCode + " "+ pvt);
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<GenCode> getAllGenCodeForArticleInPromo(String pvt, String promo) {
        try {
            Query q = em
                    .createQuery("SELECT g From GenCode g JOIN FETCH g.article a WHERE    g.article.pvtCode=:pvtCode  AND g.article.promo=:promo");
            q.setParameter("pvtCode", pvt);
            q.setParameter("promo", promo);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<GenCode> getAllGenCodeByType(CategorieGenCode cat,
            String pvtCode) {
        try {
            Query q = em
                    .createQuery("SELECT g From GenCode g JOIN FETCH g.article a WHERE    g.pvtCode=:pvtCode  AND g.catGen=:cat");
            q.setParameter("pvtCode", pvtCode);
            q.setParameter("cat", cat);
            return q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<GenCode> getAllGenCodeInGisement(String pvt,
            long gisementDebut, long gisementFin) {
        try {
            Query q = em
                    .createQuery("SELECT g From GenCode g JOIN FETCH g.article a WHERE    g.article.pvtCode=:pvtCode  AND g.article.codeGisement >=:gisementDebut AND g.article.codeGisement <=:gisementFin ");
            q.setParameter("pvtCode", pvt);
            q.setParameter("gisementDebut", gisementDebut);
            q.setParameter("gisementFin", gisementFin);
            return q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<GenCode> getAllGenCodeInSecteur(String pvt, String sec) {
        try {
            Query q = em
                    .createQuery("SELECT g From GenCode g JOIN FETCH g.article a WHERE    g.article.pvtCode=:pvtCode  AND g.article.secteur =:sec ");
            q.setParameter("pvtCode", pvt);
            q.setParameter("sec", sec);
            return q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<GenCode> getAllGenCodeInRayon(String pvt, String ray) {
        try {
            Query q = em
                    .createQuery("SELECT g From GenCode g JOIN FETCH g.article a WHERE    g.article.pvtCode=:pvtCode  AND g.article.rayon =:ray ");
            q.setParameter("pvtCode", pvt);
            q.setParameter("ray", ray);
            return q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<GenCode> getAllGenCodeInFamille(String pvt, String famille) {
        try {
            Query q = em
                    .createQuery("SELECT g From GenCode g JOIN FETCH g.article a WHERE    g.article.pvtCode=:pvtCode  AND g.article.famille =:famille ");
            q.setParameter("pvtCode", pvt);
            q.setParameter("famille", famille);
            return q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<GenCode> getAllGenCodeInSousFamille(String pvt,
            String sousFamille) {
        try {
            Query q = em
                    .createQuery("SELECT g From GenCode g JOIN FETCH g.article a WHERE    g.article.pvtCode=:pvtCode  AND g.article.sousFamille =:sousFamille ");
            q.setParameter("pvtCode", pvt);
            q.setParameter("sousFamille", sousFamille);
            return q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public GenCode updateGenCode(GenCode entity) {

        em.merge(entity);
        em.flush();
        return entity;

    }

    @Override
    public GenCode createGenCode(GenCode entity) {
        em.persist(entity);
        em.flush();
        return entity;
    }

    @Override
    public void removeBarCode(String code) {
        try {
            Query q = em.createQuery("Select g FROM GenCode g JOIN FETCH g.article a  WHERE g.code =:code");
            q.setParameter("code", code);
            GenCode genCode = (GenCode) q.getSingleResult();
            System.out.println(" @@@@@@@@@@@@@@@  genCode : " + genCode.toString());
            em.remove(genCode);
            em.flush();
        } catch (Exception ex) {
            System.out.println("%%%%%%%%%%%%%%%%%%%% | " + ex.getMessage());
        }

    }

    @Override
    public List<GenCodeDTO> getAllGenCodeDTOForArticle(String codeArticle, String pvtCode) {
         List<GenCodeDTO> resultList =new ArrayList<>();
        Query query = em.createNativeQuery("SELECT g.GENCODE_PK, g.GENCODE_CODE, g.PRIX_VENTE_TTC_EN_COURS, g.CATEGORIE_GENCODE  "
                + "FROM gencode g  where g.CODE_ARTICLE_FK=:codeArticle AND g.CODE_MAGASIN_FK=:pvtCode");
        query.setParameter("codeArticle", codeArticle);
        query.setParameter("pvtCode", pvtCode);
         List<Object[]> resultObject = query.getResultList();
         if(!resultObject.isEmpty()){
             resultObject.stream().map((o) -> {
                 GenCodeDTO gen = new GenCodeDTO();
                 gen.setCatGen((String) o[3]);
                 gen.setCode((String) o[1]);
                 BigInteger bigInt =  (BigInteger) o[0];
                 gen.setId(bigInt.intValue());
                 gen.setPrixVenteTTCEnCours(2);
                return gen;
            }).forEach((gen) -> {
                resultList.add(gen);
            });
         }
         return resultList;
    }

    @Override
    public List<GenCode> getAllGenCodeForArticleWithoutFetch(String codeArticle, String pvtCode) {
        Query q = em.createQuery("SELECT  g From GenCode g WHERE g.article.codeArticle=:codeArticle AND g.article.pvtCode=:pvtCode");
        q.setParameter("codeArticle", codeArticle);
        q.setParameter("pvtCode", pvtCode);
        return q.getResultList();
    }

}
