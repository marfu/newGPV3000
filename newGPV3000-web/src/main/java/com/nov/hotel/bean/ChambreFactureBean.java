/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.bean;

import com.nov.hotel.dto.ChambreOccupeDto;
import com.nov.hotel.entities.TChambreReservation;
import com.nov.hotel.entities.TClient;
import com.nov.hotel.entities.TFacture;
import com.nov.hotel.entities.TOccupation;
import com.nov.hotel.services.TChambreReservationService;
import com.nov.hotel.services.TClientService;
import com.nov.hotel.services.TDepositeService;
import com.nov.hotel.services.TFactureService;
import com.nov.hotel.services.TOccupationService;
import com.sun.jmx.remote.internal.ArrayQueue;
import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author marfu
 */
@Named(value = "chambreFactureBean")
@SessionScoped
public class ChambreFactureBean implements Serializable {

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
    

    private List<TOccupation> chambreOccupation;

    private ChambreOccupeDto chambreOccSelectedDto;

    private List<TOccupation> chambreOccupationSelected;

    private TOccupation chambreOccSelected;
    private TOccupation chambreOccSelected2;
    private TFacture fac = new TFacture();

    private long remise;
    private long clientId;

    private Double montantTotal = 0.0;
    private Double montantTTC = 0.0;
private Double deposit = 0.0;
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

        montantTTC = montantTotal + (montantTotal * 18 / 100);

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

       
       montantTTC = montantTotal + (montantTotal * 18 / 100);
        tOccupationService.CreerOrUpdateTOccupation(chambreOccSelectedDto.getOccupation());
        // chambreOccupeDto.add(chambreOccSelectedDto);
    }
    
    
    public void findDepository(){
    deposit=tDepositeService.findTDepositeByUser(clientId);
    }

    public void saveObject() {

    }

}
