/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.bean;

import ci.prosumagpv.web.bean.security.SecurityBean;
import com.nov.hotel.dto.ChambreDto;
import com.nov.hotel.dto.InfoChambreDto;
import com.nov.hotel.entities.ClientEnum;
import com.nov.hotel.entities.EtatChambreEnum;
import com.nov.hotel.entities.EtatReservationEnum;
import com.nov.hotel.entities.TCategorieChambre;
import com.nov.hotel.entities.TChambre;
import com.nov.hotel.entities.TChambreReservation;
import com.nov.hotel.entities.TClient;
import com.nov.hotel.entities.TOffreTarifaire;
import com.nov.hotel.entities.TReservation;
import com.nov.hotel.entities.TTarif;
import com.nov.hotel.services.TCategorieChambreService;
import com.nov.hotel.services.TChambreReservationService;
import com.nov.hotel.services.TChambreService;
import com.nov.hotel.services.TClientService;
import com.nov.hotel.services.TOffreTarifaireService;
import com.nov.hotel.services.TReservationService;
import com.nov.hotel.services.TTarifService;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

/**
 *
 * @author marfu
 */
@Named(value = "reservationBean")
@SessionScoped
public class ReservationBean implements Serializable {

    private long idCategorieChambre;

    private int nombreNuit;

    private Date dateDebut;

    private Date dateFin;

    private Date dateCreation;

    private List<InfoChambreDto> detailsChambres = new ArrayList<InfoChambreDto>();

    private List<ChambreDto> listChambresDto = new ArrayList<ChambreDto>();

    private TChambre chambreDetails = new TChambre();
    private ChambreDto chambreDeleteDetails;

    private ClientEnum clientCat;

    private TClient client = new TClient();

    private List<InfoChambreDto> dtoAll = new ArrayList<InfoChambreDto>();
    private List<TChambre> listChambreLibre;

    private TReservation reservation = new TReservation();

    private boolean testCat = true;

    private String typeClient;
    @ManagedProperty(value = "#{securityBean}")
    private SecurityBean securityBean;
    @EJB
    private TChambreService tChambreService;

    @EJB
    private TCategorieChambreService tCategorieChambreService;

    @EJB
    private TOffreTarifaireService tOffreTarifaireService;

    @EJB
    private TTarifService tTarifService;

    @EJB
    private TClientService tClientService;

    @EJB
    private TReservationService tReservationService;

    @EJB
    private TChambreReservationService tChambreReservationService;

    private List<TOffreTarifaire> listTOffreTarifaire;

    public long getIdCategorieChambre() {
        return idCategorieChambre;
    }

    public void setIdCategorieChambre(long idCategorieChambre) {
        this.idCategorieChambre = idCategorieChambre;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public List<InfoChambreDto> getDetailsChambres() {
        return detailsChambres;
    }

    public void setDetailsChambres(List<InfoChambreDto> detailsChambres) {
        this.detailsChambres = detailsChambres;
    }

    public List<InfoChambreDto> getDtoAll() {

        return dtoAll;
    }

    public void setDtoAll(List<InfoChambreDto> dtoAll) {
        this.dtoAll = dtoAll;
    }

    public TReservation getReservation() {
        return reservation;
    }

    public void setReservation(TReservation reservation) {
        this.reservation = reservation;
    }

    public int getNombreNuit() {
        return nombreNuit;
    }

    public void setNombreNuit(int nombreNuit) {
        this.nombreNuit = nombreNuit;
    }

    public ClientEnum getClientCat() {
        return clientCat;
    }

    public void setClientCat(ClientEnum clientCat) {
        this.clientCat = clientCat;
    }

    public TClient getClient() {
        return client;
    }

    public void setClient(TClient client) {
        this.client = client;
    }

    public boolean isTestCat() {
        return testCat;
    }

    public void setTestCat(boolean testCat) {
        this.testCat = testCat;
    }

    public TChambre getChambreDetails() {
        return chambreDetails;
    }

    public void setChambreDetails(TChambre chambreDetails) {
        this.chambreDetails = chambreDetails;
    }

    public List<ChambreDto> getListChambresDto() {
        return listChambresDto;
    }

    public void setListChambresDto(List<ChambreDto> listChambresDto) {
        this.listChambresDto = listChambresDto;
    }

    public List<TChambre> getListChambreLibre() {

        return listChambreLibre = tChambreService.listChambreByEtat(EtatChambreEnum.LIBRE);
    }

    public void setListChambreLibre(List<TChambre> listChambreLibre) {
        this.listChambreLibre = listChambreLibre;
    }

    public List<TOffreTarifaire> getListTOffreTarifaire() {

        return listTOffreTarifaire;
    }

    public void setListTOffreTarifaire(List<TOffreTarifaire> listTOffreTarifaire) {
        this.listTOffreTarifaire = listTOffreTarifaire;
    }

    public String getTypeClient() {
        return typeClient;
    }

    public void setTypeClient(String typeClient) {
        this.typeClient = typeClient;
    }

    public ChambreDto getChambreDeleteDetails() {
        return chambreDeleteDetails;
    }

    public void setChambreDeleteDetails(ChambreDto chambreDeleteDetails) {
        this.chambreDeleteDetails = chambreDeleteDetails;
    }

    public void loadOffreTarifaire() {
        //System.out.println("size===sdfdsfsdfsdfsdf");
        listTOffreTarifaire = tOffreTarifaireService.listOffreTarifaireByCategorieChambre(chambreDetails.getChCategorie().getCatChambreId());

        System.out.println("size===" + listTOffreTarifaire.size());
    }

    public void deleteChoixChambre() {
        System.out.println("==============" + chambreDeleteDetails.getIdChambre());
        listChambresDto.remove(chambreDeleteDetails);

    }

    public void rechercher(ActionEvent actionEvent) {
        dtoAll = new ArrayList<InfoChambreDto>();
        findDetailsByTypeChamber();
        System.out.println("tAILLE :" + dtoAll.size());
        //tChambreService.

        //FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "ssssssssssss", null);
        //FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void findDetailsByTypeChamber() {
        List<TCategorieChambre> listCategorieChambre = tCategorieChambreService.listTCategorieChambre();

//System.out.println("xxxxxxxxxx");
//System.out.println("xxxxxxxxxx");
        for (TCategorieChambre temp : listCategorieChambre) {
            System.out.println("xxxxxxxxxx");
            InfoChambreDto dtoUtil = new InfoChambreDto();
            InfoChambreDto dto = tChambreService.findDetailsChambre(temp.getCatChambreId(), dateDebut, dateFin);
            dtoUtil.setTotalChambre(dto.getTotalChambre());
            dtoUtil.setTotalChambreLibre(dto.getTotalChambreLibre());
            dtoUtil.setTotalChambreOccupe(dto.getTotalChambreOccupe());
            dtoUtil.setTotalChambreReserve(dto.getTotalChambreReserve());
            dtoUtil.setTypeChambre(temp.getCatChambreLib());

            dtoAll.add(dtoUtil);

        }

    }

    public void changeNbNuit() {

        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, nombreNuit);
        dateFin = c.getTime();
    }

    public void changeDate() {

        long CONST_DURATION_OF_DAY = 1000l * 60 * 60 * 24;

        long date1 = dateDebut.getTime();

        long date2 = dateFin.getTime();
        // Différence
        long diff = Math.abs(date2 - date1);
        long numberOfDay = (long) diff / CONST_DURATION_OF_DAY;

        nombreNuit = (int) numberOfDay;
    }

    public void ajouterChambre(ActionEvent actionEvent) {
        System.out.println("xxxxxxxxxxxxx");
        TTarif tTarif = tTarifService.findOffreTarifaire(idCategorieChambre, chambreDetails.getChCategorie().getCatChambreId());
        TOffreTarifaire toffr = tOffreTarifaireService.findOffreTarifaire(idCategorieChambre);
        ChambreDto dto = new ChambreDto();
        dto.setCatChambre(chambreDetails.getChCategorie().getCatChambreLib());
        dto.setIdChambre(chambreDetails.getChId());
        dto.setLibChambre(chambreDetails.getChLib());
        dto.setFofChambre(toffr.getOffTitre());
        dto.setIdfofChambre(toffr.getOffreId());
        dto.setPrixFofChambre(tTarif.getTTARIF_PRIX());
        dto.setNumeroChambre(chambreDetails.getChNumeroChambre());

        listChambresDto.add(dto);

        System.out.println("xxxxxxxxxxxxx");

        //    idCategorieChambre;
    }

    public void changeCategorie() {
        System.out.println(client.getTypeClient());
        if ((typeClient).equals("INDIVIDU")) {
            testCat = true;

        } else {
            testCat = false;

        }

    }

    public void valider() {

        if (listChambresDto.size() == 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error!", "Vous devez selectioner une chambre."));

        } else {

            if (typeClient.equals(ClientEnum.INDIVIDU.toString())) {
                client.setTypeClient(ClientEnum.INDIVIDU);
            } else {
                client.setTypeClient(ClientEnum.GROUPE);
            }

            tClientService.CreerTClient(client);
            int lastId=1;
            TReservation lastReserv = tReservationService.getLastReservation();
            if(lastReserv!=null && String.valueOf(lastReserv.getResId()) !=null ){
              lastId = (int) (lastReserv.getResId() + 1);
            }else{
               lastId = 1;
            }
            
            reservation.setResClient(client);

            reservation.setDateArrive(dateDebut);
            reservation.setEtat(EtatReservationEnum.NON_CONFIRMEE);
            reservation.setDateDepart(dateFin);
            reservation.setResNumReservation("R-2017-" + lastId);
            String userName = getSecurityBean().getUserName();
            reservation.setUserCreation(userName);
            tReservationService.CreerTReservation(reservation);

            for (ChambreDto temp : listChambresDto) {

//                temp.getIdChambre();
//                temp.getIdfofTarif();
                TOffreTarifaire tchOffre = tOffreTarifaireService.findOffreTarifaire(temp.getIdfofChambre());

                TChambre tch = tChambreService.findChambreById(temp.getIdChambre());

                TChambreReservation tChambreReservation = new TChambreReservation();

                tChambreReservation.setPrix(temp.getPrixFofChambre());

                tChambreReservation.setChambre(tch);
                tChambreReservation.setDateCreate(dateCreation);
                tChambreReservation.setForfait(tchOffre);
                tChambreReservation.setReservation(reservation);
                tChambreReservation.setDateDebut(dateDebut);
                tChambreReservation.setDateFin(dateFin);

                tChambreReservationService.CreerOrUpdateTCategorieChambre(tChambreReservation);

                tch.setEtat(EtatChambreEnum.RESERVEE);
                tChambreService.CreerOrUpdate(tch);

            }

            FacesContext context = FacesContext.getCurrentInstance();

            context.addMessage(null, new FacesMessage("Success", "Votre reservation a été prise en compte"));
            clearEntity();
        }

        //    idCategorieChambre;
    }

    @PostConstruct
    public void init() {
        dateDebut = new Date();
        dateCreation = new Date();
        reservation.setResDateCreate(dateCreation);
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 7);
        dateFin = c.getTime();

        long CONST_DURATION_OF_DAY = 1000l * 60 * 60 * 24;

        long date1 = dateDebut.getTime();

        long date2 = dateFin.getTime();
        // Différence
        long diff = Math.abs(date2 - date1);
        long numberOfDay = (long) diff / CONST_DURATION_OF_DAY;

        nombreNuit = (int) numberOfDay;

        findDetailsByTypeChamber();

    }

    public void clearEntity() {
        listChambresDto = new ArrayList<ChambreDto>();
        reservation = new TReservation();
        client = new TClient();
        detailsChambres = new ArrayList<InfoChambreDto>();
        chambreDetails = new TChambre();
    }

    public SecurityBean getSecurityBean() {
        if (securityBean == null) {
            FacesContext context = FacesContext.getCurrentInstance();
            securityBean = (SecurityBean) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(context.getELContext(), null, "securityBean");
        }
        return securityBean;
    }

}
