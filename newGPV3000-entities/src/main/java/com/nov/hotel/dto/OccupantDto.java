/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.dto;

import com.nov.hotel.entities.TChambre;
import com.nov.hotel.entities.TClient;

/**
 *
 * @author marfu
 */
public class OccupantDto {
    
    private TClient client;
    private DetailsChambreOccupantsDto chambre;

    public TClient getClient() {
        return client;
    }

    public void setClient(TClient client) {
        this.client = client;
    }

    public DetailsChambreOccupantsDto getChambre() {
        return chambre;
    }

    public void setChambre(DetailsChambreOccupantsDto chambre) {
        this.chambre = chambre;
    }

   
    
    
    
}
