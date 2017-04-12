package ci.prosuma.prosumagpv.entity.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ArticleMagRefPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "CODE_MAGASIN_PK")
	private String pvtCode;

	@Column(name = "CODE_ARTICLE_PK")
	private String codeArticle;

	public ArticleMagRefPK() {
	}

	public ArticleMagRefPK(String pvtCode, String codeArticle) {
		super();
		this.pvtCode = pvtCode;
		this.codeArticle = codeArticle;
	}

	public String getPvtCode() {
		return pvtCode;
	}

	public void setPvtCode(String pvtCode) {
		this.pvtCode = pvtCode;
	}

	public String getCodeArticle() {
		return codeArticle;
	}

	public void setCodeArticle(String codeArticle) {
		this.codeArticle = codeArticle;
	}

	@Override
	public int hashCode() {
		return (getCodeArticle() + getPvtCode()).hashCode();
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
		final ArticleMagRefPK other = (ArticleMagRefPK) obj;
		if (!getCodeArticle().equals(other.getCodeArticle()) 
				|| !getPvtCode().equals(other.getPvtCode()) ) {
			return false;
		}
		return true;
	}

}
