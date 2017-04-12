package ci.prosuma.prosumagpv.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

//@Entity
//@Table(name = "HISTORIQUE_PROMO")
public class HistoriquePromo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, name = "CODE_PROMO_PK", length = 4, nullable = false)
    private String libelReduit;

    @Column(name = "DESIGNATION", length = 100, nullable = false)
    private String designation;

    @Column(name = "DATE_DEBUT_PROMO", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateDebutPromo;

    @Column(name = "DATE_FIN_PROMO", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateFinPromo;

    @Column(name = "DATE_DEBUT_FACTURATION")
    @Temporal(TemporalType.DATE)
    private Date dateDebutFacturation;

    @Column(name = "DATE_FIN_FACTURATION")
    @Temporal(TemporalType.DATE)
    private Date dateFinFacturation;

    @Column(name = "CODE_ARTICLE_FK", nullable = false)
    private String codeArticle;

    @Column(name = "CODE_MAGASIN_FK", nullable = false)
    private String codeMag;

    @Column(name = "PV_NORMAL_TTC")
    private float prixVenteTTCNormal;

    @Column(name = "PR_NORMAL_TTC")
    private float prixReviensTTCNormal;

    @Column(name = "PV_PROMO_TTC")
    private float prixVenteTTCPromo;

    @Column(name = "PR_PROMO_TTC")
    private float prixReviensTTCPromo;

    @Column(name = "QUANTITE_DANS_LOT", columnDefinition = "int default 0")
    private Integer quantiteLot = 0;

    @Column(name = "PRIX_DU_LOT", columnDefinition = "int default 0")
    private Integer prixDuLot = 0;

    @Column(name = "TYPE_PROMO", columnDefinition = "int default 0")
    private Integer typePromo = 0;

    public HistoriquePromo() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLibelReduit() {
        return libelReduit;
    }

    public void setLibelReduit(String libelReduit) {
        this.libelReduit = libelReduit;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Date getDateDebutPromo() {
        return dateDebutPromo;
    }

    public void setDateDebutPromo(Date dateDebutPromo) {
        this.dateDebutPromo = dateDebutPromo;
    }

    public Date getDateFinPromo() {
        return dateFinPromo;
    }

    public void setDateFinPromo(Date dateFinPromo) {
        this.dateFinPromo = dateFinPromo;
    }

    public Date getDateDebutFacturation() {
        return dateDebutFacturation;
    }

    public void setDateDebutFacturation(Date dateDebutFacturation) {
        this.dateDebutFacturation = dateDebutFacturation;
    }

    public Date getDateFinFacturation() {
        return dateFinFacturation;
    }

    public void setDateFinFacturation(Date dateFinFacturation) {
        this.dateFinFacturation = dateFinFacturation;
    }

    public String getCodeArticle() {
        return codeArticle;
    }

    public void setCodeArticle(String codeArticle) {
        this.codeArticle = codeArticle;
    }

    public String getCodeMag() {
        return codeMag;
    }

    public void setCodeMag(String codeMag) {
        this.codeMag = codeMag;
    }

    public float getPrixVenteTTCNormal() {
        return prixVenteTTCNormal;
    }

    public void setPrixVenteTTCNormal(float prixVenteTTCNormal) {
        this.prixVenteTTCNormal = prixVenteTTCNormal;
    }

    public float getPrixReviensTTCNormal() {
        return prixReviensTTCNormal;
    }

    public void setPrixReviensTTCNormal(float prixReviensTTCNormal) {
        this.prixReviensTTCNormal = prixReviensTTCNormal;
    }

    public float getPrixVenteTTCPromo() {
        return prixVenteTTCPromo;
    }

    public void setPrixVenteTTCPromo(float prixVenteTTCPromo) {
        this.prixVenteTTCPromo = prixVenteTTCPromo;
    }

    public float getPrixReviensTTCPromo() {
        return prixReviensTTCPromo;
    }

    public void setPrixReviensTTCPromo(float prixReviensTTCPromo) {
        this.prixReviensTTCPromo = prixReviensTTCPromo;
    }

    public Integer getQuantiteLot() {
        return quantiteLot;
    }

    public void setQuantiteLot(Integer quantiteLot) {
        this.quantiteLot = quantiteLot;
    }

    public Integer getPrixDuLot() {
        return prixDuLot;
    }

    public void setPrixDuLot(Integer prixDuLot) {
        this.prixDuLot = prixDuLot;
    }

    public Integer getTypePromo() {
        return typePromo;
    }

    public void setTypePromo(Integer typePromo) {
        this.typePromo = typePromo;
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
        final HistoriquePromo other = (HistoriquePromo) obj;
        if (getId() != other.getId()) {
            return false;
        }
        return true;
    }

    /*
	@Override
	public String toString() {
		return "HistoriquePromo [id=" + id + ", libelReduit=" + libelReduit
				+ ", designation=" + designation + ", dateDebutPromo="
				+ dateDebutPromo + ", dateFinPromo=" + dateFinPromo
				+ ", dateDebutFacturation=" + dateDebutFacturation
				+ ", dateFinFacturation=" + dateFinFacturation
				+ ", codeArticle=" + codeArticle + ", codeMag=" + codeMag
				+ ", prixVenteTTCNormal=" + prixVenteTTCNormal
				+ ", prixReviensTTCNormal=" + prixReviensTTCNormal
				+ ", prixVenteTTCPromo=" + prixVenteTTCPromo
				+ ", prixReviensTTCPromo=" + prixReviensTTCPromo + "]";
	}
     */
}
