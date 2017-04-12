/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.dto;

import java.io.Serializable;

/**
 *
 * @author manukey
 */

public class ServiceFactureDto implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private long sceId;

    private String sceNom;

    private Boolean isService;
    
    private long sceParentId;

    private double scePrix;

    private int qte;

    private double totalPrice;

    private ServiceFactureDto() {
        System.out.println("private com.nov.hotel.dto.ServiceFactureDto.<init>()");
    }

    public ServiceFactureDto(long sceId) {
        this.sceId = sceId;
        System.out.println(" com.nov.hotel.dto.ServiceFactureDto.<init>()");
    }

    
    
//
//    
//    
//    public long getSceId() {
//        return sceId;
//    }
//
//    public void setSceId(long sceId) {
//        this.sceId = sceId;
//    }
//
//    public String getSceNom() {
//        return sceNom;
//    }
//
//    public void setSceNom(String sceNom) {
//        this.sceNom = sceNom;
//    }
//
//    public Boolean getIsService() {
//        return isService;
//    }
//
//    public void setIsService(Boolean isService) {
//        this.isService = isService;
//    }
//
//    public long getSceParentId() {
//        return sceParentId;
//    }
//
//    public void setSceParentId(long sceParentId) {
//        this.sceParentId = sceParentId;
//    }
//
//    public double getScePrix() {
//        return scePrix;
//    }
//
//    public void setScePrix(double scePrix) {
//        this.scePrix = scePrix;
//    }
//
//    public int getQte() {
//         System.out.println("com.nov.hotel.entities.TService.setTotalPrice() qte : " + qte);
//        return qte;
//    }
//
//    public void setQte(int qtes) {
//        this.qte = qtes;
//      
//    }
//
//    public double getTotalPrice() {
//      
//        return totalPrice;
//    }
//
//    public void setTotalPrice(double totalPrice) {
//        this.totalPrice = totalPrice;
//    }
//    

    public long getSceId() {
        return sceId;
    }

    public void setSceId(long sceId) {
        this.sceId = sceId;
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

    public long getSceParentId() {
        return sceParentId;
    }

    public void setSceParentId(long sceParentId) {
        this.sceParentId = sceParentId;
    }

    public double getScePrix() {
        return scePrix;
    }

    public void setScePrix(double scePrix) {
        this.scePrix = scePrix;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        System.out.println("serv1.qte()" + qte);
        this.qte = qte;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrices) {
        this.totalPrice = totalPrices;
    }

    @Override
    public String toString() {
        return "ServiceFactureDto{" + "sceId=" + sceId + ", sceNom=" + sceNom + ", isService=" + isService + ", sceParentId=" + sceParentId + ", scePrix=" + scePrix + ", qte=" + qte + ", totalPrice=" + totalPrice + '}';
    }
    
    
    
    

}
