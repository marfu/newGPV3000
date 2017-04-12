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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ci.prosuma.prosumagpv.entity.PointDeVente;
import ci.prosuma.prosumagpv.entity.util.EnumerationParam.TypeInventaire;
import ci.prosuma.prosumagpv.entity.util.Rayon;
import ci.prosuma.prosumagpv.entity.util.Secteur;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * @author Serge AYEPI
 *
 */
//@Entity
//@Table(name = "INVENTAIRE_ENTETE",
//        uniqueConstraints={@UniqueConstraint(columnNames = {"NUMERO_INVENTAIRE", "CODE_MAGASIN_FK"})
//})
public class EnteteInventaire implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ENTETE_INVENTAIRE_PK")
    private long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_INVENTAIRE", nullable = false)
    private Date dateInventaire;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE_INVENTAIRE")
    private TypeInventaire typeInventaire;

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "SECTEUR_FK", referencedColumnName = "SECTEUR_PK")
    private Secteur secteur;

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "RAYON_FK", referencedColumnName = "RAYON_PK")
    private Rayon rayon;

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "CODE_MAGASIN_FK", referencedColumnName = "CODE_MAGASIN_PK")
    private PointDeVente pvt;

    @Column(name = "GISEMENT_DEBUT")
    private long gisementDebut;

    @Column(name = "GISEMENT_FIN")
    private long gisementFin;

    @Column(name = "SI_GENERER")
    private boolean generer;

    @Column(name = "SI_LANCER")
    private boolean lancer;

    @Column(name = "SI_CLOTURER")
    private boolean cloturer;

    @Column(name = "OBSERVATION", nullable = false)
    private String observation;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_CREATION")
    private Date dateCreation;

    @Column(name = "DATE_MODIFICATION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModification;

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "DEPOT_FK", referencedColumnName = "DEPOT_PK")
    private Depot depot;

    /**
     * PROPHYL.COM => FetchType.EAGER Il y’a effectivement un soucis à ce
     * niveau, car les inventaires sont créés sans qu’il y ait une vérification
     * de la présence de l’article dans un autre inventaire non clôturé.
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "enteteInventaire")
    private List<DetailInventaire> detailInventaire;

    @Column(name = "NUMERO_INVENTAIRE")
    private String numInventaire;

    public EnteteInventaire() {
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
        final EnteteInventaire other = (EnteteInventaire) obj;
        if (getId() != other.getId()) {
            return false;
        }
        return true;
    }

    public List<DetailInventaire> getDetailInventaire() {
        return detailInventaire;
    }

    public void setDetailInventaire(List<DetailInventaire> detailInventaire) {
        this.detailInventaire = detailInventaire;
    }

    public TypeInventaire getTypeInventaire() {
        return typeInventaire;
    }

    public void setTypeInventaire(TypeInventaire typeInventaire) {
        this.typeInventaire = typeInventaire;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public PointDeVente getPvt() {
        return pvt;
    }

    public void setPvt(PointDeVente pvt) {
        this.pvt = pvt;
    }

    public Date getDateModification() {
        return dateModification;
    }

    public void setDateModification(Date dateModification) {
        this.dateModification = dateModification;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDateInventaire() {
        return dateInventaire;
    }

    public void setDateInventaire(Date dateInventaire) {
        this.dateInventaire = dateInventaire;
    }

    public Depot getDepot() {
        return depot;
    }

    public void setDepot(Depot depot) {
        this.depot = depot;
    }

    public Secteur getSecteur() {
        return secteur;
    }

    public void setSecteur(Secteur secteur) {
        this.secteur = secteur;
    }

    public Rayon getRayon() {
        return rayon;
    }

    public void setRayon(Rayon rayon) {
        this.rayon = rayon;
    }

    public long getGisementDebut() {
        return gisementDebut;
    }

    public void setGisementDebut(long gisementDebut) {
        this.gisementDebut = gisementDebut;
    }

    public long getGisementFin() {
        return gisementFin;
    }

    public void setGisementFin(long gisementFin) {
        this.gisementFin = gisementFin;
    }

    public boolean isGenerer() {
        return generer;
    }

    public void setGenerer(boolean generer) {
        this.generer = generer;
    }

    public boolean isLancer() {
        return lancer;
    }

    public void setLancer(boolean lancer) {
        this.lancer = lancer;
    }

    public boolean isCloturer() {
        return cloturer;
    }

    public void setCloturer(boolean cloturer) {
        this.cloturer = cloturer;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getNumInventaire() {
        return numInventaire;
    }

    public void setNumInventaire(String numInventaire) {
        this.numInventaire = numInventaire;
    }

    public String mytoString() {
        return "EnteteInventaire [id=" + id + ", dateInventaire="
                + dateInventaire + ", typeInventaire=" + typeInventaire
                + ", secteur=" + secteur + ", rayon=" + rayon + ", pvt=" + pvt
                + ", gisementDebut=" + gisementDebut + ", gisementFin="
                + gisementFin + ", generer=" + generer + ", lancer=" + lancer
                + ", cloturer=" + cloturer + ", observation=" + observation
                + ", dateCreation=" + dateCreation + ", dateModification="
                + dateModification + ", depot=" + depot.mytoString()
                + ", detailInventaire=" + detailInventaire + "]";
    }

}
