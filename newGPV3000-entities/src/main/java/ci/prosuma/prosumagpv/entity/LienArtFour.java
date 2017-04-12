/**
 * 
 */
package ci.prosuma.prosumagpv.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



/**
 * @author AKHDAR Zoul
 * 
 */
//@Entity
//@Table(name = "LIEN_ARTICLE_FOURNISSEUR")
public class LienArtFour implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "CODE_ARTICLE", length = 11,nullable = false)
	private String article;

	@Column(name = "REF_FOURNISSEUR_FK", length = 7)
	private String fournisseur;

	@Column(name = "SI_ACTIF")
	private boolean actif;

	@Column(name = "SI_COMMANDABLE")
	private boolean commandable;

	public LienArtFour() {
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
		final LienArtFour other = (LienArtFour) obj;
		if (getId() != other.getId()) {
			return false;
		}
		return true;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public String getFournisseur() {
		return fournisseur;
	}

	public void setFournisseur(String fournisseur) {
		this.fournisseur = fournisseur;
	}

	public boolean isActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}

	public boolean isCommandable() {
		return commandable;
	}

	public void setCommandable(boolean commandable) {
		this.commandable = commandable;
	}
	
	public boolean needUpdate(LienArtFour laf){
		if(laf == null){
			return true;
		}
		if(getArticle() != null && laf.getArticle() != null && !getArticle().equals(laf.getArticle()))
			return true;
		if(getFournisseur() != null && laf.getFournisseur() != null && !getFournisseur().equals(laf.getFournisseur()))
			return true;
		if(isActif() != laf.isActif())
			return true;
		if(isCommandable() != laf.isCommandable())
			return true;
		
		return false;
	}

}
