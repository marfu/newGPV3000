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

import ci.prosuma.prosumagpv.entity.commande.DetailBonCommandeFournisseur;
import ci.prosuma.prosumagpv.entity.commande.EnteteBonCommandeFournisseur;
import ci.prosuma.prosumagpv.entity.util.EnumerationParam.EtatCommande;
import ci.prosuma.prosumagpv.entity.util.EnumerationParam.OrigineCommande;
import ci.prosuma.prosumagpv.entity.util.EnumerationParam.TypeLivraison;
import ci.prosuma.prosumagpv.metier.dao.mysql.IDetailBonCommandeDAO;
import ci.prosuma.prosumagpv.metier.dao.mysql.IEnteteBonCommandeDAO;
import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

@Stateless
@Local(IEnteteBonCommandeDAO.class)
@TransactionManagement(TransactionManagementType.BEAN)
public class EnteteBonCommandeDAOImpl implements IEnteteBonCommandeDAO,
        Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @PersistenceContext
    protected EntityManager em;

    @Resource
    public UserTransaction tx;

    @EJB
    private IDetailBonCommandeDAO detailDAO;

    @Override
    public EnteteBonCommandeFournisseur createOrUpdateEBCFDetail(EnteteBonCommandeFournisseur a) {
        EnteteBonCommandeFournisseur temp = getEBCF(a.getId());
        try {
            float totalValeurPF = 0.0f;
            float totalValeurPV = 0.0f;
            float totalValeurMarge = 0.0f;
            float totalValeurMargePourcent = 0.0f;
            for (DetailBonCommandeFournisseur dsc : a.getDetailBonCommande()) {
                if (temp.getDetailBonCommande().contains(dsc)) {
                    if (dsc.getQteCommande() == 0) {
                        temp.getDetailBonCommande().remove(dsc);
                        continue;
                    } else {
                        temp.getDetailBonCommande().remove(dsc);
                        if (dsc.getPvUnitaireTTC() == 0) {
                            dsc.setPvUnitaireTTC(1);
                        }
                        if (dsc.getPfUnitaireTTC() == 0) {
                            dsc.setPfUnitaireTTC(1);
                        }
                        float pvTot = 0;
                        float pfTot = 0;
                        if (dsc.getPvUnitaireTTC() > 0
                                && dsc.getPfUnitaireTTC() > 0) {
                            pvTot = dsc.getPvUnitaireTTC() * dsc.getQteCommande();
                            pfTot = dsc.getPfUnitaireTTC()
                                    * dsc.getQteCommande();
                            dsc.setValeurMarge(pvTot - pfTot);
                            dsc.setValeurMargePourcent((dsc.getValeurMarge() * 100) / pvTot);
                            totalValeurPF += pfTot;
                            totalValeurPV += pvTot;
                            totalValeurMarge += dsc.getValeurMarge();
                            totalValeurMargePourcent += dsc
                                    .getValeurMargePourcent();
                        } else {
                            pvTot = 0;
                            pfTot = 0;
                            dsc.setValeurMarge(0);
                            dsc.setValeurMargePourcent(0);
                            totalValeurPF += pfTot;
                            totalValeurPV += pvTot;
                            totalValeurMarge += dsc.getValeurMarge();
                            totalValeurMargePourcent += dsc.getValeurMargePourcent();
                        }
                        temp.getDetailBonCommande().add(dsc);
                    }

                }

            }

            if (!temp.getDetailBonCommande().isEmpty()) {
                temp.setUserModification(a.getUserModification());
                temp.setValeurPF(totalValeurPF);
                temp.setValeurPV(totalValeurPV);
                temp.setValeurMarge(totalValeurMarge);
                temp.setValeurMargePourcent(totalValeurMargePourcent / temp.getDetailBonCommande().size());
                tx.begin();
                em.merge(temp);
                em.flush();
                tx.commit();
                return temp;
            } else {
                temp.setValeurPF(0);
                temp.setValeurPV(0);
                temp.setValeurMarge(0);
                temp.setValeurMargePourcent(0);
                tx.begin();
                em.merge(temp);
                em.flush();
                tx.commit();
                return temp;
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return null;
        }

    }

    @Override
    public EnteteBonCommandeFournisseur createOrUpdateEBCF(
            EnteteBonCommandeFournisseur a) {
        EnteteBonCommandeFournisseur temp = getEBCF(a.getId());
        if (temp != null) {
            em.merge(a);
            em.flush();
            return a;
        } else {
            em.persist(a);
            return a;
        }
    }

    @Override
    public EnteteBonCommandeFournisseur updateEBCFReceptDetail(EnteteBonCommandeFournisseur a) {
        try {
            EnteteBonCommandeFournisseur temp = getEBCF(a.getId());
            if (temp != null) {

                float totalValeurPF = 0.0f;
                float totalValeurPV = 0.0f;
                float totalValeurMarge = 0.0f;
                float totalValeurMargePourcent = 0.0f;

                for (DetailBonCommandeFournisseur dsc : a.getDetailBonCommande()) {
                    if (temp.getDetailBonCommande().contains(dsc)) {

                        if (dsc.getQteReceptionner() == 0) {
                            temp.getDetailBonCommande().remove(dsc);
                            dsc.setValeurMarge(0);
                            dsc.setValeurMargePourcent(0);
                            totalValeurPF += 0;
                            totalValeurPV += 0;
                            totalValeurMarge += dsc.getValeurMarge();
                            totalValeurMargePourcent += dsc.getValeurMargePourcent();
                            temp.getDetailBonCommande().add(dsc);
                        } else {
                            temp.getDetailBonCommande().remove(dsc);

                            if (dsc.getPvUnitaireTTC() == 0) {
                                dsc.setPvUnitaireTTC(1);
                            }
                            if (dsc.getPfUnitaireTTC() == 0) {
                                dsc.setPfUnitaireTTC(1);
                            }
                            float pvTot = dsc.getPvUnitaireTTC() * dsc.getQteReceptionner();
                            float pfTot = dsc.getPfUnitaireTTC() * dsc.getQteReceptionner();
                            dsc.setValeurMarge(pvTot - pfTot);
                            dsc.setValeurMargePourcent((dsc.getValeurMarge() * 100) / pvTot);
                            totalValeurPF += pfTot;
                            totalValeurPV += pvTot;
                            totalValeurMarge += dsc.getValeurMarge();
                            totalValeurMargePourcent += dsc.getValeurMargePourcent();
                            temp.getDetailBonCommande().add(dsc);
                        }
                    }
                }
                if (!temp.getDetailBonCommande().isEmpty()) {
                    temp.setDateReception(a.getDateReception());
                    temp.setUserReception(a.getUserReception());
                    temp.setValeurPF(totalValeurPF);
                    temp.setValeurPV(totalValeurPV);
                    temp.setValeurMarge(totalValeurMarge);
                    temp.setValeurMargePourcent(totalValeurMargePourcent / temp.getDetailBonCommande().size());
                    tx.begin();
                    em.merge(temp);
                    em.flush();
                    tx.commit();
                    return temp;
                } else {
                    temp.setValeurPF(0);
                    temp.setValeurPV(0);
                    temp.setValeurMarge(0);
                    temp.setValeurMargePourcent(0);
                    tx.begin();
                    em.merge(temp);
                    em.flush();
                    tx.commit();
                    return temp;
                }
            } else {
                return null;
            }
        } catch (NotSupportedException | SystemException | HeuristicMixedException | HeuristicRollbackException | IllegalStateException | RollbackException | SecurityException ex) {
            System.err.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public EnteteBonCommandeFournisseur getEBCF(long entityId) {
        Query query = em.createQuery("SELECT e  FROM EnteteBonCommandeFournisseur e JOIN FETCH e.detailBonCommande where e.id=:id ");
        query.setParameter("id", entityId);
        return (EnteteBonCommandeFournisseur) query.getSingleResult();
    }

    @Override
    public EnteteBonCommandeFournisseur getEBCFWithDetails(long entityId) {
        Query query = em.createQuery("SELECT e  FROM EnteteBonCommandeFournisseur e JOIN FETCH e.detailBonCommande where e.id=:id ");
        query.setParameter("id", entityId);
        return (EnteteBonCommandeFournisseur) query.getSingleResult();
    }

    @Override
    public boolean removeEBCF(EnteteBonCommandeFournisseur entity) {
        EnteteBonCommandeFournisseur a = getEBCF(entity.getId());
        if (a != null) {
            em.remove(a);
            return true;
        } else {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<EnteteBonCommandeFournisseur> getAllEnteteBonCommandeFournisseurForMag(
            String mag) {
        Query query = em
                .createQuery("SELECT u  FROM EnteteBonCommandeFournisseur u "
                        + "WHERE u.pvt.pvtCode=:pvtCode  "
                        + "ORDER BY u.id DESC");
        query.setParameter("pvtCode", mag);

        List<EnteteBonCommandeFournisseur> result = query.getResultList();
        /*
		 * if (result != null) { for (EnteteBonCommandeFournisseur ebcf :
		 * result) { if (ebcf.getDetailBonCommande() != null)
		 * ebcf.getDetailBonCommande().size(); }
		 * 
		 * }
         */
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<EnteteBonCommandeFournisseur> getAllEnteteBonCommandeFournisseurForMagAndDateRange(
            String mag, Date dateDebut, Date dateFin) {
        Query query = em
                .createQuery("SELECT u  FROM EnteteBonCommandeFournisseur u "
                        + "WHERE u.pvt.pvtCode=:pvtCode  "
                        + "AND u.dateCreation BETWEEN :dateDebut AND :dateFin  "
                        + "ORDER BY u.id DESC");
        query.setParameter("pvtCode", mag);
        query.setParameter("dateDebut", dateDebut);
        query.setParameter("dateFin", dateFin);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<EnteteBonCommandeFournisseur> getAllEnteteBonCommandeFournisseurForMagAndDateRangeAndOrigAndEtat(
            String mag, Date dateDebut, Date dateFin, OrigineCommande origine,
            EtatCommande etat) {
        Query query = em
                .createQuery("SELECT u  FROM EnteteBonCommandeFournisseur u "
                        + "WHERE u.pvt.pvtCode=:pvtCode  "
                        + "AND (u.dateCreation BETWEEN :dateDebut AND :dateFin)  "
                        + "AND u.etatCommande=:etatCommande "
                        + "AND u.origineCommande=:origineCommande "
                        + "ORDER BY u.id DESC");
        query.setParameter("pvtCode", mag);
        query.setParameter("dateDebut", dateDebut);
        query.setParameter("dateFin", dateFin);
        query.setParameter("origineCommande", origine);
        query.setParameter("etatCommande", etat);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<EnteteBonCommandeFournisseur> getAllEnteteBonCommandeFournisseurForMagAndUserCreation(
            String mag, String userName) {
        Query query = em
                .createQuery("SELECT u  FROM EnteteBonCommandeFournisseur u "
                        + "WHERE u.pvt.pvtCode=:pvtCode "
                        + "AND u.userCreation=:userName "
                        + "ORDER BY u.id DESC");
        query.setParameter("pvtCode", mag);
        query.setParameter("userName", userName);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<EnteteBonCommandeFournisseur> getAllEnteteBonCommandeFournisseurForMagAndEtat(
            String mag, EtatCommande etatCommande) {
        Query query = em
                .createQuery("SELECT u  FROM EnteteBonCommandeFournisseur u "
                        + "WHERE u.pvt.pvtCode=:pvtCode "
                        + "AND u.etatCommande=:etatCommande "
                        + "ORDER BY u.id DESC");
        query.setParameter("pvtCode", mag);
        query.setParameter("etatCommande", etatCommande);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<EnteteBonCommandeFournisseur> getAllEnteteBonCommandeFournisseurForMagAndFournisseur(
            String mag, String refFournisseur) {
        Query query = em
                .createQuery("SELECT u  FROM EnteteBonCommandeFournisseur u "
                        + "WHERE u.pvt.pvtCode=:pvtCode "
                        + "AND u.fournisseur.refFournisseur=:refFournisseur "
                        + "ORDER BY u.id DESC");
        query.setParameter("pvtCode", mag);
        query.setParameter("refFournisseur", refFournisseur);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<EnteteBonCommandeFournisseur> getAllEnteteBonCommandeFournisseurForMagAndSuggestion(
            String mag, long suggestionCommandeId) {
        Query query = em
                .createQuery("SELECT u  FROM EnteteBonCommandeFournisseur u "
                        + "WHERE u.pvt.pvtCode=:pvtCode "
                        + "AND u.enteteSuggestionCommande.id=:suggestionCommandeId");
        query.setParameter("pvtCode", mag);
        query.setParameter("suggestionCommandeId", suggestionCommandeId);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<EnteteBonCommandeFournisseur> getAllEnteteBonCommandeFournisseurForMagAndOrigine(
            String mag, OrigineCommande origineCommande) {
        Query query = em
                .createQuery("SELECT u  FROM EnteteBonCommandeFournisseur u "
                        + "WHERE u.pvt.pvtCode=:pvtCode "
                        + "AND u.origineCommande=:origineCommande "
                        + "ORDER BY u.id DESC");
        query.setParameter("pvtCode", mag);
        query.setParameter("origineCommande", origineCommande);
        List<EnteteBonCommandeFournisseur> result = query.getResultList();
        if (result != null) {
            for (EnteteBonCommandeFournisseur ebcf : result) {
                if (ebcf.getDetailBonCommande() != null) {
                    ebcf.getDetailBonCommande().size();
                }
            }

        }

        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<EnteteBonCommandeFournisseur> getAllEnteteBonCommandeFournisseurForMagAndTypeLivraison(
            String mag, TypeLivraison typelivraison) {
        Query query = em
                .createQuery("SELECT u  FROM EnteteBonCommandeFournisseur u "
                        + "WHERE u.pvt.pvtCode=:pvtCode "
                        + "AND u.typeLivraison=:typelivraison "
                        + "ORDER BY u.id DESC");
        query.setParameter("pvtCode", mag);
        query.setParameter("typeLivraison", typelivraison);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<EnteteBonCommandeFournisseur> getAllEBCFForMagBySecteurAndRayon(
            String mag, String codeSecteur, String codeRayon) {
        Query query = em
                .createQuery("SELECT u  FROM EnteteBonCommandeFournisseur u "
                        + "WHERE u.pvt.pvtCode=:pvtCode "
                        + "AND u.secteur.code=:codeSecteur "
                        + "AND u.rayon.code=:codeRayon  "
                        + "ORDER BY u.id DESC");
        query.setParameter("pvtCode", mag);
        query.setParameter("codeRayon", codeRayon);
        query.setParameter("codeSecteur", codeSecteur);
        return query.getResultList();
    }

    private boolean checkNull(String e) {
        if (e == null || e.trim().equals("")) {
            return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<EnteteBonCommandeFournisseur> getAllEnteteBonCommandeFournisseurForMagAndOrigineAndEtat(
            String mag, OrigineCommande origineCommande,
            EtatCommande etatCommande) {

        Query query = em.createQuery("SELECT u  FROM EnteteBonCommandeFournisseur u  JOIN FETCH u.detailBonCommande "
                + "WHERE u.pvt.pvtCode=:pvtCode "
                + "AND u.etatCommande=:etatCommande "
                + "AND u.origineCommande=:origineCommande "
                + "ORDER BY u.id DESC");
        query.setParameter("pvtCode", mag);
        query.setParameter("etatCommande", etatCommande);
        query.setParameter("origineCommande", origineCommande);
        return query.getResultList();

    }
    
    @Override
    public List<EnteteBonCommandeFournisseur> getAllEnteteBonCommandeFournisseurForMagAndOrigineAndEtatWhithDetails(
            String mag, OrigineCommande origineCommande,
            EtatCommande etatCommande) {

        Query query = em.createQuery("SELECT u  FROM EnteteBonCommandeFournisseur u   "
                + "WHERE u.pvt.pvtCode=:pvtCode "
                + "AND u.etatCommande=:etatCommande "
                + "AND u.origineCommande=:origineCommande "
                + "ORDER BY u.id DESC");
        query.setParameter("pvtCode", mag);
        query.setParameter("etatCommande", etatCommande);
        query.setParameter("origineCommande", origineCommande);
        return query.getResultList();

    }

    @SuppressWarnings("unchecked")
    @Override
    public List<EnteteBonCommandeFournisseur> filterDataTable(String query,
            String etatSearch, String numSearch, String secteurSearch,
            String rayonSearch, String fourSearch, String mag) {
        Query q = em.createQuery(query);

        if (!checkNull(etatSearch) && !etatSearch.trim().equals("TOUT")) {

            if (etatSearch.equals("SAISIE")) {
                q.setParameter("etatSearch", EtatCommande.SAISIE);
            }
            if (etatSearch.equals("VALIDER")) {
                q.setParameter("etatSearch", EtatCommande.VALIDER);
            }
            if (etatSearch.equals("TRANSMIT")) {
                q.setParameter("etatSearch", EtatCommande.TRANSMIT);
            }
            if (etatSearch.equals("FACTURER")) {
                q.setParameter("etatSearch", EtatCommande.FACTURER);
            }
            if (etatSearch.equals("RECU")) {
                q.setParameter("etatSearch", EtatCommande.RECU);
            }
            if (etatSearch.equals("CLOTURER")) {
                q.setParameter("etatSearch", EtatCommande.CLOTURER);
            }

        }
        if (!checkNull(numSearch) && !numSearch.trim().equals("0")) {
            q.setParameter("numSearch", Long.parseLong(numSearch));
        }
        if (!checkNull(secteurSearch) && !secteurSearch.trim().equals("XXX")) {
            q.setParameter("secteurSearch", secteurSearch);
        }
        if (!checkNull(rayonSearch) && !rayonSearch.trim().equals("XXX")) {
            q.setParameter("rayonSearch", rayonSearch + "%");
        }
        if (!checkNull(fourSearch) && !fourSearch.trim().equals("0")) {
            q.setParameter("fourSearch", fourSearch);
        }
        ;
        q.setParameter("mag", mag);
        List<EnteteBonCommandeFournisseur> result = q.getResultList();
        if (result != null) {
            for (EnteteBonCommandeFournisseur e : result) {
                if (e.getDetailBonCommande() != null) {
                    e.getDetailBonCommande().size();
                }
            }
        }
        return result;

    }

    @SuppressWarnings("unchecked")
    @Override
    public List<EnteteBonCommandeFournisseur> executeLazyQuery(String query,
            int first, int pageSize) {
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

    @SuppressWarnings("unchecked")
    @Override
    public List<DetailBonCommandeFournisseur> getAllDSCforESC(long enteteId) {
        Query query = em
                .createQuery("SELECT u.detailBonCommande  FROM EnteteBonCommandeFournisseur u WHERE  u.id=:id");
        query.setParameter("id", enteteId);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<EnteteBonCommandeFournisseur> getLastCommandEnteteBonCommandeFournisseurForMag(
            String mag, int limit, OrigineCommande origine, EtatCommande etat) {

        Query query = em
                .createQuery("SELECT u  FROM EnteteBonCommandeFournisseur u "
                        + "WHERE u.pvt.pvtCode=:pvtCode  "
                        + "AND u.etatCommande=:etatCommande "
                        + "AND u.origineCommande=:origineCommande "
                        + "ORDER BY u.dateCreation DESC ");// +
        // "LIMIT 0,:limit");
        query.setParameter("pvtCode", mag);
        // query.setParameter("limit", limit);
        query.setParameter("origineCommande", origine);
        query.setParameter("etatCommande", etat);
        return query.getResultList();

    }

    @SuppressWarnings("unchecked")
    @Override
    public List<EnteteBonCommandeFournisseur> getAllCommandEnteteBonCommandeFournisseurForMagForListEtat(
            String mag, OrigineCommande origine, List<EtatCommande> etat) {

        Query query = em
                .createQuery("SELECT u  FROM EnteteBonCommandeFournisseur u "
                        + "WHERE u.pvt.pvtCode=:pvtCode  "
                        + "AND u.etatCommande IN (:etatCommande) "
                        + "AND u.origineCommande=:origineCommande "
                        + "ORDER BY u.dateCreation DESC ");
        query.setParameter("pvtCode", mag);
        query.setParameter("origineCommande", origine);
        query.setParameter("etatCommande", etat);
        return query.getResultList();

    }

    private Long getLastCmdIdByMag(String pvt) {
        Query query = em.createNativeQuery("select  NUMERO_CMD from BON_COMMANDE_FOURNISSEUR_ENTETE where CODE_MAGASIN_FK=:pvt order by ENTETE_COMMANDE_PK desc");
        query.setMaxResults(1);
        query.setParameter("pvt", pvt);
        try {
            BigInteger result = (BigInteger) query.getSingleResult();
            result=result.add(new BigInteger("1"));
            return result.longValue();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return 1L;
        }
    }

    @Override
    public EnteteBonCommandeFournisseur saveBonCommande(EnteteBonCommandeFournisseur ebcf) {

        try {
            ebcf.setNumCmd(getLastCmdIdByMag(ebcf.getPvt().getPvtCode()));
            try {
                tx.begin();
                em.persist(ebcf);
                tx.commit();
                return ebcf;
            } catch (NotSupportedException | SystemException | HeuristicMixedException | HeuristicRollbackException | IllegalStateException | RollbackException | SecurityException ex) {
                Logger.getLogger(EnteteInventaireDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }

        } catch (javax.persistence.PersistenceException ex) {
            try {
                tx.rollback();
                System.out.println("@@@@@@@@@@@@@@@@@@@@@  erreur  :      " + ex.getMessage());
                System.out.println("@@@@@@@@@@@@@@@@@@@@@  erreur 2  :      " + ex.getClass().getName());
                boolean rep = true;
                while (rep) {
                    ebcf.setNumCmd(getLastCmdIdByMag(ebcf.getPvt().getPvtCode()));
                    try {
                        tx.begin();
                        em.persist(ebcf);
                        tx.commit();
                        rep = false;
                    } catch (javax.persistence.PersistenceException e) {
                        tx.rollback();
                        System.out.println("@@@@@@@@@@ Error :  " + e.getMessage());
                    } catch (NotSupportedException | HeuristicMixedException | HeuristicRollbackException | RollbackException ex1) {
                        Logger.getLogger(EnteteInventaireDAOImpl.class.getName()).log(Level.SEVERE, null, ex1);
                    }

                }
                return ebcf;
            } catch (IllegalStateException | SecurityException | SystemException ex1) {
                Logger.getLogger(EnteteInventaireDAOImpl.class.getName()).log(Level.SEVERE, null, ex1);
                return null;
            }
        }

    }

    @Override
    public EnteteBonCommandeFournisseur updateBonCommande(EnteteBonCommandeFournisseur a) {
        try {
            tx.begin();
            em.merge(a);
            tx.commit();
            return a;
        } catch (NotSupportedException | SystemException | HeuristicMixedException | HeuristicRollbackException | IllegalStateException | RollbackException | SecurityException ex) {
            Logger.getLogger(EnteteBonCommandeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return a;
        }

    }

}
