package ci.prosuma.prosumagpv.metier.service.impl;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import org.jboss.logging.Logger;

import ci.prosuma.prosumagpv.entity.Article;
import ci.prosuma.prosumagpv.entity.GenCode;
import ci.prosuma.prosumagpv.entity.HistoriquePromo;
import ci.prosuma.prosumagpv.entity.Promo;
import ci.prosuma.prosumagpv.entity.PromoHistoDateDTO;
import ci.prosuma.prosumagpv.metier.dao.mysql.IArticleDAO;
import ci.prosuma.prosumagpv.metier.dao.mysql.IGenCodeDAO;
import ci.prosuma.prosumagpv.metier.dao.mysql.IHistoriquePromoDAO;
import ci.prosuma.prosumagpv.metier.dao.mysql.IPromoDAO;
import ci.prosuma.prosumagpv.metier.service.IPromoService;

@Stateless
@Local(IPromoService.class)
public class PromoServiceImpl implements IPromoService, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private static Logger logger = Logger.getLogger(PromoServiceImpl.class);

    @EJB
    private IPromoDAO promoDAO;

    @EJB
    private IArticleDAO articleDAO;

    @EJB
    private IHistoriquePromoDAO historiquePromoDAO;

    @EJB
    private IGenCodeDAO genCodeDAO;

    @Override
    public Promo createOrUpdatePromo(Promo entity) {
        return promoDAO.createOrUpdatePromo(entity);
    }

    @Override
    public Promo getPromo(String entity) {
        return promoDAO.getPromo(entity);
    }

    @Override
    public boolean removePromo(Promo entity) {
        return promoDAO.removePromo(entity);
    }

    @Override
    public List<Promo> getAllPromoForPVT(String pvt) {
        return promoDAO.getAllPromoForPVT(pvt);
    }

    @Override
    public List<Promo> getAllPromoActiveForPVT(String pvt) {
        return promoDAO.getAllPromoActiveForPVT(pvt);
    }

    @Override
    public List<Promo> getAllPromoEnVenteForPVT(String pvt) {
        return promoDAO.getAllPromoEnVenteForPVT(pvt);
    }

//	@Override
//	public List<Promo> getAllPromoForDateRangeAndMag(Date dateDebut,
//			Date dateFin, String pvt) {
//		return promoDAO.getAllPromoForDateRangeAndMag(dateDebut, dateFin, pvt);
//	}
    @Override
    public List<Promo> getAllPromoForDateRangeAndMag(Date dateDebut,
            Date dateFin, String pvt) {
        List<Promo> responseList = new ArrayList<>();
        Date today = new Date();
        List<Promo> listPromo = promoDAO.getAllPromoForDateRangeAndMag(dateDebut, dateFin, pvt);
        logger.info("##########  listPROMO size : " + listPromo.size());
        for (Promo p : listPromo) {
            logger.info("##########  Promo  : " + p.toString());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dateFormat = null;
            try {
                dateFormat = sdf.parse(sdf.format(today));
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (p.getDateFinPromo().compareTo(dateFormat) > 0 || p.getDateFinPromo().compareTo(dateFormat) == 0) {
                responseList.add(p);

                List<PromoHistoDateDTO> listHisto = promoDAO.getAllPastPromoDate(p.getLibelReduit(), pvt);
                if (listHisto != null && listHisto.size() > 0) {
                    logger.info("@@@@@@@@@   listHisto : " + listHisto.toString());
                    for (PromoHistoDateDTO ph : listHisto) {
                        Promo promo = new Promo();
                        promo.setTypePromo(p.getTypePromo());
                        promo.setLibelReduit(p.getLibelReduit());
                        promo.setDateDebutFacturation(ph.getDateDebutFacturation());
                        promo.setActif(false);
                        promo.setDateDebutPromo(ph.getDateDebutPromo());
                        promo.setDateFinFacturation(ph.getDateFinFacturation());
                        promo.setDateFinPromo(ph.getDateFinPromo());
                        promo.setDesignation(p.getDesignation());
                        responseList.add(promo);
                    }
                }
            } else {
                List<PromoHistoDateDTO> listHisto = promoDAO.getAllPastPromoDate(p.getLibelReduit(), pvt);
                if (listHisto != null && listHisto.size() > 0) {
                    for (PromoHistoDateDTO ph : listHisto) {
                        Promo promo = new Promo();
                        promo.setTypePromo(p.getTypePromo());
                        promo.setLibelReduit(p.getLibelReduit());
                        promo.setDateDebutFacturation(ph.getDateDebutFacturation());
                        promo.setActif(false);
                        promo.setDateDebutPromo(ph.getDateDebutPromo());
                        promo.setDateFinFacturation(ph.getDateFinFacturation());
                        promo.setDateFinPromo(ph.getDateFinPromo());
                        promo.setDesignation(p.getDesignation());
                        responseList.add(promo);
                    }
                }
            }
        }
        Collections.sort(responseList);
        List<Promo> sortedList = new ArrayList<>();
        for (int i = (responseList.size() - 1); i > 0; i--) {
            sortedList.add(responseList.get(i));
        }
        return sortedList;
    }

    @Override
    public List<Promo> getAllPromoForDateAndMag(Date date, String pvtCode) {
        return promoDAO.getAllPromoForDateAndMag(date, pvtCode);
    }

    @Override
    public void processPromoForMag(String pvtCode, String promo) {
        try {

            Promo p = promoDAO.getPromo(promo);
            if (p != null) {

                Calendar dateDuJour = Calendar.getInstance();
                dateDuJour.setTime(new Date(System.currentTimeMillis()));

                Calendar dateDebutPromo = Calendar.getInstance();
                dateDebutPromo.setTime(p.getDateDebutPromo());

                Calendar dateFinPromo = Calendar.getInstance();
                dateFinPromo.setTime(p.getDateFinPromo());
                dateFinPromo.add(Calendar.DATE, 1);

                Calendar dateDebutFacturation = Calendar.getInstance();
                dateDebutFacturation.setTime(p.getDateDebutFacturation());

                Calendar dateFinFacturation = Calendar.getInstance();
                dateFinFacturation.setTime(p.getDateFinFacturation());
                dateFinFacturation.add(Calendar.DATE, 1);

                if (dateDuJour.getTime().after(dateDebutFacturation.getTime())
                        && dateDuJour.getTime().before(
                                dateFinFacturation.getTime())) {
                    p.setEnFacturation(true);
                    p.setActif(true);
                    createOrUpdatePromo(p);
                } else {
                    p.setEnFacturation(false);
                    createOrUpdatePromo(p);
                }

                if (dateDuJour.getTime().after(dateDebutPromo.getTime())
                        && dateDuJour.getTime().before(dateFinPromo.getTime())) {
                    p.setEnVente(true);
                    p.setActif(true);
                    createOrUpdatePromo(p);
                } else {
                    p.setEnVente(false);
                    createOrUpdatePromo(p);
                }

                if (dateDuJour.getTime().after(dateFinPromo.getTime())) {
                    for (Article a : articleDAO.getAllArticleForPromoAndPVT(
                            p.getLibelReduit(), pvtCode)) {

                        HistoriquePromo hp = new HistoriquePromo();
                        hp.setCodeArticle(a.getCodeArticle());
                        hp.setCodeMag(a.getPvtCode());
                        hp.setDateDebutFacturation(p.getDateDebutFacturation());
                        hp.setDateDebutPromo(p.getDateDebutPromo());
                        hp.setDateFinFacturation(p.getDateFinFacturation());
                        hp.setDateFinPromo(p.getDateFinPromo());
                        hp.setDesignation(p.getDesignation());
                        hp.setLibelReduit(p.getLibelReduit());
                        hp.setPrixReviensTTCNormal(a.getPrixReviensTTCEnCours());
                        hp.setPrixReviensTTCPromo(a.getPrixReviensPromoTTC());
                        hp.setPrixVenteTTCNormal(a.getPrixVenteTTCEnCours());
                        hp.setPrixVenteTTCPromo(a.getPrixVentePromoTTC());

                        historiquePromoDAO.createOrUpdateHistoriquePromo(hp);

                        a.setPromo(null);
                        a.setPrixVentePromoTTC(0);
                        a.setPrixReviensPromoTTC(0);
                        a.setPrixModifier(true);
                        articleDAO.createOrUpdateArticle(a);
                    }
                    p.setActif(false);
                    p.setEnFacturation(false);
                    p.setEnVente(false);
                    promoDAO.createOrUpdatePromo(p);
                    // removePromo(p);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public HistoriquePromo createOrUpdateHistoriquePromo(HistoriquePromo entity) {
        return historiquePromoDAO.createOrUpdateHistoriquePromo(entity);
    }

    @Override
    public HistoriquePromo getHistoriquePromo(HistoriquePromo entity) {
        return historiquePromoDAO.getHistoriquePromo(entity);
    }

    @Override
    public boolean removeHistoriquePromo(HistoriquePromo entity) {
        return historiquePromoDAO.removeHistoriquePromo(entity);
    }

    @Override
    public List<HistoriquePromo> getAllHistoriquePromoForPromo(String pvtCode,
            String libelReduit) {
        return historiquePromoDAO.getAllHistoriquePromoForPromo(pvtCode,
                libelReduit);
    }

    @Override
    public void UpdatePromoZero() {
        System.out.println(" #############    DEBUT  - UpdatePromoZero  ");
        articleDAO.UpdatePrixZero();
        System.out.println(" #############    FIN  - UpdatePromoZero  ");
    }

    @Override
    public void calculPromoAuto(String mag) {
        System.out.println(" #############    DEBUT  - calculPromoAuto  ");
        for (Promo p : getAllPromoForPVT(mag)) {
            try {
                if (p != null) {
                    Calendar dateDuJour = Calendar.getInstance();
                    dateDuJour.setTime(new Date(System.currentTimeMillis()));

                    Calendar dateFinPromo = Calendar.getInstance();
                    dateFinPromo.setTime(p.getDateFinPromo());
                    dateFinPromo.add(Calendar.DATE, 1);

                    Calendar dateFinFacturation = Calendar.getInstance();
                    dateFinFacturation.setTime(p.getDateFinFacturation());
                    dateFinFacturation.add(Calendar.DATE, 1);

                    // fin facturation
                    if (dateDuJour.getTime().after(dateFinFacturation.getTime())) {
                        p.setEnFacturation(false);
                        createOrUpdatePromo(p);
                    }

                    // fin promo
                    if (dateDuJour.getTime().after(dateFinPromo.getTime())) {
                        System.out.println("@@@@@@ FIN PROMO  THEME : " + p.getLibelReduit());
                        p.setEnVente(false);
                        createOrUpdatePromo(p);
                    }

                    // histo et libere article promo
                    if (dateDuJour.getTime().after(dateFinPromo.getTime())) {
                        System.out.println("@@@@@@ LIBERATION DU  THEME : " + p.getLibelReduit());
                        /*for (Article a : articleDAO.getAllArticleForPromoAndPVT(p.getLibelReduit(), mag)) {*/
                        for (Article a : articleDAO.getAllArticleForPromoAndMag(p.getLibelReduit(), mag)) {
                            HistoriquePromo hp = new HistoriquePromo();
                            hp.setCodeArticle(a.getCodeArticle());
                            hp.setCodeMag(a.getPvtCode());
                            hp.setDateDebutFacturation(p.getDateDebutFacturation());
                            hp.setDateDebutPromo(p.getDateDebutPromo());
                            hp.setDateFinFacturation(p.getDateFinFacturation());
                            hp.setDateFinPromo(p.getDateFinPromo());
                            hp.setDesignation(p.getDesignation());
                            hp.setLibelReduit(p.getLibelReduit());
                            hp.setPrixReviensTTCNormal(a.getPrixReviensTTCEnCours());
                            hp.setPrixReviensTTCPromo(a.getPrixReviensPromoTTC());
                            hp.setPrixVenteTTCNormal(a.getPrixVenteTTCEnCours());
                            hp.setPrixVenteTTCPromo(a.getPrixVentePromoTTC());
                            try {
                                historiquePromoDAO.createOrUpdateHistoriquePromo(hp);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            a.setPromo(null);
                            /* modif ayepi*/
                            a.setTypePromo(Article.NO_PROMO);
                            a.setCodeLot(0);
                            a.setQuantiteLot(0);
                            a.setPrixDuLot(0);
                            a.setPrixVentePromoTTC(0);
                            a.setPrixReviensPromoTTC(0);
                            /*fin  modif ayepi*/

                            a.setPrixModifier(true);
                            a.setModifier(true);
                            articleDAO.createOrUpdateArticle(a);
                            // recherche la liste des code-barres à mettre à jours
                            List<GenCode> genCodeList = genCodeDAO.getAllGenCodeForArticle(a.getCodeArticle(), a.getPvtCode());
                            for (GenCode genCode : genCodeList) {

                                /* Modif effectué par SErge AYEPI*/
                                if (a.getPrixVenteTTCEnCours() == 0) {
                                    genCode.setDescenteAEffectuer(false);
                                    genCode.setModifier(false);
                                } else {
                                    genCode.setDescenteAEffectuer(true);
                                    genCode.setModifier(true);
                                }

                                genCodeDAO.createOrUpdateGenCode(genCode);
                            }
                        }
                        p.setActif(false);
                        p.setEnFacturation(false);
                        p.setEnVente(false);
                        promoDAO.createOrUpdatePromo(p);
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println(" #############    FIN  - calculPromoAuto  ");
    }

    @Override
    public void calculArticlePromo(String mag) {
        System.out.println(" #############    DEBUT  - CalculArticlePromo  ");
        promoDAO.getAllPromoActiveForPVT(mag).stream().forEach((p) -> {
            try {
                if (p != null) {
                    Calendar dateDuJour = Calendar.getInstance();
                    dateDuJour.setTime(new Date(System.currentTimeMillis()));

                    Calendar dateDebutPromo = Calendar.getInstance();
                    dateDebutPromo.setTime(p.getDateDebutPromo());

                    Calendar dateFinPromo = Calendar.getInstance();
                    dateFinPromo.setTime(p.getDateFinPromo());
                    dateFinPromo.add(Calendar.DATE, 1);

                    Calendar dateDebutFacturation = Calendar.getInstance();
                    dateDebutFacturation.setTime(p.getDateDebutFacturation());

                    Calendar dateFinFacturation = Calendar.getInstance();
                    dateFinFacturation.setTime(p.getDateFinFacturation());
                    dateFinFacturation.add(Calendar.DATE, 1);

                    // debut facturation
                    if ((dateDuJour.getTime().after(dateDebutFacturation.getTime()) || dateDuJour.getTime().equals(dateDebutFacturation.getTime())) && dateDuJour.getTime().before(
                            dateFinFacturation.getTime())) {
                        p.setEnFacturation(true);
                        p.setActif(true);
                        createOrUpdatePromo(p);
                    }//rajout par serge pour la refonte du processus des descente quotidienne
                    else if (dateDuJour.getTime().after(dateFinFacturation.getTime())) {
                        System.out.println("@@@@@@@@@@@@  FIN FACTURATION DU THEME : " + p.getLibelReduit());
                        p.setEnFacturation(false);
                        createOrUpdatePromo(p);
                    }

                    // debut promo
                    if ((dateDuJour.getTime().after(dateDebutPromo.getTime()) || dateDuJour.getTime().equals(dateDebutPromo.getTime())) && dateDuJour.getTime().before(dateFinPromo.getTime())) {
                        p.setEnVente(true);
                        p.setActif(true);
                        createOrUpdatePromo(p);
                        System.out.println(" #############    getLibelReduit  :  " + p.getLibelReduit());
                        List<Article> listArticle = articleDAO.getAllArticleByMagAndPromo(p.getLibelReduit(), mag);
                        if (listArticle != null && listArticle.size() > 0) {
                            for (Article a : listArticle) {
                                System.out.println("PROMOSERVICEIMPL - CalculArticlePromo = " + a.getCodeArticle());
                                if (a != null) {
                                    a.setPromo(p.getLibelReduit());
                                    a.setPrixModifier(true);
                                    a.setModifier(true);
                                    articleDAO.createOrUpdateArticle(a);
                                }
                            }
                        } else {
                            System.out.println("PROMOSERVICEIMPL - CalculArticlePromo Aucun article Promo :  " + p.getLibelReduit());
                        }
                    }//rajout par serge pour la refonte du processus des descente quotidienne
                    else if (dateDuJour.getTime().after(dateFinPromo.getTime())) {
                        System.out.println("@@@@@@@@@@@@  FIN PROMO  DU THEME : " + p.getLibelReduit());

                        /*for (Article a : articleDAO.getAllArticleForPromoAndPVT(p.getLibelReduit(), mag)) {*/
                        for (Article a : articleDAO.getAllArticleForPromoAndMag(p.getLibelReduit(), mag)) {
//                            HistoriquePromo hp = new HistoriquePromo();
//                            hp.setCodeArticle(a.getCodeArticle());
//                            hp.setCodeMag(a.getPvtCode());
//                            hp.setDateDebutFacturation(p.getDateDebutFacturation());
//                            hp.setDateDebutPromo(p.getDateDebutPromo());
//                            hp.setDateFinFacturation(p.getDateFinFacturation());
//                            hp.setDateFinPromo(p.getDateFinPromo());
//                            hp.setDesignation(p.getDesignation());
//                            hp.setLibelReduit(p.getLibelReduit());
//                            hp.setPrixReviensTTCNormal(a.getPrixReviensTTCEnCours());
//                            hp.setPrixReviensTTCPromo(a.getPrixReviensPromoTTC());
//                            hp.setPrixVenteTTCNormal(a.getPrixVenteTTCEnCours());
//                            hp.setPrixVenteTTCPromo(a.getPrixVentePromoTTC());
//                            try {
//                                historiquePromoDAO.createOrUpdateHistoriquePromo(hp);
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
                            a.setPromo(null);

                            /* modif ayepi*/
                            a.setTypePromo(Article.NO_PROMO);
                            a.setCodeLot(0);
                            a.setQuantiteLot(0);
                            a.setPrixDuLot(0);
                            a.setPrixVentePromoTTC(0);
                            a.setPrixReviensPromoTTC(0);
                            /*fin  modif ayepi*/

                            a.setPrixModifier(true);
                            a.setModifier(true);
                            articleDAO.createOrUpdateArticle(a);
                        }

                        System.out.println("@@@@@@ DESACTIVATION DU  THEME : " + p.getLibelReduit());
                        p.setEnVente(false);
                        p.setActif(false);
                        p.setaTraiter(false);
                        createOrUpdatePromo(p);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        System.out.println(" #############    FIN  - CalculArticlePromo  ");

    }

    @Override
    public List<PromoHistoDateDTO> getAllPromoDate(String codePromo, String mag) {
        // TODO Auto-generated method stub
        return promoDAO.getAllPastPromoDate(codePromo, mag);
    }

    @Override
    public Promo getPromoByCodePromoAndDateDebutFacturation(String entity,
            Date dateDebutPromo) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Promo> getAllPromoRangeAndMag(String codePromo, String pvt) {
        List<Promo> responseList = new ArrayList<>();
        Date today = new Date();
        Promo p = promoDAO.getPromoByMag(codePromo, pvt);
        if (p != null) {
            logger.info("##########  Promo  : " + p.toString());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dateFormat = null;
            try {
                dateFormat = sdf.parse(sdf.format(today));
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (p.getDateFinPromo().compareTo(dateFormat) > 0 || p.getDateFinPromo().compareTo(dateFormat) == 0) {
                responseList.add(p);

                List<PromoHistoDateDTO> listHisto = promoDAO.getAllPastPromoDate(p.getLibelReduit(), pvt);
                if (listHisto != null && listHisto.size() > 0) {
                    logger.info("@@@@@@@@@   listHisto : " + listHisto.toString());
                    for (PromoHistoDateDTO ph : listHisto) {
                        Promo promo = new Promo();
                        promo.setTypePromo(p.getTypePromo());
                        promo.setLibelReduit(p.getLibelReduit());
                        promo.setDateDebutFacturation(ph.getDateDebutFacturation());
                        promo.setActif(false);
                        promo.setDateDebutPromo(ph.getDateDebutPromo());
                        promo.setDateFinFacturation(ph.getDateFinFacturation());
                        promo.setDateFinPromo(ph.getDateFinPromo());
                        promo.setDesignation(p.getDesignation());
                        responseList.add(promo);
                    }
                }
            } else {
                List<PromoHistoDateDTO> listHisto = promoDAO.getAllPastPromoDate(p.getLibelReduit(), pvt);
                if (listHisto != null && listHisto.size() > 0) {
                    for (PromoHistoDateDTO ph : listHisto) {
                        logger.info("@@@@@@@@@   listHisto else : " + listHisto.toString());
                        Promo promo = new Promo();
                        promo.setTypePromo(p.getTypePromo());
                        promo.setLibelReduit(p.getLibelReduit());
                        promo.setDateDebutFacturation(ph.getDateDebutFacturation());
                        promo.setActif(false);
                        promo.setDateDebutPromo(ph.getDateDebutPromo());
                        promo.setDateFinFacturation(ph.getDateFinFacturation());
                        promo.setDateFinPromo(ph.getDateFinPromo());
                        promo.setDesignation(p.getDesignation());
                        responseList.add(promo);

                    }
                    logger.info("@@@@@@@@@   responseList  : " + responseList.toString());
                }
            }
        }
        Collections.sort(responseList);
        List<Promo> sortedList = new ArrayList<>();
        for (Promo pro : responseList) {
            sortedList.add(pro);
        }
//		for(int i=(responseList.size()-1);i==0;i--){
//			sortedList.add(responseList.get(i));
//		}
        logger.info("@@@@@@@@@   sortedList : " + sortedList.toString());
        return sortedList;
    }

    @Override
    public List<Promo> getAllPromoByMagAndThemePromoLike(String codeLot,
            String pvt) {
        // TODO Auto-generated method stub
        return promoDAO.getPromoByMagAndThemePromoLike(codeLot, pvt);
    }

    @Override
    public HashMap<String, String> getAllPromoEnFacturationByMag(String pvt) {
        HashMap<String, String> resultMap = new HashMap<>();
        List<Promo> listProFacturation = promoDAO.getAllPromoEnFacturationForPVT(pvt);

        if (listProFacturation != null && listProFacturation.size() > 0) {
            listProFacturation.stream().forEach((p) -> {
                resultMap.put(p.getLibelReduit(), p.getLibelReduit());
            });
        }
        return resultMap;
    }

    @Override
    public List<String> getAllPromoStringEnFacturationByMag(String pvt) {
        List<String> results = new ArrayList<>();
        List<Promo> listProFacturation = promoDAO.getAllPromoEnFacturationForPVT(pvt);

        if (listProFacturation != null && listProFacturation.size() > 0) {
            listProFacturation.stream().forEach((p) -> {
                results.add(p.getLibelReduit());
            });
        }
        return results;
    }

}
