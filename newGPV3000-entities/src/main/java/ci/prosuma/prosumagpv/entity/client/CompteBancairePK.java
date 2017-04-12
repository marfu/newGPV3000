//package ci.prosuma.prosumagpv.entity.client;
//
//import java.io.Serializable;
//
//import javax.persistence.Column;
//import javax.persistence.Embeddable;
//import javax.persistence.Id;
//
//@Embeddable
//public class CompteBancairePK implements Serializable {
//
//	/**
//	 * 
//	 */
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
//	public CompteBancairePK() {
//	}
//
//	public CompteBancairePK(String codeBanque, String codeGuichet, String numCompte) {
//		super();
//		this.codeBanque = codeBanque;
//		this.codeGuichet = codeGuichet;
//		this.numCompte = numCompte;
//	}
//
//	@Override
//	public int hashCode() {
//		return (getCodeBanque() + getCodeGuichet() + getNumCompte()).hashCode();
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
//		final CompteBancairePK other = (CompteBancairePK) obj;
//		if (!getCodeBanque().equals(other.getCodeBanque()) || !getCodeGuichet().equals(other.getCodeGuichet()) || !getNumCompte().equals(other.getNumCompte())) {
//			return false;
//		}
//		return true;
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
//}
