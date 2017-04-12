/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.dto;

/**
 *
 * @author marfu
 */
public class InfoChambreDto {
    
   public String typeChambre;
    public int totalChambre; 
    public int totalChambreLibre; 
    public int totalChambreOccupe; 
    public int totalChambreReserve; 
     public int totalHorsService; 

    public String getTypeChambre() {
        return typeChambre;
    }

    public void setTypeChambre(String typeChambre) {
        this.typeChambre = typeChambre;
    }

    public int getTotalChambre() {
        return totalChambre;
    }

    public void setTotalChambre(int totalChambre) {
        this.totalChambre = totalChambre;
    }

    public int getTotalChambreLibre() {
        return totalChambreLibre;
    }

    public void setTotalChambreLibre(int totalChambreLibre) {
        this.totalChambreLibre = totalChambreLibre;
    }

    public int getTotalChambreOccupe() {
        return totalChambreOccupe;
    }

    public void setTotalChambreOccupe(int totalChambreOccupe) {
        this.totalChambreOccupe = totalChambreOccupe;
    }

    public int getTotalChambreReserve() {
        return totalChambreReserve;
    }

    public void setTotalChambreReserve(int totalChambreReserve) {
        this.totalChambreReserve = totalChambreReserve;
    }

    public int getTotalHorsService() {
        return totalHorsService;
    }

    public void setTotalHorsService(int totalHorsService) {
        this.totalHorsService = totalHorsService;
    }
    
    
    
}
