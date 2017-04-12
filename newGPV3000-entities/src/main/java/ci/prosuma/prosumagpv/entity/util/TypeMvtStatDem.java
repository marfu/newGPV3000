package ci.prosuma.prosumagpv.entity.util;
/** PROPHYL.COM */
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

//@SuppressWarnings("serial")
//@Entity
//@Table(name = "param_mouvement_stat_dem",
//	uniqueConstraints=@UniqueConstraint(columnNames={"NOM_COLONNE", "TYPE_MOUVEMENT", "NUM_MAGASIN_FK"}))
public class TypeMvtStatDem implements Serializable {


	@Id
	@Column(name = "MOUVEMENT_STAT_DEM_PK")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false, name = "NOM_COLONNE", length = 20)
	private String nomColonne;

	
	@Column(nullable = false, name = "TYPE_MOUVEMENT", length = 20)
	private String typeMouvement;

	@Column(nullable = false, name = "NUM_MAGASIN_FK", length = 20)
	private String numMagasin;

	public TypeMvtStatDem(){
	}

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getNomColonne() {
		return nomColonne;
	}


	public void setNomColonne(String nomColonne) {
		this.nomColonne = nomColonne;
	}


	public String getTypeMouvement() {
		return typeMouvement;
	}


	public void setTypeMouvement(String typeMouvement) {
		this.typeMouvement = typeMouvement;
	}

	public String getNumMagasin() {
		return numMagasin;
	}

	public void setNumMagasin(String numMagasin) {
		this.numMagasin = numMagasin;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result
				+ ((nomColonne == null) ? 0 : nomColonne.hashCode());
		result = prime * result
				+ ((numMagasin == null) ? 0 : numMagasin.hashCode());
		result = prime * result
				+ ((typeMouvement == null) ? 0 : typeMouvement.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TypeMvtStatDem other = (TypeMvtStatDem) obj;
		if (id != other.id)
			return false;
		if (nomColonne == null) {
			if (other.nomColonne != null)
				return false;
		} else if (!nomColonne.equals(other.nomColonne))
			return false;
		if (numMagasin == null) {
			if (other.numMagasin != null)
				return false;
		} else if (!numMagasin.equals(other.numMagasin))
			return false;
		if (typeMouvement == null) {
			if (other.typeMouvement != null)
				return false;
		} else if (!typeMouvement.equals(other.typeMouvement))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TypeMvtStatDem [id=" + id + ", nomColonne=" + nomColonne
				+ ", typeMouvement=" + typeMouvement + ", numMagasin="
				+ numMagasin + "]";
	}

	

	
}