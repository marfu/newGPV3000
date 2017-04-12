/**
 * 
 */
package ci.prosuma.prosumagpv.entity.stock;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


import ci.prosuma.prosumagpv.entity.PointDeVente;
import ci.prosuma.prosumagpv.entity.util.OrigineMouvement;
import javax.persistence.FetchType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * @author ASerge AYEPI
 * 
 */
//@Entity
//@Table(name = "ENTETE_MOUVEMENT")
public class EnteteMouvement implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ENTETE_MOUVEMENT_PK")
	private long id;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_MOUVEMENT",nullable = false)
	private Date dateMouvement;

	@ManyToOne(fetch = FetchType.LAZY)
        @Fetch(FetchMode.JOIN)
	@JoinColumn(name = "ORIGINE_MOUVEMENT_FK", referencedColumnName = "ORIGINE_MOUVEMENT_PK")
	private OrigineMouvement origineMouvement;

        @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "enteteMouvement")
	private List<DetailMouvement> mouvements;

	@Column(name = "OBSERVATION",nullable = false)
	private String observations;

	@Column(name = "UTILISATEUR_CREATION",nullable = false)
	private String userCreation;

	@ManyToOne(fetch = FetchType.LAZY)
        @Fetch(FetchMode.JOIN)
	@JoinColumn(name = "CODE_MAGASIN_FK", referencedColumnName = "CODE_MAGASIN_PK")
	private PointDeVente pvt;

	private String numFac;

	private long enteteInv;

	@Transient
	private float montant;

	public EnteteMouvement() {
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
		final EnteteMouvement other = (EnteteMouvement) obj;
		if (getId() != other.getId()) {
			return false;
		}
		return true;
	}

	public PointDeVente getPvt() {
		return pvt;
	}

	public void setPvt(PointDeVente pvt) {
		this.pvt = pvt;
	}

	public List<DetailMouvement> getMouvements() {
		return mouvements;
	}

	public void setMouvements(List<DetailMouvement> mouvements) {
		this.mouvements = mouvements;
	}

	public OrigineMouvement getOrigineMouvement() {
		return origineMouvement;
	}

	public void setOrigineMouvement(OrigineMouvement origineMouvement) {
		this.origineMouvement = origineMouvement;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public String getUserCreation() {
		return userCreation;
	}

	public void setUserCreation(String userCreation) {
		this.userCreation = userCreation;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDateMouvement() {
		return dateMouvement;
	}

	public void setDateMouvement(Date dateMouvement) {
		this.dateMouvement = dateMouvement;
	}

	public String getNumFac() {
		return numFac;
	}

	public void setNumFac(String numFac) {
		this.numFac = numFac;
	}

	public long getEnteteInv() {
		return enteteInv;
	}

	public void setEnteteInv(long enteteInv) {
		this.enteteInv = enteteInv;
	}

	public String myToString() {
		return "EnteteMouvement [id=" + id + ", dateMouvement=" + dateMouvement
				+ ", origineMouvement=" + origineMouvement + ", mouvements="
				+ mouvements + ", observations=" + observations
				+ ", userCreation=" + userCreation + ", pvt=" + pvt
				+ ", numFac=" + numFac + ", enteteInv=" + enteteInv + "]";
	}

	public float getMontant() {
		return montant;
	}

	public void setMontant(float montant) {
		this.montant = montant;
	}

//	@PostLoad
//	public void calculateMontant() {
//		float result = 0;
//		try {
//			for (DetailMouvement dm : getMouvements()) {
//				result += (float) (dm.getQteMvt() * dm.getArtPrixVente());
//			}
//		} catch (EntityNotFoundException e) {
//			e.printStackTrace();
//		}
//		setMontant(result);
//	}

}
