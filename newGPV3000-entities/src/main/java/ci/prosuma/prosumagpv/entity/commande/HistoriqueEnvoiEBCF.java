/**
 *
 */
package ci.prosuma.prosumagpv.entity.commande;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ci.prosuma.prosumagpv.entity.PointDeVente;
import ci.prosuma.prosumagpv.entity.util.EnumerationParam.EtatTransmission;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;

/**
 * @author AKHDAR Zoul
 *
 */
//@Entity
//@Table(name = "HISTORIQUE_EBCF")
public class HistoriqueEnvoiEBCF implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "HISTO_EBCF_PK")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "OBSERVATION", nullable = false)
    private String observation;

    @ManyToOne
    @JoinColumn(name = "CODE_MAGASIN_FK", referencedColumnName = "CODE_MAGASIN_PK")
    private PointDeVente pvt;

    @Enumerated(EnumType.STRING)
    private EtatTransmission etatTransmission;

    @Column(name = "DATE_TRANSMITION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTransmission;

    @ElementCollection
    @CollectionTable(name = "lien_historique_bcf")
    private List<String> listEntete;

    public HistoriqueEnvoiEBCF() {
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + (int) getId();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final HistoriqueEnvoiEBCF other = (HistoriqueEnvoiEBCF) obj;
        if (getId() != other.getId()) {
            return false;
        }
        return true;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public PointDeVente getPvt() {
        return pvt;
    }

    public void setPvt(PointDeVente pvt) {
        this.pvt = pvt;
    }

    public Date getDateTransmission() {
        return dateTransmission;
    }

    public void setDateTransmission(Date dateTransmission) {
        this.dateTransmission = dateTransmission;
    }

    public List<String> getListEntete() {
        return listEntete;
    }

    public void setListEntete(List<String> listEntete) {
        this.listEntete = listEntete;
    }
    
    public EtatTransmission getEtatTransmission() {
        return etatTransmission;
    }

    public void setEtatTransmission(EtatTransmission etatTransmission) {
        this.etatTransmission = etatTransmission;
    }
    /*
	@Override
	public String toString() {
		return "HistoriqueEnvoiEBCF [id=" + id + ", observation=" + observation
				+ ", pvt=" + pvt + ", etatTransmission=" + etatTransmission
				+ ", dateTransmission=" + dateTransmission + ", listEntete="
				+ listEntete + "]";
	}

     */

}
