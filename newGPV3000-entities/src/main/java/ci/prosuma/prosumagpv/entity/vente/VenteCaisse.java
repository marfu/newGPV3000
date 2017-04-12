package ci.prosuma.prosumagpv.entity.vente;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Index;

//@Entity
//@Table(name = "VENTE_CAISSE")
public class VenteCaisse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Index(name = "code_article_vc")
	private String codeArticle;

	@Index(name = "code_mag_vc")
	private String codeMagasin;
	
	private float nbrArticleVendu;
	
	@Index(name = "rayon_vc")
	private String rayon;
	
	private String secteur;

	private long montantArticleVendu;

	private float nbrArticleRembourser;

	private long montantArticleRembourser;
	
	@Index(name = "montant_total_vc")
	private long montantTotal;

	private boolean retour;

	@Temporal(TemporalType.DATE)
	@Index(name = "date_vente_vc")
	private Date dateVente;
	
//	@Index(name = "theme_promo_vc")
//	private String themePromo;
//	
//	private Integer nombreArticleLot;
//	
//	private Long prixDuLot;

	public VenteCaisse() {
	}

	public Date getDateVente() {
		return dateVente;
	}

	public void setDateVente(Date dateVente) {
		this.dateVente = dateVente;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	

	public String getCodeArticle() {
		return codeArticle;
	}

	public void setCodeArticle(String codeArticle) {
		this.codeArticle = codeArticle;
	}

	public String getCodeMagasin() {
		return codeMagasin;
	}

	public void setCodeMagasin(String codeMagasin) {
		this.codeMagasin = codeMagasin;
	}
	
	

	public long getMontantTotal() {
		return montantTotal;
	}

	public void setMontantTotal(long montantTotal) {
		this.montantTotal = montantTotal;
	}

	public float getNbrArticleVendu() {
		return nbrArticleVendu;
	}

	public void setNbrArticleVendu(float nbrArticleVendu) {
		this.nbrArticleVendu = nbrArticleVendu;
	}

	public long getMontantArticleVendu() {
		return montantArticleVendu;
	}

	public void setMontantArticleVendu(long montantArticleVendu) {
		this.montantArticleVendu = montantArticleVendu;
	}

	public float getNbrArticleRembourser() {
		return nbrArticleRembourser;
	}

	public void setNbrArticleRembourser(float nbrArticleRembourser) {
		this.nbrArticleRembourser = nbrArticleRembourser;
	}

	public long getMontantArticleRembourser() {
		return montantArticleRembourser;
	}

	public void setMontantArticleRembourser(long montantArticleRembourser) {
		this.montantArticleRembourser = montantArticleRembourser;
	}

	public boolean isRetour() {
		return retour;
	}

	public void setRetour(boolean retour) {
		this.retour = retour;
	}
	
	

	public String getRayon() {
		return rayon;
	}

	public void setRayon(String rayon) {
		this.rayon = rayon;
	}

	public String getSecteur() {
		return secteur;
	}

	public void setSecteur(String secteur) {
		this.secteur = secteur;
	}
	
	

//	public String getThemePromo() {
//		return themePromo;
//	}
//
//	public void setThemePromo(String themePromo) {
//		this.themePromo = themePromo;
//	}
//
//	public Integer getNombreArticleLot() {
//		return nombreArticleLot;
//	}
//
//	public void setNombreArticleLot(Integer nombreArticleLot) {
//		this.nombreArticleLot = nombreArticleLot;
//	}
//
//	public Long getPrixDuLot() {
//		return prixDuLot;
//	}
//
//	public void setPrixDuLot(Long prixDuLot) {
//		this.prixDuLot = prixDuLot;
//	}

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
		final VenteCaisse other = (VenteCaisse) obj;
		if (getId() != other.getId()) {
			return false;
		}
		return true;
	}
/*
	@Override
	public String toString() {
		return "VenteCaisse [id=" + id + ", codeArticle=" + codeArticle
				+ ", codeMagasin=" + codeMagasin + ", nbrArticleVendu="
				+ nbrArticleVendu + ", rayon=" + rayon + ", secteur=" + secteur
				+ ", montantArticleVendu=" + montantArticleVendu
				+ ", nbrArticleRembourser=" + nbrArticleRembourser
				+ ", montantArticleRembourser=" + montantArticleRembourser
				+ ", montantTotal=" + montantTotal + ", retour=" + retour
				+ ", dateVente=" + dateVente + "]";
	}
*/
	
}
