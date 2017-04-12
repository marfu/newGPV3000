/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.bean;

import ci.prosumagpv.web.bean.security.SecurityBean;
import com.nov.hotel.dto.ChambreOccupeDto;
import com.nov.hotel.entities.EtatChambreEnum;
import com.nov.hotel.entities.EtatFactureEnum;
import com.nov.hotel.entities.TChambre;
import com.nov.hotel.entities.TChambreReservation;
import com.nov.hotel.entities.TClient;
import com.nov.hotel.entities.TDetailFacture;
import com.nov.hotel.entities.TFacture;
import com.nov.hotel.entities.TModePaiment;
import com.nov.hotel.entities.TOccupation;
import com.nov.hotel.entities.TRemise;
import com.nov.hotel.entities.TService;
import com.nov.hotel.services.TChambreReservationService;
import com.nov.hotel.services.TChambreService;
import com.nov.hotel.services.TClientService;
import com.nov.hotel.services.TDepositeService;
import com.nov.hotel.services.TDetailFactureService;
import com.nov.hotel.services.TFactureService;
import com.nov.hotel.services.TModePaiementService;
import com.nov.hotel.services.TOccupationService;
import com.nov.hotel.services.TRemiseService;
import com.nov.hotel.services.TServiceService;
import com.sun.jmx.remote.internal.ArrayQueue;
import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author marfu
 */
@Named(value = "chambreFactureBean")
@SessionScoped
public class ChambreFactureBean implements Serializable {
    
    @EJB
    private TRemiseService tRemiseService;
    
    @EJB
    private TOccupationService tOccupationService;
    
    @EJB
    private TFactureService tFactureService;
    
    @EJB
    private TClientService tClientService;
    
    @EJB
    private TChambreReservationService tChambreReservationService;
    
    @EJB
    private TDepositeService tDepositeService;
    
    @EJB
    private TServiceService tServiceService;
    
    @EJB
    private TChambreService tChambreService;
    
    @EJB
    private TDetailFactureService tDetailFactureService;
    
    @EJB
    private TModePaiementService tModePaiementService;
    
    private List<TOccupation> chambreOccupation;
    
    private ChambreOccupeDto chambreOccSelectedDto;
    
    private List<TOccupation> chambreOccupationSelected;
    
    private TRemise reemise;
    
    private TOccupation chambreOccSelected;
    private TOccupation chambreOccSelected2;
    private TFacture fac = new TFacture();
    
    private long remise;
    private long clientId;
    private long modePaiementId;
    
    private Double tauxRemise = 0.0;
    private Double montantTotal = 0.0;
    private Double montantTTC = 0.0;
    
    private Double montantRemise = 0.0;
    
    private Double deposit = 0.0;
    
    @ManagedProperty(value = "#{securityBean}")
    private SecurityBean securityBean;
    
    private List<TClient> clientByChoose;
    
    private List<ChambreOccupeDto> chambreOccupeDto = new ArrayList<>();
    
    public ChambreOccupeDto getChambreOccSelectedDto() {
        return chambreOccSelectedDto;
    }
    
    public void setChambreOccSelectedDto(ChambreOccupeDto chambreOccSelectedDto) {
        this.chambreOccSelectedDto = chambreOccSelectedDto;
    }
    
    public List<TOccupation> getChambreOccupation() {
        return chambreOccupation = tOccupationService.ListTOccupation();
    }
    
    public void setChambreOccupation(List<TOccupation> chambreOccupation) {
        this.chambreOccupation = chambreOccupation;
    }
    
    public List<TOccupation> getChambreOccupationSelected() {
        return chambreOccupationSelected;
    }
    
    public void setChambreOccupationSelected(List<TOccupation> chambreOccupationSelected) {
        this.chambreOccupationSelected = chambreOccupationSelected;
    }
    
    public TFacture getFac() {
        return fac;
    }
    
    public void setFac(TFacture fac) {
        this.fac = fac;
    }
    
    public TOccupation getChambreOccSelected() {
        return chambreOccSelected;
    }
    
    public void setChambreOccSelected(TOccupation chambreOccSelected) {
        this.chambreOccSelected = chambreOccSelected;
    }
    
    public List<TClient> getClientByChoose() {
        return clientByChoose;
    }
    
    public void setClientByChoose(List<TClient> clientByChoose) {
        this.clientByChoose = clientByChoose;
    }
    
    public long getClientId() {
        return clientId;
    }
    
    public void setClientId(long clientId) {
        this.clientId = clientId;
    }
    
    public long getRemise() {
        return remise;
    }
    
    public void setRemise(long remise) {
        this.remise = remise;
    }
    
    public List<ChambreOccupeDto> getChambreOccupeDto() {
        return chambreOccupeDto;
    }
    
    public void setChambreOccupeDto(List<ChambreOccupeDto> chambreOccupeDto) {
        this.chambreOccupeDto = chambreOccupeDto;
    }
    
    public Double getMontantTotal() {
        return montantTotal;
    }
    
    public void setMontantTotal(Double montantTotal) {
        this.montantTotal = montantTotal;
    }
    
    public Double getMontantTTC() {
        return montantTTC;
    }
    
    public void setMontantTTC(Double montantTTC) {
        this.montantTTC = montantTTC;
    }
    
    public Double getDeposit() {
        
        return deposit;
    }
    
    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }
    
    public Double getMontantRemise() {
        return montantRemise;
    }
    
    public void setMontantRemise(Double montantRemise) {
        this.montantRemise = montantRemise;
    }
    
    public long getModePaiementId() {
        return modePaiementId;
    }
    
    public void setModePaiementId(long modePaiementId) {
        this.modePaiementId = modePaiementId;
    }
    
    public void loadDetailsFacture() {
        clientByChoose = new ArrayList<>();
        chambreOccupeDto = new ArrayList<>();
        montantTTC = 0.0;
        montantTotal = 0.0;
        long lastId = tFactureService.findLastTFacture();
        
        if (lastId == 0) {
            lastId = (long) 1;
        }
        
        int lastIdN = (int) (lastId + 1);
        
        Date date = new Date();
        Format format = new SimpleDateFormat("yyyy");
        String dateToString = format.format(date);

        //fac.setNumFacture("F-" + dateToString + "-" + lastIdN);
        fac.setFactDateCreate(date);
        System.out.println("Nombre de lignes" + chambreOccupationSelected.size());
        for (TOccupation str : chambreOccupationSelected) {
            ChambreOccupeDto chOccupeDto = new ChambreOccupeDto();
            
            System.out.println("xxxxxxxxxxxxxxxxxxxxxx" + str.getClient().getCliId());
            
            TClient listTClient = tClientService.listCLientByChambreEtReservation(str.getClient().getCliId());
            
            clientByChoose.add(listTClient);
            
            TChambreReservation tChambreReser = tChambreReservationService.findChambreOccReser(str.getNumReservation(), str.getChambre().getChId());
            
            long CONST_DURATION_OF_DAY = 1000l * 60 * 60 * 24;
            
            long date1 = str.getOccDateArr().getTime();
            
            long date2 = str.getOccDateDep().getTime();
            // Différence
            long diff = Math.abs(date2 - date1);
            long numberOfDay = (long) diff / CONST_DURATION_OF_DAY;
            
            Double prix = tChambreReser.getPrix() * numberOfDay;
            chOccupeDto.setChambreOccupation(tChambreReser);
            chOccupeDto.setOccupation(str);
            chOccupeDto.setNbreNuits((int) numberOfDay);
            
            chOccupeDto.setPrix(tChambreReser.getPrix());
            
            chOccupeDto.setTotal(prix);
            
            chambreOccupeDto.add(chOccupeDto);
            
            montantTotal = montantTotal + prix;
            
        }

        //montantTTC = montantTotal + (montantTotal * 18 / 100);
        montantTTC = montantTotal - deposit;
        
    }
    
    public void changeDate() {
        montantTotal = 0.0;
        long CONST_DURATION_OF_DAY = 1000l * 60 * 60 * 24;
        
        long date1 = chambreOccSelectedDto.getOccupation().getOccDateArr().getTime();
        
        long date2 = chambreOccSelectedDto.getOccupation().getOccDateDep().getTime();
        // Différence
        long diff = Math.abs(date2 - date1);
        long numberOfDay = (long) diff / CONST_DURATION_OF_DAY;
        
        Double prix = chambreOccSelectedDto.getPrix() * numberOfDay;
        
        chambreOccSelectedDto.setNbreNuits((int) numberOfDay);
        
        chambreOccSelectedDto.setPrix(chambreOccSelectedDto.getPrix());
        
        chambreOccSelectedDto.setTotal(prix);
        
        for (ChambreOccupeDto str : chambreOccupeDto) {
            
            prix = str.getTotal();

            //chambreOccupeDto.add(chOccupeDto);
            montantTotal = montantTotal + prix;
            
        }

        // montantTTC = montantTotal + (montantTotal * 18 / 100);
        tOccupationService.CreerOrUpdateTOccupation(chambreOccSelectedDto.getOccupation());
        montantTTC = montantTotal - deposit;

        // chambreOccupeDto.add(chambreOccSelectedDto);
        reemise = tRemiseService.findRemiseById(remise);
        tauxRemise = reemise.getRemiseTaux();
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxx" + tauxRemise);
        montantRemise = (montantTotal * tauxRemise) / 100;
        montantTTC = montantTotal - (montantTotal * tauxRemise / 1000);
    }
    
    public void findDepository() {
        deposit = tDepositeService.findTDepositeByUser(clientId);
        montantTTC = montantTotal - deposit;
        
    }
    
    public void chooseRemise() {
        
        reemise = tRemiseService.findRemiseById(remise);
        tauxRemise = reemise.getRemiseTaux();
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxx" + tauxRemise);
        montantRemise = (montantTotal * tauxRemise) / 100;
        montantTTC = montantTotal - (montantTotal * tauxRemise / 1000);
        
    }
    
    public void saveObject() {
        
    }
    
    public void validerEncaisser() {
        
        TFacture fac = new TFacture();
        fac.setFactDateCreate(new Date());

        //tFactureService.
        String userName = getSecurityBean().getUserName();
        
        fac.setStatuId(EtatFactureEnum.CLOTURER);
        long lastId = tFactureService.findLastTFacture();
        
        if (lastId == 0) {
            lastId = (long) 1;
        }
        
        int lastIdN = (int) (lastId + 1);
        
        fac.setUserCreate(userName);
        
        Date date = new Date();
        Format format = new SimpleDateFormat("yyyy");
        String dateToString = format.format(date);
        
        fac.setNumFacture("F-" + dateToString + "-" + lastIdN);
        fac = tFactureService.CreerTFacture(fac);
        TClient cli = tClientService.findById(clientId);
        fac.setClient(cli);
        TModePaiment modPaie = tModePaiementService.findTModePaimentById(modePaiementId);
        fac.setModePaiement(modPaie);
        for (ChambreOccupeDto str : chambreOccupeDto) {
            
            TDetailFacture detailsFac = new TDetailFacture();
            // detailsFac.setFacture(fac);
            detailsFac.setUserModif(userName);
            detailsFac.setDfactQte(str.getNbreNuits());
            detailsFac.setDfactDateCreate(new Date());
            detailsFac.setDfactPrix(str.getPrix());
            TService tS = tServiceService.findByLibService("HEBERGEMENT");
            TService tSConso = tServiceService.findByLibService("CHAMBRE");
            //TService tSConso = tServiceService.findByLibService("CHAMBRE");
            detailsFac.setService(tS);
            detailsFac.setServiceConsoId(tSConso);
            //detailsFac.setArticleConsoId(articleConsoId);
            tDetailFactureService.CreerTDetailFacture(detailsFac);
            
            TChambre tchambre = tChambreService.findChambreById(str.getChambreOccupation().getChambre().getChId());
            System.out.println("xxxxxxttttt" + tchambre.getChId());
            tchambre.setEtat(EtatChambreEnum.LIBRE);
            tChambreService.CreerOrUpdate(tchambre);
            
        }
        
        FacesContext context = FacesContext.getCurrentInstance();
        
        context.addMessage(null, new FacesMessage("Success", "Votre traitement a bien été pris en compte !"));
        
    }
    
    public SecurityBean getSecurityBean() {
        if (securityBean == null) {
            FacesContext context = FacesContext.getCurrentInstance();
            securityBean = (SecurityBean) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(context.getELContext(), null, "securityBean");
        }
        return securityBean;
    }
    
    public void setSecurityBean(SecurityBean securityBean) {
        this.securityBean = securityBean;
    }
    
    public void onRowSelect(SelectEvent event) {
        System.out.print(" xxxxxxxxxNbxxxxxxxxxxxxxx:" + chambreOccupationSelected);
    }
    
}
