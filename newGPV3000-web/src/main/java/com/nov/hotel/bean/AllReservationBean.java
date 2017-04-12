/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.bean;

import ci.prosumagpv.web.bean.security.SecurityBean;
import com.nov.hotel.dto.ChambreDto;
import com.nov.hotel.dto.DetailsChambreOccupantsDto;
import com.nov.hotel.dto.InfoChambreDto;
import com.nov.hotel.dto.OccupantDto;
import com.nov.hotel.dto.ReservationDto;
import com.nov.hotel.entities.ClientEnum;
import com.nov.hotel.entities.EtatChambreEnum;
import com.nov.hotel.entities.EtatFactureEnum;
import com.nov.hotel.entities.EtatReservationEnum;
import com.nov.hotel.entities.TChambre;
import com.nov.hotel.entities.TChambreReservation;
import com.nov.hotel.entities.TClient;
import com.nov.hotel.entities.TDetailFacture;
import com.nov.hotel.entities.TFacture;
import com.nov.hotel.entities.TOccupation;
import com.nov.hotel.entities.TOffreTarifaire;
import com.nov.hotel.entities.TReservation;
import com.nov.hotel.entities.TService;
import com.nov.hotel.entities.TTarif;
import com.nov.hotel.services.TCategorieChambreService;
import com.nov.hotel.services.TChambreReservationService;
import com.nov.hotel.services.TChambreService;
import com.nov.hotel.services.TClientService;
import com.nov.hotel.services.TDetailFactureService;
import com.nov.hotel.services.TFactureService;
import com.nov.hotel.services.TOccupationService;
import com.nov.hotel.services.TOffreTarifaireService;
import com.nov.hotel.services.TReservationService;
import com.nov.hotel.services.TServiceService;
import com.nov.hotel.services.TTarifService;
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
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import org.primefaces.event.TabChangeEvent;

/**
 *
 * @author marfu
 */
@Named(value = "allReservationBean")
@SessionScoped
public class AllReservationBean implements Serializable {

    private long idCategorieChambre;
    @EJB
    private TReservationService tReservationService;

    @EJB
    private TClientService tClientService;

    @EJB
    private TChambreService tChambreService;

    @EJB
    private TChambreReservationService tChambreReservationService;

    @EJB
    private TTarifService tTarifService;

    @EJB
    private TOffreTarifaireService tOffreTarifaireService;

    @EJB
    private TFactureService tFactureService;

    @EJB
    private TOccupationService tOccupationService;

    @EJB
    private TDetailFactureService tDetailFactureService;

    @EJB
    private TServiceService tServiceService;
    
    private List<TChambre> listChambreLibre;

    private TClient clientOcc = new TClient();

    private List<ReservationDto> lisReservationDto;

    private List<OccupantDto> occupantDto = new ArrayList<>();

    private List<TOffreTarifaire> listTOffreTarifaire;
    private TReservation reservation;
    private TReservation reservationSelected;
    private Date fin;
    private TChambre chambreDetails = new TChambre();
    private Date debut;
    private List<TChambreReservation> tHambreResev;
    private String typePiece;
    private ChambreDto chambreDeleteDetails;

    @ManagedProperty(value = "#{securityBean}")
    private SecurityBean securityBean;

    private List<ChambreDto> listChambresDto = new ArrayList<>();

    private List<DetailsChambreOccupantsDto> detailsChambreOccupantsDto = new ArrayList<>();

    private DetailsChambreOccupantsDto detailsChDto = new DetailsChambreOccupantsDto();

    public long getIdCategorieChambre() {
        return idCategorieChambre;
    }

    public void setIdCategorieChambre(long idCategorieChambre) {
        this.idCategorieChambre = idCategorieChambre;
    }

    public TChambre getChambreDetails() {
        return chambreDetails;
    }

    public void setChambreDetails(TChambre chambreDetails) {
        this.chambreDetails = chambreDetails;
    }

    public List<ReservationDto> getLisReservationDto() {
        setLisReservationDto(tReservationService.getAllReservation());
        return lisReservationDto;
    }

    public void setLisReservationDto(List<ReservationDto> lisReservationDto) {
        this.lisReservationDto = lisReservationDto;
    }

    public TReservation getReservation() {
        return reservation;
    }

    public void setReservation(TReservation reservation) {
        this.reservation = reservation;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public Date getDebut() {
        return debut;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public void onTabChange(TabChangeEvent event) {
        lisReservationDto = tReservationService.getAllReservation();
    }

    public List<ChambreDto> getListChambresDto() {
        return listChambresDto;
    }

    public void setListChambresDto(List<ChambreDto> listChambresDto) {
        this.listChambresDto = listChambresDto;
    }

    public void deleteChoixChambre() {
        System.out.println("==============" + chambreDeleteDetails.getIdChambre());
        listChambresDto.remove(chambreDeleteDetails);

    }

    public ChambreDto getChambreDeleteDetails() {
        return chambreDeleteDetails;
    }

    public void setChambreDeleteDetails(ChambreDto chambreDeleteDetails) {
        this.chambreDeleteDetails = chambreDeleteDetails;
    }

    public List<TOffreTarifaire> getListTOffreTarifaire() {
        return listTOffreTarifaire;
    }

    public void setListTOffreTarifaire(List<TOffreTarifaire> listTOffreTarifaire) {
        this.listTOffreTarifaire = listTOffreTarifaire;
    }

    public TClient getClientOcc() {
        return clientOcc;
    }

    public void setClientOcc(TClient clientOcc) {
        this.clientOcc = clientOcc;
    }

    public String getTypePiece() {
        return typePiece;
    }

    public void setTypePiece(String typePiece) {
        this.typePiece = typePiece;
    }

    public List<DetailsChambreOccupantsDto> getDetailsChambreOccupantsDto() {
        return detailsChambreOccupantsDto;
    }

    public void setDetailsChambreOccupantsDto(List<DetailsChambreOccupantsDto> detailsChambreOccupantsDto) {
        this.detailsChambreOccupantsDto = detailsChambreOccupantsDto;
    }

    public DetailsChambreOccupantsDto getDetailsChDto() {
        return detailsChDto;
    }

    public void setDetailsChDto(DetailsChambreOccupantsDto detailsChDto) {
        this.detailsChDto = detailsChDto;
    }

    public List<TChambre> getListChambreLibre() {
        return listChambreLibre= tChambreService.listChambreByEtat(EtatChambreEnum.LIBRE);
    }

    public void setListChambreLibre(List<TChambre> listChambreLibre) {
        this.listChambreLibre = listChambreLibre;
    }

    
    
    public void detailReservation() {
        occupantDto = new ArrayList<>();

        System.out.println("xxxxxxxxxxxxxokxxxxxxxx");
        detailsChambreOccupantsDto = new ArrayList<>();

        detailsChambreOccupantsDto = new ArrayList<>();
        listChambresDto = new ArrayList<>();
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("reservationId");
        long idRes = Long.valueOf(id);

        reservation = tReservationService.findById(idRes);
        debut = reservation.getDateArrive();
        fin = reservation.getDateDepart();

        tHambreResev = tChambreReservationService.listOffreTarifaireByCategorie(idRes);
        // tHambreResev = tChambreReservationService.listOffreTarifaireByCategorie(idRes);
        System.out.println("xxxxxxxxxxxxxokxxxxxxxxxxx");

        String userName = getSecurityBean().getUserName();

        for (TChambreReservation str : tHambreResev) {

            DetailsChambreOccupantsDto detailsDto = new DetailsChambreOccupantsDto();

            ChambreDto dto = new ChambreDto();

            dto.setCatChambre(str.getChambre().getChCategorie().getCatChambreLib());
            dto.setIdChambre(str.getChambre().getChId());
            dto.setLibChambre(str.getChambre().getChLib());
            dto.setFofChambre(str.getForfait().getOffTitre());
            dto.setIdfofChambre(str.getForfait().getOffreId());
            dto.setPrixFofChambre(str.getPrix());
            dto.setNumeroChambre(str.getChambre().getChNumeroChambre());

            detailsDto.setCatChambre(str.getChambre().getChCategorie().getCatChambreLib());
            detailsDto.setIdChambre(str.getChambre().getChId());
            detailsDto.setLibChambre(str.getChambre().getChLib());
            detailsDto.setFofChambre(str.getForfait().getOffTitre());
            detailsDto.setIdfofChambre(str.getForfait().getOffreId());
            detailsDto.setPrixFofChambre(str.getPrix());
            detailsDto.setNumeroChambre(str.getChambre().getChNumeroChambre());

            detailsChambreOccupantsDto.add(detailsDto);

            listChambresDto.add(dto);

        }

    }

    public void deleteChambre(long idCHAM) {
        System.out.println("xxxxxxxxxxxxxokxxxxxxxx" + idCHAM);

        //String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("chId");
        // long idCh = Long.valueOf(id);
        //reservationSelected.getResId();
        // TChambreReservation tChambreRese = tChambreReservationService.findChambreReser(reservationSelected.getResId(), idCHAM);
        TChambre c = tChambreService.findChambreById(idCHAM);
        c.setEtat(EtatChambreEnum.LIBRE);
        tChambreService.CreerOrUpdate(c);
        tChambreReservationService.delete(reservationSelected.getResId(), idCHAM);

        tHambreResev = tChambreReservationService.listOffreTarifaireByCategorie(reservationSelected.getResId());
        System.out.println("xxxxxxxxxxxxxokxxxxxxxxxxx");

        String userName = getSecurityBean().getUserName();
        listChambresDto = new ArrayList<>();
        for (TChambreReservation str : tHambreResev) {

            ChambreDto dto = new ChambreDto();
            dto.setCatChambre(str.getChambre().getChCategorie().getCatChambreLib());
            dto.setIdChambre(str.getChambre().getChId());
            dto.setLibChambre(str.getChambre().getChLib());
            dto.setFofChambre(str.getForfait().getOffTitre());
            dto.setIdfofChambre(str.getForfait().getOffreId());
            dto.setPrixFofChambre(str.getPrix());
            dto.setNumeroChambre(str.getChambre().getChNumeroChambre());

            listChambresDto.add(dto);

        }

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

    public void loadOffreTarifaire() {
        //System.out.println("size===sdfdsfsdfsdfsdf");
        listTOffreTarifaire = tOffreTarifaireService.listOffreTarifaireByCategorieChambre(chambreDetails.getChCategorie().getCatChambreId());

        System.out.println("size===" + listTOffreTarifaire.size());
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
 listChambresDto.remove(dto);
        listChambresDto.add(dto);

        System.out.println("xxxxxxxxxxxxx");

        //    idCategorieChambre;
    }

    public void ajouterOccupant(ActionEvent actionEvent) {

        OccupantDto dto = new OccupantDto();
        dto.setChambre(detailsChDto);
        dto.setClient(clientOcc);

        System.out.println(detailsChDto.getCatChambre());

        DetailsChambreOccupantsDto tempDTO = new DetailsChambreOccupantsDto();
//
        tempDTO.setCatChambre(detailsChDto.getCatChambre());
        tempDTO.setIdChambre(detailsChDto.getIdChambre());
        tempDTO.setLibChambre(detailsChDto.getLibChambre());
        tempDTO.setFofChambre(detailsChDto.getFofChambre());
        tempDTO.setIdfofChambre(detailsChDto.getIdfofChambre());
        tempDTO.setPrixFofChambre(detailsChDto.getPrixFofChambre());
        tempDTO.setNumeroChambre(detailsChDto.getNumeroChambre());
        tempDTO.setOccupant(clientOcc.getCliNom() + " " + clientOcc.getCliPrenom());

        detailsChambreOccupantsDto.remove(detailsChDto);
        detailsChambreOccupantsDto.add(tempDTO);

        occupantDto.add(dto);

        System.out.println(occupantDto.size());

        //    idCategorieChambre;
    }

    public void valider() {

        if (listChambresDto.size() == 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error!", "Vous devez selectioner une chambre."));

        } else {

            TReservation lastReserv = tReservationService.getLastReservation();
            int lastId = (int) (lastReserv.getResId() + 1);

            String userName = getSecurityBean().getUserName();

            reservation.setDateArrive(debut);
            reservation.setDateDepart(fin);
            reservation.setResNumReservation("R-2017-" + lastId);
            reservation.setResDateModif(new Date());
            reservation.setUserModif(userName);
            tReservationService.CreerTReservation(reservation);

            for (ChambreDto temp : listChambresDto) {

//                temp.getIdChambre();
//                temp.getIdfofTarif();
                TOffreTarifaire tchOffre = tOffreTarifaireService.findOffreTarifaire(temp.getIdfofChambre());

                TChambre tch = tChambreService.findChambreById(temp.getIdChambre());

                TChambreReservation tChambreReservation = new TChambreReservation();

                tChambreReservation.setPrix(temp.getPrixFofChambre());

                tChambreReservation.setChambre(tch);

                tChambreReservation.setForfait(tchOffre);
                tChambreReservation.setReservation(reservation);
                tChambreReservation.setDateDebut(debut);
                tChambreReservation.setDateFin(fin);

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

    public String annulerReservation(long id) {
        reservation = tReservationService.findById(id);

        tHambreResev = tChambreReservationService.listOffreTarifaireByCategorie(id);
        // tHambreResev = tChambreReservationService.listOffreTarifaireByCategorie(idRes);

        String userName = getSecurityBean().getUserName();

        for (TChambreReservation str : tHambreResev) {

            TChambre tempChambre = str.getChambre();

            tempChambre.setEtat(EtatChambreEnum.LIBRE);
            tChambreService.CreerOrUpdate(tempChambre);

        }
        reservation.setUserCrloture(userName);
        
        reservation.setResDateModif(new Date());
        reservation.setEtat(EtatReservationEnum.ANNULEE);
        tReservationService.CreerTReservation(reservation);
        return "success";
    }

    public String confirmerReservation() {

        boolean testOccupantChambre = false;
        System.out.println("tesss" + detailsChambreOccupantsDto.size());
        detailsChambreOccupantsDto.size();
        for (DetailsChambreOccupantsDto strVerif : detailsChambreOccupantsDto) {
            System.out.println("chambre" + strVerif.getOccupant());

            if (strVerif.getOccupant() == null) {
                testOccupantChambre = true;
            }

        }

        if (testOccupantChambre == true) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Votre traitement n'a pas pu être traité ! Veuillez SVP renseigner un occupant par chambre choisie."));
            return "";
        } else {
            TFacture fac = new TFacture();
            fac.setFactDateCreate(new Date());

            //tFactureService.
            String userName = getSecurityBean().getUserName();

            fac.setStatuId(EtatFactureEnum.ENCOURS);
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
           // fac = tFactureService.CreerTFacture(fac);

            for (OccupantDto str : occupantDto) {

                TDetailFacture detailsFac = new TDetailFacture();
               // detailsFac.setFacture(fac);
                detailsFac.setUserModif(userName);
                detailsFac.setDfactQte(1);
                detailsFac.setDfactDateCreate(new Date());
                detailsFac.setDfactPrix(idCategorieChambre);
                TService tS = tServiceService.findByLibService("HEBERGEMENT");
                TService tSConso = tServiceService.findByLibService("CHAMBRE");
                detailsFac.setService(tS);
                detailsFac.setServiceConsoId(tSConso);

                //tDetailFactureService.CreerTDetailFacture(detailsFac);

                str.getChambre().getIdChambre();

                TChambre tchambre = tChambreService.findChambreById(str.getChambre().getIdChambre());

                TOccupation u = new TOccupation();
                TClient tCli = tClientService.CreerTClient(str.getClient());
                u.setClient(tCli);
                u.setChambre(tchambre);
                u.setOccDateCreate(new Date());
                u.setOccDateDep(debut);
                u.setOccDateArr(fin);
               // u.setFactureId(fac.getFactId());
                u.setNumReservation(reservation.getResNumReservation());

                tOccupationService.CreerTOccupation(u);

                tchambre.setEtat(EtatChambreEnum.OCCUPEE);
                tChambreService.CreerOrUpdate(tchambre);

            }
            reservation.setUserConfir(userName);
            reservation.setEtat(EtatReservationEnum.CONFIRMEE);
            tReservationService.CreerTReservation(reservation);

            FacesContext context = FacesContext.getCurrentInstance();

            context.addMessage(null, new FacesMessage("Success", "Votre reservation a été confirmée !"));
            return "";
        }
//        

        //    idCategorieChambre;
    }

    public void clearEntity() {
        listChambresDto = new ArrayList<ChambreDto>();
        reservation = new TReservation();

        chambreDetails = new TChambre();
    }
    
    
    public void loadChambreLibre() {
         
         
         System.out.println("xxxxxxxxx===========");
         listChambreLibre = tChambreService.listChambreByEtat(EtatChambreEnum.LIBRE);
          System.out.println("xxxxxxxxx==========="+ listChambreLibre.size());
       RequestContext context =RequestContext.getCurrentInstance();
         context.execute("PF('carDialog').show();");
    }

}
