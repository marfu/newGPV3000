//package ci.prosuma.prosumagpv.entity.client;
//
//import java.io.Serializable;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.IdClass;
//import javax.persistence.Table;
//
//import ci.prosuma.prosumagpv.entity.pk.ArticleMagRefPK;
//
//@Entity
//@Table(name = "COMPTE_BANCAIRE")
//@IdClass(value = CompteBancairePK.class)
//public class CompteBancaire implements Serializable {
//
//	private static final long serialVersionUID = 1L;
//	
//	@Id
//	@Column(name = "CODE_BANQUE_PK")
//	private String codeBanque;
//
//	@Id
//	@Column(name = "CODE_GUICHET_PK")
//	private String codeGuichet;
//
//	@Id
//	@Column(name = "NUM_COMPTE_PK")
//	private String numCompte;
//
//	@Column(name = "TITULAIRE")
//	private String titulaire;
//
//	@Column(name = "DOMICILIATION")
//	private String domiciliation;
//
//	@Column(name = "CLEF_RIB")
//	private String clefRib;
//
//	@Column(name = "IBAN")
//	private String iban;
//
//	@Column(name = "SWIFT")
//	private String swift;
//
//	public CompteBancaire() {
//	}
//
//	@Override
//	public int hashCode() {
//		return (getCodeBanque() + getCodeGuichet()+ getNumCompte()).hashCode();
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj) {
//			return true;
//		}
//		if (obj == null) {
//			return false;
//		}
//		if (getClass() != obj.getClass()) {
//			return false;
//		}
//		final CompteBancaire other = (CompteBancaire) obj;
//		if (!getCodeBanque().equals(other.getCodeBanque()) 
//				|| !getCodeGuichet().equals(other.getCodeGuichet())   || !getNumCompte().equals(other.getNumCompte())) {
//			return false;
//		}
//		return true;
//	}
//
//
//
//	
//
//	public String getTitulaire() {
//		return titulaire;
//	}
//
//	public void setTitulaire(String titulaire) {
//		this.titulaire = titulaire;
//	}
//
//	public String getDomiciliation() {
//		return domiciliation;
//	}
//
//	public void setDomiciliation(String domiciliation) {
//		this.domiciliation = domiciliation;
//	}
//
//	public String getCodeBanque() {
//		return codeBanque;
//	}
//
//	public void setCodeBanque(String codeBanque) {
//		this.codeBanque = codeBanque;
//	}
//
//	public String getCodeGuichet() {
//		return codeGuichet;
//	}
//
//	public void setCodeGuichet(String codeGuichet) {
//		this.codeGuichet = codeGuichet;
//	}
//
//	public String getNumCompte() {
//		return numCompte;
//	}
//
//	public void setNumCompte(String numCompte) {
//		this.numCompte = numCompte;
//	}
//
//	public String getClefRib() {
//		return clefRib;
//	}
//
//	public void setClefRib(String clefRib) {
//		this.clefRib = clefRib;
//	}
//
//	public String getIban() {
//		return iban;
//	}
//
//	public void setIban(String iban) {
//		this.iban = iban;
//	}
//
//	public String getSwift() {
//		return swift;
//	}
//
//	public void setSwift(String swift) {
//		this.swift = swift;
//	}
//
//}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
