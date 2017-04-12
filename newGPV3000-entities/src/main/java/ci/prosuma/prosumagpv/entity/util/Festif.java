/**
 * 
 */
package ci.prosuma.prosumagpv.entity.util;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ci.prosuma.prosumagpv.entity.Article;

/**
 * @author PROPHYL.COM
 * 
 */
//@Entity
//@Table(name = "PARAM_ARTICLE_FESTIF")
public class Festif implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7608111497308708833L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@Column(name = "FESTIF_PK")
	private long id;
	
	@OneToOne
	@JoinColumns({
			@JoinColumn(name = "CODE_ARTICLE_FK", referencedColumnName = "CODE_ARTICLE_PK"),
			@JoinColumn(name = "CODE_MAGASIN_FK", referencedColumnName = "CODE_MAGASIN_PK"), })
	private Article article;


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
		final Festif other = (Festif) obj;
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

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}
	
}