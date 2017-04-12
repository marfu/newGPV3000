/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.prosuma.prosumagpv.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

/**
 *
 * @author serge
 */
//@Entity
//@Table(name = "gencode_inconnu")
//@NamedQueries({
//    @NamedQuery(name = "GencodeInconnu.findAll", query = "SELECT g FROM GencodeInconnu g")})
public class GencodeInconnu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "gencode_inconnu_pk")
    private Long gencodeInconnuPk;
    
    @Basic(optional = false)
    @Index(name = "inconnu_gencode")
    private String gencode;
    
    @Basic(optional = false)
    @Column(name = "magasin_fk")
    @Index(name = "inconnu_magasin_fk")
    private String magasinFk;

    public GencodeInconnu() {
    }

    public GencodeInconnu(Long gencodeInconnuPk) {
        this.gencodeInconnuPk = gencodeInconnuPk;
    }

    public GencodeInconnu(Long gencodeInconnuPk, String gencode, long idVenteCaisse, String magasinFk) {
        this.gencodeInconnuPk = gencodeInconnuPk;
        this.gencode = gencode;
        this.magasinFk = magasinFk;
    }

    public Long getGencodeInconnuPk() {
        return gencodeInconnuPk;
    }

    public void setGencodeInconnuPk(Long gencodeInconnuPk) {
        this.gencodeInconnuPk = gencodeInconnuPk;
    }

    public String getGencode() {
        return gencode;
    }

    public void setGencode(String gencode) {
        this.gencode = gencode;
    }

    public String getMagasinFk() {
        return magasinFk;
    }

    public void setMagasinFk(String magasinFk) {
        this.magasinFk = magasinFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gencodeInconnuPk != null ? gencodeInconnuPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GencodeInconnu)) {
            return false;
        }
        GencodeInconnu other = (GencodeInconnu) object;
        if ((this.gencodeInconnuPk == null && other.gencodeInconnuPk != null) || (this.gencodeInconnuPk != null && !this.gencodeInconnuPk.equals(other.gencodeInconnuPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ci.prosuma.GencodeInconnu[ gencodeInconnuPk=" + gencodeInconnuPk + " ]";
    }
    
}
