package ci.prosuma.prosumagpv.entity.dto;
/** PROPHYL.COM */
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;

@SuppressWarnings("serial")
@Entity
@Table(name = "stat_dem")
public class StatDem implements Serializable {

	//@Id
	//@Column(nullable = false, unique = true)
	//private String codeArticle;

	@Id
	@Column(name = "stat_dem_pk")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
	private String codeArticle;

	
	private String designation;

	private long codeGisement;

	private String secteur;

	private String rayon;

	private String famille;

	private String sousFamille;

	private String codeAnal;

	@Column(name = "codeMag", columnDefinition="VARCHAR(3)")
	private String codeMag ="";

	private int colissage;

	private int prixReviensUnitaire;
	
	private int prixReviensInitialUnitaire;

	private int prixVenteUnitaire;
	
	private int prixVenteInitialUnitaire;

	private float qteLivrer;

	private float qteDemarque;

	private float qteCasse;
	
	private float qteSortie;
	
	private float qteAvantMvt;
	
	private int sensMvt;
	
	private Date date;
	
	private int duree;

	public StatDem() {
	}

	public StatDem(String codeArticle) {
		this.codeArticle = codeArticle;
	}

	public long getCodeGisement() {
		return codeGisement;
	}

	public void setCodeGisement(long codeGisement) {
		this.codeGisement = codeGisement;
	}

	@Override
	public int hashCode() {
		return getCodeArticle().hashCode();
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
		final StatDem other = (StatDem) obj;
		if (!getCodeArticle().equals(other.getCodeArticle())) {
			return false;
		}
		return true;
	}

	public String getCodeArticle() {
		return codeArticle;
	}

	public void setCodeArticle(String codeArticle) {
		this.codeArticle = codeArticle;
	}

	public String getSecteur() {
		return secteur;
	}

	public void setSecteur(String secteur) {
		this.secteur = secteur;
	}

	public String getRayon() {
		return rayon;
	}

	public void setRayon(String rayon) {
		this.rayon = rayon;
	}

	public String getFamille() {
		return famille;
	}

	public void setFamille(String famille) {
		this.famille = famille;
	}

	public String getSousFamille() {
		return sousFamille;
	}

	public void setSousFamille(String sousFamille) {
		this.sousFamille = sousFamille;
	}

	public String getCodeAnal() {
		return codeAnal;
	}

	public void setCodeAnal(String codeAnal) {
		this.codeAnal = codeAnal;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public int getColissage() {
		return colissage;
	}

	public void setColissage(int colissage) {
		this.colissage = colissage;
	}

	public int getPrixReviensUnitaire() {
		return prixReviensUnitaire;
	}

	public void setPrixReviensUnitaire(int prixReviensUnitaire) {
		this.prixReviensUnitaire = prixReviensUnitaire;
	}

	public int getPrixReviensInitialUnitaire() {
		return prixReviensInitialUnitaire;
	}

	public void setPrixReviensInitialUnitaire(int prixReviensInitialUnitaire) {
		this.prixReviensInitialUnitaire = prixReviensInitialUnitaire;
	}

	public int getPrixVenteUnitaire() {
		return prixVenteUnitaire;
	}

	public void setPrixVenteUnitaire(int prixVenteUnitaire) {
		this.prixVenteUnitaire = prixVenteUnitaire;
	}

	public int getPrixVenteInitialUnitaire() {
		return prixVenteInitialUnitaire;
	}

	public void setPrixVenteInitialUnitaire(int prixVenteInitialUnitaire) {
		this.prixVenteInitialUnitaire = prixVenteInitialUnitaire;
	}

	public float getQteLivrer() {
		return qteLivrer;
	}

	public void setQteLivrer(float qteLivrer) {
		this.qteLivrer = qteLivrer;
	}

	public float getQteDemarque() {
		return qteDemarque;
	}

	public void setQteDemarque(float qteDemarque) {
		this.qteDemarque = qteDemarque;
	}

	public float getQteCasse() {
		return qteCasse;
	}

	public void setQteCasse(float qteCasse) {
		this.qteCasse = qteCasse;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public float getQteSortie() {
		return qteSortie;
	}

	public void setQteSortie(float qteSortie) {
		this.qteSortie = qteSortie;
	}

	public float getQteAvantMvt() {
		return qteAvantMvt;
	}

	public void setQteAvantMvt(float qteAvantMvt) {
		this.qteAvantMvt = qteAvantMvt;
	}

	public int getSensMvt() {
		return sensMvt;
	}

	public void setSensMvt(int sensMvt) {
		this.sensMvt = sensMvt;
	}
	
	

	public String getCodeMag() {
		return codeMag;
	}

	public void setCodeMag(String codeMag) {
		this.codeMag = codeMag;
	}

	@Override
	public String toString() {
		return "StatDem [id=" + id + ", codeArticle=" + codeArticle
				+ ", designation=" + designation + ", codeGisement="
				+ codeGisement + ", secteur=" + secteur + ", rayon=" + rayon
				+ ", famille=" + famille + ", sousFamille=" + sousFamille
				+ ", codeAnal=" + codeAnal + ", codeMag=" + codeMag
				+ ", colissage=" + colissage + ", prixReviensUnitaire="
				+ prixReviensUnitaire + ", prixReviensInitialUnitaire="
				+ prixReviensInitialUnitaire + ", prixVenteUnitaire="
				+ prixVenteUnitaire + ", prixVenteInitialUnitaire="
				+ prixVenteInitialUnitaire + ", qteLivrer=" + qteLivrer
				+ ", qteDemarque=" + qteDemarque + ", qteCasse=" + qteCasse
				+ ", qteSortie=" + qteSortie + ", qteAvantMvt=" + qteAvantMvt
				+ ", sensMvt=" + sensMvt + ", date=" + date + ", duree="
				+ duree + "]";
	}

	

	
}