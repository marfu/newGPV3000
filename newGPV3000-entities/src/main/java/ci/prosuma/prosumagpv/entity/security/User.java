package ci.prosuma.prosumagpv.entity.security;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


//import org.hibernate.validator.Email;
//import org.hibernate.validator.NotNull;

import ci.prosuma.prosumagpv.entity.PointDeVente;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "USER")
public class User implements Serializable {

	private static final long serialVersionUID = 7199807568018597880L;

	@Id
//	@NotNull
	@Column(unique = true, name = "NOM_UTILISATEUR_PK")
	private String userName;

//	@Email
	@Column(name = "EMAIL")
	private String email;

	@Column(name = "SI_ACTIF")
	private boolean enable;


	@Column(name = "ALERTE_SI_NB", columnDefinition="bigint(11) default 100")
	private int alerte = 100;


	@ManyToOne
	private Role role;
	
//	@ManyToMany(fetch = FetchType.EAGER)
//        @Fetch(FetchMode.SELECT)
//	@JoinTable(name = "TABLE_LIAISON_SECURITE_USER_PVT", joinColumns = @JoinColumn(name = "NOM_UTILISATEUR_FK", referencedColumnName = "NOM_UTILISATEUR_PK"), inverseJoinColumns = @JoinColumn(name = "CODE_MAGASIN_FK", referencedColumnName = "CODE_MAGASIN_PK"))
//	private List<PointDeVente> pvt;

	@Column(name = "NOM_DE_FAMILLE")
	private String familyName;

	@Column(name = "PRENOM")
	private String firstName;

	@Column(name = "MOT_DE_PASSE")
	private String password;

	@Column(name = "DEPARTEMENT_ACTIVITE")
	private String departement;

	@Column(name = "MATRICULE")
	private String matricule;

//	@NotNull
	@Column(name = "DATE_CREATION")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreation=new Date();

	@Column(name = "DATE_DERNIERE_CONNEXION")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateDerniereConnexion;

	public User() {
	}

	@Override
	public int hashCode() {
		return getUserName().hashCode();
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Date getDateDerniereConnexion() {
		return dateDerniereConnexion;
	}

	public void setDateDerniereConnexion(Date dateDerniereConnexion) {
		this.dateDerniereConnexion = dateDerniereConnexion;
	}

//	public List<PointDeVente> getPvt() {
//		return pvt;
//	}
//
//	public void setPvt(List<PointDeVente> pvt) {
//		this.pvt = pvt;
//	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
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
		final User other = (User) obj;
		if (getUserName() == null) {
			if (other.getUserName() != null) {
				return false;
			}
		} else if (!getUserName().equals(other.getUserName())) {
			return false;
		}
		return true;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password  =  DigestUtils.md5Hex(password);
	}

	public String getDepartement() {
		return departement;
	}

	public void setDepartement(String departement) {
		this.departement = departement;
	}
        
	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}


	public boolean hasRole(String role) {
		if(getRole() == null ){
			return false;
		}else if(getRole().getDesignation().equals(role)){
			return true;
		}
		
		return false;
	}
	
	

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public int getAlerte() {
		return alerte;
	}

	public void setAlerte(int alerte) {
		this.alerte = alerte;
	}

	

}
