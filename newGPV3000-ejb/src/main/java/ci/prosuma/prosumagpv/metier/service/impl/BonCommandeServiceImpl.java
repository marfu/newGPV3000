package ci.prosuma.prosumagpv.metier.service.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import ci.prosuma.metier.dataexchange.jdbc.db2.IJDBCConnectionAS400;
import ci.prosuma.metier.dataexchange.jdbc.db2.dao.ISuggestionCommandeAS400DAO;
import ci.prosuma.metier.dataexchange.jdbc.mysql.IJDBCConnectionMySQL;
import ci.prosuma.metier.dataexchange.smb.ISMBConnection;
import ci.prosuma.prosumagpv.entity.Article;
import ci.prosuma.prosumagpv.entity.PointDeVente;
import ci.prosuma.prosumagpv.entity.commande.DetailBonCommandeFournisseur;
import ci.prosuma.prosumagpv.entity.commande.DetailSuggestionCommande;
import ci.prosuma.prosumagpv.entity.commande.EnteteBonCommandeFournisseur;
import ci.prosuma.prosumagpv.entity.commande.EnteteSuggestionCommande;
import ci.prosuma.prosumagpv.entity.commande.HistoriqueEnvoiEBCF;
import ci.prosuma.prosumagpv.entity.pk.ArticleMagRefPK;
import ci.prosuma.prosumagpv.entity.pk.EnteteSuggestionPK;
import ci.prosuma.prosumagpv.entity.util.EnumerationParam.EtatCommande;
import ci.prosuma.prosumagpv.entity.util.EnumerationParam.EtatTransmission;
import ci.prosuma.prosumagpv.entity.util.EnumerationParam.ModeDossier;
import ci.prosuma.prosumagpv.entity.util.EnumerationParam.OrigineCommande;
import ci.prosuma.prosumagpv.entity.util.EnumerationParam.TypeLivraison;
import ci.prosuma.prosumagpv.metier.dao.mysql.IArticleDAO;
import ci.prosuma.prosumagpv.metier.dao.mysql.IDetailBonCommandeDAO;
import ci.prosuma.prosumagpv.metier.dao.mysql.IDetailSuggestionCommandeDAO;
import ci.prosuma.prosumagpv.metier.dao.mysql.IEnteteBonCommandeDAO;
import ci.prosuma.prosumagpv.metier.dao.mysql.IEnteteSuggestionCommandeDAO;
import ci.prosuma.prosumagpv.metier.dao.mysql.IFournisseurDAO;
import ci.prosuma.prosumagpv.metier.dao.mysql.IPointDeVenteDAO;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeRayonDAO;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeSecteurDAO;
import ci.prosuma.prosumagpv.metier.service.IBonCommandeService;
import ci.prosuma.prosumagpv.metier.service.IGenCodeService;
import ci.prosuma.prosumagpv.metier.service.IHistoriqueEnvoiService;

@Stateless
@Local(IBonCommandeService.class)
public class BonCommandeServiceImpl implements IBonCommandeService,
        Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @EJB
    private ITypeSecteurDAO secteurDAO;

    @EJB
    private ITypeRayonDAO rayonDAO;

    @EJB
    private IEnteteBonCommandeDAO enteteBonCommandeDAO;

    @EJB
    private IDetailBonCommandeDAO detailBonCommandeDAO;

    @EJB
    private IBonCommandeService bonCommandeService;

    @EJB
    private ISuggestionCommandeAS400DAO suggestionAS400;

    @EJB
    private ISMBConnection smbConnection;

    @EJB
    private IPointDeVenteDAO pointDeVenteDAO;

    @EJB
    private IFournisseurDAO fournisseurDAO;

    @EJB
    private IEnteteSuggestionCommandeDAO enteteSuggestionCommandeDAO;

    @EJB
    private IDetailSuggestionCommandeDAO detailSuggestionCommandeDAO;

    @EJB
    private IArticleDAO articleDAO;

    @EJB
    private IGenCodeService genCodeService;

    @EJB
    private IHistoriqueEnvoiService histoService;

    @EJB
    private IJDBCConnectionMySQL sqlRequestDAO;
    @EJB
    private IJDBCConnectionAS400 jdbcAS400Connection;

    @Override
    public EnteteBonCommandeFournisseur createOrUpdateEBCF(
            EnteteBonCommandeFournisseur entity) {
        return enteteBonCommandeDAO.saveBonCommande(entity);
    }

    @Override
    public EnteteBonCommandeFournisseur getEBCF(long entityId) {
        return enteteBonCommandeDAO.getEBCFWithDetails(entityId);
    }

    @Override
    public boolean removeEBCF(EnteteBonCommandeFournisseur entity) {
        return enteteBonCommandeDAO.removeEBCF(entity);
    }

    @Override
    public List<EnteteBonCommandeFournisseur> getAllEnteteBonCommandeFournisseurForMag(
            String mag) {
        return enteteBonCommandeDAO
                .getAllEnteteBonCommandeFournisseurForMag(mag);
    }

    @Override
    public List<EnteteBonCommandeFournisseur> getAllEnteteBonCommandeFournisseurForMagAndDateRange(
            String mag, Date dateDebut, Date dateFin) {
        return enteteBonCommandeDAO
                .getAllEnteteBonCommandeFournisseurForMagAndDateRange(mag,
                        dateDebut, dateFin);
    }

    @Override
    public List<EnteteBonCommandeFournisseur> getAllEnteteBonCommandeFournisseurForMagAndUserCreation(
            String mag, String userName) {
        return enteteBonCommandeDAO
                .getAllEnteteBonCommandeFournisseurForMagAndUserCreation(mag,
                        userName);
    }

    @Override
    public List<EnteteBonCommandeFournisseur> getAllEnteteBonCommandeFournisseurForMagAndEtat(
            String mag, EtatCommande etatCommande) {
        return enteteBonCommandeDAO
                .getAllEnteteBonCommandeFournisseurForMagAndEtat(mag,
                        etatCommande);
    }

    @Override
    public List<EnteteBonCommandeFournisseur> getAllEnteteBonCommandeFournisseurForMagAndFournisseur(
            String mag, String refFournisseur) {
        return enteteBonCommandeDAO
                .getAllEnteteBonCommandeFournisseurForMagAndFournisseur(mag,
                        refFournisseur);
    }

    @Override
    public List<EnteteBonCommandeFournisseur> getAllEnteteBonCommandeFournisseurForMagAndSuggestion(
            String mag, long suggestionCommandeId) {
        return enteteBonCommandeDAO
                .getAllEnteteBonCommandeFournisseurForMagAndSuggestion(mag,
                        suggestionCommandeId);
    }

    @Override
    public List<EnteteBonCommandeFournisseur> getAllEnteteBonCommandeFournisseurForMagAndOrigine(
            String mag, OrigineCommande origineCommande) {
        return enteteBonCommandeDAO
                .getAllEnteteBonCommandeFournisseurForMagAndOrigine(mag,
                        origineCommande);
    }

    @Override
    public List<EnteteBonCommandeFournisseur> getAllEnteteBonCommandeFournisseurForMagAndOrigineAndEtat(
            String mag, OrigineCommande origineCommande,
            EtatCommande etatCommande) {
        return enteteBonCommandeDAO.getAllEnteteBonCommandeFournisseurForMagAndOrigineAndEtat(mag, origineCommande, etatCommande);
    }

    @Override
    public List<EnteteBonCommandeFournisseur> getAllEnteteBonCommandeFournisseurForMagAndTypeLivraison(
            String mag, TypeLivraison typelivraison) {
        return enteteBonCommandeDAO
                .getAllEnteteBonCommandeFournisseurForMagAndTypeLivraison(mag,
                        typelivraison);
    }

    @Override
    public List<EnteteBonCommandeFournisseur> getAllEBCFForMagBySecteurAndRayon(
            String mag, String codeSecteur, String codeRayon) {
        return enteteBonCommandeDAO.getAllEBCFForMagBySecteurAndRayon(mag,
                codeSecteur, codeRayon);
    }

    @Override
    public DetailBonCommandeFournisseur createOrUpdateDBCF(
            DetailBonCommandeFournisseur entity) {
        return detailBonCommandeDAO.createOrUpdateDBCF(entity);
    }

    @Override
    public DetailBonCommandeFournisseur getDBCF(long entityId) {
        return detailBonCommandeDAO.getDBCF(entityId);
    }

    @Override
    public boolean removeDBCF(DetailBonCommandeFournisseur entity) {
        return detailBonCommandeDAO.removeDBCF(entity);

    }

    @Override
    public EnteteBonCommandeFournisseur createBonCommande(
            EnteteBonCommandeFournisseur ebcf, List<DetailBonCommandeFournisseur> listDetailBonCommandeFournisseurs) {

        List<DetailBonCommandeFournisseur> listDBCF = new ArrayList<>();
        float totalValeurPF = 0.0f;
        float totalValeurPV = 0.0f;
        float totalValeurMarge = 0.0f;
        float totalValeurMargePourcent = 0.0f;

        for (DetailBonCommandeFournisseur dsc : listDetailBonCommandeFournisseurs) {
            dsc.setEnteteBCF(ebcf);

            if (dsc.getQteCommande() > 0) {
                float pvTot = 0;
                float pfTot = 0;

                if (dsc.getPvUnitaireTTC() > 0 && dsc.getPfUnitaireTTC() > 0) {
                    pvTot = dsc.getPvUnitaireTTC() * dsc.getQteCommande();
                    pfTot = dsc.getPfUnitaireTTC() * dsc.getQteCommande();
                    dsc.setValeurMarge(pvTot - pfTot);
                    dsc.setValeurMargePourcent((dsc.getValeurMarge() * 100)
                            / pvTot);
                    totalValeurPF += pfTot;
                    totalValeurPV += pvTot;
                    totalValeurMarge += dsc.getValeurMarge();
                    totalValeurMargePourcent += dsc.getValeurMargePourcent();
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
                listDBCF.add(dsc);
            }
        }
        if (!listDBCF.isEmpty()) {
            ebcf.setDetailBonCommande(listDBCF);
        }
        if (totalValeurPF > 0) {
            ebcf.setValeurPF(totalValeurPF);
        }
        if (totalValeurPV > 0) {
            ebcf.setValeurPV(totalValeurPV);
        }
        if (totalValeurMarge > 0) {
            ebcf.setValeurMarge(totalValeurMarge);
        }
        if (totalValeurMargePourcent > 0 && listDBCF.size() > 0) {
            ebcf.setValeurMargePourcent(totalValeurMargePourcent / listDBCF.size());
        }

        ebcf.setDetailBonCommande(listDBCF);
        return createOrUpdateEBCF(ebcf);

    }

    @Override
    public EnteteBonCommandeFournisseur createTempBonCommande(
            EnteteBonCommandeFournisseur ebcf,
            List<DetailBonCommandeFournisseur> listDetailBonCommandeFournisseurs) {
        ebcf.setDetailBonCommande(listDetailBonCommandeFournisseurs);
        return createOrUpdateEBCF(ebcf);

    }

    @Override
    public EnteteBonCommandeFournisseur saveSuggestionCommandeToBonCommandeFournisseur(
            EnteteSuggestionCommande entete, String mag, String user) {
        System.out.println("entre : " + entete);
        EnteteBonCommandeFournisseur ebcf = new EnteteBonCommandeFournisseur();
        ebcf.setUserCreation(user);
        ebcf.setDepot(pointDeVenteDAO.getDepotPrincipalForMag(mag));
        ebcf.setDateLivraison(entete.getDateLivaison());
        if (entete.getFournisseur() == null || entete.getFournisseur().equals("")) {
            ebcf.setFournisseur(fournisseurDAO.getFournisseur("000000"));
        } else {
            DecimalFormat dm = new DecimalFormat("000000");
            ebcf.setFournisseur(fournisseurDAO.getFournisseur(dm.format(Long.parseLong(entete.getFournisseur()))));
        }

        if (entete.getModeDossier().equals(ModeDossier.CENTRAL)) {
            ebcf.setTypeLivraison(TypeLivraison.CENTRALE);
        }

        if (entete.getModeDossier().equals(ModeDossier.LIVRAISON_DIRECT)) {
            ebcf.setTypeLivraison(TypeLivraison.DIRECTE);
        }

        if (entete.getModeDossier().equals(ModeDossier.PREALLOTI)) {
            ebcf.setTypeLivraison(TypeLivraison.ECLATEMENT);
        }

        ebcf.setNumDossierSuggestion(entete.getNumDossier());
        ebcf.setCloturer(false);
        ebcf.setDateCreation(new Date(System.currentTimeMillis()));
        ebcf.setOrigineCommande(OrigineCommande.SUGGESTION);
        ebcf.setPvt(pointDeVenteDAO.getPVT(mag));
        ebcf.setEtatCommande(EtatCommande.SAISIE);
        ebcf.setRayon(rayonDAO.getRayon(entete.getRayon()));
        ebcf.setSecteur(secteurDAO.getSecteur(entete.getSecteur()));
        ebcf.setUserCreation(user);
        ebcf.setObservation(entete.getObservation());
        List<DetailBonCommandeFournisseur> listDBCF = new ArrayList<>();

        float totalValeurPF = 0.0f;
        float totalValeurPV = 0.0f;
        float totalValeurMarge = 0.0f;
        float totalValeurMargePourcent = 0.0f;

        for (DetailSuggestionCommande dsc : entete.getDetailSuggestionCommande()) {

            if (dsc.getQteUCCommander() > 0) {
                DetailBonCommandeFournisseur dbcf = new DetailBonCommandeFournisseur();
                dbcf.setEnteteBCF(ebcf);
                dbcf.setColisage(dsc.getColisage());
                if (dsc.getPfUnitaireTTC() > 0 && dsc.getPvUnitaireTTC() > 0) {
                    float pvTot = dsc.getPvUnitaireTTC() * dsc.getQteUCCommander();
                    float pfTot = dsc.getPfUnitaireTTC() * dsc.getQteUCCommander();
                    dbcf.setArticle(articleDAO.getArticle(new ArticleMagRefPK(mag, dsc.getCodeArticle())));
                    dbcf.setPfUnitaireTTC(dsc.getPfUnitaireTTC());
                    dbcf.setPvUnitaireTTC(dsc.getPvUnitaireTTC());
                    dbcf.setQteCommande(dsc.getQteUCCommander());
                    dbcf.setValeurMarge(pvTot - pfTot);
                    dbcf.setValeurMargePourcent(dbcf.getValeurMarge() * 100 / pvTot);
                    totalValeurPF += dsc.getPfUnitaireTTC() * dsc.getQteUCCommander();
                    totalValeurPV += dsc.getPvUnitaireTTC() * dsc.getQteUCCommander();
                    totalValeurMarge += dbcf.getValeurMarge();
                    totalValeurMargePourcent += dbcf.getValeurMargePourcent();
                } else {
                    dbcf.setArticle(articleDAO.getArticle(new ArticleMagRefPK(mag, dsc.getCodeArticle())));
                    dbcf.setPfUnitaireTTC(dsc.getPfUnitaireTTC());
                    dbcf.setPvUnitaireTTC(dsc.getPvUnitaireTTC());
                    dbcf.setQteCommande(dsc.getQteUCCommander());
                    dbcf.setValeurMarge(0);
                    dbcf.setValeurMargePourcent(0);
                }

                //dbcf = createOrUpdateDBCF(dbcf);
                listDBCF.add(dbcf);
            }
        }
        if (listDBCF.size() > 0) {
            ebcf.setDetailBonCommande(listDBCF);
        }
        if (totalValeurPF > 0) {
            ebcf.setValeurPF(totalValeurPF);
        }
        if (totalValeurPV > 0) {
            ebcf.setValeurPV(totalValeurPV);
        }
        if (totalValeurMarge > 0) {
            ebcf.setValeurMarge(totalValeurMarge);
        }
        if (totalValeurMargePourcent > 0 && listDBCF.size() > 0) {
            ebcf.setValeurMargePourcent(totalValeurMargePourcent / listDBCF.size());
        }
        //System.out.println("sortie : " + createOrUpdateEBCF(ebcf));
        return createOrUpdateEBCF(ebcf);
    }

    @Override
    public EnteteSuggestionCommande createOrUpdateESC(
            EnteteSuggestionCommande entity) {

        return enteteSuggestionCommandeDAO.createOrUpdateESC(entity);

    }

    @Override
    public EnteteSuggestionCommande getESC(EnteteSuggestionPK a) {
        return enteteSuggestionCommandeDAO.getESC(a);
    }

    @Override
    public boolean removeESC(EnteteSuggestionCommande entity) {
        return enteteSuggestionCommandeDAO.removeESC(entity);
    }

    @Override
    public List<EnteteSuggestionCommande> getAllESCForMag(String mag) {
        return enteteSuggestionCommandeDAO.getAllESCForMag(mag);
    }

    @Override
    public DetailSuggestionCommande createOrUpdateDSC(
            DetailSuggestionCommande entity) {
        return detailSuggestionCommandeDAO.createOrUpdateDSC(entity);
    }

    @Override
    public List<DetailBonCommandeFournisseur> getAllDSCforESC(long entity) {
        return enteteBonCommandeDAO.getAllDSCforESC(entity);
    }

    @Override
    public DetailSuggestionCommande getDSC(long entityId) {
        return detailSuggestionCommandeDAO.getDSC(entityId);
    }

    @Override
    public boolean removeDSC(DetailSuggestionCommande entity) {
        return detailSuggestionCommandeDAO.removeDSC(entity);
    }

    @Override
    public EnteteBonCommandeFournisseur updateEBCFReceptDetail(
            EnteteBonCommandeFournisseur a) {
        return enteteBonCommandeDAO.updateEBCFReceptDetail(a);
    }

    @Override
    public List<EnteteBonCommandeFournisseur> getAllEnteteBonCommandeFournisseurForMagAndDateRangeAndOrigAndEtat(
            String mag, Date dateDebut, Date dateFin, OrigineCommande origine,
            EtatCommande etat) {
        return enteteBonCommandeDAO
                .getAllEnteteBonCommandeFournisseurForMagAndDateRangeAndOrigAndEtat(
                        mag, dateDebut, dateFin, origine, etat);
    }

    @Override
    public List<EnteteBonCommandeFournisseur> getLastCommandEnteteBonCommandeFournisseurForMag(
            String mag, int limit, OrigineCommande origine, EtatCommande etat) {
        return enteteBonCommandeDAO
                .getLastCommandEnteteBonCommandeFournisseurForMag(mag, limit,
                        origine, etat);
    }

    @Override
    public List<EnteteBonCommandeFournisseur> getAllCommandEnteteBonCommandeFournisseurForMagForListEtat(
            String mag, OrigineCommande origine, List<EtatCommande> etat) {
        return enteteBonCommandeDAO
                .getAllCommandEnteteBonCommandeFournisseurForMagForListEtat(
                        mag, origine, etat);
    }

    @Override
    public String sendCommandeReassort(PointDeVente pvt) {
        try {

            List<EnteteBonCommandeFournisseur> listFinalReassort = new ArrayList<>();

            List<EnteteBonCommandeFournisseur> listEBCF = getAllEnteteBonCommandeFournisseurForMagAndOrigineAndEtatWhithDetails(pvt.getPvtCode(), OrigineCommande.CENTRALE, EtatCommande.VALIDER);
            if (listEBCF != null) {
                listFinalReassort.addAll(listEBCF);
            }
            System.out.println("listFinalReassort.size : " + listFinalReassort.size());
            ArrayList<String> ligneComm;
            DecimalFormat df8 = new DecimalFormat("00000000");
            DecimalFormat df9 = new DecimalFormat("000000000");
            DecimalFormat df13 = new DecimalFormat("0000000000000");

            if (listFinalReassort.size() > 0) {
                ligneComm = new ArrayList<>();
                for (EnteteBonCommandeFournisseur ebcf : listFinalReassort) {
                    List<DetailBonCommandeFournisseur> dtx = detailBonCommandeDAO.getDetailsByEBCF(ebcf.getId());
                    if (!dtx.isEmpty() && dtx.size() > 0) {
                        for (DetailBonCommandeFournisseur a : dtx) {
                            String s = ebcf.getPvt().getPvtCode()
                                    + df9.format(Long.parseLong(a.getArticle().getCodeArticle()))
                                    + df13.format((a.getQteCommande() * 1000))
                                    + df13.format((a.getPvUnitaireTTC() * 1000))
                                    + df8.format(ebcf.getNumCmd());
                            ligneComm.add(s);
                        }
                    }

                }
                //String result = smbConnection.addFile(ligneComm, "CENTRALE",pvt.getPvtCode());generateFile
                if (ligneComm.size() > 0) {
                    String result = generateFile(ligneComm, "CENTRALE", pvt.getPvtCode());
                    if (result.equals("SUCCESS")) {
                        for (EnteteBonCommandeFournisseur ebcf : listFinalReassort) {
                            ebcf.setDateTransmission(new Date(System.currentTimeMillis()));
                            ebcf.setEtatCommande(EtatCommande.TRANSMIT);
                            bonCommandeService.UpdateEBCF(ebcf);
                        }
                        return "SUCCESS";
                    }
                }else{
                    return "FAILURE";
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "FAILURE";
    }

    @Override
    public String reSendCommandeReassort(PointDeVente pvt) {
        try {

            List<EnteteBonCommandeFournisseur> listFinalReassort = new ArrayList<>();
            List<EnteteBonCommandeFournisseur> listEBCF = bonCommandeService.getAllEnteteBonCommandeFournisseurForMagAndOrigineAndEtat(
                    pvt.getPvtCode(), OrigineCommande.CENTRALE, EtatCommande.TRANSMIT);
            if (listEBCF != null) {
                listFinalReassort.addAll(listEBCF);
            }

            ArrayList<String> ligneComm;
            DecimalFormat df8 = new DecimalFormat("00000000");
            DecimalFormat df9 = new DecimalFormat("000000000");
            DecimalFormat df13 = new DecimalFormat("0000000000000");

            if (listFinalReassort.size() > 0) {
                ligneComm = new ArrayList<>();
                for (EnteteBonCommandeFournisseur ebcf : listFinalReassort) {
                    for (DetailBonCommandeFournisseur a : ebcf.getDetailBonCommande()) {
                        String s = ebcf.getPvt().getPvtCode()
                                + df9.format(Long.parseLong(a.getArticle().getCodeArticle()))
                                + df13.format((a.getQteCommande() * 1000))
                                + df13.format((a.getPvUnitaireTTC() * 1000))
                                + df8.format(ebcf.getId());
                        ligneComm.add(s);
                    }
                }
                String result = smbConnection.addFile(ligneComm, "CENTRALE",
                        pvt.getPvtCode());
                if (result.equals("SUCCESS")) {
                    for (EnteteBonCommandeFournisseur ebcf : listFinalReassort) {
                        ebcf.setDateTransmission(new Date(System
                                .currentTimeMillis()));
                        ebcf.setEtatCommande(EtatCommande.TRANSMIT);
                        bonCommandeService.UpdateEBCF(ebcf);
                    }
                    return "SUCCESS";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "FAILURE";
    }

    @Override
    public String sendCommandeLD(PointDeVente pvt,
            List<EnteteBonCommandeFournisseur> listEBCF) {

        HistoriqueEnvoiEBCF histo = new HistoriqueEnvoiEBCF();
        try {

            ArrayList<String> ligneComm = new ArrayList<>();
            DecimalFormat df8 = new DecimalFormat("00000000");
            DecimalFormat df9 = new DecimalFormat("000000000");
            // DecimalFormat df6 = new DecimalFormat("000000");
            DecimalFormat df7 = new DecimalFormat("0000000");
            DecimalFormat df13 = new DecimalFormat("0000000000000");
            if (listEBCF != null && listEBCF.size() > 0) {
                histo.setObservation("LOT CMD LD : ");
                histo.setPvt(pvt);
                if (histo.getListEntete() == null) {
                    histo.setListEntete(new ArrayList<>());
                }
                String obs = "";
                for (EnteteBonCommandeFournisseur tempEbcf : listEBCF) {
                    EnteteBonCommandeFournisseur ebcf = enteteBonCommandeDAO.getEBCFWithDetails(tempEbcf.getId());
                    System.out.println("BOUCLE : " + ebcf);
                    histo.getListEntete().add(ebcf.getNumCmd().toString());
                    obs += ebcf.getNumCmd() + "/";
                    for (DetailBonCommandeFournisseur a : ebcf.getDetailBonCommande()) {
                        String s = ebcf.getPvt().getPvtCode()
                                + df9.format(Long.parseLong(a.getArticle().getCodeArticle()))
                                + df13.format((a.getQteReceptionner() * 1000))
                                + df13.format((a.getPvUnitaireTTC() * 1000))
                                + df8.format(ebcf.getNumCmd())
                                + df8.format(Long.parseLong(ebcf.getBlFournisseur()))
                                + df7.format(Long.parseLong(ebcf.getFournisseur().getRefFournisseur()));
                        ligneComm.add(s);
                    }
                }
                histo.setDateTransmission(new Date(System.currentTimeMillis()));
                histo = histoService.createOrUpdateHistoriqueEnvoiEBCF(histo);
                histo.setObservation(histo.getObservation() + obs);
                String result = generateFile(ligneComm, "LD", pvt.getPvtCode());
                if (result.equals("SUCCESS")) {
                    for (EnteteBonCommandeFournisseur ebcf : listEBCF) {
                        ebcf.setDateTransmission(new Date(System.currentTimeMillis()));
                        ebcf.setEtatCommande(EtatCommande.TRANSMIT);
                        UpdateEBCF(ebcf);
                    }
                    histo.setEtatTransmission(EtatTransmission.TRANSMIT);
                    histoService.createOrUpdateHistoriqueEnvoiEBCF(histo);
                    return "SUCCESS";
                } else {
                    histo.setEtatTransmission(EtatTransmission.A_RETRANSMETTRE);
                    histoService.createOrUpdateHistoriqueEnvoiEBCF(histo);
                    return "FAILURE";
                }
            }
        } catch (NumberFormatException | FileNotFoundException e) {
            System.err.println(e.getMessage());
            histo.setEtatTransmission(EtatTransmission.A_RETRANSMETTRE);
            histoService.createOrUpdateHistoriqueEnvoiEBCF(histo);
            return "FAILURE";
        }
        return "NOTHING";
    }

    @Override
    public String reSendCommandeLD(HistoriqueEnvoiEBCF histo, String codeMag) {
//        try {
//
//            ArrayList<String> ligneComm = new ArrayList<>();
//            DecimalFormat df8 = new DecimalFormat("00000000");
//            DecimalFormat df9 = new DecimalFormat("000000000");
//            // DecimalFormat df6 = new DecimalFormat("000000");
//            DecimalFormat df7 = new DecimalFormat("0000000");
//            DecimalFormat df13 = new DecimalFormat("0000000000000");
//
//            for (EnteteBonCommandeFournisseur ebcf : histo.getListEntete()) {
//                EnteteBonCommandeFournisseur ebcf2 = getEBCF(ebcf.getId());
//                for (DetailBonCommandeFournisseur a : ebcf2.getDetailBonCommande()) {
//                    String s = ebcf.getPvt().getPvtCode()
//                            + df9.format(Long.parseLong(a.getArticle().getCodeArticle()))
//                            + df13.format((a.getQteReceptionner() * 1000))
//                            + df13.format((a.getPvUnitaireTTC() * 1000))
//                            + ebcf.getNumCmd()
//                            + df8.format(Long.parseLong(ebcf.getBlFournisseur()))
//                            + df7.format(Long.parseLong(ebcf.getFournisseur().getRefFournisseur()));
//                    ligneComm.add(s);
//                }
//            }
//
//            String result = smbConnection.addFile(ligneComm, "LD", codeMag);
//
//            if (result.equals("SUCCESS")) {
//                histo.setEtatTransmission(EtatTransmission.TRANSMIT);
//                histo.setDateTransmission(new Date(System.currentTimeMillis()));
//                histoService.createOrUpdateHistoriqueEnvoiEBCF(histo);
//                for (EnteteBonCommandeFournisseur ebcf : histo.getListEntete()) {
//                    ebcf.setDateTransmission(new Date(System.currentTimeMillis()));
//                    ebcf.setEtatCommande(EtatCommande.TRANSMIT);
//                    UpdateEBCF(ebcf);
//                }
//                return "SUCCESS";
//            } else {
//                histo.setDateTransmission(new Date(System.currentTimeMillis()));
//                histo.setEtatTransmission(EtatTransmission.A_RETRANSMETTRE);
//                histoService.createOrUpdateHistoriqueEnvoiEBCF(histo);
//                return "FAILURE";
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            histo.setEtatTransmission(EtatTransmission.A_RETRANSMETTRE);
//            histoService.createOrUpdateHistoriqueEnvoiEBCF(histo);
//            return "FAILURE";
//        }
        return null;
    }

    public String refreshESCFromAS400(String mag) {
        System.out.println("BonCommandeService");
        Map<String, EnteteSuggestionCommande> map = suggestionAS400.allOneEnteteSuggestionCommande(mag);
        if (map == null) {
            return "map null";
        }
        if (map.isEmpty()) {
            return "nothing";
        }
        for (String key : map.keySet()) {
            EnteteSuggestionCommande esc = map.get(key);
            // System.out.println("NEW ONE : " + esc.getNumDossier());
            EnteteSuggestionCommande tmp = enteteSuggestionCommandeDAO.getESC(new EnteteSuggestionPK(mag, esc.getNumDossier()));
            if (tmp == null) {
                System.out.println("CREATION OR UPDATE");
                for (DetailSuggestionCommande dsf : esc.getDetailSuggestionCommande()) {
                    dsf.setEnteteSuggestion(esc);
                }
                esc = enteteSuggestionCommandeDAO.createOrUpdateESC(esc);
                System.out.println(esc.toString());
            }
        }
        /*
		 * // /!\ long for (EnteteSuggestionCommande escToRemove :
		 * enteteSuggestionCommandeDAO .getAllESCForMag(mag)) { if
		 * (escToRemove.isCloturer()) { System.out.println("CLOTURE");
		 * enteteSuggestionCommandeDAO.removeESC(escToRemove); } }
         */
        return "SUCCESS";
    }

    @Override
    public EnteteBonCommandeFournisseur createOrUpdateEBCFDetail(
            EnteteBonCommandeFournisseur a) {
        return enteteBonCommandeDAO.createOrUpdateEBCFDetail(a);
    }

    @Override
    public List<EnteteBonCommandeFournisseur> filterDataTable(
            String etatSearch, String numSearch, String secteurSearch,
            String rayonSearch, String fourSearch, String mag) {
        boolean first = true;
        String query = "SELECT u FROM EnteteBonCommandeFournisseur u ";

        if (!checkNull(etatSearch) || !checkNull(numSearch)
                || !checkNull(secteurSearch) || !checkNull(rayonSearch)
                || !checkNull(fourSearch) || !checkNull(mag)) {
            query = query + " WHERE ";
        }

        if (!checkNull(etatSearch) && !etatSearch.trim().equals("TOUT")) {
            if (first) {
                query = query + "  u.etatCommande=:etatSearch ";
                first = false;
            } else {
                query = query + " AND   u.etatCommande=:etatSearch ";
            }
        }
        if (!checkNull(numSearch) && !numSearch.trim().equals("0")) {
            if (first) {
                query = query + "  u.id=:numSearch ";
                first = false;
            } else {
                query = query + " AND   u.id=:numSearch ";
            }
        }
        if (!checkNull(secteurSearch) && !secteurSearch.trim().equals("XXX")) {
            if (first) {
                query = query + "  u.secteur.code=:secteurSearch ";
                first = false;
            } else {
                query = query + " AND   u.secteur.code=:secteurSearch ";
            }
        }
        if (!checkNull(rayonSearch) && !rayonSearch.trim().equals("XXX")) {
            if (first) {
                query = query + "  u.rayon.code LIKE :rayonCode ";
                first = false;
            } else {
                query = query + " AND   u.rayon.code LIKE :rayonCode ";
            }
        }
        if (!checkNull(fourSearch) && !fourSearch.trim().equals("0")) {
            if (first) {
                query = query + "  u.fournisseur.refFournisseur=:fourSearch ";
                first = false;
            } else {
                query = query
                        + " AND   u.fournisseur.refFournisseur=:fourSearch ";
            }
        }

        if (first) {
            query = query + "  u.pvt.pvtCode=:mag ";
            first = false;
        } else {
            query = query + " AND    u.pvt.pvtCode=:mag ";
        }

        return enteteBonCommandeDAO.filterDataTable(query, etatSearch,
                numSearch, secteurSearch, rayonSearch, fourSearch, mag);

    }

    private boolean checkNull(String e) {
        if (e == null || e.trim().equals("")) {
            return true;
        }
        return false;
    }

    @SuppressWarnings("unused")
    @Override
    public void saveSuggestionToAS400(EnteteBonCommandeFournisseur ebcf) {

        try {
            ebcf = bonCommandeService.getEBCFWithoutDetails(ebcf.getId());
            //List<DetailBonCommandeFournisseur> details = detailBonCommandeDAO.getDetailsByEBCF(ebcf.getId());
             EnteteSuggestionCommande entete = new EnteteSuggestionCommande();
             entete.setPvtCode(ebcf.getPvt().getPvtCode());
             entete.setNumDossier(ebcf.getNumDossierSuggestion());
            List<DetailSuggestionCommande> details =detailSuggestionCommandeDAO.getAllDetailSugestionWithquantityByEnteteSug(entete);
            //List<DetailSuggestionCommande> details = enteteSuggestionCommandeDAO.getESC(new EnteteSuggestionPK(ebcf.getPvt().getPvtCode(),ebcf.getNumDossierSuggestion())).getDetailSuggestionCommande();
            String req1 = sqlRequestDAO.getSqlRequestByLibelle("suggestion.update.1");
            for (DetailSuggestionCommande dt : details) {
                String request1 = req1
                        .replaceAll("qteUc", dt.getQteUCCommander() + "")
                        .replaceAll("qtVl", dt.getQteVLCommander() + "")
                        .replaceAll("codeArticle", dt.getCodeArticle())
                        .replaceAll("pvtCode", ebcf.getPvt().getPvtCode())
                        .replaceAll("numDossier", ebcf.getNumDossierSuggestion());
                int i = jdbcAS400Connection.executeUpdate(request1);
                System.out.println("reponse i : "+i);
            }
            String req2 = sqlRequestDAO.getSqlRequestByLibelle("suggestion.update.2");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat sdf2 = new SimpleDateFormat("HHmmssSS");
            String request2 = req2
                    .replaceAll("etat", "'4'")
                    .replaceAll("dateSys", sdf.format(new Date(System.currentTimeMillis())))
                    .replaceAll("heureSys", sdf2.format(new Date(System.currentTimeMillis())).substring(0, 1))
                    .replaceAll("pvtCode", ebcf.getPvt().getPvtCode())
                    .replaceAll("numDossier", ebcf.getNumDossierSuggestion());
            int j = jdbcAS400Connection.executeUpdate(request2);
            System.out.println("reponse j : "+j);
            //Commentaire effectué par serge pour laisser en etat l'état de la suggestion de commande
//			String reqEtat = sqlRequestDAO.getSqlRequestByLibelle("suggestion.select.etat");
//			ResultSet rs = jdbcAS400Connection.executeQuery(reqEtat.replaceAll("numDossier", ebcf.getNumDossierSuggestion()));
//			String etat = null;
//			while (rs.next()) {
//				etat = rs.getString("ETACEE");
//			}
//			if (etat != null && !etat.trim().equals("")) {
//				int etatNum = Integer.parseInt(etat);
//				if (etatNum < 4) {
//					String req3 = sqlRequestDAO.getSqlRequestByLibelle("suggestion.update.3");
//					req3 = req3.replaceAll("etat","'2'"); // modifié par
//															// Ferdinand DIAHA
//															// ancienne valeur :
//															// 4
//					req3 = req3.replaceAll("numDossier",ebcf.getNumDossierSuggestion());
//					int y = jdbcAS400Connection.executeUpdate(req3);
//				}
//			}
            if (j == 1) {
                ebcf.setEtatCommande(EtatCommande.VALIDER);
                ebcf.setDateTransmission(new Date(System.currentTimeMillis()));
            }
        } catch (Exception e) {
            e.printStackTrace();
            ebcf.setEtatCommande(EtatCommande.SAISIE);
        } finally {
            try {
                bonCommandeService.UpdateEBCF(ebcf);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @SuppressWarnings("unused")
    @Override
    public void saveSuggestionToAS400NoPlacement(String mag, String numDossier) {

        try {
            EnteteSuggestionCommande esc = getESC(new EnteteSuggestionPK(mag, numDossier));
            //List<DetailSuggestionCommande> details = esc.getDetailSuggestionCommande();
//            String req1 = sqlRequestDAO.getSqlRequestByLibelle("suggestion.update.1");
//            for (DetailSuggestionCommande dt : details) {
//                String request1 = req1.replaceAll("qteUc", 0 + "")
//                        .replaceAll("qtVl", 0 + "")
//                        .replaceAll("codeArticle", dt.getCodeArticle())
//                        .replaceAll("pvtCode", mag)
//                        .replaceAll("numDossier", numDossier);
//                int i = jdbcAS400Connection.executeUpdate(request1);
//            }

            String req2 = sqlRequestDAO
                    .getSqlRequestByLibelle("suggestion.update.2");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat sdf2 = new SimpleDateFormat("HHmmssSS");

            String request2 = req2
                    .replaceAll("etat", "'4'")
                    .replaceAll("dateSys", sdf.format(new Date(System.currentTimeMillis())))
                    .replaceAll("heureSys", sdf2.format(new Date(System.currentTimeMillis())).substring(0, 1))
                    .replaceAll("pvtCode", mag)
                    .replaceAll("numDossier", numDossier);
            int j = jdbcAS400Connection.executeUpdate(request2);

            //Commentaire effectué par serge pour laisser en etat l'état de la suggestion de commande
//			String reqEtat = sqlRequestDAO.getSqlRequestByLibelle("suggestion.select.etat");
//			ResultSet rs = jdbcAS400Connection.executeQuery(reqEtat.replaceAll("numDossier", numDossier));
//			String etat = null;
//			while (rs.next()) {
//				etat = rs.getString("ETACEE");
//			}
//
//			if (etat != null && !etat.trim().equals("")) {
//				int etatNum = Integer.parseInt(etat);
//				if (etatNum < 4) {
//					String req3 = sqlRequestDAO
//							.getSqlRequestByLibelle("suggestion.update.3");
//					req3 = req3.replaceAll("etat", 4 + "");
//					req3 = req3.replaceAll("numDossier", numDossier);
//					int y = jdbcAS400Connection.executeUpdate(req3);
//				}
//			}
            if (j == 1) {
                esc.setCloturer(true);
                createOrUpdateESC(esc);
            }

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    @Override
    public List<EnteteBonCommandeFournisseur> executeLazyQuery(String query,
            int first, int pageSize) {
        return enteteBonCommandeDAO.executeLazyQuery(query, first, pageSize);
    }

    @Override
    public Long getRowCount(String query) {
        return enteteBonCommandeDAO.getRowCount(query);
    }

    @Override
    public Map<String, HashMap<String, Article>> importationCmdRapidPDA(
            String pvtCode) {

        Map<String, HashMap<String, Article>> result = new HashMap<String, HashMap<String, Article>>();

        String archiveDate = (new SimpleDateFormat("ddMMyyyyHHmm"))
                .format(new Date());
        String hostIP = sqlRequestDAO
                .getSqlRequestByLibelle(pvtCode + ".ip.mp");

        try {

            BufferedWriter archiveFlux = new BufferedWriter(new FileWriter(
                    "\\\\" + hostIP + "\\pda\\commandes\\archives\\rapide "
                    + archiveDate + ".txt"));

            File file_in = new File("\\\\" + hostIP
                    + "\\pda\\commandes\\rapide.txt");

            Reader in = new InputStreamReader(new FileInputStream(file_in),
                    "Cp1252");

            BufferedReader br = new BufferedReader(in);
            String ligne;

            while ((ligne = br.readLine()) != null) {

                // archivage
                if (!ligne.equals("")) {
                    archiveFlux.write(ligne);
                    archiveFlux.newLine();
                }

                String[] lig = ligne.split(";");
                try {
                    Article art = genCodeService.getArticleForGenCodeAndPvt(
                            lig[0], pvtCode);
                    Map<String, Article> current = new HashMap<String, Article>();
                    current.put(lig[1], art);
                    result.put(art.getCodeArticle(),
                            (HashMap<String, Article>) current);
                } catch (NullPointerException e) {
                    System.err.println("Aucun Article trouve pour le GenCode :"
                            + lig[0]);
                    continue;
                } catch (IndexOutOfBoundsException e) {
                    if (!ligne.equals("")) {
                        System.err.println("Invalid ligne :" + ligne);
                    }
                    continue;
                }
            }
            in.close();
            br.close();
            archiveFlux.close();

            BufferedWriter cleanFile = new BufferedWriter(new FileWriter("\\\\"
                    + hostIP + "\\pda\\commandes\\rapide.txt"));
            cleanFile.write("");
            cleanFile.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }

    /*
	 * (non-Javadoc) Methode pour l'envoi des commandes reassort suivant le
	 * format de AX
	 * 
	 * @see ci.prosuma.prosumagpv.metier.service.IBonCommandeService#
	 * sendCommandeReassortForAX(ci.prosuma.prosumagpv.entity.PointDeVente)
     */
    @Override
    public String sendCommandeReassortForAX(PointDeVente pvt) {
        try {
            List<EnteteBonCommandeFournisseur> listFinalReassort = new ArrayList<EnteteBonCommandeFournisseur>();
            List<EnteteBonCommandeFournisseur> listEBCF = bonCommandeService.getAllEnteteBonCommandeFournisseurForMagAndOrigineAndEtat(pvt.getPvtCode(), OrigineCommande.CENTRALE, EtatCommande.VALIDER);
            if (listEBCF != null) {
                listFinalReassort.addAll(listEBCF);
            }
            ArrayList<String> ligneComm = new ArrayList<String>();
            //DecimalFormat df8 = new DecimalFormat("00000000");
            DecimalFormat df10 = new DecimalFormat("0000000000");
            DecimalFormat df13 = new DecimalFormat("0000000000000");
            DecimalFormat df20 = new DecimalFormat("00000000000000000000");
            DecimalFormat df30 = new DecimalFormat("000000000000000000000000000000");
            DateFormat dateF = new SimpleDateFormat("dd/MM/yyyy");
            if (listFinalReassort.size() > 0) {

                for (EnteteBonCommandeFournisseur ebcf : listFinalReassort) {
                    ligneComm = new ArrayList<String>();
                    String fournisseur;
                    if (ebcf.getFournisseur() != null) {
                        fournisseur = ebcf.getFournisseur().getRefFournisseur();
                    } else {
                        fournisseur = "";
                    }
                    String enteteCommande = "EC                    " + df20.format(Long.valueOf(ebcf.getId())) + df20.format(Long.valueOf(pvt.getPvtCode()))
                            + String.format("%30s", dateF.format(ebcf.getDateCreation())) + String.format("%30s", dateF.format(ebcf.getDateCreation()))
                            + String.format("%30s", dateF.format(ebcf.getDateCreation())) + String.format("%20s", fournisseur)
                            + String.format("%100s", ebcf.getObservation());
                    ligneComm.add(enteteCommande);
                    for (DetailBonCommandeFournisseur a : ebcf.getDetailBonCommande()) {
                        String s = "L" + String.format("%20s", a.getArticle().getCodeArticle())
                                + df13.format(a.getPvUnitaireTTC())
                                + df10.format((a.getQteCommande()))
                                + String.format("%20s", "UN");
                        ligneComm.add(s);
                    }
                    String result = generateFile(ligneComm, "CENTRALE", pvt.getPvtCode());
                    if (result.equals("SUCCESS")) {
                        ebcf.setDateTransmission(new Date(System.currentTimeMillis()));
                        ebcf.setEtatCommande(EtatCommande.TRANSMIT);
                        bonCommandeService.createOrUpdateEBCF(ebcf);
                        return "SUCCESS";
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "FAILURE";
    }

    private String generateFile(ArrayList<String> listTemp, String type, String codeMag) throws FileNotFoundException {
        System.out.println("D\351but creation fichier  \n");
        String name = "";
        String path = System.getProperty("commande.path");
        if (type.equals("CENTRALE")) {
            name = path + "CMD" + codeMag + "REA" + "-" + System.currentTimeMillis() + ".txt";
        }
        if (type.equals("LD")) {
            name = path + "CMD" + codeMag + "LD" + "-" + System.currentTimeMillis() + ".txt";
        }

        File fileDir = new File(name);
        Writer monFluxSortie;
        try {
            monFluxSortie = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileDir), "cp1252"));
            for (String s : listTemp) {
                System.out.println("String   :  " + s);
                monFluxSortie.write(s);
                monFluxSortie.write("\r\n");
            }
            monFluxSortie.close();
            System.out.println("FIN creation fichier  \n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "SUCCESS";
    }

    @Override
    public EnteteBonCommandeFournisseur UpdateEBCF(EnteteBonCommandeFournisseur entity) {
        return enteteBonCommandeDAO.updateBonCommande(entity);
    }

    @Override
    public List<EnteteBonCommandeFournisseur> getAllEnteteBonCommandeFournisseurForMagAndOrigineAndEtatWhithDetails(String mag, OrigineCommande origineCommande, EtatCommande etatCommande) {
        return enteteBonCommandeDAO.getAllEnteteBonCommandeFournisseurForMagAndOrigineAndEtatWhithDetails(mag, origineCommande, etatCommande);
    }

    @Override
    public EnteteBonCommandeFournisseur getEBCFWithoutDetails(long entityId) {
        return enteteBonCommandeDAO.getEBCF(entityId);
    }
}
