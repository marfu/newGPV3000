/**
 *
 */
package com.nov.hotel.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author montan
 *
 */
@Entity
@Table(name = "T_FACTURE")
public class TFacture implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TFACT_ID")
    private long factId;

    @Column(name = "TFACT_DATE_CREATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date factDateCreate;

    @Column(name = "TFACT_DATE_MODIF")
    @Temporal(TemporalType.TIMESTAMP)
    private Date factDateModif;

    @Enumerated(EnumType.STRING)
    @Column(name = "ETAT", length = 10)
    private EtatFactureEnum statuId;

//    @ManyToOne
//   	@JoinColumn(name = "TFACT_CH_ID", referencedColumnName = "TCH_ID")
//    private TChambre chambre;
    @Column(name = "TFACT_USER_CREATE")
    private String userCreate;

    @Column(name = "TFACT_USER_MODIF")
    private String userModif;

    @ManyToOne
    @JoinColumn(name = "TFACT_REMISE", referencedColumnName = "TREMISE_ID")
    private TRemise remise;

    @ManyToOne
    @JoinColumn(name = "TMODE_PAIEMENT", referencedColumnName = "TModePaiment_ID")
    private TModePaiment modePaiement;
    
    
    @ManyToOne
    @JoinColumn(name = "TFACT_CLIENT", referencedColumnName = "TCLI_ID")
    private TClient client;

    @Column(name = "TFACT_NUMFACTURE")
    private String numFacture;

    @Column(name = "TFACT_NUMCHEQUE")
    private String numCheque;

    public long getFactId() {
        return factId;
    }

    public void setFactId(long factId) {
        this.factId = factId;
    }

    public Date getFactDateCreate() {
        return factDateCreate;
    }

    public void setFactDateCreate(Date factDateCreate) {
        this.factDateCreate = factDateCreate;
    }

    public Date getFactDateModif() {
        return factDateModif;
    }

    public void setFactDateModif(Date factDateModif) {
        this.factDateModif = factDateModif;
    }

    public EtatFactureEnum getStatuId() {
        return statuId;
    }

    public void setStatuId(EtatFactureEnum statuId) {
        this.statuId = statuId;
    }

    public String getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(String userCreate) {
        this.userCreate = userCreate;
    }

    public String getUserModif() {
        return userModif;
    }

    public void setUserModif(String userModif) {
        this.userModif = userModif;
    }

    public TRemise getRemise() {
        return remise;
    }

    public void setRemise(TRemise remise) {
        this.remise = remise;
    }

    public String getNumFacture() {
        return numFacture;
    }

    public void setNumFacture(String numFacture) {
        this.numFacture = numFacture;
    }

    public TModePaiment getModePaiement() {
        return modePaiement;
    }

    public void setModePaiement(TModePaiment modePaiement) {
        this.modePaiement = modePaiement;
    }

    public String getNumCheque() {
        return numCheque;
    }

    public void setNumCheque(String numCheque) {
        this.numCheque = numCheque;
    }

    public TClient getClient() {
        return client;
    }

    public void setClient(TClient client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "TFacture{" + "factId=" + factId + ", factDateCreate=" + factDateCreate + ", factDateModif=" + factDateModif + ", statuId=" + statuId + ", userCreate=" + userCreate + ", userModif=" + userModif + ", remise=" + remise + ", modePaiement=" + modePaiement + ", client=" + client + ", numFacture=" + numFacture + ", numCheque=" + numCheque + '}';
    }


}
