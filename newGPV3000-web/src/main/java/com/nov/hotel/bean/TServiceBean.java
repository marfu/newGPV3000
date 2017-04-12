/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.bean;

import com.nov.hotel.dto.ServiceFactureDto;
import com.nov.hotel.entities.EtatFactureEnum;
import com.nov.hotel.entities.TChambre;
import com.nov.hotel.entities.TDetailFacture;
import com.nov.hotel.entities.TFacture;
import com.nov.hotel.entities.TRemise;
import com.nov.hotel.entities.TService;
import com.nov.hotel.services.TChambreService;
import com.nov.hotel.services.TDetailFactureService;
import com.nov.hotel.services.TFactureService;
import com.nov.hotel.services.TRemiseService;
import com.nov.hotel.services.TServiceService;
import static com.sun.javafx.logging.PulseLogger.addMessage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.inject.Named;

/**
 *
 * @author manukey
 */
@Named(value = "serviceBean")
@SessionScoped
public class TServiceBean implements Serializable {

    @EJB
    private TServiceService tServiceService;
    @EJB
    private TRemiseService tRemiseService;
    @EJB
    private TFactureService tfactureService;
    @EJB
    private TDetailFactureService tdetailFactureService;
    @EJB
    private TChambreService tchambreService;

    // private TService tservice;
    private long idservice ;

    private String sceNom;

    private Boolean isService;

    private Date sceDateCreate;

    private Date sceDateModif;

    private TService sceParentId;
    private TService tservice = new TService();

    private ServiceFactureDto selectedService;
    private final List<ServiceFactureDto> selectedServiceList;

    private double scePrix;
    private double prixTot;
//    private int qte;
//    private double scePrixCalcule;

    private List<TService> listTService;
    private List<TService> listTServiceParent;
    private List<TService> listTServiceIntermediaire  ;
    private List<TService> listAllTServiceIntermediaire  ;
//    private List<TService> listTServiceIntermediaire  = new ArrayList<>();
    private List<ServiceFactureDto> listTServiceTerminal;
    private List<ServiceFactureDto> ldto = new ArrayList<>();
    private long chambreid;

    //private ServiceFactureDto dto;
    private long idtest;
    private HashMap<Long, TService> hashMapService;

    public TServiceBean() {
        System.out.println("com.nov.hotel.Beans.TServiceBean.<init>()");
        selectedServiceList = new ArrayList<>();
    }

    public long getIdtest() {

        return idtest;
    }

    public void setIdtest(long idtest) {
        this.idtest = idtest;
        System.out.println("getIdtest  ");

        List<ServiceFactureDto> result = new ArrayList<>();
        List<TService> dbList = tServiceService.listTServiceTerminal(idtest);
        dbList.stream().forEach((serv) -> {
            result.add(generateFactureDTOByService(serv));
        });

        setListTServiceTerminal(result);
    }

    public long getChambreid() {
        return chambreid;
    }

    public void setChambreid(long chambreid) {
        this.chambreid = chambreid;
    }

    public List<ServiceFactureDto> getListTServiceTerminal() {
        System.out.println("getListTServiceTerminal");
        return listTServiceTerminal;
    }

    public void setListTServiceTerminal(List<ServiceFactureDto> listTServiceTerminal) {
        this.listTServiceTerminal = listTServiceTerminal;
    }

    public List<ServiceFactureDto> getLdto() {
        return ldto;
    }

    public void setLdto(List<ServiceFactureDto> ldto) {
        this.ldto = ldto;
    }

    public long getIdservice() {
        return idservice;
    }

    public void setIdservice(long idservice) {
        this.idservice = idservice;
    }

    public List<TService> getListTService() {
        listTService = tServiceService.listTService();
        return listTService;
    }

    public void setListTService(List<TService> listTService) {
        this.listTService = listTService;
    }

    public String getSceNom() {
        return sceNom;
    }

    public void setSceNom(String sceNom) {
        this.sceNom = sceNom;
    }

    public Boolean getIsService() {
        return isService;
    }

    public void setIsService(Boolean isService) {
        this.isService = isService;
    }

    public Date getSceDateCreate() {
        return sceDateCreate;
    }

    public void setSceDateCreate(Date sceDateCreate) {
        this.sceDateCreate = sceDateCreate;
    }

    public Date getSceDateModif() {
        return sceDateModif;
    }

    public void setSceDateModif(Date sceDateModif) {
        this.sceDateModif = sceDateModif;
    }

    public TService getSceParentId() {
        return sceParentId;
    }

    public void setSceParentId(TService sceParentId) {
        this.sceParentId = sceParentId;
    }

    public double getScePrix() {
        return scePrix;
    }

    public void setScePrix(double scePrix) {
        this.scePrix = scePrix;
    }

    public List<TService> getListTServiceParent() {
        listTServiceParent = tServiceService.listTServiceParent();
        return listTServiceParent;
    }

    public void setListTServiceParent(List<TService> listTServiceParent) {
        this.listTServiceParent = listTServiceParent;
    }

    public List<TService> getListTServiceIntermediaire() {

        return listTServiceIntermediaire;
    }

    public void setListTServiceIntermediaire(List<TService> listTServiceIntermediaire) {
        this.listTServiceIntermediaire = listTServiceIntermediaire;
    }

    public HashMap<Long, TService> getHashMapService() {
        return hashMapService;
    }

    public void setHashMapService(HashMap<Long, TService> hashMapService) {
        this.hashMapService = hashMapService;
    }

    public double getPrixTot() {
        prixTot = calculPrixTotal();
        return prixTot;
    }

    public void setPrixTot(double prixTot) {
        this.prixTot = prixTot;
    }

    public TService getTservice() {
        return tservice;
    }

    public void setTservice(TService tservice) {
        this.tservice = tservice;
    }

    public TDetailFactureService getTdetailFactureService() {
        return tdetailFactureService;
    }

    public void setTdetailFactureService(TDetailFactureService tdetailFactureService) {
        this.tdetailFactureService = tdetailFactureService;
    }

    public List<TService> getListAllTServiceIntermediaire() {
        listAllTServiceIntermediaire=tServiceService.listAllTServiceIntermediaire();
        return listAllTServiceIntermediaire;
    }

    public void setListAllTServiceIntermediaire(List<TService> listAllTServiceIntermediaire) {
        this.listAllTServiceIntermediaire = listAllTServiceIntermediaire;
    }

    
    
    
    
    // fonction et methode
    public String creerTService() {
        Date today = new Date();
        

        if (idservice != 0) {
            TService tserviceparent = new TService();
            tserviceparent = tServiceService.findByIDService(idservice);
            sceParentId = tserviceparent;
            tservice.setSceParentId(tserviceparent);
        }
        if (tservice.getSceId() != 0) {
            tservice.setSceDateModif(today);
        } else {
            tservice.setSceDateCreate(today);
        }


        tServiceService.CreerOrUpdate(tservice);
        clearEntity();
        return "success";
    }

    public void supprimerTService() {

    }

    public void calcul() {

        for (ServiceFactureDto serv : listTServiceTerminal) {
            ServiceFactureDto dto = new ServiceFactureDto(serv.getSceId());
            if (serv.getQte() != 0) {
                double p = serv.getScePrix();

                int q = serv.getQte();
                double tp = p * q;
                dto.setQte(serv.getQte());
                dto.setSceNom(serv.getSceNom());
                dto.setSceParentId(serv.getSceParentId());
                dto.setScePrix(serv.getScePrix());
                dto.setTotalPrice(tp);
                ldto.add(serv);
                System.out.println("q " + q);
                System.out.println("p " + p);
                System.out.println("tp " + tp);

            }

        }
        for (ServiceFactureDto serv1 : ldto) {
//            System.out.println("serv1.getSceNom()" + serv1.getSceNom());
//            System.out.println("serv1.getTotalPrice()" + serv1.getTotalPrice());
            System.out.println("serv1.getSI" + serv1.getSceId());
            System.out.println("serv1.getQte()" + serv1.getQte());
            System.out.println("----------------------------------------------------------------------------------------");

        }

    }

    public void onblurvalue(ServiceFactureDto serv) {

        System.out.println("onblurvalue !   : " + serv.toString());

        ServiceFactureDto dto = new ServiceFactureDto(serv.getSceId());
        double p = serv.getScePrix();

        int q = serv.getQte();
        double tp = p * q;
        dto.setQte(serv.getQte());
        dto.setSceNom(serv.getSceNom());
        dto.setSceParentId(serv.getSceParentId());
        dto.setScePrix(serv.getScePrix());
        dto.setTotalPrice(tp);
        selectedServiceList.add(dto);

        System.out.println("selectedServiceList !   : " + selectedServiceList.toString());

    }

    public void calculByID(ServiceFactureDto ids) {
        String qte = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("qte");

        System.out.println("@@@@@@@@@@@@@@@@@@@@@@   qte   :    " + qte);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@   listTServiceTerminal.size()   :    " + selectedServiceList.size());
        if (selectedServiceList.contains(ids)) {
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@   ids   :    " + selectedServiceList.get(selectedServiceList.lastIndexOf(ids)));
        }
        if (ids != null) {
            List<ServiceFactureDto> tabToRemove = new ArrayList<>();
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@   ids   :    " + ids.toString());
            for (ServiceFactureDto search : selectedServiceList) {
                if (search.getSceId() == ids.getSceId()) {
                    ldto.add(search);
                    tabToRemove.add(search);
                }
            }
            selectedServiceList.removeAll(tabToRemove);

        }

    }

    public double calculPrixTotal() {
        double ptotal = 0;
        if (ldto != null) {
            for (ServiceFactureDto serv : ldto) {
                ptotal = ptotal + serv.getTotalPrice();
            }

        }
        System.out.println("ptotal      " + ptotal);
        return ptotal;
    }

    public String modifierTService() {

        //
        return "success";
    }

    private ServiceFactureDto generateFactureDTOByService(TService ser) {
        ServiceFactureDto fact = new ServiceFactureDto(ser.getSceId());
        fact.setIsService(ser.getIsService());

        fact.setSceId(ser.getSceId());
        fact.setSceNom(ser.getSceNom());
        fact.setSceParentId(ser.getSceParentId().getSceId());
        fact.setScePrix(ser.getScePrix());
        return fact;

    }

    public void buttonAction(ActionEvent actionEvent) {
        addMessage("Welcome to Primefaces!!");
    }

    public String detailEBCF(ActionEvent actionEvent) {
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("parentId").trim();
        if (id != null && !id.trim().equals("")) {
            System.out.println("IDDD : " + id);
            try {

                listTServiceIntermediaire = tServiceService.listTServiceIntermediaire(Long.parseLong(id));

                for (TService serv : listTServiceIntermediaire) {
                    System.out.println(serv.getSceNom());
                                   System.out.println("serv : " + serv.getSceNom());
                }
            } catch (Exception e) {

                return "";
            }

            if (!listTServiceIntermediaire.isEmpty() && listTServiceIntermediaire.size() > 0) {
//				
                return "serviceInterSearch";
            }

            return "";

        } else {
            System.out.println("IDDDgfgfhghhhghghghgh : " + id);
            return "";
        }
    }

    public void onTabChange(long ID) {
        setIdtest(ID);
        idtest = ID;
        getIdtest();

    }

    public ServiceFactureDto getSelectedService() {
        return selectedService;
    }

    public void setSelectedService(ServiceFactureDto selectedService) {
        this.selectedService = selectedService;
    }

    public void creerFacture() {
        TFacture factureVar = new TFacture();
        Date today = new Date();
        TRemise tremise = new TRemise();
        TChambre tchambre = new TChambre();
        TFacture tfacture = new TFacture();
        TService serviceConso = new TService();
        TService serviceParent = new TService();
        TDetailFacture tdFature = new TDetailFacture();
        if (chambreid == 0) {
            tremise = tRemiseService.findRemiseTauxzero();
            factureVar.setFactDateCreate(today);
            factureVar.setStatuId(EtatFactureEnum.ENCOURS);
            factureVar.setRemise(tremise);
            tfacture = tfactureService.CreerTFacture(factureVar);

        } else {
            tchambre = tchambreService.finbyIDChambre(chambreid);
            factureVar = tfactureService.findTFactureByChambre(tchambre);
            factureVar.setFactDateModif(today);
            tfacture = tfactureService.UpdateTFacture(factureVar);

        }
        if (ldto != null) {
            for (ServiceFactureDto serv : ldto) {
                serviceParent = tServiceService.findByIDService(serv.getSceParentId());
                serviceConso = tServiceService.findByIDService(serv.getSceId());
                TDetailFacture tdetailFacture = new TDetailFacture();
                tdetailFacture.setFacture(tfacture);
                tdetailFacture.setdFactDateCreate(today);
                tdetailFacture.setdFactQte(serv.getQte());
                tdetailFacture.setdFactPrix(serv.getScePrix());
                tdetailFacture.setService(serviceParent);
                tdetailFacture.setServiceConsoId(serviceConso);
                tdFature = tdetailFactureService.CreerTDetailFacture(tdetailFacture);
                System.out.println("tdFature" + tdFature.getFactId());

            }
            ldto.clear();

        }

    }

    public void detailService(ActionEvent actionEvent) {

        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("userIds");

        long ids = Long.valueOf(id);
        if (id != null && !id.trim().equals("")) {

            tservice = tServiceService.findByIDService(ids);

        } else {
            //  return "";
        }
    }
    public void clearEntity() {
        tservice = new TService();
    }

}