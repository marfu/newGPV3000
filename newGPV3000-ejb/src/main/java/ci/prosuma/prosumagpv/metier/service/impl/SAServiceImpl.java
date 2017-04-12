package ci.prosuma.prosumagpv.metier.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.io.Writer;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
//import java.util.HashMap;
import java.util.List;
//import java.util.Map;
import java.util.StringTokenizer;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.transaction.UserTransaction;

import org.apache.commons.io.FileUtils;

import ci.prosuma.metier.dataexchange.jdbc.mysql.IJDBCConnectionMySQL;
import ci.prosuma.metier.dataexchange.smb.ISMBConnection;
import ci.prosuma.prosumagpv.entity.Article;
import ci.prosuma.prosumagpv.entity.GenCode;
import ci.prosuma.prosumagpv.entity.GenCodeDTO;
import ci.prosuma.prosumagpv.entity.PointDeVente;
import ci.prosuma.prosumagpv.entity.Promo;
import ci.prosuma.prosumagpv.entity.dto.ArticleDTO;
import ci.prosuma.prosumagpv.entity.pk.ArticleMagRefPK;
import ci.prosuma.prosumagpv.entity.stock.Depot;
import ci.prosuma.prosumagpv.entity.stock.DetailMouvement;
import ci.prosuma.prosumagpv.entity.stock.EnteteMouvement;
import ci.prosuma.prosumagpv.entity.util.EnumerationParam.CategorieGenCode;
import ci.prosuma.prosumagpv.entity.util.TypeMouvementStock;
import ci.prosuma.prosumagpv.entity.vente.VenteCaisse;
import ci.prosuma.prosumagpv.metier.dao.mysql.IArticleDAO;
import ci.prosuma.prosumagpv.metier.dao.mysql.IEnteteMouvementDAO;
import ci.prosuma.prosumagpv.metier.dao.mysql.IGenCodeDAO;
import ci.prosuma.prosumagpv.metier.dao.mysql.IPointDeVenteDAO;
import ci.prosuma.prosumagpv.metier.dao.mysql.IPromoDAO;
import ci.prosuma.prosumagpv.metier.dao.mysql.IVenteCaisseDAO;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeMouvementStockDAO;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeOrigineMouvementDAO;
import ci.prosuma.prosumagpv.metier.service.IArticleService;
import ci.prosuma.prosumagpv.metier.service.IClassificationService;
import ci.prosuma.prosumagpv.metier.service.ISAService;
import ci.prosuma.prosumagpv.metier.service.IUtilService;
import ci.prosuma.prosumagpv.metier.util.app.GPV3000RemonteeException;
/*
 import ci.prosuma.prosumagpv.metier.util.app.ByteUtil;
 import ci.prosuma.prosumagpv.metier.util.app.PackedDecimal;
 */
import ci.prosuma.prosumagpv.metier.util.app.entity.LigneMouvementSA;
import java.util.Objects;
import org.apache.commons.net.ftp.FTPClient;

@Local(ISAService.class)
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class SAServiceImpl implements ISAService, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 7624678757246507095L;

    // private static final
    @Resource
    UserTransaction tx;

    @EJB
    private ITypeOrigineMouvementDAO origineMvmtnDAO;

    @EJB
    private ITypeMouvementStockDAO mouvStockTypeDAO;

    @EJB
    private IEnteteMouvementDAO enteteMouvementDAO;

    @EJB
    private IClassificationService classservice;

    @EJB
    private IPointDeVenteDAO pointDeVenteDAO;

    @EJB
    private IJDBCConnectionMySQL mysqlConnection;

    @EJB
    private IArticleDAO articleDAO;

    @EJB
    private IArticleService articleService;

    @EJB
    private IPromoDAO promoDAO;

    // @EJB
    // private IClientService clientService;
    @EJB
    private IVenteCaisseDAO venteCaisseDAO;

    @EJB
    private IGenCodeDAO genCodeDAO;

    @EJB
    private IUtilService utilService;

    @EJB
    private ISMBConnection smbConnection;

    @SuppressWarnings("unused")
    private String query;
    @SuppressWarnings("unused")
    private String query2;
    @SuppressWarnings("unused")
    private String query3;
    ArrayList<String> listGenerique;

    private static int nbLigne = 0;

    public SAServiceImpl() {

    }

    private void initVar() {

        listGenerique = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(utilService.getSqlRequestByLibelle("liste.generique"), ";");

        while (tokenizer.hasMoreTokens()) {
            listGenerique.add(tokenizer.nextToken());
        }
        //System.out.println(" listGenerique : " + listGenerique.toString());

    }

    public void majTENDV() {

        // List<String> listFinal = new ArrayList<String>();
        // DecimalFormat dm24 = new DecimalFormat("000000000000000000000000");
        // DecimalFormat dm8 = new DecimalFormat("00000000");
        // try {
        //
        // for (CompteClient c : clientService.getAllCompteClients()) {
        // String s = "";
        // String code = "50";
        // if (!c.isEnabled())
        // code = "58";
        //
        // String ean = "2001" + dm8.format(c.getId());
        // String clef = Ean13Generator.generateEAN13(ean);
        // String cbClient = ean + clef;
        // s = s + "61" + "*" + dm24.format(Long.parseLong(cbClient)) + "*" +
        // code + "*" + "M";
        // listFinal.add(s);
        //
        // }
        //
        // BufferedWriter monFluxSortie;
        // System.out.println("D\351but creation fichier  \n");
        // monFluxSortie = null;
        // monFluxSortie = new BufferedWriter(new FileWriter("OBTENDV.DAT"));
        //
        // for (String s : listFinal) {
        // monFluxSortie.write(s + "\n");
        // }
        //
        // monFluxSortie.close();
        // FTPClient client = new FTPClient();
        // FileInputStream fis = null;
        // client.connect("192.168.1.1");
        // client.login("serveur", "serveur");
        // File f = new File("OBTENDV.DAT");
        // fis = new FileInputStream(f);
        // client.storeFile("/OBTENDV.DAT", fis);
        // client.logout();
        // fis.close();
        // System.out.println("fichier uploader sur le serveur");
        // } catch (Exception e) {
        // e.printStackTrace();
        // }
    }

    /*
	 * (non-Javadoc) Methode de descente Hebdomadaire
	 * 
	 * @see
	 * ci.prosuma.prosumagpv.metier.service.ISAService#uploadToTPVAll(java.lang
	 * .String, java.lang.String)
     */
    @Override
    public void uploadToTPVAll(String ip, String pvtCode) {
        if (listGenerique == null) {
            initVar();
        }
        List<String> listFinal = new ArrayList<>();
        DecimalFormat dm = new DecimalFormat("000000000000");
        DecimalFormat dm2 = new DecimalFormat("0000000");
        DecimalFormat dm3 = new DecimalFormat("0000");
        try {
            listFinal.add("1o0000000000");

             //for (Article a : articleDAO.getAllArticleForMag(pvtCode)) {
            for (ArticleDTO a : articleDAO.getAllArticleDTOForMag(pvtCode)) {
                if (a != null) {
                    List<ci.prosuma.prosumagpv.entity.dto.GenCodeDTO> genCodeList = genCodeDAO.getAllGenCodeDTOForArticle(a.getCodeArticle(), a.getPvtCode());
                    Map<String, ci.prosuma.prosumagpv.entity.dto.GenCodeDTO> gencodeMap = new HashMap<>();
                    if (!genCodeList.isEmpty()) {
                        genCodeList.stream().forEach((tmp) -> {
                            gencodeMap.put(tmp.getCode(), tmp);
                        });
                    }
                    if (gencodeMap.size() > 0) {
                        System.out.println(" gencodeMap size : " + gencodeMap.size());
                        long gcCode = 0;
                        if (null != a.getArticeConsigne()
                                && !a.getArticeConsigne().equals("")
                                && !a.getArticeConsigne().equals("000000000")) {
                            List<GenCode> gcList = genCodeDAO.getAllGenCodeForArticle(a.getArticeConsigne(), pvtCode);
                            if (gcList != null) {
                                for (GenCode gx : gcList) {
                                    long plu = Long.parseLong(gx.getCode());
                                    if (plu > 100 && plu < 10000) {
                                        gcCode = Long.parseLong(gx.getCode());
                                    }
                                }
                            }
                        }
                        for (ci.prosuma.prosumagpv.entity.dto.GenCodeDTO gc : gencodeMap.values()) {
                            float prixVente = 0;
                            if (null != a.getPromo()) {
                                Promo p = promoDAO.getPromo(a.getPromo());
                                if (p.isEnVente()) {
                                    prixVente = a.getPrixVentePromoTTC();
                                } else {
                                    prixVente = a.getPrixVenteTTCEnCours();
                                }
                            } else {
                                prixVente = a.getPrixVenteTTCEnCours();
                            }

                            if (gc.getCatGen().equals(CategorieGenCode.GROS)
                                    || gc.getCatGen().equals(CategorieGenCode.RCOND)
                                    || gc.getCatGen().equals(CategorieGenCode.SCOND)
                                    || gc.getCatGen().equals(CategorieGenCode.DLVM)) {
                                prixVente = gc.getPrixVenteTTCEnCours();
                            }

                            if (prixVente <= 0 || prixVente > 9999999
                                    || gc.getCode().equals("000000000000")) {
                                continue;
                            } else {
                                String modif = "4M";
                                String designation = a.getLibelReduit()
                                        .concat("                    ")
                                        .substring(0, 17)
                                        .replaceAll("[^A-Za-z0-9 .%/*+-]", " ");
                                if (gcCode > 0) {

                                    // if(!gc.isActif())
                                    // modif = "4S";
                                    if (listGenerique.contains(a.getCodeArticle())) {

                                        String c = modif
                                                + dm.format(Long.parseLong(gc.getCode()))
                                                + "NNNONNN"
                                                + Long.parseLong(a.getTauxTVA().substring(1, 2))
                                                + "NN0"
                                                + a.getRayon().substring(2, 5)
                                                + dm2.format(Float.parseFloat("" + prixVente))
                                                + dm3.format(gcCode)
                                                + designation + "          ";
                                        System.out.println(c);
                                        listFinal.add(c);
                                        listFinal.add("\r\n");
                                    } else {
                                        String c = modif
                                                + dm.format(Long.parseLong(gc.getCode()))
                                                + "NNNNNNN"
                                                + Long.parseLong(a.getTauxTVA().substring(1, 2))
                                                + "NN0" + a.getRayon().substring(2, 5)
                                                + dm2.format(Float.parseFloat("" + prixVente))
                                                + dm3.format(gcCode)
                                                + designation + "          ";
                                        System.out.println(c);
                                        listFinal.add(c);
                                        listFinal.add("\r\n");
                                    }

                                } else if (listGenerique.contains(a.getCodeArticle())) {
                                    String c = modif
                                            + dm.format(Long.parseLong(gc.getCode()))
                                            + "NNNONNN" + Long.parseLong(a.getTauxTVA().substring(1, 2))
                                            + "NN0" + a.getRayon().substring(2, 5)
                                            + dm2.format(Float.parseFloat("" + prixVente))
                                            + "0000" + designation + "          ";
                                    System.out.println(c);
                                    listFinal.add(c);
                                    listFinal.add("\r\n");
                                } else {
                                    String c = modif
                                            + dm.format(Long.parseLong(gc.getCode()))
                                            + "NNNNNNN" + Long.parseLong(a.getTauxTVA().substring(1, 2))
                                            + "NN0" + a.getRayon().substring(2, 5)
                                            + dm2.format(Float.parseFloat("" + prixVente))
                                            + "0000" + designation
                                            + "          ";
                                    System.out.println(c);
                                    listFinal.add(c);
                                    listFinal.add("\r\n");
                                }
                            }
                        }
                    }
                }
            }
            System.out.println("D\351but creation fichier  \n");
            // File fileDir = new File("MAJART.DAT");
            File fileDir = new File("tomajcai.fic." + pvtCode);
            System.out.println(" listFinal  size = " + listFinal.size());
            // monFluxSortie.write("1o0000000000"+"                                                                                                                                                                                                                                                   ");
            try (Writer monFluxSortie = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileDir), "cp1252"))) {
                // monFluxSortie.write("1o0000000000"+"                                                                                                                                                                                                                                                   ");
                monFluxSortie.write("\r\n");
                for (String s : listFinal) {
                    monFluxSortie.write(s);
                }
            }
            File archive = new File("C:\\archive_descente\\tomajcai.fic." + pvtCode + "." + System.currentTimeMillis());
            FileUtils.copyFile(fileDir, archive);
            System.out.println("FIN creation fichier  \n");
            System.out.println("fileDir.getName : " + fileDir.getName() + "\n");
            File f = new File("tomajcai.fic." + pvtCode);
            copyFilesToMagRelais(f, pvtCode);
            System.out.println("fichier uploader sur le serveur");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void uploadToTPVPromo(String promo, String pvtCode) {
        if (listGenerique == null) {
            initVar();
        }
        List<String> listFinal = new ArrayList<>();
        DecimalFormat dm = new DecimalFormat("000000000000");
        DecimalFormat dm2 = new DecimalFormat("0000000");
        DecimalFormat dm3 = new DecimalFormat("0000");
        try {
            listFinal.add("1o0000000000");

            for (Article a : articleDAO.getAllArticleForPromoAndPVT(promo,
                    pvtCode)) {
                if (a != null) {
                    List<GenCode> genCodeList = genCodeDAO
                            .getAllGenCodeForArticle(a.getCodeArticle(),
                                    a.getPvtCode());
                    if (genCodeList != null && genCodeList.size() > 0) {

                        long gcCode = 0;
                        if (null != a.getArticeConsigne()
                                && !a.getArticeConsigne().equals("")
                                && !a.getArticeConsigne().equals("000000000")) {
                            List<GenCode> gcList = genCodeDAO
                                    .getAllGenCodeForArticle(
                                            a.getArticeConsigne(), pvtCode);
                            if (gcList != null) {
                                for (GenCode gx : gcList) {
                                    long plu = Long.parseLong(gx.getCode());
                                    if (plu > 100 && plu < 10000) {
                                        gcCode = Long.parseLong(gx.getCode());
                                    }
                                }
                            }
                        }

                        for (GenCode gc : genCodeList) {

                            float prixVente = 0;
                            if (null != a.getPromo()) {
                                Promo p = promoDAO.getPromo(a.getPromo());
                                if (p.isEnVente()) {
                                    prixVente = a.getPrixVentePromoTTC();
                                } else {
                                    prixVente = a.getPrixVenteTTCEnCours();
                                }
                            } else {
                                prixVente = a.getPrixVenteTTCEnCours();
                            }

                            if (gc.getCatGen().equals(CategorieGenCode.GROS)
                                    || gc.getCatGen().equals(
                                            CategorieGenCode.RCOND)
                                    || gc.getCatGen().equals(
                                            CategorieGenCode.SCOND)
                                    || gc.getCatGen().equals(
                                            CategorieGenCode.DLVM)) {
                                prixVente = gc.getPrixVenteTTCEnCours();
                            }

                            if (prixVente <= 0 || prixVente > 9999999
                                    || gc.getCode().equals("000000000000")) {
                                continue;
                            } else {
                                String modif = "4M";
                                String designation = a.getLibelReduit()
                                        .concat("                    ")
                                        .substring(0, 17)
                                        .replaceAll("[^A-Za-z0-9 .%/*+-]", " ");
                                if (gcCode > 0) {

                                    // if(!gc.isActif())
                                    // modif = "4S";
                                    if (listGenerique.contains(a
                                            .getCodeArticle())) {

                                        String c = modif
                                                + dm.format(Long.parseLong(gc
                                                        .getCode()))
                                                + "NNNONNN"
                                                + Long.parseLong(a.getTauxTVA()
                                                        .substring(1, 2))
                                                + "NN0"
                                                + a.getRayon().substring(2, 5)
                                                + dm2.format(Float
                                                        .parseFloat(""
                                                                + prixVente))
                                                + dm3.format(gcCode)
                                                + designation + "          ";
                                        System.out.println(c);
                                        listFinal.add(c);
                                    } else {
                                        String c = modif
                                                + dm.format(Long.parseLong(gc
                                                        .getCode()))
                                                + "NNNNNNN"
                                                + Long.parseLong(a.getTauxTVA()
                                                        .substring(1, 2))
                                                + "NN0"
                                                + a.getRayon().substring(2, 5)
                                                + dm2.format(Float
                                                        .parseFloat(""
                                                                + prixVente))
                                                + dm3.format(gcCode)
                                                + designation + "          ";
                                        System.out.println(c);
                                        listFinal.add(c);
                                    }

                                } else // if(!gc.isActif())
                                // modif = "4S";
                                 if (listGenerique.contains(a
                                            .getCodeArticle())) {
                                        String c = modif
                                                + dm.format(Long.parseLong(gc
                                                        .getCode()))
                                                + "NNNONNN"
                                                + Long.parseLong(a.getTauxTVA()
                                                        .substring(1, 2))
                                                + "NN0"
                                                + a.getRayon().substring(2, 5)
                                                + dm2.format(Float
                                                        .parseFloat(""
                                                                + prixVente))
                                                + "0000" + designation
                                                + "          ";
                                        System.out.println(c);
                                        listFinal.add(c);
                                    } else {
                                        String c = modif
                                                + dm.format(Long.parseLong(gc
                                                        .getCode()))
                                                + "NNNNNNN"
                                                + Long.parseLong(a.getTauxTVA()
                                                        .substring(1, 2))
                                                + "NN0"
                                                + a.getRayon().substring(2, 5)
                                                + dm2.format(Float
                                                        .parseFloat(""
                                                                + prixVente))
                                                + "0000" + designation
                                                + "          ";
                                        System.out.println(c);
                                        listFinal.add(c);
                                    }
                            }
                        }
                    }
                }
            }

            BufferedWriter monFluxSortie;
            System.out.println("D\351but creation fichier  \n");
            monFluxSortie = null;
            monFluxSortie = new BufferedWriter(new FileWriter("tomajcai.fic"));

            for (String s : listFinal) {
                monFluxSortie.write(s + "\n");
            }

            monFluxSortie.close();
            FTPClient client = new FTPClient();
            FileInputStream fis = null;
            client.connect(utilService.getSqlRequestByLibelle(pvtCode
                    + ".ip.mp"));
            client.login("serveur", "serveur");
            File f = new File("tomajcai.fic");
            fis = new FileInputStream(f);
            BufferedInputStream bis = new BufferedInputStream(fis);
            client.storeFile("/tomajcai.fic", bis);
            bis.close();
            fis.close();
            client.sendCommand("ADXSTART ADX_SPGM:obmajart.286");
            client.logout();
            System.out.println("fichier uploader sur le serveur");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void uploadToTPVListPromo(List<Promo> listPromo, String pvtCode) {

        try {

            BufferedWriter monFluxSortie;
            System.out.println("D\351but creation fichier  \n");
            monFluxSortie = null;
            monFluxSortie = new BufferedWriter(new FileWriter("tomajcai.fic."
                    + pvtCode, true));

            for (Promo promo : listPromo) {

                if (listGenerique == null) {
                    initVar();
                }

                List<String> listFinal = new ArrayList<>();
                DecimalFormat dm = new DecimalFormat("000000000000");
                DecimalFormat dm2 = new DecimalFormat("0000000");
                DecimalFormat dm3 = new DecimalFormat("0000");

                // listFinal.add("1o0000000000");
                for (Article a : articleDAO.getAllArticleForPromoAndPVT(
                        promo.getLibelReduit(), pvtCode)) {
                    if (a != null) {

                        List<GenCode> genCodeList = genCodeDAO.getAllGenCodeForArticle(a.getCodeArticle(), a.getPvtCode());
                        if (genCodeList != null && genCodeList.size() > 0) {
                            long gcCode = 0;
                            if (null != a.getArticeConsigne()
                                    && !a.getArticeConsigne().equals("")
                                    && !a.getArticeConsigne().equals(
                                            "000000000")) {
                                List<GenCode> gcList = genCodeDAO.getAllGenCodeForArticle(a.getArticeConsigne(), pvtCode);
                                if (gcList != null) {
                                    for (GenCode gx : gcList) {
                                        long plu = Long.parseLong(gx.getCode());
                                        if (plu > 100 && plu < 10000) {
                                            gcCode = Long.parseLong(gx
                                                    .getCode());
                                        }
                                    }
                                }
                            }

                            for (GenCode gc : genCodeList) {
                                if (!gc.isDescenteAEffectuer()) {
                                    continue;
                                }
                                gc.setDescenteAEffectuer(false);
                                genCodeDAO.createOrUpdateGenCode(gc);
                                float prixVente = 0;
                                if (null != a.getPromo()) {
                                    Promo p = promoDAO.getPromo(a.getPromo());
                                    if (p.isEnVente()) {
                                        prixVente = a.getPrixVentePromoTTC();
                                    } else {
                                        prixVente = a.getPrixVenteTTCEnCours();
                                    }
                                } else {
                                    prixVente = a.getPrixVenteTTCEnCours();
                                }

                                if (gc.getCatGen()
                                        .equals(CategorieGenCode.GROS)
                                        || gc.getCatGen().equals(
                                                CategorieGenCode.RCOND)
                                        || gc.getCatGen().equals(
                                                CategorieGenCode.SCOND)
                                        || gc.getCatGen().equals(
                                                CategorieGenCode.DLVM)) {
                                    prixVente = gc.getPrixVenteTTCEnCours();
                                }

                                if (prixVente <= 0 || prixVente > 9999999 || gc.getCode().equals("000000000000")) {
                                    continue;
                                } else {
                                    String modif = "4M";
                                    String designation = a
                                            .getLibelReduit()
                                            .concat("                    ")
                                            .substring(0, 17)
                                            .replaceAll("[^A-Za-z0-9 .%/*+-]",
                                                    " ");
                                    if (gcCode > 0) {

                                        // if(!gc.isActif())
                                        // modif = "4S";
                                        if (listGenerique.contains(a.getCodeArticle())) {
                                            String c = modif
                                                    + dm.format(Long.parseLong(gc.getCode())) + "NNNONNN"
                                                    + Long.parseLong(a.getTauxTVA().substring(1, 2)) + "NN0"
                                                    + a.getRayon().substring(2, 5)
                                                    + dm2.format(Float.parseFloat("" + prixVente))
                                                    + dm3.format(gcCode) + designation + "          ";
                                            System.out.println(c);
                                            listFinal.add(c);
                                        } else {
                                            String c = modif
                                                    + dm.format(Long
                                                            .parseLong(gc
                                                                    .getCode()))
                                                    + "NNNNNNN"
                                                    + Long.parseLong(a
                                                            .getTauxTVA()
                                                            .substring(1, 2))
                                                    + "NN0"
                                                    + a.getRayon().substring(2,
                                                            5)
                                                    + dm2.format(Float.parseFloat("" + prixVente))
                                                    + dm3.format(gcCode)
                                                    + designation
                                                    + "          ";
                                            System.out.println(c);
                                            listFinal.add(c);
                                        }

                                    } else // if(!gc.isActif())
                                    // modif = "4S";
                                     if (listGenerique.contains(a
                                                .getCodeArticle())) {
                                            String c = modif
                                                    + dm.format(Long
                                                            .parseLong(gc
                                                                    .getCode()))
                                                    + "NNNONNN"
                                                    + Long.parseLong(a.getTauxTVA().substring(1, 2))
                                                    + "NN0"
                                                    + a.getRayon().substring(2, 5)
                                                    + dm2.format(Float.parseFloat("" + prixVente))
                                                    + "0000" + designation
                                                    + "          ";
                                            //System.out.println(c);
                                            listFinal.add(c);
                                        } else {
                                            String c = modif
                                                    + dm.format(Long
                                                            .parseLong(gc
                                                                    .getCode()))
                                                    + "NNNNNNN"
                                                    + Long.parseLong(a
                                                            .getTauxTVA()
                                                            .substring(1, 2))
                                                    + "NN0"
                                                    + a.getRayon().substring(2,
                                                            5)
                                                    + dm2.format(Float
                                                            .parseFloat(""
                                                                    + prixVente))
                                                    + "0000" + designation
                                                    + "          ";
                                            //System.out.println(c);
                                            listFinal.add(c);
                                        }
                                }
                            }
                        }
                    }
                }

                nbLigne += listFinal.size() - 1;

                for (String s : listFinal) {
                    monFluxSortie.write(s + "\n");
                }

            }

            monFluxSortie.close();

            if (nbLigne > 1) {
                FTPClient client = new FTPClient();
                FileInputStream fis = null;
                client.connect(utilService.getSqlRequestByLibelle(pvtCode
                        + ".ip.mp"));
                client.login("serveur", "serveur");
                File f = new File("tomajcai.fic." + pvtCode);
                fis = new FileInputStream(f);
                BufferedInputStream bis = new BufferedInputStream(fis);
                client.storeFile("/tomajcai.fic", bis);
                bis.close();
                fis.close();
                client.sendCommand("ADXSTART ADX_SPGM:obmajart.286");
                client.logout();
                System.out.println("fichier uploader sur le serveur");
            } else {
                System.out.println("fichier vide");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void uploadToTPVUpdate(String ip, String pvtCode) {
        if (listGenerique == null) {
            initVar();
        }
        List<String> listFinal = new ArrayList<String>();
        DecimalFormat dm = new DecimalFormat("000000000000");
        DecimalFormat dm2 = new DecimalFormat("0000000");
        DecimalFormat dm3 = new DecimalFormat("0000");
        try {
            listFinal.add("1o0000000000");

            for (String s : articleDAO.getAllCodeArticleForMagUpdated(pvtCode)) {
                Article a = articleDAO.getArticle(new ArticleMagRefPK(pvtCode,
                        s));
                if (a != null) {
                    List<GenCode> genCodeList = genCodeDAO
                            .getAllGenCodeForArticle(a.getCodeArticle(),
                                    a.getPvtCode());
                    if (genCodeList != null && genCodeList.size() > 0) {

                        long gcCode = 0;
                        if (null != a.getArticeConsigne()
                                && !a.getArticeConsigne().equals("")
                                && !a.getArticeConsigne().equals("000000000")) {
                            List<GenCode> gcList = genCodeDAO
                                    .getAllGenCodeForArticle(
                                            a.getArticeConsigne(), pvtCode);
                            if (gcList != null) {
                                for (GenCode gx : gcList) {
                                    long plu = Long.parseLong(gx.getCode());
                                    if (plu > 100 && plu < 10000) {
                                        gcCode = Long.parseLong(gx.getCode());
                                    }
                                }
                            }
                        }

                        for (GenCode gc : genCodeList) {

                            float prixVente = 0;
                            if (null != a.getPromo()) {
                                Promo p = promoDAO.getPromo(a.getPromo());
                                if (p.isEnVente()) {
                                    prixVente = a.getPrixVentePromoTTC();
                                } else {
                                    prixVente = a.getPrixVenteTTCEnCours();
                                }
                            } else {
                                prixVente = a.getPrixVenteTTCEnCours();
                            }

                            if (gc.getCatGen().equals(CategorieGenCode.GROS)
                                    || gc.getCatGen().equals(
                                            CategorieGenCode.RCOND)
                                    || gc.getCatGen().equals(
                                            CategorieGenCode.SCOND)
                                    || gc.getCatGen().equals(
                                            CategorieGenCode.DLVM)) {
                                prixVente = gc.getPrixVenteTTCEnCours();
                            }

                            if (prixVente <= 0 || prixVente > 9999999
                                    || gc.getCode().equals("000000000000")) {
                                continue;
                            } else {
                                String modif = "4M";
                                String designation = a.getLibelReduit()
                                        .concat("                    ")
                                        .substring(0, 17)
                                        .replaceAll("[^A-Za-z0-9 .%/*+-]", " ");
                                if (gcCode > 0) {

                                    // if(!gc.isActif())
                                    // modif = "4S";
                                    if (listGenerique.contains(a
                                            .getCodeArticle())) {

                                        String c = modif
                                                + dm.format(Long.parseLong(gc
                                                        .getCode()))
                                                + "NNNONNN"
                                                + Long.parseLong(a.getTauxTVA()
                                                        .substring(1, 2))
                                                + "NN0"
                                                + a.getRayon().substring(2, 5)
                                                + dm2.format(Float
                                                        .parseFloat(""
                                                                + prixVente))
                                                + dm3.format(gcCode)
                                                + designation + "          ";
                                        //System.out.println(c);
                                        listFinal.add(c);
                                    } else {
                                        String c = modif
                                                + dm.format(Long.parseLong(gc
                                                        .getCode()))
                                                + "NNNNNNN"
                                                + Long.parseLong(a.getTauxTVA()
                                                        .substring(1, 2))
                                                + "NN0"
                                                + a.getRayon().substring(2, 5)
                                                + dm2.format(Float
                                                        .parseFloat(""
                                                                + prixVente))
                                                + dm3.format(gcCode)
                                                + designation + "          ";
                                        //System.out.println(c);
                                        listFinal.add(c);
                                    }

                                } else // if(!gc.isActif())
                                // modif = "4S";
                                 if (listGenerique.contains(a
                                            .getCodeArticle())) {
                                        String c = modif
                                                + dm.format(Long.parseLong(gc
                                                        .getCode()))
                                                + "NNNONNN"
                                                + Long.parseLong(a.getTauxTVA()
                                                        .substring(1, 2))
                                                + "NN0"
                                                + a.getRayon().substring(2, 5)
                                                + dm2.format(Float
                                                        .parseFloat(""
                                                                + prixVente))
                                                + "0000" + designation
                                                + "          ";
                                        //System.out.println(c);
                                        listFinal.add(c);
                                    } else {
                                        String c = modif
                                                + dm.format(Long.parseLong(gc
                                                        .getCode()))
                                                + "NNNNNNN"
                                                + Long.parseLong(a.getTauxTVA()
                                                        .substring(1, 2))
                                                + "NN0"
                                                + a.getRayon().substring(2, 5)
                                                + dm2.format(Float
                                                        .parseFloat(""
                                                                + prixVente))
                                                + "0000" + designation
                                                + "          ";
                                        //System.out.println(c);
                                        listFinal.add(c);
                                    }
                            }
                        }
                    }
                }
            }

            BufferedWriter monFluxSortie;
            System.out.println("D\351but creation fichier  \n");
            monFluxSortie = null;
            monFluxSortie = new BufferedWriter(new FileWriter("tomajcai.fic"));

            for (String s : listFinal) {
                monFluxSortie.write(s + "\n");
            }

            monFluxSortie.close();
            FTPClient client = new FTPClient();
            FileInputStream fis = null;
            client.connect(utilService.getSqlRequestByLibelle(pvtCode
                    + ".ip.mp"));
            client.login("serveur", "serveur");
            File f = new File("tomajcai.fic");
            fis = new FileInputStream(f);
            BufferedInputStream bis = new BufferedInputStream(fis);
            client.storeFile("/tomajcai.fic", bis);
            bis.close();
            fis.close();
            client.sendCommand("ADXSTART ADX_SPGM:obmajart.286");
            client.logout();
            System.out.println("fichier uploader sur le serveur");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Methode modifié par Serge , pour la descente quotidienne vers S.A avec
     * les promos lots suivant le nouveau MAJART.DAT
     */
    @SuppressWarnings({"rawtypes", "unused"})
    @Override
    public void beginToTPVUpdate(String ip, String pvtCode) {

        try {

            DecimalFormat codeEANFormat = new DecimalFormat("000000000000");
            DecimalFormat formatPrixUnitaire = new DecimalFormat("0000000");
            DecimalFormat formatPrixDuLot = new DecimalFormat("00000");
            DecimalFormat formatNbreArticleLot = new DecimalFormat("00");
            DecimalFormat formatFamille = new DecimalFormat("000");
            String lastFileLine = "4Cÿÿÿÿÿÿÿÿÿÿÿÿ"
                    + "                                                                                                                                                                                                                                                 ";
            String articleLie = "0000";
            String premiereSerieZero = "00000000";
            String derniereSerieZero = "0000000000";
            String begin = "4M";
            int value1 = 0;
            char nul = (char) value1;
            char EURO = '\u20ac';
            System.out.println("EURO : " + EURO);
            List<String> listFinal = new ArrayList<String>();
            String line = "";
            int count = 0;
            String lastRecord = "";
            // recupération des articles à descendre
            List<Article> listArticles = articleDAO.getAllArticleByMagForUpdate(pvtCode);
            if (listArticles != null && listArticles.size() > 0) {
                for (int i = 0; i < listArticles.size(); i++) {
                    Article a = listArticles.get(i);
                    if (listGenerique == null) {
                        initVar();
                    }
                    if (a != null) {
                        List<GenCode> genCodeList = genCodeDAO.getAllGenCodeForArticle(a.getCodeArticle(), a.getPvtCode());
                        if (genCodeList != null && genCodeList.size() > 0) {
                            HashMap<String, GenCode> gencodeMap = new HashMap<>();
                            for (GenCode gc : genCodeList) {
                                gencodeMap.put(gc.getCode(), gc);
                            }

                            System.out.println(" gencodeMap :  " + gencodeMap.size());
                            Iterator it = gencodeMap.entrySet().iterator();
                            while (it.hasNext()) {
                                Map.Entry pair = (Map.Entry) it.next();
                                String code = (String) pair.getKey();
                                GenCode gc = (GenCode) pair.getValue();
                                int asciiLot = 0;
                                char promoLot = (char) asciiLot;;
                                float prixVente = 0;
                                Integer prixLot = 0;
                                Integer nombreArticleLot = 0;
                                if (a.getPromo() != null) {
                                    Promo p = promoDAO.getPromo(a.getPromo());
                                    if (a.getTypePromo() == Article.PROMO_ORDINAIRE) {
                                        if (p.isEnVente()) {
                                            prixVente = a.getPrixVentePromoTTC();
                                        } else {
                                            prixVente = a.getPrixVenteTTCEnCours();
                                        }
                                    } else if (a.getTypePromo() == Article.PROMO_LOT_HOMOGENE) {
                                        if (p.isEnVente()) {
                                            prixVente = a.getPrixVenteTTCEnCours();
                                            asciiLot = 02;
                                            promoLot = (char) asciiLot;
                                            prixLot = a.getPrixDuLot();
                                            nombreArticleLot = a.getQuantiteLot();
                                        } else {
                                            prixVente = a.getPrixVenteTTCEnCours();
                                        }
                                    } else {
                                        prixVente = a.getPrixVenteTTCEnCours();
                                    }
                                } else {
                                    prixVente = a.getPrixVenteTTCEnCours();
                                }
                                if (gc.getCatGen().equals(CategorieGenCode.GROS)
                                        || gc.getCatGen().equals(CategorieGenCode.RCOND)
                                        || gc.getCatGen().equals(CategorieGenCode.SCOND)
                                        || gc.getCatGen().equals(CategorieGenCode.DLVM)) {
                                    prixVente = gc.getPrixVenteTTCEnCours();
                                }
                                if (prixVente <= 0 || prixVente > 9999999 || gc.getCode().equals("000000000000")) {
                                    continue;
                                } else {
                                    char tva;
                                    int asciiValue;
                                    switch (a.getTauxTVA()) {
                                        case "03":
                                            asciiValue = 16;
                                            tva = (char) asciiValue;
                                            break;
                                        case "04":
                                            asciiValue = 16;
                                            tva = (char) asciiValue;
                                            break;
                                        case "09":
                                            asciiValue = 0;
                                            tva = (char) asciiValue;
                                            break;

                                        default:
                                            asciiValue = 16;
                                            tva = (char) asciiValue;
                                            break;
                                    }

                                    String designation = a
                                            .getLibelReduit()
                                            .concat("                    ")
                                            .substring(0, 18)
                                            .replaceAll("[^A-Za-z0-9 .%/*+-]",
                                                    " ");
                                    String lineArticle = codeEANFormat
                                            .format(Long.parseLong(gc.getCode()))
                                            + nul
                                            + tva
                                            + EURO
                                            + promoLot
                                            + formatFamille.format(Long.parseLong(a.getRayon().substring(2)))
                                            + premiereSerieZero
                                            + formatNbreArticleLot.format(nombreArticleLot)
                                            + formatPrixUnitaire.format(prixVente)
                                            + formatPrixDuLot.format(prixLot)
                                            + articleLie
                                            + designation
                                            + derniereSerieZero;
                                    lastRecord = lineArticle;
                                    count++;
                                    System.out
                                            .println("###### #  Count Value in Gencode loop  : "
                                                    + count);
                                    if (count == 3) {
                                        line = line
                                                + "C"
                                                + lineArticle
                                                + "                                ";
                                        listFinal.add(line);
                                        listFinal.add("\n");
                                        line = "";
                                        count = 0;
                                    } else if (line.equals("")) {
                                        line = begin + lineArticle;
                                    } else {
                                        line = line + "C" + lineArticle;
                                    }
                                }
                            }
                            // }
                            System.out.println("###### #  Count Value   : "
                                    + count);
                            if ((i + 1) == listArticles.size()) {
                                System.out
                                        .println(" @@@@@@@   Count Value Final  : "
                                                + count);
                                switch (count) {
                                    case 1:
                                        line = line
                                                + "C"
                                                + lastRecord
                                                + "C"
                                                + lastRecord
                                                + "                                ";
                                        listFinal.add(line);
                                        listFinal.add("\n");
                                        break;
                                    case 2:
                                        line = line
                                                + "C"
                                                + lastRecord
                                                + "                                ";
                                        listFinal.add(line);
                                        listFinal.add("\n");
                                        break;
                                    default:
                                        break;
                                }
                                // listFinal.add(lastFileLine);
                            } else {

                            }

                        }
                    }

                }
                System.out.println("D\351but creation fichier  \n");
                File fileDir = new File("tomajcai.fic." + pvtCode);
                System.out.println(" listFinal  size = " + listFinal.size());
                Writer monFluxSortie = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileDir), "cp1252"));
                // monFluxSortie.write("1o0000000000"+"                                                                                                                                                                                                                                                   ");
                monFluxSortie.write("\n");
                for (String s : listFinal) {
                    monFluxSortie.write(s);
                }
                monFluxSortie.close();
                File archive = new File("C:\\archive_descente\\tomajcai.fic." + pvtCode + "." + System.currentTimeMillis());
                FileUtils.copyFile(fileDir, archive);
                System.out.println("FIN creation fichier  \n");
                System.out.println("fileDir.getName : " + fileDir.getName() + "\n");
                File f = new File("tomajcai.fic." + pvtCode);
                copyFilesToMagRelais(f, pvtCode);
            } else {
                System.out.println("Aucun article a descendre ");
            }
            /*
			 * FTPClient client = new FTPClient(); FileInputStream fis = null;
			 * client.connect(utilService.getSqlRequestByLibelle(pvtCode+
			 * ".ip.mp")); client.login("serveur", "serveur"); File f = new
			 * File("tomajcai.fic"); fis = new FileInputStream(f);
			 * BufferedInputStream bis = new BufferedInputStream(fis);
			 * client.storeFile("/tomajcai.fic", bis); bis.close(); fis.close();
			 * //client.sendCommand("ADXSTART ADX_SPGM:obmajart.286");
			 * client.logout();
             */

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private void copyFilesToMagRelais(File file, String pvtCode) {
        System.out.println("DEBUT DE LA COPIE DES FICHIERS");
        String path = "\\\\"
                + utilService.getSqlRequestByLibelle(pvtCode + ".ip.mp")
                + "\\GPV3000SA\\descente\\tomajcai.fic";
        File dest = new File(path);
        try {
            FileUtils.copyFile(file, dest, true);
            System.out.println("Fichier  : " + file.getName() + "  Copier");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Copie des fichiers termine");
    }

    @Override
    public void uploadToTPVListArticle(List<Article> listArticles, String ip,
            String pvtCode) {

        try {

            BufferedWriter monFluxSortie;
            System.out.println("D\351but creation fichier  \n");
            monFluxSortie = null;
            monFluxSortie = new BufferedWriter(new FileWriter("tomajcai.fic."
                    + pvtCode));
            monFluxSortie.write("1o0000000000\n");

            for (Article codeArticle : listArticles) {

                if (listGenerique == null) {
                    initVar();
                }

                List<String> listFinal = new ArrayList<String>();
                DecimalFormat dm = new DecimalFormat("000000000000");
                DecimalFormat dm2 = new DecimalFormat("0000000");
                DecimalFormat dm3 = new DecimalFormat("0000");

                Article a = articleDAO.getArticle(new ArticleMagRefPK(pvtCode,
                        codeArticle.getCodeArticle()));

                if (a != null) {
                    List<GenCode> genCodeList = genCodeDAO
                            .getAllGenCodeForArticle(a.getCodeArticle(),
                                    a.getPvtCode());
                    if (genCodeList != null && genCodeList.size() > 0) {

                        long gcCode = 0;
                        if (null != a.getArticeConsigne()
                                && !a.getArticeConsigne().equals("")
                                && !a.getArticeConsigne().equals("000000000")) {
                            List<GenCode> gcList = genCodeDAO
                                    .getAllGenCodeForArticle(
                                            a.getArticeConsigne(), pvtCode);
                            if (gcList != null) {
                                for (GenCode gx : gcList) {
                                    long plu = Long.parseLong(gx.getCode());
                                    if (plu > 100 && plu < 10000) {
                                        gcCode = Long.parseLong(gx.getCode());
                                    }
                                }
                            }
                        }
                        for (GenCode gc : genCodeList) {
                            float prixVente = 0;
                            if (null != a.getPromo()) {
                                Promo p = promoDAO.getPromo(a.getPromo());
                                if (p.isEnVente()) {
                                    prixVente = a.getPrixVentePromoTTC();
                                } else {
                                    prixVente = a.getPrixVenteTTCEnCours();
                                }
                            } else {
                                prixVente = a.getPrixVenteTTCEnCours();
                            }
                            if (gc.getCatGen().equals(CategorieGenCode.GROS)
                                    || gc.getCatGen().equals(
                                            CategorieGenCode.RCOND)
                                    || gc.getCatGen().equals(
                                            CategorieGenCode.SCOND)
                                    || gc.getCatGen().equals(
                                            CategorieGenCode.DLVM)) {
                                prixVente = gc.getPrixVenteTTCEnCours();
                            }

                            if (prixVente <= 0 || prixVente > 9999999
                                    || gc.getCode().equals("000000000000")) {
                                continue;
                            } else {
                                String modif = "4M";
                                String designation = a.getLibelReduit()
                                        .concat("                    ")
                                        .substring(0, 17)
                                        .replaceAll("[^A-Za-z0-9 .%/*+-]", " ");
                                if (gcCode > 0) {
                                    if (listGenerique.contains(a
                                            .getCodeArticle())) {
                                        String c = modif
                                                + dm.format(Long.parseLong(gc
                                                        .getCode()))
                                                + "NNNONNN"
                                                + Long.parseLong(a.getTauxTVA()
                                                        .substring(1, 2))
                                                + "NN0"
                                                + a.getRayon().substring(2, 5)
                                                + dm2.format(Float
                                                        .parseFloat(""
                                                                + prixVente))
                                                + dm3.format(gcCode)
                                                + designation + "          ";
                                        System.out.println(c);
                                        listFinal.add(c);
                                    } else {
                                        String c = modif
                                                + dm.format(Long.parseLong(gc
                                                        .getCode()))
                                                + "NNNNNNN"
                                                + Long.parseLong(a.getTauxTVA()
                                                        .substring(1, 2))
                                                + "NN0"
                                                + a.getRayon().substring(2, 5)
                                                + dm2.format(Float
                                                        .parseFloat(""
                                                                + prixVente))
                                                + dm3.format(gcCode)
                                                + designation + "          ";
                                        System.out.println(c);
                                        listFinal.add(c);
                                    }

                                } else if (listGenerique.contains(a
                                        .getCodeArticle())) {
                                    String c = modif
                                            + dm.format(Long.parseLong(gc
                                                    .getCode()))
                                            + "NNNONNN"
                                            + Long.parseLong(a.getTauxTVA()
                                                    .substring(1, 2))
                                            + "NN0"
                                            + a.getRayon().substring(2, 5)
                                            + dm2.format(Float
                                                    .parseFloat(""
                                                            + prixVente))
                                            + "0000" + designation
                                            + "          ";
                                    System.out.println(c);
                                    listFinal.add(c);
                                } else {
                                    String c = modif
                                            + dm.format(Long.parseLong(gc
                                                    .getCode()))
                                            + "NNNNNNN"
                                            + Long.parseLong(a.getTauxTVA()
                                                    .substring(1, 2))
                                            + "NN0"
                                            + a.getRayon().substring(2, 5)
                                            + dm2.format(Float
                                                    .parseFloat(""
                                                            + prixVente))
                                            + "0000" + designation
                                            + "          ";
                                    System.out.println(c);
                                    listFinal.add(c);
                                }
                            }
                        }
                    }
                }

                for (String s : listFinal) {
                    monFluxSortie.write(s + "\n");
                }
            }

            monFluxSortie.close();
            FTPClient client = new FTPClient();
            FileInputStream fis = null;
            client.connect(utilService.getSqlRequestByLibelle(pvtCode
                    + ".ip.mp"));
            client.login("serveur", "serveur");
            File f = new File("tomajcai.fic." + pvtCode);
            fis = new FileInputStream(f);
            BufferedInputStream bis = new BufferedInputStream(fis);
            client.storeFile("/tomajcai.fic", bis);
            bis.close();
            fis.close();
            client.sendCommand("ADXSTART ADX_SPGM:obmajart.286");
            client.logout();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("rawtypes")
    @Override
    public void generateMAJART(List<Article> listArticles, String ip,
            String pvtCode) {
        try {
            List<GenCodeDTO> listDTO = new ArrayList<>();
            DecimalFormat codeEANFormat = new DecimalFormat("000000000000");
            DecimalFormat formatPrixUnitaire = new DecimalFormat("0000000");
            DecimalFormat formatPrixDuLot = new DecimalFormat("00000");
            DecimalFormat formatNbreArticleLot = new DecimalFormat("00");
            DecimalFormat formatFamille = new DecimalFormat("000");
            String lastFileLine = "4Cÿÿÿÿÿÿÿÿÿÿÿÿ"
                    + "                                                                                                                                                                                                                                                 ";
            String articleLie = "0000";
            String premiereSerieZero = "00000000";
            String derniereSerieZero = "0000000000";
            String begin = "4M";
            int value1 = 0;
            char nul = (char) value1;
            char EURO = '\u20ac';
            // String EURO="#";
            System.out.println("EURO : " + EURO);
            List<String> listFinal = new ArrayList<>();
            String line = "";
            int count = 0;
            String lastRecord = "";
            for (int i = 0; i < listArticles.size(); i++) {
                Article a = listArticles.get(i);
                // System.out.println(" Article :  "+a.getCodeArticle());
                if (listGenerique == null) {
                    initVar();
                }
                if (a != null) {
                    List<GenCode> genCodeList = genCodeDAO
                            .getAllGenCodeForArticle(a.getCodeArticle(),
                                    a.getPvtCode());
                    System.out.println(" Article :  " + a.getCodeArticle()
                            + " | genCodeList  size = " + genCodeList.size());
                    // List<GenCode> genCodeList = a.getListGenCode();
                    if (genCodeList != null && genCodeList.size() > 0) {
                        HashMap<String, GenCode> gencodeMap = new HashMap<>();
                        genCodeList.stream().forEach((gc) -> {
                            gencodeMap.put(gc.getCode(), gc);
                        });

                        System.out.println(" gencodeMap :  " + gencodeMap.size());
                        for (Map.Entry pair : gencodeMap.entrySet()) {
                            String code = (String) pair.getKey();
                            GenCode gc = (GenCode) pair.getValue();
                            System.out.println(" code :  " + code);
                            System.out.println(" hashGen :  " + gc);
                            System.out.println("@@@@@@@@@@@@@@@@@@@@@  count : " + count);
                            float prixVente = 0;
                            int asciiLot = 0;
                            char promoLot = (char) asciiLot;
                            Integer prixLot = 0;
                            Integer nombreArticleLot = 0;
                            if (null != a.getPromo()) {
                                Promo p = promoDAO.getPromo(a.getPromo());
                                if (Objects.equals(a.getTypePromo(), Article.PROMO_ORDINAIRE)) {
                                    if (p.isEnVente()) {
                                        prixVente = a.getPrixVentePromoTTC();
                                    } else {
                                        prixVente = a.getPrixVenteTTCEnCours();
                                    }
                                } else if (Objects.equals(a.getTypePromo(), Article.PROMO_LOT_HOMOGENE)) {
                                    if (p.isEnVente()) {
                                        prixVente = a.getPrixVenteTTCEnCours();
                                        asciiLot = 02;
                                        promoLot = (char) asciiLot;
                                        prixLot = a.getPrixDuLot();
                                        nombreArticleLot = a.getQuantiteLot();

                                    } else {
                                        prixVente = a.getPrixVenteTTCEnCours();
                                    }
                                } else {
                                    prixVente = a.getPrixVenteTTCEnCours();
                                }
                            } else {
                                prixVente = a.getPrixVenteTTCEnCours();
                            }
                            if (gc.getCatGen().equals(CategorieGenCode.GROS)
                                    || gc.getCatGen().equals(CategorieGenCode.RCOND)
                                    || gc.getCatGen().equals(CategorieGenCode.SCOND)
                                    || gc.getCatGen().equals(CategorieGenCode.DLVM)) {
                                prixVente = gc.getPrixVenteTTCEnCours();
                            }
                            if (prixVente <= 0 || prixVente > 9999999 || gc.getCode().equals("000000000000")) {
                                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@  gc.getCode  :   " + gc.getCode());
                                continue;
                            } else {
                                count++;
                                if (line.length() == 224) {
                                    listFinal
                                            .add(begin
                                                    + line
                                                    + "                                ");
                                    listFinal.add("\n");
                                    line = "";
                                } else {

                                }
                                char tva;
                                int asciiValue;
                                switch (a.getTauxTVA()) {
                                    case "03":
                                        asciiValue = 16;
                                        tva = (char) asciiValue;
                                        break;
                                    case "04":
                                        asciiValue = 16;
                                        tva = (char) asciiValue;
                                        break;
                                    case "09":
                                        asciiValue = 0;
                                        tva = (char) asciiValue;
                                        break;

                                    default:
                                        asciiValue = 16;
                                        tva = (char) asciiValue;
                                        break;
                                }

                                String designation = a.getLibelReduit()
                                        .concat("                    ")
                                        .substring(0, 18)
                                        .replaceAll("[^A-Za-z0-9 .%/*+-]", " ");
                                /*
                                * x Nouveau
                                 */

                                String lineArticle = codeEANFormat.format(Long
                                        .parseLong(gc.getCode()))
                                        + nul
                                        + tva
                                        + EURO
                                        + promoLot
                                        + formatFamille.format(Long.parseLong(a.getRayon().substring(2)))
                                        + premiereSerieZero
                                        + formatNbreArticleLot.format(nombreArticleLot)
                                        + formatPrixUnitaire.format(prixVente)
                                        + formatPrixDuLot.format(prixLot)
                                        + articleLie
                                        + designation + derniereSerieZero;
                                lastRecord = lineArticle;
                                if (count == 3) {
                                    line = line
                                            + "C"
                                            + lineArticle
                                            + "                                ";
                                    listFinal.add(line);
                                    listFinal.add("\n");
                                    line = "";
                                    count = 0;
                                } else if (line.equals("")) {
                                    line = begin + lineArticle;
                                } else {
                                    line = line + "C" + lineArticle;
                                }
                            }
                        }
                        // }
                        System.out.println("nombre i : " + i
                                + " ! listfinal  size " + listFinal.size());
                        if ((i + 1) == listArticles.size()) {
                            switch (count) {
                                case 1:
                                    line = line + "C" + lastRecord + "C"
                                            + lastRecord
                                            + "                                ";
                                    listFinal.add(line);
                                    listFinal.add("\n");
                                    break;
                                case 2:
                                    line = line + "C" + lastRecord
                                            + "                                ";
                                    listFinal.add(line);
                                    listFinal.add("\n");
                                    break;
                                default:
                                    break;
                            }
                            // listFinal.add(lastFileLine);
                        }

                    }
                }

            }
            System.out.println("D\351but creation fichier  \n");
            // File fileDir = new File("MAJART.DAT");
            File fileDir = new File("tomajcai.fic." + pvtCode);
            System.out.println(" listFinal  size = " + listFinal.size());
            // monFluxSortie.write("1o0000000000"+"                                                                                                                                                                                                                                                   ");
            try (Writer monFluxSortie = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileDir), "cp1252"))) {
                // monFluxSortie.write("1o0000000000"+"                                                                                                                                                                                                                                                   ");
                monFluxSortie.write("\n");
                for (String s : listFinal) {
                    monFluxSortie.write(s);
                }
            }
            File archive = new File("C:\\archive_descente\\tomajcai.fic." + pvtCode + "." + System.currentTimeMillis());
            FileUtils.copyFile(fileDir, archive);
            System.out.println("FIN creation fichier  \n");

            System.out.println("fileDir.getName : " + fileDir.getName() + "\n");
            File f = new File("tomajcai.fic." + pvtCode);
            copyFilesToMagRelais(f, pvtCode);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void uploadToTPVArticle(String codeArticle, String ip, String pvtCode) {
        if (listGenerique == null) {
            initVar();
        }
        List<String> listFinal = new ArrayList<>();
        DecimalFormat dm = new DecimalFormat("000000000000");
        DecimalFormat dm2 = new DecimalFormat("0000000");
        DecimalFormat dm3 = new DecimalFormat("0000");
        try {
            listFinal.add("1o0000000000");

            Article a = articleDAO.getArticle(new ArticleMagRefPK(pvtCode,
                    codeArticle));
            if (a != null) {
                List<GenCode> genCodeList = genCodeDAO.getAllGenCodeForArticle(
                        a.getCodeArticle(), a.getPvtCode());
                if (genCodeList != null && genCodeList.size() > 0) {

                    long gcCode = 0;
                    if (null != a.getArticeConsigne()
                            && !a.getArticeConsigne().equals("")
                            && !a.getArticeConsigne().equals("000000000")) {
                        List<GenCode> gcList = genCodeDAO
                                .getAllGenCodeForArticle(a.getArticeConsigne(),
                                        pvtCode);
                        if (gcList != null) {
                            for (GenCode gx : gcList) {
                                long plu = Long.parseLong(gx.getCode());
                                if (plu > 100 && plu < 10000) {
                                    gcCode = Long.parseLong(gx.getCode());
                                }
                            }
                        }
                    }

                    for (GenCode gc : genCodeList) {

                        float prixVente = 0;
                        if (null != a.getPromo()) {
                            Promo p = promoDAO.getPromo(a.getPromo());
                            if (p.isEnVente()) {
                                prixVente = a.getPrixVentePromoTTC();
                            } else {
                                prixVente = a.getPrixVenteTTCEnCours();
                            }
                        } else {
                            prixVente = a.getPrixVenteTTCEnCours();
                        }

                        if (gc.getCatGen().equals(CategorieGenCode.GROS)
                                || gc.getCatGen()
                                .equals(CategorieGenCode.RCOND)
                                || gc.getCatGen()
                                .equals(CategorieGenCode.SCOND)
                                || gc.getCatGen().equals(CategorieGenCode.DLVM)) {
                            prixVente = gc.getPrixVenteTTCEnCours();
                        }

                        if (prixVente <= 0 || prixVente > 9999999
                                || gc.getCode().equals("000000000000")) {
                            continue;
                        } else {
                            String modif = "4M";
                            String designation = a.getLibelReduit()
                                    .concat("                    ")
                                    .substring(0, 17)
                                    .replaceAll("[^A-Za-z0-9 .%/*+-]", " ");
                            if (gcCode > 0) {

                                // if(!gc.isActif())
                                // modif = "4S";
                                if (listGenerique.contains(a.getCodeArticle())) {

                                    String c = modif
                                            + dm.format(Long.parseLong(gc
                                                    .getCode()))
                                            + "NNNONNN"
                                            + Long.parseLong(a.getTauxTVA()
                                                    .substring(1, 2))
                                            + "NN0"
                                            + a.getRayon().substring(2, 5)
                                            + dm2.format(Float.parseFloat(""
                                                    + prixVente))
                                            + dm3.format(gcCode) + designation
                                            + "          ";
                                    System.out.println(c);
                                    listFinal.add(c);
                                } else {
                                    String c = modif
                                            + dm.format(Long.parseLong(gc
                                                    .getCode()))
                                            + "NNNNNNN"
                                            + Long.parseLong(a.getTauxTVA()
                                                    .substring(1, 2))
                                            + "NN0"
                                            + a.getRayon().substring(2, 5)
                                            + dm2.format(Float.parseFloat(""
                                                    + prixVente))
                                            + dm3.format(gcCode) + designation
                                            + "          ";
                                    System.out.println(c);
                                    listFinal.add(c);
                                }

                            } else // if(!gc.isActif())
                            // modif = "4S";
                             if (listGenerique.contains(a.getCodeArticle())) {
                                    String c = modif
                                            + dm.format(Long.parseLong(gc
                                                    .getCode()))
                                            + "NNNONNN"
                                            + Long.parseLong(a.getTauxTVA()
                                                    .substring(1, 2))
                                            + "NN0"
                                            + a.getRayon().substring(2, 5)
                                            + dm2.format(Float.parseFloat(""
                                                    + prixVente)) + "0000"
                                            + designation + "          ";
                                    System.out.println(c);
                                    listFinal.add(c);
                                } else {
                                    String c = modif
                                            + dm.format(Long.parseLong(gc
                                                    .getCode()))
                                            + "NNNNNNN"
                                            + Long.parseLong(a.getTauxTVA()
                                                    .substring(1, 2))
                                            + "NN0"
                                            + a.getRayon().substring(2, 5)
                                            + dm2.format(Float.parseFloat(""
                                                    + prixVente)) + "0000"
                                            + designation + "          ";
                                    System.out.println(c);
                                    listFinal.add(c);
                                }
                        }
                    }
                }
            }

            BufferedWriter monFluxSortie;
            System.out.println("D\351but creation fichier  \n");
            monFluxSortie = null;
            monFluxSortie = new BufferedWriter(new FileWriter("tomajcai.fic"));

            for (String s : listFinal) {
                monFluxSortie.write(s + "\n");
            }

            monFluxSortie.close();
            FTPClient client = new FTPClient();
            FileInputStream fis = null;
            client.connect(utilService.getSqlRequestByLibelle(pvtCode
                    + ".ip.mp"));
            client.login("serveur", "serveur");
            File f = new File("tomajcai.fic");
            fis = new FileInputStream(f);
            BufferedInputStream bis = new BufferedInputStream(fis);
            client.storeFile("/tomajcai.fic", bis);
            bis.close();
            fis.close();
            client.sendCommand("ADXSTART ADX_SPGM:obmajart.286");
            client.logout();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void uploadToTPVTest(String ip, String pvtCode) throws IOException {
        // List<String> listFam = new ArrayList<String>();
        // for (Rayon ray : classservice.getAllRayon()) {
        // List<Famille> listTemp =
        // classservice.getAllFamilleByRayonBySecteur(ray.getCode());
        // if (listTemp != null && listTemp.size() > 0) {
        // for (Famille f : listTemp) {
        // if (!f.getCode().endsWith("999")) {
        // String lib = "";
        // if (f.getLibelle().length() > 17) {
        // lib = f.getLibelle().substring(0, 17);
        // }
        // if (f.getLibelle().length() <= 17) {
        // lib = String.format("%-17s", f.getLibelle());
        // }
        //
        // listFam.add(f.getCode().substring(5, 8) + " " + lib + " " + "03");
        // }
        // }
        // String lib = "";
        // if (ray.getLibelle().length() > 17) {
        // lib = ray.getLibelle().substring(0, 17);
        // }
        // if (ray.getLibelle().length() <= 17) {
        // lib = String.format("%-17s", ray.getLibelle());
        // }
        //
        // listFam.add("    " + lib + " " + "03");
        // }
        // }
        // BufferedWriter monFluxSortie;
        // System.out.println("D\351but creation fichier  \n");
        // monFluxSortie = null;
        // monFluxSortie = new BufferedWriter(new FileWriter("MAJFAM"));
        // for (String s : listFam) {
        // System.out.println(s);
        // monFluxSortie.write(s + "\n");
        // }
        // monFluxSortie.close();
        // FTPClient client = new FTPClient();
        // FileInputStream fis = null;
        // client.connect("192.168.1.1");
        // client.login("serveur", "serveur");
        // File f = new File("MAJFAM");
        // fis = new FileInputStream(f);
        // client.storeFile("/MAJFAM", fis);
        // // client.sendCommand("ADXSTART adx_ipgm obmajfam.286");
        // client.logout();
        // fis.close();
    }

    @SuppressWarnings("resource")
    private List<LigneMouvementSA> getMouvSAFromLOGASCI(String date, String pvtCode) throws GPV3000RemonteeException {
        List<LigneMouvementSA> listSA = new ArrayList<>();

        System.out.println("DEBUT  getMouvSAFromLOGASCI");
        try {

            /*
			 * FTPClient client = new FTPClient();
			 * client.connect(utilService.getSqlRequestByLibelle(pvtCode+
			 * ".ip.mp")); // prod client.login("serveur", "serveur"); // dev //
			 * client.login("anonymous", "");
			 * 
			 * File f = new File("LOGASCI.DAT"); if (!f.exists()) {
			 * f.createNewFile(); } OutputStream os = new FileOutputStream(f);
			 * client.retrieveFile(utilService.getSqlRequestByLibelle(pvtCode +
			 * ".dir.mp"), os); os.flush(); os.close(); client.logout();
			 * 
			 * InputStream ips = new FileInputStream(f); InputStreamReader ipsr
			 * = new InputStreamReader(ips); BufferedReader br = new
			 * BufferedReader(ipsr);
             */
            // String ligne;
            // String fileLigne;
            long montantTotal = 0;
            long qteTotal = 0;
            LigneMouvementSA lastLM = null;
            // ArrayList<String> tbl = new ArrayList<String>();
            // while ((fileLigne = br.readLine()) != null) {
            // if (!fileLigne.equals("")) {
            // tbl.add(fileLigne);
            // }
            // }
            //
            List<String> tbl = copyLOGASCIIFilesFromRelais("\\\\" + utilService.getSqlRequestByLibelle(pvtCode + ".ip.mp") + "\\gpv3000sa\\LOG_ASCI.DAT", pvtCode);
            //List<String> tbl = copyLOGASCIIFilesFromRelais("/Users/tagsergi/Documents/versions/LOG_ASCI.DAT", pvtCode);
            System.out.println("@@@@@@@@@@@@@   Recuparation du LOG_ASCII Terminee");
            int numLigne = 0;
            for (String ligne : tbl) {
                String lign = ligne.replace("\"", "").trim();
                //System.out.println(lign);
                if (!lign.startsWith("00") && !lign.startsWith("01") && !lign.startsWith("02")) {
                    continue;
                }
                String[] lig = lign.split(";");
                LigneMouvementSA ligneMouv = null;

                if (lign.startsWith("00") && !(String.valueOf(lig[3]).substring(0, 6)).equals(date)) {
                    throw new GPV3000RemonteeException(
                            "La date de remont\u00E9e ne correspond pas au contenu du fichier");
                }

                if (lign.startsWith("01")) {
                    if (String.valueOf(lig[7]).equals("80")
                            || String.valueOf(lig[7]).equals("32")
                            || String.valueOf(lig[7]).equals("81")
                            || String.valueOf(lig[7]).equals("22")
                            || String.valueOf(lig[7]).equals("20")) {
                        ligneMouv = checkArt(Long.parseLong(lig[1]), -1, -1
                                * Long.parseLong(lig[2]), pvtCode);
                        if (ligneMouv != null) {
                            montantTotal -= Long.parseLong(lig[2]);
                            qteTotal--;
                        }

                    } else if (String.valueOf(lig[7]).equals("00")
                            || String.valueOf(lig[7]).equals("01")
                            || String.valueOf(lig[7]).equals("03")) {
                        ligneMouv = checkArt(Long.parseLong(lig[1]), 1,
                                Long.parseLong(lig[2]), pvtCode);
                        if (ligneMouv != null) {
                            montantTotal += Long.parseLong(lig[2]);
                            qteTotal++;
                        }
                    }
                    if (ligneMouv != null) {
                        lastLM = ligneMouv;
                        listSA.add(ligneMouv);
                    }
                }
                if (lign.startsWith("02")) {
                    lastLM.setQte(lastLM.getQte() * Long.parseLong(lig[6]));
                    if (lastLM.getQte() > 0) {
                        qteTotal = qteTotal + (Long.parseLong(lig[6])) - 1;
                    } else {
                        qteTotal = qteTotal - (Long.parseLong(lig[6])) + 1;
                    }
                }

                numLigne++;
                System.out.println("@@@@@@@@@@@@@   numLigne : " + numLigne);
            }
            System.out.println("montantTotal : " + String.valueOf(montantTotal));
            System.out.println("qteTotal : " + String.valueOf(qteTotal));
            // br.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return listSA;
    }

    private List<String> copyLOGASCIIFilesFromRelais(String filePath, String mag) {
        System.out.println("DEBUT DE LA COPIE DES FICHIERS");
        File source = new File(filePath);
        List<String> response;
        System.out.println("Chemin SOURCE : " + source.getAbsolutePath());
        try {
            response = FileUtils.readLines(source);// moveFileToDirectory(source,
            // dest,true);
            System.out.println("filePath" + filePath + "  Copier");
            System.out.println("Copie des fichiers termine");
            return response;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private LigneMouvementSA checkArt(long genCode, long qte, long montant,
            String mag) {
        if (genCode != 6001 && genCode != 601 && genCode != 800
                && genCode != 900 && genCode != 6000 && genCode != 6002
                && genCode != 9001) {
            LigneMouvementSA sa = new LigneMouvementSA(genCode, qte, montant);
            return sa;
        }
        return null;
    }

    @Override
    public String downoadFromTPV(String mag, String ip, String date) throws GPV3000RemonteeException, Exception {
        System.out.println("DEBUT downoadFromTPV ");
        try {
            boolean plu = false;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String queryTemp = "SELECT c FROM EnteteMouvement c WHERE c.pvt.pvtCode='"
                    + mag
                    + "' AND c.dateMouvement = '"
                    + date
                    + "' AND c.origineMouvement = 'CAISS'";
            List<EnteteMouvement> listEM = enteteMouvementDAO.executeQuery(queryTemp);
            // si mouvement exist alors on arret tout
            if (listEM != null && listEM.size() > 0) {
                return "EXIST";
            }
            List<LigneMouvementSA> listMouv = getMouvSAFromLOGASCI(date.substring(2, 8), mag);
            if (listMouv.isEmpty()) {
                return "FAILURE";
            }
            PointDeVente pvt = pointDeVenteDAO.getPVT(mag);
            Depot depot = pointDeVenteDAO.getDepotPrincipalForMag(pvt.getPvtCode());
            // creation et mis à jour de l'entete mouvement
            EnteteMouvement em = new EnteteMouvement();
            em.setDateMouvement(sdf.parse(date));
            em.setObservations("REMONTEE DE CAISSE DU " + date);
            em.setOrigineMouvement(origineMvmtnDAO.getOrigineMouvement("CAISS"));
            em.setPvt(pvt);
            em.setUserCreation("GPV3000");
            List<EnteteMouvement> listMouvementForGC = new ArrayList<>();
            // creation liste des details mouvement
            List<DetailMouvement> listDm = new ArrayList<>();
            // DecimalFormat df = new DecimalFormat("000000000");
            TypeMouvementStock tms = mouvStockTypeDAO.getTypeMouvementStock("C-");
            TypeMouvementStock tms2 = mouvStockTypeDAO.getTypeMouvementStock("C+");

            // creation liste des ventes
            //List<VenteCaisse> listVente = new ArrayList<>();
            // creation liste des gencodes inconnu
            HashMap<String, String> listCodeInconnu = new HashMap<>();

            // double priceTotalRemb = 0;
            DecimalFormat dfG = new DecimalFormat("000000000000");
            // List<LigneMouvementSA> listMouv = getMouvFromSA(date, mag);

            // pour chaque ligne de mouvement
            int count=0;
            Article inconnu = articleService.getArticle(new ArticleMagRefPK(mag, "INCONNU"));
            System.out.println("MAG : "+mag);
            System.out.println("ARTICLE INCONNU :  "+inconnu.toString());
            for (LigneMouvementSA lm : listMouv) {

                count++;
                System.out.println("Mouv count : "+count);
                if ((lm == null) || (String.valueOf(lm.getGencode()).length() > 12)) {
                    System.err.println("Attention taille code barre sup \u00E0 12char : " + lm);
                    continue;
                }
               // System.out.println("@@@@@@@@@@@@@@@@@@@@@    " + lm.toString());
                plu = false;
                GenCode gc = null;
                Article a = null;
                String generique = "";
                if ((lm.getGencode() == 0) || (lm.getMontant() == 0)) {
                    continue;
                }
                try {
                    if (lm.getQte() == 0) {
                        throw new ArithmeticException("Quantit\u00E9 d'article en mouvement null");
                    }
                } catch (ArithmeticException e) {
                    lm.setQte(1);
                    System.err.println("Exception division par z\u00E9ro catcher !");
                }
                gc = genCodeDAO.getGenCodeByCodeAndPVT(dfG.format(lm.getGencode()), mag);
                if (gc == null) {
                    System.err.println(lm);
                    if (String.valueOf(lm.getGencode()).startsWith("999")) {
                        generique = "GENERIQUE";
                    }
                    a = inconnu;
                    listCodeInconnu.put(String.valueOf(lm.getGencode()), String.valueOf(lm.getGencode()));
                    DetailMouvement dm = new DetailMouvement();
                    
                    dm.setArticle(a);
                    dm.setGcCode(dfG.format(lm.getGencode()));
                    dm.setRayon(a.getRayon());
                    dm.setSecteur(a.getSecteur());
                    dm.setFamille(a.getFamille());
                    dm.setSousFamille(a.getSousFamille());
                    dm.setDepot(depot);
                    dm.setDesignationArt(a.getDesignation());
                    // Retour article
                    if (lm.getMontant() < 0) {
                        dm.setObservations("RETOUR ARTICLE " + generique + " : " + a.getCodeArticle() + " QTE : "
                                + lm.getQte() + " PV : " + (lm.getMontant() / Math.abs(lm.getQte())));
                        dm.setTypeMouvement(tms2);
                        dm.setSens(Integer.parseInt(tms2.getSens()));
                    } else {
                        dm.setObservations("VENTE ARTICLE " + generique + " : "
                                + a.getCodeArticle() + " QTE : " + lm.getQte()
                                + " PV : " + (lm.getMontant() / lm.getQte()));
                        dm.setTypeMouvement(tms);
                        dm.setSens(Integer.parseInt(tms.getSens()));
                    }
                    dm.setQteMvt(lm.getQte());
                    dm.setDateMouvement(em.getDateMouvement());
                    if (null != a.getPromo()) {
                        Promo p = promoDAO.getPromo(a.getPromo());
                        if (p == null) {
                            System.err.println("La promo auquel appartient l'article n'existe pas dans la bd");
                        } else {
                            dm.setThemePromo(a.getPromo());
                            if (p.isEnVente()) {
                                dm.setArtPrixReviens(a.getPrixReviensPromoTTC());
                                dm.setArtPrixReviensHT((long) articleService.calculateHTForPrice(a.getTauxTVA(), dm.getArtPrixReviens()));
                            } else {
                                dm.setArtPrixReviens(a.getPrixReviensTTCEnCours());
                                dm.setArtPrixReviensHT((long) articleService.calculateHTForPrice(a.getTauxTVA(), dm.getArtPrixReviens()));
                            }
                        }
                    } else {
                        dm.setArtPrixReviens(a.getPrixReviensTTCEnCours());
                        dm.setArtPrixReviensHT((long) articleService.calculateHTForPrice(a.getTauxTVA(), dm.getArtPrixReviens()));
                    }
                    dm.setMontantTotal((lm.getMontant()));
                    dm.setArtPrixVente((long) (lm.getMontant() / Math.abs(lm.getQte())));
                    dm.setArtPrixVenteHT((long) articleService.calculateHTForPrice(a.getTauxTVA(), dm.getArtPrixVente()));
                    try {
                        dm.setQtePhysiqueAvantMouvement(a.getStock().getQteComptable());
                    } catch (NullPointerException e) {
                        System.err.println("Stock null  pour l'article :" + a.getCodeArticle());
                    }
                    dm.setThemePromo(a.getPromo());
                    dm.setEnteteMouvement(em);
                    listDm.add(dm);
                } else {
                    if (gc.getCatGen().equals(CategorieGenCode.GENERIQUE)) {
                        generique = "GENERIQUE";
                    }
                    a = articleService.getArticleWithStock(new ArticleMagRefPK(gc.getArticle().getPvtCode(), gc.getArticle().getCodeArticle()));
                    if (null == a) {
                        a =inconnu;
                        listCodeInconnu.put(String.valueOf(lm.getGencode()), String.valueOf(lm.getGencode()));
                    }
                    if ((gc.getCatGen().equals(CategorieGenCode.SCOND) || gc.getCatGen().equals(CategorieGenCode.RCOND)
                            || gc.getCatGen().equals(CategorieGenCode.GROS))) {
                        plu = true;
                        listMouvementForGC.add(createMouvementForGcMinus(a, gc, lm.getQte(), a.getStock().getDepot(), date));
                        listMouvementForGC.add(createMouvementForGcPlus(a, gc, lm.getQte(), a.getStock().getDepot(), date));
                    }
                    DetailMouvement dm = new DetailMouvement();
                    if (plu) {
                        dm.setGcCode(gc.getCode());
                        dm.setQteMvtUc(gc.getColisage() * lm.getQte());
                    }
                    dm.setArticle(a);
                    dm.setSecteur(a.getSecteur());
                    dm.setGcCode(dfG.format(lm.getGencode()));
                    dm.setRayon(a.getRayon());
                    dm.setFamille(a.getFamille());
                    dm.setSousFamille(a.getSousFamille());
                    dm.setDesignationArt(a.getDesignation());
                    dm.setDepot(depot);
                    if (lm.getMontant() < 0) {
                        dm.setObservations("RETOUR ARTICLE " + generique
                                + " : " + a.getCodeArticle() + " QTE : "
                                + lm.getQte() + " PV : "
                                + (lm.getMontant() / Math.abs(lm.getQte())));
                        dm.setTypeMouvement(tms2);
                        dm.setSens(Integer.parseInt(tms2.getSens()));
                    } else {
                        dm.setObservations("VENTE ARTICLE " + generique + " : "
                                + a.getCodeArticle() + " QTE : " + lm.getQte()
                                + " PV : " + (lm.getMontant() / lm.getQte()));
                        dm.setTypeMouvement(tms);
                        dm.setSens(Integer.parseInt(tms.getSens()));
                    }
                    dm.setQteMvt(lm.getQte());
                    dm.setDateMouvement(em.getDateMouvement());
                    if (null != a.getPromo()) {
                        Promo p = promoDAO.getPromo(a.getPromo());
                        if (p.isEnFacturation()) {
                            dm.setArtPrixReviens(a.getPrixReviensPromoTTC());
                            dm.setArtPrixReviensHT((long) articleService.calculateHTForPrice(a.getTauxTVA(), dm.getArtPrixReviens()));
                        } else {
                            dm.setArtPrixReviens(a.getPrixReviensTTCEnCours());
                            dm.setArtPrixReviensHT((long) articleService.calculateHTForPrice(a.getTauxTVA(), dm.getArtPrixReviens()));
                        }
                    } else {
                        dm.setArtPrixReviens(a.getPrixReviensTTCEnCours());
                        dm.setArtPrixReviensHT((long) articleService.calculateHTForPrice(a.getTauxTVA(), dm.getArtPrixReviens()));
                    }
                    dm.setMontantTotal((lm.getMontant()));
                    dm.setArtPrixVente((long) (lm.getMontant() / Math.abs(lm.getQte())));
                    dm.setArtPrixVenteHT((long) articleService.calculateHTForPrice(a.getTauxTVA(), dm.getArtPrixVente()));
                    try {
                        dm.setQtePhysiqueAvantMouvement(a.getStock().getQteComptable());
                    } catch (NullPointerException e) {
                        System.err.println("Stock null  pour l'article :" + a.getCodeArticle());
                    }
                    dm.setThemePromo(a.getPromo());
                    dm.setEnteteMouvement(em);
                    listDm.add(dm);
                }
            }
            em.setMouvements(listDm);
            tx.begin();
            listMouvementForGC.stream().forEach((emgc) -> {
                enteteMouvementDAO.createOrUpdateEnteteMouvement(emgc);
            });
            enteteMouvementDAO.createOrUpdateEnteteMouvement(em);
            int indexCodeInconnu = 0;
            System.out.println("listVente  size   : " + listCodeInconnu.size());

            Iterator it = listCodeInconnu.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                String inc = (String) pair.getValue();
                mysqlConnection.addInconnuGencode(formatLong(inc, 12), mag);
            }
            System.out.println("FIN Insertion VenteCaisse nbre de ligne : " + indexCodeInconnu);
            tx.commit();
            return "SUCCESS";
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            return "FAILURE";
        }
    }

    // @SuppressWarnings("unused")
//    @Override
//    public String downoadFromTPV(String mag, String ip, String date) throws GPV3000RemonteeException, Exception {
//        System.out.println("DEBUT downoadFromTPV ");
//        try {
//            boolean plu = false;
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//            String queryTemp = "SELECT c FROM EnteteMouvement c WHERE c.pvt.pvtCode='"
//                    + mag
//                    + "' AND c.dateMouvement = '"
//                    + date
//                    + "' AND c.origineMouvement = 'CAISS'";
//            List<EnteteMouvement> listEM = enteteMouvementDAO.executeQuery(queryTemp);
//            // si mouvement exist alors on arret tout
//            if (listEM != null && listEM.size() > 0) {
//                return "EXIST";
//            }
//            PointDeVente pvt = pointDeVenteDAO.getPVT(mag);
//            Depot depot = pointDeVenteDAO.getDepotPrincipalForMag(pvt.getPvtCode());
//            // creation et mis à jour de l'entete mouvement
//            EnteteMouvement em = new EnteteMouvement();
//            em.setDateMouvement(sdf.parse(date));
//            em.setObservations("REMONTEE DE CAISSE DU " + date);
//            em.setOrigineMouvement(origineMvmtnDAO.getOrigineMouvement("CAISS"));
//            em.setPvt(pvt);
//            em.setUserCreation("GPV3000");
//            List<EnteteMouvement> listMouvementForGC = new ArrayList<>();
//            // creation liste des details mouvement
//            List<DetailMouvement> listDm = new ArrayList<>();
//            // DecimalFormat df = new DecimalFormat("000000000");
//            TypeMouvementStock tms = mouvStockTypeDAO.getTypeMouvementStock("C-");
//            TypeMouvementStock tms2 = mouvStockTypeDAO.getTypeMouvementStock("C+");
//
//            // creation liste des ventes
//            List<VenteCaisse> listVente = new ArrayList<>();
//            // creation liste des gencodes inconnu
//            List<String> listCodeInconnu = new ArrayList<>();
//
//            double priceTotal = 0;
//            // double priceTotalRemb = 0;
//            DecimalFormat dfG = new DecimalFormat("000000000000");
//            // List<LigneMouvementSA> listMouv = getMouvFromSA(date, mag);
//
//            List<LigneMouvementSA> listMouv = getMouvSAFromLOGASCI(date.substring(2, 8), mag);
//
//            // pour chaque ligne de mouvement
//            for (LigneMouvementSA lm : listMouv) {
//
//                if ((lm == null) || (String.valueOf(lm.getGencode()).length() > 12)) {
//                    System.err.println("Attention taille code barre sup \u00E0 12char : " + lm);
//                    continue;
//                }
//                System.out.println("@@@@@@@@@@@@@@@@@@@@@    " + lm.toString());
//                plu = false;
//                GenCode gc = null;
//                Article a = null;
//                String generique = "";
//                if ((lm.getGencode() == 0) || (lm.getMontant() == 0)) {
//                    continue;
//                }
//                try {
//                    if (lm.getQte() == 0) {
//                        throw new ArithmeticException("Quantit\u00E9 d'article en mouvement null");
//                    }
//                } catch (ArithmeticException e) {
//                    lm.setQte(1);
//                    System.err.println("Exception division par z\u00E9ro catcher !");
//                }
//                gc = genCodeDAO.getGenCodeByCodeAndPVT(dfG.format(lm.getGencode()), mag);
//                if (gc == null) {
//                    System.err.println(lm);
//                    if (String.valueOf(lm.getGencode()).startsWith("999")) {
//                        generique = "GENERIQUE";
//                    }
//                    a = new Article();
//                    a.setCodeArticle("INCONNU");
//                    a.setPvtCode(mag);
//                    a.setRayon("XXXXX");
//                    a.setSecteur("XX");
//                    a.setStock(null);
//                    priceTotal = priceTotal + lm.getMontant();
//                    VenteCaisse vc = new VenteCaisse();
//                    vc.setCodeArticle("INCONNU");
//                    vc.setCodeMagasin(pvt.getPvtCode());
//                    vc.setDateVente(em.getDateMouvement());
//                    vc.setNbrArticleVendu(lm.getQte());
//                    vc.setMontantArticleVendu((long) (lm.getMontant() / Math.abs(lm.getQte())));
//                    vc.setRetour(false);
//                    vc.setMontantTotal(lm.getMontant());
//                    vc.setSecteur("XX");
//                    vc.setRayon("XXXXX");
//                    listVente.add(vc);
//                    listCodeInconnu.add(String.valueOf(lm.getGencode()));
//
//                    /* Traitement de gencode Gros */
// /*
//					 * if
//					 * (String.valueOf(lm.getGencode()).startsWith("200400000"))
//					 * { plu = true;
//					 * listMouvementForGC.add(createMouvementForGrosMinus(a, lm,
//					 * date));
//					 * listMouvementForGC.add(createMouvementForGrosPlus(a, lm,
//					 * date)); }
//                     */
//                    DetailMouvement dm = new DetailMouvement();
//                    dm.setArticle(a);
//                    dm.setDepot(depot);
//                    // Retour article
//                    if (lm.getMontant() < 0) {
//                        dm.setObservations("RETOUR ARTICLE " + generique + " : " + a.getCodeArticle() + " QTE : "
//                                + lm.getQte() + " PV : " + (lm.getMontant() / Math.abs(lm.getQte())));
//                        dm.setTypeMouvement(tms2);
//                        dm.setSens(Integer.parseInt(tms2.getSens()));
//                    } else {
//                        dm.setObservations("VENTE ARTICLE " + generique + " : "
//                                + a.getCodeArticle() + " QTE : " + lm.getQte()
//                                + " PV : " + (lm.getMontant() / lm.getQte()));
//                        dm.setTypeMouvement(tms);
//                        dm.setSens(Integer.parseInt(tms.getSens()));
//                    }
//                    dm.setQteMvt(lm.getQte());
//                    dm.setDateMouvement(em.getDateMouvement());
//                    if (null != a.getPromo()) {
//                        Promo p = promoDAO.getPromo(a.getPromo());
//                        if (p == null) {
//                            System.err.println("La promo auquel appartient l'article n'existe pas dans la bd");
//                        }
//                        if (p != null && p.isEnFacturation()) {
//                            dm.setArtPrixReviens(a.getPrixReviensPromoTTC());
//                            dm.setArtPrixReviensHT((long) articleService.calculateHTForPrice(a.getTauxTVA(), dm.getArtPrixReviens()));
//                        } else {
//                            dm.setArtPrixReviens(a.getPrixReviensTTCEnCours());
//                            dm.setArtPrixReviensHT((long) articleService.calculateHTForPrice(a.getTauxTVA(), dm.getArtPrixReviens()));
//                        }
//                    } else {
//                        dm.setArtPrixReviens(a.getPrixReviensTTCEnCours());
//                        dm.setArtPrixReviensHT((long) articleService.calculateHTForPrice(a.getTauxTVA(), dm.getArtPrixReviens()));
//                    }
//                    dm.setArtPrixVente((long) (lm.getMontant() / Math.abs(lm.getQte())));
//                    dm.setArtPrixVenteHT((long) articleService.calculateHTForPrice(a.getTauxTVA(), dm.getArtPrixVente()));
//                    try {
//                        dm.setQtePhysiqueAvantMouvement(a.getStock().getQteComptable());
//                    } catch (NullPointerException e) {
//                        System.err.println("Stock null  pour l'article :" + a.getCodeArticle());
//                    }
//                    dm.setThemePromo(a.getPromo());
//                    listDm.add(dm);
//                } else {
//                    if (gc.getCatGen().equals(CategorieGenCode.GENERIQUE)) {
//                        generique = "GENERIQUE";
//                    }
//                    a = gc.getArticle();
//                    if (null == a) {
//                        a = new Article();
//                        a.setCodeArticle("INCONNU");
//                        a.setPvtCode(mag);
//                        a.setRayon("XXXXX");
//                        a.setSecteur("XX");
//                        a.setStock(null);
//                        priceTotal = priceTotal + lm.getMontant();
//                        VenteCaisse vc = new VenteCaisse();
//                        vc.setCodeArticle("INCONNU");
//                        vc.setCodeMagasin(pvt.getPvtCode());
//                        vc.setDateVente(em.getDateMouvement());
//                        vc.setNbrArticleVendu(lm.getQte());
//                        vc.setMontantArticleVendu((long) (lm.getMontant() / Math.abs(lm.getQte())));
//                        vc.setRetour(false);
//                        vc.setMontantTotal(lm.getMontant());
//                        vc.setSecteur("XX");
//                        vc.setRayon("XXXXX");
//                        listVente.add(vc);
//                        listCodeInconnu.add(String.valueOf(lm.getGencode()));
//                    } else {
//                        priceTotal = priceTotal + lm.getMontant();
//                        VenteCaisse vc = new VenteCaisse();
//                        vc.setCodeArticle(a.getCodeArticle() + "");
//                        vc.setCodeMagasin(pvt.getPvtCode());
//                        vc.setDateVente(em.getDateMouvement());
//                        vc.setNbrArticleVendu(lm.getQte());
//                        if (a.getCodeLot() == 1 && (a.getPromo() != null)) {
//                            vc.setMontantArticleVendu(a.getPrixVentePromoTTC());
//                        } else {
//                            vc.setMontantArticleVendu(a.getPrixVenteTTCEnCours());
//                        }
//
//                        vc.setRetour(false);
//                        vc.setMontantTotal(lm.getMontant());
//                        vc.setSecteur(a.getSecteur());
//                        vc.setRayon(a.getRayon());
////						vc.setThemePromo(a.getPromo());
////						vc.setPrixDuLot(a.getPrixDuLot());
////						vc.setNombreArticleLot(a.getQuantiteLot());
//                        listVente.add(vc);
//                        listCodeInconnu.add(String.valueOf(lm.getGencode()));
//                    }
//                    if ((gc.getCatGen().equals(CategorieGenCode.SCOND)
//                            || gc.getCatGen().equals(CategorieGenCode.RCOND) || gc
//                            .getCatGen().equals(CategorieGenCode.GROS))) {
//                        plu = true;
//                        listMouvementForGC.add(createMouvementForGcMinus(a, gc, lm.getQte(), a.getStock().getDepot(), date));
//                        listMouvementForGC.add(createMouvementForGcPlus(a, gc, lm.getQte(), a.getStock().getDepot(), date));
//                    }
//                    // }
//                    DetailMouvement dm = new DetailMouvement();
//                    if (plu) {
//                        dm.setGcCode(gc.getCode());
//                        dm.setQteMvtUc(gc.getColisage() * lm.getQte());
//                    }
//                    dm.setArticle(a);
//                    dm.setSecteur(a.getSecteur());
//                    dm.setRayon(a.getRayon());
//                    dm.setFamille(a.getFamille());
//                    dm.setSousFamille(a.getSousFamille());
//                    dm.setDepot(depot);
//                    if (lm.getMontant() < 0) {
//                        dm.setObservations("RETOUR ARTICLE " + generique
//                                + " : " + a.getCodeArticle() + " QTE : "
//                                + lm.getQte() + " PV : "
//                                + (lm.getMontant() / Math.abs(lm.getQte())));
//                        dm.setTypeMouvement(tms2);
//                        dm.setSens(Integer.parseInt(tms2.getSens()));
//                    } else {
//                        dm.setObservations("VENTE ARTICLE " + generique + " : "
//                                + a.getCodeArticle() + " QTE : " + lm.getQte()
//                                + " PV : " + (lm.getMontant() / lm.getQte()));
//                        dm.setTypeMouvement(tms);
//                        dm.setSens(Integer.parseInt(tms.getSens()));
//                    }
//                    dm.setQteMvt(lm.getQte());
//                    dm.setDateMouvement(em.getDateMouvement());
//                    if (null != a.getPromo()) {
//                        Promo p = promoDAO.getPromo(a.getPromo());
//                        if (p.isEnFacturation()) {
//                            dm.setArtPrixReviens(a.getPrixReviensPromoTTC());
//                            dm.setArtPrixReviensHT((long) articleService.calculateHTForPrice(a.getTauxTVA(),dm.getArtPrixReviens()));
//                        } else {
//                            dm.setArtPrixReviens(a.getPrixReviensTTCEnCours());
//                            dm.setArtPrixReviensHT((long) articleService.calculateHTForPrice(a.getTauxTVA(),dm.getArtPrixReviens()));
//                        }
//                    } else {
//                        dm.setArtPrixReviens(a.getPrixReviensTTCEnCours());
//                        dm.setArtPrixReviensHT((long) articleService .calculateHTForPrice(a.getTauxTVA(),dm.getArtPrixReviens()));
//                    }
//                    dm.setArtPrixVente((long) (lm.getMontant() / Math.abs(lm.getQte())));
//                    dm.setArtPrixVenteHT((long) articleService.calculateHTForPrice(a.getTauxTVA(),dm.getArtPrixVente()));
//                    try {
//                        dm.setQtePhysiqueAvantMouvement(a.getStock().getQteComptable());
//                    } catch (NullPointerException e) {
//                        System.err.println("Stock null  pour l'article :" + a.getCodeArticle());
//                    }
//                    dm.setThemePromo(a.getPromo());
//                    listDm.add(dm);
//                }
//            }
//            em.setMouvements(listDm);
//
//            for (EnteteMouvement emgc : listMouvementForGC) {
//                System.out.println("LISTE MOUVEMENT FOR GC : " + emgc.myToString());
//            }
//
//            tx.begin();
//            for (EnteteMouvement emgc : listMouvementForGC) {
//                enteteMouvementDAO.createOrUpdateEnteteMouvement(emgc);
//            }
//            enteteMouvementDAO.createOrUpdateEnteteMouvement(em);
//            int indexCodeInconnu = 0;
//            System.out.println("Debut Insertion  VenteCaisse");
//            // for (VenteCaisse vc : listVente) {
//            // VenteCaisse vci = venteCaisseDAO.createVenteCaisse(vc);
//            // if (vc.getCodeArticle().equals("INCONNU")) {
//            // mysqlConnection.addInconnuGencode(formatLong(listCodeInconnu.get(indexCodeInconnu),12),
//            // vci.getId(), mag);
//            // }
//            // indexCodeInconnu++;
//            // }
//            System.out.println("listVente  size   : " + listVente.size());
//            for (VenteCaisse vc : listVente) {
//                if (vc.getCodeArticle().equals("INCONNU")) {
//                    VenteCaisse vci = venteCaisseDAO.createVenteCaisse(vc);
//                    mysqlConnection.addInconnuGencode(formatLong(listCodeInconnu.get(indexCodeInconnu),12), vci.getId(), mag);
//                    System.out.println("INCONNU : " + vci.getCodeArticle());
//                } else {
//                    venteCaisseDAO.saveVenteCaisse(vc);
//                }
//                indexCodeInconnu++;
//                System.out.println("VenteCaisse : " + indexCodeInconnu);
//            }
//            System.out.println("FIN Insertion VenteCaisse nbre de ligne : "  + indexCodeInconnu);
//            tx.commit();
//            return "SUCCESS";
//        } catch (Exception e) {
//            e.printStackTrace();
//            tx.rollback();
//            return "FAILURE";
//        }
//    }
    public EnteteMouvement createMouvementForGcMinus(Article a, GenCode gc,
            float qte, Depot d, String date) {
        EnteteMouvement em = new EnteteMouvement();
        em.setDateMouvement(new Date(System.currentTimeMillis()));
        em.setPvt(pointDeVenteDAO.getPVT(a.getPvtCode()));
        em.setUserCreation("GPV3000");
        DetailMouvement dm = new DetailMouvement();
        dm.setArticle(a);
        dm.setArtPrixReviens(a.getPrixReviensTTCEnCours());
        dm.setArtPrixReviensHT((long) articleService.calculateHTForPrice(a.getTauxTVA(), a.getPrixReviensTTCEnCours()));
        dm.setArtPrixVente(a.getPrixVenteTTCEnCours());
        dm.setArtPrixVenteHT((long) articleService.calculateHTForPrice(a.getTauxTVA(), a.getPrixVenteTTCEnCours()));
        dm.setCodeAnal(a.getCodeAnalytique());
        dm.setDateMouvement(em.getDateMouvement());
        dm.setDepot(d);
        dm.setDesignationArt(a.getDesignation());
        dm.setFamille(a.getFamille());
        dm.setQteMvt(gc.getColisage() * qte);
        dm.setQtePhysiqueAvantMouvement(a.getStock().getQteComptable());
        dm.setRayon(a.getRayon());
        dm.setSecteur(a.getSecteur());
        dm.setSens(-1);
        dm.setSousFamille(a.getSousFamille());

        if (gc.getCatGen().equals(CategorieGenCode.RCOND)) {
            // MOUVEMENT DEBITER ARTICLE PRINCIPAL
            em.setObservations("MOUVEMENT AJUSTEMENT  RCOND -  REMOTE DE CAISSE DU " + date);
            em.setOrigineMouvement(origineMvmtnDAO.getOrigineMouvement("RCOND"));
            dm.setObservations("MOUVEMENT RCOND- : " + a.getCodeArticle() + " Qte " + dm.getQteMvt());
            dm.setTypeMouvement(mouvStockTypeDAO.getTypeMouvementStock("R-"));
        }
        if (gc.getCatGen().equals(CategorieGenCode.SCOND)) {
            // MOUVEMENT DEBITER ARTICLE PRINCIPAL
            em.setObservations("MOUVEMENT AJUSTEMENT  SCOND -  REMOTE DE CAISSE DU " + date);
            em.setOrigineMouvement(origineMvmtnDAO.getOrigineMouvement("SCOND"));
            dm.setObservations("MOUVEMENT SCOND- : " + a.getCodeArticle() + " Qte " + dm.getQteMvt());
            dm.setTypeMouvement(mouvStockTypeDAO.getTypeMouvementStock("S-"));
        }
        if (gc.getCatGen().equals(CategorieGenCode.GROS)) {
            // MOUVEMENT DEBITER ARTICLE PRINCIPAL
            em.setObservations("MOUVEMENT AJUSTEMENT  GROS -  REMOTE DE CAISSE DU " + date);
            em.setOrigineMouvement(origineMvmtnDAO.getOrigineMouvement("GROS"));
            dm.setObservations("MOUVEMENT GROS- : " + a.getCodeArticle() + " Qte " + dm.getQteMvt());
            dm.setTypeMouvement(mouvStockTypeDAO.getTypeMouvementStock("G-"));
            //System.out.println("LIGNE MINUS GROS " + a.myToString());
        }
        List<DetailMouvement> listDm = new ArrayList<>();
        dm.setEnteteMouvement(em);
        listDm.add(dm);
        em.setMouvements(listDm);
        return em;
    }

    public EnteteMouvement createMouvementForGcPlus(Article a, GenCode gc, float qte, Depot d, String date) {
        EnteteMouvement em = new EnteteMouvement();
        em.setDateMouvement(new Date(System.currentTimeMillis()));
        em.setPvt(pointDeVenteDAO.getPVT(a.getPvtCode()));
        em.setUserCreation("GPV3000");
        DetailMouvement dm = new DetailMouvement();
        dm.setArticle(a);
        dm.setGcCode(gc.getCode());
        dm.setArtPrixReviens(gc.getPrixReviensTTCEnCours());
        dm.setArtPrixReviensHT((long) articleService.calculateHTForPrice(a.getTauxTVA(), gc.getPrixReviensTTCEnCours()));
        dm.setArtPrixVente(gc.getPrixVenteTTCEnCours());
        dm.setArtPrixVenteHT((long) articleService.calculateHTForPrice(a.getTauxTVA(), gc.getPrixVenteTTCEnCours()));
        dm.setCodeAnal(a.getCodeAnalytique());
        dm.setDateMouvement(em.getDateMouvement());
        dm.setDepot(d);
        dm.setDesignationArt(a.getDesignation());
        dm.setFamille(a.getFamille());
        dm.setQteMvt(qte);
        dm.setQtePhysiqueAvantMouvement(a.getStock().getQteComptable());
        dm.setRayon(a.getRayon());
        dm.setSecteur(a.getSecteur());
        dm.setSens(-1);
        dm.setSousFamille(a.getSousFamille());

        if (gc.getCatGen().equals(CategorieGenCode.RCOND)) {
            // MOUVEMENT DEBITER ARTICLE PRINCIPAL
            em.setObservations("MOUVEMENT AJUSTEMENT  RCOND -  REMOTE DE CAISSE DU " + date);
            em.setOrigineMouvement(origineMvmtnDAO.getOrigineMouvement("RCOND"));
            dm.setObservations("MOUVEMENT RCOND+ : " + a.getCodeArticle() + " Qte " + dm.getQteMvt());
            dm.setTypeMouvement(mouvStockTypeDAO.getTypeMouvementStock("R+"));
        }
        if (gc.getCatGen().equals(CategorieGenCode.SCOND)) {
            // MOUVEMENT DEBITER ARTICLE PRINCIPAL
            em.setObservations("MOUVEMENT AJUSTEMENT  SCOND -  REMOTE DE CAISSE DU " + date);
            em.setOrigineMouvement(origineMvmtnDAO.getOrigineMouvement("SCOND"));
            dm.setObservations("MOUVEMENT SCOND+ : " + a.getCodeArticle() + " Qte " + dm.getQteMvt());
            dm.setTypeMouvement(mouvStockTypeDAO.getTypeMouvementStock("S+"));
        }
        if (gc.getCatGen().equals(CategorieGenCode.GROS)) {
            // MOUVEMENT DEBITER ARTICLE PRINCIPAL
            em.setObservations("MOUVEMENT AJUSTEMENT  GROS -  REMOTE DE CAISSE DU " + date);
            em.setOrigineMouvement(origineMvmtnDAO.getOrigineMouvement("GROS"));
            dm.setObservations("MOUVEMENT GROS+ : " + a.getCodeArticle() + " Qte " + dm.getQteMvt());
            dm.setTypeMouvement(mouvStockTypeDAO.getTypeMouvementStock("G+"));
            //System.out.println("LIGNE PLUS GROS " + a.myToString());
        }
        List<DetailMouvement> listDm = new ArrayList<>();
        dm.setEnteteMouvement(em);
        listDm.add(dm);
        em.setMouvements(listDm);
        return em;

    }

    private static String formatLong(String l, int i) {
        String s1 = l;
        if (i > s1.length()) {
            for (int j = 0; j < i - l.length(); j++) {
                s1 = "0" + s1;
            }
        }
        return s1;
    }

    /*
	 * (non-Javadoc) Nouvelle methode pour les descentes globale
	 * 
	 * @see
	 * ci.prosuma.prosumagpv.metier.service.ISAService#generateAllArticle(java
	 * .lang.String)
     */
//	@Override
//	public void generateAllArticle(String pvtCode) {
//		try {
//
//			DecimalFormat codeEANFormat = new DecimalFormat("000000000000");
//			DecimalFormat formatPrixUnitaire = new DecimalFormat("0000000");
//			DecimalFormat formatPrixDuLot = new DecimalFormat("00000");
//			DecimalFormat formatNbreArticleLot = new DecimalFormat("00");
//			DecimalFormat formatFamille = new DecimalFormat("000");
//			String lastFileLine = "4Cÿÿÿÿÿÿÿÿÿÿÿÿ"+ "                                                                                                                                                                                                                                                 ";
//			String articleLie = "0000";
//			String premiereSerieZero = "00000000";
//			String derniereSerieZero = "0000000000";	
//			String begin = "4M";
//			int value1 = 0;
//			char nul = (char) value1;
//			char EURO = '\u20ac';
//			System.out.println("EURO : " + EURO);
//			List<String> listFinal = new ArrayList<String>();
//			String line = "";
//			int count = 0;
//			String lastRecord = "";
//			// recupération des articles à descendre
//			List<Article> listArticles = articleDAO.getAllArticleForMag(pvtCode);
//			if (listArticles != null && listArticles.size() > 0) {
//				for (int i = 0; i < listArticles.size(); i++) {
//					Article a = listArticles.get(i);
//					if (listGenerique == null)
//						initVar();
//					if (a != null) {
//						if(a.getPrixVenteTTCEnCours()>1){
//							List<GenCode> genCodeList = genCodeDAO.getAllGenCodeForArticle(a.getCodeArticle(),a.getPvtCode());
//							if (genCodeList != null && genCodeList.size() > 0) {
//								for (GenCode gc : genCodeList) {
//									float prixVente = 0;
//									float prixDuLot =0;
//									Integer quantiteDansLot=0; 
//									Integer codeLot =0;
//									if (null != a.getPromo()) {
//										Promo p = promoDAO.getPromo(a.getPromo());
//										if(p!=null){
//											if (p.isEnVente()) {
//												if (a.getTypePromo() == Article.PROMO_ORDINAIRE) {
//													prixVente = a.getPrixVentePromoTTC();
//													codeLot=0;
//												} else if(a.getTypePromo() == Article.PROMO_LOT_HOMOGENE){
//													prixVente = a.getPrixVenteTTCEnCours();
//													prixDuLot = a.getPrixDuLot();
//													quantiteDansLot = a.getQuantiteLot();
//													codeLot=2;
//												}else{
//													prixVente = a.getPrixVenteTTCEnCours();
//													prixDuLot = 0;
//													quantiteDansLot = 0;
//													codeLot=0;
//												}
//											} else {
//												prixVente = a.getPrixVenteTTCEnCours();
//											}
//										}
//									} else {
//										prixVente = a.getPrixVenteTTCEnCours();
//									}
//									if (gc.getCatGen().equals(CategorieGenCode.GROS) 
//											|| gc.getCatGen().equals(CategorieGenCode.RCOND)
//											|| gc.getCatGen().equals(CategorieGenCode.SCOND)
//											|| gc.getCatGen().equals(CategorieGenCode.DLVM)) {
//										prixVente = gc.getPrixVenteTTCEnCours();
//									}
//									if (prixVente <= 0 || prixVente > 9999999 || gc.getCode().equals("000000000000"))
//										continue;
//									char tva;
//									int asciiValue;
//									switch (a.getTauxTVA()) {
//									case "03":
//										asciiValue = 16;
//										tva = (char) asciiValue;
//										break;
//									case "04":
//										asciiValue = 16;
//										tva = (char) asciiValue;
//										break;
//									case "09":
//										asciiValue = 0;
//										tva = (char) asciiValue;
//										break;
//									default:
//										asciiValue = 16;
//										tva = (char) asciiValue;
//										break;
//									}
//									char promoLot;
//									if (codeLot > 0) {
//										int asciiLot = 02;
//										promoLot = (char) asciiLot;
//									} else {
//										int asciiLot = 0;
//										promoLot = (char) asciiLot;
//									}
//									String designation = a.getLibelReduit().concat("                    ").substring(0, 18)
//											.replaceAll("[^A-Za-z0-9 .%/*+-]", " ");
//									String lineArticle = codeEANFormat.format(Long.parseLong(gc.getCode()))
//											+ nul
//											+ tva
//											+ EURO
//											+ promoLot
//											+ formatFamille.format(Long.parseLong(a.getRayon().substring(2)))
//											+ premiereSerieZero
//											+ formatNbreArticleLot.format(quantiteDansLot)
//											+ formatPrixUnitaire.format(prixVente)
//											+ formatPrixDuLot.format(prixDuLot)
//											+ articleLie
//											+ designation + derniereSerieZero;
//									lastRecord = lineArticle;
//									count++;
//									System.out
//											.println("###### #  Count Value in Gencode loop  : "+ count);
//									if (count == 3) {
//										line = line+ "C"+ lineArticle+ "                                ";
//										listFinal.add(line);
//										listFinal.add("\n");
//										line = "";
//										count = 0;
//									} else {
//										if (line.equals("")) {
//											line = begin + lineArticle;
//										} else {
//											line = line + "C" + lineArticle;
//										}
//									}
//								}
//								System.out.println("###### #  Count Value   : "
//										+ count);
//								if ((i + 1) == listArticles.size()) {
//									System.out.println(" @@@@@@@   Count Value Final  : "+ count);
//									switch (count) {
//									case 1:
//										line = line
//												+ "C"
//												+ lastRecord
//												+ "C"
//												+ lastRecord
//												+ "                                ";
//										listFinal.add(line);
//										listFinal.add("\n");
//										break;
//									case 2:
//										line = line
//												+ "C"
//												+ lastRecord
//												+ "                                ";
//										listFinal.add(line);
//										listFinal.add("\n");
//										break;
//									default:
//										break;
//									}
//									// listFinal.add(lastFileLine);
//								} else {
//
//								}
//							}
//						}
//					}
//
//				}
//				System.out.println("D\351but creation fichier  \n");
//				File fileDir = new File("tomajcai.fic." + pvtCode);
//				System.out.println(" listFinal  size = " + listFinal.size());
//				Writer monFluxSortie = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileDir),"cp1252"));
//				// monFluxSortie.write("1o0000000000"+"                                                                                                                                                                                                                                                   ");
//				monFluxSortie.write("\n");
//				for (String s : listFinal) {
//					monFluxSortie.write(s);
//				}
//				monFluxSortie.close();
//				File archive = new File("C:\\archive_descente\\tomajcai.fic."+ pvtCode + "." + System.currentTimeMillis());
//				FileUtils.copyFile(fileDir, archive);
//				System.out.println("FIN creation fichier  \n");
//				System.out.println("fileDir.getName : " + fileDir.getName()+ "\n");
//				File f = new File("tomajcai.fic." + pvtCode);
//				copyFilesToMagRelais(f, pvtCode);
//
//			} else {
//				System.out.println("Aucun article a descendre ");
//			}
//
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//
//	}
    @Override
    public void generateAllArticle(String pvtCode) {
        try {

            DecimalFormat codeEANFormat = new DecimalFormat("000000000000");
            DecimalFormat formatPrixUnitaire = new DecimalFormat("0000000");
            DecimalFormat formatPrixDuLot = new DecimalFormat("00000");
            DecimalFormat formatNbreArticleLot = new DecimalFormat("00");
            DecimalFormat formatFamille = new DecimalFormat("000");
            String lastFileLine = "4Cÿÿÿÿÿÿÿÿÿÿÿÿ" + "                                                                                                                                                                                                                                                 ";
            String articleLie = "0000";
            String premiereSerieZero = "00000000";
            String derniereSerieZero = "0000000000";
            String begin = "4M";
            int value1 = 0;
            char nul = (char) value1;
            char EURO = '\u20ac';
            System.out.println("EURO : " + EURO);
            List<String> listFinal = new ArrayList<String>();
            String line = "";
            int count = 0;
            String lastRecord = "";
            // recupération des articles à descendre
            List<Article> listArticles = articleDAO.getAllArticleForMag(pvtCode);
            if (listArticles != null && listArticles.size() > 0) {
                for (int i = 0; i < listArticles.size(); i++) {
                    Article a = listArticles.get(i);
                    if (listGenerique == null) {
                        initVar();
                    }
                    if (a != null) {
                        if (a.getPrixVenteTTCEnCours() > 1) {
                            List<GenCode> genCodeList = genCodeDAO.getAllGenCodeForArticle(a.getCodeArticle(), a.getPvtCode());
                            if (genCodeList != null && genCodeList.size() > 0) {
                                for (GenCode gc : genCodeList) {
                                    float prixVente = 0;
                                    float prixDuLot = 0;
                                    Integer quantiteDansLot = 0;
                                    Integer codeLot = 0;
                                    if (null != a.getPromo()) {
                                        Promo p = promoDAO.getPromo(a.getPromo());
                                        if (p != null) {
                                            if (p.isEnVente()) {
                                                if (a.getTypePromo() == Article.PROMO_ORDINAIRE) {
                                                    prixVente = a.getPrixVentePromoTTC();
                                                    codeLot = 0;
                                                } else if (a.getTypePromo() == Article.PROMO_LOT_HOMOGENE) {
                                                    prixVente = a.getPrixVenteTTCEnCours();
                                                    prixDuLot = a.getPrixDuLot();
                                                    quantiteDansLot = a.getQuantiteLot();
                                                    codeLot = 2;
                                                } else {
                                                    prixVente = a.getPrixVenteTTCEnCours();
                                                    prixDuLot = 0;
                                                    quantiteDansLot = 0;
                                                    codeLot = 0;
                                                }
                                            } else {
                                                prixVente = a.getPrixVenteTTCEnCours();
                                            }
                                        }
                                    } else {
                                        prixVente = a.getPrixVenteTTCEnCours();
                                    }
                                    if (gc.getCatGen().equals(CategorieGenCode.GROS)
                                            || gc.getCatGen().equals(CategorieGenCode.RCOND)
                                            || gc.getCatGen().equals(CategorieGenCode.SCOND)
                                            || gc.getCatGen().equals(CategorieGenCode.DLVM)) {
                                        prixVente = gc.getPrixVenteTTCEnCours();
                                    }
                                    if (prixVente <= 0 || prixVente > 9999999 || gc.getCode().equals("000000000000")) {
                                        continue;
                                    }
                                    char tva;
                                    int asciiValue;
                                    switch (a.getTauxTVA()) {
                                        case "03":
                                            asciiValue = 16;
                                            tva = (char) asciiValue;
                                            break;
                                        case "04":
                                            asciiValue = 16;
                                            tva = (char) asciiValue;
                                            break;
                                        case "09":
                                            asciiValue = 0;
                                            tva = (char) asciiValue;
                                            break;
                                        default:
                                            asciiValue = 16;
                                            tva = (char) asciiValue;
                                            break;
                                    }
                                    char promoLot;
                                    if (codeLot > 0) {
                                        int asciiLot = 02;
                                        promoLot = (char) asciiLot;
                                    } else {
                                        int asciiLot = 0;
                                        promoLot = (char) asciiLot;
                                    }
                                    String designation = a.getLibelReduit().concat("                    ").substring(0, 18)
                                            .replaceAll("[^A-Za-z0-9 .%/*+-]", " ");
                                    String lineArticle = codeEANFormat.format(Long.parseLong(gc.getCode()))
                                            + nul
                                            + tva
                                            + EURO
                                            + promoLot
                                            + formatFamille.format(Long.parseLong(a.getRayon().substring(2)))
                                            + premiereSerieZero
                                            + formatNbreArticleLot.format(quantiteDansLot)
                                            + formatPrixUnitaire.format(prixVente)
                                            + formatPrixDuLot.format(prixDuLot)
                                            + articleLie
                                            + designation + derniereSerieZero;
                                    lastRecord = lineArticle;
                                    count++;
                                    System.out
                                            .println("###### #  Count Value in Gencode loop  : " + count);
                                    if (count == 3) {
                                        line = line + "C" + lineArticle + "                                ";
                                        listFinal.add(line);
                                        listFinal.add("\n");
                                        line = "";
                                        count = 0;
                                    } else if (line.equals("")) {
                                        line = begin + lineArticle;
                                    } else {
                                        line = line + "C" + lineArticle;
                                    }
                                }
                                System.out.println("###### #  Count Value   : "
                                        + count);
                                if ((i + 1) == listArticles.size()) {
                                    System.out.println(" @@@@@@@   Count Value Final  : " + count);
                                    switch (count) {
                                        case 1:
                                            line = line
                                                    + "C"
                                                    + lastRecord
                                                    + "C"
                                                    + lastRecord
                                                    + "                                ";
                                            listFinal.add(line);
                                            listFinal.add("\n");
                                            break;
                                        case 2:
                                            line = line
                                                    + "C"
                                                    + lastRecord
                                                    + "                                ";
                                            listFinal.add(line);
                                            listFinal.add("\n");
                                            break;
                                        default:
                                            break;
                                    }
                                    // listFinal.add(lastFileLine);
                                } else {

                                }
                            }
                        }
                    }

                }
                System.out.println("D\351but creation fichier  \n");
                File fileDir = new File("tomajcai.fic." + pvtCode);
                System.out.println(" listFinal  size = " + listFinal.size());
                Writer monFluxSortie = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileDir), "cp1252"));
                // monFluxSortie.write("1o0000000000"+"                                                                                                                                                                                                                                                   ");
                monFluxSortie.write("\n");
                for (String s : listFinal) {
                    monFluxSortie.write(s);
                }
                monFluxSortie.close();
                File archive = new File("C:\\archive_descente\\tomajcai.fic." + pvtCode + "." + System.currentTimeMillis());
                FileUtils.copyFile(fileDir, archive);
                System.out.println("FIN creation fichier  \n");
                System.out.println("fileDir.getName : " + fileDir.getName() + "\n");
                File f = new File("tomajcai.fic." + pvtCode);
                copyFilesToMagRelais(f, pvtCode);

            } else {
                System.out.println("Aucun article a descendre ");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private void copyFilesForDescenteAuto(File file, String pvtCode) {
        System.out.println("DEBUT DE LA COPIE DES FICHIERS");
        String path = "\\\\"
                + utilService.getSqlRequestByLibelle(pvtCode + ".ip.mp")
                + "\\GPV3000SA\\quotidienne\\MAJART";
        File dest = new File(path);
        try {
            FileUtils.copyFile(file, dest, true);
            System.out.println("Fichier  : " + file.getName() + "  Copier");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Copie des fichiers termine");
    }

    @SuppressWarnings("rawtypes")
    @Override
    public void beginToTPVUpdateAuto(String ip, String pvtCode) {
        try {
            DecimalFormat codeEANFormat = new DecimalFormat("000000000000");
            DecimalFormat formatPrixUnitaire = new DecimalFormat("0000000");
            DecimalFormat formatPrixDuLot = new DecimalFormat("00000");
            DecimalFormat formatNbreArticleLot = new DecimalFormat("00");
            DecimalFormat formatFamille = new DecimalFormat("000");
            String lastFileLine = "4Cÿÿÿÿÿÿÿÿÿÿÿÿ"
                    + "                                                                                                                                                                                                                                                 ";
            String articleLie = "0000";
            String premiereSerieZero = "00000000";
            String derniereSerieZero = "0000000000";
            String begin = "4M";
            int value1 = 0;
            char nul = (char) value1;
            char EURO = '\u20ac';
            System.out.println("EURO : " + EURO);
            List<String> listFinal = new ArrayList<String>();
            String line = "";
            int count = 0;
            String lastRecord = "";
            // recupération des articles à descendre
            List<Article> listArticles = articleDAO.getAllArticleByMagForUpdate(pvtCode);
            if (listArticles != null && listArticles.size() > 0) {
                for (int i = 0; i < listArticles.size(); i++) {
                    Article a = listArticles.get(i);
                    if (listGenerique == null) {
                        initVar();
                    }
                    if (a != null) {
                        List<GenCode> genCodeList = genCodeDAO.getAllGenCodeForArticle(a.getCodeArticle(), a.getPvtCode());
                        if (genCodeList != null && genCodeList.size() > 0) {
                            HashMap<String, GenCode> gencodeMap = new HashMap<>();
                            for (GenCode gc : genCodeList) {
                                gencodeMap.put(gc.getCode(), gc);
                            }
                            System.out.println(" gencodeMap :  " + gencodeMap.size());
                            Iterator it = gencodeMap.entrySet().iterator();
                            while (it.hasNext()) {
                                Map.Entry pair = (Map.Entry) it.next();
                                //String code = (String) pair.getKey();
                                GenCode gc = (GenCode) pair.getValue();
                                int asciiLot = 0;
                                char promoLot = (char) asciiLot;;
                                float prixVente = 0;
                                Integer prixLot = 0;
                                Integer nombreArticleLot = 0;
                                if (null != a.getPromo()) {
                                    Promo p = promoDAO.getPromo(a.getPromo());
                                    if (a.getTypePromo() == Article.PROMO_ORDINAIRE) {
                                        if (p.isEnVente()) {
                                            prixVente = a.getPrixVentePromoTTC();
                                        } else {
                                            prixVente = a.getPrixVenteTTCEnCours();
                                        }
                                    } else if (a.getTypePromo() == Article.PROMO_LOT_HOMOGENE) {
                                        if (p.isEnVente()) {
                                            prixVente = a.getPrixVenteTTCEnCours();
                                            asciiLot = 02;
                                            promoLot = (char) asciiLot;
                                            prixLot = a.getPrixDuLot();
                                            nombreArticleLot = a.getQuantiteLot();
                                        } else {
                                            prixVente = a.getPrixVenteTTCEnCours();
                                        }
                                    } else {
                                        prixVente = a.getPrixVenteTTCEnCours();
                                    }
                                } else {
                                    prixVente = a.getPrixVenteTTCEnCours();
                                }
                                if (gc.getCatGen().equals(CategorieGenCode.GROS)
                                        || gc.getCatGen().equals(CategorieGenCode.RCOND)
                                        || gc.getCatGen().equals(CategorieGenCode.SCOND)
                                        || gc.getCatGen().equals(CategorieGenCode.DLVM)) {
                                    prixVente = gc.getPrixVenteTTCEnCours();
                                }
                                if (prixVente <= 0 || prixVente > 9999999 || gc.getCode().equals("000000000000")) {
                                    continue;
                                } else {
                                    char tva;
                                    int asciiValue;
                                    switch (a.getTauxTVA()) {
                                        case "03":
                                            asciiValue = 16;
                                            tva = (char) asciiValue;
                                            break;
                                        case "04":
                                            asciiValue = 16;
                                            tva = (char) asciiValue;
                                            break;
                                        case "09":
                                            asciiValue = 0;
                                            tva = (char) asciiValue;
                                            break;
                                        default:
                                            asciiValue = 16;
                                            tva = (char) asciiValue;
                                            break;
                                    }
                                    String designation = a
                                            .getLibelReduit()
                                            .concat("                    ")
                                            .substring(0, 18)
                                            .replaceAll("[^A-Za-z0-9 .%/*+-]", " ");
                                    String lineArticle = codeEANFormat
                                            .format(Long.parseLong(gc.getCode()))
                                            + nul
                                            + tva
                                            + EURO
                                            + promoLot
                                            + formatFamille.format(Long.parseLong(a.getRayon().substring(2)))
                                            + premiereSerieZero
                                            + formatNbreArticleLot.format(nombreArticleLot)
                                            + formatPrixUnitaire.format(prixVente)
                                            + formatPrixDuLot.format(prixLot)
                                            + articleLie
                                            + designation
                                            + derniereSerieZero;
                                    lastRecord = lineArticle;
                                    count++;
                                    System.out.println("###### #  Count Value in Gencode loop  : " + count);
                                    if (count == 3) {
                                        line = line
                                                + "C"
                                                + lineArticle
                                                + "                                ";
                                        listFinal.add(line);
                                        listFinal.add("\n");
                                        line = "";
                                        count = 0;
                                    } else if (line.equals("")) {
                                        line = begin + lineArticle;
                                    } else {
                                        line = line + "C" + lineArticle;
                                    }
                                }
                            }
                            // }
                            System.out.println("###### #  Count Value   : " + count);
                            if ((i + 1) == listArticles.size()) {
                                System.out.println(" @@@@@@@   Count Value Final  : " + count);
                                switch (count) {
                                    case 1:
                                        line = line + "C"
                                                + lastRecord + "C"
                                                + lastRecord + "                                ";
                                        listFinal.add(line);
                                        listFinal.add("\n");
                                        break;
                                    case 2:
                                        line = line + "C" + lastRecord + "                                ";
                                        listFinal.add(line);
                                        listFinal.add("\n");
                                        break;
                                    default:
                                        break;
                                }
                                listFinal.add(lastFileLine);
                            } else {

                            }

                        }
                    }

                }
                System.out.println("D\351but creation fichier  \n");
                File fileDir = new File("tomajcai.fic." + pvtCode);
                System.out.println(" listFinal  size = " + listFinal.size());
                Writer monFluxSortie = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileDir), "cp1252"));
                monFluxSortie.write("1o0000000000" + "                                                                                                                                                                                                                                                   ");
                monFluxSortie.write("\n");
                for (String s : listFinal) {
                    monFluxSortie.write(s);
                }
                monFluxSortie.close();
                File archive = new File("C:\\archive_descente\\tomajcai.fic." + pvtCode + "." + System.currentTimeMillis());
                FileUtils.copyFile(fileDir, archive);
                System.out.println("FIN creation fichier  \n");
                System.out.println("fileDir.getName : " + fileDir.getName() + "\n");
                File f = new File("tomajcai.fic." + pvtCode);
                copyFilesForDescenteAuto(f, pvtCode);
            } else {
                System.out.println("Aucun article a descendre ");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
