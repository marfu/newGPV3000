package ci.prosuma.prosumagpv.metier.dao.mysql.impl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ci.prosuma.prosumagpv.entity.stock.DetailInventaire;
import ci.prosuma.prosumagpv.entity.stock.EnteteInventaire;
import ci.prosuma.prosumagpv.metier.dao.mysql.IDetailInventaireDAO;

@Stateless
@Local(IDetailInventaireDAO.class)
public class DetailInventaireDAOImpl implements IDetailInventaireDAO,
        Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @PersistenceContext
    protected EntityManager em;

    @Override
    public DetailInventaire createOrUpdateDetailInventaire(DetailInventaire ei) {
        DetailInventaire temp = getDetailInventaire(ei.getId());
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
    public DetailInventaire getDetailInventaire(long id) {
        try {
            return em.find(DetailInventaire.class, id);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean removeDetailInventaire(DetailInventaire ei) {
        DetailInventaire a = getDetailInventaire(ei.getId());
        if (a != null) {
            em.remove(a);
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public List<DetailInventaire> findAllDetailInventaireForArticles(String codeArticle, String pvtCode) {
        //retrieveDetailInventaire(codeArticle,pvtCode);
        List<DetailInventaire> list = null;
        try {
            Query query = em.createNativeQuery("select d.* "
                    + "from INVENTAIRE_DETAIL d "
                    + "join INVENTAIRE_ENTETE e "
                    + "on e.ENTETE_INVENTAIRE_PK=d.ENTETE_INVENTAIRE_FK "
                    + "where e.SI_CLOTURER = 0 AND e.SI_LANCER = 1 "
                    + "AND d.CODE_ARTICLE_FK = :codeArticle "
                    + "AND e.CODE_MAGASIN_FK = :pvtCode ", DetailInventaire.class);

            query.setParameter("pvtCode", pvtCode);
            query.setParameter("codeArticle", codeArticle);
            list = (List<DetailInventaire>) query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private void retrieveDetailInventaire(String codeArticle, String pvtCode) {
        List<Object[]> results;
        Query query = em.createNativeQuery("select d.DETAIL_INVENTAIRE_PK, d.A_GISER,d.QTE_MAGASIN,d.QTE_RESERVE,d.QTE_STOCK_THEORIQUE,d.CODE_ARTICLE_FK,d.CODE_MAGASIN_FK"
                + ",d.PRIX_REVIENS_HT_EN_COURS,d.PRIX_REVIENS_TTC_EN_COURS,d.PRIX_VENTE_HT_EN_COURS,d.PRIX_VENTE_TTC_EN_COURS "
                + "from inventaire_entete e "
                + "join table_liaison_inventaire_entete_detail l "
                + "on e.ENTETE_INVENTAIRE_PK=l.ENTETE_INVENTAIRE_FK "
                + "join inventaire_detail d on d.DETAIL_INVENTAIRE_PK=l.DETAIL_INVENTAIRE_FK "
                + "where e.SI_CLOTURER = 0 AND e.SI_LANCER = 1 "
                + "AND d.CODE_ARTICLE_FK = :codeArticle "
                + "AND e.CODE_MAGASIN_FK = :pvtCode ");
        query.setParameter("pvtCode", pvtCode);
        query.setParameter("codeArticle", codeArticle);
        results = query.getResultList();

        results.stream().forEach((record) -> {
            Long id = (Long) record[0];
            String codeArt = (String) record[5];
            System.out.println("id : " + id);
            System.out.println("codeArt : " + codeArt);

        });
    }

    @Override
    public List<DetailInventaire> findDetailInventaireByEntete(String numEntete) {
        Query query = em.createNativeQuery("SELECT d.* FROM INVENTAIRE_DETAIL d WHERE JOIN INVENTAIRE_ENTETE e on d.ENTETE_INVENTAIRE_FK=e.ENTETE_INVENTAIRE_PK"
                + " WHERE e.NUMERO_INVENTAIRE=:numEntete");
        query.setParameter("numEntete", numEntete);
        return query.getResultList();
    }

    @Override
    public List<DetailInventaire> findDetailInventaireByEntete(EnteteInventaire enteteInv) {
        Query query = em.createQuery("SELECT d FROM DetailInventaire d WHERE d.enteteInventaire=:enteteInv");
        query.setParameter("enteteInv", enteteInv);
        return query.getResultList();
    }

}
