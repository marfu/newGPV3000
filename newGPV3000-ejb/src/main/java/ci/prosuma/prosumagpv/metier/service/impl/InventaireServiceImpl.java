package ci.prosuma.prosumagpv.metier.service.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import ci.prosuma.prosumagpv.entity.Article;
import ci.prosuma.prosumagpv.entity.stock.DetailInventaire;
import ci.prosuma.prosumagpv.entity.stock.EnteteInventaire;
import ci.prosuma.prosumagpv.metier.dao.mysql.IDetailInventaireDAO;
import ci.prosuma.prosumagpv.metier.dao.mysql.IEnteteInventaireDAO;
import ci.prosuma.prosumagpv.metier.service.IGenCodeService;
import ci.prosuma.prosumagpv.metier.service.IInventaireService;
import ci.prosuma.prosumagpv.metier.service.IUtilService;

@Stateless
@Local(IInventaireService.class)
public class InventaireServiceImpl implements IInventaireService, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @EJB
    private IEnteteInventaireDAO enteteInvenatireDAO;

    @EJB
    private IDetailInventaireDAO detailInventaireDAO;

    @EJB
    private IUtilService utilService;

    @EJB
    private IGenCodeService genCodeService;

    @Override
    public EnteteInventaire createOrUpdateEnteteInventaire(EnteteInventaire ei) {
        return enteteInvenatireDAO.saveInventaire(ei);
    }
    
    @Override
    public EnteteInventaire updateInventaire(EnteteInventaire ei) {
        return enteteInvenatireDAO.updateInventaire(ei);
    }

    @Override
    public EnteteInventaire getEnteteInventaire(long id) {
        return enteteInvenatireDAO.getEnteteInventaire(id);
    }

    @Override
    public EnteteInventaire getEnteteInventaireWithLazyLoad(long id) {
        return enteteInvenatireDAO.getEnteteInventaireWithLazyLoadAndJoinFetch(id);
    }

    @Override
    public boolean removeEnteteInventaire(EnteteInventaire ei) {
        return enteteInvenatireDAO.removeEnteteInventaire(ei);
    }

    @Override
    public List<EnteteInventaire> getAllEnteteInventaireForMag(String pvtCode) {
        return enteteInvenatireDAO.getAllEnteteInventaireForMag(pvtCode);
    }

    @Override
    public List<EnteteInventaire> getAllEnteteInventaireForMagBySecteurAndRayon(
            String pvtCode, String codeSecteur, String codeRayon) {
        return enteteInvenatireDAO
                .getAllEnteteInventaireForMagBySecteurAndRayon(pvtCode,
                        codeSecteur, codeRayon);

    }

    @Override
    public List<EnteteInventaire> getAllEnteteInventaireForMagGenerer(
            String pvtCode) {
        return enteteInvenatireDAO.getAllEnteteInventaireForMagGenerer(pvtCode);
    }

    @Override
    public List<EnteteInventaire> getAllEnteteInventaireForMagLancer(
            String pvtCode) {
        return enteteInvenatireDAO.getAllEnteteInventaireForMagLancer(pvtCode);
    }

    @Override
    public List<EnteteInventaire> getAllEnteteInventaireForMagNonCloturer(
            String pvtCode) {
        return enteteInvenatireDAO.getAllEnteteInventaireForMagNonCloturer(pvtCode);
    }

    @Override
    public List<EnteteInventaire> getAllEnteteInventaireForMagCloturer(
            String pvtCode) {
        return enteteInvenatireDAO
                .getAllEnteteInventaireForMagCloturer(pvtCode);
    }

    @Override
    public List<EnteteInventaire> getAllEnteteInventaireForMagByGisement(
            String pvtCode, String gisementDebut, String gisementFin) {
        return enteteInvenatireDAO.getAllEnteteInventaireForMagByGisement(
                pvtCode, gisementDebut, gisementFin);
    }

    @Override
    public List<EnteteInventaire> getAllEnteteInventaireForMagByDateRange(
            String pvtCode, Date dateDebut, Date dateFin) {
        return enteteInvenatireDAO.getAllEnteteInventaireForMagByDateRange(
                pvtCode, dateDebut, dateFin);
    }

    @Override
    public List<DetailInventaire> getAllDetailInvetaireForEntete(long enteteId) {
        return enteteInvenatireDAO.getAllDetailInvetaireForEntete(enteteId);
    }

    @Override
    public DetailInventaire createOrUpdateDetailInventaire(DetailInventaire ei) {
        return detailInventaireDAO.createOrUpdateDetailInventaire(ei);

    }

    @Override
    public DetailInventaire getDetailInventaire(long id) {
        return detailInventaireDAO.getDetailInventaire(id);
    }

    @Override
    public boolean removeDetailInventaire(DetailInventaire ei) {
        return detailInventaireDAO.removeDetailInventaire(ei);
    }

    @Override
    public DetailInventaire getDetailForInventaireAndArticle(long enteteInv,
            String codeArticle) {
        return enteteInvenatireDAO.getDetailForInventaireAndArticle(enteteInv, codeArticle);
    }

    @Override
    public List<DetailInventaire> findAllDetailInventaireForArticle(String codeArticle, String pvtCode) {
        return detailInventaireDAO.findAllDetailInventaireForArticles(codeArticle, pvtCode);
    }

    @Override
    public List<EnteteInventaire> executeLazyQuery(String query, int first,
            int pageSize) {
        return enteteInvenatireDAO.executeLazyQuery(query, first, pageSize);
    }

    @Override
    public Long getRowCount(String query) {
        return enteteInvenatireDAO.getRowCount(query);
    }

    @Override
    public List<Article> getAllArticleForEnteteInventaire(long enteteId) {
        return enteteInvenatireDAO.getAllArticleForEnteteInventaire(enteteId);
    }

    @Override
    public Boolean checkArticleExistInInventaireOutstanding(String codeArticle, String pvtCode) {
        return enteteInvenatireDAO.checkArticleExistInInventaireOutstanding(codeArticle, pvtCode);
    }

    @Override
    public boolean uploadInventaire(EnteteInventaire enteteInv, String pvtCode) {

        BufferedWriter monFluxSortie;
        System.out.println("Début creation fichier");
        monFluxSortie = null;
        // DecimalFormat codeInv = new DecimalFormat("000000000000");
        String filename = String.valueOf(enteteInv.getId());// codeInv.format(enteteInv.getId());

        try {
            monFluxSortie = new BufferedWriter(new FileWriter("\\\\"
                    + utilService.getSqlRequestByLibelle(pvtCode + ".ip.mp")
                    + "\\pda\\inventaire\\inventaire" + filename + ".txt"));
            monFluxSortie.close();
            System.out.println("fichier inventaire" + filename
                    + ".txt uploader sur le serveur");

        } catch (SocketException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public List<DetailInventaire> downloadInventaire(EnteteInventaire inv, String pvt) {
        String ligne = null;

        List<DetailInventaire> listDetailInv = new ArrayList<DetailInventaire>();
        Map<String, String> mag = new HashMap<String, String>();
        Map<String, String> ent = new HashMap<String, String>();

        String filename = String.valueOf(inv.getId());
        try {

            File file_in = new File("\\\\"
                    + utilService.getSqlRequestByLibelle(pvt + ".ip.mp")
                    + "\\pda\\inventaire\\inventaire" + filename
                    + ".txt");
            Reader in = new InputStreamReader(new FileInputStream(file_in),
                    "Cp1252");

            BufferedReader br = new BufferedReader(in);

            while ((ligne = br.readLine()) != null) {
                String[] lig = ligne.split(";");
                try {
                    String art = genCodeService.getArticleForGenCodeAndPvt(
                            lig[1], pvt).getCodeArticle();
                    if (lig[0].equals("MAG")) {
                        //if(mag.containsKey(art)){
                        //mag.put(art, String.valueOf(Float.parseFloat(mag.get(art)) + Float.parseFloat(lig[2])));
                        //}else
                        mag.put(art, lig[2]);
                    } else if (lig[0].equals("ENT")) {
                        //if(ent.containsKey(art)){
                        //ent.put(art, String.valueOf(Float.parseFloat(ent.get(art)) + Float.parseFloat(lig[2])));
                        //}else
                        ent.put(art, lig[2]);
                    }
                } catch (NullPointerException e) {
                    System.err
                            .println("Aucun Article trouver pour le GenCode :"
                                    + lig[1]);
                    continue;
                } catch (IndexOutOfBoundsException e) {
                    if (!ligne.equals("")) {
                        System.err.println("Invalid ligne :" + ligne);
                    }
                    continue;
                }
            }
            br.close();
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            for (DetailInventaire detailInv : getAllDetailInvetaireForEntete(inv
                    .getId())) {
                String codeArt = detailInv.getArticle().getCodeArticle();
                if (mag.containsKey(codeArt)) {
                    detailInv.setQteMagasin(Float.parseFloat(mag.get(codeArt)));
                }
                if (ent.containsKey(codeArt)) {
                    detailInv.setQteReserve(Float.parseFloat(ent.get(codeArt)));
                }
                listDetailInv.add(detailInv);
            }
            return listDetailInv;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
