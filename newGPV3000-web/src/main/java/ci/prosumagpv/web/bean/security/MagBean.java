/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.prosumagpv.web.bean.security;

import ci.prosuma.prosumagpv.entity.PointDeVente;
import ci.prosuma.prosumagpv.metier.service.IPointDeVenteService;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author tagsergi
 */
@Named("magBean")
@SessionScoped
public class MagBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private IPointDeVenteService magService;

    private List<PointDeVente> listPointdeVente;

    private List<PointDeVente> selectedlistPointdeVente;
    
    private boolean actif;
    
    private String phoneNumber;
    
    private String nomGerant;
    
    private String codeIP;
    
    private String raisonSocialFournisseur;
    
    private String pvtCode;

    private PointDeVente newPvt;
    private static final Logger LOG = Logger.getLogger(MagBean.class.getName());

    public MagBean() {
        System.out.println("ci.prosumagpv.web.bean.security.MagBean.<init>()");
    }
    
    

    public void installVersion() {
        if (selectedlistPointdeVente.size() > 1) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFORMATION", "L'installation des versions \u00E0 d\u00E9marrer , vous serez notififi\u00E9 "
                    + "une fois l'installation termin\u00E9e");
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, facesMsg);
        } else {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement", "Veuillez s\u00E9lectionner au moins un magasin");
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, facesMsg);
        }

    }

    public void cancel(){
        newPvt = new PointDeVente();
    }
    
    public void save(){
        PointDeVente pdv = new PointDeVente();
        pdv.setActif(actif);
        pdv.setCodeIP(codeIP);
        pdv.setNomGerant(nomGerant);
        pdv.setPhoneNumber(phoneNumber);
        pdv.setPvtCode(pvtCode);
        pdv.setRaisonSocialFournisseur(raisonSocialFournisseur);
        LOG.info(pdv.toString());
        try{
            magService.createOrUpdatePVT(pdv);
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFORMATION", "Magasin : "+newPvt.getRaisonSocialFournisseur()+" cr\u00E9\u00E9 avec susc\u00E8s");
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, facesMsg);
        }catch(Exception ex ){
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "Une erreur c'est produite lors de la cr\u00E9ation du magasin "
                    + "veuillez r\u00E9essyer ou contacter l'administrateur si le probl\u00E8me persiste");
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, facesMsg);
        }
        
    }
    
    
    @PostConstruct
    private void init() {
        LOG.info("MagBean Init ");
        newPvt = new PointDeVente();
        listPointdeVente = magService.getAllPVTDB();
    }

    public List<PointDeVente> getListPointdeVente() {
        return listPointdeVente;
    }

    public void setListPointdeVente(List<PointDeVente> listPointdeVente) {
        this.listPointdeVente = listPointdeVente;
    }

    public PointDeVente getNewPvt() {
        return newPvt;
    }

    public void setNewPvt(PointDeVente newPvt) {
        this.newPvt = newPvt;
    }

    public List<PointDeVente> getSelectedlistPointdeVente() {
        return selectedlistPointdeVente;
    }

    public void setSelectedlistPointdeVente(List<PointDeVente> selectedlistPointdeVente) {
        this.selectedlistPointdeVente = selectedlistPointdeVente;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
        newPvt.setActif(actif);
    }

    public IPointDeVenteService getMagService() {
        return magService;
    }

    public void setMagService(IPointDeVenteService magService) {
        this.magService = magService;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNomGerant() {
        return nomGerant;
    }

    public void setNomGerant(String nomGerant) {
        this.nomGerant = nomGerant;
    }

    public String getCodeIP() {
        return codeIP;
    }

    public void setCodeIP(String codeIP) {
        this.codeIP = codeIP;
    }

    public String getRaisonSocialFournisseur() {
        return raisonSocialFournisseur;
    }

    public void setRaisonSocialFournisseur(String raisonSocialFournisseur) {
        this.raisonSocialFournisseur = raisonSocialFournisseur;
    }

    public String getPvtCode() {
        return pvtCode;
    }

    public void setPvtCode(String pvtCode) {
        this.pvtCode = pvtCode;
    }

}
