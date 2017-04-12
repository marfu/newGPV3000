/**
 *
 */
package ci.prosuma.prosumagpv.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ci.prosuma.prosumagpv.entity.util.EnumerationParam.TypePromo;

/**
 * @author AKHDAR Zoul
 *
 */
//@Entity
//@Table(name = "PROMOTION")
public class Promo implements Serializable, Comparable<Promo> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @Column(unique = true, name = "CODE_PROMO_PK", length = 4)
    private String libelReduit;

    @Column(name = "DATE_CREATION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;

    @Column(name = "DATE_DERNIERE_MODIF")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModification;

    @Column(name = "DESIGNATION", length = 100, nullable = false)
    private String designation;

    @Column(name = "A_TRAITER")
    private Boolean aTraiter;

    @Column(name = "SI_FACTURATION")
    private boolean enFacturation;

    @Column(name = "SI_VENTE")
    private boolean enVente;

    @Column(name = "SI_TARIF_CENTRALE")
    private boolean tarifCentrale;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE_PROMO")
    private TypePromo typePromo;

    @ManyToMany
    @JoinTable(name = "TABLE_LIAISON_PROMOTION_PVT", 
            joinColumns = @JoinColumn(name = "CODE_PROMO_FK", referencedColumnName = "CODE_PROMO_PK"), 
            inverseJoinColumns = @JoinColumn(name = "CODE_MAGASIN_FK", referencedColumnName = "CODE_MAGASIN_PK"))
    private List<PointDeVente> pointDeVentes;

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

    @Column(name = "SI_ACTIF")
    private boolean actif;

    /**
     *
     */
    public Promo() {
    }

    @Override
    public int hashCode() {
        return getLibelReduit().hashCode();
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
        final Promo other = (Promo) obj;
        if (other == null || other.getLibelReduit() == null
                || getLibelReduit() == null) {
            return false;
        }

        if (!getLibelReduit().equals(other.getLibelReduit())) {
            return false;
        }
        return true;
    }

    public boolean needUpdate(Promo p) {
        if (p == null) {
            return true;
        }
        if (getDateDebutPromo() != null && p.getDateDebutPromo() != null && getDateDebutPromo().compareTo(p.getDateDebutPromo()) != 0) {
            return true;
        }
        if (getDateFinPromo() != null && p.getDateFinPromo() != null && getDateFinPromo().compareTo(p.getDateFinPromo()) != 0) {
            return true;
        }
        if (getDateDebutFacturation() != null && p.getDateDebutFacturation() != null && getDateDebutFacturation().compareTo(p.getDateDebutFacturation()) != 0) {
            return true;
        }
        if (getDateFinFacturation() != null && p.getDateFinFacturation() != null && getDateFinFacturation().compareTo(p.getDateFinFacturation()) != 0) {
            return true;
        }
        if (getDesignation() != null && p.getDesignation() != null && !getDesignation().equals(p.getDesignation())) {
            return true;
        }
        if (getLibelReduit() != null && p.getLibelReduit() != null && !getLibelReduit().equals(p.getLibelReduit())) {
            return true;
        }
        if (getTypePromo() != null && p.getTypePromo() != null && !getTypePromo().equals(p.getTypePromo())) {
            return true;
        }
        if (isTarifCentrale() != p.isTarifCentrale()) {
            return true;
        }
        return false;
    }

    public boolean isTarifCentrale() {
        return tarifCentrale;
    }

    public void setTarifCentrale(boolean tarifCentrale) {
        this.tarifCentrale = tarifCentrale;
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

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getLibelReduit() {
        return libelReduit;
    }

    public void setLibelReduit(String libelReduit) {
        this.libelReduit = libelReduit;
    }

    public TypePromo getTypePromo() {
        return typePromo;
    }

    public void setTypePromo(TypePromo typePromo) {
        this.typePromo = typePromo;
    }

    public List<PointDeVente> getPointDeVentes() {
        return pointDeVentes;
    }

    public void setPointDeVentes(List<PointDeVente> pointDeVentes) {
        this.pointDeVentes = pointDeVentes;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
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

    public boolean isEnFacturation() {
        return enFacturation;
    }

    public void setEnFacturation(boolean enFacturation) {
        this.enFacturation = enFacturation;
    }

    public boolean isEnVente() {
        return enVente;
    }

    public void setEnVente(boolean enVente) {
        this.enVente = enVente;
    }

    public Date getDateModification() {
        return dateModification;
    }

    public void setDateModification(Date dateModification) {
        this.dateModification = dateModification;
    }

    public Boolean isaTraiter() {
        return aTraiter;
    }

    public void setaTraiter(Boolean aTraiter) {
        this.aTraiter = aTraiter;
    }

    @Override
    public String toString() {
        return "Promo [libelReduit=" + libelReduit + ", dateCreation="
                + dateCreation + ", dateModification=" + dateModification
                + ", designation=" + designation + ", aTraiter=" + aTraiter
                + ", enFacturation=" + enFacturation + ", enVente=" + enVente
                + ", tarifCentrale=" + tarifCentrale + ", typePromo="
                + typePromo + ", pointDeVentes=" + pointDeVentes
                + ", dateDebutPromo=" + dateDebutPromo + ", dateFinPromo="
                + dateFinPromo + ", dateDebutFacturation="
                + dateDebutFacturation + ", dateFinFacturation="
                + dateFinFacturation + ", actif=" + actif + "]";
    }

    public String myToString() {
        return "Promo [libelReduit=" + libelReduit + ", dateCreation="
                + dateCreation + ", designation=" + designation
                + ", enFacturation=" + enFacturation + ", enVente=" + enVente
                + ", tarifCentrale=" + tarifCentrale + ", typePromo="
                + typePromo + ", pointDeVentes=" + pointDeVentes
                + ", dateDebutPromo=" + dateDebutPromo + ", dateFinPromo="
                + dateFinPromo + ", dateDebutFacturation="
                + dateDebutFacturation + ", dateFinFacturation="
                + dateFinFacturation + ", actif=" + actif + "]";
    }

    @Override
    public int compareTo(Promo o) {
        return this.getDateDebutFacturation().compareTo(o.getDateDebutFacturation());
    }

}
