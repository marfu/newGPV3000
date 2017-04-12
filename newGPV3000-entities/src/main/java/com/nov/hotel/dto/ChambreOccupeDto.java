/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.dto;

import com.nov.hotel.entities.TChambreReservation;
import com.nov.hotel.entities.TOccupation;

/**
 *
 * @author marfu
 */
public class ChambreOccupeDto {

    private TOccupation occupation;

    private TChambreReservation chambreOccupation;

    private int nbreNuits;
    private Double prix;
    private Double total;

    public TOccupation getOccupation() {
        return occupation;
    }

    public void setOccupation(TOccupation occupation) {
        this.occupation = occupation;
    }

    public TChambreReservation getChambreOccupation() {
        return chambreOccupation;
    }

    public void setChambreOccupation(TChambreReservation chambreOccupation) {
        this.chambreOccupation = chambreOccupation;
    }

    public int getNbreNuits() {
        return nbreNuits;
    }

    public void setNbreNuits(int nbreNuits) {
        this.nbreNuits = nbreNuits;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

   
    
    
    
}
