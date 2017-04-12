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

import com.nov.hotel.entities.TStatu;

/**
 * @author montan
 *
 */
@Entity
@Table(name = "T_CLIENT")
public class TClient implements Serializable{
	 private static final long serialVersionUID = 1L;
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "TCLI_ID")
	    private long cliId;
	    
	    @Column(name = "TCLI_NOM")
	    private String cliNom;
	    
	    @Column(name = "TCLI_PRENOM")
	    private String cliPrenom;
	    
	    @Column(name = "TCLI_SEXE")
	    private String cliSexe;
	    
	    @Column(name = "TCLI_EMAIL")
	    private String cliEmail;
	    
	    @Column(name = "TCLI_MSISDN")
	    private String cliMsisdn;
	    
	    @Column(name = "TCLI_DATE_CREATE")
		@Temporal(TemporalType.TIMESTAMP)
		private Date cliDateCreate;
	    
	    @Enumerated(EnumType.STRING)
	    @Column(name = "TYPE_CLIENT", length=10)
	    private ClientEnum typeClient;
	    
	    @Enumerated(EnumType.STRING)
	    @Column(name = "PIECE_IDENTITE", length=10)
	    private PieceIdentiteEnum pieceIdentite;
	    
	    @Column(name = "TCLI_NUMER_PI")
	    private String numeroPieceIdentite;
	    
	    @Column(name = "TCLI_NOM_INCIDENT")
	    private String nomIncident;
	    
	    @Column(name = "TCLI_MSISDN_INCIDENT")
	    private String msisdnIncident;
	    
	    @Column(name = "TCLI_RAISON_SOCIALE")
	    private String raisonSociale;
	    
	    @ManyToOne
	   	@JoinColumn(name = "STATU_ID", referencedColumnName = "STATU_ID")
	    private TStatu cliStatut;
	    
	    @Column(name = "TCLI_DATE_MODIF")
		@Temporal(TemporalType.TIMESTAMP)
		private Date cliDateModif;

		public long getCliId() {
			return cliId;
		}

		public ClientEnum getTypeClient() {
			return typeClient;
		}

		public void setTypeClient(ClientEnum typeClient) {
			this.typeClient = typeClient;
		}

		public PieceIdentiteEnum getPieceIdentite() {
			return pieceIdentite;
		}

		public void setPieceIdentite(PieceIdentiteEnum pieceIdentite) {
			this.pieceIdentite = pieceIdentite;
		}

		public String getNumeroPieceIdentite() {
			return numeroPieceIdentite;
		}

		public void setNumeroPieceIdentite(String numeroPieceIdentite) {
			this.numeroPieceIdentite = numeroPieceIdentite;
		}

		public String getNomIncident() {
			return nomIncident;
		}

		public void setNomIncident(String nomIncident) {
			this.nomIncident = nomIncident;
		}

		public String getMsisdnIncident() {
			return msisdnIncident;
		}

		public void setMsisdnIncident(String msisdnIncident) {
			this.msisdnIncident = msisdnIncident;
		}

		public String getRaisonSociale() {
			return raisonSociale;
		}

		public void setRaisonSociale(String raisonSociale) {
			this.raisonSociale = raisonSociale;
		}

		public void setCliId(long cliId) {
			this.cliId = cliId;
		}

		public String getCliNom() {
			return cliNom;
		}

		public void setCliNom(String cliNom) {
			this.cliNom = cliNom;
		}

		public String getCliPrenom() {
			return cliPrenom;
		}

		public void setCliPrenom(String cliPrenom) {
			this.cliPrenom = cliPrenom;
		}

		public String getCliSexe() {
			return cliSexe;
		}

		public void setCliSexe(String cliSexe) {
			this.cliSexe = cliSexe;
		}

		public String getCliEmail() {
			return cliEmail;
		}

		public void setCliEmail(String cliEmail) {
			this.cliEmail = cliEmail;
		}

		public Date getCliDateCreate() {
			return cliDateCreate;
		}

		public void setCliDateCreate(Date cliDateCreate) {
			this.cliDateCreate = cliDateCreate;
		}

		public TStatu getCliStatut() {
			return cliStatut;
		}

		public void setCliStatut(TStatu cliStatut) {
			this.cliStatut = cliStatut;
		}

		public Date getCliDateModif() {
			return cliDateModif;
		}

		public void setCliDateModif(Date cliDateModif) {
			this.cliDateModif = cliDateModif;
		}

		public String getCliMsisdn() {
			return cliMsisdn;
		}

		public void setCliMsisdn(String cliMsisdn) {
			this.cliMsisdn = cliMsisdn;
		}

		@Override
		public String toString() {
			return "TClient [cliId=" + cliId + ", cliNom=" + cliNom + ", cliPrenom=" + cliPrenom + ", cliSexe="
					+ cliSexe + ", cliEmail=" + cliEmail + ", cliMsisdn=" + cliMsisdn + ", cliDateCreate="
					+ cliDateCreate + ", typeClient=" + typeClient + ", pieceIdentite=" + pieceIdentite
					+ ", numeroPieceIdentite=" + numeroPieceIdentite + ", nomIncident=" + nomIncident
					+ ", msisdnIncident=" + msisdnIncident + ", raisonSociale=" + raisonSociale + ", cliStatut="
					+ cliStatut + ", cliDateModif=" + cliDateModif + "]";
		}

		
}
