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
@Table(name = "T_DETAIL_FACTURE")
public class TDetailFacture implements Serializable{
		private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TDFACT_ID")
    private long factId;
    
     @Column(name = "TDFACT_DATE_CREATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dfactDateCreate;
    
    @ManyToOne
   	@JoinColumn(name = "TDFACT_FACTURE", referencedColumnName = "TFACT_ID")
    private TFacture facture;
    
    @ManyToOne
   	@JoinColumn(name = "TDFACT_SCE_ID", referencedColumnName = "TSCE_ID")
    private TService service;
    
    @ManyToOne
   	@JoinColumn(name = "TDFACT_SCE_CONSO_ID", referencedColumnName = "TSCE_ID")
    private TService serviceConsoId;
    
    @ManyToOne
   	@JoinColumn(name = "TDFACT_ART_CONSO_ID", referencedColumnName = "TART_ID")
    private TArticle articleConsoId;
    
   	@Column(name = "TDFACT_USER_MODIF")
    private String userModif;

    @Column(name = "TDFACT_PRIX")
    private double dfactPrix;
    
    @Column(name = "TDFACT_QTE")
    private long dfactQte;

	public long getFactId() {
		return factId;
	}

	public void setFactId(long factId) {
		this.factId = factId;
	}


	public TFacture getFacture() {
		return facture;
	}

	public void setFacture(TFacture facture) {
		this.facture = facture;
	}

	public TService getService() {
		return service;
	}

	public void setService(TService service) {
		this.service = service;
	}

	public TService getServiceConsoId() {
		return serviceConsoId;
	}

	public void setServiceConsoId(TService serviceConsoId) {
		this.serviceConsoId = serviceConsoId;
	}

    public String getUserModif() {
        return userModif;
    }

    public void setUserModif(String userModif) {
        this.userModif = userModif;
    }

    public Date getDfactDateCreate() {
        return dfactDateCreate;
    }

    public void setDfactDateCreate(Date dfactDateCreate) {
        this.dfactDateCreate = dfactDateCreate;
    }

    public double getDfactPrix() {
        return dfactPrix;
    }

    public void setDfactPrix(double dfactPrix) {
        this.dfactPrix = dfactPrix;
    }

    public long getDfactQte() {
        return dfactQte;
    }

    public void setDfactQte(long dfactQte) {
        this.dfactQte = dfactQte;
    }

	

	

    public TArticle getArticleConsoId() {
        return articleConsoId;
    }

    public void setArticleConsoId(TArticle articleConsoId) {
        this.articleConsoId = articleConsoId;
    }

    @Override
    public String toString() {
        return "TDetailFacture{" + "factId=" + factId + ", dfactDateCreate=" + dfactDateCreate + ", facture=" + facture + ", service=" + service + ", serviceConsoId=" + serviceConsoId + ", articleConsoId=" + articleConsoId + ", userModif=" + userModif + ", dfactPrix=" + dfactPrix + ", dfactQte=" + dfactQte + '}';
    }

    


}
