package ci.prosuma.prosumagpv.metier.service.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.jboss.logging.Logger;

import ci.prosuma.metier.dataexchange.jdbc.mysql.IJDBCConnectionMySQL;
import ci.prosuma.prosumagpv.entity.Article;
import ci.prosuma.prosumagpv.entity.Fournisseur;
import ci.prosuma.prosumagpv.entity.GenCode;
import ci.prosuma.prosumagpv.entity.HistoriquePrix;
import ci.prosuma.prosumagpv.entity.LienArtFour;
import ci.prosuma.prosumagpv.entity.LienArtPromo;
import ci.prosuma.prosumagpv.entity.LienPromoPvt;
import ci.prosuma.prosumagpv.entity.PointDeVente;
import ci.prosuma.prosumagpv.entity.Promo;
import ci.prosuma.prosumagpv.entity.pk.ArticleMagRefPK;
import ci.prosuma.prosumagpv.entity.stock.Depot;
import ci.prosuma.prosumagpv.entity.stock.DetailMouvement;
import ci.prosuma.prosumagpv.entity.stock.EnteteMouvement;
import ci.prosuma.prosumagpv.entity.stock.StockArticle;
import ci.prosuma.prosumagpv.entity.util.CategorieClient;
import ci.prosuma.prosumagpv.entity.util.CodeAnalytique;
import ci.prosuma.prosumagpv.entity.util.EnumerationParam;
import ci.prosuma.prosumagpv.entity.util.EnumerationParam.ModeGenCode;
import ci.prosuma.prosumagpv.entity.util.EnumerationParam.TypeArticle;
import ci.prosuma.prosumagpv.entity.util.EnumerationParam.TypeClient;
import ci.prosuma.prosumagpv.entity.util.EnumerationParam.TypeDeDepot;
import ci.prosuma.prosumagpv.entity.util.EnumerationParam.TypeOrigineMouvement;
import ci.prosuma.prosumagpv.entity.util.EnumerationParam.TypePromo;
import ci.prosuma.prosumagpv.entity.util.EnumerationParam.TypeReglement;
import ci.prosuma.prosumagpv.entity.util.Famille;
import ci.prosuma.prosumagpv.entity.util.ModeReglement;
import ci.prosuma.prosumagpv.entity.util.OrigineMouvement;
import ci.prosuma.prosumagpv.entity.util.Rayon;
import ci.prosuma.prosumagpv.entity.util.Secteur;
import ci.prosuma.prosumagpv.entity.util.SousFamille;
import ci.prosuma.prosumagpv.entity.util.TauxASDI;
import ci.prosuma.prosumagpv.entity.util.TauxTVA;
import ci.prosuma.prosumagpv.entity.util.TypeDepot;
import ci.prosuma.prosumagpv.entity.util.TypeMouvementStock;
import ci.prosuma.prosumagpv.metier.dao.mysql.IArticleDAO;
import ci.prosuma.prosumagpv.metier.dao.mysql.IDepotDAO;
import ci.prosuma.prosumagpv.metier.dao.mysql.IDetailMouvementDAO;
import ci.prosuma.prosumagpv.metier.dao.mysql.IEnteteMouvementDAO;
import ci.prosuma.prosumagpv.metier.dao.mysql.IFournisseurDAO;
import ci.prosuma.prosumagpv.metier.dao.mysql.IGenCodeDAO;
import ci.prosuma.prosumagpv.metier.dao.mysql.IHistoriquePrixDAO;
import ci.prosuma.prosumagpv.metier.dao.mysql.IHistoriquePromoDAO;
import ci.prosuma.prosumagpv.metier.dao.mysql.ILienArtFourDAO;
import ci.prosuma.prosumagpv.metier.dao.mysql.ILienArtPromoDAO;
import ci.prosuma.prosumagpv.metier.dao.mysql.ILienPromoPvtDAO;
import ci.prosuma.prosumagpv.metier.dao.mysql.IPointDeVenteDAO;
import ci.prosuma.prosumagpv.metier.dao.mysql.IPromoDAO;
import ci.prosuma.prosumagpv.metier.dao.mysql.IStockArticleDAO;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeCategorieClientDAO;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeCodeAnalytiqueDAO;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeDepotDAO;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeFamilleDAO;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeModeReglementDAO;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeMouvementStockDAO;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeOrigineMouvementDAO;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeRayonDAO;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeSecteurDAO;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeSousFamilleDAO;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeTauxASDIDAO;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeTauxTVADAO;
import ci.prosuma.prosumagpv.metier.service.IClassificationService;
import ci.prosuma.prosumagpv.metier.service.IVersionService;
import java.io.IOException;
import java.util.Iterator;
import javax.persistence.PersistenceException;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;

@Stateless
@Local(IVersionService.class)
@TransactionManagement(TransactionManagementType.BEAN)
public class VersionServiceImpl implements IVersionService, Serializable {

    /*
     *
     */
    public static final long serialVersionUID = 1L;

    public static final Logger logger = Logger.getLogger(VersionServiceImpl.class);

    @EJB
    private IClassificationService classService;

    @EJB
    private IHistoriquePromoDAO histoPromo;

    @EJB
    private IGenCodeDAO genCodeDAO;

    @EJB
    public ITypeSecteurDAO secteurDAO;

    @EJB
    public ITypeRayonDAO rayonDAO;

    @EJB
    public ITypeFamilleDAO familleDAO;

    @EJB
    public ITypeSousFamilleDAO sousFamilleDAO;

    @EJB
    public ITypeCategorieClientDAO categorieClientDAO;

    @EJB
    public ITypeCodeAnalytiqueDAO codeAnalytiqueDAO;

    @EJB
    public ITypeDepotDAO typeDepotDAO;

    @EJB
    public ITypeModeReglementDAO modeReglementDAO;

    @EJB
    public ITypeMouvementStockDAO typeMouvDAO;

    @EJB
    public ITypeTauxASDIDAO tauxASDIDAO;

    @EJB
    public ITypeOrigineMouvementDAO origineMouvementDAO;

    @EJB
    public ITypeTauxTVADAO tauxTVADAO;

    @EJB
    public IPointDeVenteDAO pointDeVenteDAO;

    @EJB
    public IFournisseurDAO fournisseurDAO;

    @EJB
    public ILienArtFourDAO lientArtFourDAO;

    @EJB
    public ILienArtPromoDAO lienArtPromoDAO;

    @EJB
    public ILienPromoPvtDAO lienPromoPvtDAO;

    @EJB
    public IPromoDAO promoDAO;

    @EJB
    public IArticleDAO articleDAO;

    @EJB
    public IStockArticleDAO stockDAO;

    @Resource
    public UserTransaction tx;

    @EJB
    public IJDBCConnectionMySQL mySql;

    @EJB
    private IEnteteMouvementDAO enteteMouvementDAO;

    @EJB
    private IDetailMouvementDAO detailMouvementDAO;

    @EJB
    ITypeMouvementStockDAO typeMouvementStockDAO;

    @EJB
    IHistoriquePrixDAO histoPrixDAO;

    @EJB
    IDepotDAO depotDao;

    @Override
    public void updateGisement(String s, String mag) {
        String codeArticle = s.substring(0, s.indexOf(";"));
        String codeGisement = s.substring(s.indexOf(";") + 1, s.length());
        if (!codeArticle.trim().equals("") && !codeGisement.trim().equals("")) {
            Article art = articleDAO.getArticle(new ArticleMagRefPK(mag, codeArticle));
            System.out.println("codeArticle : " + codeArticle + " | codeGisement : " + codeGisement);
            if (art != null) {
                art.setCodeGisement(Long.parseLong(codeGisement));
                try {
                    tx.begin();
                    articleDAO.createOrUpdateArticle(art);
                    tx.commit();
                } catch (NotSupportedException | SystemException | HeuristicMixedException | HeuristicRollbackException | IllegalStateException | RollbackException | SecurityException e) {
                    try {
                        tx.rollback();
                    } catch (IllegalStateException | SecurityException | SystemException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public void creationGenerique(String s, String mag) {
        DecimalFormat df = new DecimalFormat("000000000");
        DecimalFormat genDF = new DecimalFormat("000000000000");
        String designation = s.substring(s.indexOf(";") + 1, s.length());
        Long value = Long.parseLong("28" + s.substring(0, s.indexOf(";")).trim());
        String codeArticle = df.format(value);
        if (!codeArticle.trim().equals("")) {
            try {

                Article art = articleDAO.getArticle(new ArticleMagRefPK(mag, codeArticle));
                System.out.println("codeArticle : " + codeArticle);
                if (art == null) {
                    StockArticle stock = new StockArticle();
                    stock.setQteComptable(0);
                    stock.setSeuilMax(0);
                    stock.setSeuilMin(0);
                    stock.setDepot(pointDeVenteDAO.getDepotPrincipalForMag(mag));
                    tx.begin();
                    StockArticle result = stockDAO.createOrUpdateStockArticle(stock);
                    art = new Article();
                    art.setActif(true);
                    art.setArticeConsigne("");
                    art.setAttr1(true);
                    art.setCodeAnalytique("");
                    art.setCodeGisement(0);
                    art.setColisage("000001");
                    art.setCommandableCentrale(true);
                    art.setDateCreation(new Date());
                    art.setDesignation(designation);
                    try {
                        tx.begin();
                        articleDAO.createOrUpdateArticle(art);
                        tx.commit();
                    } catch (NotSupportedException | SystemException | HeuristicMixedException | HeuristicRollbackException | IllegalStateException | RollbackException | SecurityException e) {
                        try {
                            tx.rollback();
                        } catch (IllegalStateException | SecurityException | SystemException e1) {
                            e1.printStackTrace();
                        }
                    }
                } else {

                }

            } catch (Exception ex) {

            }
        }
    }

    @SuppressWarnings("unused")
    @Override
    public String parseFacture(BufferedReader br, String mag, String user
    ) {
        try {
            String a = "";
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
            String expediteur = null;
            String numFac = null;
            String observation = null;
            boolean first = true;
            while ((a = br.readLine()) != null) {
                if (a.startsWith("E") && first) {
                    if (!a.substring(1, 4).equals(mag)) {
                        return "cette facture ne concerne pas votre magasin";
                    }
                    if (a.substring(152, 153).equals("C")) {
                        avoir = true;
                    } else {
                        avoir = false;
                    }
                    if (!a.substring(181, 187).equals("000000")) {
                        livDir = true;
                    } else {
                        livDir = false;
                    }
                    if (a.substring(180, 181).equals("F")) {
                        mvntFin = true;
                    } else {
                        mvntFin = false;
                    }
                    // traitement entete
                    dateFacturation = "20" + a.substring(18, 24);
                    mag = a.substring(1, 4);
                    expediteur = "trmcent";
                    numFac = a.substring(24, 26) + "/" + a.substring(26, 34);
                    observation = a.substring(112, 152);
                    // creation entete mouvement
                    em = new EnteteMouvement();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                    em.setDateMouvement(sdf.parse(dateFacturation));
                    em.setOrigineMouvement(origineMouvementDAO.getOrigineMouvement("RECEP"));
                    obs = a.substring(112, 142);
                    obs2 = "";
                    if (avoir) {
                        obs2 = "A" + a.substring(24, 26) + a.substring(26, 34)
                                + "E" + a.substring(153, 161) + "R"
                                + a.substring(189, 197);
                        codMvnt = "E-";
                        em.setNumFac("A"
                                + (a.substring(24, 26) + a.substring(26, 34))
                                .trim());
                    } else {
                        obs2 = "F" + a.substring(24, 26) + a.substring(26, 34)
                                + "E" + a.substring(153, 161) + "R"
                                + a.substring(189, 197);
                        codMvnt = "E+";
                        em.setNumFac("F"
                                + (a.substring(24, 26) + a.substring(26, 34)).trim());
                    }
                    em.setObservations(obs + " " + obs2);
                    em.setUserCreation(user);
                    em.setPvt(pointDeVenteDAO.getPVT(mag));
                    // em = createOrUpdateEnteteMouvement(em);
                    themePromo = a.substring(35, 39);
                    // GENERATION DOSSIER 2
                    if (livDir) {
                        em2 = new EnteteMouvement();
                        em2.setDateMouvement(sdf.parse(dateFacturation));
                        obsDoss = "Annulation LD";
                        em2.setOrigineMouvement(origineMouvementDAO
                                .getOrigineMouvement("RECD"));
                        obsDoss2 = "F" + a.substring(24, 26)
                                + a.substring(26, 34) + "E"
                                + a.substring(153, 161) + "R"
                                + a.substring(189, 197);
                        codMvnt2 = "LD-";
                        em2.setNumFac("F"
                                + (a.substring(24, 26) + a.substring(26, 34))
                                .trim());
                        if (avoir) {
                            obsDoss = "Annulation DAV";
                            em2.setOrigineMouvement(origineMouvementDAO
                                    .getOrigineMouvement("DAV"));
                            obsDoss2 = "A" + a.substring(24, 26)
                                    + a.substring(26, 34) + "E"
                                    + a.substring(153, 161) + "R"
                                    + a.substring(189, 197);
                            codMvnt2 = "AVP+";
                            em2.setNumFac("A"
                                    + (a.substring(24, 26) + a
                                    .substring(26, 34)).trim());
                        }
                        em2.setObservations(obsDoss + " " + obsDoss2);
                        em2.setUserCreation("GPV3000");
                        em2.setPvt(pointDeVenteDAO.getPVT(mag));
                        // em2 = createOrUpdateEnteteMouvement(em2);
                    }
                    first = false;
                }
                if (a.startsWith("L") && !first) {
                    DetailMouvement dm = new DetailMouvement();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                    dm.setDateMouvement(sdf.parse(dateFacturation));
                    Article art = articleDAO.getArticleWithStock(new ArticleMagRefPK(mag, a.substring(1, 10)));
                    dm.setArticle(art);
                    dm.setDesignationArt(art.getDesignation());
                    dm.setFamille(art.getFamille());
                    dm.setRayon(art.getRayon());
                    dm.setSecteur(art.getSecteur());
                    dm.setSousFamille(art.getSousFamille());
                    dm.setDesignationArt(art.getDesignation());
                    dm.setTypeMouvement(typeMouvementStockDAO.getTypeMouvementStock(codMvnt));
                    dm.setSens(1);
                    // dm.setQteMvt((Float.parseFloat(a.substring(60, 71)) /
                    // 1000));
                    if (mvntFin) {
                        dm.setQteMvt(0);
                        dm.setQtePhysiqueAvantMouvement(0);
                        // dm.setQteMvt((Float.parseFloat(a.substring(60, 71)) /
                        // 1000));
                        // dm.setQtePhysiqueAvantMouvement(dm.getArticle().getStock().getQteComptable());
                        dm.setArtPrixReviens((Long.parseLong(a.substring(82, 93)) / 1000) * (Long.parseLong(a.substring(60, 71)) / 1000));
                        dm.setArtPrixVente((Long.parseLong(a.substring(93, 104)) / 1000) * (Long.parseLong(a.substring(60, 71)) / 1000));
                        dm.setSens(1);
                        if (avoir) {
                            dm.setArtPrixReviens(dm.getArtPrixReviens());
                            dm.setArtPrixVente(dm.getArtPrixVente());
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
                        // StockArticle sa = dm.getArticle().getStock();
                        // if (sa != null) {
                        // dm.setQtePhysiqueAvantMouvement(sa.getQteComptable());
                        //
                        // }
                    }
                    dm.setThemePromo(themePromo);
                    dm.setEnteteMouvement(em);
                    dm.setDepot(pointDeVenteDAO.getDepotPrincipalForMag(mag));
                    listDm.add(dm);
                    if (livDir) {
                        DetailMouvement dm2 = new DetailMouvement();
                        Article art1 = articleDAO.getArticleWithStock(new ArticleMagRefPK(mag, a.substring(1, 10)));
                        dm2.setArticle(art1);
                        dm2.setFamille(art1.getFamille());
                        dm2.setRayon(art1.getRayon());
                        dm2.setSecteur(art1.getSecteur());
                        dm2.setSousFamille(art1.getSousFamille());
                        dm2.setDesignationArt(art1.getDesignation());
                        dm2.setTypeMouvement(typeMouvementStockDAO.getTypeMouvementStock(codMvnt2));
                        dm2.setQteMvt((Float.parseFloat(a.substring(60, 71)) / 1000));
                        dm2.setSens(-1);
                        if (avoir) {
                            dm2.setQteMvt((Float.parseFloat(a.substring(60, 71)) / 1000));
                            dm2.setSens(1);
                        }
                        dm2.setDateMouvement(sdf.parse(dateFacturation));
                        dm2.setObservations("MOUVEMENT ANNULATION LD : " + dm2.getArticle().getCodeArticle() + " QTE : " + dm2.getQteMvt());
                        dm2.setArtPrixReviens(Long.parseLong(a.substring(82, 93)) / 1000);
                        dm2.setArtPrixVente(Long.parseLong(a.substring(93, 104)) / 1000);
                        dm2.setQtePhysiqueAvantMouvement(0);
                        // StockArticle sa2 = dm.getArticle().getStock();
                        // if (sa2 != null) {
                        // dm2.setQtePhysiqueAvantMouvement(sa2.getQteComptable());
                        // }

                        dm2.setThemePromo(themePromo);
                        dm2.setEnteteMouvement(em2);
                        dm2.setDepot(pointDeVenteDAO.getDepotPrincipalForMag(mag));
                        listDm2.add(dm2);
                    }
                }
            }
            if (em != null) {
                em.setMouvements(listDm);
                enteteMouvementDAO.createOrUpdateEnteteMouvement(em);
            }
            if (em2 != null) {
                em2.setMouvements(listDm2);
                enteteMouvementDAO.createOrUpdateEnteteMouvement(em2);
            }
        } catch (IOException | ParseException | NumberFormatException e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return "SUCCESS";
    }

    @Override
    public boolean integreVersion(String pvtCode) throws Exception {
        String archiveDate = (new SimpleDateFormat("ddMMyyyyHHmm")).format(new Date());
//        String cheminVersion = "/Users/tagsergi/Documents/versions/";
//        boolean resultat = false;
//        BufferedWriter archiveFlux = new BufferedWriter(new FileWriter(cheminVersion + "/archives/FGPV2000-" + archiveDate + "." + pvtCode));
//        File file_in = new File(cheminVersion + "/FGPV2000" + "." + pvtCode);
//        Reader in = new InputStreamReader(new FileInputStream(file_in), "UTF-8");

        /* version pour Windows */
        String cheminVersion = mySql.getSqlRequestByLibelle(pvtCode + ".chemin.version");
        boolean resultat = false;
        BufferedWriter archiveFlux = new BufferedWriter(new FileWriter(cheminVersion + "\\archives\\FGPV2000-" + archiveDate + "." + pvtCode));
        File file_in = new File(cheminVersion + "\\FGPV2000" + "." + pvtCode);
        Reader in = new InputStreamReader(new FileInputStream(file_in), "Cp1252");

        /*Fin  version pour Windows */
        BufferedReader br = new BufferedReader(in);
        String ligne;
        ArrayList<String> tbl = new ArrayList<>();
        while ((ligne = br.readLine()) != null) {
            // archivage
            //commentaire serge  
            // Utiliser fileUtil de apache common pour la copy , gain de temps
            if (!ligne.equals("")) {
                archiveFlux.write(ligne);
                archiveFlux.newLine();
                tbl.add(ligne.replaceAll("[^\\x00-\\x7F]", " "));
            }
        }
        resultat = parseAndCreateEntity(tbl);
        br.close();
        in.close();
        archiveFlux.close();

        if (!file_in.delete()) {
            System.err.println("Delete operation is failed.");
        }

        return resultat;
    }

    @Override
    public boolean parseAndCreateEntity(ArrayList<String> tbl
    ) {
        try {
            //boolean processTable = SystemPropertiesUtils.processTable.equals("0");
            String pvt = null;
            /*
			 * String b; ArrayList<String> tbl = new ArrayList<String>(); while
			 * ((b = br.readLine()) != null) {
			 * tbl.add(b.replaceAll("[^\\x00-\\x7F]", " ")); }
             */
            Integer ligne = 0;
            for (String a : tbl) {
                if (a.startsWith("I")) {
                    pvt = a.substring(2, 5);
                    logger.info("PVT : " + pvt);
                }
                ligne++;
                logger.info(pvt + " / Ligne N : " + ligne);
                if (a.startsWith("BU")) {
                    String table = a.substring(2, 5);
                    //System.out.println("@@@@@@ |  table :"+table);
                    if (table.equals("SEC")) {
                        updateSecteur(a);
                        System.out.println("@@@@@@ |  Secteur ");
                    }
                    if (table.equals("RAY")) {
                        updateRayon(a);
                        System.out.println("@@@@@@ |  Rayon ");
                    }
                    if (table.equals("FAM")) {
                        updateFamille(a);
                        System.out.println("@@@@@@ |  Famille ");
                    }
                    if (table.equals("SFA")) {
                        updateSousFamille(a);
                        System.out.println("@@@@@@ |  Sous Famille ");
                    }
                    if (table.equals("IFL")) {
                        updateIFL(a);
                    }
                    if (table.equals("TTV")) {
                        updateTTV(a);
                    }
                    if (table.equals("TTT")) {
                        updateTTT(a);
                    }
                    if (table.equals("OMV")) {
                        updateOMV(a);
                    }
                    if (table.equals("CMV")) {
                        updateCMV(a);
                    }
                    if (table.equals("CAC")) {
                        updateCAC(a);
                    }
                    if (table.equals("RGL")) {
                        updateRGL(a);
                    }
                    if (table.equals("DEP")) {
                        updateDEP(a);
                    }
                }
                if (a.startsWith("BF")) {
                    updateFournisseur(a);
                }
                if (a.startsWith("BT")) {
                    updateThemePromo(a, pvt);
                }
                if (a.startsWith("BA")) {
                    String typeEnregistrement = a.substring(11, 12);
                    if (typeEnregistrement.equals("A")) {
                        updateArticle(a, pvt);
                    }
                    if (typeEnregistrement.equals("B")) {
                        updateCodeBarre(a, pvt);
                    }
                    if (typeEnregistrement.equals("C")) {
                        updateLienArticleFournisseur(a, pvt);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public void updateSecteur(String s
    ) {
        try {
            Secteur sec = new Secteur();
            sec.setCode(s.substring(5, 7));
            sec.setLibelle(s.substring(47, 77));
            tx.begin();
            System.out.println("########  | secteur : " + sec.toString());
            secteurDAO.createOrUpdateSecteur(sec);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateRayon(String s
    ) {
        try {
            Rayon record = new Rayon();
            record.setCode(s.substring(12, 17).trim()
                    + s.substring(5, 10).trim());
            record.setSecteur(s.substring(12, 17).trim());
            record.setLibelle(s.substring(47, 72));
            tx.begin();
            rayonDAO.createOrUpdateRayon(record);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateFamille(String s) {
        try {
            Famille record = new Famille();
            String secteur = s.substring(12, 17).trim();
            String rayon = s.substring(19, 24).trim();
            record.setCode(secteur + rayon + s.substring(5, 10).trim());
            record.setLibelle(s.substring(47, 72));
            record.setRayon(rayon);
            record.setSecteur(secteur);
            tx.begin();
            familleDAO.createOrUpdateFamille(record);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateSousFamille(String s) {
        try {
            SousFamille record = new SousFamille();
            String secteur = s.substring(12, 17).trim();
            String rayon = s.substring(19, 24).trim();
            String famille = s.substring(26, 31).trim();
            record.setCode(secteur + rayon + famille
                    + s.substring(5, 10).trim());
            record.setLibelle(s.substring(47, 72));
            record.setFamille(famille);
            record.setRayon(rayon);
            record.setSecteur(secteur);
            tx.begin();
            sousFamilleDAO.createOrUpdateSousFamille(record);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateIFL(String s) {
        try {
            CodeAnalytique record = new CodeAnalytique(s.substring(5, 12)
                    .trim(), s.substring(47, 77));
            tx.begin();
            codeAnalytiqueDAO.createOrUpdateCodeAnalytique(record);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateTTV(String s) {
        try {
            TauxTVA record = new TauxTVA();
            record.setCode(s.substring(5, 10).trim());
            record.setTaux(Float.parseFloat(s.substring(77, 82).trim()) / 1000);
            record.setLibelle(s.substring(47, 77));
            tx.begin();
            tauxTVADAO.createOrUpdateTauxTVA(record);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateTTT(String s) {
        try {
            TauxASDI record = new TauxASDI();
            record.setCode(s.substring(5, 10).trim());
            record.setTaux(Float.parseFloat(s.substring(77, 82).trim()) / 1000);
            record.setLibelle(s.substring(47, 77));
            tx.begin();
            tauxASDIDAO.createOrUpdateTauxASDI(record);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateOMV(String s) {
        try {
            OrigineMouvement record = new OrigineMouvement();
            record.setCode(s.substring(5, 10).trim());
            record.setLibelle(s.substring(47, 77));
            String arg0 = s.substring(82, 91);
            if (arg0.equals("100000000")) {
                record.setTypeOrigineMouvement(TypeOrigineMouvement.RECEPTION);
            }
            if (arg0.equals("010000000")) {
                record.setTypeOrigineMouvement(TypeOrigineMouvement.INVENTAIRE);
            }
            if (arg0.equals("001000000")) {

                record.setTypeOrigineMouvement(TypeOrigineMouvement.VENTE);
            }
            if (arg0.equals("000100000")) {
                record.setTypeOrigineMouvement(TypeOrigineMouvement.CESSION);
            }
            if (arg0.equals("000010000")) {
                record.setTypeOrigineMouvement(TypeOrigineMouvement.CAISSE);
            }
            if (arg0.equals("000001000")) {
                record.setTypeOrigineMouvement(TypeOrigineMouvement.AJUSTEMENT);
            }
            if (arg0.equals("000000100")) {
                record.setTypeOrigineMouvement(TypeOrigineMouvement.DEMARQUE);
            }
            if (arg0.equals("000000010")) {
                record.setTypeOrigineMouvement(TypeOrigineMouvement.DLV);
            }
            if (arg0.equals("000000001")) {
                record.setTypeOrigineMouvement(TypeOrigineMouvement.SCDT);
            }
            tx.begin();
            origineMouvementDAO.createOrUpdateOrigineMouvement(record);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public TypeMouvementStock updateCMV(String s) {
        try {
            TypeMouvementStock record = new TypeMouvementStock();
            record.setCode(s.substring(5, 10).trim());
            record.setOrigineMouvement(s.substring(12, 17).trim());
            record.setNumCodeMouvement(s.substring(19, 21).trim());
            record.setDescription(s.substring(47, 77));
            String tempSens = s.substring(82, 83);
            if (tempSens.equals("1")) {
                record.setSens("-1");
            } else {
                record.setSens("1");
            }
            tx.begin();
            typeMouvDAO.createOrUpdateTypeMouvementStock(record);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public CategorieClient updateCAC(String s) {
        try {
            CategorieClient record = new CategorieClient();
            record.setCategorie(s.substring(5, 10).trim());
            record.setLibelle(s.substring(47, 77));
            String arg0 = s.substring(82, 85);
            if (arg0.equals("100")) {
                record.setTypeClient(TypeClient.DEMI_GROS);
            }
            if (arg0.equals("010")) {
                record.setTypeClient(TypeClient.TERMES);
            }
            if (arg0.equals("001")) {
                record.setTypeClient(TypeClient.COMMERCES_ELECTRONIQUE);
            }
            tx.begin();
            categorieClientDAO.createOrUpdateCategorieClient(record);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ModeReglement updateRGL(String s) {
        try {
            ModeReglement record = new ModeReglement();
            record.setCode(s.substring(5, 10).trim());
            record.setLibelle(s.substring(47, 77));
            String tempType = s.substring(82, 85);
            if (tempType.equals("100000")) {
                record.setTypeReglement(TypeReglement.ESPECE);
            }
            if (tempType.equals("010000")) {
                record.setTypeReglement(TypeReglement.CHEQUE);
            }
            if (tempType.equals("001000")) {
                record.setTypeReglement(TypeReglement.TRAITE);
            }
            if (tempType.equals("000100")) {
                record.setTypeReglement(TypeReglement.VIREMENT);
            }
            if (tempType.equals("000010")) {
                record.setTypeReglement(TypeReglement.COMPTE_A_TERME);
            }
            if (tempType.equals("000001")) {
                record.setTypeReglement(TypeReglement.CARTE_ELECTRONIQUE);
            }
            record.setNbrJour(Integer.parseInt(s.substring(92, 95).trim()));
            tx.begin();
            modeReglementDAO.createOrUpdateModeReglement(record);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public TypeDepot updateDEP(String s) {
        try {
            TypeDepot record = new TypeDepot();
            record.setCode(s.substring(5, 10).trim());
            record.setLibelle(s.substring(47, 77));
            String tempType = s.substring(82, 85);
            if (tempType.equals("100")) {
                record.setTypeDeDepot(TypeDeDepot.MAGASIN);
            }
            if (tempType.equals("010")) {
                record.setTypeDeDepot(TypeDeDepot.DEMI_GROS);
            }
            tx.begin();
            typeDepotDAO.createOrUpdateTypeDepot(record);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateFournisseur(String s) {
        try {
            boolean check = false;
            Fournisseur record = new Fournisseur();
            record.setBp(s.substring(73, 140));
            record.setAdresse(s.substring(73, 100) + " "
                    + s.substring(100, 132) + " " + s.substring(132, 140) + " "
                    + s.substring(143, 168));
            record.setRefFournisseur(s.substring(2, 8));
            record.setMotDirecteur(s.substring(11, 21));
            record.setLivraisonCentrale(s.substring(9, 10).equals("9") ? false
                    : true);
            record.setLivraisonDirecte(s.substring(10, 11).equals("9") ? false
                    : true);
            record.setRaisonSocial(s.substring(21, 53));
            record.setNumContribuable(s.substring(214, 226));
            record.setEnable(s.substring(140, 142).equals("NA")
                    || s.substring(142, 143).equals("9") ? false : true);
            record.setNumTel(s.substring(168, 188));
            record.setContact(s.substring(53, 73));
            record.setNumFax(s.substring(188, 208));
            if (s.substring(9, 10).equals("1")) {
                record.setLivraisonCentrale(true);
            } else {
                record.setLivraisonCentrale(false);
            }
            if (s.substring(10, 11).equals("1")) {
                record.setLivraisonDirecte(true);
            } else {
                record.setLivraisonDirecte(false);
            }
            Fournisseur temp = fournisseurDAO.getFournisseur(record
                    .getRefFournisseur());
            if (temp == null) {
                temp = record;
                temp.setNouveau(true);
                temp.setModifier(false);
                check = true;
            } else {
                if (temp.isNouveau()) {
                    temp.setNouveau(false);
                    check = true;
                }
                if (temp.isModifier()) {
                    temp.setModifier(false);
                    check = true;
                }
                if (temp.needUpdate(record)) {
                    temp.setModifier(true);
                    temp.setEnable(record.isEnable());
                    temp.setLivraisonCentrale(record.isLivraisonCentrale());
                    temp.setLivraisonDirecte(record.isLivraisonDirecte());
                    check = true;
                }
            }
            if (check) {
                tx.begin();
                fournisseurDAO.createOrUpdateFournisseur(temp);
                tx.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateThemePromo(String s, String pvtCode)
            throws ParseException {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Promo record = new Promo();
            record.setDateCreation(new Date(System.currentTimeMillis()));
            record.setTypePromo(TypePromo.CENTRALE);
            record.setTarifCentrale(false);
            record.setActif(true);
            record.setDateDebutPromo(sdf.parse(s.substring(47, 55)));
            record.setDateFinPromo(sdf.parse(s.substring(55, 63)));
            record.setDateDebutFacturation(sdf.parse(s.substring(31, 39)));
            record.setDateFinFacturation(sdf.parse(s.substring(39, 47)));
            record.setDesignation(s.substring(6, 31));
            record.setLibelReduit(s.substring(2, 6).trim());
            Promo temp = promoDAO.getPromo(record.getLibelReduit());
            if (temp == null) {
                temp = record;
                List<PointDeVente> listPVT = new ArrayList<>();
                listPVT.add(pointDeVenteDAO.getPVT(pvtCode));
                temp.setPointDeVentes(listPVT);
                temp.setActif(true);
                temp.setEnFacturation(false);
                temp.setEnVente(false);
                tx.begin();
                promoDAO.createOrUpdatePromo(temp);
                tx.commit();
            } else if (temp.needUpdate(record)) {
                temp.setDateDebutPromo(record.getDateDebutPromo());
                temp.setDateFinPromo(record.getDateFinPromo());
                temp.setDateDebutFacturation(record.getDateDebutFacturation());
                temp.setDateFinFacturation(record.getDateFinFacturation());
                temp.setDesignation(record.getDesignation());
                temp.setLibelReduit(record.getLibelReduit());
                temp.setDateModification(new Date());
                temp.setTarifCentrale(record.isTarifCentrale());
                List<PointDeVente> listPVT = temp.getPointDeVentes();
                temp.setaTraiter(true);
                temp.setActif(true);
                if (!listPVT.contains(pointDeVenteDAO.getPVT(pvtCode))) {
                    listPVT.add(pointDeVenteDAO.getPVT(pvtCode));
                    temp.setPointDeVentes(listPVT);
                }
                tx.begin();
                try {
                    promoDAO.createOrUpdatePromo(temp);
                    tx.commit();
                } catch (Exception ex) {
                    tx.rollback();
                    ex.printStackTrace();
                }

            }

            /*add lien promo & pvt */
            LienPromoPvt lienPvt = new LienPromoPvt();
            lienPvt.setPvt(pvtCode);
            lienPvt.setPromo(record.getLibelReduit());
            tx.begin();
            try {
                lienPromoPvtDAO.createOrUpdateLPP(lienPvt);
                tx.commit();
            } catch (RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException | SystemException ex) {
                tx.rollback();
                ex.printStackTrace();
            }

        } catch (ParseException | NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateArticle(String s, String pvt) throws Exception {
        String numArticle = s.substring(2, 11);

        //System.out.println(" La chaine :  "+s);
        Article a = new Article();
        /**
         * modification Serge Prise en compte des promos Lots
         */
        Integer codeLot = 0;
        Integer quantiteDuLot = 0;
        Integer prixLot = 0;
        Boolean isPromoLot;
        try {
            codeLot = Integer.parseInt(s.substring(232, 234).trim());
            logger.info("###########   codeLot  : " + codeLot);
            quantiteDuLot = Integer.parseInt(s.substring(234, 236).trim());
            logger.info("@@@@@@@@@@@@  quantiteDuLot  : " + quantiteDuLot);
            prixLot = Integer.parseInt(s.substring(242, 249).trim());
            logger.info("&&&&&&&&&&&&  prixLot  : " + prixLot);
            isPromoLot = true;
        } catch (NumberFormatException ex) {
            //logger.error(ex.getMessage());
            isPromoLot = false;
        }
        a.setCodeLot(codeLot);
        a.setQuantiteLot(quantiteDuLot);
        a.setPrixDuLot(prixLot);
        if (s.lastIndexOf("*") > 0) {
            a.setPrixVert(true);
        }
        a.setDateCreation(new Date(System.currentTimeMillis()));
        String commandable = s.substring(91, 93);

        a.setActif(false);
        a.setCommandableCentrale(false);

        String cas = s.substring(123, 125);
        if (cas.equals("AC")) {
            a.setActif(true);
            a.setCommandableCentrale(true);
        }
        if (cas.equals("NA")) {
            a.setActif(false);
            a.setCommandableCentrale(false);
        }
        if (cas.equals("NC")) {
            if (commandable.equals("CD")) {
                a.setActif(true);
                a.setCommandableCentrale(false);
            } else {
                a.setActif(true);
                a.setCommandableCentrale(true);
            }
        }
        if (cas.equals("ES")) {
            a.setActif(true);
            a.setCommandableCentrale(false);
        }
        a.setPrixVenteTTCPrecedent(0);
        a.setPrixReviensTTCPrecedent(0);
        int prixReviensTTCEnCours = Integer.parseInt(s.substring(174, 187).trim());
        int prixVenteTTCEnCours = Integer.parseInt(s.substring(187, 200).trim());
        int prixVenteTTCEnCoursWithoutPromo = Integer.parseInt(s.substring(157, 170).trim());
        int prixReviensTTCEnCoursWithoutPromo = Integer.parseInt(s.substring(144, 157).trim());
        if (!s.substring(170, 174).trim().equals("")) {

            /**
             * PROPHYL.COM et Ajout Serge
             */
            String stringPromo = s.substring(170, 174);
            logger.info("Code PROMO :  " + stringPromo);

            if (isPromoLot) {
                a.setTypePromo(Article.PROMO_LOT_HOMOGENE);
            } else {
                a.setTypePromo(Article.PROMO_ORDINAIRE);
            }

            a.setPromo(stringPromo);
            a.setPrixReviensPromoTTC(prixReviensTTCEnCours);
            a.setPrixVentePromoTTC(prixVenteTTCEnCours);
            a.setPrixReviensTTCEnCours(prixReviensTTCEnCoursWithoutPromo);
            a.setPrixVenteTTCEnCours(prixVenteTTCEnCoursWithoutPromo);
            updateLienArticlePromo(s, pvt);
        } else {
            a.setPrixReviensTTCEnCours(prixReviensTTCEnCoursWithoutPromo);
            a.setPrixVenteTTCEnCours(prixVenteTTCEnCoursWithoutPromo);
            a.setPrixVentePromoTTC(0);
            a.setPrixReviensPromoTTC(0);
            a.setPromo(null);
            a.setTypePromo(Article.NO_PROMO);
        }
        a.setPvtCode(pvt);
        a.setCodeArticle(numArticle);
        a.setDesignation(s.substring(13, 48).replaceAll("[^A-Za-z0-9 .%/*+-]", " "));
        a.setLibelReduit(s.substring(58, 78).replaceAll("[^A-Za-z0-9 .%/*+-]", " "));
        //System.out.println(" La setDesignation :  "+s.substring(13, 48).replaceAll("[^A-Za-z0-9 .%/*+-]"," "));
        //System.out.println(" La setLibelReduit :  "+s.substring(58, 78).replaceAll("[^A-Za-z0-9 .%/*+-]"," "));
        a.setMotDirecteur(s.substring(48, 68));
        a.setArticeConsigne(s.substring(126, 135));
        DecimalFormat df = new DecimalFormat("0000000");
        if (s.substring(95, 100).equals("00000")) {
            a.setCodeAnalytique("0000000");
        } else {
            a.setCodeAnalytique(df.format(Long.parseLong(s.substring(95, 100).trim())) + "");
        }
        a.setTauxTVA(s.substring(142, 144));
        a.setTauxASDI(s.substring(138, 140));
        a.setSecteur(s.substring(100, 102));
        a.setRayon(a.getSecteur() + s.substring(102, 105));
        a.setFamille(a.getRayon() + s.substring(105, 108));
        a.setSousFamille(a.getFamille() + s.substring(108, 111));
        a.setColisage(s.substring(115, 120));
        a.setLivraisonDirecte(true);
        a.setLivraisonCentrale(true);
        a.setLivraisonEclatement(true);
        a.setPasDeRupture(false);
        if (s.substring(82, 86).equals("EXCL")) {
            a.setPasDeRupture(true);
        }
        a.setPresentoireCaisse(false);
        if (s.substring(81, 83).equals("03")) {
            a.setPresentoireCaisse(true);
        }
        a.setTypeArticle(TypeArticle.PRINCIPAL);
        boolean modif = false;
        Article temp = articleDAO.getArticle(new ArticleMagRefPK(a.getPvtCode(), a.getCodeArticle()));
        if (temp == null) {
            System.out.println("NOUVEAU ARTICLE");
            System.out.println("ARTICLE NEW " + a.getCodeArticle() + " " + a.getPvtCode());
            temp = a;
            StockArticle sa = new StockArticle();
            if (pointDeVenteDAO.getDepotPrincipalForMag(a.getPvtCode()) == null) {
                PointDeVente pv = pointDeVenteDAO.getPVT(a.getPvtCode());
                Depot dep = depotDao.getActiveDepot();
                List<Depot> listDep = new ArrayList<>();
                listDep.add(dep);
                pv.setDepot(listDep);
                tx.begin();
                pointDeVenteDAO.createOrUpdatePVT(pv);
                tx.commit();
            }
            sa.setDepot(pointDeVenteDAO.getDepotPrincipalForMag(a.getPvtCode()));
            sa.setSeuilMax(0);
            sa.setSeuilMin(0);
            tx.begin();
            stockDAO.createStock(sa);
            tx.commit();
            temp.setStock(sa);
            temp.setNouvelArticle(true);
            temp.setModifier(false);
            temp.setSupprimer(false);
            temp.setReactiver(false);
            temp.setPrixModifier(false);
            temp.setDateCreation(new Date(System.currentTimeMillis()));
            temp.setAttr1(false);
            modif = true;
            /*
            Historisation des prix
             */
            HistoriquePrix historiquePrix = new HistoriquePrix();
            historiquePrix.setCodeAticle(temp.getCodeArticle());
            historiquePrix.setCodeMagasin(temp.getPvtCode());
            historiquePrix.setDateCreation(new Date());
            historiquePrix.setPrixRevient(temp.getPrixReviensTTCEnCours());
            historiquePrix.setPrixVente(temp.getPrixVenteTTCEnCours());
            tx.begin();
            histoPrixDAO.createHistorique(historiquePrix);
            tx.commit();

        } else {
            System.out.println("ANCIEN " + a.getCodeArticle() + " " + a.getPvtCode());
            if (temp.isModifier() || temp.isNouvelArticle() || temp.isPrixModifier()) {
                temp.setModifier(false);
                temp.setNouvelArticle(false);
                temp.setPrixModifier(false);
                modif = true;
            }
            if (temp.needUpdateInfo(a)) {
                temp.setDesignation(a.getDesignation());
                temp.setMotDirecteur(a.getMotDirecteur());
                temp.setLibelReduit(a.getLibelReduit());
                temp.setPromo(a.getPromo());
                /* ajout des promo lot */
                temp.setTypePromo(a.getTypePromo());
                temp.setCodeLot(a.getCodeLot());
                temp.setQuantiteLot(a.getQuantiteLot());
                temp.setPrixDuLot(prixLot);
                /* Fin Ajout lot */
                temp.setArticeConsigne(a.getArticeConsigne());
                temp.setCodeAnalytique(a.getCodeAnalytique());
                temp.setColisage(a.getColisage());
                temp.setFamille(a.getFamille());
                temp.setRayon(a.getRayon());
                temp.setSecteur(a.getSecteur());
                temp.setSousFamille(a.getSousFamille());
                temp.setTauxASDI(a.getTauxASDI());
                temp.setTauxTVA(a.getTauxTVA());
                temp.setTypeArticle(a.getTypeArticle());
                temp.setCommandableCentrale(a.isCommandableCentrale());
                temp.setLivraisonCentrale(a.isLivraisonCentrale());
                temp.setLivraisonDirecte(a.isLivraisonDirecte());
                temp.setLivraisonEclatement(a.isLivraisonEclatement());
                temp.setPasDeRupture(a.isPasDeRupture());
                temp.setPresentoireCaisse(a.isPresentoireCaisse());
                temp.setTarifCentrale(a.isTarifCentrale());
                temp.setModifier(true);
                modif = true;
            }
            if (temp.needUpdatePrice(a)) {

                if (temp.isNouvelArticle()) {
                    temp.setPrixVenteTTCPrecedent(temp.getPrixVenteTTCEnCours());
                    temp.setPrixReviensTTCPrecedent(temp.getPrixReviensTTCEnCours());
                    temp.setPrixReviensTTCEnCours(a.getPrixReviensTTCEnCours());
                    temp.setPrixVenteTTCEnCours(a.getPrixVenteTTCEnCours());
                    temp.setPrixVentePromoTTC(a.getPrixVentePromoTTC());
                    temp.setPrixReviensPromoTTC(a.getPrixReviensPromoTTC());
                    temp.setPromo(a.getPromo());
                    temp.setCodeLot(a.getCodeLot());
                    temp.setQuantiteLot(a.getQuantiteLot());
                    temp.setPrixDuLot(prixLot);
                } else {

                    if (temp.getPrixReviensTTCEnCours() != a.getPrixReviensTTCEnCours()) {
                        if (a.getPrixReviensTTCEnCours() > 0) {
                            temp.setPrixReviensTTCPrecedent(temp.getPrixReviensTTCEnCours());
                            temp.setPrixReviensTTCEnCours(a.getPrixReviensTTCEnCours());
                        }
                    }
                    if (temp.getPrixVenteTTCEnCours() != a.getPrixVenteTTCEnCours()) {
                        if (a.getPrixVenteTTCEnCours() > 0) {
                            temp.setPrixVenteTTCPrecedent(temp.getPrixVenteTTCEnCours());
                            temp.setPrixVenteTTCEnCours(a.getPrixVenteTTCEnCours());
                        }
                    }
                    temp.setPrixVentePromoTTC(a.getPrixVentePromoTTC());
                    temp.setPrixReviensPromoTTC(a.getPrixReviensPromoTTC());
                    temp.setCodeLot(a.getCodeLot());
                    temp.setQuantiteLot(a.getQuantiteLot());
                    temp.setPrixDuLot(a.getPrixDuLot());
                    temp.setPromo(a.getPromo());
                    temp.setTypePromo(a.getTypePromo());
                }
                temp.setPrixModifier(true);
                modif = true;
                /*
                    Historisation des prix
                 */
                HistoriquePrix historiquePrix = new HistoriquePrix();
                historiquePrix.setCodeAticle(temp.getCodeArticle());
                historiquePrix.setCodeMagasin(temp.getPvtCode());
                historiquePrix.setDateCreation(new Date());
                historiquePrix.setPrixRevient(temp.getPrixReviensTTCEnCours());
                historiquePrix.setPrixVente(temp.getPrixVenteTTCEnCours());
                tx.begin();
                histoPrixDAO.createHistorique(historiquePrix);
                tx.commit();
            }
            if (temp.isActif() && !a.isActif()) {
                temp.setSupprimer(true);
                temp.setReactiver(false);
                temp.setActif(false);
                temp.setModifier(true);
                modif = true;
            }
            if (temp.isCommandableCentrale() && !a.isCommandableCentrale()) {
                temp.setSupprimer(true);
                temp.setReactiver(false);
                temp.setActif(false);
                temp.setModifier(true);
                modif = true;
            }
            if (!temp.isActif() && a.isActif()) {
                temp.setSupprimer(false);
                temp.setReactiver(true);
                temp.setActif(true);
                temp.setModifier(true);
                modif = true;
            }
            if (!temp.isCommandableCentrale() && a.isCommandableCentrale()) {
                temp.setSupprimer(false);
                temp.setReactiver(true);
                temp.setActif(true);
                temp.setModifier(true);
                modif = true;
            }
        }
        if (modif) {
            tx.begin();
            temp.setDateModification(new Date(System.currentTimeMillis()));
            try {
                articleDAO.createOrUpdateArticle(temp);
                tx.commit();
            } catch (HeuristicMixedException | HeuristicRollbackException | IllegalStateException | RollbackException | SecurityException | SystemException ex) {
                logger.error(ex.getMessage());
            }

        }
    }

    /*
	 * 
	 * X-XXXXXXXXX-XXXX-X D-CODE_ARTICLE-PROMO-DELETED D : update Lien Article
	 * Promo
	 * 
	 * */
    public void updateLienArticlePromo(String s, String pvt) throws Exception {
        String numArticle = s.substring(2, 11);
        String promo = s.substring(170, 174);
        String deleted = s.substring(36, 37);
        /**
         * modification Serge Prise en compte des promos Lots
         */
        Integer codeLot = 0;
        Integer quantiteDuLot = 0;
        Integer prixLot = 0;
        Boolean isPromoLot;
        
        int prixReviensTTCEnCours = Integer.parseInt(s.substring(174, 187).trim());
        int prixVenteTTCEnCours = Integer.parseInt(s.substring(187, 200).trim());
        int prixVenteTTCEnCoursWithoutPromo = Integer.parseInt(s.substring(157, 170).trim());
        int prixReviensTTCEnCoursWithoutPromo = Integer.parseInt(s.substring(144, 157).trim());
        LienArtPromo lap = new LienArtPromo();
        try {
            codeLot = Integer.parseInt(s.substring(232, 234).trim());
            logger.info("###########   codeLot  : " + codeLot);
            quantiteDuLot = Integer.parseInt(s.substring(234, 236).trim());
            logger.info("@@@@@@@@@@@@  quantiteDuLot  : " + quantiteDuLot);
            prixLot = Integer.parseInt(s.substring(242, 249).trim());
            logger.info("&&&&&&&&&&&&  prixLot  : " + prixLot);
            isPromoLot = true;
        } catch (NumberFormatException ex) {
            //logger.error(ex.getMessage());
            isPromoLot = false;
        }

        String stringPromo = s.substring(170, 174);
        logger.info("Code PROMO :  " + stringPromo);

        if (isPromoLot) {
            lap.setTypePromo(Article.PROMO_LOT_HOMOGENE);
        } else {
            lap.setTypePromo(Article.PROMO_ORDINAIRE);
        }

        lap.setPrixReviensPromoTTC(prixReviensTTCEnCours);
        lap.setPrixVentePromoTTC(prixVenteTTCEnCours);
        lap.setPrixReviensTTC(prixReviensTTCEnCoursWithoutPromo);
        lap.setPrixVenteTTC(prixVenteTTCEnCoursWithoutPromo);
        lap.setArticle(numArticle);
        lap.setPromo(promo);
        lap.setActif(true);

        if (deleted.equals("9")) {
            lap.setActif(false);
        }

        LienArtPromo lp = lienArtPromoDAO.getLienArtPromoByPromoAndArt(lap.getArticle(), lap.getPromo());
        boolean createOrUpdate = false;
        if (lp == null) {
            createOrUpdate = true;
            lp = lap;
        } else if (lp.needUpdate(lap)) {
            createOrUpdate = true;
            lp.setActif(lap.isActif());
            lp.setArticle(lap.getArticle());
            lp.setPromo(lap.getPromo());
        }
        if (createOrUpdate) {
            tx.begin();
            lienArtPromoDAO.createOrUpdateLAP(lp);
            tx.commit();
        }
    }

    @Override
    public void updateCodeBarre(String s, String pvt) throws Exception {
        String numArticle = s.substring(2, 11);
        String numCodeBarre = s.substring(13, 25);
        GenCode gen = new GenCode();
        gen.setDateCreation(new Date(System.currentTimeMillis()));
        gen.setCode(numCodeBarre);
        gen.setCaractereControle(s.substring(25, 26));
        gen.setCatGen(s.substring(12, 13).equals("P") ? EnumerationParam.CategorieGenCode.PRINCIPAL : EnumerationParam.CategorieGenCode.SECONDAIRE);
        gen.setOrigineGenCode(EnumerationParam.OrigineGenCode.CENTRALE);
        String modegen = s.substring(28, 29);
        if (modegen.equals("C")) {
            gen.setModeGenCode(ModeGenCode.PRINCIPAL);
        }
        if (modegen.equals("1")) {
            gen.setModeGenCode(ModeGenCode.SECONDAIRE);
        }
        if (modegen.equals("2")) {
            gen.setModeGenCode(ModeGenCode.DLVC);
            gen.setPrixVenteTTCEnCours(Integer.parseInt(s.substring(68, 77).trim()));
            gen.setPrixReviensTTCEnCours(Integer.parseInt(s.substring(59, 68).trim()));
        }
        if (!modegen.equals("1") && !modegen.equals("C") && !modegen.equals("2")) {
            gen.setModeGenCode(ModeGenCode.PRINCIPAL);
        }
        if (!s.substring(54, 59).trim().equals("") || !s.substring(54, 59).trim().equals("0")) {
            gen.setCoefSurArticleInPourcent(Float.parseFloat(s.substring(54, 59).trim()) / 1000);
        }
        gen.setActif(false);
        if (!s.substring(80, 81).equals("9")) {
            gen.setActif(true);
        }
        gen.setDateCreation(new Date(System.currentTimeMillis()));
        if (s.substring(80, 81).equals("9")) {
            if (gen.isActif()) {
                gen.setActif(false);
            }
        }
        GenCode cb = genCodeDAO.getGenCodeForArtAndGenCode(gen.getCode(), numArticle, pvt);
        if (cb == null) {
            cb = gen;
            cb.setArticle(articleDAO.getArticle(new ArticleMagRefPK(pvt, numArticle)));
            cb.setNouveauCB(true);
            cb.setUserCreation("gpv3000");
            cb.setPvtCode(pvt);
            if (cb.getModeGenCode() != null && cb.getModeGenCode().equals(ModeGenCode.SECONDAIRE)) {
                cb.setPrixVenteTTCEnCours((int) (cb.getArticle().getPrixVenteTTCEnCours() * gen.getCoefSurArticleInPourcent()));
                cb.setPrixReviensTTCEnCours((int) (cb.getArticle().getPrixReviensTTCEnCours() * gen.getCoefSurArticleInPourcent()));
            }
            cb.setDescenteAEffectuer(true);

            try {
                tx.begin();
                //System.out.println("###########################     |    tx.getStatus() first : "+tx.getStatus());
                genCodeDAO.updateGenCode(cb);
                tx.commit();
            } catch (Exception ex) {
                tx.rollback();
                System.out.println(" @@@@@@@@@@@@@@@@@@@@@@@@     |    barCode with duplicated key : " + numCodeBarre);
                //System.out.println(" @@@@@@@@@@@@@@@@@@@@@@@@     |    tx.getStatus() : "+tx.getStatus());
                tx.begin();
                //if(tx.getStatus() ==Status.STATUS_NO_TRANSACTION ){
                genCodeDAO.removeBarCode(numCodeBarre);
                GenCode genTopersist = cb;
                //System.out.println(" @@@@@@@@@@@@@@@@@@@@@@@@     |    genTopersist : "+genTopersist.toString());
                genCodeDAO.updateGenCode(genTopersist);
                //}
                tx.commit();
            }

        } else {
            if (cb.needUpdateOrigine(gen)) {
                cb.setModifier(true);
                cb.setOrigineGenCode(gen.getOrigineGenCode());
            }
            if (cb.needUpdatePrice(gen)) {
                cb.setModifier(true);
                cb.setModeGenCode(gen.getModeGenCode());
                if (cb.getModeGenCode() != null && cb.getModeGenCode().equals(ModeGenCode.DLVC)) {
                    cb.setPrixVenteTTCEnCours(gen.getPrixVenteTTCEnCours());
                    cb.setPrixReviensTTCEnCours(gen.getPrixVenteTTCEnCours());
                }
                if (cb.getModeGenCode() != null && cb.getModeGenCode().equals(ModeGenCode.SECONDAIRE)) {
                    cb.setPrixVenteTTCEnCours((int) (cb.getArticle().getPrixVenteTTCEnCours() * gen.getCoefSurArticleInPourcent()));
                    cb.setPrixReviensTTCEnCours((int) (cb.getArticle().getPrixReviensTTCEnCours() * gen.getCoefSurArticleInPourcent()));
                }
            }
            if (cb.isModifier()) {
                cb.setDescenteAEffectuer(true);
                tx.begin();
                genCodeDAO.updateGenCode(cb);
                tx.commit();
            }
        }
    }

    @Override
    public void updateLienArticleFournisseur(String s, String pvt)
            throws Exception {
        String numArticle = s.substring(2, 11);
        String refFour = s.substring(13, 19);
        String commandable = s.substring(36, 38);
        String deleted = s.substring(43, 44);
        LienArtFour laf = new LienArtFour();
        laf.setArticle(numArticle);
        laf.setFournisseur(refFour);
        laf.setActif(true);
        laf.setCommandable(true);
        if (commandable.equals("NA") || deleted.equals("9")) {
            laf.setActif(false);
            laf.setCommandable(false);
        }
        if (commandable.equals("NC")) {
            laf.setCommandable(false);
        }
        LienArtFour lf = lientArtFourDAO.getLienArtFourByFourANdArt(laf.getArticle(), laf.getFournisseur());
        boolean createOrUpdate = false;
        if (lf == null) {
            createOrUpdate = true;
            lf = laf;
        } else if (lf.needUpdate(laf)) {
            createOrUpdate = true;
            lf.setActif(laf.isActif());
            lf.setArticle(laf.getArticle());
            lf.setCommandable(laf.isCommandable());
            lf.setFournisseur(laf.getFournisseur());
        }
        if (createOrUpdate) {
            tx.begin();
            lientArtFourDAO.createOrUpdateLAF(lf);
            tx.commit();
        }
    }

    @Override
    public void resetAllTablesForIntegration(String codeMag) {
        mySql.executeUpdate(codeMag);
    }

    @Override
    public void integrationFournisseurAX(String pvt) {
        // TODO Auto-generated method stub

    }

    @Override
    public void integrationArticlesAX(String pvt) {
        // TODO Auto-generated method stub

    }

    @Override
    public void integrationArticlesFournisseursAX(String pvt) {
        // TODO Auto-generated method stub

    }

    @Override
    public void integrationGenCodeAX(String pvt) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean integrationVersionAX(String mag) throws Exception {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void updateStock(String s, String mag) {
        String codeArticle = s.substring(0, s.indexOf(";"));
        String stock = s.substring(s.indexOf(";") + 1, s.length());
        if (!codeArticle.trim().equals("") && !stock.trim().equals("")) {
            Article art = articleDAO.getArticleWithStock(new ArticleMagRefPK(mag, codeArticle));
            System.out.println("codeArticle : " + codeArticle + " | stock : " + stock);
            if (art != null) {
                StockArticle stocArt = art.getStock();
                if (stocArt != null) {
                    Float quantite = 0f;
                    try {
                        quantite = Float.parseFloat(stock);
                    } catch (NumberFormatException ex) {
                        System.out.println("@@@@@@@@@@@@@@@@@@   Erreur Format : " + stock);
                    }

                    stocArt.setQteComptable(quantite);

                    try {
                        tx.begin();
                        stockDAO.createOrUpdateStockArticle(stocArt);
                        tx.commit();
                    } catch (NotSupportedException | SystemException | HeuristicMixedException | HeuristicRollbackException | IllegalStateException | RollbackException | SecurityException e) {
                        try {
                            tx.rollback();
                        } catch (IllegalStateException | SecurityException | SystemException e1) {
                            e1.printStackTrace();
                        }
                    }
                }

            }
        }
    }

    @Override
    public void updateGencode(String s, String mag) {
        String codeArticle = s.substring(0, s.indexOf(";"));
        String gencode = s.substring(s.indexOf(";") + 1, s.length());
        if (!codeArticle.trim().equals("") && !gencode.trim().equals("")) {
            Article art = articleDAO.getArticleWithStock(new ArticleMagRefPK(mag, codeArticle));
            System.out.println("codeArticle : " + codeArticle + " | gencode : " + gencode);
            if (art != null) {
                String rightGen = gencode.substring(0, (gencode.length() - 1));
                System.out.println(" RIGHT GENCODE : " + rightGen);
                List<GenCode> codeBarres = genCodeDAO.getAllGenCodeByCodeAndPVT(rightGen, mag);
                try {
                    tx.begin();
                    if (!codeBarres.isEmpty()) {
                        for (Iterator<GenCode> it = codeBarres.listIterator(); it.hasNext();) {
                            GenCode gen = it.next();
                            if (!gen.getArticle().getCodeArticle().equals(art.getCodeArticle())) {
                                genCodeDAO.removeGenCode(gen);
                                it.remove();
                            }

                        }
                        if (codeBarres.size() == 0) {
                            GenCode newCode = new GenCode();
                            newCode.setActif(true);
                            newCode.setArticle(art);
                            newCode.setCode(rightGen);
                            newCode.setCaractereControle(gencode.substring(gencode.length() - 1));
                            newCode.setCatGen(EnumerationParam.CategorieGenCode.PRINCIPAL);
                            newCode.setDateCreation(new Date());
                            newCode.setOrigineGenCode(EnumerationParam.OrigineGenCode.CENTRALE);
                            newCode.setPvtCode(mag);
                            newCode.setModeGenCode(ModeGenCode.PRINCIPAL);
                            newCode.setModifier(false);
                            newCode.setNouveauCB(false);
                            newCode.setCoefSurArticleInPourcent(1);
                            genCodeDAO.createOrUpdateGenCode(newCode);
                        }

                    } else {
                        GenCode newCode = new GenCode();
                        newCode.setActif(true);
                        newCode.setArticle(art);
                        newCode.setCode(rightGen);
                        newCode.setCaractereControle(gencode.substring(gencode.length() - 1));
                        newCode.setCatGen(EnumerationParam.CategorieGenCode.PRINCIPAL);
                        newCode.setDateCreation(new Date());
                        newCode.setOrigineGenCode(EnumerationParam.OrigineGenCode.CENTRALE);
                        newCode.setPvtCode(mag);
                        newCode.setCoefSurArticleInPourcent(1);
                        newCode.setModeGenCode(ModeGenCode.PRINCIPAL);
                        newCode.setModifier(false);
                        newCode.setNouveauCB(false);

                        genCodeDAO.createOrUpdateGenCode(newCode);
                    }
                    tx.commit();
                } catch (PersistenceException | NotSupportedException | SystemException | HeuristicMixedException | HeuristicRollbackException | IllegalStateException | RollbackException | SecurityException e) {
                    System.err.println(e.getMessage());
                    try {
                        tx.rollback();
                    } catch (IllegalStateException | SecurityException | SystemException e1) {
                        e1.printStackTrace();
                    }
                }
            } else {
                System.out.println("ci.prosuma.prosumagpv.metier.service.impl.VersionServiceImpl.updateGencode() ARTICLE INCONNU");
            }
        }
    }
}
