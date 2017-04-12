/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.prosuma.prosumagpv.entity.dto;


/**
 *
 * @author tagsergi
 */
public class GenCodeDTO {

    private long id;

    private String code;

    private int prixVenteTTCEnCours;

    private String catGen;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getPrixVenteTTCEnCours() {
        return prixVenteTTCEnCours;
    }

    public void setPrixVenteTTCEnCours(int prixVenteTTCEnCours) {
        this.prixVenteTTCEnCours = prixVenteTTCEnCours;
    }

    public String getCatGen() {
        return catGen;
    }

    public void setCatGen(String catGen) {
        this.catGen = catGen;
    }


}
