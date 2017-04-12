/**
 *
 */
package ci.prosuma.prosumagpv.entity;

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
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import ci.prosuma.prosumagpv.entity.util.EnumerationParam.CategorieGenCode;
import ci.prosuma.prosumagpv.entity.util.EnumerationParam.ModeGenCode;
import ci.prosuma.prosumagpv.entity.util.EnumerationParam.OrigineGenCode;
import javax.persistence.FetchType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * @author AKHDAR Zoul
 *
 */
//@Entity
//@Table(name = "GENCODE", uniqueConstraints = @UniqueConstraint(columnNames = {"GENCODE_CODE", "CODE_ARTICLE_FK", "CODE_MAGASIN_FK"}))
public class GenCode implements Serializable, Comparable<GenCode> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GENCODE_PK")
    private long id;

    @Column(name = "GENCODE_CODE", length = 12, nullable = false)
    private String code;

    @Column(name = "COLISAGE", unique = false)
    private int colisage;

    @Column(name = "CARACTERE_CONTROLE", length = 1, nullable = false)
    private String caractereControle;

    @Column(name = "PRIX_VENTE_TTC_SUGGERER", nullable = true)
    private int prixSuggerer;

    @Column(name = "PRIX_REVIENS_TTC_EN_COURS")
    private int prixReviensTTCEnCours;

    @Column(name = "PRIX_VENTE_TTC_EN_COURS")
    private int prixVenteTTCEnCours;

    @Column(name = "COEF_SUR_ARTICLE")
    private float coefSurArticleInPourcent;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "CATEGORIE_GENCODE")
    private CategorieGenCode catGen;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "ORIGINE_GENCODE")
    private OrigineGenCode origineGenCode;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "MODE_GENCODE")
    private ModeGenCode modeGenCode;

    @Column(name = "SI_ACTIF")
    private boolean actif;

    @Column(name = "DATE_CREATION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;

    @Column(name = "SI_NOUVEAU")
    private boolean nouveauCB;

    @Column(name = "SI_MODIFIER")
    private boolean modifier;

    @Column(name = "USER_CREATION")
    private String userCreation;

    @ManyToOne
    @Fetch(FetchMode.JOIN)
    @JoinColumns({
        @JoinColumn(name = "CODE_ARTICLE_FK", referencedColumnName = "CODE_ARTICLE_PK"),
        @JoinColumn(name = "CODE_MAGASIN_FK", referencedColumnName = "CODE_MAGASIN_PK"),})
    private Article article;

    @Column(name = "CODEMAG")
    private String pvtCode;

    @Column(name = "DTYPE", nullable = false, length = 31, columnDefinition = "VARCHAR(31) default ' '")
    private String dType = " ";

    // @Column(name = "ALERTE_SI_NB", columnDefinition="bigint(11) default 0")
    // private int utilVar = 0;
    @Column(name = "A_DESCENDRE", columnDefinition = "bit(1) default 1")
    private boolean descenteAEffectuer = false;

    @Transient
    private int utilVar = 1;

    public GenCode() {
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
        final GenCode other = (GenCode) obj;
        if (getId() != other.getId()) {
            return false;
        }
        return true;
    }

    public boolean needUpdatePrice(GenCode gc) {
        if (getModeGenCode() == null || gc.getModeGenCode() == null) {
            return true;
        }
        if (getModeGenCode().equals(gc.getModeGenCode())) {
            if (!getModeGenCode().equals(ModeGenCode.PRINCIPAL)
                    || getModeGenCode().equals(ModeGenCode.GENERIQUE)) {
                if (getPrixReviensTTCEnCours() != gc.getPrixReviensTTCEnCours()) {
                    return true;
                }
                if (getPrixVenteTTCEnCours() != gc.getPrixVenteTTCEnCours()) {
                    return true;
                }

                return false;
            } else {
                return false;
            }

        } else {
            return true;
        }
    }

    public boolean needUpdateOrigine(GenCode gc) {
        if (getOrigineGenCode() == null || gc.getOrigineGenCode() == null) {
            return true;
        }
        if (getOrigineGenCode().equals(gc.getOrigineGenCode())) {
            return false;
        } else {
            return true;
        }

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPrixReviensTTCEnCours() {
        if (getModeGenCode().equals(ModeGenCode.PRINCIPAL)
                || getModeGenCode().equals(ModeGenCode.GENERIQUE)) {
            if (null != getArticle().getPromo()) {
                return getArticle().getPrixReviensPromoTTC();
            } else {
                return getArticle().getPrixReviensTTCEnCours();
            }
        } else {
            return prixReviensTTCEnCours;
        }

    }

    public void setPrixReviensTTCEnCours(int prixReviensTTCEnCours) {
        this.prixReviensTTCEnCours = prixReviensTTCEnCours;
    }

    public int getPrixVenteTTCEnCours() {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@  getModeGenCode : " + getModeGenCode());
        if (getModeGenCode().equals(ModeGenCode.PRINCIPAL) || getModeGenCode().equals(ModeGenCode.GENERIQUE)) {
            if (null != getArticle().getPromo()) {
                return getArticle().getPrixVentePromoTTC();
            } else {
                return getArticle().getPrixVenteTTCEnCours();
            }
        } else {
            return prixVenteTTCEnCours;
        }

    }

    public void setPrixVenteTTCEnCours(int prixVenteTTCEnCours) {
        this.prixVenteTTCEnCours = prixVenteTTCEnCours;
    }

    public float getCoefSurArticleInPourcent() {
        return coefSurArticleInPourcent;
    }

    public void setCoefSurArticleInPourcent(float coefSurArticleInPourcent) {
        this.coefSurArticleInPourcent = coefSurArticleInPourcent;
    }

    public CategorieGenCode getCatGen() {
        return catGen;
    }

    public void setCatGen(CategorieGenCode catGen) {
        this.catGen = catGen;
    }

    public OrigineGenCode getOrigineGenCode() {
        return origineGenCode;
    }

    public void setOrigineGenCode(OrigineGenCode origineGenCode) {
        this.origineGenCode = origineGenCode;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCaractereControle() {
        return caractereControle;
    }

    public void setCaractereControle(String caractereControle) {
        this.caractereControle = caractereControle;
    }

    public boolean isNouveauCB() {
        return nouveauCB;
    }

    public void setNouveauCB(boolean nouveauCB) {
        this.nouveauCB = nouveauCB;
    }

    public boolean isModifier() {
        return modifier;
    }

    public void setModifier(boolean modifier) {
        this.modifier = modifier;
    }

    public ModeGenCode getModeGenCode() {
        return modeGenCode;
    }

    public void setModeGenCode(ModeGenCode modeGenCode) {
        this.modeGenCode = modeGenCode;
    }

    public int getPrixSuggerer() {
        return prixSuggerer;
    }

    public void setPrixSuggerer(int prixSuggerer) {
        this.prixSuggerer = prixSuggerer;
    }

    public int getColisage() {
        return colisage;
    }

    public void setColisage(int colisage) {
        this.colisage = colisage;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public String getUserCreation() {
        return userCreation;
    }

    public void setUserCreation(String userCreation) {
        this.userCreation = userCreation;
    }

    public String getPvtCode() {
        return pvtCode;
    }

    public void setPvtCode(String pvtCode) {
        this.pvtCode = pvtCode;
    }

    public int getUtilVar() {
        return utilVar;
    }

    public void setUtilVar(int utilVar) {
        this.utilVar = utilVar;
    }

    public String getdType() {
        return dType;
    }

    public void setdType(String dType) {
        this.dType = dType;
    }

    public boolean isDescenteAEffectuer() {
        return descenteAEffectuer;
    }

    public void setDescenteAEffectuer(boolean descenteAEffectuer) {
        this.descenteAEffectuer = descenteAEffectuer;
    }

    @Override
    public int compareTo(GenCode arg0) {
        Article a1 = this.getArticle();
        Article a2 = ((GenCode) arg0).getArticle();
        // tri desc
        if (a1.getCodeArticle().compareTo(a2.getCodeArticle()) == 1) {
            return 1;
        } else if (a1.getCodeArticle().compareTo(a2.getCodeArticle()) == -1) {
            return -1;
        } else {
            return 0;
        }
    }

    public String myToString() {
        return "GenCode [id=" + id + ", code=" + code + ", colisage="
                + colisage + ", caractereControle=" + caractereControle
                + ", prixSuggerer=" + prixSuggerer + ", prixReviensTTCEnCours="
                + prixReviensTTCEnCours + ", prixVenteTTCEnCours="
                + prixVenteTTCEnCours + ", coefSurArticleInPourcent="
                + coefSurArticleInPourcent + ", catGen=" + catGen
                + ", origineGenCode=" + origineGenCode + ", modeGenCode="
                + modeGenCode + ", actif=" + actif + ", dateCreation="
                + dateCreation + ", nouveauCB=" + nouveauCB + ", modifier="
                + modifier + ", userCreation=" + userCreation + ", article="
                + article.getCodeArticle() + ", pvtCode=" + pvtCode
                + ", dType=" + dType + ", utilVar=" + utilVar + "]\n";
    }

    @Override
    public String toString() {
        return "GenCode [id=" + id + ", code=" + code + ", colisage="
                + colisage + ", caractereControle=" + caractereControle
                + ", prixSuggerer=" + prixSuggerer + ", prixReviensTTCEnCours="
                + prixReviensTTCEnCours + ", prixVenteTTCEnCours="
                + prixVenteTTCEnCours + ", coefSurArticleInPourcent="
                + coefSurArticleInPourcent + ", catGen=" + catGen
                + ", origineGenCode=" + origineGenCode + ", modeGenCode="
                + modeGenCode + ", actif=" + actif + ", dateCreation="
                + dateCreation + ", nouveauCB=" + nouveauCB + ", modifier="
                + modifier + ", userCreation=" + userCreation + ", article="
                + article.getCodeArticle() + ", pvtCode=" + pvtCode + ", dType=" + dType
                + ", descenteAEffectuer=" + descenteAEffectuer + ", utilVar="
                + utilVar + "]";
    }

    public String info() {
        return "	" + article.getSecteur() + "		" + article.getRayon() + "	" + code + caractereControle + "	" + article.getCodeArticle() + "		" + article.getDesignation();
    }

}
