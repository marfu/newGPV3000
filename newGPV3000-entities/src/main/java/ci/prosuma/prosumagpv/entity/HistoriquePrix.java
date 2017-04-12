/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.prosuma.prosumagpv.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author tagsergi
 */
//@Entity
//@Table(name = "historique_prix")
//@NamedQueries({
//    @NamedQuery(name = "HistoriquePrix.findAll", query = "SELECT h FROM HistoriquePrix h")})
public class HistoriquePrix implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_historique_prix")
    private Integer idHistoriquePrix;
    @Basic(optional = false)
    @Column(name = "prix_vente")
    private double prixVente;
    @Basic(optional = false)
    @Column(name = "prix_revient")
    private double prixRevient;
    @Basic(optional = false)
    @Column(name = "date_creation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;
    
    @Column(name = "CODE_ARTICLE_FK")
    @org.hibernate.annotations.Index(name = "article_fk")
    private String codeAticle;
    
    @Column(name = "CODE_MAGASIN_FK")
    @org.hibernate.annotations.Index(name = "magasin_fk")
    private String codeMagasin;

    public HistoriquePrix() {
    }

    public HistoriquePrix(Integer idHistoriquePrix) {
        this.idHistoriquePrix = idHistoriquePrix;
    }

    public HistoriquePrix(Integer idHistoriquePrix, double prixVente, double prixRevient, Date dateCreation) {
        this.idHistoriquePrix = idHistoriquePrix;
        this.prixVente = prixVente;
        this.prixRevient = prixRevient;
        this.dateCreation = dateCreation;
    }

    public Integer getIdHistoriquePrix() {
        return idHistoriquePrix;
    }

    public void setIdHistoriquePrix(Integer idHistoriquePrix) {
        this.idHistoriquePrix = idHistoriquePrix;
    }

    public double getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(double prixVente) {
        this.prixVente = prixVente;
    }

    public double getPrixRevient() {
        return prixRevient;
    }

    public void setPrixRevient(double prixRevient) {
        this.prixRevient = prixRevient;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getCodeAticle() {
        return codeAticle;
    }

    public void setCodeAticle(String codeAticle) {
        this.codeAticle = codeAticle;
    }

    public String getCodeMagasin() {
        return codeMagasin;
    }

    public void setCodeMagasin(String codeMagasin) {
        this.codeMagasin = codeMagasin;
    }

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHistoriquePrix != null ? idHistoriquePrix.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistoriquePrix)) {
            return false;
        }
        HistoriquePrix other = (HistoriquePrix) object;
        if ((this.idHistoriquePrix == null && other.idHistoriquePrix != null) || (this.idHistoriquePrix != null && !this.idHistoriquePrix.equals(other.idHistoriquePrix))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.prosuma.gpv3000.entities.HistoriquePrix[ idHistoriquePrix=" + idHistoriquePrix + " ]";
    }
    
}
